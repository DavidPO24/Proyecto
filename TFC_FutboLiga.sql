CREATE DATABASE  IF NOT EXISTS `proyecto` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `proyecto`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: proyecto
-- ------------------------------------------------------
-- Server version	8.2.0

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
-- Table structure for table `entrenador`
--

DROP TABLE IF EXISTS `entrenador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrenador` (
  `id_entrenador` int NOT NULL AUTO_INCREMENT,
  `id_equipo` int NOT NULL,
  `id_usuario` int NOT NULL,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id_entrenador`),
  UNIQUE KEY `UK_bfxddocunj814ylqkj8vdki2o` (`id_equipo`),
  UNIQUE KEY `UK_jhy5r4xefcus906okonxq8o49` (`id_usuario`),
  CONSTRAINT `FKaffqswx5ltngootef5gkd3d1y` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id_equipo`),
  CONSTRAINT `FKc3byla4dg8fijid98r5w1rd79` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrenador`
--

LOCK TABLES `entrenador` WRITE;
/*!40000 ALTER TABLE `entrenador` DISABLE KEYS */;
INSERT INTO `entrenador` VALUES (2,2,7,'Albertin'),(3,1,18,'Mourinho'),(4,3,21,'Dani');
/*!40000 ALTER TABLE `entrenador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipo` (
  `id_equipo` int NOT NULL AUTO_INCREMENT,
  `nombre_equipo` varchar(255) NOT NULL,
  PRIMARY KEY (`id_equipo`),
  UNIQUE KEY `nombre_equipo_UNIQUE` (`nombre_equipo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` VALUES (4,'Black Cats'),(3,'Camino de las Estrellas'),(1,'Dogfighters'),(6,'Green Wolves'),(2,'White Dragons');
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evento_partido`
--

