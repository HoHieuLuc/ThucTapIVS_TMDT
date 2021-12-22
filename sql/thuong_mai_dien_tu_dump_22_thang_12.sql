CREATE DATABASE  IF NOT EXISTS `thuong_mai_dien_tu` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `thuong_mai_dien_tu`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: thuong_mai_dien_tu
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `anh_san_pham`
--

DROP TABLE IF EXISTS `anh_san_pham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anh_san_pham` (
  `ma_anh` int NOT NULL AUTO_INCREMENT,
  `ma_san_pham` varchar(36) NOT NULL,
  `anh` varchar(255) NOT NULL,
  PRIMARY KEY (`ma_anh`),
  KEY `asp_ibfk_1_idx` (`ma_san_pham`),
  CONSTRAINT `asp_ibfk_1` FOREIGN KEY (`ma_san_pham`) REFERENCES `san_pham` (`ma_san_pham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anh_san_pham`
--

LOCK TABLES `anh_san_pham` WRITE;
/*!40000 ALTER TABLE `anh_san_pham` DISABLE KEYS */;
/*!40000 ALTER TABLE `anh_san_pham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bao_cao_nguoi_dung`
--

DROP TABLE IF EXISTS `bao_cao_nguoi_dung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bao_cao_nguoi_dung` (
  `ma_bao_cao` int NOT NULL AUTO_INCREMENT,
  `id_nguoi_nhan` int NOT NULL,
  `id_nguoi_gui` int NOT NULL,
  `noi_dung` text NOT NULL,
  `status` int NOT NULL,
  `ngay_tao` date NOT NULL,
  PRIMARY KEY (`ma_bao_cao`),
  KEY `bcnd_ibfk_1_idx` (`id_nguoi_nhan`,`id_nguoi_gui`),
  KEY `bcnd_ibfk_1` (`id_nguoi_gui`),
  CONSTRAINT `bcnd_ibfk1` FOREIGN KEY (`id_nguoi_nhan`) REFERENCES `tai_khoan` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `bncd_ibfk2` FOREIGN KEY (`id_nguoi_gui`) REFERENCES `tai_khoan` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bao_cao_nguoi_dung`
--

LOCK TABLES `bao_cao_nguoi_dung` WRITE;
/*!40000 ALTER TABLE `bao_cao_nguoi_dung` DISABLE KEYS */;
/*!40000 ALTER TABLE `bao_cao_nguoi_dung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chi_tiet_dat_hang`
--

DROP TABLE IF EXISTS `chi_tiet_dat_hang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chi_tiet_dat_hang` (
  `ma_dat_hang` int NOT NULL,
  `ma_san_pham` varchar(36) NOT NULL,
  `so_luong` int NOT NULL,
  PRIMARY KEY (`ma_dat_hang`,`ma_san_pham`),
  KEY `ctdh_ibfk_1_idx` (`ma_san_pham`),
  CONSTRAINT `ctdh_ibfk_1` FOREIGN KEY (`ma_san_pham`) REFERENCES `san_pham` (`ma_san_pham`),
  CONSTRAINT `ctdh_ibfk_2` FOREIGN KEY (`ma_dat_hang`) REFERENCES `dat_hang` (`ma_dat_hang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chi_tiet_dat_hang`
--

LOCK TABLES `chi_tiet_dat_hang` WRITE;
/*!40000 ALTER TABLE `chi_tiet_dat_hang` DISABLE KEYS */;
/*!40000 ALTER TABLE `chi_tiet_dat_hang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chuc_vu`
--

DROP TABLE IF EXISTS `chuc_vu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chuc_vu` (
  `ma_chuc_vu` varchar(30) NOT NULL,
  `ten_chuc_vu` varchar(255) NOT NULL,
  PRIMARY KEY (`ma_chuc_vu`),
  UNIQUE KEY `ten_chuc_vu_UNIQUE` (`ten_chuc_vu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chuc_vu`
--

LOCK TABLES `chuc_vu` WRITE;
/*!40000 ALTER TABLE `chuc_vu` DISABLE KEYS */;
/*!40000 ALTER TABLE `chuc_vu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `danh_gia_khach_hang`
--

DROP TABLE IF EXISTS `danh_gia_khach_hang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `danh_gia_khach_hang` (
  `ma_danh_gia` int NOT NULL,
  `ma_kh_danh_gia` int NOT NULL,
  `ma_kh_duoc_danh_gia` int NOT NULL,
  `so_sao` int NOT NULL,
  `ngay_tao` date NOT NULL,
  `ngay_sua` date NOT NULL,
  PRIMARY KEY (`ma_danh_gia`),
  KEY `dgkh_ibfk_1_idx` (`ma_kh_danh_gia`),
  KEY `dgkh_ibfk_2_idx` (`ma_kh_duoc_danh_gia`),
  CONSTRAINT `dgkh_ibfk_1` FOREIGN KEY (`ma_kh_danh_gia`) REFERENCES `khach_hang` (`ma_khach_hang`),
  CONSTRAINT `dgkh_ibfk_2` FOREIGN KEY (`ma_kh_duoc_danh_gia`) REFERENCES `khach_hang` (`ma_khach_hang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `danh_gia_khach_hang`
--

LOCK TABLES `danh_gia_khach_hang` WRITE;
/*!40000 ALTER TABLE `danh_gia_khach_hang` DISABLE KEYS */;
/*!40000 ALTER TABLE `danh_gia_khach_hang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `danh_gia_san_pham`
--

DROP TABLE IF EXISTS `danh_gia_san_pham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `danh_gia_san_pham` (
  `ma_danh_gia` int NOT NULL AUTO_INCREMENT,
  `ma_khach_hang` int NOT NULL,
  `so_sao` smallint NOT NULL,
  `noi_dung` varchar(255) NOT NULL,
  `ma_san_pham` varchar(36) NOT NULL,
  `ngay_tao` date NOT NULL,
  `ngay_sua` date NOT NULL,
  PRIMARY KEY (`ma_danh_gia`,`ma_khach_hang`,`ma_san_pham`),
  KEY `dgsp_ibfk_1_idx` (`ma_khach_hang`),
  KEY `dgsp_ibfk_2_idx` (`ma_san_pham`),
  CONSTRAINT `dgsp_ibfk_1` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`ma_khach_hang`),
  CONSTRAINT `dgsp_ibfk_2` FOREIGN KEY (`ma_san_pham`) REFERENCES `san_pham` (`ma_san_pham`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `danh_gia_san_pham`
--

LOCK TABLES `danh_gia_san_pham` WRITE;
/*!40000 ALTER TABLE `danh_gia_san_pham` DISABLE KEYS */;
INSERT INTO `danh_gia_san_pham` VALUES (1,1,5,'Oh My God, That is so cold','SP001','2021-12-22','2021-12-22');
/*!40000 ALTER TABLE `danh_gia_san_pham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dat_hang`
--

DROP TABLE IF EXISTS `dat_hang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dat_hang` (
  `ma_dat_hang` int NOT NULL AUTO_INCREMENT,
  `ma_khach_hang` int NOT NULL,
  `ngay_dat` date NOT NULL,
  `tong_tien` double NOT NULL,
  `tinh_trang` int NOT NULL,
  PRIMARY KEY (`ma_dat_hang`),
  KEY `dh_ibfk_1_idx` (`ma_khach_hang`),
  CONSTRAINT `dh_ibfk_1` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`ma_khach_hang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dat_hang`
--

LOCK TABLES `dat_hang` WRITE;
/*!40000 ALTER TABLE `dat_hang` DISABLE KEYS */;
/*!40000 ALTER TABLE `dat_hang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gio_hang`
--

DROP TABLE IF EXISTS `gio_hang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gio_hang` (
  `ma_khach_hang` int NOT NULL,
  `ma_san_pham` varchar(36) NOT NULL,
  `so_luong` int NOT NULL,
  PRIMARY KEY (`ma_khach_hang`,`ma_san_pham`),
  KEY `gh_ibfk_2_idx` (`ma_san_pham`),
  CONSTRAINT `gh_ibfk_1` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`ma_khach_hang`),
  CONSTRAINT `gh_ibfk_2` FOREIGN KEY (`ma_san_pham`) REFERENCES `san_pham` (`ma_san_pham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gio_hang`
--

LOCK TABLES `gio_hang` WRITE;
/*!40000 ALTER TABLE `gio_hang` DISABLE KEYS */;
/*!40000 ALTER TABLE `gio_hang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khach_hang`
--

DROP TABLE IF EXISTS `khach_hang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khach_hang` (
  `ma_khach_hang` int NOT NULL AUTO_INCREMENT,
  `ten` varchar(50) NOT NULL,
  `dia_chi` varchar(100) NOT NULL,
  `id_tai_khoan` int NOT NULL,
  `tien_no` double NOT NULL,
  `gioi_thieu` text NOT NULL,
  PRIMARY KEY (`ma_khach_hang`),
  KEY `kh_ibfk_1_idx` (`id_tai_khoan`),
  CONSTRAINT `kh_ibfk_1` FOREIGN KEY (`id_tai_khoan`) REFERENCES `tai_khoan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khach_hang`
--

LOCK TABLES `khach_hang` WRITE;
/*!40000 ALTER TABLE `khach_hang` DISABLE KEYS */;
INSERT INTO `khach_hang` VALUES (1,'Lâm Minh Thiện','421412',4,0,'412421');
/*!40000 ALTER TABLE `khach_hang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kho_hang`
--

DROP TABLE IF EXISTS `kho_hang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kho_hang` (
  `ma_khach_hang` int NOT NULL,
  `ma_san_pham` varchar(36) NOT NULL,
  PRIMARY KEY (`ma_khach_hang`,`ma_san_pham`),
  KEY `kh_ibfk_2_idx` (`ma_san_pham`),
  CONSTRAINT `khohang_ibfk_1` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`ma_khach_hang`),
  CONSTRAINT `khohang_ibfk_2` FOREIGN KEY (`ma_san_pham`) REFERENCES `san_pham` (`ma_san_pham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kho_hang`
--

LOCK TABLES `kho_hang` WRITE;
/*!40000 ALTER TABLE `kho_hang` DISABLE KEYS */;
/*!40000 ALTER TABLE `kho_hang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loai_san_pham`
--

DROP TABLE IF EXISTS `loai_san_pham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loai_san_pham` (
  `ma_loai_sp` int NOT NULL AUTO_INCREMENT,
  `ten_loai_sp` varchar(155) NOT NULL,
  PRIMARY KEY (`ma_loai_sp`),
  UNIQUE KEY `ma_loai_sp_UNIQUE` (`ma_loai_sp`),
  UNIQUE KEY `ten_loai_sp_UNIQUE` (`ten_loai_sp`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loai_san_pham`
--

LOCK TABLES `loai_san_pham` WRITE;
/*!40000 ALTER TABLE `loai_san_pham` DISABLE KEYS */;
INSERT INTO `loai_san_pham` VALUES (1,'Điện thoại');
/*!40000 ALTER TABLE `loai_san_pham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log` (
  `id_log` int NOT NULL AUTO_INCREMENT,
  `id_tai_khoan` int NOT NULL,
  `ngay_tao` date NOT NULL,
  `hoat_dong` text NOT NULL,
  PRIMARY KEY (`id_log`),
  KEY `log_ibfk_1` (`id_tai_khoan`),
  CONSTRAINT `log_ibfk_1` FOREIGN KEY (`id_tai_khoan`) REFERENCES `tai_khoan` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhan_vien`
--

DROP TABLE IF EXISTS `nhan_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhan_vien` (
  `ma_nhan_vien` varchar(20) NOT NULL,
  `ten_nhan_vien` varchar(255) NOT NULL,
  `ma_chuc_vu` varchar(30) NOT NULL,
  `id_tai_khoan` int DEFAULT NULL,
  `luong` double NOT NULL,
  `dia_chi` varchar(255) NOT NULL,
  PRIMARY KEY (`ma_nhan_vien`),
  KEY `nv_ibfk_1_idx` (`ma_chuc_vu`),
  KEY `nv_ibfk_2_idx` (`id_tai_khoan`),
  CONSTRAINT `nv_ibfk_1` FOREIGN KEY (`ma_chuc_vu`) REFERENCES `chuc_vu` (`ma_chuc_vu`),
  CONSTRAINT `nv_ibfk_2` FOREIGN KEY (`id_tai_khoan`) REFERENCES `tai_khoan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhan_vien`
--

LOCK TABLES `nhan_vien` WRITE;
/*!40000 ALTER TABLE `nhan_vien` DISABLE KEYS */;
/*!40000 ALTER TABLE `nhan_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phan_hoi_danh_gia`
--

DROP TABLE IF EXISTS `phan_hoi_danh_gia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phan_hoi_danh_gia` (
  `ma_danh_gia` int NOT NULL,
  `noi_dung` varchar(255) NOT NULL,
  `ma_khach_hang` int NOT NULL,
  PRIMARY KEY (`ma_danh_gia`),
  KEY `phdg_fk_1_idx` (`ma_khach_hang`),
  CONSTRAINT `phdg_fk_1` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`ma_khach_hang`),
  CONSTRAINT `phdg_fk_2` FOREIGN KEY (`ma_danh_gia`) REFERENCES `danh_gia_khach_hang` (`ma_danh_gia`),
  CONSTRAINT `phdg_fk_3` FOREIGN KEY (`ma_danh_gia`) REFERENCES `danh_gia_san_pham` (`ma_danh_gia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phan_hoi_danh_gia`
--

LOCK TABLES `phan_hoi_danh_gia` WRITE;
/*!40000 ALTER TABLE `phan_hoi_danh_gia` DISABLE KEYS */;
/*!40000 ALTER TABLE `phan_hoi_danh_gia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quyen`
--

DROP TABLE IF EXISTS `quyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quyen` (
  `ma_quyen` varchar(30) NOT NULL,
  `ten_quyen` varchar(100) NOT NULL,
  `cap_do` int NOT NULL,
  PRIMARY KEY (`ma_quyen`),
  UNIQUE KEY `ten_quyen_UNIQUE` (`ten_quyen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quyen`
--

LOCK TABLES `quyen` WRITE;
/*!40000 ALTER TABLE `quyen` DISABLE KEYS */;
INSERT INTO `quyen` VALUES ('KH','Khách Hàng',0);
/*!40000 ALTER TABLE `quyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `san_pham`
--

DROP TABLE IF EXISTS `san_pham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `san_pham` (
  `ma_san_pham` varchar(36) NOT NULL,
  `ten_san_pham` varchar(255) NOT NULL,
  `mo_ta` text NOT NULL,
  `gia` double NOT NULL,
  `status` int NOT NULL,
  `ma_loai_san_pham` int NOT NULL,
  `so_luong` int NOT NULL,
  `ngay_dang` date NOT NULL,
  `so_luong_da_ban` int NOT NULL,
  PRIMARY KEY (`ma_san_pham`),
  KEY `sp_fk_1_idx` (`ma_loai_san_pham`),
  CONSTRAINT `sp_fk_1` FOREIGN KEY (`ma_loai_san_pham`) REFERENCES `loai_san_pham` (`ma_loai_sp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `san_pham`
--

LOCK TABLES `san_pham` WRITE;
/*!40000 ALTER TABLE `san_pham` DISABLE KEYS */;
INSERT INTO `san_pham` VALUES ('SP001','Galaxy Note 21 Ultra','Điện thoại samsung S21 ultra',27000000,0,1,20,'2021-01-13',0);
/*!40000 ALTER TABLE `san_pham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `san_pham_yeu_thich`
--

DROP TABLE IF EXISTS `san_pham_yeu_thich`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `san_pham_yeu_thich` (
  `ma_khach_hang` int NOT NULL,
  `ma_san_pham` varchar(36) NOT NULL,
  PRIMARY KEY (`ma_khach_hang`,`ma_san_pham`),
  KEY `spyt_ibfk_1` (`ma_san_pham`),
  CONSTRAINT `spyt_ibfk_1` FOREIGN KEY (`ma_san_pham`) REFERENCES `san_pham` (`ma_san_pham`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `spyt_ibfk_2` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`ma_khach_hang`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `san_pham_yeu_thich`
--

LOCK TABLES `san_pham_yeu_thich` WRITE;
/*!40000 ALTER TABLE `san_pham_yeu_thich` DISABLE KEYS */;
/*!40000 ALTER TABLE `san_pham_yeu_thich` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tai_khoan`
--

DROP TABLE IF EXISTS `tai_khoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tai_khoan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `ngay_tao` date NOT NULL,
  `so_dien_thoai` varchar(20) NOT NULL,
  `ngay_sinh` date NOT NULL,
  `gioi_tinh` tinyint(1) NOT NULL,
  `so_lan_canh_cao` int NOT NULL DEFAULT '0',
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `ma_quyen` varchar(30) NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `tk_ibfk_1_idx` (`ma_quyen`),
  CONSTRAINT `tk_ibfk_1` FOREIGN KEY (`ma_quyen`) REFERENCES `quyen` (`ma_quyen`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tai_khoan`
--

LOCK TABLES `tai_khoan` WRITE;
/*!40000 ALTER TABLE `tai_khoan` DISABLE KEYS */;
INSERT INTO `tai_khoan` VALUES (4,'thienlam782','$2a$12$0ytCrPt6M1p42IqBuqQii.kQqAmboSY7.QuuoJLQLTSxNOVH.Dq3O','thien@gmail.com','2021-12-21','0828504336','2021-12-09',0,0,0,'KH','');
/*!40000 ALTER TABLE `tai_khoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thong_bao`
--

DROP TABLE IF EXISTS `thong_bao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thong_bao` (
  `ma_tb` int NOT NULL AUTO_INCREMENT,
  `id_nguoi_nhan` int NOT NULL,
  `id_nguoi_gui` int NOT NULL,
  `noi_dung` text NOT NULL,
  `ngay_tao` date NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`ma_tb`),
  KEY `tb_ibfk_1_idx` (`id_nguoi_nhan`),
  KEY `tb_ibfk_2_idx` (`id_nguoi_gui`),
  CONSTRAINT `tb_ibfk_1` FOREIGN KEY (`id_nguoi_nhan`) REFERENCES `tai_khoan` (`id`),
  CONSTRAINT `tb_ibfk_2` FOREIGN KEY (`id_nguoi_gui`) REFERENCES `tai_khoan` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thong_bao`
--

LOCK TABLES `thong_bao` WRITE;
/*!40000 ALTER TABLE `thong_bao` DISABLE KEYS */;
/*!40000 ALTER TABLE `thong_bao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thong_tin_lien_he`
--

DROP TABLE IF EXISTS `thong_tin_lien_he`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thong_tin_lien_he` (
  `ma_khach_hang` int NOT NULL,
  `twitter_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `facebook_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `personal_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`ma_khach_hang`),
  UNIQUE KEY `ma_khach_hang_UNIQUE` (`ma_khach_hang`),
  CONSTRAINT `ttlh_ibfk_1` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`ma_khach_hang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thong_tin_lien_he`
--

LOCK TABLES `thong_tin_lien_he` WRITE;
/*!40000 ALTER TABLE `thong_tin_lien_he` DISABLE KEYS */;
/*!40000 ALTER TABLE `thong_tin_lien_he` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thu_phi`
--

DROP TABLE IF EXISTS `thu_phi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thu_phi` (
  `ma_khach_hang` int NOT NULL,
  `ngay_thanh_toan` date NOT NULL,
  `phi` double NOT NULL,
  PRIMARY KEY (`ma_khach_hang`,`ngay_thanh_toan`),
  CONSTRAINT `tp_ibfk_1` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`ma_khach_hang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thu_phi`
--

LOCK TABLES `thu_phi` WRITE;
/*!40000 ALTER TABLE `thu_phi` DISABLE KEYS */;
/*!40000 ALTER TABLE `thu_phi` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-22  9:44:56
