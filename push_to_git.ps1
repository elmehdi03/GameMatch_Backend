# Script de Push vers Git - GameMatch Backend
# Exécutez ce script pour pousser vos changements vers Git

Write-Host "`n🚀 PUSH VERS GIT - GameMatch Backend`n" -ForegroundColor Green

# Vérifier si on est dans le bon dossier
if (-not (Test-Path "build.gradle")) {
    Write-Host "❌ Erreur: Ce script doit être exécuté depuis la racine du projet!" -ForegroundColor Red
    exit 1
}

# 1. Vérifier le statut
Write-Host "📋 Étape 1: Vérification du statut Git..." -ForegroundColor Cyan
git status

Write-Host "`n"

# 2. Vérifier que .env n'est pas suivi
Write-Host "🔒 Étape 2: Vérification de sécurité..." -ForegroundColor Cyan
$envCheck = git ls-files | Select-String "^\.env$"
if ($envCheck) {
    Write-Host "⚠️  ATTENTION: Le fichier .env est suivi par Git!" -ForegroundColor Red
    Write-Host "   Exécutez: git rm --cached .env" -ForegroundColor Yellow
    exit 1
} else {
    Write-Host "✅ Sécurité OK: .env est bien ignoré" -ForegroundColor Green
}

Write-Host "`n"

# 3. Vérifier la remote
Write-Host "🌐 Étape 3: Vérification du dépôt distant..." -ForegroundColor Cyan
$remote = git remote -v

if (-not $remote) {
    Write-Host "⚠️  Aucun dépôt distant configuré!" -ForegroundColor Yellow
    Write-Host "`nVoulez-vous configurer un dépôt distant maintenant? (O/N)" -ForegroundColor Yellow
    $response = Read-Host

    if ($response -eq "O" -or $response -eq "o") {
        Write-Host "`nEntrez l'URL de votre dépôt Git:" -ForegroundColor Cyan
        Write-Host "Exemple: https://github.com/votre-username/backend_gamematch.git" -ForegroundColor Gray
        $repoUrl = Read-Host

        if ($repoUrl) {
            git remote add origin $repoUrl
            Write-Host "✅ Dépôt distant configuré!" -ForegroundColor Green
        } else {
            Write-Host "❌ URL invalide" -ForegroundColor Red
            exit 1
        }
    } else {
        Write-Host "`n📝 Pour configurer manuellement:" -ForegroundColor Yellow
        Write-Host "   git remote add origin <URL_DE_VOTRE_DEPOT>" -ForegroundColor Gray
        exit 0
    }
} else {
    Write-Host "✅ Dépôt distant configuré:" -ForegroundColor Green
    git remote -v
}

Write-Host "`n"

# 4. Demander confirmation
Write-Host "🔄 Prêt à pousser vers Git!" -ForegroundColor Green
Write-Host "`nVoulez-vous continuer? (O/N)" -ForegroundColor Yellow
$confirm = Read-Host

if ($confirm -ne "O" -and $confirm -ne "o") {
    Write-Host "`n❌ Push annulé par l'utilisateur" -ForegroundColor Yellow
    exit 0
}

Write-Host "`n"

# 5. Push
Write-Host "📤 Étape 4: Push vers le dépôt distant..." -ForegroundColor Cyan
$branch = git branch --show-current

try {
    git push origin $branch

    Write-Host "`n✅ SUCCÈS! Votre code a été poussé vers Git! 🎉" -ForegroundColor Green
    Write-Host "`n📊 Vérifiez votre dépôt distant pour confirmer:" -ForegroundColor Cyan
    git remote get-url origin

} catch {
    Write-Host "`n⚠️  Le push a échoué. Essayez avec --force-with-lease:" -ForegroundColor Yellow
    Write-Host "   git push origin $branch --force-with-lease" -ForegroundColor Gray
    Write-Host "`n⚠️  ATTENTION: --force-with-lease réécrit l'historique!" -ForegroundColor Red
    Write-Host "   N'utilisez cette commande que si vous êtes seul sur ce dépôt!" -ForegroundColor Red
    exit 1
}

Write-Host "`n🎮 GameMatch Backend est maintenant sur Git!" -ForegroundColor Green
Write-Host "📚 Consultez PUSH_INSTRUCTIONS.md pour plus d'informations`n" -ForegroundColor Cyan

