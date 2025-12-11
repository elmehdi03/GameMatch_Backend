# 🚀 GameMatch Backend - URLs d'Accès

## ✅ Configuration Actuelle

- **Port** : 8080
- **Context Path** : /api
- **Base URL** : http://localhost:8080/api

---

## 📍 URLs Swagger / OpenAPI

### ✨ Interface Swagger UI (Documentation Interactive)
```
http://localhost:8080/api/swagger-ui.html
```
**OU**
```
http://localhost:8080/api/swagger-ui/index.html
```

### 📄 Documentation OpenAPI (JSON)
```
http://localhost:8080/api/v3/api-docs
```

---

## 🔐 Endpoints d'Authentification

### Inscription
```http
POST http://localhost:8080/api/auth/register
Content-Type: application/json

{
  "username": "testuser",
  "email": "test@example.com",
  "password": "password123"
}
```

### Connexion
```http
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "username": "testuser",
  "password": "password123"
}
```

---

## 🛠️ Démarrage de l'Application

### Option 1 : Depuis IntelliJ
1. Ouvrir `BackendGameMatchApplication.java`
2. Cliquer sur Run ▶️

### Option 2 : Via Gradle
```bash
.\gradlew bootRun
```

---

## ⚠️ Important

**Pourquoi `/api` dans l'URL ?**

Vous avez configuré `server.servlet.context-path=/api` dans `application.properties`. 
Cela signifie que TOUS vos endpoints sont préfixés par `/api`, y compris Swagger.

**Pour accéder à Swagger, utilisez :**
👉 **http://localhost:8080/api/swagger-ui.html**

---

## 🔧 Si Vous Voulez Changer

### Pour que Swagger soit accessible sur http://localhost:8080/swagger-ui.html (sans /api)

Modifiez `application.properties` :
```properties
# Supprimer ou commenter cette ligne :
# server.servlet.context-path=/api
```

Puis vos endpoints deviendraient :
- Auth : `http://localhost:8080/auth/register`
- Swagger : `http://localhost:8080/swagger-ui.html`

---

## ✅ Vérification Rapide

Une fois l'application démarrée, testez :

1. **Backend actif ?**
   ```bash
   curl http://localhost:8080/api/v3/api-docs
   ```

2. **Swagger accessible ?**
   Ouvrez dans votre navigateur :
   ```
   http://localhost:8080/api/swagger-ui.html
   ```

---

## 🎯 Endpoints Publics (Pas d'authentification requise)

- ✅ `/api/auth/**` - Tous les endpoints d'authentification
- ✅ `/api/swagger-ui/**` - Interface Swagger
- ✅ `/api/v3/api-docs/**` - Documentation OpenAPI

Tous les autres endpoints nécessitent une authentification.

