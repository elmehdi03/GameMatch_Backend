# 📱 Guide d'Intégration Android - GameMatch Backend

## 🔗 Connexion Backend ↔ Frontend Android

### Configuration Backend

**URL de base de l'API :** `http://10.0.2.2:8080/api`

> ⚠️ **Important** : `10.0.2.2` est l'adresse spéciale de l'émulateur Android pour accéder à `localhost` de votre machine.
> - Émulateur Android : `http://10.0.2.2:8080/api`
> - Appareil physique : `http://VOTRE_IP_LOCAL:8080/api` (ex: `http://192.168.1.10:8080/api`)

---

## 📋 Endpoints Disponibles

### 1. **Inscription (Register)**

**Endpoint :** `POST /api/auth/register`

**Headers :**
```
Content-Type: application/json
```

**Corps de la requête (JSON) :**
```json
{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "SecurePass123",
  "fullname": "John Doe",
  "city": "Paris",
  "playstyle": "aggressive",
  "gamemode": "ranked",
  "bio": "Gamer passionné",
  "discordId": "john#1234"
}
```

**Réponse (200 OK) :**
```json
{
  "token": "DUMMY_TOKEN",
  "userId": 1,
  "username": "john_doe"
}
```

**Erreurs possibles :**
- `400 Bad Request` : Username déjà utilisé ou email déjà utilisé
- `400 Bad Request` : Validation échouée (champs manquants)

---

### 2. **Connexion (Login)**

**Endpoint :** `POST /api/auth/login`

**Headers :**
```
Content-Type: application/json
```

**Corps de la requête (JSON) :**
```json
{
  "usernameOrEmail": "john_doe",
  "password": "SecurePass123"
}
```

**Réponse (200 OK) :**
```json
{
  "token": "DUMMY_TOKEN",
  "userId": 1,
  "username": "john_doe"
}
```

**Erreurs possibles :**
- `404 Not Found` : Utilisateur non trouvé
- `400 Bad Request` : Mot de passe incorrect

---

## 🔧 Configuration Android (Retrofit)

### Étape 1 : Ajouter les dépendances dans `build.gradle`

```gradle
dependencies {
    // Retrofit pour les appels API
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    
    // OkHttp pour le logging (optionnel mais utile)
    implementation 'com.squareup.okhttp3:logging-interceptor:4.11.0'
    
    // Coroutines (si vous utilisez Kotlin)
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1'
}
```

### Étape 2 : Ajouter la permission Internet dans `AndroidManifest.xml`

```xml
<uses-permission android:name="android.permission.INTERNET" />

<!-- Pour Android 9+ (Cleartext Traffic) -->
<application
    android:usesCleartextTraffic="true"
    ...>
</application>
```

---

## 📦 Structure Android Recommandée

### 1. Modèles de données (Data Classes)

**AuthResponse.java / .kt**
```kotlin
data class AuthResponse(
    val token: String,
    val userId: Long,
    val username: String
)
```

**LoginRequest.java / .kt**
```kotlin
data class LoginRequest(
    val usernameOrEmail: String,
    val password: String
)
```

**RegisterRequest.java / .kt**
```kotlin
data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val fullname: String,
    val city: String,
    val playstyle: String,
    val gamemode: String,
    val bio: String? = null,
    val discordId: String? = null
)
```

---

### 2. Interface API (Retrofit)

**ApiService.kt**
```kotlin
interface ApiService {
    
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): AuthResponse
    
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse
}
```

---

### 3. Configuration Retrofit

**RetrofitClient.kt**
```kotlin
object RetrofitClient {
    
    // Pour l'émulateur Android
    private const val BASE_URL = "http://10.0.2.2:8080/api/"
    
    // Pour appareil physique, utilisez votre IP locale :
    // private const val BASE_URL = "http://192.168.1.10:8080/api/"
    
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
    
    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
```

---

### 4. Repository (Couche de données)

**AuthRepository.kt**
```kotlin
class AuthRepository {
    
    private val apiService = RetrofitClient.instance
    
    suspend fun register(request: RegisterRequest): Result<AuthResponse> {
        return try {
            val response = apiService.register(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun login(request: LoginRequest): Result<AuthResponse> {
        return try {
            val response = apiService.login(request)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
```

---

### 5. ViewModel (Architecture MVVM)

