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
  `faixaR` float NOT NULL,
  `categoria` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `descricao` varchar(120) CHARACTER SET utf8 DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `telefone` varchar(11) CHARACTER SET utf8 NOT NULL,
  `endereco` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Claudio José','43608631011','Patos de Minas','Minas Gerais',30000,'T.I','Claudio José e um cliente de Patos de Minas.','claudioJ@gmail.com','34988567904','Avenida Alguma Coisa nº 13');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entregador`
--

DROP TABLE IF EXISTS `entregador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entregador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_entregador` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `cpf` varchar(11) CHARACTER SET utf8 DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `telefone` varchar(11) CHARACTER SET utf8 DEFAULT NULL,
  `email` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `estado` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `cidade` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `descricao` varchar(150) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entregador`
--

LOCK TABLES `entregador` WRITE;
/*!40000 ALTER TABLE `entregador` DISABLE KEYS */;
INSERT INTO `entregador` VALUES (1,'João Claudio','09327202007','2000-05-13','34988419535','joaoclaudio@gmail.com','Minas Gerais (MG)','Tiros','João Claudio é um entregador de Tiros MG.');
/*!40000 ALTER TABLE `entregador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entregas_detalhado`
--

DROP TABLE IF EXISTS `entregas_detalhado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entregas_detalhado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `entregador` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `produto` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `qtd` int(11) DEFAULT NULL,
  `cliente` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `endereco` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `data_entrega` date DEFAULT NULL,
  `NF` varchar(9) CHARACTER SET utf8 DEFAULT NULL,
  `estado` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `cidade` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `telefone` varchar(11) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entregas_detalhado`
--

LOCK TABLES `entregas_detalhado` WRITE;
/*!40000 ALTER TABLE `entregas_detalhado` DISABLE KEYS */;
/*!40000 ALTER TABLE `entregas_detalhado` ENABLE KEYS */;
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
  `qtdestoque` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estoque`
--

LOCK TABLES `estoque` WRITE;
/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
INSERT INTO `estoque` VALUES (1,'Maçã',100),(2,'Coca-Cola',125);
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
  `data_nascimento` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
INSERT INTO `fornecedor` VALUES (1,'Gilmar','15274233000150','123456789011','Minas Gerais (MG)','Patos de Minas','Gilmar é um fornecedor de Patos de Minas. Ele vende Coca-Cola','34988754301','gilmarforn@gmail.com','1988-03-12');
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
  `frete` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor_produto`
--

LOCK TABLES `fornecedor_produto` WRITE;
/*!40000 ALTER TABLE `fornecedor_produto` DISABLE KEYS */;
INSERT INTO `fornecedor_produto` VALUES (1,'Gilmar','Coca-Cola',12,5);
/*!40000 ALTER TABLE `fornecedor_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notafiscal_entrada`
--

DROP TABLE IF EXISTS `notafiscal_entrada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notafiscal_entrada` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero` varchar(11) CHARACTER SET utf8 DEFAULT NULL,
  `serie` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notafiscal_entrada`
--

LOCK TABLES `notafiscal_entrada` WRITE;
/*!40000 ALTER TABLE `notafiscal_entrada` DISABLE KEYS */;
INSERT INTO `notafiscal_entrada` VALUES (1,'000000001',1);
/*!40000 ALTER TABLE `notafiscal_entrada` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notafiscal_saida`
--

LOCK TABLES `notafiscal_saida` WRITE;
/*!40000 ALTER TABLE `notafiscal_saida` DISABLE KEYS */;
INSERT INTO `notafiscal_saida` VALUES (1,'000000001',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'Maçã',10,'Maçã de boa qualidade e bem gostosa.'),(2,'Coca-Cola',12,'Coca-Cola muito boa.');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_compra`
--

LOCK TABLES `produto_compra` WRITE;
/*!40000 ALTER TABLE `produto_compra` DISABLE KEYS */;
INSERT INTO `produto_compra` VALUES (1,'Coca-Cola',12,10,125,'2022-06-14','Gilmar',5);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto_venda`
--

LOCK TABLES `produto_venda` WRITE;
/*!40000 ALTER TABLE `produto_venda` DISABLE KEYS */;
INSERT INTO `produto_venda` VALUES (1,'Coca-Cola',5,10,50,'2022-06-14','Claudio José');
/*!40000 ALTER TABLE `produto_venda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relatoriototal`
--

DROP TABLE IF EXISTS `relatoriototal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relatoriototal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_produto` varchar(30) DEFAULT NULL,
  `qtd_total` int(11) DEFAULT 0,
  `preco_total` float DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relatoriototal`
--

LOCK TABLES `relatoriototal` WRITE;
/*!40000 ALTER TABLE `relatoriototal` DISABLE KEYS */;
INSERT INTO `relatoriototal` VALUES (1,'Maçã',0,0),(2,'Coca-Cola',10,125);
/*!40000 ALTER TABLE `relatoriototal` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_dados`
--

LOCK TABLES `usuario_dados` WRITE;
/*!40000 ALTER TABLE `usuario_dados` DISABLE KEYS */;
INSERT INTO `usuario_dados` VALUES ('Tiros Motos','14572457000185','tirosmotos777@gmail.com.br','34988240987','Tiros',2,'Minas Gerais (MG)','Avenida José Bomtempo nº 223',30000),('Alpendre Mart','45705987000188','alpnedremart@hotmail.com','34988765410','Patos de Minas',3,'Minas Gerais (MG)','Avenida Alpendre nº 14',20000);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_login`
--

LOCK TABLES `usuario_login` WRITE;
/*!40000 ALTER TABLE `usuario_login` DISABLE KEYS */;
INSERT INTO `usuario_login` VALUES (1,'TirosMotos','tirosmotos7',1),(2,'AM','45705987000188',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `varia_estoque`
--

LOCK TABLES `varia_estoque` WRITE;
/*!40000 ALTER TABLE `varia_estoque` DISABLE KEYS */;
INSERT INTO `varia_estoque` VALUES (1,'Maçã',100,'2022-06-14'),(2,'Coca-Cola',120,'2022-06-14'),(3,'Coca-Cola',130,'2022-06-14'),(4,'Coca-Cola',125,'2022-06-14');
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

-- Dump completed on 2022-06-14 15:16:01
