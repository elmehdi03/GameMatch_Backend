# 🤝 Guide de Contribution - GameMatch Backend

Merci de votre intérêt pour contribuer au projet GameMatch ! Ce guide vous aidera à soumettre vos contributions de manière efficace.

## 📋 Table des matières

- [Code de conduite](#code-de-conduite)
- [Comment contribuer](#comment-contribuer)
- [Configuration de l'environnement](#configuration-de-lenvironnement)
- [Standards de code](#standards-de-code)
- [Processus de Pull Request](#processus-de-pull-request)

## 🌟 Code de conduite

En participant à ce projet, vous acceptez de maintenir un environnement respectueux et inclusif pour tous.

## 🚀 Comment contribuer

### Signaler un bug

1. Vérifiez que le bug n'a pas déjà été signalé dans les Issues
2. Ouvrez une nouvelle Issue avec :
   - Un titre clair et descriptif
   - Une description détaillée du problème
   - Les étapes pour reproduire le bug
   - Le comportement attendu vs le comportement observé
   - Des captures d'écran si pertinent
   - Votre environnement (OS, version Java, etc.)

### Proposer une nouvelle fonctionnalité

1. Ouvrez une Issue pour discuter de la fonctionnalité
2. Décrivez le problème que cela résout
3. Proposez une solution ou une implémentation

### Soumettre du code

1. Fork le projet
2. Créez une branche pour votre fonctionnalité (`git checkout -b feature/AmazingFeature`)
3. Committez vos changements (`git commit -m 'Add some AmazingFeature'`)
4. Pushez vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrez une Pull Request

## ⚙️ Configuration de l'environnement

### Prérequis

- Java JDK 17+
- MySQL 8.0+
- Gradle (inclus via wrapper)
- Git

### Installation

```bash
# Cloner votre fork
git clone https://github.com/VOTRE_USERNAME/backend_gamematch.git
cd backend_gamematch

# Configurer l'upstream
git remote add upstream https://github.com/ORIGINAL_OWNER/backend_gamematch.git

# Créer votre fichier .env
copy .env.example .env

# Lancer l'application
./gradlew bootRun
```

## 📝 Standards de code

### Style de code Java

- Suivre les conventions Java standard
- Utiliser les annotations Lombok pour réduire le boilerplate
- Commenter le code complexe
- Nommer les variables de manière descriptive

### Structure des commits

```
type(scope): subject

body

footer
```

**Types :**
- `feat`: Nouvelle fonctionnalité
- `fix`: Correction de bug
- `docs`: Documentation uniquement
- `style`: Formatage, point-virgules manquants, etc.
- `refactor`: Refactoring de code
- `test`: Ajout de tests
- `chore`: Maintenance

**Exemple :**
```
feat(auth): add JWT token refresh endpoint

Implement automatic token refresh mechanism to improve user experience
and reduce the need for frequent re-authentication.

Closes #123
```

### Tests

- Écrire des tests unitaires pour toute nouvelle fonctionnalité
- Maintenir une couverture de code > 70%
- Exécuter tous les tests avant de soumettre : `./gradlew test`

### Documentation

- Mettre à jour le README si nécessaire
- Documenter les endpoints API avec Swagger annotations
- Ajouter des commentaires JavaDoc pour les méthodes publiques

## 🔄 Processus de Pull Request

### Avant de soumettre

- [ ] Le code compile sans erreur
- [ ] Tous les tests passent
- [ ] Le code suit les standards du projet
- [ ] La documentation est à jour
- [ ] Les commits sont propres et descriptifs

### Template de Pull Request

```markdown
## Description
Brief description of what this PR does

## Type de changement
- [ ] Bug fix
- [ ] Nouvelle fonctionnalité
- [ ] Breaking change
- [ ] Documentation

## Tests
- [ ] Tests unitaires ajoutés/modifiés
- [ ] Tests manuels effectués

## Checklist
- [ ] Mon code suit les standards du projet
- [ ] J'ai commenté mon code, notamment les parties complexes
- [ ] J'ai mis à jour la documentation
- [ ] Mes changements ne génèrent pas de nouveaux warnings
- [ ] J'ai ajouté des tests qui prouvent que ma correction est efficace
```

## 🆘 Besoin d'aide ?

N'hésitez pas à :
- Ouvrir une Issue pour poser des questions
- Rejoindre nos discussions
- Consulter la documentation existante

## 📜 Licence

En contribuant, vous acceptez que vos contributions soient sous la même licence MIT que le projet.

---

Merci de contribuer à GameMatch ! 🎮✨

