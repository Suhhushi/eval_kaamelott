# Eval Kaamelott

## Description

**Eval Kaamelott** est une application backend développée avec Spring Boot pour gérer des quêtes et chevaliers dans un univers inspiré de Kaamelott.
Elle permet de gérer les chevaliers, leurs quêtes, les participations, ainsi que les statistiques d'activité.

---

## Fonctionnalités principales

* Gestion des chevaliers : création, consultation, recherche par caractéristique principale.
* Gestion des quêtes : consultation, affectation des chevaliers, filtres sur les quêtes (difficulté aberrante, effectif manquant, les plus longues, période).
* Gestion des participations chevalier-quête : assigner, retirer, obtenir les participants d’une quête.
* Rapport de performance individuel pour un chevalier.
* Statistiques mensuelles d’activité.

---

## Endpoints principaux

### Chevaliers

| Méthode | URL                                             | Description                                       |
| ------- | ----------------------------------------------- | ------------------------------------------------- |
| GET     | `/chevaliers`                                   | Liste tous les chevaliers                         |
| POST    | `/chevaliers`                                   | Crée un nouveau chevalier                         |
| GET     | `/chevaliers/{id}/quetes-en-cours`              | Récupère les quêtes en cours d’un chevalier       |
| GET     | `/chevaliers/{id}/retirer-quete/{idQuete}`      | Retire un chevalier d’une quête                   |
| GET     | `/chevaliers/caracteristique/{caracteristique}` | Cherche chevaliers par caractéristique principale |
| GET     | `/chevaliers/rapport-performance/{id}`          | Rapport de performance d’un chevalier             |

### Quêtes

| Méthode | URL                                        | Description                                               |
| ------- | ------------------------------------------ | --------------------------------------------------------- |
| GET     | `/quetes/{id}/participants`                | Liste des participants d’une quête                        |
| POST    | `/quetes/{idQuete}/assigner-chevalier`     | Assigne un chevalier à une quête                          |
| GET     | `/quetes/difficulte-aberrante`             | Quêtes de difficulté aberrante non commencées ou en cours |
| GET     | `/quetes/effectif-manquant?minChevaliers=` | Quêtes avec effectif manquant selon un seuil              |
| GET     | `/quetes/les-plus-longues?limit=`          | Quêtes les plus longues (limite configurable)             |
| GET     | `/quetes/periode?date_debut=&date_fin=`    | Quêtes dont la période chevauche la période donnée        |

### Statistiques

| Méthode | URL                                            | Description                |
| ------- | ---------------------------------------------- | -------------------------- |
| GET     | `/stats/rapport-activite-mensuel?mois=&annee=` | Rapport d’activité mensuel |

---

## Technologies utilisées

* Java 17+
* Spring Boot (Spring Web, Spring Data JPA)
* Hibernate / JPA
* MySQL (base de données)
* Lombok (pour simplifier le code)
* Maven (gestion de projet)

---

## Installation et lancement

### Prérequis

* Java 17 ou supérieur installé
* MySQL installé et une base de données `kaamelott` créée
* Maven installé ou IDE compatible Maven

### Configuration de la base de données

Dans `src/main/resources/application.properties` :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/kaamelott?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Adapter le nom d’utilisateur et mot de passe selon votre installation MySQL.

### Lancer le projet

