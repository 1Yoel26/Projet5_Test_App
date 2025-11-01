# 🧘‍♀️ Yoga App – Application Full Stack (Angular + Spring Boot + MySQL)

Une application complète permettant la gestion d’un studio de yoga : authentification, gestion des sessions, inscriptions, et tests automatisés (unitaires et end-to-end).

---

## 📋 Description

Ce projet est composé de deux parties :

- **Back-end** : API REST Java Spring Boot avec base MySQL et authentification JWT  
- **Front-end** : Application Angular permettant la consultation et la gestion des sessions de yoga  
- **Tests** : Couverture complète du front avec Jest (unitaires) et Cypress (E2E)

---

## 🗂️ Structure principale

### 🖥️ Back-end (`Projet2_OC_Yoel_Partie_Back_Java_Spring`)
- `src/main/java/.../controller` → Contrôleurs REST  
- `src/main/java/.../service` → Logique métier  
- `src/main/java/.../repository` → Requêtes JPA / accès BDD  
- `src/main/java/.../model` → Entités (User, Session, etc.)  
- `src/main/java/.../configuration` → Sécurité, Swagger, CORS  

### 🌐 Front-end (`Projet2_OC_Yoel_Partie_Front_Angular`)
- `src/app/components` → Composants Angular  
- `src/app/features` → Modules fonctionnels (auth, sessions, etc.)  
- `src/app/services` → Services d’appel à l’API  
- `src/app/guards` → Protection des routes  
- `src/app/interceptors` → Gestion automatique des tokens JWT  

---

## 🚀 Installation et lancement

### 1️⃣ Créer la base de données MySQL

Un script SQL est disponible pour générer la base et insérer des données de test :

### Suivez les étapes ci-dessous pour initialiser et lancer le projet sur votre machine.

### 1. Créer la base de données déjà remplis 

#### Exécutez le script SQL suivant dans votre interface MySQL (ex : MySQL Workbench, phpMyAdmin, terminal...) pour créer la base de données `bd1` avec ses tables et données :

```sql
CREATE DATABASE IF NOT EXISTS bd1;
USE bd1;

DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `rental_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `rental_id` (`rental_id`),
  CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`rental_id`) REFERENCES `rentals` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `messages` VALUES
(44,35,14,'Bonjour, j\'aimerais savoir s\'il est possible de rajouter 2 chaises longues avec la piscine svp. Merci !','2025-08-06','2025-08-06');

DROP TABLE IF EXISTS `rentals`;
CREATE TABLE `rentals` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `surface` decimal(38,2) DEFAULT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `owner_id` int NOT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `owner_id` (`owner_id`),
  CONSTRAINT `rentals_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `rentals` VALUES
(35,'Maison avec piscine',300.00,5000000.00,'large-home-389271_640.jpg','Maison avec grande piscine ! Idéale pour vos vacances d\'été !',13,'2025-08-06','2025-08-06'),
(36,'Maison avec jardin verdoyant',400.00,800000.00,'house-1836070_640.jpg','Maison avec grand jardin et parking pour garer vos voitures ! ',13,'2025-08-06','2025-08-06'),
(37,'Maison de campagne en bord de lac',100.00,4000000.00,'house-6597406_640.jpg','Maison avec vu sur le lac ! Idéale pour les beaux jours en famille !',14,'2025-08-06','2025-08-06');

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `USERS_index` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `users` VALUES
(13,'test@test.fr','Yoel ILLOUZ','$2a$10$ZwgmI6FM3M0EgPYH89/psevH6L1U27JwHFEapC11IKy2cppmvBf8i','2025-08-06','2025-08-06'),
(14,'test1@test.fr','Jean Dubois','$2a$10$GhJENxvVSXLByRmPG/BZcOgm9PUOr/KOFEdQLM1IUf.RoEPbo7WrG','2025-08-06','2025-08-06');

```

### Avec ce code Sql executer, 2 utilisateurs seront déjà créé :

- Email : test@test.fr  |  Mot de passe : 1234
- Email : test1@test.fr |  Mot de passe : 1111


### 2 Cloner le projet back end Java Spring

Dans votre terminal :

- git clone https://github.com/1Yoel26/Projet2_OC_Yoel_Partie_Back_Java_Spring.git

- cd votreChemin/Projet2_OC_Yoel_Partie_Back_Java_Spring
./mvnw spring-boot:run



### 3 Cloner le projet front end Angular 

Dans votre terminal :

- git clone https://github.com/1Yoel26/Projet2_OC_Yoel_Partie_Front_Angular.git

- cd votreChemin/Projet2_OC_Yoel_Partie_Front_Angular

- npm install

- ng serve




### 4 Accéder à l’application 

- 📍 L’API sera accessible sur : http://localhost:4200

- 📍 La documentation Swagger sera disponible sur : http://localhost:8080/swagger-ui/index.html



## ⚙️ Fonctionnalités clés

### 🔐 Authentification & sécurité
- JWT pour protéger les routes
- Spring Security avec gestion des rôles
- Configuration CORS pour liaison avec Angular

### 🏠 Gestion des Rentals
- Récupérer tous les rentals
- Récupérer un rental par son id
- Créer un nouveau rental (protégé par authentification)
- Modifier un rental (uniquement si propriétaire)


### 👤 Gestion des utilisateurs
- Création de compte (`/auth/register`)
- Connexion (`/auth/login`)
- Récupération des infos de l’utilisateur connecté (`/auth/me`)
- Récupération d’un utilisateur par ID (`/user/{id}`)

### 💬 Gestion des messages
- Envoi d’un message lié à un rental


### 📄 Documentation Swagger
- Toutes les routes documentées
- Test possible directement dans l’interface Swagger
- Support du JWT dans Swagger pour tester les routes sécurisées

## 📚 Technologies utilisées
- Spring Boot 3+
- Spring Data JPA pour l’accès à la base de données
- Spring Security + JWT pour la sécurité
- MySQL comme base de données
- Lombok pour réduire le code boilerplate
- Springdoc OpenAPI pour générer la documentation Swagger
- Maven pour la gestion des dépendances


Merci d’utiliser Rentals API !
