# 🎮 GameMatch Backend API

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.1-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Une API REST moderne pour la plateforme de mise en relation de joueurs **GameMatch**. Cette application permet aux gamers de créer des profils, choisir leurs jeux favoris, et gérer leurs préférences de jeu.

## ✨ Fonctionnalités

- 🔐 **Authentification** : Système d'inscription et de connexion sécurisé
- 👤 **Gestion des profils** : Profils utilisateurs avec préférences (style de jeu, mode, ville, bio, Discord ID)
- 🎮 **Jeux favoris** : Ajout, modification et suppression de jeux favoris
- 📊 **Documentation API** : Interface Swagger UI pour tester l'API
- 🗄️ **Base de données** : Persistance MySQL avec Spring Data JPA
- ✅ **Validation** : Validation automatique des entrées
- 📱 **Compatible Android** : CORS configuré pour les applications mobiles

## 🛠️ Technologies

- **Backend Framework** : Spring Boot 3.2.1
- **Langage** : Java 17
- **Build Tool** : Gradle
- **Base de données** : MySQL
- **ORM** : Spring Data JPA / Hibernate
- **Sécurité** : Spring Security
- **Documentation API** : SpringDoc OpenAPI (Swagger)
- **Validation** : Spring Validation
- **Outils** : Lombok, DevTools

## 📋 Prérequis

Avant de commencer, assurez-vous d'avoir installé :

- ☕ **Java JDK 17** ou supérieur
- 🗄️ **MySQL 8.0** ou supérieur
- 🔧 **Gradle** (inclus via le wrapper)
- 🖥️ **Un IDE** (IntelliJ IDEA, Eclipse, VS Code recommandés)

## 🚀 Installation et démarrage

### 1. Cloner le dépôt

```bash
git clone <votre-url-repo>
cd backend_gamematch
```

### 2. Configurer la base de données

Créez une base de données MySQL nommée `gamematch` :

```sql
CREATE DATABASE gamematch;
```

Ou utilisez XAMPP et créez la base via phpMyAdmin.

### 3. Configuration

Le fichier `application.properties` est déjà configuré pour :
- MySQL sur `localhost:3306`
- Base de données : `gamematch`
- Utilisateur : `root`
- Pas de mot de passe (configuration XAMPP par défaut)

Si votre configuration MySQL est différente, modifiez `src/main/resources/application.properties` :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gamematch
spring.datasource.username=votre_user
spring.datasource.password=votre_password
```

### 4. Compiler et lancer l'application

**Sur Windows :**
```bash
.\gradlew.bat bootRun
```

**Sur Linux/Mac :**
```bash
./gradlew bootRun
```

L'application démarre sur **http://localhost:8080**

## 📚 Documentation de l'API

### Swagger UI

Accédez à la documentation interactive de l'API :

🔗 **http://localhost:8080/api/swagger-ui/index.html**

### Endpoints principaux

#### Authentification
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| `POST` | `/api/auth/register` | Créer un compte utilisateur |
| `POST` | `/api/auth/login` | Se connecter |

#### Jeux
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| `GET` | `/api/games` | Liste tous les jeux disponibles |
| `POST` | `/api/games` | Ajouter un nouveau jeu |

#### Jeux Favoris
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| `GET` | `/api/users/{userId}/favorite-games` | Voir les jeux favoris d'un utilisateur |
| `POST` | `/api/users/{userId}/favorite-games` | Ajouter des jeux aux favoris |
| `PUT` | `/api/users/{userId}/favorite-games` | Remplacer tous les jeux favoris |
| `DELETE` | `/api/users/{userId}/favorite-games` | Supprimer des jeux des favoris |

#### Santé
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| `GET` | `/api/health` | Vérifier si l'API fonctionne |

Pour plus de détails, consultez **[API_SPECIFICATION.md](API_SPECIFICATION.md)**

### Exemples de requêtes

#### S'inscrire avec jeux favoris

```json
POST /api/auth/register

{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "SecurePass123",
  "fullname": "John Doe",
  "city": "Paris",
  "playstyle": "aggressive",
  "gamemode": "ranked",
  "favoriteGameIds": [1, 2, 3]
}
```

#### Se connecter

```json
POST /api/auth/login

{
  "usernameOrEmail": "john_doe",
  "password": "SecurePass123"
}
```

## 🧪 Tests

### Exécuter les tests

```bash
# Windows
.\gradlew.bat test

# Linux/Mac
./gradlew test
```

### Tester l'API manuellement

```powershell
# Health check
Invoke-RestMethod -Uri "http://localhost:8080/api/health" -Method Get