Le serveur démarre par défaut sur [http://localhost:8080](http://localhost:8080).

---

## Exemple de requêtes

* Obtenir la liste de tous les chevaliers :
  `GET http://localhost:8080/chevaliers`

* Créer un chevalier (exemple JSON) :
  `POST http://localhost:8080/chevaliers`

  ```json
  {
    "nom": "Lancelot",
    "titre": "Chevalier de la Table Ronde",
    "caracteristiquePrincipale": "Bravoure"
  }
  ```

* Assigner un chevalier à une quête :
  `POST http://localhost:8080/quetes/1/assigner-chevalier`

  ```json
  {
    "idChevalier": 1,
    "role": "COMBATTANT",
    "statutParticipation": "EN_COURS"
  }
  ```

---

## Structure du projet

* `controller` : classes REST exposant les endpoints
* `services` : logique métier
* `repositories` : interfaces JPA pour accéder aux données
* `entities` : entités JPA représentant les tables
* `dto` et `requests` : classes pour les échanges avec l’API
* `enumeration` : enums pour statuts, rôles, etc.

---

## Remarques

* Le projet utilise des relations JPA entre `Chevalier`, `Quete` et `ParticipationQuete` pour gérer les participations.
* Les validations basiques sont gérées dans les services.
* Le format JSON est utilisé pour les échanges API.

---

## Initialisation de la base de données `kaamelott`

```sql
-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : dim. 06 juil. 2025 à 09:35
-- Version du serveur : 8.2.0
-- Version de PHP : 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

-- Base de données : `kaamelott`

-- Structure de la table `chevalier`
DROP TABLE IF EXISTS `chevalier`;
CREATE TABLE IF NOT EXISTS `chevalier` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `caracteristique_principale` varchar(255) DEFAULT NULL,
  `niveau_bravoure` int DEFAULT NULL,
  PRIMARY KEY (`id`)
)ENGINE=INNODB;

-- Données table `chevalier`
INSERT INTO `chevalier` (`id`, `nom`, `titre`, `caracteristique_principale`, `niveau_bravoure`) VALUES
(1, 'Perceval', 'Chevalier', 'Naïf', 4),
(2, 'Karadoc', 'Chevalier', 'Gourmand', 3),
(3, 'Léodagan', 'Sire', 'Ronchon', 8),
(4, 'Bohort', 'Chevalier', 'Lâche', 2),
(5, 'Arthur', 'Roi', 'Dépressif', 7),
(6, 'Perceval', 'Chevalier', 'Naïf', 5),
(7, 'Perceval', 'Chevalier', 'Naïf', 5),
(8, 'Perceval', 'Chevalier', 'Naïf', 5);

-- Structure de la table `quete`
DROP TABLE IF EXISTS `quete`;
CREATE TABLE IF NOT EXISTS `quete` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom_quete` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `difficulte` enum('FACILE','MOYENNE','DIFFICILE','ABERRANTE') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `date_assignation` date NOT NULL,
  `date_echeance` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB;

-- Données table `quete`
INSERT INTO `quete` (`id`, `nom_quete`, `description`, `difficulte`, `date_assignation`, `date_echeance`) VALUES
(1, 'La quête du Graal (encore)', 'Trouvez le Graal. Enfin essayez.', 'ABERRANTE', '2025-01-01', '2025-12-04'),
(2, 'Ramener du poisson frais', 'Allez au marché. Mais pas celui du village.', 'FACILE', '2025-06-01', '2026-04-23'),
(3, 'Comprendre un truc', 'Juste... comprendre quelque chose.', 'MOYENNE', '2025-03-15', '2025-10-15'),
(4, 'Chasser le sanglier géant', 'Il a mangé deux cochons. Et la moitié d’un village.', 'DIFFICILE', '2025-02-10', '2025-10-15'),
(5, 'Organiser un banquet', 'Prévoir 12 cochons, 6 tonneaux, et pas de discours.', 'MOYENNE', '2024-03-12', '2025-03-11'),
(6, 'Recherche du Graal', 'Quête pour trouver le Graal', 'DIFFICILE', '2025-06-15', '2025-07-05'),
(7, 'Défense du royaume', 'Protéger le royaume contre les envahisseurs', 'MOYENNE', '2025-07-01', '2025-07-20');

-- Structure de la table `participation_quete`
DROP TABLE IF EXISTS `participation_quete`;
CREATE TABLE IF NOT EXISTS `participation_quete` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_chevalier` int NOT NULL,
  `id_quete` int NOT NULL,
  `role` enum('CHEF_EXPEDITION','ACCOLYTE','RESERVE') NOT NULL,
  `statut_participation` enum('EN_COURS','TERMINEE','ECHOUEE_LAMENTABLEMENT','ABANDONNEE_PAR_FLEME') NOT NULL,
  `commentaire_roi` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_id_chevalier_id_quete` (`id_chevalier`,`id_quete`),
  KEY `id_quete` (`id_quete`)
) ENGINE=INNODB;

