# Changelog

Toutes les modifications notables de ce projet seront documentées dans ce fichier.

Le format est basé sur [Keep a Changelog](https://keepachangelog.com/fr/1.0.0/),
et ce projet adhère au [Semantic Versioning](https://semver.org/lang/fr/).

## [Unreleased]

### À venir
- Authentification JWT
- Système de matching avancé
- WebSockets pour le chat en temps réel
- Tests d'intégration complets

## [0.0.1-SNAPSHOT] - 2025-01-11

### Ajouté
- Configuration initiale du projet Spring Boot 3.2.1
- Modèles de données : User, Game, Match, Message
- Authentification basique avec Spring Security
- Endpoints REST pour l'inscription et la connexion
- Documentation API avec Swagger UI / SpringDoc
- Configuration CORS pour le développement frontend
- Validation des données avec Spring Validation
- Intégration MySQL avec Spring Data JPA
- Support de Lombok pour réduire le boilerplate
- Scripts SQL pour la création de la base de données
- README.md complet avec documentation
- Licence MIT
- Guide de contribution (CONTRIBUTING.md)
- Configuration .gitignore adaptée
- Configuration .gitattributes pour la gestion des fins de ligne
- Configuration .editorconfig pour la standardisation du code

### Configuration
- Java 17
- Gradle avec wrapper
- MySQL 8.0
- Spring Boot DevTools pour le développement

### Sécurité
- Configuration Spring Security avec CORS
- Encodage BCrypt pour les mots de passe
- Protection CSRF désactivée pour l'API REST

### Documentation
- README principal avec guide d'installation
- README rapide pour démarrage (README_QUICK.md)
- Documentation Swagger accessible à /api/swagger-ui/index.html
- Guide de contribution
- Scripts PowerShell de test

---

Format des sections :
- `Added` : Nouvelles fonctionnalités
- `Changed` : Modifications de fonctionnalités existantes
- `Deprecated` : Fonctionnalités obsolètes mais encore disponibles
- `Removed` : Fonctionnalités supprimées
- `Fixed` : Corrections de bugs
- `Security` : Correctifs de sécurité

