-- Script pour recréer la base de données gamematch proprement
DROP DATABASE IF EXISTS gamematch;
CREATE DATABASE gamematch CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE gamematch;

-- Afficher un message de confirmation
SELECT 'Base de données gamematch créée avec succès!' AS message;
SELECT 'Vous pouvez maintenant démarrer votre application Spring Boot.' AS info;

