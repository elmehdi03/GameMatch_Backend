# Environment Variables

Ce fichier documente toutes les variables d'environnement utilisées par l'application.

## Base de données

```properties
# URL de connexion à la base de données MySQL
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/gamematch_db

# Nom d'utilisateur de la base de données
SPRING_DATASOURCE_USERNAME=gamematch_user

# Mot de passe de la base de données
SPRING_DATASOURCE_PASSWORD=your_secure_password
```

## Configuration du serveur

```properties
# Port sur lequel l'application écoute (défaut: 8080)
SERVER_PORT=8080

# Contexte de l'application (défaut: /api)
SERVER_SERVLET_CONTEXT_PATH=/api
```

## Configuration JPA/Hibernate

```properties
# Mode de mise à jour du schéma (none, validate, update, create, create-drop)
SPRING_JPA_HIBERNATE_DDL_AUTO=update

# Afficher les requêtes SQL dans les logs (true/false)
SPRING_JPA_SHOW_SQL=false

# Formater les requêtes SQL (true/false)
SPRING_JPA_PROPERTIES_HIBERNATE_FORMAT_SQL=false
```

## Configuration de sécurité (à venir)

```properties
# Clé secrète pour la génération des tokens JWT
JWT_SECRET=your_jwt_secret_key_here

# Durée de validité des tokens JWT en millisecondes (défaut: 24h)
JWT_EXPIRATION=86400000
```

## Configuration du logging

```properties
# Niveau de log (TRACE, DEBUG, INFO, WARN, ERROR)
LOGGING_LEVEL_ROOT=INFO
LOGGING_LEVEL_COM_EXAMPLE_BACKEND_GAMEMATCH=DEBUG

# Fichier de log
LOGGING_FILE_NAME=logs/gamematch.log
```

## Configuration CORS (pour le développement)

```properties
# Origines autorisées (séparées par des virgules)
CORS_ALLOWED_ORIGINS=http://localhost:3000,http://localhost:4200
```

## Utilisation

### Méthode 1 : Fichier .env (recommandé pour le développement)

Créez un fichier `.env` à la racine du projet :

```env
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/gamematch_db
SPRING_DATASOURCE_USERNAME=gamematch_user
SPRING_DATASOURCE_PASSWORD=SecurePassword123
SERVER_PORT=8080
```

**Note** : Le fichier `.env` est ignoré par Git pour des raisons de sécurité.

### Méthode 2 : Variables d'environnement système

**Windows (PowerShell) :**
```powershell
$env:SPRING_DATASOURCE_URL="jdbc:mysql://localhost:3306/gamematch_db"
$env:SPRING_DATASOURCE_USERNAME="gamematch_user"
$env:SPRING_DATASOURCE_PASSWORD="SecurePassword123"
```

**Linux/Mac (bash) :**
```bash
export SPRING_DATASOURCE_URL="jdbc:mysql://localhost:3306/gamematch_db"
export SPRING_DATASOURCE_USERNAME="gamematch_user"
export SPRING_DATASOURCE_PASSWORD="SecurePassword123"
```

### Méthode 3 : Arguments de ligne de commande

```bash
./gradlew bootRun --args='--spring.datasource.url=jdbc:mysql://localhost:3306/gamematch_db --spring.datasource.username=gamematch_user --spring.datasource.password=SecurePassword123'
```

### Méthode 4 : Configuration IntelliJ IDEA

1. Run → Edit Configurations
2. Sélectionnez votre configuration Spring Boot
3. Dans "Environment variables", ajoutez : 
   ```
   SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/gamematch_db;SPRING_DATASOURCE_USERNAME=gamematch_user;SPRING_DATASOURCE_PASSWORD=SecurePassword123
   ```

## Production

Pour la production, utilisez des gestionnaires de secrets comme :
- **AWS Secrets Manager**
- **Azure Key Vault**
- **HashiCorp Vault**
- **Docker Secrets**
- **Kubernetes Secrets**

**Ne committez JAMAIS de secrets dans Git !**

