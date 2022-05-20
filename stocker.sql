-- MariaDB dump 10.19  Distrib 10.4.24-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: stocker
-- ------------------------------------------------------
-- Server version	10.4.24-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `cnpj` varchar(14) CHARACTER SET utf8 DEFAULT NULL,
  `cidade` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `estado` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Cleiton','123456789','Tiros','Minas'),(2,'Julio','23145644','Tiros','Minas');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estoque`
--

DROP TABLE IF EXISTS `estoque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estoque` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_produto` varchar(20) NOT NULL,
  `qtdestoque` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque`
--

LOCK TABLES `estoque` WRITE;
/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
INSERT INTO `estoque` VALUES (1,'Maçã',20),(2,'Coca-Cola',100);
/*!40000 ALTER TABLE `estoque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornecedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(20) DEFAULT NULL,
  `cnpj` varchar(14) DEFAULT NULL,
  `inscE` varchar(12) CHARACTER SET utf8 DEFAULT NULL,
  `estado` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `cidade` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `descricao` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `telefone` varchar(11) DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
INSERT INTO `fornecedor` VALUES (1,'Jorge','14572457000185','123456789012','Minas Gerais','Patos de Minas','Jorge é um fornecedor da cidade de Patos de Minas ele vende produtos diversos.','34988567329','jorgefo@gmail.com.br'),(2,'Robson','14572457000187','121212121212','Minas Gerais','Patos de Minas','Robson é um fornecedor de Patos de Minas.','34988759043','robsonfo@gmail.com.br');
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor_produto`
--

DROP TABLE IF EXISTS `fornecedor_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornecedor_produto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fornecedor` varchar(30) DEFAULT NULL,
  `produto` varchar(30) DEFAULT NULL,
  `preco` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor_produto`
--

LOCK TABLES `fornecedor_produto` WRITE;
/*!40000 ALTER TABLE `fornecedor_produto` DISABLE KEYS */;
INSERT INTO `fornecedor_produto` VALUES (1,'Jorge','Maçã',5),(2,'Jorge','Coca-Cola',10),(3,'Robson','Maçã',6);
/*!40000 ALTER TABLE `fornecedor_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notafiscal_saida`
--

DROP TABLE IF EXISTS `notafiscal_saida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notafiscal_saida` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero` varchar(11) NOT NULL,
  `serie` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notafiscal_saida`
--

LOCK TABLES `notafiscal_saida` WRITE;
/*!40000 ALTER TABLE `notafiscal_saida` DISABLE KEYS */;
INSERT INTO `notafiscal_saida` VALUES (1,'000000001',1),(2,'000000002',1),(3,'000000003',2),(4,'000000004',2);
/*!40000 ALTER TABLE `notafiscal_saida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(20) CHARACTER SET utf8 NOT NULL,
  `preco` float NOT NULL,
  `descricao` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (3,'Maçã',5,'Maçã gostosa'),(4,'Coca-Cola',10,'Coca-Cola gostosa');
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto_compra`
--

DROP TABLE IF EXISTS `produto_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto_compra` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `produtoC` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `precoC` float DEFAULT NULL,
  `quantidadeC` int(11) DEFAULT NULL,
  `totalC` float DEFAULT NULL,
  `data_entrada` date DEFAULT NULL,
  `fornecedor` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `frete` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_compra`
--

LOCK TABLES `produto_compra` WRITE;
/*!40000 ALTER TABLE `produto_compra` DISABLE KEYS */;
INSERT INTO `produto_compra` VALUES (1,'Maçã',5,20,106,'2022-05-20','Jorge',6),(2,'Coca-Cola',10,30,312,'2022-05-20','Jorge',12);
/*!40000 ALTER TABLE `produto_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto_venda`
--

DROP TABLE IF EXISTS `produto_venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto_venda` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_produto` varchar(20) NOT NULL,
  `quantidade` int(10) NOT NULL,
  `preco_unitario` float NOT NULL,
  `total` float DEFAULT NULL,
  `data_saida` date DEFAULT NULL,
  `cliente` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_venda`
--

LOCK TABLES `produto_venda` WRITE;
/*!40000 ALTER TABLE `produto_venda` DISABLE KEYS */;
INSERT INTO `produto_venda` VALUES (1,'Maçã',5,7,35,'2022-05-22','Cleiton'),(2,'Coca-Cola',15,12,180,'2022-05-22','Cleiton'),(3,'Maçã',5,10,50,'2022-05-17','Julio'),(4,'Coca-Cola',35,12,420,'2022-05-17','Julio');
/*!40000 ALTER TABLE `produto_venda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_dados`
--

DROP TABLE IF EXISTS `usuario_dados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_dados` (
  `nome_empresa` varchar(30) CHARACTER SET utf8 NOT NULL,
  `cnpj` varchar(14) CHARACTER SET utf8 NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 NOT NULL,
  `telefone` varchar(11) CHARACTER SET utf8 NOT NULL,
  `cidade` varchar(20) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `estado` varchar(30) CHARACTER SET utf8 NOT NULL,
  `endereco` varchar(40) CHARACTER SET utf8 NOT NULL,
  `ganho_mensal` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_dados`
--

LOCK TABLES `usuario_dados` WRITE;
/*!40000 ALTER TABLE `usuario_dados` DISABLE KEYS */;
INSERT INTO `usuario_dados` VALUES ('Tiros Motos','14572457000185','tirosmotos777@gmail.com.br','34988240987','',2,'Minas Gerais (MG)','Avenida José Bomtempo nº 223',30000);
/*!40000 ALTER TABLE `usuario_dados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_login`
--

DROP TABLE IF EXISTS `usuario_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `senha` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `confirma_login` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_login`
--

LOCK TABLES `usuario_login` WRITE;
/*!40000 ALTER TABLE `usuario_login` DISABLE KEYS */;
INSERT INTO `usuario_login` VALUES (1,'TirosMotos','tirosmotos7',1);
/*!40000 ALTER TABLE `usuario_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `varia_estoque`
--

DROP TABLE IF EXISTS `varia_estoque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `varia_estoque` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `produto` varchar(20) DEFAULT NULL,
  `quantidadeE` int(11) DEFAULT NULL,
  `data` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `varia_estoque`
--

LOCK TABLES `varia_estoque` WRITE;
/*!40000 ALTER TABLE `varia_estoque` DISABLE KEYS */;
INSERT INTO `varia_estoque` VALUES (1,'Maçã',10,'2022-05-17'),(2,'Coca-Cola',120,'2022-05-17'),(3,'Maçã',30,'2022-05-20'),(4,'Coca-Cola',150,'2022-05-20'),(5,'Maçã',25,'2022-05-22'),(6,'Coca-Cola',135,'2022-05-22'),(7,'Maçã',20,'2022-05-17'),(8,'Coca-Cola',100,'2022-05-17');
/*!40000 ALTER TABLE `varia_estoque` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-20 15:03:29
