# Résolution des problèmes - GameMatch Backend

## Problèmes résolus

### 1. Erreur 500 - Application ne démarrait pas correctement

**Causes identifiées :**
- Problème de sérialisation JSON avec les relations bidirectionnelles entre `User` et `Game`
- Port 8080 déjà utilisé par une ancienne instance

**Solutions appliquées :**
- ✅ Ajout de `@JsonIgnore` sur la relation `users` dans `Game.java`
- ✅ Ajout de `@JsonIgnore` sur le champ `password` dans `User.java` (sécurité)
- ✅ Arrêt du processus qui utilisait le port 8080

### 2. Erreur 404 - Swagger UI inaccessible

**Cause :**
- Le `context-path=/api` nécessite d'accéder à Swagger via l'URL complète

**Solution :**
- ✅ Configuration correcte de SpringDoc avec le context-path
- ✅ Ajout de `/api-docs/**` aux endpoints publics dans SecurityConfig

### 3. Erreur 403 - Accès refusé

**Cause :**
- Configuration de sécurité trop restrictive

**Solution :**
- ✅ Ajout de tous les endpoints Swagger et OpenAPI aux URLs publiques
- ✅ Ajout du endpoint `/health` pour les tests
- ✅ Configuration CORS correcte

## Configuration finale

### application.properties
```properties
# Contexte de l'application
server.servlet.context-path=/api
server.port=8080

# Base de données
spring.datasource.url=jdbc:mysql://localhost:3306/gamematch?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update  # Changé de 'create' à 'update'
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.open-in-view=false

# SpringDoc OpenAPI / Swagger
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.enabled=true
springdoc.swagger-ui.operationsSorter=method
springdoc.swagger-ui.tagsSorter=alpha
```

### SecurityConfig.java
Endpoints publics configurés :
- `/auth/**` - Authentification
- `/health/**` - Health check
- `/swagger-ui/**` - Interface Swagger
- `/api-docs/**` - Documentation OpenAPI
- Tous les endpoints Swagger nécessaires

## URLs d'accès

### Application principale
- **Base URL**: `http://localhost:8080/api`
- **Health Check**: `http://localhost:8080/api/health`

### Swagger / OpenAPI
- **Swagger UI**: `http://localhost:8080/api/swagger-ui/index.html`
- **OpenAPI Docs (JSON)**: `http://localhost:8080/api/api-docs`
- **OpenAPI Docs (YAML)**: `http://localhost:8080/api/api-docs.yaml`

### Endpoints d'authentification
- **Register**: `POST http://localhost:8080/api/auth/register`
- **Login**: `POST http://localhost:8080/api/auth/login`

## Test des endpoints

### Test Health Check
```bash
curl http://localhost:8080/api/health
```

Réponse attendue:
```json
{
  "status": "UP",
  "message": "GameMatch API is running"
}
```

### Test Register
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123",
    "fullname": "Test User",
    "city": "Paris"
  }'
```

### Test Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "usernameOrEmail": "testuser",
    "password": "password123"
  }'
```

## Fichiers modifiés

1. **SecurityConfig.java** - Configuration de sécurité améliorée
2. **application.properties** - Configuration Swagger et ddl-auto
3. **User.java** - Ajout @JsonIgnore sur password
4. **Game.java** - Ajout @JsonIgnore sur users
5. **HealthController.java** - Nouveau endpoint de health check

## Prochaines étapes recommandées

1. **Implémenter JWT** pour l'authentification (actuellement on utilise "DUMMY_TOKEN")
2. **Ajouter des tests unitaires** pour les services
3. **Configurer la validation** des données d'entrée
4. **Ajouter des logs structurés** pour le monitoring
5. **Documenter les endpoints** avec des annotations Swagger (@Operation, @ApiResponse)
6. **Sécuriser davantage** l'application (rate limiting, HTTPS, etc.)

## Commandes utiles

### Démarrer l'application
```bash
.\gradlew.bat bootRun
```

### Compiler et builder
```bash
.\gradlew.bat clean build
```

### Arrêter un processus sur le port 8080
```bash
# Trouver le PID
netstat -ano | findstr :8080

# Tuer le processus
taskkill /F /PID <PID>
```

---

✅ **Statut actuel : Application fonctionnelle et accessible**