# Get games
Invoke-RestMethod -Uri "http://localhost:8080/api/games" -Method Get
```

## 📱 Intégration Android

Ce backend est **prêt à être utilisé** avec votre application Android.

### 📖 Guides disponibles

1. **[API_SPECIFICATION.md](API_SPECIFICATION.md)** - Spécification complète de l'API
2. **[COMPATIBILITY_GUIDE.md](COMPATIBILITY_GUIDE.md)** - Guide de compatibilité Android
3. **[DECISION_FRONTEND.md](DECISION_FRONTEND.md)** - Faut-il reconstruire ou adapter votre frontend ?

### ⚙️ Configuration

**Base URL pour émulateur :** `http://10.0.2.2:8080/api/`  
**Base URL pour téléphone :** `http://[IP_DE_VOTRE_PC]:8080/api/`

### ✅ Fonctionnalités compatibles Android

- CORS configuré pour émulateur (`10.0.2.2`)
- Endpoints REST JSON standard
- Compatible Retrofit/OkHttp
- Validation des données côté serveur

## 📁 Structure du projet

```
backend_gamematch/
├── src/main/java/com/example/backend_gamematch/
│   ├── config/                 # Configurations (Security, CORS, Swagger)
│   ├── controller/             # Contrôleurs REST
│   │   ├── AuthController      # Inscription/Connexion
│   │   ├── GameController      # Gestion des jeux
│   │   ├── UserController      # Jeux favoris
│   │   └── HealthController    # Health check
│   ├── dto/                    # Data Transfer Objects
│   │   ├── request/            # RegisterRequest, LoginRequest, etc.
│   │   └── response/           # AuthResponse
│   ├── exception/              # Gestion des exceptions
│   ├── model/                  # Entités JPA (User, Game, Match, etc.)
│   ├── repository/             # Spring Data JPA Repositories
│   ├── security/               # UserDetailsService
│   ├── service/                # Logique métier
│   │   ├── AuthService         # Authentification
│   │   └── UserService         # Gestion des jeux favoris
│   └── BackendGameMatchApplication.java
├── src/main/resources/
│   └── application.properties
├── build.gradle
├── .gitignore
└── README.md
```

## 🔧 Configuration avancée

### Variables d'environnement

Le fichier `application.properties` est configuré par défaut pour XAMPP. Pour une configuration personnalisée :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gamematch
spring.datasource.username=root
spring.datasource.password=
server.port=8080
server.servlet.context-path=/api
springdoc.api-docs.path=/api/v3/api-docs
```

## 🐛 Dépannage

### Le port 8080 est déjà utilisé

```bash
# Windows
netstat -ano | findstr :8080
taskkill /F /PID <PID>

# Linux/Mac
lsof -i :8080
kill -9 <PID>
```

### Problèmes de connexion à la base de données

- Vérifiez que MySQL est démarré
- Vérifiez les credentials dans `application.properties`
- Vérifiez que la base de données existe

### Recompiler le projet

```bash
.\gradlew.bat clean build
```

## 🗺️ Roadmap

- [x] Configuration Spring Boot
- [x] Modèles de données (User, Game, Match, Message, Notification)
- [x] Authentification avec Spring Security
- [x] Gestion des jeux favoris
- [x] Documentation Swagger UI
- [x] CORS pour Android
- [ ] Authentification JWT (actuellement token dummy)
- [ ] Système de matching utilisateurs
- [ ] WebSockets pour chat en temps réel
- [ ] Notifications push
- [ ] Tests d'intégration
- [ ] Déploiement (Docker/Cloud)

## 📄 Licence

Ce projet est sous licence MIT. Voir le fichier [LICENSE](LICENSE) pour plus de détails.

## 📞 Support

Pour toute question ou problème d'intégration Android, consultez :
- **[DECISION_FRONTEND.md](DECISION_FRONTEND.md)** - Guide de décision adapter/reconstruire
- **[API_SPECIFICATION.md](API_SPECIFICATION.md)** - Documentation complète de l'API
- **[COMPATIBILITY_GUIDE.md](COMPATIBILITY_GUIDE.md)** - Checklist de compatibilité

---

**Développé avec ☕ et Spring Boot**

## 🙏 Remerciements

- Spring Boot Team pour le framework exceptionnel
- La communauté open source pour les bibliothèques utilisées
- Swagger pour la documentation interactive

---

## 🔧 Dernières Corrections (Décembre 2024)

### ✅ Résolution de l'erreur 500 lors de la mise à jour du profil

**Problème corrigé :** `LazyInitializationException` lors de l'accès aux jeux favoris

**Solution appliquée :**
- Ajout de `Hibernate.initialize()` pour forcer le chargement des collections LAZY
- Création du DTO `UserProfileResponse` pour une sérialisation propre
- Ajout de logging détaillé pour faciliter le débogage

**Endpoints affectés :**
- `GET /api/users/{userId}` - Récupération du profil ✅
- `PUT /api/users/{userId}` - Mise à jour du profil ✅

**Compatibilité Android :** 100% fonctionnelle

---

**Made with ❤️ for gamers**