**AuthViewModel.kt**
```kotlin
class AuthViewModel : ViewModel() {
    
    private val repository = AuthRepository()
    
    private val _loginState = MutableLiveData<Result<AuthResponse>>()
    val loginState: LiveData<Result<AuthResponse>> = _loginState
    
    private val _registerState = MutableLiveData<Result<AuthResponse>>()
    val registerState: LiveData<Result<AuthResponse>> = _registerState
    
    fun login(usernameOrEmail: String, password: String) {
        viewModelScope.launch {
            val request = LoginRequest(usernameOrEmail, password)
            _loginState.value = repository.login(request)
        }
    }
    
    fun register(
        username: String,
        email: String,
        password: String,
        fullname: String,
        city: String,
        playstyle: String,
        gamemode: String,
        bio: String? = null,
        discordId: String? = null
    ) {
        viewModelScope.launch {
            val request = RegisterRequest(
                username, email, password, fullname, 
                city, playstyle, gamemode, bio, discordId
            )
            _registerState.value = repository.register(request)
        }
    }
}
```

---

### 6. Activity/Fragment (UI)

**LoginActivity.kt (exemple)**
```kotlin
class LoginActivity : AppCompatActivity() {
    
    private val viewModel: AuthViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        
        // Observer la réponse
        viewModel.loginState.observe(this) { result ->
            result.onSuccess { authResponse ->
                // Connexion réussie
                Toast.makeText(this, "Bienvenue ${authResponse.username}", Toast.LENGTH_SHORT).show()
                // Sauvegarder le token
                saveToken(authResponse.token)
                // Naviguer vers l'écran principal
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            result.onFailure { error ->
                // Erreur de connexion
                Toast.makeText(this, "Erreur: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        }
        
        // Bouton de connexion
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            viewModel.login(username, password)
        }
    }
    
    private fun saveToken(token: String) {
        val prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        prefs.edit().putString("auth_token", token).apply()
    }
}
```

---

## ✅ Checklist de Compatibilité

### Backend
- ✅ CORS configuré pour `http://10.0.2.2` (émulateur Android)
- ✅ Endpoints REST JSON (compatible avec Retrofit)
- ✅ Validation des données
- ✅ Réponses HTTP standards (200, 400, 404)

### Frontend Android (à vérifier)
- [ ] Retrofit configuré avec l'URL `http://10.0.2.2:8080/api/`
- [ ] Permission Internet dans AndroidManifest
- [ ] usesCleartextTraffic activé (Android 9+)
- [ ] Modèles de données compatibles avec les DTOs backend
- [ ] Gestion des erreurs HTTP

---

## 🧪 Tester la Connexion

### Méthode 1 : Démarrer le backend

```bash
cd C:\Users\ROG STRIX\IdeaProjects\backend_gamematch
.\gradlew.bat bootRun
```

Le backend démarre sur `http://localhost:8080/api`

### Méthode 2 : Tester avec l'émulateur Android

1. Lancez votre émulateur Android
2. Lancez votre application Android
3. Utilisez `http://10.0.2.2:8080/api` dans votre code

### Méthode 3 : Tester avec un appareil physique

1. Trouvez votre IP locale : `ipconfig` (Windows)
2. Remplacez `10.0.2.2` par votre IP (ex: `192.168.1.10`)
3. Assurez-vous que le téléphone et l'ordinateur sont sur le même réseau Wi-Fi

---

## 🔒 Sécurité (À implémenter plus tard)

Actuellement, le backend utilise un token "DUMMY_TOKEN". Pour la production :

1. **JWT (JSON Web Token)** sera implémenté
2. Ajouter l'header `Authorization: Bearer <token>` dans les requêtes
3. Stocker le token de manière sécurisée (EncryptedSharedPreferences)

---

## 🆘 Problèmes Courants

### Erreur : "Failed to connect to /10.0.2.2:8080"
- ✅ Vérifiez que le backend est démarré
- ✅ Vérifiez la permission Internet dans AndroidManifest
- ✅ Vérifiez `usesCleartextTraffic="true"`

### Erreur : "CORS policy"
- ✅ Le backend est déjà configuré pour accepter `http://10.0.2.2`

### Erreur 400 : "Validation failed"
- ✅ Vérifiez que tous les champs requis sont envoyés
- ✅ Vérifiez le format JSON

---

## 📞 Support

Consultez la documentation Swagger pour tester l'API :
**http://localhost:8080/api/swagger-ui.html**

---

*Document créé le 2025-12-11*

