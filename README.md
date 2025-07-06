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

## SQL d'initialisation

Voici un dump SQL pour la table `participation_quete` avec contraintes et données initiales :

```sql
-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : dim. 06 juil. 2025 à 09:32
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

-- --------------------------------------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Données de la table `participation_quete`

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

-- Contraintes pour la table `participation_quete`

ALTER TABLE `participation_quete`
  ADD CONSTRAINT `participation_quete_ibfk_1` FOREIGN KEY (`id_chevalier`) REFERENCES `chevalier` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `participation_quete_ibfk_2` FOREIGN KEY (`id_quete`) REFERENCES `quete` (`id`) ON DELETE CASCADE;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
```

---

## Jeu de données (JDD) pour tests

Pour pouvoir tester tous les endpoints, voici un jeu de données minimal pour les tables `chevalier` et `quete`. Ce JDD correspond aux références dans `participation_quete` :

```sql
-- Table chevalier

DROP TABLE IF EXISTS `chevalier`;
CREATE TABLE IF NOT EXISTS `chevalier` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `titre` varchar(100) DEFAULT NULL,
  `caracteristique_principale` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `chevalier` (`id`, `nom`, `titre`, `caracteristique_principale`) VALUES
(1, 'Perceval', 'Chevalier', 'Force'),
(2, 'Karadoc', 'Chevalier', 'Endurance'),
(3, 'Léodagan', 'Seigneur', 'Force'),
(4, 'Bohort', 'Chevalier', 'Agilité'),
(5, 'Gauvain', 'Chevalier', 'Technique');

-- Table quete

DROP TABLE IF EXISTS `quete`;
CREATE TABLE IF NOT EXISTS `quete` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(150) NOT NULL,
  `difficulte` int NOT NULL,
  `date_assignation` date DEFAULT NULL,
  `date_echeance` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `quete` (`id`, `nom`, `difficulte`, `date_assignation`, `date_echeance`) VALUES
(1, 'Trouver le Graal', 8, '2025-06-01', '2025-07-31'),
(2, 'La quête du dragon', 5, '2025-05-15', '2025-06-15'),
(3, 'Réparer Excalibur', 10, '2025-04-01', '2025-05-01'),
(4, 'Gagner le tournoi', 7, '2025-03-20', '2025-04-20'),
(5, 'Libérer le château', 6, '2025-02-01', '2025-03-01'),
(6, 'Trouver la potion magique', 9, '2025-01-10', '2025-02-10'),
(7, 'Détruire le maléfice', 4, '2025-07-01', '2025-08-01');
```

---

## Instructions pour tests

1. Importer les tables `chevalier` et `quete` (création + insertion) dans ta base MySQL.
2. Importer ensuite la table `participation_quete` (structure + données + contraintes).
3. Démarrer l’application Spring Boot.
4. Tester les endpoints listés dans le README avec ces données.

