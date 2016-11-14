-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: 127.0.0.1    Database: eVoting
-- ------------------------------------------------------
-- Server version	5.7.16

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
-- Table structure for table `candidates`
--

DROP TABLE IF EXISTS `candidates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `candidates` (
  `candidateId` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `party` varchar(45) NOT NULL,
  PRIMARY KEY (`candidateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidates`
--

LOCK TABLES `candidates` WRITE;
/*!40000 ALTER TABLE `candidates` DISABLE KEYS */;
INSERT INTO `candidates` VALUES (1,'Hillary Clinton','Democrat'),(2,'Donald Trump','Republican'),(3,'Gary Johnson','Independent'),(4,'Jill Stein','Green');
/*!40000 ALTER TABLE `candidates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pollingOfficial`
--

DROP TABLE IF EXISTS `pollingOfficial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pollingOfficial` (
  `id` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pollingOfficial`
--

LOCK TABLES `pollingOfficial` WRITE;
/*!40000 ALTER TABLE `pollingOfficial` DISABLE KEYS */;
INSERT INTO `pollingOfficial` VALUES ('12345','Hassam Solano'),('678910','Sarah Ford');
/*!40000 ALTER TABLE `pollingOfficial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voters`
--

DROP TABLE IF EXISTS `voters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `voters` (
  `voterId` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `birthdate` varchar(45) NOT NULL,
  `hasVoted` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`voterId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voters`
--

LOCK TABLES `voters` WRITE;
/*!40000 ALTER TABLE `voters` DISABLE KEYS */;
INSERT INTO `voters` VALUES ('1600121301099','Nicholas Mcintosh','07-17-1947',0),('1601091983899','Aileen Murray','09-24-1927',0),('1604040301699','Tana Yang','12-03-1992',0),('1604042863699','Quin Barr','08-23-1965',0),('1606061549799','Garrison Perez','03-16-1948',0),('1609010476499','Ginger Middleton','12-31-1979',0),('1609033064899','Darius Sellers','07-27-1961',0),('1610092985399','Rinah White','12-26-1965',0),('1610120206299','Sybil Houston','09-19-1926',0),('1613100173299','Jaquelyn Vargas','11-07-1994',0),('1613110817099','Hannah Curry','05-20-1972',0),('1615072856299','Dora Sandoval','10-05-1954',0),('1616052215199','Jana Stanton','02-01-1953',0),('1617110596199','Kimberly Conway','06-11-1938',0),('1618021503199','Edan Olson','03-13-1981',0),('1618101794599','Nita Carson','06-01-1989',0),('1619050244299','Griffith Slater','11-17-1973',0),('1619081696899','Nero Tate','12-27-1957',0),('1619120323199','Maggy Justice','05-13-1964',0),('1620093025099','Alana Blair','11-24-1942',0),('1621030413499','Ifeoma Watts','06-11-1918',0),('1621082408899','Ifeoma Monroe','04-15-1952',0),('1621110199499','Riley Glass','04-12-1930',0),('1621112219199','Avye Marks','12-04-1916',0),('1623031865099','Phyllis Cash','03-31-1922',0),('1624060978199','Lilah Fitzpatrick','05-22-1958',0),('1624102829399','Aquila Walsh','04-27-1957',0),('1626101656899','Alden Richardson','06-01-1980',0),('1628062619899','Allegra Mcclain','04-06-1913',0),('1628101989599','Adrian Frazier','02-26-1967',0),('1632073048099','Rina Carter','12-12-1947',0),('1633042900499','Mariko Burch','10-03-1969',0),('1634062434399','Abel Bishop','04-09-1952',0),('1637101932699','Libby Hess','11-30-1992',0),('1638110862799','Juliet Mcdaniel','06-29-1992',0),('1640100569099','Abdul Witt','02-27-1961',0),('1642101707199','Xaviera Justice','01-04-1981',0),('1643090514799','Price Nash','12-30-1987',0),('1644111255199','Lydia Farrell','02-25-1956',0),('1648021831599','Honorato Holt','02-12-1911',0),('1649112510199','Macy Hooper','05-30-1929',0),('1650052775499','Robert Ashley','02-27-1945',0),('1650060879399','Maisie Lynn','12-25-1944',0),('1650080657799','Eugenia Fitzgerald','03-02-1928',0),('1651122964099','Ava Vasquez','09-18-1994',0),('1652070421799','Iliana Snider','06-02-1946',0),('1654100930199','Erica Saunders','03-15-1938',0),('1655081038099','Martina Merrill','10-09-1936',0),('1655123082799','Tanner Anderson','07-10-1932',0),('1660031383499','Jescie Randall','10-29-1947',0),('1661051987899','Pearl Larson','05-02-1936',0),('1662091289399','Owen Blanchard','06-26-1930',0),('1663050970399','Helen Blackwell','11-30-1921',0),('1663120299099','Illiana Glass','06-22-1939',0),('1663121360299','Kaitlin Benjamin','12-13-1919',0),('1664080362499','Mari Mcknight','02-02-1969',0),('1666060682099','Jack Marsh','03-14-1933',0),('1667100683999','Tamekah Weaver','04-03-1986',0),('1670021681599','Hammett Davis','11-27-1956',0),('1670021836999','Carter Harding','01-27-1933',0),('1670060949199','Imani Bryan','11-26-1918',0),('1670071262199','Aaron Walters','05-23-1929',0),('1670122710999','Sierra Massey','12-05-1966',0),('1672072364199','Cole Nicholson','06-02-1921',0),('1673042243099','Medge Richmond','12-28-1958',0),('1674011722299','Leroy Mckenzie','01-04-1989',0),('1676093059699','Rahim Fitzpatrick','08-02-1960',0),('1678020440399','Noble Fuentes','06-23-1967',0),('1678031428699','Uma Fulton','12-26-1977',0),('1678052852799','Francis Joseph','10-16-1943',0),('1678100761599','Arden Moody','02-12-1970',0),('1681072329399','Elijah Stephenson','12-18-1993',0),('1681080722499','Ryder Castaneda','02-06-1960',0),('1682012600099','Eleanor Farley','06-20-1978',0),('1684042233099','Flynn Waters','10-06-1964',0),('1684083095499','Lee Stone','03-28-1979',0),('1686112411999','Akeem Woodward','04-13-1950',0),('1687043025099','Karen Levine','03-17-1986',0),('1688022456999','Blair Ferguson','04-30-1949',0),('1688052610199','Dennis Dale','10-12-1921',0),('1688122657599','Dalton Delaney','03-12-1935',0),('1689081330399','Gavin Cunningham','06-11-1927',0),('1689121596199','Jenette Hester','08-15-1938',0),('1691010280499','Fatima Rowland','01-05-1990',0),('1692010356599','Ali Bernard','10-13-1942',0),('1692040407899','Alexis Hart','11-09-1926',0),('1692061049399','Alyssa Carney','06-09-1952',0),('1692061855499','Jeremy Daniels','01-29-1916',0),('1692111248199','Chaney Murphy','04-07-1986',0),('1693020276799','Keiko Albert','08-25-1980',0),('1694012583899','Molly Wyatt','09-15-1994',0),('1694031123099','Rose Preston','02-06-1972',0),('1694051818099','Kristen Webb','11-11-1994',0),('1694071163099','Amy Glenn','11-27-1935',0),('1694111053799','Guy Key','09-04-1920',0),('1695012741399','Jameson Savage','10-21-1929',0),('1695072280699','Constance Finley','06-30-1961',0),('1698052474299','Mariko Valdez','05-16-1932',0),('1698071303799','Orson Mcclain','01-21-1978',0),('1699021736999','Lunea Graves','08-01-1916',0);
/*!40000 ALTER TABLE `voters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `votes`
--

DROP TABLE IF EXISTS `votes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `votes` (
  `candidateId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `votes`
--

LOCK TABLES `votes` WRITE;
/*!40000 ALTER TABLE `votes` DISABLE KEYS */;
/*!40000 ALTER TABLE `votes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-14 14:01:55
