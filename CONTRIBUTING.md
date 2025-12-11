# Guide de contribution

Merci de votre intérêt pour contribuer au projet GameMatch Backend ! 🎉

## 🌟 Comment contribuer

### Signaler un bug

1. Vérifiez que le bug n'a pas déjà été signalé dans les [Issues](../../issues)
2. Ouvrez une nouvelle issue avec un titre clair
3. Décrivez le bug en détail :
   - Étapes pour reproduire
   - Comportement attendu vs comportement observé
   - Version de Java, Spring Boot, etc.
   - Logs d'erreur si disponibles

### Proposer une nouvelle fonctionnalité

1. Ouvrez une issue avec le tag `enhancement`
2. Décrivez la fonctionnalité et son utilité
3. Attendez les retours avant de commencer l'implémentation

### Soumettre une Pull Request

1. **Forkez** le projet
2. **Clonez** votre fork :
   ```bash
   git clone https://github.com/votre-username/backend_gamematch.git
   ```

3. **Créez une branche** pour votre fonctionnalité :
   ```bash
   git checkout -b feature/ma-super-fonctionnalite
   ```

4. **Développez** votre fonctionnalité :
   - Suivez les conventions de code du projet
   - Ajoutez des tests si nécessaire
   - Mettez à jour la documentation

5. **Committez** vos changements :
   ```bash
   git add .
   git commit -m "feat: ajout de ma super fonctionnalité"
   ```

6. **Poussez** vers votre fork :
   ```bash
   git push origin feature/ma-super-fonctionnalite
   ```

7. **Ouvrez une Pull Request** depuis votre fork vers la branche `main` du projet original

## 📝 Conventions de code

### Style de code Java

- Suivez les conventions Java standard
- Utilisez des noms de variables et méthodes explicites en anglais
- Commentez le code complexe
- Maximum 120 caractères par ligne

### Convention de commit

Utilisez des messages de commit clairs suivant le format :

```
<type>: <description courte>

[corps optionnel]

[footer optionnel]
```

Types de commit :
- `feat`: Nouvelle fonctionnalité
- `fix`: Correction de bug
- `docs`: Documentation uniquement
- `style`: Formatage, points-virgules manquants, etc.
- `refactor`: Refactorisation du code
- `test`: Ajout de tests
- `chore`: Maintenance, mise à jour de dépendances

Exemples :
```
feat: ajout de l'authentification JWT
fix: correction du bug de connexion MySQL
docs: mise à jour du README avec les instructions Docker
```

### Structure des branches

- `main` : Branche principale stable
- `develop` : Branche de développement
- `feature/*` : Nouvelles fonctionnalités
- `fix/*` : Corrections de bugs
- `hotfix/*` : Corrections urgentes

## 🧪 Tests

Assurez-vous que tous les tests passent avant de soumettre votre PR :

```bash
.\gradlew.bat test
```

Si vous ajoutez du code, ajoutez les tests correspondants.

## 📚 Documentation

- Mettez à jour le README.md si nécessaire
- Ajoutez des commentaires JavaDoc pour les méthodes publiques
- Documentez les endpoints API avec les annotations Swagger

## ✅ Checklist avant soumission

- [ ] Le code compile sans erreur
- [ ] Tous les tests passent
- [ ] Les nouveaux tests sont ajoutés si nécessaire
- [ ] La documentation est mise à jour
- [ ] Le code suit les conventions du projet
- [ ] Les commits sont clairs et bien formatés
- [ ] La branche est à jour avec `main`

## 🤝 Code de conduite

- Soyez respectueux et courtois
- Acceptez les critiques constructives
- Concentrez-vous sur ce qui est le mieux pour la communauté
- Montrez de l'empathie envers les autres membres

## 📞 Besoin d'aide ?

- Ouvrez une issue avec le tag `question`
- Consultez la documentation dans le README.md
- Regardez les issues et PR existantes

## 📜 Licence

En contribuant, vous acceptez que vos contributions soient sous licence MIT.

---

Merci pour vos contributions ! 🙏