DROP TABLE IF EXISTS `evento_partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evento_partido` (
  `id_evento_partido` int NOT NULL AUTO_INCREMENT,
  `id_jugador` int NOT NULL,
  `id_partido` int NOT NULL,
  `id_tipo_evento` int NOT NULL,
  `momento_partido` int NOT NULL,
  PRIMARY KEY (`id_evento_partido`),
  KEY `FK9poayrbv336fi5dfsujkbb0a8` (`id_partido`),
  KEY `FK9ryhkwtt4xjl0ju43tdk08d47` (`id_tipo_evento`),
  KEY `FKi2cc5x7to413d53ykpu6431se` (`id_jugador`),
  CONSTRAINT `FK9poayrbv336fi5dfsujkbb0a8` FOREIGN KEY (`id_partido`) REFERENCES `partido` (`id_partido`),
  CONSTRAINT `FK9ryhkwtt4xjl0ju43tdk08d47` FOREIGN KEY (`id_tipo_evento`) REFERENCES `tipoevento` (`idtipo_evento`),
  CONSTRAINT `FKi2cc5x7to413d53ykpu6431se` FOREIGN KEY (`id_jugador`) REFERENCES `jugador` (`id_jugador`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento_partido`
--

LOCK TABLES `evento_partido` WRITE;
/*!40000 ALTER TABLE `evento_partido` DISABLE KEYS */;
INSERT INTO `evento_partido` VALUES (14,1,1,1,4),(15,1,1,2,42),(16,1,1,1,75),(17,1,2,1,5),(18,1,3,1,25),(19,2,3,2,29);
/*!40000 ALTER TABLE `evento_partido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugador`
--

DROP TABLE IF EXISTS `jugador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jugador` (
  `dorsal` int NOT NULL,
  `id_equipo` int NOT NULL,
  `id_jugador` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  `nombre_jugador` varchar(255) NOT NULL,
  PRIMARY KEY (`id_jugador`),
  UNIQUE KEY `UK_p03m9e984cbqgvr7d809gn3f5` (`id_usuario`),
  KEY `FKaokwp6ub68btu8jebvm792fsc` (`id_equipo`),
  CONSTRAINT `FKaokwp6ub68btu8jebvm792fsc` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id_equipo`),
  CONSTRAINT `FKjfmrmyy8c11yfd0h7vnl82g2i` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugador`
--

LOCK TABLES `jugador` WRITE;
/*!40000 ALTER TABLE `jugador` DISABLE KEYS */;
INSERT INTO `jugador` VALUES (7,1,1,2,'Miguelito'),(7,1,2,5,'David'),(15,2,3,6,'Luis'),(1,2,4,8,'David'),(2,1,5,9,'Monica'),(18,1,6,10,'Luis Alberto'),(3,1,7,11,'Laura'),(65,1,8,12,'Marcos'),(9,1,10,14,'Jose'),(8,1,12,16,'Natalia'),(1,4,13,22,'Jason');
/*!40000 ALTER TABLE `jugador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partido`
--

DROP TABLE IF EXISTS `partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partido` (
  `equipo_local` int DEFAULT NULL,
  `equipo_visitante` int DEFAULT NULL,
  `fecha_partido` datetime(6) DEFAULT NULL,
  `id_partido` int NOT NULL AUTO_INCREMENT,
  `jornada` int DEFAULT NULL,
  `estadio` varchar(255) DEFAULT NULL,
  `tiene_datos` bit(1) NOT NULL,
  PRIMARY KEY (`id_partido`),
  KEY `FKrtm26kk52g9yxsj2ya842p9xx` (`equipo_local`),
  KEY `FK85meo6d3n3ds0pdwd0tii5g9e` (`equipo_visitante`),
  CONSTRAINT `FK85meo6d3n3ds0pdwd0tii5g9e` FOREIGN KEY (`equipo_visitante`) REFERENCES `equipo` (`id_equipo`),
  CONSTRAINT `FKrtm26kk52g9yxsj2ya842p9xx` FOREIGN KEY (`equipo_local`) REFERENCES `equipo` (`id_equipo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partido`
--

LOCK TABLES `partido` WRITE;
/*!40000 ALTER TABLE `partido` DISABLE KEYS */;
INSERT INTO `partido` VALUES (1,2,'2023-09-24 00:00:00.000000',1,1,'Cabezón del Manzanares',_binary '\0'),(2,1,'2023-10-28 00:00:00.000000',2,2,'Municipal de Villalba',_binary '\0'),(3,1,'2024-06-07 14:36:00.000000',3,2,'Estadio Barrio del Puerto',_binary '\0'),(4,3,'2024-06-03 14:51:00.000000',4,1,'Campos Madrid Río',_binary '\0'),(6,3,'2024-06-03 14:45:00.000000',5,3,'Campos Madrid Río',_binary '\0');
/*!40000 ALTER TABLE `partido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoevento`
--

DROP TABLE IF EXISTS `tipoevento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipoevento` (
  `idtipo_evento` int NOT NULL AUTO_INCREMENT,
  `nombre_evento` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idtipo_evento`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoevento`
--

LOCK TABLES `tipoevento` WRITE;
/*!40000 ALTER TABLE `tipoevento` DISABLE KEYS */;
INSERT INTO `tipoevento` VALUES (1,'gol'),(2,'amarilla'),(3,'roja'),(5,'penalti');
/*!40000 ALTER TABLE `tipoevento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipousuario`
--

DROP TABLE IF EXISTS `tipousuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipousuario` (
  `id_tipo_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre_tipo_usuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipousuario`
--

LOCK TABLES `tipousuario` WRITE;
/*!40000 ALTER TABLE `tipousuario` DISABLE KEYS */;
INSERT INTO `tipousuario` VALUES (1,'admin'),(2,'jugador'),(3,'entrenador'),(4,'arbitro');
/*!40000 ALTER TABLE `tipousuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_tipo_usuario` int NOT NULL,
  `idusuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`idusuario`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`),
  KEY `FKd7n4ko7gx9bm7o4wvhamcwsrc` (`id_tipo_usuario`),
  CONSTRAINT `FKd7n4ko7gx9bm7o4wvhamcwsrc` FOREIGN KEY (`id_tipo_usuario`) REFERENCES `tipousuario` (`id_tipo_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,1,'admin','admin'),(2,2,'miguel10','miguel10'),(2,5,'davidkv','davidkv'),(2,6,'luisito','luisito'),(3,7,'alberto','alberto'),(2,8,'davidpalacios','davidpalacios'),(2,9,'monicagomez','monicagomez'),(2,10,'abcde','abcde'),(2,11,'fgrh','fgrh'),(2,12,'asdfg','asdfg'),(2,14,'lkjh','lkjh'),(2,16,'awert','awert'),(4,17,'1234567','1234567'),(3,18,'joseM','joseM'),(4,19,'56789','56789'),(4,20,'123456789','123456789'),(3,21,'mithos','mithos'),(2,22,'abc','abc');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-08 19:27:27
