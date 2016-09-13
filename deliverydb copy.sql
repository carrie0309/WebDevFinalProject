-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: localhost    Database: deliverydb
-- ------------------------------------------------------
-- Server version	5.6.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Address`
--

DROP TABLE IF EXISTS `Address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Address` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `City` varchar(255) DEFAULT NULL,
  `Country` varchar(255) DEFAULT NULL,
  `State` varchar(255) DEFAULT NULL,
  `Street` varchar(255) DEFAULT NULL,
  `Zipcode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Address`
--

LOCK TABLES `Address` WRITE;
/*!40000 ALTER TABLE `Address` DISABLE KEYS */;
INSERT INTO `Address` VALUES (1,'123','123','123','123','123'),(2,'123','123','123','123','123'),(3,'dasfsda','China','adfdsg','erwg','534467'),(4,'new york','USA','NY','adsfgrnm','023456'),(5,'123','123','123','123','123'),(6,'13','123','123','12','123'),(7,'123','123','123','123','123'),(8,'13','123','123','12','123'),(9,'hfh','cbc','hhhgj','6578','456'),(10,'64','ryt','trdg','342','6536');
/*!40000 ALTER TABLE `Address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Roles`
--

DROP TABLE IF EXISTS `Roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Roles` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `RoleName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Roles`
--

LOCK TABLES `Roles` WRITE;
/*!40000 ALTER TABLE `Roles` DISABLE KEYS */;
INSERT INTO `Roles` VALUES (1,'Admin'),(2,'Customer'),(3,'Company'),(4,'Admin'),(5,'Customer'),(6,'Company'),(7,'Admin'),(8,'Customer'),(9,'Company'),(10,'Admin'),(11,'Customer'),(12,'Company'),(13,'Admin'),(14,'Customer'),(15,'Company'),(16,'Admin'),(17,'Customer'),(18,'Company'),(19,'Admin'),(20,'Customer'),(21,'Company'),(22,'Customer'),(24,'Customer'),(25,'Customer'),(26,'Customer'),(27,'Company'),(30,'Customer'),(36,'Customer');
/*!40000 ALTER TABLE `Roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tasks`
--

DROP TABLE IF EXISTS `Tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tasks` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Category` varchar(255) DEFAULT NULL,
  `TaskDate` datetime DEFAULT NULL,
  `Weight` varchar(255) DEFAULT NULL,
  `acceptCompany_Id` int(11) DEFAULT NULL,
  `fromAddress_Id` int(11) DEFAULT NULL,
  `fromUser_Id` int(11) DEFAULT NULL,
  `toAddress_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_3qvjpkfbvd0yumig53trx4pmm` (`acceptCompany_Id`),
  KEY `FK_73x0pbun6glpsfwtfcerw6ui5` (`fromAddress_Id`),
  KEY `FK_cs2xbh48vt7j8f5jcbclhxytk` (`fromUser_Id`),
  KEY `FK_c33w7xhmwnnk8joi17cj56n55` (`toAddress_Id`),
  CONSTRAINT `FK_3qvjpkfbvd0yumig53trx4pmm` FOREIGN KEY (`acceptCompany_Id`) REFERENCES `Users` (`Id`),
  CONSTRAINT `FK_73x0pbun6glpsfwtfcerw6ui5` FOREIGN KEY (`fromAddress_Id`) REFERENCES `Address` (`Id`),
  CONSTRAINT `FK_c33w7xhmwnnk8joi17cj56n55` FOREIGN KEY (`toAddress_Id`) REFERENCES `Address` (`Id`),
  CONSTRAINT `FK_cs2xbh48vt7j8f5jcbclhxytk` FOREIGN KEY (`fromUser_Id`) REFERENCES `Users` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tasks`
--

LOCK TABLES `Tasks` WRITE;
/*!40000 ALTER TABLE `Tasks` DISABLE KEYS */;
INSERT INTO `Tasks` VALUES (1,'Paper','2015-04-23 15:46:16','123',NULL,2,2,1),(2,'Clothes','2015-04-23 22:30:48','56',NULL,4,14,3),(3,'Paper','2015-04-23 23:02:22','12',NULL,6,2,5),(4,'Paper','2015-04-23 23:04:13','12',3,8,2,7),(5,'Clothes','2015-04-24 02:58:14','54',NULL,10,2,9);
/*!40000 ALTER TABLE `Tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(255) DEFAULT NULL,
  `Name` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `role_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UK_9cw87ffd4i55ki0qpkwu63er` (`Username`),
  KEY `FK_cd72g22xtwhcbveah8umq8c9n` (`role_Id`),
  CONSTRAINT `FK_cd72g22xtwhcbveah8umq8c9n` FOREIGN KEY (`role_Id`) REFERENCES `Roles` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,NULL,'Jiayang Shen','123','xiaoyang',1),(2,NULL,'Jiaxing Shen','123','xiaoxing',2),(3,NULL,'Hello Delivery','123','c1',3),(10,'234@gmail.com','shen','123','carrie',22),(12,'123435@gmail.com','dsfg','123','customer',24),(14,'234@gmail.com','shen ','123','john',26),(15,'23j354@jdk.com','dsajhk','123','c2',27),(18,'hkdfjhrjt@hotmail.com','Peter Dan','123','peter',30),(24,'rtpoiyo@gmail.com','adjlkfl','123','dan',36);
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-24 17:00:38
