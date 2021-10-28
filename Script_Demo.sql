CREATE DATABASE  IF NOT EXISTS `paymybuddy` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `paymybuddy`;
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: paymybuddy
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorities` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (1,'User'),(2,'Admin');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_account`
--

DROP TABLE IF EXISTS `bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank_account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bic` varchar(255) DEFAULT NULL,
  `card_number` varchar(255) DEFAULT NULL,
  `cryptogram` varchar(255) DEFAULT NULL,
  `deer` varchar(255) DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  `holder` varchar(255) DEFAULT NULL,
  `iban` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_agc58phswaxjlwmhygmhwo8jj` (`card_number`),
  KEY `FKftsfxon3d4ectm5bv7glrhlko` (`user_id`),
  CONSTRAINT `FKftsfxon3d4ectm5bv7glrhlko` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account`
--

LOCK TABLES `bank_account` WRITE;
/*!40000 ALTER TABLE `bank_account` DISABLE KEYS */;
INSERT INTO `bank_account` VALUES (2,'CEPAFRPP156','$2a$10$UZsnEPANM9UToiXkI7XTae3mxIlRZYpWlNfEqA4WiaP60l1Rb/rJe','$2a$10$JVr7.VEoHJsxyigaEe14ZOvq6weaotiZ6BW8bKbVWHPx6Z5lC5U16','51','2025-06-11','Démon Stration','FR7530002032810000193032Y15','Perso',2),(3,'CEPAFRPP266','$2a$10$79A4jn86Fw5uSLKFFXLUcOT9HYxddrsbCICAYhop8Ud42E4QqTpQi','$2a$10$8dI7KG4mPJNdT6/bzsyGJODfa.mR.fVAHVkvoHNhSlLviymsb8Wli','53','2025-11-13','M et Mme Stration','FR7530002032815870193032Y15','Commun',2),(4,'CEPAFRPP176','$2a$10$jFrcA5w/V3LEquS.pkbhheVfPdlSk6h2UUAs43Tqa0Lly61Fy39zO','$2a$10$QzqlFT58nzJOACZ3LT3LLukQIL7.rDOop.ZUHWMVmGTMS0VyHhc36','57','2026-07-16','Démon Stration','FR7530002032810578198032Y15','Pro',2);
/*!40000 ALTER TABLE `bank_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billing`
--

DROP TABLE IF EXISTS `billing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billing` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` decimal(19,2) DEFAULT NULL,
  `transfer_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6tp5u9y61e0cgokcx3iior6f4` (`transfer_id`),
  KEY `FK7u0qmrh9gxtqnm08yj1eji0ju` (`user_id`),
  CONSTRAINT `FK6tp5u9y61e0cgokcx3iior6f4` FOREIGN KEY (`transfer_id`) REFERENCES `transfers` (`id`),
  CONSTRAINT `FK7u0qmrh9gxtqnm08yj1eji0ju` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billing`
--

LOCK TABLES `billing` WRITE;
/*!40000 ALTER TABLE `billing` DISABLE KEYS */;
INSERT INTO `billing` VALUES (1,0.50,3,2),(2,0.55,4,2),(3,0.60,5,2),(4,0.65,6,2),(5,0.70,7,2),(6,0.75,8,2),(7,0.80,9,2),(8,0.05,10,3),(9,0.10,11,4),(10,0.15,12,5),(11,0.20,13,6),(12,0.25,14,7),(13,0.30,15,8),(14,0.35,16,9);
/*!40000 ALTER TABLE `billing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfers`
--

DROP TABLE IF EXISTS `transfers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transfers` (
  `dtype` varchar(31) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` decimal(19,2) DEFAULT NULL,
  `date_time` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `bank_account_id` bigint DEFAULT NULL,
  `credited_id` bigint DEFAULT NULL,
  `debited_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdlhva9qsxv2ah7b3dcv3aa4kb` (`bank_account_id`),
  KEY `FKrmn0wqmui89k4t3e980bh5rln` (`credited_id`),
  KEY `FKcd2ryeqgau76hy65y6ignoy2u` (`debited_id`),
  CONSTRAINT `FKcd2ryeqgau76hy65y6ignoy2u` FOREIGN KEY (`debited_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKdlhva9qsxv2ah7b3dcv3aa4kb` FOREIGN KEY (`bank_account_id`) REFERENCES `bank_account` (`id`),
  CONSTRAINT `FKrmn0wqmui89k4t3e980bh5rln` FOREIGN KEY (`credited_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfers`
--

LOCK TABLES `transfers` WRITE;
/*!40000 ALTER TABLE `transfers` DISABLE KEYS */;
INSERT INTO `transfers` VALUES ('TransferAdd',1,5000.00,'2021-10-28 13:36:30.302000','Test de Retrait',3,2,NULL),('TransferRemov',2,2000.00,'2021-10-28 13:36:37.657000','Test de Dépôt',2,NULL,2),('TransferUser',3,100.00,'2021-10-28 13:36:58.663000','Test à 1',NULL,3,2),('TransferUser',4,110.00,'2021-10-28 13:37:11.222000','Test à 2',NULL,4,2),('TransferUser',5,120.00,'2021-10-28 13:37:21.442000','Test à 3',NULL,5,2),('TransferUser',6,130.00,'2021-10-28 13:37:30.247000','Test à 4',NULL,6,2),('TransferUser',7,140.00,'2021-10-28 13:37:41.159000','Test à 5',NULL,7,2),('TransferUser',8,150.00,'2021-10-28 13:37:53.730000','Test à 6',NULL,8,2),('TransferUser',9,160.00,'2021-10-28 13:38:05.165000','Test à 7',NULL,9,2),('TransferUser',10,10.00,'2021-10-28 13:39:16.685000','test de 1',NULL,2,3),('TransferUser',11,20.00,'2021-10-28 13:39:37.860000','Test de 2',NULL,2,4),('TransferUser',12,30.00,'2021-10-28 13:40:01.150000','Test de 3',NULL,2,5),('TransferUser',13,40.00,'2021-10-28 13:40:26.735000','Test de 4',NULL,2,6),('TransferUser',14,50.00,'2021-10-28 13:40:44.061000','Test de 5',NULL,2,7),('TransferUser',15,60.00,'2021-10-28 13:41:02.945000','Test de 6',NULL,2,8),('TransferUser',16,70.00,'2021-10-28 13:41:20.178000','Test de 7',NULL,2,9);
/*!40000 ALTER TABLE `transfers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` bigint NOT NULL,
  `authority_id` bigint NOT NULL,
  KEY `FKtklyeanapeqmrko5d84wu1xlo` (`authority_id`),
  KEY `FKj345gk1bovqvfame88rcx7yyx` (`user_id`),
  CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKtklyeanapeqmrko5d84wu1xlo` FOREIGN KEY (`authority_id`) REFERENCES `authorities` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(1,2),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(2,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_user`
--

DROP TABLE IF EXISTS `user_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_user` (
  `user_id` bigint NOT NULL,
  `know_user_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`know_user_id`),
  KEY `FK90a6071u77ytph72ar2kvqxg7` (`know_user_id`),
  CONSTRAINT `FK6d6hc5m6rbc4ojf9troqgwmb8` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK90a6071u77ytph72ar2kvqxg7` FOREIGN KEY (`know_user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_user`
--

LOCK TABLES `user_user` WRITE;
/*!40000 ALTER TABLE `user_user` DISABLE KEYS */;
INSERT INTO `user_user` VALUES (3,2),(4,2),(5,2),(6,2),(7,2),(8,2),(9,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9);
/*!40000 ALTER TABLE `user_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `pay` decimal(19,2) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin@email.com','$2a$10$6G7afdlZakhKpER8nv536eF2XUy2R1p5xfzAsHrYMnQFi/C3n39fy',0.00,'Admin'),(2,'demo@email.com','$2a$10$8bARamialA8Za570Tk2vqOy941vwNOy37YZVDBfKNvi4CfTPKtSIW',2365.45,'Démonstration'),(3,'test1@email.com','$2a$10$aDGuInz0CDzqSlpvHJ.eV.bmEzK.aITh2TKP91w3PXLeh3Ki.0n4G',89.95,'Un'),(4,'test2@email.com','$2a$10$qyrjYHG5cvpBHf5CQwPS9OXPUejDkTR3gDCFJ9cl1DeorElXPZyv6',89.90,'Deux'),(5,'test3@email.com','$2a$10$eJxWFuA1f7niZbqd0EdlvuTzkCmC9p2JG51WTFbzVqP1s9b7V0Ple',89.85,'Trois'),(6,'test4@email.com','$2a$10$ay65QYNFbKQOGSp8CGNnsuuB6tsWrAko0BBMLB/8ozOoOdN3mE0ci',89.80,'Quatre'),(7,'test5@email.com','$2a$10$OA/.Tw6MGkDJtZ8.vGoQquModpc3BWZ3m.YsLsT./QUL6dWo.qPJC',89.75,'Cinq'),(8,'test6@email.com','$2a$10$BbnIRVRuVxuiDQofqYQCyuhwCfMRu7XdSLrbRButkjOBIGFNcjBFC',89.70,'Six'),(9,'test7@email.com','$2a$10$nE54Bt1LAUTBbUSwJqPiOeJ.c36jUjabJFJRHBJU.RVIW8gQyXA.a',89.65,'Sept');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_history_credited`
--

DROP TABLE IF EXISTS `users_history_credited`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_history_credited` (
  `user_id` bigint NOT NULL,
  `history_credited_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`history_credited_id`),
  UNIQUE KEY `UK_hh5djlln9haqfb88refk0w5pj` (`history_credited_id`),
  CONSTRAINT `FKdp3d6j14y16thk4il29odhxbs` FOREIGN KEY (`history_credited_id`) REFERENCES `transfers` (`id`),
  CONSTRAINT `FKfc22fdaclrb6vara882m95pot` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_history_credited`
--

LOCK TABLES `users_history_credited` WRITE;
/*!40000 ALTER TABLE `users_history_credited` DISABLE KEYS */;
INSERT INTO `users_history_credited` VALUES (2,1),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(2,10),(2,11),(2,12),(2,13),(2,14),(2,15),(2,16);
/*!40000 ALTER TABLE `users_history_credited` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_history_debited`
--

DROP TABLE IF EXISTS `users_history_debited`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_history_debited` (
  `user_id` bigint NOT NULL,
  `history_debited_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`history_debited_id`),
  UNIQUE KEY `UK_f158tf3bjhy6tkjrl800t4da3` (`history_debited_id`),
  CONSTRAINT `FKleoox8e5h96256ilo058jhaih` FOREIGN KEY (`history_debited_id`) REFERENCES `transfers` (`id`),
  CONSTRAINT `FKmccb4eisrgm3lr7f2yycjvyrn` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_history_debited`
--

LOCK TABLES `users_history_debited` WRITE;
/*!40000 ALTER TABLE `users_history_debited` DISABLE KEYS */;
INSERT INTO `users_history_debited` VALUES (2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(3,10),(4,11),(5,12),(6,13),(7,14),(8,15),(9,16);
/*!40000 ALTER TABLE `users_history_debited` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-28 13:50:57
