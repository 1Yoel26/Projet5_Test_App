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

```-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `participate`
--

DROP TABLE IF EXISTS `participate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participate` (
  `user_id` int DEFAULT NULL,
  `session_id` int DEFAULT NULL,
  KEY `user_id` (`user_id`),
  KEY `session_id` (`session_id`),
  CONSTRAINT `participate_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `participate_ibfk_2` FOREIGN KEY (`session_id`) REFERENCES `sessions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participate`
--

LOCK TABLES `participate` WRITE;
/*!40000 ALTER TABLE `participate` DISABLE KEYS */;
INSERT INTO `participate` VALUES (2,4),(2,6);
/*!40000 ALTER TABLE `participate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sessions`
--

DROP TABLE IF EXISTS `sessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sessions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `date` timestamp NULL DEFAULT NULL,
  `teacher_id` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `sessions_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessions`
--

LOCK TABLES `sessions` WRITE;
/*!40000 ALTER TABLE `sessions` DISABLE KEYS */;
INSERT INTO `sessions` VALUES (4,'Session test 1','Session de yoga de test1','2025-09-10 00:00:00',1,'2025-09-05 14:44:11','2025-09-05 17:08:47'),(6,'Session 2','Session de Yoga 2 top!','2026-12-10 00:00:00',1,'2025-10-10 10:22:51','2025-10-10 12:22:52');
/*!40000 ALTER TABLE `sessions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teachers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `last_name` varchar(40) DEFAULT NULL,
  `first_name` varchar(40) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachers`
--

LOCK TABLES `teachers` WRITE;
/*!40000 ALTER TABLE `teachers` DISABLE KEYS */;
INSERT INTO `teachers` VALUES (1,'ILLOUZ','Yoel','2025-08-25 10:49:41','2025-10-21 19:01:51'),(2,'THIERCELIN','Hélène','2025-08-25 10:49:41','2025-08-25 12:49:41');
/*!40000 ALTER TABLE `teachers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `last_name` varchar(40) DEFAULT NULL,
  `first_name` varchar(40) DEFAULT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'ILLOUZ','Yoel',0,'test@test.fr','$2a$10$1VrUSNpb.LTyUcs06l8HDuDHnAcmHREXDLxrn9QccOmQIl6nx9s0G','2025-08-27 09:42:14','2025-08-27 11:42:14'),(3,'toto','toto',0,'toto3@toto.com','$2a$10$DrwcwIISvzzVvdABJ/qtIORWxe747ePsKvMJBwf8oDWzt4LNHpRx.','2025-08-27 17:25:09','2025-08-27 19:25:09'),(4,'Admin','Admin',1,'yoga@studio.com','$2a$10$.Hsa/ZjUVaHqi0tp9xieMeewrnZxrZ5pQRzddUXE/WjDu2ZThe6Iq','2025-09-02 12:18:04','2025-09-02 14:18:04'),(5,'test nom','test prenom',0,'testnom@test.fr','$2a$10$qr3sb5N3HRuZkbI7h3T4S.Gyx75LxNEG4e25TaabzJcuNejQuHvyu','2025-09-07 16:06:42','2025-09-07 18:06:43'),(7,'Last name de test','First name de test',1,'test.mdp@user.com','$2a$10$vTSODg/QOcZ87ZX7awoKhePEXp7wGLkzS3FUg.oPmoYQjz2C8AbFW','2025-10-28 21:56:54','2025-10-28 22:56:54'),(8,'ILLOUZ','Yoel',0,'yoga@studio.com',NULL,'2025-10-29 17:35:00','2025-10-29 18:35:00'),(9,'ILLOUZ','Yoel',0,'yoga@studio.com',NULL,'2025-10-29 17:37:50','2025-10-29 18:37:50');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-01 21:05:26


```

### Avec ce code Sql executer, 1 utilisateur sera déjà créé :

- Email : yoga@studio.com  |  Mot de passe : test!1234


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
