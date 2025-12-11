# 🎮 GameMatch Backend API

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.1-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Une API REST moderne pour la plateforme de mise en relation de joueurs **GameMatch**. Cette application permet aux gamers de créer des profils, de trouver des partenaires de jeu compatibles et de gérer leurs sessions de gaming.

## ✨ Fonctionnalités

- 🔐 **Authentification & Autorisation** : Système d'inscription et de connexion sécurisé avec Spring Security
- 👤 **Gestion des profils** : Création et gestion de profils utilisateurs avec préférences de jeu
- 🎯 **Matching de joueurs** : Système de mise en relation basé sur les préférences (style de jeu, mode, ville)
- 📊 **Documentation API interactive** : Interface Swagger UI pour tester l'API
- 🗄️ **Persistance des données** : Base de données MySQL avec Spring Data JPA
- ✅ **Validation des données** : Validation automatique des entrées utilisateur

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

Créez une base de données MySQL et exécutez le script de création :

```bash
mysql -u root -p < create_database.sql
```

Ou créez manuellement la base de données :

```sql
CREATE DATABASE gamematch_db;
CREATE USER 'gamematch_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON gamematch_db.* TO 'gamematch_user'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Configurer l'application

**Méthode 1 : Utiliser le fichier .env (Recommandé)**

Copiez le fichier `.env.example` en `.env` :

```bash
# Windows PowerShell
copy .env.example .env

# Linux/Mac
cp .env.example .env
```

Puis éditez le fichier `.env` avec vos paramètres :

```properties
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/gamematch_db
SPRING_DATASOURCE_USERNAME=gamematch_user
SPRING_DATASOURCE_PASSWORD=votre_mot_de_passe
```

> ⚠️ **Note** : Si vous n'avez pas de mot de passe MySQL, laissez `SPRING_DATASOURCE_PASSWORD` vide

**Méthode 2 : Utiliser application.properties**

Alternativement, modifiez directement `src/main/resources/application.properties` avec vos paramètres

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

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| `POST` | `/api/auth/register` | Créer un nouveau compte utilisateur |
| `POST` | `/api/auth/login` | Se connecter et obtenir un token |
| `GET` | `/api/users` | Lister tous les utilisateurs |
| `GET` | `/api/users/{id}` | Obtenir un utilisateur par ID |
| `PUT` | `/api/users/{id}` | Mettre à jour un utilisateur |
| `DELETE` | `/api/users/{id}` | Supprimer un utilisateur |
| `GET` | `/api/health` | Vérifier l'état de l'application |

### Exemples de requêtes

#### S'inscrire

```bash
POST /api/auth/register
Content-Type: application/json

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

#### Se connecter

```bash
POST /api/auth/login
Content-Type: application/json

{
  "usernameOrEmail": "john_doe",
  "password": "SecurePass123"
}
```

## 🧪 Tests

### Exécuter les tests unitaires

```bash
.\gradlew.bat test
```

### Script de test automatique (Windows)

```powershell
.\test_api.ps1
```

## 📁 Structure du projet

```
backend_gamematch/
├── src/
│   ├── main/
│   │   ├── java/com/example/backend_gamematch/
│   │   │   ├── config/          # Configurations (Security, CORS, Swagger)
│   │   │   ├── controller/      # Contrôleurs REST
│   │   │   ├── dto/             # Data Transfer Objects
│   │   │   ├── exception/       # Gestion des exceptions
│   │   │   ├── model/           # Entités JPA
│   │   │   ├── repository/      # Repositories Spring Data
│   │   │   ├── security/        # Configuration de sécurité
│   │   │   ├── service/         # Logique métier
│   │   │   └── BackendGameMatchApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/                    # Tests unitaires et d'intégration
├── gradle/                      # Gradle Wrapper
├── build.gradle                 # Configuration Gradle
├── .gitignore
├── LICENSE
└── README.md
```

## 🔧 Configuration

### Fichier application.properties

Principales propriétés configurables :

```properties
# Port du serveur
server.port=8080

# Configuration de la base de données
spring.datasource.url=jdbc:mysql://localhost:3306/gamematch_db
spring.datasource.username=gamematch_user
spring.datasource.password=your_password

# Configuration JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Configuration Swagger
springdoc.swagger-ui.path=/api/swagger-ui.html
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

- [x] Configuration de base du projet Spring Boot
- [x] Modèles de données (User, Game, Match, Message)
- [x] Authentification basique avec Spring Security
- [x] Documentation Swagger UI
- [ ] Authentification JWT
- [ ] Système de matching avancé
- [ ] WebSockets pour le chat en temps réel
- [ ] API de gestion des parties
- [ ] Tests d'intégration complets
- [ ] Déploiement Docker
- [ ] CI/CD Pipeline

## 🤝 Contribution

Les contributions sont les bienvenues ! Pour contribuer :

1. Forkez le projet
2. Créez une branche pour votre fonctionnalité (`git checkout -b feature/AmazingFeature`)
3. Committez vos changements (`git commit -m 'Add some AmazingFeature'`)
4. Poussez vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrez une Pull Request

## 📄 Licence

Ce projet est sous licence MIT. Voir le fichier [LICENSE](LICENSE) pour plus de détails.

## 👥 Auteurs

- Votre nom - Développeur principal

## 🙏 Remerciements

- Spring Boot Team pour le framework exceptionnel
- La communauté open source pour les bibliothèques utilisées
- Swagger pour la documentation interactive

---

**Made with ❤️ for gamers**

