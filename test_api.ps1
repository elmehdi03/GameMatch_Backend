Write-Host "=====================================" -ForegroundColor Cyan
Write-Host "Test de l API GameMatch Backend" -ForegroundColor Cyan
Write-Host "=====================================" -ForegroundColor Cyan
Write-Host ""

Write-Host "TEST 1 - Health Check..." -ForegroundColor Yellow
try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/api/health" -Method Get
    Write-Host "OK - Health Check OK" -ForegroundColor Green
    Write-Host "  Status: $($response.status)" -ForegroundColor Gray
    Write-Host "  Message: $($response.message)" -ForegroundColor Gray
} catch {
    Write-Host "FAILED - Health Check FAILED" -ForegroundColor Red
    Write-Host "  Error: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

Write-Host "TEST 2 - Swagger UI..." -ForegroundColor Yellow
try {
    $response = Invoke-WebRequest -Uri "http://localhost:8080/api/swagger-ui/index.html" -Method Get -UseBasicParsing
    if ($response.StatusCode -eq 200) {
        Write-Host "OK - Swagger UI accessible" -ForegroundColor Green
        Write-Host "  URL: http://localhost:8080/api/swagger-ui/index.html" -ForegroundColor Gray
    }
} catch {
    Write-Host "FAILED - Swagger UI non accessible" -ForegroundColor Red
    Write-Host "  Error: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

Write-Host "TEST 3 - OpenAPI Documentation..." -ForegroundColor Yellow
try {
    $response = Invoke-WebRequest -Uri "http://localhost:8080/api/api-docs" -Method Get -UseBasicParsing
    if ($response.StatusCode -eq 200) {
        Write-Host "OK - OpenAPI Docs accessible" -ForegroundColor Green
        Write-Host "  URL: http://localhost:8080/api/api-docs" -ForegroundColor Gray
        $json = $response.Content | ConvertFrom-Json
        Write-Host "  Title: $($json.info.title)" -ForegroundColor Gray
        Write-Host "  Version: $($json.info.version)" -ForegroundColor Gray
    }
} catch {
    Write-Host "FAILED - OpenAPI Docs non accessible" -ForegroundColor Red
    Write-Host "  Error: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

Write-Host "TEST 4 - Register endpoint..." -ForegroundColor Yellow
$registerData = @{
    username = "testuser_$(Get-Random)"
    email = "test$(Get-Random)@example.com"
    password = "Test123456"
    fullname = "Test User"
    city = "Paris"
    playstyle = "aggressive"
    gamemode = "ranked"
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/api/auth/register" -Method Post -Body $registerData -ContentType "application/json"
    Write-Host "OK - Register OK" -ForegroundColor Green
    Write-Host "  User ID: $($response.userId)" -ForegroundColor Gray
    Write-Host "  Username: $($response.username)" -ForegroundColor Gray
    Write-Host "  Token: $($response.token)" -ForegroundColor Gray
    $script:testUsername = $response.username
    $script:testUserId = $response.userId
} catch {
    Write-Host "FAILED - Register FAILED" -ForegroundColor Red
    Write-Host "  Error: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

if ($script:testUsername) {
    Write-Host "TEST 5 - Login endpoint..." -ForegroundColor Yellow
    $loginData = @{
        usernameOrEmail = $script:testUsername
        password = "Test123456"
    } | ConvertTo-Json

    try {
        $response = Invoke-RestMethod -Uri "http://localhost:8080/api/auth/login" -Method Post -Body $loginData -ContentType "application/json"
        Write-Host "OK - Login OK" -ForegroundColor Green
        Write-Host "  User ID: $($response.userId)" -ForegroundColor Gray
        Write-Host "  Username: $($response.username)" -ForegroundColor Gray
        Write-Host "  Token: $($response.token)" -ForegroundColor Gray
    } catch {
        Write-Host "FAILED - Login FAILED" -ForegroundColor Red
        Write-Host "  Error: $($_.Exception.Message)" -ForegroundColor Red
    }
} else {
    Write-Host "TEST 5 - Login endpoint - SKIPPED" -ForegroundColor Yellow
}
Write-Host ""

Write-Host "=====================================" -ForegroundColor Cyan
Write-Host "Tests termines!" -ForegroundColor Cyan
Write-Host "=====================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Accedez a Swagger UI:" -ForegroundColor Green
Write-Host "http://localhost:8080/api/swagger-ui/index.html" -ForegroundColor Cyan

