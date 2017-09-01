CREATE DATABASE  IF NOT EXISTS `projet_pentagone` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `projet_pentagone`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: projet_pentagone
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `account_id` int(10) NOT NULL AUTO_INCREMENT,
  `account_balance` decimal(10,4) DEFAULT NULL,
  `creation_date` varchar(45) DEFAULT NULL,
  `account_type` varchar(45) DEFAULT NULL,
  `client_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  KEY `fk_account_client_idx` (`client_id`),
  CONSTRAINT `fk_account_client` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,100000.0000,'2015-11-01','current',NULL),(2,56211.0000,'0000-00-00','savings',NULL),(3,133.0000,'0000-00-00','current',NULL),(4,15336.0000,'0000-00-00','savings',NULL),(5,45669.0000,'0000-00-00','savings',NULL),(6,12344.0000,'0000-00-00','savings',2),(7,504.0000,'0000-00-00','current',NULL),(8,20045.0000,'0000-00-00','current',2),(9,65002.0000,'0000-00-00','savings',NULL),(10,2000.0000,'2012-05-08','current',1),(11,74881.0000,'0000-00-00','savings',NULL),(12,32669.0000,'0000-00-00','savings',NULL),(13,12554.0000,'0000-00-00','savings',2),(14,1123.0000,'0000-00-00','current',NULL),(15,51.0000,'0000-00-00','current',NULL),(16,23665.0000,'0000-00-00','current',NULL),(17,45221.0000,'0000-00-00','savings',NULL),(18,999999.9999,'0000-00-00','savings',NULL),(19,999999.9999,'0000-00-00','savings',NULL),(20,62558.0000,'0000-00-00','savings',NULL);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_executive`
--

DROP TABLE IF EXISTS `bank_executive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank_executive` (
  `exec_id` int(10) unsigned NOT NULL,
  `surname` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`exec_id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_executive`
--

LOCK TABLES `bank_executive` WRITE;
/*!40000 ALTER TABLE `bank_executive` DISABLE KEYS */;
INSERT INTO `bank_executive` VALUES (1,'Dupont','Marcel','moiGrandConseiller','123Soleil'),(2,'Dubois','Michel','moiGrandVizir','laMarelle123'),(3,'Digne','Lucas','moiGrandManitou','Jemarcheendormant'),(4,'Hugo','Lloris','moiGrandPatron','Enrigolant'),(5,'Samuel','Umtiti','moiGrandDefenseur','Legrandsoir'),(6,'Nabil','Fekir','moiGrandTireur','Meaculpa'),(7,'Alexandre','Lacazette','moiGrandTireurDePenalty','Olympique'),(8,'Christophe','Jallet','moiGrandChauve','TransfertDelEte'),(9,'Olivier','Giroud','moiGrandJoeurAerien','ArsenalFC'),(10,'Raphael','Varane','moiGrandMadrilene','RealZizou');
/*!40000 ALTER TABLE `bank_executive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `client_id` int(10) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `zipcode` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `exec_id` int(10) NOT NULL,
  PRIMARY KEY (`client_id`),
  KEY `fk_client_bank_executive_idx` (`exec_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Messi','Lionel','Rue de la Paix','31000','Toulouse','0652336600',1),(2,'Suarez','Luis','Rue des Bois','75200','Paris','0452336622',1),(3,'Piqué','Gerard','Rue des Champs','54233','Lyon','0755221133',1),(4,'Iniesta','Andres','Rue du Temps','65000','Marseille','0955339512',2),(5,'Turan','Arda','Cours Des Cerises','13000','Nice','0521778436',2),(6,'Rakitic','Ivan','Avenue du Printemps','69200','Auxerre','0122001058',2),(7,'Roberto','Sergi','Boulevard Saint Honoré','32100','Dijon','0266953718',2),(8,'Alba','Jordi','Impasse Denis Flaubert','34100','Sochaux','0156943018',3),(9,'Digne','Lucas','Rue de la Voile','57400','Bordeaux','0455219966',3),(10,'Mascherano','Javier','Route du Soleil','68000','Nantes','0455130288',4);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `virement`
--

DROP TABLE IF EXISTS `virement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `virement` (
  `virement_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `dateVirement` date NOT NULL,
  `amount` decimal(10,4) NOT NULL,
  `account_debit_id` int(10) NOT NULL,
  `account_credit_id` int(10) NOT NULL,
  PRIMARY KEY (`virement_id`),
  UNIQUE KEY `money_transfer_id_UNIQUE` (`virement_id`),
  KEY `fk_money_transfer_basic_account_idx` (`account_debit_id`),
  CONSTRAINT `fk_exec_id` FOREIGN KEY (`virement_id`) REFERENCES `bank_executive` (`exec_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `virement`
--

LOCK TABLES `virement` WRITE;
/*!40000 ALTER TABLE `virement` DISABLE KEYS */;
INSERT INTO `virement` VALUES (1,'2015-08-12',100.0000,224,328),(2,'2017-01-13',200.0000,343,165),(3,'2006-11-07',50.0000,721,677);
/*!40000 ALTER TABLE `virement` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-01 19:43:08
