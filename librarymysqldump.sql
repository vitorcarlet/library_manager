-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dblibrary1
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `library_books`
--

DROP TABLE IF EXISTS `library_books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `library_books` (
  `library_id` bigint NOT NULL,
  `book_id` bigint NOT NULL,
  PRIMARY KEY (`library_id`,`book_id`),
  KEY `FKsvbx7qr5rnanyhpo1ke4fxw1b` (`book_id`),
  CONSTRAINT `FKmod4uagjkftyv0m9rcduniw62` FOREIGN KEY (`library_id`) REFERENCES `tb_libraries` (`id`),
  CONSTRAINT `FKsvbx7qr5rnanyhpo1ke4fxw1b` FOREIGN KEY (`book_id`) REFERENCES `tb_books` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `library_books`
--

LOCK TABLES `library_books` WRITE;
/*!40000 ALTER TABLE `library_books` DISABLE KEYS */;
/*!40000 ALTER TABLE `library_books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `library_books_observers`
--

DROP TABLE IF EXISTS `library_books_observers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `library_books_observers` (
  `library_id` bigint NOT NULL,
  `observer_id` bigint NOT NULL,
  PRIMARY KEY (`library_id`,`observer_id`),
  KEY `FKiqvhc6ce8b1nwtdv15tc8bidt` (`observer_id`),
  CONSTRAINT `FK16374n84f0tq3w0kbfpk5vqko` FOREIGN KEY (`library_id`) REFERENCES `tb_libraries` (`id`),
  CONSTRAINT `FKiqvhc6ce8b1nwtdv15tc8bidt` FOREIGN KEY (`observer_id`) REFERENCES `tb_users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `library_books_observers`
--

