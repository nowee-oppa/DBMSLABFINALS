CREATE DATABASE  IF NOT EXISTS `eskwelahan` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `eskwelahan`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: eskwelahan
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classes` (
  `classcode` char(5) NOT NULL,
  `room` char(5) NOT NULL,
  `day` enum('M','T','W','TH','F','S') NOT NULL,
  `subjid` char(6) NOT NULL,
  `units` int(11) NOT NULL,
  PRIMARY KEY (`classcode`),
  KEY `subjid_idx` (`subjid`),
  CONSTRAINT `subjid` FOREIGN KEY (`subjid`) REFERENCES `subject` (`subjid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES ('9353','D523','S','IT311',3),('9354A','D525','TH','IT312',2),('9354B','D522','F','IT312L',2),('9356','D323','T','IT317',3),('9357','D525','M','IT316',2);
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructor`
--

DROP TABLE IF EXISTS `instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instructor` (
  `instruc_id` int(11) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `gender` enum('M','F') NOT NULL,
  `classcode` char(5) NOT NULL,
  PRIMARY KEY (`instruc_id`),
  KEY `classcode_idx` (`classcode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructor`
--

LOCK TABLES `instructor` WRITE;
/*!40000 ALTER TABLE `instructor` DISABLE KEYS */;
INSERT INTO `instructor` VALUES (11123,'Nathan','Jon','F','9356'),(11345,'Ila','Liag','M','9657'),(11567,'Bert','Lam','M','9353'),(11789,'Betty','Peya','F','9354A'),(11986,'Jessi','Jay','F','9354B');
/*!40000 ALTER TABLE `instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `stud_id` int(11) NOT NULL,
  `fist_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `address` char(45) NOT NULL,
  `gender` enum('M','F') NOT NULL,
  `course` varchar(45) NOT NULL,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`stud_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (2147893,'Mario','Luigi','Baguio City','M','BSIT',4),(2163094,'Patricia ','Ann','Baguio City','F','BSIT',3),(2163492,'Miguel ','Dela','Pangasinan','M','BSIT',3),(2164834,'Veronica','Sims','Baguio City','F','BSIT',2),(2168569,'Hazel','Ignacio','Baguio City','F','BSIT',3);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_class`
--

DROP TABLE IF EXISTS `student_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_class` (
  `classcode` char(5) NOT NULL,
  `instruc_id` int(11) NOT NULL,
  `grade` char(5) NOT NULL,
  `status` enum('P','F','INC','DR','WP') NOT NULL,
  `stud_id` int(11) NOT NULL,
  KEY `instruc_id_idx` (`instruc_id`),
  KEY `classcode_idx` (`classcode`),
  KEY `stud_id_idx` (`stud_id`),
  CONSTRAINT `classcode` FOREIGN KEY (`classcode`) REFERENCES `classes` (`classcode`) ON DELETE CASCADE,
  CONSTRAINT `stud_id` FOREIGN KEY (`stud_id`) REFERENCES `student` (`stud_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_class`
--

LOCK TABLES `student_class` WRITE;
/*!40000 ALTER TABLE `student_class` DISABLE KEYS */;
INSERT INTO `student_class` VALUES ('9356',123,'80','P',2163094),('9357',345,'65','F',2147893),('9353',456,'79','P',2168569),('9354A',678,'65','F',2164834),('9354B',891,'86','P',2163492);
/*!40000 ALTER TABLE `student_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `subjid` char(6) NOT NULL,
  `description` char(45) NOT NULL,
  `units` int(11) NOT NULL,
  `instruc_id` int(11) NOT NULL,
  PRIMARY KEY (`subjid`),
  KEY `instruc_id_idx` (`instruc_id`),
  CONSTRAINT `instruc_id` FOREIGN KEY (`instruc_id`) REFERENCES `instructor` (`instruc_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES ('IT311','Software Modeling and Analysis',3,11567),('IT312','Programming Application',2,11789),('IT312L','Programming Appication',1,11986),('IT316','Human Computer Interactions',3,11345),('IT317','Financial Systems',3,11123);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-08  0:18:55