-- Données table `participation_quete`
INSERT INTO `participation_quete` (`id`, `id_chevalier`, `id_quete`, `role`, `statut_participation`, `commentaire_roi`) VALUES
(1, 1, 1, 'ACCOLYTE', 'EN_COURS', 'Il comprend rien mais il est motivé.'),
(2, 1, 3, 'CHEF_EXPEDITION', 'ABANDONNEE_PAR_FLEME', 'Il a abandonné au mot "déduction".'),
(3, 2, 1, 'ACCOLYTE', 'EN_COURS', 'Il a emporté une saucisse en offrande.'),
(4, 2, 3, 'ACCOLYTE', 'ABANDONNEE_PAR_FLEME', 'Ils ont cuisiné à la place.'),
(5, 2, 5, 'CHEF_EXPEDITION', 'TERMINEE', 'Il a mangé autant qu’il a organisé.'),
(6, 3, 2, 'CHEF_EXPEDITION', 'TERMINEE', 'Étonnamment efficace.'),
(7, 3, 4, 'CHEF_EXPEDITION', 'TERMINEE', 'Très sanglant, mais efficace.'),
(8, 4, 2, 'ACCOLYTE', 'ECHOUEE_LAMENTABLEMENT', 'Il est rentré avec du fromage.'),
(9, 4, 5, 'ACCOLYTE', 'TERMINEE', 'A bien aidé à goûter les plats.'),
(10, 5, 1, 'CHEF_EXPEDITION', 'EN_COURS', 'Ça commence à me gonfler.'),
(11, 5, 4, 'RESERVE', 'TERMINEE', 'Il a supervisé en buvant du vin.'),
(12, 1, 6, 'CHEF_EXPEDITION', 'TERMINEE', 'Très bonne mission'),
(13, 2, 6, 'ACCOLYTE', 'TERMINEE', 'Bon travail'),
(14, 3, 6, 'RESERVE', 'ECHOUEE_LAMENTABLEMENT', 'Échec total'),
(15, 4, 7, 'CHEF_EXPEDITION', 'EN_COURS', ''),
(16, 5, 7, 'ACCOLYTE', 'ECHOUEE_LAMENTABLEMENT', 'Désastre'),
(17, 1, 7, 'RESERVE', 'EN_COURS', '');

-- Contraintes
ALTER TABLE `participation_quete`
  ADD CONSTRAINT `participation_quete_ibfk_1` FOREIGN KEY (`id_chevalier`) REFERENCES `chevalier` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `participation_quete_ibfk_2` FOREIGN KEY (`id_quete`) REFERENCES `quete` (`id`) ON DELETE CASCADE;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
```

---

## Jeu de données (JDD) pour tests des endpoints

Les données insérées permettent de tester :

* Les endpoints qui listent, créent, modifient et suppriment des **chevaliers**.
* Les endpoints qui listent, créent, modifient et suppriment des **quêtes**.
* Les endpoints qui gèrent les **participations aux quêtes**, y compris les rôles (CHEF\_EXPEDITION, ACCOLYTE, RESERVE) et les statuts de participation.
* Les filtres sur les quêtes (dates, difficultés).
* La gestion des contraintes (pas de participation à une quête inexistante, suppression en cascade des participations liées à un chevalier ou une quête supprimée).

---

## 🏁 Getting Started

Suivez ces étapes pour démarrer rapidement le projet **Eval Kaamelott** en local.

### 1. Cloner le dépôt

```bash
git clone https://github.com/Suhhushi/eval_kaamelott
cd eval-kaamelott
```

### 2. Configurer la base de données

Assurez-vous d’avoir une base MySQL nommée `kaamelott` :

Utilisé les script SQL données précédement, pour importer la base de données et le jeux de données

Puis configurez vos identifiants dans le fichier :

```
src/main/resources/application.properties
```

```properties
spring.datasource.username=VotreNomUtilisateur
spring.datasource.password=VotreMotDePasse
```

### 4. Lancer l’application

Utilisez Maven ou votre IDE :

L’application sera accessible sur :
👉 [http://localhost:8080](http://localhost:8080)

### 5. Accéder à la documentation Swagger

Une documentation interactive est disponible ici :
🔗 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

> ✅ **Astuce** : Pour tester rapidement les endpoints, utilisez le fichier `generated-requests.http` dans `src/test/java`.

---

  