LOCK TABLES `library_books_observers` WRITE;
/*!40000 ALTER TABLE `library_books_observers` DISABLE KEYS */;
/*!40000 ALTER TABLE `library_books_observers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `library_users`
--

DROP TABLE IF EXISTS `library_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `library_users` (
  `library_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`library_id`,`user_id`),
  KEY `FK8ejauo5ff18bvcg2yv7imck46` (`user_id`),
  CONSTRAINT `FK8ejauo5ff18bvcg2yv7imck46` FOREIGN KEY (`user_id`) REFERENCES `tb_users` (`id`),
  CONSTRAINT `FKi4j5omkldldwjwad7kgdc1ea8` FOREIGN KEY (`library_id`) REFERENCES `tb_libraries` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `library_users`
--

LOCK TABLES `library_users` WRITE;
/*!40000 ALTER TABLE `library_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `library_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_adress`
--

DROP TABLE IF EXISTS `tb_adress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_adress` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `adress_identification` varchar(255) NOT NULL,
  `cep` int NOT NULL,
  `city` varchar(255) NOT NULL,
  `complement` varchar(255) NOT NULL,
  `neighborhood` varchar(255) NOT NULL,
  `number` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `street` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_adress`
--

LOCK TABLES `tb_adress` WRITE;
/*!40000 ALTER TABLE `tb_adress` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_adress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_bookloans`
--

DROP TABLE IF EXISTS `tb_bookloans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_bookloans` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `loan_date` datetime(6) DEFAULT NULL,
  `loan_duration` int DEFAULT NULL,
  `return_loan_date` datetime(6) DEFAULT NULL,
  `returned` bit(1) DEFAULT NULL,
  `book_id_fk` bigint DEFAULT NULL,
  `user_id_fk` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKqmf26k5nsop0sy6pxepwgx6eb` (`book_id_fk`),
  UNIQUE KEY `UK6rxcxhcudg833gjjpmt2hpg76` (`user_id_fk`),
  CONSTRAINT `FK59t47xorn525jf7s3lrim3lq` FOREIGN KEY (`user_id_fk`) REFERENCES `tb_users` (`id`),
  CONSTRAINT `FKi608wocgu1uvba0p1y3wurbsl` FOREIGN KEY (`book_id_fk`) REFERENCES `tb_books` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_bookloans`
--

LOCK TABLES `tb_bookloans` WRITE;
/*!40000 ALTER TABLE `tb_bookloans` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_bookloans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_books`
--

DROP TABLE IF EXISTS `tb_books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_books` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `book_code` int NOT NULL,
  `book_type` varchar(255) DEFAULT NULL,
  `pub_year` int NOT NULL,
  `quantity` int NOT NULL,
  `registration_date` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_books`
--

LOCK TABLES `tb_books` WRITE;
/*!40000 ALTER TABLE `tb_books` DISABLE KEYS */;
INSERT INTO `tb_books` VALUES (1,'J.R.R. Tolkien',33556,'Fantasy',1937,14,'2024-06-03 00:43:54.327000','O Hobbit'),(2,'Jane Austen',98765,'Romance',1813,20,'2024-06-03 00:46:02.252000','Orgulho e Preconceito'),(4,'Dan Brown',67890,'Mystery',2003,25,'2024-06-03 00:46:13.062000','O Código Da Vinci'),(5,'Yuval Noah Harari',11223,'Non-Fiction',2011,30,'2024-06-03 00:46:19.174000','Sapiens: Uma Breve História da Humanidade'),(6,'J.R.R. Tolkien',33445,'Fantasy',1937,12,'2024-06-03 00:46:24.417000','O Hobbit');
/*!40000 ALTER TABLE `tb_books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_libraries`
--

DROP TABLE IF EXISTS `tb_libraries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_libraries` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_libraries`
--

LOCK TABLES `tb_libraries` WRITE;
/*!40000 ALTER TABLE `tb_libraries` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_libraries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_users`
--

DROP TABLE IF EXISTS `tb_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `active_user` bit(1) NOT NULL,
  `birth` datetime(6) NOT NULL,
  `cpf` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `registration` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_users`
--

LOCK TABLES `tb_users` WRITE;
/*!40000 ALTER TABLE `tb_users` DISABLE KEYS */;
INSERT INTO `tb_users` VALUES (3,_binary '','2000-01-01 00:00:00.000000','123.456.789-00','M','João Silva',0),(7,_binary '','1990-05-10 21:00:00.000000','123.456.789-00','M','Vitor Carlet',0);
/*!40000 ALTER TABLE `tb_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_users_permissions`
--

DROP TABLE IF EXISTS `tb_users_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_users_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_admin` bit(1) DEFAULT NULL,
  `is_assistant` bit(1) DEFAULT NULL,
  `is_operator` bit(1) DEFAULT NULL,
  `user_credentials_id_fk` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKslxf0k2659wk9mvqfvoccb3pv` (`user_credentials_id_fk`),
  CONSTRAINT `FKec8g48508qsrotf3ov2l1hgkr` FOREIGN KEY (`user_credentials_id_fk`) REFERENCES `tb_userscredentials` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_users_permissions`
--

LOCK TABLES `tb_users_permissions` WRITE;
/*!40000 ALTER TABLE `tb_users_permissions` DISABLE KEYS */;
INSERT INTO `tb_users_permissions` VALUES (8,_binary '',_binary '',_binary '',7),(12,_binary '',_binary '',_binary '',11);
/*!40000 ALTER TABLE `tb_users_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_userscredentials`
--

DROP TABLE IF EXISTS `tb_userscredentials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_userscredentials` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_id_fk` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKgdwkdvphb6e5nguky13la1ep2` (`user_id_fk`),
  CONSTRAINT `FK7bc0hpemubakk5j6k3eq932m6` FOREIGN KEY (`user_id_fk`) REFERENCES `tb_users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_userscredentials`
--

LOCK TABLES `tb_userscredentials` WRITE;
/*!40000 ALTER TABLE `tb_userscredentials` DISABLE KEYS */;
INSERT INTO `tb_userscredentials` VALUES (7,'joaosilva','$2a$08$fHMsuIUHaHt0vbpOnwnagOb8gOnGF3lLnNbovWn2ljl3.MZfzzg3W',3),(11,'vitor123','$2a$10$b0lcFb5ptKqvE3IaoMx7ZO2NWBKGTOQ00dOQIA3qm18BkRcQj93Lu',7);
/*!40000 ALTER TABLE `tb_userscredentials` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-03  0:59:15
