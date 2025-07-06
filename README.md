Voici un exemple complet de fichier **README.md** adapté à ton projet Spring Boot **Eval Kaamelott**. Il décrit l'objectif, les fonctionnalités principales, la configuration, et comment lancer le projet.

---

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

Si tu souhaites, je peux aussi t’aider à générer un fichier Swagger/OpenAPI pour documenter automatiquement tes endpoints.
Veux-tu ?
