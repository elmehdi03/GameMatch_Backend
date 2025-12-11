# GUIDE RAPIDE - GameMatch Backend API

## ✅ TOUT FONCTIONNE !

Votre API GameMatch est maintenant opérationnelle.

---

## 🚀 Démarrage rapide

### 1. Démarrer l'application
```bash
.\gradlew.bat bootRun
```

### 2. Ouvrir Swagger UI
Dans votre navigateur : **http://localhost:8080/api/swagger-ui/index.html**

### 3. Tester l'API
```bash
.\test_api.ps1
```

---

## 📍 URLs principales

| Service | URL |
|---------|-----|
| **Swagger UI** | http://localhost:8080/api/swagger-ui/index.html |
| **Health Check** | http://localhost:8080/api/health |
| **Register** | POST http://localhost:8080/api/auth/register |
| **Login** | POST http://localhost:8080/api/auth/login |

---

## 🔑 Endpoints API

### 1. Créer un compte
```json
POST /api/auth/register
{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "SecurePass123",
  "fullname": "John Doe",
  "city": "Paris",
  "playstyle": "aggressive",
  "gamemode": "ranked"
}
```

### 2. Se connecter
```json
POST /api/auth/login
{
  "usernameOrEmail": "john_doe",
  "password": "SecurePass123"
}
```

---

## 🛠️ Dépannage rapide

### Port 8080 occupé ?
```bash
netstat -ano | findstr :8080
taskkill /F /PID <PID>
```

### Recompiler ?
```bash
.\gradlew.bat clean build
```

---

## 📁 Fichiers importants

- `STATUS_FINAL.md` - Documentation complète
- `RESOLUTION.md` - Détails des problèmes résolus
- `test_api.ps1` - Script de test automatique
- `application.properties` - Configuration de l'application
- `SecurityConfig.java` - Configuration de sécurité

---

## 🎯 Prochaines étapes

1. ✅ L'application fonctionne
2. ⏭️ Implémenter JWT pour l'authentification
3. ⏭️ Ajouter les endpoints CRUD pour Games, Matches, Messages
4. ⏭️ Ajouter des tests unitaires
5. ⏭️ Déployer en production

---

**Besoin d'aide ?** Consultez `STATUS_FINAL.md` pour la documentation complète.

**Tout est prêt ! 🎉**

