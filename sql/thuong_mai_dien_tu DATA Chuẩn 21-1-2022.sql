-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 21, 2022 at 12:23 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `thuong_mai_dien_tu`
--

-- --------------------------------------------------------

--
-- Table structure for table `anh_san_pham`
--

CREATE TABLE `anh_san_pham` (
  `ma_anh` int(11) NOT NULL,
  `ma_san_pham` varchar(36) NOT NULL,
  `anh` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `anh_san_pham`
--

INSERT INTO `anh_san_pham` (`ma_anh`, `ma_san_pham`, `anh`) VALUES
(1, 'SP001', '1.png'),
(2, 'SP001', '2.png'),
(3, 'test', '3.png'),
(4, 'test', '4.png'),
(5, 'a862b5ce-64d6-11ec-bb14-8378cfa7d63d', '5.png'),
(6, 'a862b5ce-64d6-11ec-bb14-8378cfa7d63d', '6.png'),
(7, 'a862b5ce-64d6-11ec-bb14-8378cfa7d63d', '7.png'),
(8, 'a862b5ce-64d6-11ec-bb14-8378cfa7d63d', '8.png'),
(9, '35a99f29-64da-11ec-bb14-8378cfa7d63d', '9.png'),
(10, '35a99f29-64da-11ec-bb14-8378cfa7d63d', '10.png'),
(11, '35a99f29-64da-11ec-bb14-8378cfa7d63d', '11.png'),
(12, '35a99f29-64da-11ec-bb14-8378cfa7d63d', '12.png'),
(13, '35a99f29-64da-11ec-bb14-8378cfa7d63d', '13.png'),
(14, '5dfb7106-651f-11ec-b702-7845f2f0d96e', '14.png'),
(15, '5dfb7106-651f-11ec-b702-7845f2f0d96e', '15.png'),
(16, '5dfb7106-651f-11ec-b702-7845f2f0d96e', '16.png'),
(17, '5dfb7106-651f-11ec-b702-7845f2f0d96e', '17.png'),
(18, '5dfb7106-651f-11ec-b702-7845f2f0d96e', '18.png'),
(19, '130ea67a-6528-11ec-b702-7845f2f0d96e', '19.png'),
(20, '130ea67a-6528-11ec-b702-7845f2f0d96e', '20.png'),
(21, '130ea67a-6528-11ec-b702-7845f2f0d96e', '21.png'),
(22, '130ea67a-6528-11ec-b702-7845f2f0d96e', '22.png'),
(23, 'bc49e268-6528-11ec-b702-7845f2f0d96e', '23.png'),
(24, 'bc49e268-6528-11ec-b702-7845f2f0d96e', '24.png'),
(25, 'bc49e268-6528-11ec-b702-7845f2f0d96e', '25.png'),
(26, 'e4f55954-652b-11ec-b702-7845f2f0d96e', '26.png'),
(27, 'e4f55954-652b-11ec-b702-7845f2f0d96e', '27.png'),
(28, 'e4f55954-652b-11ec-b702-7845f2f0d96e', '28.png'),
(29, 'e4f55954-652b-11ec-b702-7845f2f0d96e', '29.png'),
(30, 'e4f55954-652b-11ec-b702-7845f2f0d96e', '30.png'),
(31, '1e717293-652c-11ec-b702-7845f2f0d96e', '31.png'),
(32, '1e717293-652c-11ec-b702-7845f2f0d96e', '32.png'),
(33, '1e717293-652c-11ec-b702-7845f2f0d96e', '33.png'),
(34, '1e717293-652c-11ec-b702-7845f2f0d96e', '34.png'),
(35, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', '35.png'),
(36, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', '36.png'),
(37, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', '37.png'),
(38, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', '38.png'),
(39, 'aa76ecd2-6b6b-11ec-8506-571a7e295222', '39.png'),
(40, 'aa76ecd2-6b6b-11ec-8506-571a7e295222', '40.png'),
(41, 'aa76ecd2-6b6b-11ec-8506-571a7e295222', '41.png'),
(42, 'aa76ecd2-6b6b-11ec-8506-571a7e295222', '42.png'),
(43, 'aa76ecd2-6b6b-11ec-8506-571a7e295222', '43.png'),
(44, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', '44.png'),
(45, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', '45.png'),
(46, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', '46.png'),
(47, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', '47.png'),
(48, '560c2201-6ef1-11ec-9aea-4c52b493c111', '48.png'),
(49, '560c2201-6ef1-11ec-9aea-4c52b493c111', '49.png'),
(50, 'd23e005c-6ef6-11ec-9aea-4c52b493c111', '50.png'),
(51, 'd23e005c-6ef6-11ec-9aea-4c52b493c111', '51.png'),
(52, '699515ad-737a-11ec-9e8d-e836550a30d1', '1641973013630_image_2022-01-12_143430.png'),
(53, '699515ad-737a-11ec-9e8d-e836550a30d1', '1641973013651_image_2022-01-12_143451.png'),
(54, '10627767-74a2-11ec-ab12-0dd3fc775f75', '1642099996805_image_2022-01-14_014833.png'),
(55, '10627767-74a2-11ec-ab12-0dd3fc775f75', '1642099996833_image_2022-01-14_015215.png'),
(56, '426249a8-7515-11ec-bf3a-fcf5d54cfc0d', '1642149471034_image_2022-01-14_153655.png'),
(57, '426249a8-7515-11ec-bf3a-fcf5d54cfc0d', '1642149471063_2022-Honda-Civic-2.jpg'),
(58, '426249a8-7515-11ec-bf3a-fcf5d54cfc0d', '1642149471075_2022-honda-civic-preview-malaysia-ext-1-1200x609.jpeg'),
(59, '5c3f89c8-751c-11ec-bf3a-fcf5d54cfc0d', '1642152520888_2022-Honda-Civic-2.jpg'),
(60, '5c3f89c8-751c-11ec-bf3a-fcf5d54cfc0d', '1642152520900_2022-honda-civic-preview-malaysia-ext-1-1200x609.jpeg'),
(61, 'a7e477d7-7927-11ec-bd3a-40b7d60cb467', '1642597176923_daniel-korpai-hbTKIbuMmBI-unsplash.jpg'),
(62, 'a7e477d7-7927-11ec-bd3a-40b7d60cb467', '1642597176943_eniko-kis-KsLPTsYaqIQ-unsplash.jpg'),
(63, 'a7e477d7-7927-11ec-bd3a-40b7d60cb467', '1642597176956_giorgio-trovato-K62u25Jk6vo-unsplash.jpg'),
(64, 'a7e477d7-7927-11ec-bd3a-40b7d60cb467', '1642597176970_imani-bahati-LxVxPA1LOVM-unsplash.jpg'),
(65, 'da3b10b1-7945-11ec-bd3a-40b7d60cb467', '1642610146275_giorgio-trovato-K62u25Jk6vo-unsplash.jpg'),
(66, 'da3b10b1-7945-11ec-bd3a-40b7d60cb467', '1642610146300_imani-bahati-LxVxPA1LOVM-unsplash.jpg'),
(67, 'da3b10b1-7945-11ec-bd3a-40b7d60cb467', '1642610146314_rachit-tank-2cFZ_FB08UM-unsplash.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `bao_cao_nguoi_dung`
--

CREATE TABLE `bao_cao_nguoi_dung` (
  `ma_bao_cao` int(11) NOT NULL,
  `id_nguoi_nhan` int(11) NOT NULL,
  `id_nguoi_gui` int(11) NOT NULL,
  `noi_dung` text NOT NULL,
  `status` int(11) NOT NULL,
  `ngay_tao` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `bao_cao_nguoi_dung`
--

INSERT INTO `bao_cao_nguoi_dung` (`ma_bao_cao`, `id_nguoi_nhan`, `id_nguoi_gui`, `noi_dung`, `status`, `ngay_tao`) VALUES
(1, 24, 10, 'trẻ trâu quá trời ơi khóa tài khoản nó giùm cái', 1, '2022-01-14 18:56:19'),
(2, 25, 10, 'aagaggggggggggggggggggggggggggggggggggg', 0, '2022-01-19 23:20:34'),
(3, 25, 10, 'ffffffffffffffffffffffffffffffffffffffffffffffffffffffffff', 0, '2022-01-19 23:20:39'),
(4, 25, 10, 'ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff', 0, '2022-01-19 23:20:40'),
(5, 25, 10, 'ffffffffffffffffffffffffffffffffffffffffffffffffffffffffff', 0, '2022-01-19 23:20:41'),
(6, 25, 10, 'ffffffffffffffffffffffffffffffffffffffffffffffffffffffffff', 0, '2022-01-19 23:20:42'),
(7, 25, 10, 'ffffffffffffffffffffffffffffffffffffffffffffffffffffffffff', 0, '2022-01-19 23:20:43'),
(8, 25, 10, 'ffffffffffffffffffffffffffffffffffffffffffffffffffffffffff', 0, '2022-01-19 23:20:44'),
(9, 25, 10, 'ffffffffffffffffffffffffffffffffffffffffffffffffffffffffff', 0, '2022-01-19 23:20:45'),
(10, 10, 27, 'faffaggggggggggggggggggggggggggg', 0, '2022-01-19 23:24:26'),
(11, 10, 27, 'removeURLparam(\'page\');', 0, '2022-01-19 23:24:27'),
(12, 10, 27, 'removeURLparam(\'page\');', 0, '2022-01-19 23:24:29'),
(13, 10, 27, 'fffffffffffffffffffffffffffffaaaaaaaaaaaaaaaaaaaaaaaaaa', 0, '2022-01-19 23:24:50'),
(14, 10, 27, 'fffffffffffffffffffffffffffffaaaaaaaaaaaaaaaaaaaaaaaaaa', 0, '2022-01-19 23:24:51'),
(15, 10, 27, 'fffffffffffffffffffffffffffffaaaaaaaaaaaaaaaaaaaaaaaaaa', 0, '2022-01-19 23:24:51'),
(16, 10, 27, 'fffffffffffffffffffffffffffffaaaaaaaaaaaaaaaaaaaaaaaaaa', 0, '2022-01-19 23:24:52'),
(17, 10, 27, 'fffffffffffffffffffffffffffffaaaaaaaaaaaaaaaaaaaaaaaaaa', 0, '2022-01-19 23:24:53'),
(18, 10, 27, 'fffffffffffffffffffffffffffffaaaaaaaaaaaaaaaaaaaaaaaaaa', 0, '2022-01-19 23:24:54'),
(19, 10, 27, 'fffffffffffffffffffffffffffffaaaaaaaaaaaaaaaaaaaaaaaaaa', 0, '2022-01-19 23:24:55'),
(20, 10, 27, 'fffffffffffffffffffffffffffffaaaaaaaaaaaaaaaaaaaaaaaaaa', 0, '2022-01-19 23:24:56'),
(21, 10, 27, 'fffffffffffffffffffffffffffffaaaaaaaaaaaaaaaaaaaaaaaaaa', 0, '2022-01-19 23:24:56'),
(22, 10, 27, 'fffffffffffffffffffffffffffffaaaaaaaaaaaaaaaaaaaaaaaaaa', 0, '2022-01-19 23:24:57');

-- --------------------------------------------------------

--
-- Table structure for table `chi_tiet_dat_hang`
--

CREATE TABLE `chi_tiet_dat_hang` (
  `ma_dat_hang` int(11) NOT NULL,
  `ma_san_pham` varchar(36) NOT NULL,
  `so_luong` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 0,
  `ma_nhan_hang` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chi_tiet_dat_hang`
--

INSERT INTO `chi_tiet_dat_hang` (`ma_dat_hang`, `ma_san_pham`, `so_luong`, `status`, `ma_nhan_hang`) VALUES
(18, '130ea67a-6528-11ec-b702-7845f2f0d96e', 4, 2, 'DED49B'),
(18, 'SP001', 1, -1, '97DAEF'),
(19, 'bc49e268-6528-11ec-b702-7845f2f0d96e', 1, -1, 'B65305'),
(20, '5dfb7106-651f-11ec-b702-7845f2f0d96e', 2, -1, '9FBCB2'),
(21, '130ea67a-6528-11ec-b702-7845f2f0d96e', 2, -1, '57594C'),
(21, '35a99f29-64da-11ec-bb14-8378cfa7d63d', 2, -1, 'D85BD3'),
(21, '5dfb7106-651f-11ec-b702-7845f2f0d96e', 1, -1, '426B60'),
(21, 'test', 3, -1, 'B4BA57'),
(25, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', 2, -1, 'A1FC29'),
(25, '5dfb7106-651f-11ec-b702-7845f2f0d96e', 1, 0, '16A41D'),
(26, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', 4, -1, 'D97A14'),
(26, '5dfb7106-651f-11ec-b702-7845f2f0d96e', 1, 0, '2F25AC'),
(27, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', 1, 0, '7D9538'),
(27, '5dfb7106-651f-11ec-b702-7845f2f0d96e', 1, 0, 'D40D52'),
(27, 'aa76ecd2-6b6b-11ec-8506-571a7e295222', 1, 0, '91C67A'),
(28, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', 1, 0, 'E8F4F4'),
(28, 'aa76ecd2-6b6b-11ec-8506-571a7e295222', 2, 0, '382769'),
(29, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 2, 0, 'AFCED3'),
(29, 'aa76ecd2-6b6b-11ec-8506-571a7e295222', 3, 0, 'E5E7A0'),
(29, 'd23e005c-6ef6-11ec-9aea-4c52b493c111', 2, 0, 'C6E95B'),
(30, '5dfb7106-651f-11ec-b702-7845f2f0d96e', 2, 0, '8419ED'),
(31, '130ea67a-6528-11ec-b702-7845f2f0d96e', 3, 0, 'F3F9E5'),
(32, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', 1, -1, '6AB360'),
(32, '560c2201-6ef1-11ec-9aea-4c52b493c111', 1, 0, 'CA2A1F'),
(32, '5dfb7106-651f-11ec-b702-7845f2f0d96e', 1, 0, 'F68B1F'),
(32, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, 0, '5CCCCB'),
(32, 'aa76ecd2-6b6b-11ec-8506-571a7e295222', 1, 0, 'E8B3CB'),
(32, 'd23e005c-6ef6-11ec-9aea-4c52b493c111', 1, 0, 'C6587D'),
(33, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 3, 0, '1C76A9'),
(34, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, -1, '606B2B'),
(35, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, -1, '48EFAF'),
(36, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, 0, '976F18'),
(37, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, 0, 'E17BB6'),
(38, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, 0, 'E26D6B'),
(39, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, 0, '143CB8'),
(40, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, 0, '676441'),
(41, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, -1, 'E2DFAF'),
(41, 'd23e005c-6ef6-11ec-9aea-4c52b493c111', 1, -1, '55F9DE'),
(42, '560c2201-6ef1-11ec-9aea-4c52b493c111', 1, 2, '7267D2'),
(42, 'd23e005c-6ef6-11ec-9aea-4c52b493c111', 1, -1, '97FB85'),
(43, '560c2201-6ef1-11ec-9aea-4c52b493c111', 1, -1, 'F72683'),
(43, 'd23e005c-6ef6-11ec-9aea-4c52b493c111', 1, 2, 'F048E7'),
(44, '560c2201-6ef1-11ec-9aea-4c52b493c111', 1, 2, 'AFE6C3'),
(44, 'd23e005c-6ef6-11ec-9aea-4c52b493c111', 1, -1, 'BBC39B'),
(45, '560c2201-6ef1-11ec-9aea-4c52b493c111', 1, -1, '9EDFE8'),
(45, 'd23e005c-6ef6-11ec-9aea-4c52b493c111', 1, -1, 'E1BD55'),
(46, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', 1, -1, '4831F4'),
(47, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', 2, 0, '77B094'),
(47, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, 0, '7084C9'),
(48, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 99, 2, '1DCCA8'),
(49, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 2, 1, '0BA9BF'),
(50, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, -1, '645101'),
(51, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, -1, 'DD101B'),
(52, 'bc49e268-6528-11ec-b702-7845f2f0d96e', 1, 0, '685831'),
(53, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, -1, '3F31B3'),
(54, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, -1, '103102'),
(55, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, -1, '090A25'),
(56, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, -1, 'E427B8'),
(57, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, -1, '0D0739'),
(58, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, -1, 'C85693'),
(59, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, -1, '369355'),
(60, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, -1, 'FEF415'),
(61, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, -1, '04CE42'),
(62, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, -1, 'E092A0'),
(63, 'aa76ecd2-6b6b-11ec-8506-571a7e295222', 2, -1, '50650F'),
(64, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', 2, -1, 'FAC14C'),
(64, 'aa76ecd2-6b6b-11ec-8506-571a7e295222', 1, 1, '0ABD93'),
(65, '35a99f29-64da-11ec-bb14-8378cfa7d63d', 2, 1, '5627C0'),
(65, 'a862b5ce-64d6-11ec-bb14-8378cfa7d63d', 1, -1, '8F8E3E'),
(66, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, 1, '1F9940'),
(67, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 3, 2, 'AD7646'),
(72, '35a99f29-64da-11ec-bb14-8378cfa7d63d', 2, -1, 'DFB9AD'),
(73, '35a99f29-64da-11ec-bb14-8378cfa7d63d', 1, 2, 'F91FFD'),
(73, '5dfb7106-651f-11ec-b702-7845f2f0d96e', 1, 0, '413734'),
(73, 'a862b5ce-64d6-11ec-bb14-8378cfa7d63d', 2, 0, '4A7845'),
(73, 'SP001', 1, 0, 'B3C95A'),
(74, 'SP001', 11, -1, '8D2D8B'),
(75, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', 1, 1, '44084D'),
(75, '10627767-74a2-11ec-ab12-0dd3fc775f75', 2, 1, '8D4953'),
(75, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, 1, '1D6F03'),
(75, 'aa76ecd2-6b6b-11ec-8506-571a7e295222', 1, 1, '375063'),
(76, '426249a8-7515-11ec-bf3a-fcf5d54cfc0d', 1, -1, 'A089A7'),
(76, '5c3f89c8-751c-11ec-bf3a-fcf5d54cfc0d', 1, -1, '20AA06'),
(77, '10627767-74a2-11ec-ab12-0dd3fc775f75', 1, -1, 'D4828C'),
(77, '426249a8-7515-11ec-bf3a-fcf5d54cfc0d', 1, -1, '93CF56'),
(77, '5c3f89c8-751c-11ec-bf3a-fcf5d54cfc0d', 1, 2, '153AC9'),
(78, '5c3f89c8-751c-11ec-bf3a-fcf5d54cfc0d', 4, -1, 'A47019'),
(79, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 0, -1, 'B5ED4B');

--
-- Triggers `chi_tiet_dat_hang`
--
DELIMITER $$
CREATE TRIGGER `thong_bao_don_hang` AFTER UPDATE ON `chi_tiet_dat_hang` FOR EACH ROW BEGIN
	IF (NEW.status = 1) THEN
    	INSERT INTO thong_bao(id_nguoi_nhan, id_nguoi_gui, noi_dung, ngay_tao, status)
        SELECT tk_nhan.id, -1, CONCAT("Sản phẩm <b>", sp.ten_san_pham, "</b> do bạn đặt ngày ", 					DATE_FORMAT(dh.ngay_dat, '%d-%m-%Y lúc %T'), " đang được vận chuyển."), NOW(), 0
        FROM khach_hang kh_nhan JOIN tai_khoan tk_nhan ON tk_nhan.id = kh_nhan.id_tai_khoan
        JOIN dat_hang dh ON dh.ma_dat_hang = NEW.ma_dat_hang
        JOIN san_pham sp ON sp.ma_san_pham = NEW.ma_san_pham
        WHERE kh_nhan.ma_khach_hang = dh.ma_khach_hang;
    ELSEIF (NEW.status = 2) THEN
    	INSERT INTO thong_bao(id_nguoi_nhan, id_nguoi_gui, noi_dung, ngay_tao, status)
        SELECT kh_nhan.id_tai_khoan, -1, 
        CONCAT("Sản phẩm <b>", sp.ten_san_pham, "</b> do <u><i>", kh_mua.ten,"</i></u> đặt đã được giao thành công."),
        NOW(), 0
        FROM khach_hang kh_nhan JOIN san_pham sp ON sp.ma_khach_hang = kh_nhan.ma_khach_hang
        JOIN dat_hang dh ON dh.ma_dat_hang = NEW.ma_dat_hang
        JOIN khach_hang kh_mua ON kh_mua.ma_khach_hang = dh.ma_khach_hang
        JOIN tai_khoan tk_mua ON tk_mua.id = kh_mua.id_tai_khoan
        WHERE sp.ma_san_pham = NEW.ma_san_pham;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `chuc_vu`
--

CREATE TABLE `chuc_vu` (
  `ma_chuc_vu` varchar(30) NOT NULL,
  `ten_chuc_vu` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `chuc_vu`
--

INSERT INTO `chuc_vu` (`ma_chuc_vu`, `ten_chuc_vu`) VALUES
('giamdoc', 'Giám đốc');

-- --------------------------------------------------------

--
-- Table structure for table `danh_gia_san_pham`
--

CREATE TABLE `danh_gia_san_pham` (
  `ma_danh_gia` int(11) NOT NULL,
  `ma_khach_hang` int(11) NOT NULL,
  `so_sao` smallint(6) NOT NULL,
  `noi_dung` varchar(255) NOT NULL,
  `ma_san_pham` varchar(36) NOT NULL,
  `ngay_tao` datetime NOT NULL,
  `ngay_sua` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `danh_gia_san_pham`
--

INSERT INTO `danh_gia_san_pham` (`ma_danh_gia`, `ma_khach_hang`, `so_sao`, `noi_dung`, `ma_san_pham`, `ngay_tao`, `ngay_sua`) VALUES
(1, 1, 5, 'Oh My God, That is so cold 421222222222222222222222', 'SP001', '2021-12-22 07:13:15', '2021-12-27 09:47:32'),
(7, 2, 1, 'gagag', 'SP001', '2021-12-23 05:27:46', '2021-12-23 20:08:34'),
(8, 2, 1, 'f', 'SP001', '2021-12-23 08:20:48', '2021-12-27 09:46:16'),
(9, 15, 1, 'fafaf', '35a99f29-64da-11ec-bb14-8378cfa7d63d', '2021-12-25 14:38:19', '2021-12-25 08:44:26'),
(13, 15, 1, 'adad', '5dfb7106-651f-11ec-b702-7845f2f0d96e', '2021-12-25 08:13:00', '2021-12-27 07:30:47'),
(14, 15, 1, 'alo thiện ơi', 'a862b5ce-64d6-11ec-bb14-8378cfa7d63d', '2021-12-25 12:00:00', '2021-12-27 11:24:47'),
(15, 17, 4, 'ồ năm nay tai thỏ ngắn hơn rồi', '35a99f29-64da-11ec-bb14-8378cfa7d63d', '2021-12-25 05:08:00', '2021-12-27 11:18:47'),
(16, 15, 4, 'hello', '130ea67a-6528-11ec-b702-7845f2f0d96e', '2021-12-25 06:11:14', '2021-12-27 11:20:47'),
(18, 17, 5, 'Màn hình to thật, xem phim giải trí thật thích', 'bc49e268-6528-11ec-b702-7845f2f0d96e', '2021-12-25 10:23:17', '2021-12-27 06:12:47'),
(20, 19, 4, 'gsgsgdadfafaf', '1e717293-652c-11ec-b702-7845f2f0d96e', '2021-12-26 10:10:29', '2021-12-26 06:28:30'),
(21, 18, 5, '4214', '1e717293-652c-11ec-b702-7845f2f0d96e', '2021-12-27 03:08:41', '2021-12-27 03:08:41'),
(22, 1, 5, '1234', '130ea67a-6528-11ec-b702-7845f2f0d96e', '2021-12-27 06:16:39', '2021-12-28 08:26:34'),
(23, 1, 1, '9999999999999', '35a99f29-64da-11ec-bb14-8378cfa7d63d', '2021-12-27 14:32:18', '2021-12-27 14:33:16'),
(24, 1, 1, '41242w112212', 'test', '2021-12-28 08:05:52', '2021-12-28 08:05:52'),
(25, 1, 5, 'aa', '1e717293-652c-11ec-b702-7845f2f0d96e', '2021-12-28 08:13:54', '2021-12-28 08:14:04'),
(26, 1, 1, '412412', 'bc49e268-6528-11ec-b702-7845f2f0d96e', '2021-12-28 09:53:24', '2021-12-28 10:53:13'),
(27, 1, 1, '4214', 'e4f55954-652b-11ec-b702-7845f2f0d96e', '2021-12-28 12:11:30', '2021-12-28 12:11:30'),
(28, 21, 5, 'rất\r\nlà\r\nok', '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', '2021-12-31 21:59:29', '2021-12-31 21:59:29'),
(32, 19, 4, 'fag\r\nga\r\nha\r\nh', '130ea67a-6528-11ec-b702-7845f2f0d96e', '2022-01-04 14:31:14', '2022-01-04 14:31:14'),
(33, 19, 5, 'xd', 'aa76ecd2-6b6b-11ec-8506-571a7e295222', '2022-01-04 15:13:22', '2022-01-04 15:13:22'),
(34, 6, 5, 'okkkkk', 'test', '2022-01-09 22:45:31', '2022-01-09 22:45:31'),
(36, 8, 5, 'agggs', '130ea67a-6528-11ec-b702-7845f2f0d96e', '2022-01-12 16:45:56', '2022-01-12 16:45:56'),
(37, 16, 3, 'fafaf', '130ea67a-6528-11ec-b702-7845f2f0d96e', '2021-12-08 16:46:18', '2022-01-12 16:46:18'),
(42, 6, 1, 'hàng nhái à', '35a99f29-64da-11ec-bb14-8378cfa7d63d', '2022-01-13 00:56:52', '2022-01-13 00:56:52'),
(43, 20, 5, 'ok', '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', '2022-01-14 10:06:19', '2022-01-14 10:06:19'),
(44, 19, 4, 'ok', '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', '2022-01-14 16:59:11', '2022-01-14 16:59:11'),
(45, 19, 4, 'ok', '5c3f89c8-751c-11ec-bf3a-fcf5d54cfc0d', '2022-01-14 18:04:02', '2022-01-14 18:04:02'),
(46, 19, 5, 'ok\r\nko\r\nk\r\nk\r\n\r\n\r\noiy\r\nyi\r\ny\r\n', '426249a8-7515-11ec-bf3a-fcf5d54cfc0d', '2022-01-14 18:04:40', '2022-01-14 18:04:40'),
(47, 22, 4, 'fqf\r\nqf\r\nq\r\nfq\r\nf\r\nqf\r\nqf', '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', '2022-01-14 18:11:27', '2022-01-14 18:11:27'),
(50, 19, 3, 'gsgsg', '10627767-74a2-11ec-ab12-0dd3fc775f75', '2022-01-21 13:23:54', '2022-01-21 13:23:54');

--
-- Triggers `danh_gia_san_pham`
--
DELIMITER $$
CREATE TRIGGER `thong_bao_danh_gia_san_pham` AFTER INSERT ON `danh_gia_san_pham` FOR EACH ROW BEGIN 
	INSERT INTO thong_bao (id_nguoi_nhan, id_nguoi_gui, noi_dung, ngay_tao, status)
    SELECT tk_nhan.id, tk_gui.id, CONCAT("Đã đánh giá sản phẩm <b>", sp.ten_san_pham, "</b> của bạn.\r\nSố sao: ", 
 NEW.so_sao, ".\r\nNội dung: ", NEW.noi_dung ), NOW(), 0
    FROM khach_hang kh_gui JOIN tai_khoan tk_gui ON tk_gui.id = kh_gui.id_tai_khoan
    JOIN san_pham sp ON sp.ma_san_pham = NEW.ma_san_pham
    JOIN khach_hang kh_nhan ON kh_nhan.ma_khach_hang = sp.ma_khach_hang
    JOIN tai_khoan tk_nhan ON tk_nhan.id = kh_nhan.id_tai_khoan
    WHERE kh_gui.ma_khach_hang = NEW.ma_khach_hang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `dat_hang`
--

CREATE TABLE `dat_hang` (
  `ma_dat_hang` int(11) NOT NULL,
  `ma_khach_hang` int(11) NOT NULL,
  `ngay_dat` datetime NOT NULL,
  `tong_tien` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dat_hang`
--

INSERT INTO `dat_hang` (`ma_dat_hang`, `ma_khach_hang`, `ngay_dat`, `tong_tien`) VALUES
(18, 6, '2022-01-09 22:22:00', 27399996),
(19, 6, '2022-01-09 22:46:03', 31000000),
(20, 6, '2022-01-10 13:56:41', 127272726),
(21, 6, '2022-01-10 14:03:13', 65593938),
(25, 22, '2022-01-10 15:16:38', 64636363),
(26, 22, '2022-01-10 15:20:05', 65636363),
(27, 22, '2022-01-10 15:58:33', 89136363),
(28, 22, '2022-01-10 15:59:03', 50500000),
(29, 1, '2022-01-10 19:41:57', 76127376),
(30, 1, '2022-01-10 19:42:19', 127272726),
(31, 1, '2022-01-10 19:43:08', 299997),
(32, 15, '2022-01-10 19:46:23', 89863687),
(33, 15, '2022-01-10 19:46:32', 1690908),
(34, 15, '2022-01-04 19:46:38', 563636),
(35, 15, '2022-01-10 19:46:44', 563636),
(36, 15, '2022-01-10 19:47:02', 563636),
(37, 15, '2022-01-10 19:47:09', 563636),
(38, 15, '2022-01-10 19:47:14', 563636),
(39, 15, '2022-01-10 19:47:19', 563636),
(40, 15, '2022-01-10 19:47:25', 563636),
(41, 15, '2022-01-10 19:48:23', 563688),
(42, 15, '2022-01-10 19:48:36', 163688),
(43, 15, '2022-01-10 19:48:42', 163688),
(44, 15, '2022-01-10 19:48:48', 163688),
(45, 15, '2022-01-10 19:49:43', 163688),
(46, 15, '2022-01-10 19:50:09', 500000),
(47, 1, '2022-01-10 19:54:08', 1563636),
(48, 1, '2022-01-10 19:54:19', 563636),
(49, 1, '2022-01-10 19:54:42', 1127272),
(50, 1, '2022-01-10 19:54:52', 563636),
(51, 1, '2022-01-10 19:55:08', 563636),
(52, 1, '2022-01-10 19:55:22', 31000000),
(53, 1, '2022-01-10 19:55:33', 563636),
(54, 1, '2022-01-10 19:55:46', 563636),
(55, 1, '2022-01-10 19:55:53', 563636),
(56, 1, '2022-01-10 19:56:03', 563636),
(57, 1, '2022-01-10 19:56:09', 563636),
(58, 1, '2022-01-10 19:56:15', 563636),
(59, 1, '2022-01-10 19:56:22', 563636),
(60, 1, '2022-01-10 19:56:28', 563636),
(61, 1, '2022-01-10 19:56:37', 563636),
(62, 1, '2022-01-10 19:56:43', 563636),
(63, 15, '2022-01-11 00:32:38', 50000000),
(64, 15, '2022-01-11 00:36:10', 26000000),
(65, 6, '2022-01-11 00:50:48', 360000),
(66, 15, '2022-01-11 00:54:44', 563636),
(67, 1, '2022-01-11 09:27:44', 1690908),
(68, 10, '2022-01-12 11:44:09', 9602000),
(69, 10, '2022-01-12 11:45:45', 9602000),
(70, 10, '2022-01-12 11:45:50', 9602000),
(71, 10, '2022-01-12 11:46:50', 7497000),
(72, 6, '2022-01-12 15:39:11', 61400000),
(73, 6, '2022-01-13 22:20:58', 73800000),
(74, 6, '2022-01-14 16:19:36', 405900000),
(75, 19, '2022-01-14 21:21:55', 157998000),
(76, 19, '2022-01-15 10:52:19', 750525252),
(77, 16, '2022-01-17 23:37:19', 814515252),
(78, 19, '2022-01-21 15:22:58', 2101008),
(79, 15, '2022-01-21 17:43:32', 0),
(80, 15, '2022-01-21 17:46:41', 0),
(81, 15, '2022-01-21 17:46:50', 0);

-- --------------------------------------------------------

--
-- Table structure for table `gio_hang`
--

CREATE TABLE `gio_hang` (
  `ma_khach_hang` int(11) NOT NULL,
  `ma_san_pham` varchar(36) NOT NULL,
  `so_luong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `gio_hang`
--

INSERT INTO `gio_hang` (`ma_khach_hang`, `ma_san_pham`, `so_luong`) VALUES
(1, 'bc49e268-6528-11ec-b702-7845f2f0d96e', 1),
(6, 'bc49e268-6528-11ec-b702-7845f2f0d96e', 10),
(6, 'SP001', 1),
(10, '35a99f29-64da-11ec-bb14-8378cfa7d63d', 1),
(10, '560c2201-6ef1-11ec-9aea-4c52b493c111', 63),
(10, 'a862b5ce-64d6-11ec-bb14-8378cfa7d63d', 1),
(16, '130ea67a-6528-11ec-b702-7845f2f0d96e', 1);

-- --------------------------------------------------------

--
-- Table structure for table `khach_hang`
--

CREATE TABLE `khach_hang` (
  `ma_khach_hang` int(11) NOT NULL,
  `ten` varchar(50) NOT NULL,
  `dia_chi` varchar(100) NOT NULL,
  `id_tai_khoan` int(11) NOT NULL,
  `tien_no` double NOT NULL,
  `gioi_thieu` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `khach_hang`
--

INSERT INTO `khach_hang` (`ma_khach_hang`, `ten`, `dia_chi`, `id_tai_khoan`, `tien_no`, `gioi_thieu`) VALUES
(1, 'Lâm Minh Thiện', '324 Minh Mạng, Đường 16/7,TX. Ninh Hòa', 4, 0, 'Đây là giới thiệu của Store Lâm Minh Thiện'),
(2, 'Hồ Hiểu Lực', 'Newyork', 5, 0, 'Đây là giới thiệu store Hồ Hiểu Lực'),
(6, 'Quang Thế Bảo', 'Cam Ranh', 10, 0, 'Đây là giới thiệu store của Quang Thế Bảo'),
(8, 'Trường Gia Minh', 'Ninh Diêm', 13, 0, 'Đây là giới thiệu store của Trường Gia Minh'),
(10, 'Vũ Trọng Nghĩa', 'Ninh Phước', 18, 0, 'Đây là giới thiệu store của '),
(11, 'fafafaf afa faf', 'Ninh Hà', 19, 0, 'This is present text'),
(15, 'Nguyễn Thị Test', 'Ninh Đông', 23, 0, 'This is present text'),
(16, 'Khách Hàng', 'Phan Rang', 24, 0, 'This is present text'),
(17, 'Lâm Minh Thiện', 'Ninh Hòa, Khánh Hòa, Việt Nam', 25, 0, 'This is present text'),
(18, 'Huỳnh Nam', 'Dục Mỹ', 26, 0, 'This is present text'),
(19, 'Đinh Khánh', 'Mỹ Lệ', 27, 0, 'This is present text'),
(20, 'Lâm Chấn Khang', 'Phú Quốc', 29, 0, 'This is present text'),
(21, 'Trương Tấn Vĩnh', 'Jamaica', 30, 0, 'This is present text'),
(22, 'John Dang', 'Hậu Gian', 31, 0, 'This is present text'),
(23, 'Tommy Vercities', 'Cần Thơ', 32, 0, 'This is present text'),
(25, 'Test Lần Cuối', '1', 34, 0, '1');

-- --------------------------------------------------------

--
-- Table structure for table `loai_san_pham`
--

CREATE TABLE `loai_san_pham` (
  `ma_loai_sp` int(11) NOT NULL,
  `ten_loai_sp` varchar(155) NOT NULL,
  `anh` varchar(255) NOT NULL,
  `ma_loai_cha` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `loai_san_pham`
--

INSERT INTO `loai_san_pham` (`ma_loai_sp`, `ten_loai_sp`, `anh`, `ma_loai_cha`) VALUES
(1, 'Điện thoại & phụ kiện', '1.png', NULL),
(2, 'Thời Trang Nam', '2.png', NULL),
(3, 'Thời Trang Nữ', '3.png', NULL),
(4, 'Máy tính & Laptop', '4.png', NULL),
(5, 'Phương tiện di chuyển', '5.png', NULL),
(10, 'Điện thoại', '6.png', 1),
(11, 'Máy tính bảng', '7.png', 1),
(12, 'Sạc dự phòng', '8.png', 1),
(13, 'Áo vest', '9.png', 2),
(14, 'Quần Jeans', '10.png', 2),
(15, 'Áo ba lỗ', '11.png', 2),
(16, 'Thắt lưng', '12.png', 2),
(17, 'Váy/Đầm', '13.png', 3),
(18, 'Đồ lót', '14.png', 3),
(19, 'Đồ bầu', '15.png', 3),
(20, 'Máy tính bàn', '16.png', 4),
(21, 'Laptop', '17.png', 4),
(22, 'Phụ kiện máy tính', '18.png', 4),
(23, 'Ô tô', '19.png', 5),
(24, 'Xe máy', '20.png', 5),
(25, 'Xe đạp', '21.png', 5),
(27, 'Hoa củ quả', '22.png', NULL),
(28, 'Sầu Riêng', '23.png', 27),
(29, 'Thơm', '24.png', 28),
(30, 'Dú Sữa', '25.png', 28),
(31, 'Mận', '26.png', 27),
(46, 'Youtube', '1642010407838_youtube_icon.png', 27);

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE `log` (
  `id_log` int(11) NOT NULL,
  `id_tai_khoan` int(11) NOT NULL,
  `ngay_tao` date NOT NULL,
  `hoat_dong` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `nhan_vien`
--

CREATE TABLE `nhan_vien` (
  `ma_nhan_vien` varchar(20) NOT NULL,
  `ten_nhan_vien` varchar(255) NOT NULL,
  `ma_chuc_vu` varchar(30) NOT NULL,
  `id_tai_khoan` int(11) DEFAULT NULL,
  `luong` double NOT NULL,
  `dia_chi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `nhan_vien`
--

INSERT INTO `nhan_vien` (`ma_nhan_vien`, `ten_nhan_vien`, `ma_chuc_vu`, `id_tai_khoan`, `luong`, `dia_chi`) VALUES
('1test', 'Hồ Hiểu Lực', 'giamdoc', 7, 100000000, 'fafafa'),
('2test', 'Keqing', 'giamdoc', 33, 100000000, 'a'),
('HETHONG', 'Hệ thống', 'giamdoc', -1, 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `phan_hoi_danh_gia_sp`
--

CREATE TABLE `phan_hoi_danh_gia_sp` (
  `ma_phan_hoi` int(11) NOT NULL,
  `ma_danh_gia` int(11) NOT NULL,
  `noi_dung` varchar(255) NOT NULL,
  `ma_khach_hang` int(11) NOT NULL,
  `ngay_tao` datetime NOT NULL,
  `ngay_sua` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Phản hồi đánh giá sản phẩm';

--
-- Dumping data for table `phan_hoi_danh_gia_sp`
--

INSERT INTO `phan_hoi_danh_gia_sp` (`ma_phan_hoi`, `ma_danh_gia`, `noi_dung`, `ma_khach_hang`, `ngay_tao`, `ngay_sua`) VALUES
(1, 16, '9999999999999999', 1, '2021-12-28 15:20:22', NULL),
(2, 16, '11111111111111111111111', 1, '2021-12-28 15:20:36', NULL),
(3, 25, 'ok', 6, '2021-12-31 17:12:24', NULL),
(4, 25, 'ium', 6, '2021-12-31 17:12:37', NULL),
(5, 25, 'fafaf', 6, '2021-12-31 17:12:44', NULL),
(6, 25, 'fafaf', 6, '2021-12-31 17:12:53', NULL),
(7, 25, 'da', 6, '2021-12-31 17:59:18', NULL),
(8, 25, 'dad', 6, '2021-12-31 18:21:21', NULL),
(9, 25, 'dad', 6, '2021-12-31 18:21:24', NULL),
(10, 25, 'df', 6, '2021-12-31 18:21:34', NULL),
(13, 25, 'ff', 6, '2021-12-31 20:12:32', NULL),
(14, 25, 'ffgaga', 6, '2021-12-31 20:12:35', NULL),
(16, 25, 'mới nè', 6, '2021-12-31 21:13:57', NULL),
(17, 27, 'sorry', 6, '2021-12-31 21:31:45', NULL),
(18, 27, 'xịn mà', 6, '2021-12-31 21:38:17', NULL),
(19, 25, 'mới mới\r\nmới\r\nmới', 6, '2021-12-31 21:40:30', NULL),
(20, 28, 'ukm', 21, '2021-12-31 21:59:35', NULL),
(21, 16, 'gaag', 21, '2021-12-31 22:03:10', NULL),
(24, 26, 'asd', 6, '2022-01-02 08:29:15', NULL),
(25, 16, 'f\r\ng\r\n\r\ng\r\nga\r\n\r\nga\r\n', 6, '2022-01-04 14:27:37', NULL),
(28, 22, 'xd', 19, '2022-01-04 14:57:22', NULL),
(29, 22, 'xd', 19, '2022-01-04 14:58:03', NULL),
(30, 23, 'xd', 19, '2022-01-04 15:01:48', NULL),
(31, 23, 'xdfggg\r\ngd\r\ng\r\ndg\r\nd\r\ng\r\ns\r\ng\r\ns\r\ng\r\nsg\r\ns\r\ng\r\ns\r\ng', 19, '2022-01-04 15:02:02', NULL),
(32, 20, 'ok', 19, '2022-01-04 15:02:23', NULL),
(34, 32, 'xgxgx', 19, '2022-01-04 15:05:18', NULL),
(35, 33, 'ok', 19, '2022-01-04 15:13:29', NULL),
(36, 22, 'xg', 6, '2022-01-04 22:38:15', NULL),
(40, 28, 'afgggg', 6, '2022-01-04 22:57:14', NULL),
(43, 8, 'gg', 6, '2022-01-04 22:57:39', NULL),
(45, 21, 'gggag', 6, '2022-01-04 22:59:33', NULL),
(46, 28, 'cảm ơn', 6, '2022-01-07 22:43:08', NULL),
(56, 42, 'nhái đâu mà nhái', 6, '2022-01-13 00:57:00', NULL),
(57, 33, '?????', 6, '2022-01-14 21:47:09', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `quyen`
--

CREATE TABLE `quyen` (
  `ma_quyen` varchar(30) NOT NULL,
  `ten_quyen` varchar(100) NOT NULL,
  `cap_do` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `quyen`
--

INSERT INTO `quyen` (`ma_quyen`, `ten_quyen`, `cap_do`) VALUES
('admin', 'admin', 999999),
('KH', 'Khách Hàng', 0);

-- --------------------------------------------------------

--
-- Table structure for table `san_pham`
--

CREATE TABLE `san_pham` (
  `ma_san_pham` varchar(36) NOT NULL,
  `ma_khach_hang` int(11) NOT NULL,
  `ten_san_pham` varchar(255) NOT NULL,
  `mo_ta` text NOT NULL,
  `gia` double NOT NULL,
  `status` int(11) NOT NULL,
  `ma_loai_san_pham` int(11) NOT NULL,
  `so_luong` int(11) NOT NULL,
  `ngay_dang` datetime NOT NULL,
  `so_luong_da_ban` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `san_pham`
--

INSERT INTO `san_pham` (`ma_san_pham`, `ma_khach_hang`, `ten_san_pham`, `mo_ta`, `gia`, `status`, `ma_loai_san_pham`, `so_luong`, `ngay_dang`, `so_luong_da_ban`) VALUES
('0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', 6, 'Bộ máy tính I5 Siêu nhanh', '<p>Cấu hình phù hợp mọi Game thủ Chơi,đồ họa mượt LoL, đột kích, truy kích, free fire, văn phòng cấu hình cao</p>', 4890000, 2, 20, 0, '2021-12-31 21:58:04', 0),
('10627767-74a2-11ec-ab12-0dd3fc775f75', 6, 'MacBook Pro 14\" 2021 M1 Pro Ram 32GB', '<p>Chip Apple M1 định nghĩa lại <strong>MacBook Pro 13 inch.</strong> Sở hữu CPU 8 lõi xử lý siêu tốc các luồng công việc phức tạp về hình ảnh, mã hóa, biên tập video cùng nhiều việc khác. GPU 8 lõi siêu mạnh xử lý gọn các tác vụ đồ họa khủng và chạy game siêu mượt. Neural Engine 16 lõi tiên tiến tăng cường sức mạnh công nghệ máy học tích hợp trong các ứng dụng yêu thích của bạn. Bộ nhớ thống nhất siêu nhanh cho mọi hoạt động mượt mà. Và thời lượng pin dài nhất từng có trên máy Mac, lên đến 20 giờ. Đây chính là chiếc máy tính xách tay chuyên nghiệp thịnh hành nhất của Apple. Hiệu năng cao hơn hẳn, pro hơn hẳn.</p><p>Tính năng nổi bật:</p><ul><li>Chip M1 do Apple thiết kế tạo ra một cú nhảy vọt về hiệu năng máy học, CPU và GPU&nbsp;</li><li>Làm được nhiều việc hơn với thời lượng pin lên đến 20 giờ, thời lượng pin lâu nhất trong các dòng máy tính Mac từ trước đến nay.</li><li>CPU 8 lõi cho hiệu năng nhanh hơn đến 2.8x, xử lý các luồng công việc nhanh hơn bao giờ hết.&nbsp;</li><li>GPU 8 lõi với tốc độ xử lý đồ họa nhanh gấp 5x cho các ứng dụng và game có đồ họa khủng.</li><li>Neural Engine 16 lõi cho công nghệ máy học hiện đại.</li><li>Bộ nhớ thống nhất 8GB giúp bạn làm việc gì cũng nhanh chóng và trôi chảy.</li><li>Ổ lưu trữ SSD siêu nhanh giúp mở các ứng dụng và tập tin chỉ trong tích tắc.&nbsp;</li><li>Hệ thống tản nhiệt chủ động mang lại hiệu năng tuyệt vời.</li><li>Màn hình Retina với độ sáng 500 nit cho màu sắc sống động và chi tiết ấn tượng.</li><li>Camera FaceTime HD với bộ xử lý tín hiệu hình ảnh tiên tiến cho các cuộc gọi video đẹp hình, rõ tiếng hơn&nbsp;</li><li>Ba micro phối hợp chuẩn studio thu giọng nói của bạn rõ hơn.</li><li>Wi-Fi 6 thế hệ mới giúp kết nối nhanh hơn.&nbsp;</li><li>Hai cổng Thunderbolt / USB 4 để sạc và kết nối phụ kiện.</li><li>Bàn phím Magic Keyboard có đèn nền và Touch ID giúp mở khóa và thanh toán an toàn hơn.</li><li>macOS Big Sur với thiết kế mới đầy táo bạo cùng nhiều cập nhật quan trọng cho các ứng dụng Safari, Messages và Maps.</li><li>Hiện có màu xám bạc và bạc.</li></ul>', 63990000, 0, 21, 10000, '2022-01-14 01:53:16', 0),
('130ea67a-6528-11ec-b702-7845f2f0d96e', 15, 'Áo vest nam DARNELL', '<p>Áo vest Darnell với thiết kế form vừa vặn, cấu trúc đường may tỉ mỉ, đẹp mắt, giúp bạn thể hiện phong cách thời trang nơi công sở. Bạn dễ dàng phối với áo sơ mi, áo polo,...item không thể thiếu trong tủ đồ của phái nam.&nbsp;</p><ul><li>Chất liệu: Nhung Dạ Màu kẻ đơn giản, sang trọng Kiểu dáng trẻ trung Form vừa vặn Công sở là quá chuẩn.</li><li>Màu sắc: Đen Trơn, Ghi Kẻ Ô, Kẻ Tím, Đen Nhung, Xanh Xước.</li><li>Kích thước : S – M - L - XL.</li><li>Thương hiệu: Darnell.</li><li>Xuất xứ: Việt Nam&nbsp;</li></ul><p><strong>ĐẶC TÍNH VƯỢT TRỘI:&nbsp;</strong></p><ul><li>Chất vải co giãn, đẹp, bền, không bai, không xù, không bám dính Đường may tinh tế, chỉn chu, khéo léo Màu sắc đa dạng, trẻ trung&nbsp;</li><li>Chất lượng sản phẩm tốt, giá cả hợp lý</li></ul>', 1189000, 0, 13, 10, '2021-12-13 04:19:07', 0),
('1e717293-652c-11ec-b702-7845f2f0d96e', 6, 'Áo phao lông vũ nam ', 'áo ba lỗ siêu nhẹ, giữ nhiệt tốt cản nước cản gió', 679000, 0, 15, 12, '2021-12-23 03:12:11', 0),
('35a99f29-64da-11ec-bb14-8378cfa7d63d', 15, 'Apple MacBook Air (2020)', '<p>Máy tính xách tay mỏng và nhẹ nhất của Apple, nay siêu mạnh mẽ với chip Apple M1. Xử lý công việc giúp bạn với CPU 8 lõi nhanh như chớp. Đưa các ứng dụng và game có đồ họa khủng lên một tầm cao mới với GPU 8 lõi. Đồng thời, tăng tốc các tác vụ máy học với Neural Engine 16 lõi. Tất cả gói gọn trong một thiết kế không quạt, giảm thiểu tiếng ồn, thời lượng pin dài nhất từ trước đến nay lên đến 18 giờ. (1) MacBook Air. Vẫn cực kỳ cơ động. Mà mạnh mẽ hơn nhiều.&nbsp;</p><p>Tính năng nổi bật:&nbsp;</p><ul><li>Chip M1 do Apple thiết kế tạo ra một cú nhảy vọt về hiệu năng máy học, CPU và GPU</li><li>Tăng thời gian sử dụng với thời lượng pin lên đến 18 giờ (1)&nbsp;</li><li>CPU 8 lõi cho tốc độ nhanh hơn đến 3.5x, xử lý công việc nhanh chóng hơn bao giờ hết (2)&nbsp;</li><li>GPU lên đến 8 lõi với tốc độ xử lý đồ họa nhanh hơn đến 5x cho các ứng dụng và game đồ họa khủng (2)&nbsp;</li><li>Neural Engine 16 lõi cho công nghệ máy học hiện đại&nbsp;</li><li>Bộ nhớ thống nhất 8GB giúp bạn làm việc gì cũng nhanh chóng và trôi chảy&nbsp;</li><li>Ổ lưu trữ SSD siêu nhanh giúp mở các ứng dụng và tập tin chỉ trong tích tắc&nbsp;</li><li>Thiết kế không quạt giảm tối đa tiếng ồn khi sử dụng&nbsp;</li><li>Màn hình Retina 13.3 inch với dải màu rộng P3 cho hình ảnh sống động và chi tiết ấn tượng (3)&nbsp;</li><li>Camera FaceTime HD với bộ xử lý tín hiệu hình ảnh tiên tiến cho các cuộc gọi video đẹp hình, rõ tiếng hơn&nbsp;</li><li>Bộ ba micro phối hợp tập trung thu giọng nói của bạn, không thu tạp âm môi trường&nbsp;</li><li>Wi-Fi 6 thế hệ mới giúp kết nối nhanh hơn&nbsp;</li><li>Hai cổng Thunderbolt / USB 4 để sạc và kết nối phụ kiện&nbsp;</li><li>Bàn phím Magic Keyboard có đèn nền và Touch ID giúp mở khóa và thanh toán an toàn hơn&nbsp;</li><li>macOS Big Sur với thiết kế mới đầy táo bạo cùng nhiều cập nhật quan trọng cho các ứng dụng Safari, Messages và Maps&nbsp;</li><li>Hiện có màu vàng kim, xám bạc và bạc Pháp lý Hiện có sẵn các lựa chọn để nâng cấp. (1) Thời lượng pin khác nhau tùy theo cách sử dụng và cấu hình. Truy cập apple.com/batteries để biết thêm thông tin. (2) So với thế hệ máy trước. (3) Kích thước màn hình tính theo đường chéo.</li></ul>', 30700000, 0, 21, 20, '2021-12-24 00:00:00', 0),
('426249a8-7515-11ec-bf3a-fcf5d54cfc0d', 6, 'Honda civic', '<p>Xe ô tô Honda civic cực mạnh:&nbsp;</p><ul><li>Mạnh 1.&nbsp;</li><li>Mạnh 2.&nbsp;</li><li>Mạnh 3.&nbsp;</li><li>Mạnh 4.</li></ul>', 750000000, 0, 23, 20, '2022-01-14 15:37:51', 0),
('560c2201-6ef1-11ec-9aea-4c52b493c111', 6, 'Chuột không dây Bluetooth SIDOTECH', 'CHUỘT KHÔNG DÂY BLUETOOTH TỰ SẠC PIN SIDOTECH M1P\n\nƯU ĐIỂM NỔI BẬT CHUỘT KHÔNG DÂY BLUETOOTH SẠC PIN SIDOTECH M1P\n• Không cần sử dụng pin tiểu AAA\n• Sạc trực tiếp thông qua cổng USB\n• Một lần sạc đầy khoảng 2h và sử dụng trong 1 tuần liên tục\n• Có thể vừa sạc vừa dùng như một chiếc chuột có dây thông thường\n• Kết nối xa 10 mét\n• Độ nhạy DPI 1000/1200/1600 điều chỉnh thông qua nút\n• 3 chế độ kết nối, dùng cổng USB, Bluetooth, cổng Sạc\n• Thiết kế hiện đại thoải mái sang trọng như chuột của Apple\n• Nhiều màu để lựa chọn', 119000, 2, 22, 63, '2022-01-06 20:05:35', 0),
('5c3f89c8-751c-11ec-bf3a-fcf5d54cfc0d', 6, 'test 123', 'gsgsg', 525252, 0, 11, 5252, '2022-01-14 16:28:40', 0),
('5dfb7106-651f-11ec-b702-7845f2f0d96e', 16, 'Xe Đạp Trẻ Em LanQ FD', '<ul><li>Tên sản phẩm : Xe Đạp Trẻ Em LanQ FD Có Giảm Xóc&nbsp;</li><li>Nhập khẩu và phân phối : Công Ty TNHH Toykid Việt Nam&nbsp;</li><li>Địa chỉ : 414 Bạch Đằng, phường Chương Dương, quận Hoàn Kiếm, Hà Nội&nbsp;</li><li>Xuất Xứ : Trung Quốc&nbsp;</li><li>Lưu ý : Không dùng cho trẻ em dưới 12 tháng tuổi</li></ul>', 1990000, 0, 25, 15, '2021-12-25 00:00:00', 0),
('699515ad-737a-11ec-9e8d-e836550a30d1', 6, 'Card màn hình Asus ROG-STRIX-RTX3090-24G-GAMING (24GB GDD6X, 384-bit, HDMI +DP, 3x8-pin)', '<p>Thông số sản phẩm</p><ul><li>Dung lượng bộ nhớ: 24Gb GDDR6X - 10496 CUDA Cores&nbsp;</li><li>Core Clock: 1725 MHz (Boost Clock) - Kết nối: DisplayPort 1.4a, HDMI 2.1&nbsp;</li><li>Nguồn yêu cầu: 850W Card màn hình Asus ROG Strix RTX 3090 24Gb Gaming là một trong những sản phẩm cao cấp nhất của Asus phục vụ cho nhu cầu gaming ở độ phân giải 4K 60Fps. Đây là card đồ họa sử dụng kiến trúc Ampare hoàn toàn mới cùng nhân RT thế hệ 2, nhân Tensor thế hệ 3, Nvidia RTX IO, VRAM GDDR6X và sản xuất trên tiến trình 8nm được Samsung làm riêng.</li></ul>', 69999000, 0, 22, 50, '2022-01-12 14:36:53', 0),
('983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 6, 'Áo lót mút mỏng chất ren BOM SISTER BR009', '<p>Đặc điểm sản phẩm:&nbsp;</p><p>Chất liệu:&nbsp;</p><ul><li>Vải ren mềm mại, không gây mẫn ngứa ( Nhà cung cấp có quyền đổi chi tiết ren nếu loại ren trên hình hết.</li><li>Không thay đổi cấu trúc áo và màu).</li><li>Thiết kế: Gia công bởi người Việt, đường kim mũi chỉ chắc chắn.&nbsp;</li><li>Ứng dụng: Thích hợp mặc vào \"ngày đèn đỏ\" cực dễ chịu.</li><li>Được bảo hành đường kim - mũi chỉ trọn đời.</li></ul>', 128000, 0, 18, 100, '2022-01-04 23:01:36', 99),
('a7e477d7-7927-11ec-bd3a-40b7d60cb467', 6, 'test new new', '<p><strong>In đậm nè.</strong></p><p><i>Table nè:</i></p><figure class=\"table\"><table><tbody><tr><td>1</td><td>2</td><td>3</td></tr><tr><td>4</td><td>5</td><td>6</td></tr><tr><td>7</td><td>8</td><td>9</td></tr></tbody></table></figure><p>Link nè: <a href=\"https://ckeditor.com/docs/ckeditor5/latest/builds/guides/integration/saving-data.html\">https://ckeditor.com/docs/ckeditor5/latest/builds/guides/integration/saving-data.html</a></p><h2>Heading 1</h2><ol><li>Một</li><li>Hai</li></ol><ul><li>a</li><li>b</li></ul>', 7000, 0, 16, 700, '2022-01-19 19:59:36', 0),
('a862b5ce-64d6-11ec-bb14-8378cfa7d63d', 15, 'Hub cổng chuyển đổi', '<p>Hub chuyển đa năng Baseus CAHUB-BG0G 16 trong 1 được trang bị gần như tất cả các cổng chuyển đổi cơ bản và hỗ trợ hầu hết các thiết bị số hiện nay từ Macbook, smartphone/ Tablet/ Laptop Android/Windows... HUB chuyển đổi có ngõ vào là cổng USB Type C và ngỏ ra là hầu hết các chuẩn giao tiếp phổ thông hiện nay như : USB 2.0/ USB 3.0 / HDMI/ VGA/ USB 3.0/ Card Reader/ RJ-45/AUX 3.5mm...&nbsp;</p><p>Với Hub chuyển đa năng Baseus Working Station 16 in 1 Multifunctional bạn có thể dễ dàng xuất hình ảnh, âm thanh độ nét cao qua cổng HDMI/ VGA/ AUX 3.5mm, đồng bộ và chia sẽ dữ liệu/ hình ảnh từ thẻ nhớ, các thiết bị lưu trữ. Ngoài ra bộ HUB còn được trang bị cổng mạng LAN có dây (RJ-45) cho phép bạn có thể kết nối mạng ở những nơi không có Wifi . Bạn sẽ không còn phải gặp các phiền phức và bất tiện do thiếu cổng kết nối.&nbsp;</p><p>Sản phẩm được trang bị cổng HDMI và VGA hỗ trợ 4K ở ngõ ra cho phép bạn xuất hình ảnh từ Laptop ra 2 màn hình lớn khác nhau ở cả 2 chế độ song song hoặc mở rộng (chia/ghép màn hình).&nbsp;</p><p>Trang bị chip xử lý thông minh, đảm bảo tốc độ truyền tải nhanh và ổn định, tương thích với hầu hết các thiết bị Type C . Chỉ việc cắm và sử dụng (Plug &amp; Play) không cần phải thực hiện các thao tác cài đặt phức tạp.&nbsp;</p><p>Thiết kế dạng dock tiện dụng, bề mặt bằng nhôm tinh chế với tông màu xám sang trong và đẳng cấp tạo sự đồng bộ với thiết kế của Macbook. <strong>Thông số kỹ thuật sản phẩm:&nbsp;</strong></p><ul><li>Chất liệu: Hợp kim nhôm.</li><li>Kích thước: 80mm * 86mm * 162mm.</li><li>Trọng lượng: 400g.</li><li>Giao diện: Loại-CPD (đầu vào) + Loại-C (đầu ra) + USB2.0 * 2 + VGA * 1 + 4K HD * 1 +.</li><li>Bộ nguồn DC * 1 + RJ45 * 1 + SD + TF * 1 + USB 3.0 * 3+ - Jack âm thanh loại: C + 3,5mm.</li><li>Đầu vào PD loại C: 100W - 4K HD: Hỗ trợ màn hình 4K @ 30HZ HD.</li><li>VGA: Hỗ trợ màn hình HD 1080P @ 60HZ - RJ45: Tốc độ mạng Gigabit, kết nối mạng có dây, kết nối mạng cho máy tính, máy tính bảng và điện thoại di động - USB3.0 * 3: đọc và ghi dữ liệu tốc độ cao của đĩa flash, tương thích hướng xuống với USB2.0 và USB1.1.</li><li>USB2.0 * 2: đọc và ghi dữ liệu tốc độ cao của đĩa flash, tương thích hướng xuống với USB1.1 - SD / TF: Hỗ trợ đọc dữ liệu tối đa 2T (không hỗ trợ đọc đồng thời, ưu tiên thẻ được đọc trước tiên).</li><li>Giắc âm thanh 3,5 mm: Hỗ trợ đầu vào và đầu ra - Loại-C * 2: Hỗ trợ đọc từ đĩa flash Loại-C (Không hỗ trợ đầu ra âm thanh Loại-c).</li><li>Giao diện: Loại-CPD (đầu vào) + Loại-C (đầu ra) + USB2.0 * 2 + VGA * 1 + 4K HD * 1 + Bộ nguồn DC * 1 + RJ45 * 1 + SD + TF * 1 + USB 3.0 * 3+.</li><li>16 in 1 Model (CAHUB-BG0G): USB 3.0*3 + USB 2.0*2 + HDMI +VGA +TF/SD Card Reader + RJ45 +PD + PC +3.5mm Jack + DC Port + PD Cable + UK/EU Plug + CN DC Cord (Suit).&nbsp;</li></ul><p><strong>Lưu ý:</strong></p><ul><li>Do giới hạn về hệ thống của MACOS, MacBook không hỗ trợ tách thành 3 màn hình khác nhau.&nbsp;</li><li>Khi sử dụng một cổng HDMI, chất lượng hình ảnh là 4K / 60Hz, khi sử dụng hai cổng HDMI, chất lượng hình ảnh là 4K / 30Hz, khi sử dụng 3 cổng HDMI, chất lượng hình ảnh là 1080p.</li></ul>', 2105000, 0, 22, 500, '2021-12-24 00:00:00', 0),
('aa76ecd2-6b6b-11ec-8506-571a7e295222', 6, 'Xe đạp điện DK Bike 133M ', '<p>Thông số kỹ thuật&nbsp;</p><ul><li>Kích cỡ/Sizes: 1593mm x 635mm x 1015mm.</li><li>Màu sắc/Colors: Đỏ, Ghi, Xanh cửu long, Cam.&nbsp;</li><li>Ắc quy/Battery: Chì axit kín khí 48V-12A.</li><li>Công suất động cơ/Motor: 600W Vận tốc tối đa/Max speed 35 – 40 km/h.</li><li>Quãng đường di chuyển/Range: 50 – 60km/lần sạc.</li><li>Bánh xe trước/Front Wheel: Lốp 16″x3.0.&nbsp;</li><li>Bánh xe sau/Rear Tires: Lốp 16″x3.0.</li><li>Thắng trước/Front Brakes: Thắng tang trống.</li><li>Thắng sau/Rear Brakes: Thắng tang trống.</li><li>Sạc điện/Charge: Ắc quy tự ngắt khi sạc đầy.</li></ul>', 25000000, 0, 25, 20, '2022-01-02 08:31:10', 0),
('bc49e268-6528-11ec-b702-7845f2f0d96e', 17, 'Quần lót ren lọt khe form T No Limit Miss K', '<p>✨ Chất liệu ren nhập khẩu, thoáng mát và mềm mại&nbsp;</p><p>✨ Form T lọt khe gợi cảm&nbsp;</p><p>✨ Phù hợp với nhiều trang phục</p>', 30000, 0, 18, 10, '2021-12-25 00:00:00', 0),
('d23e005c-6ef6-11ec-9aea-4c52b493c111', 6, 'Đế Tản Nhiệt Máy Tính Xách Tay INPHIC R2 Với Hai Quạt Siêu Mát Dùng Cho Máy 14 đến 17 inch', '<p><strong>Thương hiệu:</strong> INPHIC Kích thước gói: 40,6 x 31,7 x 3,7 cm.<br><strong>Trọng lượng:</strong> 760 ± 20 g.<br><strong>Model:</strong> R2.<br>Kích thước phù hợp cho máy tính: 14 đến 17 inch.<br>Tấm làm mát máy tính xách tay R2 được phát triển bởi đội ngũ thiết kế sản phẩm xuất sắc của INPHIC. Hai quạt hoạt động mạnh mẽ nhưng cực kỳ yên tĩnh để bạn cảm thấy thoải mái nhất khi làm việc hoặc giải trí.&nbsp;</p><ul><li>Kích thước sản phẩm: 390 x 280 x 28 mm.&nbsp;</li><li>Số lượng và kích thước quạt: 2 quạt lớn.&nbsp;</li><li>Đường kính quạt: 125 mm.&nbsp;</li><li>Tốc độ quạt: 1500 vòng / phút.</li><li>Nguồn điện: USB 5 V DC.&nbsp;</li><li>Dòng điện làm việc: 1 A.&nbsp;</li><li>Công suất nguồn: 5 W.&nbsp;</li></ul><p><strong>Đặc trưng:&nbsp;</strong></p><ul><li>Chân đế điều chỉnh 4 mức.&nbsp;</li><li>Phù hợp với các loại laptop từ 14 - 17 inch.&nbsp;</li><li>Mặt lưới kim loại.</li></ul>', 150000, 0, 22, 52, '2022-01-06 20:44:50', 0),
('da3b10b1-7945-11ec-bd3a-40b7d60cb467', 6, 'test new new new', '<figure class=\"table\"><table><tbody><tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td><td>9</td><td>0</td></tr><tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td><td>9</td><td>0</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>ada</td><td>dad</td><td>dada</td><td>5454545454545454412</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>da</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>g</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>ag</td><td>gag</td><td>&nbsp;</td><td>dad</td><td>aga</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>ag</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>faf</td><td>&nbsp;</td><td>a</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>ag</td><td>&nbsp;</td><td>ad</td><td>&nbsp;</td><td>&nbsp;</td><td>fa</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>fa</td><td>&nbsp;</td><td>g</td><td>&nbsp;</td><td>ag</td></tr><tr><td>&nbsp;</td><td>g</td><td>&nbsp;</td><td>a</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>aga</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table></figure>', 5555555, 0, 10, 5555, '2022-01-19 23:35:46', 0),
('e4f55954-652b-11ec-b702-7845f2f0d96e', 6, 'Quần jean nam LB, vải jean denim co giãn, màu xám đen trơn, phom slim fit DNBB5226', '<p>Thông tin sản phẩm quần jeans dài nam LBjean 5226&nbsp;</p><ul><li>Thương hiệu: LB.&nbsp;</li><li>Xuất xứ: được thiết kế và gia công tại xưỡng sản xuất LBjean,tp HCM – Việt Nam.</li><li>Chất liệu : 100% jean Cotton.</li><li>Chất jean cotton loại 1 dày dặn lên dáng chuẩn lắm các bạn nhé.</li><li>Thiết kế cạp cao cá tính mặc vào tôn dáng lắm luôn nà.</li><li>Dễ dàng phối đồ với áo polo thích hợp 4 mùa, mặc đi chơi dạo phố đều rất đẹp nà.</li><li>Thiết kế theo xu hướng thời trang mới nhất.&nbsp;</li><li>Phong cách hàn quốc phù hợp mọi lứa tuổi.</li><li>Quần jeans được làm màu bằng công nghệ mới giúp vãi mềm mịn và tươi màu, không bị phai bạc màu khi sữ dụng nên bạn yên tâm hàng luôn bền màu như mới nhé.</li></ul>', 299000, 0, 14, 2626, '2021-12-25 00:00:00', 0),
('SP001', 15, 'Xe máy Honda Vision 2021 - Phiên bản cao cấp Smartkey', '<p>Đặc điểm Honda Vision 2021:</p><ul><li>Thiết kế phía trước hiện đại và năng động.</li><li>Thiết kế phía trước kế thừa sự năng động vốn có của Vision nhưng ấn tượng hơn với diện mạo hoàn toàn mới.&nbsp;</li><li>Logo 3D nổi trên thân xe với đường nét liền mạch, rõ ràng mang đến hình ảnh trẻ trung và năng động.</li></ul>', 36900000, 0, 24, 20, '2022-01-13 00:00:00', 0),
('test', 15, 'Sạc Dự Phòng TOPK I1007P 20W PD QC3.0 10000mAh Sạc Nhanh Hiển Thị Màn Hình Điện Tử', '<p><strong>Chi tiết sản phẩm:&nbsp;</strong></p><p>☀ Tên sản phẩm: Sạc dự phòng TOPK I1006P màn hình kỹ thuật số 10000mAh&nbsp;</p><p>☀ Màu sắc: Xanh Navy/Bạc&nbsp;</p><p>☀ Kích thước: 135 * 66.2 * 14.7mm&nbsp;</p><p>☀ Cổng đầu ra: Type-C (PD3.0), USB&nbsp;</p><p>☀ Cổng đầu vào: Type-C, Micro&nbsp;</p><p>☀ Dung lượng định mức: 6500mAh&nbsp;</p><p>☀ Dung lượng: 10000mAh&nbsp;</p><p>☀ Tương thích cho tất cả các thiết bị Apple, Type C và Android.&nbsp;</p><p><strong>Đặc trưng:&nbsp;</strong></p><p>☀ Sạc nhanh 20W PD3.0 + QC 3.0: Cổng USB-C phân phối nhanh hai chiều, hỗ trợ cung cấp năng lượng lên đến 20W ở đầu vào và đầu ra. Đầu ra QC 3.0 hỗ trợ 5V/3A, 9V/2A, 12V/1.66A. Bạn có thể sạc 2 điện thoại cùng một lúc, pin sẽ sạc nhanh cho các thiết bị của bạn khi sử dụng tất cả 2 đầu ra cùng nhau.&nbsp;</p><p>☀ Kích thước nhỏ dung lượng lớn: Sạc dự phòng DP 10000mAh 20W có thể sạc cho iPhone 8 3.5 lần, iPhone 12 XS 2.7 lần, Xiaomi Mi 9 2.2 lần. Thích hợp cho công việc, du lịch, đi chơi, bạn không phải lo lắng về việc điện thoại bị hết pin.&nbsp;</p><p>☀ Vỏ bằng hợp kim nhôm với màn hình LED: Thiết kế chống trượt và chống vân tay với màn hình LED để hiển thị dung lượng pin còn lại. Màn hình sẽ hiển thị \"PD\" khi sạc nhanh thiết bị của bạn. Khi lượng pin của iPhone bạn được sạc lên tới 80%, pin sạc dự phòng sẽ sạc iPhone của bạn ở tốc độ thường nhờ hệ thống an toàn IOS.&nbsp;</p><p>☀ Bảo vệ an toàn: Các biện pháp bảo vệ tích hợp giúp bảo vệ các thiết bị của bạn khỏi cường độ điện quá mức, quá nhiệt và quá tải.</p>', 259000, 0, 12, 100000, '2021-12-21 00:00:00', 25);

--
-- Triggers `san_pham`
--
DELIMITER $$
CREATE TRIGGER `thong_bao_duyet_san_pham` AFTER UPDATE ON `san_pham` FOR EACH ROW BEGIN 
	IF (NEW.status = 2) THEN
    	INSERT INTO thong_bao (id_nguoi_nhan, id_nguoi_gui, noi_dung, ngay_tao, status)
        SELECT tk.id, -1, CONCAT("Sản phẩm <b>", NEW.ten_san_pham, "</b> của bạn đã được duyệt."), NOW(), 0
        FROM khach_hang kh
        JOIN tai_khoan tk ON tk.id = kh.id_tai_khoan
        WHERE kh.ma_khach_hang = NEW.ma_khach_hang;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `san_pham_yeu_thich`
--

CREATE TABLE `san_pham_yeu_thich` (
  `ma_khach_hang` int(11) NOT NULL,
  `ma_san_pham` varchar(36) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `san_pham_yeu_thich`
--

INSERT INTO `san_pham_yeu_thich` (`ma_khach_hang`, `ma_san_pham`) VALUES
(6, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f'),
(6, '10627767-74a2-11ec-ab12-0dd3fc775f75'),
(6, '426249a8-7515-11ec-bf3a-fcf5d54cfc0d'),
(6, '5c3f89c8-751c-11ec-bf3a-fcf5d54cfc0d'),
(6, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb'),
(6, 'SP001'),
(16, 'a862b5ce-64d6-11ec-bb14-8378cfa7d63d'),
(16, 'da3b10b1-7945-11ec-bd3a-40b7d60cb467');

-- --------------------------------------------------------

--
-- Table structure for table `tai_khoan`
--

CREATE TABLE `tai_khoan` (
  `id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `ngay_tao` datetime NOT NULL,
  `so_dien_thoai` varchar(20) NOT NULL,
  `ngay_sinh` date NOT NULL,
  `gioi_tinh` tinyint(1) NOT NULL,
  `so_lan_canh_cao` int(11) NOT NULL DEFAULT 0,
  `status` tinyint(1) NOT NULL DEFAULT 0,
  `ma_quyen` varchar(30) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tai_khoan`
--

INSERT INTO `tai_khoan` (`id`, `username`, `password`, `email`, `ngay_tao`, `so_dien_thoai`, `ngay_sinh`, `gioi_tinh`, `so_lan_canh_cao`, `status`, `ma_quyen`, `avatar`) VALUES
(-1, 'hethong', '$2a$12$b6JZOu.AS8RVywSYjKO.POS.0cpH6B9OUvDj5YOtJXkdw0ApVH3my', '', '2022-01-14 11:20:42', '', '2022-01-14', 1, 999, 0, 'admin', NULL),
(4, 'thienlam782', '$2a$12$qIUcCjPDF.pcsWLorMeJy.aSd3NQOAEHY8iEfnRkBNKY2BNjX75mm', 'thien@gmail.com', '2021-12-21 00:00:00', '0828504336', '2021-12-09', 0, 0, 0, 'KH', '1.png'),
(5, 'provjpzz123', '$2a$12$qIUcCjPDF.pcsWLorMeJy.aSd3NQOAEHY8iEfnRkBNKY2BNjX75mm', 'fafa.gagag@gmail.com', '2021-12-22 00:00:00', '62626262', '2021-12-21', 0, 0, 0, 'KH', '2.png'),
(7, 'admin', '$2a$12$b6JZOu.AS8RVywSYjKO.POS.0cpH6B9OUvDj5YOtJXkdw0ApVH3my', 'kingofika123@gmail.com', '2021-12-23 00:00:00', '5226262626', '2021-12-15', 0, 0, 0, 'admin', '3.png'),
(10, 'trantest', '$2a$12$qIUcCjPDF.pcsWLorMeJy.aSd3NQOAEHY8iEfnRkBNKY2BNjX75mm', 'x@x.x', '2021-12-23 00:00:00', '0987654321', '2021-12-23', 1, 0, 0, 'KH', '4.png'),
(13, 'hohieuluc', '$2a$12$qIUcCjPDF.pcsWLorMeJy.aSd3NQOAEHY8iEfnRkBNKY2BNjX75mm', '525ffa@ga.q', '2021-12-23 00:00:00', '636372726', '2021-12-08', 0, 0, 0, 'KH', '6.png'),
(18, 'test1234', '$2a$12$qIUcCjPDF.pcsWLorMeJy.aSd3NQOAEHY8iEfnRkBNKY2BNjX75mm', '52ddad5ffa@ga.q', '2021-12-24 00:00:00', '5252626262', '2021-12-15', 1, 0, 1, 'KH', '9.png'),
(19, 'testtest123', '$2a$12$qIUcCjPDF.pcsWLorMeJy.aSd3NQOAEHY8iEfnRkBNKY2BNjX75mm', '5hfhfha@ghaaf.fafaf', '2021-12-24 00:00:00', '526377262', '2021-12-08', 1, 0, 1, 'KH', '8.png'),
(23, 'nguyenthitest', '$2a$12$qIUcCjPDF.pcsWLorMeJy.aSd3NQOAEHY8iEfnRkBNKY2BNjX75mm', 'gakgag@gahgag.agag', '2021-12-24 00:00:00', '526372626', '2021-12-01', 0, 0, 1, 'KH', '10.png'),
(24, 'khtest123', '$2a$12$qIUcCjPDF.pcsWLorMeJy.aSd3NQOAEHY8iEfnRkBNKY2BNjX75mm', 'khtest@ktktkkttk.ttqjtkqkt', '2021-12-25 00:00:00', '74736373737', '2021-12-16', 0, 0, 1, 'KH', '11.png'),
(25, 'minhthienmap', '$2a$12$qIUcCjPDF.pcsWLorMeJy.aSd3NQOAEHY8iEfnRkBNKY2BNjX75mm', 'lamminhthien02012000@gmail.com', '2021-12-25 00:00:00', '08285043336', '2006-02-14', 0, 0, 1, 'KH', 'kequing.png'),
(26, 'fafafafafaffa', '$2a$12$qIUcCjPDF.pcsWLorMeJy.aSd3NQOAEHY8iEfnRkBNKY2BNjX75mm', 'gagaggg@gg.a.gag', '2021-12-25 00:00:00', '525252525', '2021-12-10', 0, 0, 1, 'KH', '12.png'),
(27, 'johntest', '$2a$12$qIUcCjPDF.pcsWLorMeJy.aSd3NQOAEHY8iEfnRkBNKY2BNjX75mm', 'johntest2252@s.g.s.g.s', '2021-12-26 00:00:00', '5555252525', '2021-12-15', 0, 0, 1, 'KH', '13.png'),
(29, 'thienlam1100', '$2a$12$qIUcCjPDF.pcsWLorMeJy.aSd3NQOAEHY8iEfnRkBNKY2BNjX75mm', 'thien1100@gmail.com', '2021-12-27 11:36:24', '08285043336', '2021-11-30', 0, 0, 1, 'KH', '14.png'),
(30, 'testlan6', '$2a$12$qIUcCjPDF.pcsWLorMeJy.aSd3NQOAEHY8iEfnRkBNKY2BNjX75mm', 'fag@gga.hhq', '2021-12-31 21:59:08', '112254646', '2021-12-15', 0, 0, 1, 'KH', '15.png'),
(31, 'testlan3', '$2a$12$qIUcCjPDF.pcsWLorMeJy.aSd3NQOAEHY8iEfnRkBNKY2BNjX75mm', 'taga@ga.gaga', '2022-01-01 22:12:00', '5525255555', '2022-01-06', 0, 0, 1, 'KH', '16.png'),
(32, 'taikhoanmoi', '$2a$12$qIUcCjPDF.pcsWLorMeJy.aSd3NQOAEHY8iEfnRkBNKY2BNjX75mm', 'hsg@gg.hhwttq', '2022-01-02 09:41:07', '25252525666', '2022-01-04', 0, 0, 1, 'KH', '17.png'),
(33, 'provjpzz', '$2a$12$b6JZOu.AS8RVywSYjKO.POS.0cpH6B9OUvDj5YOtJXkdw0ApVH3my', '525ffa@ga.q526gsgs', '2022-01-13 22:26:22', '5373727272', '2022-01-13', 0, 0, 1, 'admin', '1642087582403_FNA6INk.png'),
(34, 'testlancuoi1', '$2a$12$yxacjty7pQyIwUsQivfks.IZDEkyFtOtwAPqlc7ZjJ7oItbIyqqCq', 'testlancuoi@tlt.tltlttlt.tlttl', '2022-01-21 18:10:43', '0987654321', '2022-01-10', 0, 0, 1, 'KH', '1642763443729_giorgio-trovato-K62u25Jk6vo-unsplash.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `thong_bao`
--

CREATE TABLE `thong_bao` (
  `ma_tb` int(11) NOT NULL,
  `id_nguoi_nhan` int(11) NOT NULL,
  `id_nguoi_gui` int(11) NOT NULL,
  `noi_dung` text NOT NULL,
  `ngay_tao` datetime NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `thong_bao`
--

INSERT INTO `thong_bao` (`ma_tb`, `id_nguoi_nhan`, `id_nguoi_gui`, `noi_dung`, `ngay_tao`, `status`) VALUES
(1, 4, 24, 'Vì \"nội dung\" nên bạn bị cảnh cáo và còn 2 lần cảnh cáo nữa sẽ khóa tài khoản', '2022-01-11 14:27:47', 1),
(4, 4, 7, 'Vì \"nội dung\" nên bạn bị nhắc nhở mà không tăng số lần cảnh cáo. Nếu tái phạm sẽ bị cảnh cáo', '2022-01-12 18:32:11', 1),
(5, 4, 5, 'abc', '2022-01-11 10:19:16', 1),
(6, 4, 10, '412412', '2022-01-11 10:19:34', 1),
(9, 7, 32, 'aaaaaaaaaaaaaaa\r\naf\r\naf\r\naa\r\naa\r\na', '2022-01-11 15:45:13', 1),
(19, 24, 33, 'Bạn bị tố cáo vì lừa đảo. Nếu tiếp tục vi phạm sẽ bị cảnh cáo, quá 2 lần cảnh cáo sẽ khóa tài khoản', '2022-01-14 18:57:07', 1),
(29, 27, 10, 'Sản phẩm <b>Honda civic</b> mà bạn đặt ngày 15-01-2022 lúc 10:52:19 đang được vận chuyển.', '2022-01-17 21:56:53', 1),
(30, 27, 10, 'Sản phẩm <b>Honda civic</b> mà bạn đặt ngày 15-01-2022 lúc 10:52:19 đã bị ngừng vận chuyển.\r\nLý do: Không có lý do', '2022-01-17 21:56:53', 1),
(31, 27, 10, 'Sản phẩm <b>Honda civic</b> mà bạn đặt ngày 15-01-2022 lúc 10:52:19 đang được vận chuyển.', '2022-01-17 21:58:15', 1),
(32, 27, 10, 'Sản phẩm <b>Honda civic</b> mà bạn đặt ngày 15-01-2022 lúc 10:52:19 đã bị ngừng vận chuyển.\r\nLý do: đột xuất', '2022-01-17 21:58:38', 1),
(33, 27, 10, 'Sản phẩm <b>Honda civic</b> mà bạn đặt ngày 15-01-2022 lúc 10:52:19 đang được vận chuyển.', '2022-01-17 21:58:49', 1),
(34, 27, 10, 'Sản phẩm <b>Honda civic</b> mà bạn đặt ngày 15-01-2022 lúc 10:52:19 đã bị ngừng vận chuyển.\r\nLý do: a\r\nb\r\nc\r\nd\r\ne', '2022-01-17 21:58:54', 1),
(59, 24, -1, 'Sản phẩm <b>Honda civic</b> do bạn đặt ngày 17-01-2022 lúc 23:37:19 đang được vận chuyển.', '2022-01-17 23:37:43', 1),
(60, 24, -1, 'Sản phẩm <b>Honda civic</b> mà bạn đặt ngày 17-01-2022 lúc 23:37:19 đã bị ngừng vận chuyển.\r\nLý do:\r\nKhông có lý do', '2022-01-17 23:38:01', 1),
(61, 24, -1, 'Sản phẩm <b>Honda civic</b> mà bạn đặt ngày 17-01-2022 lúc 23:37:19 đã bị hủy.\r\nLý do:\r\nKhông có lý do', '2022-01-17 23:38:23', 1),
(63, 24, -1, 'Sản phẩm <b>test 123</b> do bạn đặt ngày 17-01-2022 lúc 23:37:19 đang được vận chuyển.', '2022-01-17 23:40:17', 1),
(65, 23, -1, 'Sản phẩm <b>Đế Tản Nhiệt Máy Tính Xách Tay INPHIC R2 Với Hai Quạt Siêu Mát Dùng Cho Máy 14 đến 17 inch</b> do bạn đặt ngày 10-01-2022 lúc 19:48:23 đang được vận chuyển.', '2022-01-17 23:51:55', 1),
(66, 23, -1, 'Sản phẩm <b>Đế Tản Nhiệt Máy Tính Xách Tay INPHIC R2 Với Hai Quạt Siêu Mát Dùng Cho Máy 14 đến 17 inch</b> do bạn đặt ngày 10-01-2022 lúc 19:48:23 đã bị ngừng vận chuyển.\r\nLý do:\r\na\r\nb\r\nc', '2022-01-17 23:52:46', 0),
(67, 23, -1, 'Sản phẩm <b>Đế Tản Nhiệt Máy Tính Xách Tay INPHIC R2 Với Hai Quạt Siêu Mát Dùng Cho Máy 14 đến 17 inch</b> do bạn đặt ngày 10-01-2022 lúc 19:48:23 đã bị hủy.\r\nLý do:\r\nkhông bán nữa', '2022-01-17 23:53:24', 0),
(68, 10, -1, 'Sản phẩm <b>Bộ máy tính I5 Siêu nhanh</b> do <u><i>Nguyễn Thị Test</i></u> đặt ngày 10-01-2022 lúc 19:46:23 đã bị hủy.\r\nLý do:\r\nkhông mua nữa', '2022-01-17 23:54:39', 1),
(71, 24, 33, 'Bạn bị tố cáo vì: saaa\r\nNếu tiếp tục vi phạm sẽ bị cảnh cáo, quá 2 lần cảnh cáo sẽ bị khóa tài khoản.', '2022-01-19 22:29:39', 1),
(73, 10, -1, 'Sản phẩm <b>Bộ máy tính I5 Siêu nhanh</b> của bạn đã được duyệt.', '2022-01-21 13:23:03', 1),
(74, 10, 27, 'Đã đánh giá sản phẩm <b>MacBook Pro 14\" 2021 M1 Pro Ram 32GB</b> của bạn.\r\nSố sao: 4.\r\nNội dung: ggaga', '2022-01-21 13:23:41', 1),
(75, 10, 27, 'Đã đánh giá sản phẩm <b>MacBook Pro 14\" 2021 M1 Pro Ram 32GB</b> của bạn.\r\nSố sao: 3.\r\nNội dung: gsgsg', '2022-01-21 13:23:54', 1),
(78, 27, -1, 'Sản phẩm <b>test 123</b> do bạn đặt ngày 21-01-2022 lúc 15:22:58 đã bị hủy.\r\nLý do:\r\nfafa', '2022-01-21 15:27:53', 0),
(81, 4, -1, 'Sản phẩm <b>Áo lót mút mỏng chất ren BOM SISTER BR009</b> do bạn đặt ngày 10-01-2022 lúc 19:54:42 đang được vận chuyển.', '2022-01-21 15:44:17', 1),
(82, 10, -1, 'Sản phẩm <b>Bộ máy tính I5 Siêu nhanh</b> của bạn đã được duyệt.', '2022-01-21 15:49:51', 1),
(83, 4, -1, 'Sản phẩm <b>Áo lót mút mỏng chất ren BOM SISTER BR009</b> do bạn đặt ngày 10-01-2022 lúc 19:54:19 đang được vận chuyển.', '2022-01-21 16:37:08', 1),
(84, 4, -1, 'Sản phẩm <b>Áo lót mút mỏng chất ren BOM SISTER BR009</b> do bạn đặt ngày 10-01-2022 lúc 19:54:19 đã bị ngừng vận chuyển.\r\nLý do:\r\nKhông có lý do', '2022-01-21 16:37:10', 1),
(85, 4, -1, 'Sản phẩm <b>Áo lót mút mỏng chất ren BOM SISTER BR009</b> do bạn đặt ngày 10-01-2022 lúc 19:54:19 đang được vận chuyển.', '2022-01-21 16:38:37', 1),
(86, 10, -1, 'Sản phẩm <b>Áo lót mút mỏng chất ren BOM SISTER BR009</b> do <u><i>Lâm Minh Thiện</i></u> đặt đã được giao thành công.', '2022-01-21 17:37:52', 0),
(87, 10, -1, 'Sản phẩm <b>Áo lót mút mỏng chất ren BOM SISTER BR009</b> do <u><i>Nguyễn Thị Test</i></u> đặt ngày 21-01-2022 lúc 17:43:32 đã bị hủy.\r\nLý do:\r\nKhông có lý do', '2022-01-21 17:43:45', 0),
(88, 4, -1, 'Sản phẩm <b>Áo lót mút mỏng chất ren BOM SISTER BR009</b> do bạn đặt ngày 10-01-2022 lúc 19:54:08 đang được vận chuyển.', '2022-01-21 17:47:46', 0),
(89, 4, -1, 'Sản phẩm <b>Áo lót mút mỏng chất ren BOM SISTER BR009</b> do bạn đặt ngày 10-01-2022 lúc 19:54:08 đã bị ngừng vận chuyển.\r\nLý do:\r\nKhông có lý do', '2022-01-21 17:47:48', 0),
(90, 23, -1, 'Sản phẩm <b>Áo lót mút mỏng chất ren BOM SISTER BR009</b> do bạn đặt ngày 10-01-2022 lúc 19:46:44 đã bị hủy.\r\nLý do:\r\nKhông có lý do', '2022-01-21 17:56:32', 0);

-- --------------------------------------------------------

--
-- Table structure for table `thong_tin_lien_he`
--

CREATE TABLE `thong_tin_lien_he` (
  `ma_khach_hang` int(11) NOT NULL,
  `twitter_link` varchar(255) DEFAULT NULL,
  `facebook_link` varchar(255) DEFAULT NULL,
  `personal_link` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `thong_tin_lien_he`
--

INSERT INTO `thong_tin_lien_he` (`ma_khach_hang`, `twitter_link`, `facebook_link`, `personal_link`) VALUES
(21, '', '', ''),
(22, '', '', ''),
(23, '', '', ''),
(25, '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `thu_phi`
--

CREATE TABLE `thu_phi` (
  `ma_khach_hang` int(11) NOT NULL,
  `ngay_thanh_toan` datetime NOT NULL,
  `phi` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anh_san_pham`
--
ALTER TABLE `anh_san_pham`
  ADD PRIMARY KEY (`ma_anh`),
  ADD KEY `asp_ibfk_1_idx` (`ma_san_pham`);

--
-- Indexes for table `bao_cao_nguoi_dung`
--
ALTER TABLE `bao_cao_nguoi_dung`
  ADD PRIMARY KEY (`ma_bao_cao`),
  ADD KEY `bcnd_ibfk_1_idx` (`id_nguoi_nhan`,`id_nguoi_gui`),
  ADD KEY `bcnd_ibfk_1` (`id_nguoi_gui`);

--
-- Indexes for table `chi_tiet_dat_hang`
--
ALTER TABLE `chi_tiet_dat_hang`
  ADD PRIMARY KEY (`ma_dat_hang`,`ma_san_pham`),
  ADD KEY `ctdh_ibfk_1_idx` (`ma_san_pham`);

--
-- Indexes for table `chuc_vu`
--
ALTER TABLE `chuc_vu`
  ADD PRIMARY KEY (`ma_chuc_vu`),
  ADD UNIQUE KEY `ten_chuc_vu_UNIQUE` (`ten_chuc_vu`);

--
-- Indexes for table `danh_gia_san_pham`
--
ALTER TABLE `danh_gia_san_pham`
  ADD PRIMARY KEY (`ma_danh_gia`,`ma_khach_hang`,`ma_san_pham`),
  ADD KEY `dgsp_ibfk_1_idx` (`ma_khach_hang`),
  ADD KEY `dgsp_ibfk_2_idx` (`ma_san_pham`);

--
-- Indexes for table `dat_hang`
--
ALTER TABLE `dat_hang`
  ADD PRIMARY KEY (`ma_dat_hang`),
  ADD KEY `dh_ibfk_1_idx` (`ma_khach_hang`);

--
-- Indexes for table `gio_hang`
--
ALTER TABLE `gio_hang`
  ADD PRIMARY KEY (`ma_khach_hang`,`ma_san_pham`),
  ADD KEY `gh_ibfk_2_idx` (`ma_san_pham`);

--
-- Indexes for table `khach_hang`
--
ALTER TABLE `khach_hang`
  ADD PRIMARY KEY (`ma_khach_hang`),
  ADD KEY `kh_ibfk_1_idx` (`id_tai_khoan`);

--
-- Indexes for table `loai_san_pham`
--
ALTER TABLE `loai_san_pham`
  ADD PRIMARY KEY (`ma_loai_sp`),
  ADD UNIQUE KEY `ma_loai_sp_UNIQUE` (`ma_loai_sp`),
  ADD UNIQUE KEY `ten_loai_sp_UNIQUE` (`ten_loai_sp`),
  ADD KEY `lsp_ibfk_1` (`ma_loai_cha`);

--
-- Indexes for table `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`id_log`),
  ADD KEY `log_ibfk_1` (`id_tai_khoan`);

--
-- Indexes for table `nhan_vien`
--
ALTER TABLE `nhan_vien`
  ADD PRIMARY KEY (`ma_nhan_vien`),
  ADD KEY `nv_ibfk_1_idx` (`ma_chuc_vu`),
  ADD KEY `nv_ibfk_2_idx` (`id_tai_khoan`);

--
-- Indexes for table `phan_hoi_danh_gia_sp`
--
ALTER TABLE `phan_hoi_danh_gia_sp`
  ADD PRIMARY KEY (`ma_phan_hoi`),
  ADD KEY `phdg_fk_1_idx` (`ma_khach_hang`),
  ADD KEY `phdg_fk_2` (`ma_danh_gia`);

--
-- Indexes for table `quyen`
--
ALTER TABLE `quyen`
  ADD PRIMARY KEY (`ma_quyen`),
  ADD UNIQUE KEY `ten_quyen_UNIQUE` (`ten_quyen`);

--
-- Indexes for table `san_pham`
--
ALTER TABLE `san_pham`
  ADD PRIMARY KEY (`ma_san_pham`),
  ADD KEY `sp_fk_1_idx` (`ma_loai_san_pham`),
  ADD KEY `sp_fk_2` (`ma_khach_hang`);
ALTER TABLE `san_pham` ADD FULLTEXT KEY `ten_san_pham` (`ten_san_pham`);

--
-- Indexes for table `san_pham_yeu_thich`
--
ALTER TABLE `san_pham_yeu_thich`
  ADD PRIMARY KEY (`ma_khach_hang`,`ma_san_pham`),
  ADD KEY `spyt_ibfk_1` (`ma_san_pham`);

--
-- Indexes for table `tai_khoan`
--
ALTER TABLE `tai_khoan`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username_UNIQUE` (`username`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`),
  ADD KEY `tk_ibfk_1_idx` (`ma_quyen`);

--
-- Indexes for table `thong_bao`
--
ALTER TABLE `thong_bao`
  ADD PRIMARY KEY (`ma_tb`),
  ADD KEY `tb_ibfk_1_idx` (`id_nguoi_nhan`),
  ADD KEY `tb_ibfk_2_idx` (`id_nguoi_gui`);

--
-- Indexes for table `thong_tin_lien_he`
--
ALTER TABLE `thong_tin_lien_he`
  ADD PRIMARY KEY (`ma_khach_hang`),
  ADD UNIQUE KEY `ma_khach_hang_UNIQUE` (`ma_khach_hang`);

--
-- Indexes for table `thu_phi`
--
ALTER TABLE `thu_phi`
  ADD PRIMARY KEY (`ma_khach_hang`,`ngay_thanh_toan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `anh_san_pham`
--
ALTER TABLE `anh_san_pham`
  MODIFY `ma_anh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- AUTO_INCREMENT for table `bao_cao_nguoi_dung`
--
ALTER TABLE `bao_cao_nguoi_dung`
  MODIFY `ma_bao_cao` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `danh_gia_san_pham`
--
ALTER TABLE `danh_gia_san_pham`
  MODIFY `ma_danh_gia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT for table `dat_hang`
--
ALTER TABLE `dat_hang`
  MODIFY `ma_dat_hang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=82;

--
-- AUTO_INCREMENT for table `khach_hang`
--
ALTER TABLE `khach_hang`
  MODIFY `ma_khach_hang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `loai_san_pham`
--
ALTER TABLE `loai_san_pham`
  MODIFY `ma_loai_sp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `log`
--
ALTER TABLE `log`
  MODIFY `id_log` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `phan_hoi_danh_gia_sp`
--
ALTER TABLE `phan_hoi_danh_gia_sp`
  MODIFY `ma_phan_hoi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT for table `tai_khoan`
--
ALTER TABLE `tai_khoan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `thong_bao`
--
ALTER TABLE `thong_bao`
  MODIFY `ma_tb` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=91;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `anh_san_pham`
--
ALTER TABLE `anh_san_pham`
  ADD CONSTRAINT `asp_ibfk_1` FOREIGN KEY (`ma_san_pham`) REFERENCES `san_pham` (`ma_san_pham`);

--
-- Constraints for table `bao_cao_nguoi_dung`
--
ALTER TABLE `bao_cao_nguoi_dung`
  ADD CONSTRAINT `bcnd_ibfk1` FOREIGN KEY (`id_nguoi_nhan`) REFERENCES `tai_khoan` (`id`),
  ADD CONSTRAINT `bncd_ibfk2` FOREIGN KEY (`id_nguoi_gui`) REFERENCES `tai_khoan` (`id`);

--
-- Constraints for table `chi_tiet_dat_hang`
--
ALTER TABLE `chi_tiet_dat_hang`
  ADD CONSTRAINT `ctdh_ibfk_1` FOREIGN KEY (`ma_san_pham`) REFERENCES `san_pham` (`ma_san_pham`),
  ADD CONSTRAINT `ctdh_ibfk_2` FOREIGN KEY (`ma_dat_hang`) REFERENCES `dat_hang` (`ma_dat_hang`);

--
-- Constraints for table `danh_gia_san_pham`
--
ALTER TABLE `danh_gia_san_pham`
  ADD CONSTRAINT `dgsp_ibfk_1` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`ma_khach_hang`),
  ADD CONSTRAINT `dgsp_ibfk_2` FOREIGN KEY (`ma_san_pham`) REFERENCES `san_pham` (`ma_san_pham`);

--
-- Constraints for table `dat_hang`
--
ALTER TABLE `dat_hang`
  ADD CONSTRAINT `dh_ibfk_1` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`ma_khach_hang`);

--
-- Constraints for table `gio_hang`
--
ALTER TABLE `gio_hang`
  ADD CONSTRAINT `gh_ibfk_1` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`ma_khach_hang`),
  ADD CONSTRAINT `gh_ibfk_2` FOREIGN KEY (`ma_san_pham`) REFERENCES `san_pham` (`ma_san_pham`);

--
-- Constraints for table `khach_hang`
--
ALTER TABLE `khach_hang`
  ADD CONSTRAINT `kh_ibfk_1` FOREIGN KEY (`id_tai_khoan`) REFERENCES `tai_khoan` (`id`);

--
-- Constraints for table `loai_san_pham`
--
ALTER TABLE `loai_san_pham`
  ADD CONSTRAINT `lsp_ibfk_1` FOREIGN KEY (`ma_loai_cha`) REFERENCES `loai_san_pham` (`ma_loai_sp`);

--
-- Constraints for table `log`
--
ALTER TABLE `log`
  ADD CONSTRAINT `log_ibfk_1` FOREIGN KEY (`id_tai_khoan`) REFERENCES `tai_khoan` (`id`);

--
-- Constraints for table `nhan_vien`
--
ALTER TABLE `nhan_vien`
  ADD CONSTRAINT `nv_ibfk_1` FOREIGN KEY (`ma_chuc_vu`) REFERENCES `chuc_vu` (`ma_chuc_vu`),
  ADD CONSTRAINT `nv_ibfk_2` FOREIGN KEY (`id_tai_khoan`) REFERENCES `tai_khoan` (`id`);

--
-- Constraints for table `phan_hoi_danh_gia_sp`
--
ALTER TABLE `phan_hoi_danh_gia_sp`
  ADD CONSTRAINT `phdg_fk_1` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`ma_khach_hang`) ON DELETE CASCADE,
  ADD CONSTRAINT `phdg_fk_2` FOREIGN KEY (`ma_danh_gia`) REFERENCES `danh_gia_san_pham` (`ma_danh_gia`) ON DELETE CASCADE,
  ADD CONSTRAINT `phdg_fk_3` FOREIGN KEY (`ma_danh_gia`) REFERENCES `danh_gia_san_pham` (`ma_danh_gia`) ON DELETE CASCADE;

--
-- Constraints for table `san_pham`
--
ALTER TABLE `san_pham`
  ADD CONSTRAINT `sp_fk_1` FOREIGN KEY (`ma_loai_san_pham`) REFERENCES `loai_san_pham` (`ma_loai_sp`),
  ADD CONSTRAINT `sp_fk_2` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`ma_khach_hang`);

--
-- Constraints for table `san_pham_yeu_thich`
--
ALTER TABLE `san_pham_yeu_thich`
  ADD CONSTRAINT `spyt_ibfk_1` FOREIGN KEY (`ma_san_pham`) REFERENCES `san_pham` (`ma_san_pham`),
  ADD CONSTRAINT `spyt_ibfk_2` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`ma_khach_hang`);

--
-- Constraints for table `tai_khoan`
--
ALTER TABLE `tai_khoan`
  ADD CONSTRAINT `tk_ibfk_1` FOREIGN KEY (`ma_quyen`) REFERENCES `quyen` (`ma_quyen`);

--
-- Constraints for table `thong_bao`
--
ALTER TABLE `thong_bao`
  ADD CONSTRAINT `tb_ibfk_1` FOREIGN KEY (`id_nguoi_nhan`) REFERENCES `tai_khoan` (`id`),
  ADD CONSTRAINT `tb_ibfk_2` FOREIGN KEY (`id_nguoi_gui`) REFERENCES `tai_khoan` (`id`);

--
-- Constraints for table `thong_tin_lien_he`
--
ALTER TABLE `thong_tin_lien_he`
  ADD CONSTRAINT `ttlh_ibfk_1` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`ma_khach_hang`);

--
-- Constraints for table `thu_phi`
--
ALTER TABLE `thu_phi`
  ADD CONSTRAINT `tp_ibfk_1` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`ma_khach_hang`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
