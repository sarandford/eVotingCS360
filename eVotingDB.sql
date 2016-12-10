CREATE DATABASE  IF NOT EXISTS `eVoting` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `eVoting`;
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
INSERT INTO `candidates` VALUES (1,'Hillary Clinton','Democrat'),(2,'Donald Trump','Republican'),(3,'Gary Johnson','Independent'),(4,'Jill Stein','Green'),(5,'No Vote','No Party');
/*!40000 ALTER TABLE `candidates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pollingOfficial`
--

DROP TABLE IF EXISTS `pollingOfficial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pollingOfficial` (
  `id` varchar(255) NOT NULL,
  `name` varchar(45) NOT NULL,
  `salt` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pollingOfficial`
--

LOCK TABLES `pollingOfficial` WRITE;
/*!40000 ALTER TABLE `pollingOfficial` DISABLE KEYS */;
INSERT INTO `pollingOfficial` VALUES ('hL0m9TYoVd0Eiofp3TXAjoF8Op0IIFAqmS6u0bM7hpw=','Hassam Solano','106472486'),('wxTPNRqCed/t57hOU9qceAAYQF/fBk0EX5b0TWRLzBY=','Sarah Ford','1756149373');
/*!40000 ALTER TABLE `pollingOfficial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voters`
--

DROP TABLE IF EXISTS `voters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `voters` (
  `voterId` varchar(255) NOT NULL,
  `name` varchar(45) NOT NULL,
  `birthdate` varchar(45) NOT NULL,
  `hasVoted` tinyint(4) NOT NULL DEFAULT '0',
  `salt` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`voterId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voters`
--

LOCK TABLES `voters` WRITE;
/*!40000 ALTER TABLE `voters` DISABLE KEYS */;
INSERT INTO `voters` VALUES ('/f+mGDnMeZIEXTcDKII65i3blutHTItUHQGBktRmrt4=','Mariko Valdez','05-16-1932',0,'-1862478922'),('0uE8oTmaPWUzQnanJxC4kM/oCdjofcYlxN56JNjPRdg=','Lilah Fitzpatrick','05-22-1958',0,'-1106191046'),('1R+RPPwoKJWHwFItVE5SYJ271lOeOmI2k30qQLWOK60=','Elijah Stephenson','12-18-1993',0,'-438860638'),('3+LrncvlFKelfFj3i1o0yfv4uhM4zAAx9bG/pOtxNHA=','Dennis Dale','10-12-1921',0,'-654770378'),('40j7w9mhZX0EMPodOI0Vt59rtfzPdPSgm2gZ1VoZpxQ=','Aquila Walsh','04-27-1957',0,'1412020580'),('4dfoOH+ceuv4XnHnTTAYu1iveoAJdSmKe1FZuWnPZ90=','Hannah Curry','05-20-1972',0,'-172058591'),('4FL0U6nexYIJjn2zTL6tOst84tn5cdoYKP6Ngfmb2YA=','Edan Olson','03-13-1981',0,'1820831553'),('5/GE0UuPJXl0H3n9J9f1E9TXud25mDIFGUbOSKo7zlo=','Karen Levine','03-17-1986',0,'1929014012'),('5h8Ccz723o0//LkS0rL2KswYoj0TLd0NM9qsmEpNMcE=','Uma Fulton','12-26-1977',0,'-1518932311'),('63J7jb41Z6fcdnlZ/rtU2FGZETdToutzQK62Hl9hOGQ=','Tanner Anderson','07-10-1932',0,'929630261'),('8glfiAmR9gbbdQa47j/Fu/3LgkseRzPpjGLTiVhCY6s=','Jameson Savage','10-21-1929',0,'-603707709'),('8HUSi3JHzw8+4iwOJG9lKXMKypJrP5IhkAwMdW2Fd24=','Lunea Graves','08-01-1916',0,'1991809195'),('8xelHoKBx1JeA6rZ9n8ZL6EjDGMJqk43II0qJWt5Yjo=','Riley Glass','04-12-1930',0,'-1911280981'),('9cmT8h7ozNqTohwxiJl1CCB1pYUwYOjzYHorGJyzyRY=','Jack Marsh','03-14-1933',0,'-1849376900'),('9f6cc7pu1ovre32KiF+GqUtMm4e18pvLsA5W2fvdjmc=','Blair Ferguson','04-30-1949',0,'905174814'),('9nH4woYbAhs5bN8DJqJT5oSMMXzLzRKHxwc+HhIAoh8=','Tana Yang','12-03-1992',0,'-1501486304'),('9vgX8b9Wc1jdNP9qy55pXIClF7t22VgC7SburYouVUQ=','Orson Mcclain','01-21-1978',0,'-633164359'),('A6VJfB2KERr+pfardZr8LB6fOB+xYMzVWfFBzsAocNE=','Flynn Waters','10-06-1964',0,'636269567'),('aATFNfnie6fnkH2c7tRCzO20AfgbR8UvU6TOkAhd4d4=','Leroy Mckenzie','01-04-1989',0,'-234421567'),('aDVtEP7y9Tu9dcelg0t1bBu3x+e8sLFFRuZYmwcocEs=','Ali Bernard','10-13-1942',0,'2051021617'),('Ah4RvNP7oEUARQ1haywOu3cGfaeVDLp4OErViUT+O7k=','Libby Hess','11-30-1992',0,'1406656031'),('aPvuZ2U+cgHnMnDwP7R3AyvAJ0AvYnll8mZMLsQv1yw=','Xaviera Justice','01-04-1981',0,'-617012894'),('Bg5LTVlkooshN/bJ5J3pbm7Fy/uOWbHEJcI3iaNxZkA=','Price Nash','12-30-1987',0,'1984468041'),('bVfkoVVDfsAwyUR5bE07/Rt568jR8NqFaFvFo6/ODL0=','Aaron Walters','05-23-1929',0,'770526350'),('CGUw2crccHgX7ielwebGWY8VS6wT6gLxTQSAqANe0I0=','Kristen Webb','11-11-1994',0,'924525923'),('CLkcXt7awJTg/62gtZFdiNkfuFQyDHkC9qkjrNMxa+s=','Rose Preston','02-06-1972',0,'692553162'),('CY5/bBL2OpbM5AIXJgfWXg8aeqi76XA+Gtt8bd2eCJ4=','Ifeoma Monroe','04-15-1952',0,'1933985077'),('D8iNXwnNK5BkgRqzWXOWfs2zZOgrjGg5ksaGOABIosM=','Maggy Justice','05-13-1964',0,'2010631502'),('djnL2d9jpa213hwacUjq6sOtoefkQ/t6wnISz0/wvkY=','Keiko Albert','08-25-1980',0,'1265624146'),('DnuYWNF59L1/gEo3lqE8DmICqZrfQo3xNhNSvC9n/2E=','Rina Carter','12-12-1947',0,'795253176'),('Dr1ENHR9UEI4Zot3Ydv83d5WMYbtMpvMB2Mu1S4c56o=','Arden Moody','02-12-1970',0,'1943781241'),('dxt4DCCpecmepnovUP5ZB3dW96ysFDbaveTGM1+pXws=','Jescie Randall','10-29-1947',0,'1273225162'),('EKd6vLIy/AcqQft9FVWSZ1r2KUFvxamTPlGyfac8V0Q=','Tamekah Weaver','04-03-1986',0,'891144912'),('EnoCLisWILSLLNMfsyVijscSbBUK1+BAEkWpqjPRCvo=','Juliet Mcdaniel','06-29-1992',0,'1338004955'),('f79AUGvvIEDiXeRrvdnkqjFAUvUyL2Yzu5uQLU4HD3c=','Alyssa Carney','06-09-1952',0,'1694762524'),('gbz+O51pDv27TTP5CEB6iG6MF2nKwCRJdwncd+0ji74=','Constance Finley','06-30-1961',0,'1333506554'),('gCoo+atc8snmXqEsvAimKEjARJYM9w3KrmlJPXb0ay8=','Griffith Slater','11-17-1973',0,'-2002684450'),('gDS16oNEL+KusxVwoDwbloJ8/+0ZHQ/L9lwG6dDmTvA=','Jeremy Daniels','01-29-1916',0,'-475418562'),('gdzvhIKDeWlHaZUsY7qRZRBSyTGT+wt1pJ08gHRgL/Y=','Akeem Woodward','04-13-1950',0,'-1217677945'),('GGXFJG0ZHSkKQkPhNBEL5uTpNEW5lsK/4uDCbKAYq38=','Nita Carson','06-01-1989',0,'-1473443357'),('Gtsuuj3CnjhECjdhLIdRPdKE+szPg6rOpWn8LDsYLhA=','Sybil Houston','09-19-1926',0,'1162225541'),('h/kUV004damIVBtfST07VhWyBiMx9MHag9oFScEo3tw=','Phyllis Cash','03-31-1922',0,'892760874'),('hKZ5P/GMVvQAoxx6Dbt/b8+0kQ8OnG1spiNfTIkaRvg=','Ava Vasquez','09-18-1994',0,'-1391975579'),('hw/ad7e5BaxvdXA5qCDuDwdRsF9G95iWSCwpU+4BL3c=','Jenette Hester','08-15-1938',0,'-283157754'),('iepwRqP9HR3+XpMrzWkQANbQvUkx2sXoqRNeCDhVegk=','Macy Hooper','05-30-1929',0,'697861607'),('je6DwQW4TvOUtvUbSG+IckcESJ3qqGs9r7beV3K5xxg=','Aileen Murray','09-24-1927',0,'-1026885077'),('JfpEz/ZzvNUg5wxywwtSWwGabwlWZHy25dlVkg5gu0w=','Adrian Frazier','02-26-1967',0,'1769920959'),('jrDvnqzeR5XXPQT4TtuivJrB1CgXJLzVgT2V8O6PIlQ=','Mariko Burch','10-03-1969',0,'-1804365849'),('jtdjNUD8rQ9JNwjsc5ITzi4sts2eGHGLrP9+aY6HZ7E=','Eugenia Fitzgerald','03-02-1928',0,'1141367907'),('Ka5iSgc6XJuXSaYVJ8YWqK7ZXqOkYRktjne3QNR269g=','Nero Tate','12-27-1957',0,'1095273958'),('keMCtD3F6E75P763XuFbFi+MZLqr6QeQKCifoalUso4=','Garrison Perez','03-16-1948',0,'-1778416958'),('kIMmSllb7HRU7zJr9uEPuM854L+Gjjbg1YkLslTWHbI=','Lydia Farrell','02-25-1956',0,'305243451'),('kQaxUb4tqlJcY67f+5K0WHd+cNIZn1pMkobn87mvAU0=','Abdul Witt','02-27-1961',0,'1118870117'),('LN7qUJyhX9Lp7Mde2dq/PfvkNmLLRG6q0y60xiQ61iQ=','Kimberly Conway','06-11-1938',0,'1023367889'),('lZ+d5Tlk9Ecir5RJUIpg+D6aRyOtXI025MwrnexPEg4=','Kaitlin Benjamin','12-13-1919',0,'-741826378'),('mApBNhwolp0PkMfhuepzW7uy+Px8SQuocj4M21nqEIc=','Francis Joseph','10-16-1943',0,'-210358707'),('mBDPrxB5BCsABCvvWc3Z+BViyHuzXCVBkw4zrMXsvxU=','Allegra Mcclain','04-06-1913',0,'-697186101'),('mGCeWfiaDCVO2sPc5S1aEojCqXecPdvkwxHHANDdriQ=','Lee Stone','03-28-1979',0,'312774837'),('nk9egddtj+rtZtoxamRlMaOYB0gL0iLjh1qLVVooorM=','Ginger Middleton','12-31-1979',0,'1944267501'),('NqMmUV8zTSaEJzmbfyKZKpg/cDbiAZ+mVrOIE5aPaGs=','Illiana Glass','06-22-1939',0,'-1080152763'),('OeHURSsIVrwbbJjKFJRiFcbv4bjUJmySgOnHbcJS+6o=','Sierra Massey','12-05-1966',0,'-1079618192'),('oFDQhZTJuEW80PW5tFEJwehN4zJdwuMXM1hjl7vWtKA=','Robert Ashley','02-27-1945',0,'1669738495'),('oJdzCvcQg1x6ONJzLpPl4e+rk3mbxRrizVaAHDabxt0=','Amy Glenn','11-27-1935',0,'-219240603'),('OsN43eh7zayKlLEp8cXuFKIUgPkx3zeFbpaiV7BXUeI=','Eleanor Farley','06-20-1978',0,'-1849725756'),('P3No1OgHWdrayCx1AePXfJUIhCdzGFh4S8SUr1S5sIw=','Alana Blair','11-24-1942',0,'416020606'),('pbV4uK4sItJo6yKzGYirOrixM/yct2jjo7dGgNgAd8k=','Helen Blackwell','11-30-1921',0,'740958321'),('q3k8Ae02EMZQQrhNrk5VcFrvcBHgtj0yUJOwFpK3sOw=','Mari Mcknight','02-02-1969',0,'839185511'),('q8GBQUGsNtyYy2JDgpbWkZJ1It/P0JSvGlrlbAeD9Sg=','Ifeoma Watts','06-11-1918',0,'-1475904809'),('qezzjLPA2FnKIsjVZA2zL0M2UlXJWaoYW5sJpg2xc/A=','Chaney Murphy','04-07-1986',0,'-1321663330'),('Qo24fS2bF5FiR6Q3KpUwfVsXVOp7ekLg+5NWFRFSHi4=','Carter Harding','01-27-1933',0,'-203781480'),('qxFlMLk8G2LiN0QMNnDiOGwxWeCYept/rw8BSEJajV4=','Guy Key','09-04-1920',0,'-1683566252'),('rBzr5KoKI8+HXI+pcvQOfECJYas9OgndBCeXhlVmXME=','Noble Fuentes','06-23-1967',0,'-1494953169'),('rwzgFftVDr+iffIN5OcQOTeWtNURelMGVQAF1aSHD2o=','Iliana Snider','06-02-1946',0,'-626362696'),('sbzZw4+jbaG/SX3b/r7CQitlwP/X+rx/68sjE2aGOGg=','Ryder Castaneda','02-06-1960',0,'1624045149'),('SfMaajwPw23cbljwAGR/HdbZdoyT/ESqoF6W1htF0Og=','Dora Sandoval','10-05-1954',0,'1648079519'),('SgCsr/gWqQsq/9T3hf9oKIho7DmHCYer/g9L5UrLCLY=','Honorato Holt','02-12-1911',0,'-1656094353'),('snSgNrCHtjdd9DRwFMX0BhzMfWZgmPCJ2l0mIh8raRE=','Owen Blanchard','06-26-1930',0,'-1660953887'),('SPdK/NyCu7Uat/yD66HNeggce/v+I3cvX+rI+uwEAXk=','Martina Merrill','10-09-1936',0,'474030477'),('sSwviHANijh/Qv5Ih22mg0c8b/iVV5m1tIc8hS7D82g=','Quin Barr','08-23-1965',0,'848224813'),('tRPZa1x7g2l/ka1v/+f9alJdjbNgIH5l7SR1iFtwqRU=','Imani Bryan','11-26-1918',0,'1897210736'),('TUepjPSEsQbxKHXPnbmtxQbZIpfDislSlB5wGGuBrRY=','Nicholas Mcintosh','07-17-1947',0,'-2134650076'),('U3fEeyRTkOVhLr16xsp5ED9wLSp9b+4+8k3WOQILyn8=','Jaquelyn Vargas','11-07-1994',0,'-376488111'),('uv1sJHgJOmXbvR1Yi9PfjHdEedDlXqvW/3+DaRyx4QE=','Alexis Hart','11-09-1926',0,'104726246'),('UZld6nd9eiO+oVAPULVRNisLvaakZVxPKoVPwvAZB4o=','Rahim Fitzpatrick','08-02-1960',0,'-1213395622'),('V4UQRIGH2/I+JopLO/SvFyuWQxFfKxmAGxAkEPwphp0=','Rinah White','12-26-1965',0,'-1784097572'),('VNUDpQ/nbkyxKgyg/GWX/dkY7D9XLwBPGAY2kpNdws8=','Dalton Delaney','03-12-1935',0,'-583887480'),('W9CMRYPRUhIDqctrAdjthZaa8l5HZaGY0pHEQS4H2T0=','Fatima Rowland','01-05-1990',0,'424359790'),('W9j4zTYAkMI52vQhGkRhv3PkJVHxO+SxKryz+0sVpCo=','Maisie Lynn','12-25-1944',0,'1576590053'),('wkipYymx4X81BGg3geSwlrH5x2+ptBmtXJzXBd+o9yE=','Cole Nicholson','06-02-1921',0,'843930387'),('wQzeTsIDDuCmccsG/dTEDjoof/CNDLYnHHJn08F8MK8=','Molly Wyatt','09-15-1994',0,'-846781287'),('WRzAqO4WfLkbATE9+b949YTf2ujGOzkh6OuUxeLIeXg=','Medge Richmond','12-28-1958',0,'-1317452063'),('xgH4cG5tXeORO4w81mdG1x9Y8Aoem8ku3v8y0yh+jrM=','Abel Bishop','04-09-1952',0,'2033390964'),('xZBp9C753jLzJOykWQQZ61UW9lYQtp9LKKHmwNMOj1U=','Darius Sellers','07-27-1961',0,'-2055803334'),('Y1eOVfdnzuMGjNvVBIG+fJ9BEdrmyeSxPn8U8SRWPBs=','Avye Marks','12-04-1916',0,'-1937537831'),('Y6XXSWzbA4bJZwXKJz/ndv/YsIWnif6fzqDQSJAfKpE=','Erica Saunders','03-15-1938',0,'788490051'),('yDVr36MQw6Ruy7mxISzTp5U/K9s92f5rhj3UtYXvdfo=','Gavin Cunningham','06-11-1927',0,'-842681258'),('YILfnFbzquR7H/rhBc7BqXx1X2Z4eyhsrO6UuOIrPmw=','Hammett Davis','11-27-1956',0,'1482568191'),('YJEwW7B/exTeiQDt/h3PxOLyseFx/o7yhFvNWYY/TJM=','Alden Richardson','06-01-1980',0,'1054155811'),('yuDEgi8YkzrUQYyOmsyxewzwxe1ZTxWxq7WqqPrhbME=','Pearl Larson','05-02-1936',0,'801294804'),('ZGyE75/dmAYSAnw6TxwtNA1Ag8koIMtnKYKBQP+X2lA=','Jana Stanton','02-01-1953',0,'1964465482');
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

-- Dump completed on 2016-12-08 14:17:47
