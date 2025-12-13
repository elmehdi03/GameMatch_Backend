# RÉSOLUTION DE L'ERREUR 500 - PUT /users/{userId}

## PROBLÈME IDENTIFIÉ
L'erreur 500 était causée par une `LazyInitializationException` de Hibernate :
```
org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: 
com.example.backend_gamematch.model.User.favoriteGames: could not initialize proxy - no Session
```

## CAUSE
Quand on essayait de sérialiser l'objet User en JSON dans `UserProfileResponse.fromUser()`, 
la collection `favoriteGames` était en chargement LAZY et la session Hibernate était déjà fermée.

## SOLUTION APPLIQUÉE

### 1. Ajout de Hibernate.initialize() dans UserService.java

```java
import org.hibernate.Hibernate;

@Transactional(readOnly = true)
public User getUserById(Long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    // Force loading of lazy collection
    Hibernate.initialize(user.getFavoriteGames());
    return user;
}

@Transactional
public User updateProfile(Long userId, UpdateProfileRequest request) {
    // ... mise à jour des champs ...
    User savedUser = userRepository.save(user);
    // Force loading of lazy collection before transaction ends
    Hibernate.initialize(savedUser.getFavoriteGames());
    return savedUser;
}
```

### 2. Création du DTO UserProfileResponse

Un DTO de réponse a été créé pour éviter les problèmes de sérialisation :
- `UserProfileResponse.java` - DTO propre sans relations circulaires
- Conversion manuelle de User → UserProfileResponse dans le Controller

## POUR TESTER

1. **Démarrez XAMPP** (MySQL doit être actif)

2. **Démarrez le backend** :
   ```bash
   cd "C:\Users\ROG STRIX\IdeaProjects\backend_gamematch"
   .\gradlew.bat bootRun
   ```

3. **Attendez le démarrage complet** (environ 30-40 secondes)

4. **Testez l'endpoint** :
   ```powershell
   .\simple_test.ps1
   ```
   
   OU manuellement avec Postman/curl :
   ```
   PUT http://localhost:8080/api/users/1
   Content-Type: application/json
   
   {
       "fullname": "John Doe Updated",
       "bio": "Joueur passionné",
       "city": "Paris",
       "discordId": "john#1234",
       "playstyle": "pushing",
       "gamemode": "ranked"
   }
   ```

## RÉSULTAT ATTENDU

✅ **200 OK** avec la réponse JSON complète incluant :
- id, username, email
- fullname, bio, city, discordId, playstyle, gamemode
- favoriteGames (liste des jeux)
- createdAt

## FICHIERS MODIFIÉS
1. `UserService.java` - Ajout de Hibernate.initialize()
2. `UserController.java` - Utilisation de UserProfileResponse + logging
3. `UserProfileResponse.java` - NOUVEAU - DTO de réponse
4. `UpdateProfileRequest.java` - Modifié pour les bons champs

## SI ÇA NE FONCTIONNE PAS

Vérifiez :
1. MySQL est démarré (XAMPP)
2. La base `gamematch` existe
3. Un utilisateur avec id=1 existe dans la table `users`
4. Le backend est bien démarré sur le port 8080

Pour vérifier le serveur :
```powershell
Invoke-RestMethod -Uri "http://localhost:8080/api/health"
```

