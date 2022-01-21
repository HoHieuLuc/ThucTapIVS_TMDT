-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 12, 2022 at 05:53 AM
-- Server version: 8.0.27
-- PHP Version: 8.1.1

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
  `ma_anh` int NOT NULL,
  `ma_san_pham` varchar(36) NOT NULL,
  `anh` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

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
(51, 'd23e005c-6ef6-11ec-9aea-4c52b493c111', '51.png');

-- --------------------------------------------------------

--
-- Table structure for table `bao_cao_nguoi_dung`
--

CREATE TABLE `bao_cao_nguoi_dung` (
  `ma_bao_cao` int NOT NULL,
  `id_nguoi_nhan` int NOT NULL,
  `id_nguoi_gui` int NOT NULL,
  `noi_dung` text NOT NULL,
  `status` int NOT NULL,
  `ngay_tao` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Table structure for table `chi_tiet_dat_hang`
--

CREATE TABLE `chi_tiet_dat_hang` (
  `ma_dat_hang` int NOT NULL,
  `ma_san_pham` varchar(36) NOT NULL,
  `so_luong` int NOT NULL,
  `status` int NOT NULL DEFAULT '0',
  `ma_nhan_hang` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `chi_tiet_dat_hang`
--

INSERT INTO `chi_tiet_dat_hang` (`ma_dat_hang`, `ma_san_pham`, `so_luong`, `status`, `ma_nhan_hang`) VALUES
(18, '130ea67a-6528-11ec-b702-7845f2f0d96e', 4, 2, 'DED49B'),
(18, 'SP001', 1, -1, '97DAEF'),
(19, 'bc49e268-6528-11ec-b702-7845f2f0d96e', 1, 0, 'B65305'),
(20, '5dfb7106-651f-11ec-b702-7845f2f0d96e', 2, 0, '9FBCB2'),
(21, '130ea67a-6528-11ec-b702-7845f2f0d96e', 2, -1, '57594C'),
(21, '35a99f29-64da-11ec-bb14-8378cfa7d63d', 2, -1, 'D85BD3'),
(21, '5dfb7106-651f-11ec-b702-7845f2f0d96e', 1, 0, '426B60'),
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
(32, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', 1, 0, '6AB360'),
(32, '560c2201-6ef1-11ec-9aea-4c52b493c111', 1, 0, 'CA2A1F'),
(32, '5dfb7106-651f-11ec-b702-7845f2f0d96e', 1, 0, 'F68B1F'),
(32, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, 0, '5CCCCB'),
(32, 'aa76ecd2-6b6b-11ec-8506-571a7e295222', 1, 0, 'E8B3CB'),
(32, 'd23e005c-6ef6-11ec-9aea-4c52b493c111', 1, 0, 'C6587D'),
(33, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 3, 0, '1C76A9'),
(34, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, -1, '606B2B'),
(35, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, 0, '48EFAF'),
(36, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, 0, '976F18'),
(37, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, 0, 'E17BB6'),
(38, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, 0, 'E26D6B'),
(39, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, 0, '143CB8'),
(40, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, 0, '676441'),
(41, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, 0, 'E2DFAF'),
(41, 'd23e005c-6ef6-11ec-9aea-4c52b493c111', 1, 0, '55F9DE'),
(42, '560c2201-6ef1-11ec-9aea-4c52b493c111', 1, 0, '7267D2'),
(42, 'd23e005c-6ef6-11ec-9aea-4c52b493c111', 1, 0, '97FB85'),
(43, '560c2201-6ef1-11ec-9aea-4c52b493c111', 1, 0, 'F72683'),
(43, 'd23e005c-6ef6-11ec-9aea-4c52b493c111', 1, 0, 'F048E7'),
(44, '560c2201-6ef1-11ec-9aea-4c52b493c111', 1, 0, 'AFE6C3'),
(44, 'd23e005c-6ef6-11ec-9aea-4c52b493c111', 1, 0, 'BBC39B'),
(45, '560c2201-6ef1-11ec-9aea-4c52b493c111', 1, -1, '9EDFE8'),
(45, 'd23e005c-6ef6-11ec-9aea-4c52b493c111', 1, -1, 'E1BD55'),
(46, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', 1, -1, '4831F4'),
(47, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', 2, 0, '77B094'),
(47, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, 0, '7084C9'),
(48, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, 0, '1DCCA8'),
(49, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 2, 0, '0BA9BF'),
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
(63, 'aa76ecd2-6b6b-11ec-8506-571a7e295222', 2, 0, '50650F'),
(64, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', 2, 0, 'FAC14C'),
(64, 'aa76ecd2-6b6b-11ec-8506-571a7e295222', 1, 0, '0ABD93'),
(65, '35a99f29-64da-11ec-bb14-8378cfa7d63d', 2, 1, '5627C0'),
(65, 'a862b5ce-64d6-11ec-bb14-8378cfa7d63d', 1, 0, '8F8E3E'),
(66, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 1, 1, '1F9940'),
(67, '983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 3, 2, 'AD7646');

-- --------------------------------------------------------

--
-- Table structure for table `chuc_vu`
--

CREATE TABLE `chuc_vu` (
  `ma_chuc_vu` varchar(30) NOT NULL,
  `ten_chuc_vu` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

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
  `ma_danh_gia` int NOT NULL,
  `ma_khach_hang` int NOT NULL,
  `so_sao` smallint NOT NULL,
  `noi_dung` varchar(255) NOT NULL,
  `ma_san_pham` varchar(36) NOT NULL,
  `ngay_tao` datetime NOT NULL,
  `ngay_sua` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

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
(34, 6, 5, 'okkkkk', 'test', '2022-01-09 22:45:31', '2022-01-09 22:45:31');

-- --------------------------------------------------------

--
-- Table structure for table `dat_hang`
--

CREATE TABLE `dat_hang` (
  `ma_dat_hang` int NOT NULL,
  `ma_khach_hang` int NOT NULL,
  `ngay_dat` datetime NOT NULL,
  `tong_tien` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

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
(71, 10, '2022-01-12 11:46:50', 7497000);

-- --------------------------------------------------------

--
-- Table structure for table `gio_hang`
--

CREATE TABLE `gio_hang` (
  `ma_khach_hang` int NOT NULL,
  `ma_san_pham` varchar(36) NOT NULL,
  `so_luong` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `gio_hang`
--

INSERT INTO `gio_hang` (`ma_khach_hang`, `ma_san_pham`, `so_luong`) VALUES
(1, 'bc49e268-6528-11ec-b702-7845f2f0d96e', 1),
(10, '35a99f29-64da-11ec-bb14-8378cfa7d63d', 1),
(10, '560c2201-6ef1-11ec-9aea-4c52b493c111', 63),
(10, 'a862b5ce-64d6-11ec-bb14-8378cfa7d63d', 1);

-- --------------------------------------------------------

--
-- Table structure for table `khach_hang`
--

CREATE TABLE `khach_hang` (
  `ma_khach_hang` int NOT NULL,
  `ten` varchar(50) NOT NULL,
  `dia_chi` varchar(100) NOT NULL,
  `id_tai_khoan` int NOT NULL,
  `tien_no` double NOT NULL,
  `gioi_thieu` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

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
(23, 'Tommy Vercities', 'Cần Thơ', 32, 0, 'This is present text');

-- --------------------------------------------------------

--
-- Table structure for table `loai_san_pham`
--

CREATE TABLE `loai_san_pham` (
  `ma_loai_sp` int NOT NULL,
  `ten_loai_sp` varchar(155) NOT NULL,
  `anh` varchar(255) NOT NULL,
  `ma_loai_cha` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

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
(31, 'Mận', '26.png', 27);

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE `log` (
  `id_log` int NOT NULL,
  `id_tai_khoan` int NOT NULL,
  `ngay_tao` date NOT NULL,
  `hoat_dong` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Table structure for table `nhan_vien`
--

CREATE TABLE `nhan_vien` (
  `ma_nhan_vien` varchar(20) NOT NULL,
  `ten_nhan_vien` varchar(255) NOT NULL,
  `ma_chuc_vu` varchar(30) NOT NULL,
  `id_tai_khoan` int DEFAULT NULL,
  `luong` double NOT NULL,
  `dia_chi` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `nhan_vien`
--

INSERT INTO `nhan_vien` (`ma_nhan_vien`, `ten_nhan_vien`, `ma_chuc_vu`, `id_tai_khoan`, `luong`, `dia_chi`) VALUES
('1test', 'Hồ Hiểu Lực', 'giamdoc', 7, 100000000, 'fafafa');

-- --------------------------------------------------------

--
-- Table structure for table `phan_hoi_danh_gia_sp`
--

CREATE TABLE `phan_hoi_danh_gia_sp` (
  `ma_phan_hoi` int NOT NULL,
  `ma_danh_gia` int NOT NULL,
  `noi_dung` varchar(255) NOT NULL,
  `ma_khach_hang` int NOT NULL,
  `ngay_tao` datetime NOT NULL,
  `ngay_sua` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='Phản hồi đánh giá sản phẩm';

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
(38, 28, 'af', 6, '2022-01-04 22:57:01', NULL),
(39, 28, 'afggg', 6, '2022-01-04 22:57:10', NULL),
(40, 28, 'afgggg', 6, '2022-01-04 22:57:14', NULL),
(43, 8, 'gg', 6, '2022-01-04 22:57:39', NULL),
(45, 21, 'gggag', 6, '2022-01-04 22:59:33', NULL),
(46, 28, 'cảm ơn', 6, '2022-01-07 22:43:08', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `quyen`
--

CREATE TABLE `quyen` (
  `ma_quyen` varchar(30) NOT NULL,
  `ten_quyen` varchar(100) NOT NULL,
  `cap_do` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

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
  `ma_khach_hang` int NOT NULL,
  `ten_san_pham` varchar(255) NOT NULL,
  `mo_ta` text NOT NULL,
  `gia` double NOT NULL,
  `status` int NOT NULL,
  `ma_loai_san_pham` int NOT NULL,
  `so_luong` int NOT NULL,
  `ngay_dang` datetime NOT NULL,
  `so_luong_da_ban` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `san_pham`
--

INSERT INTO `san_pham` (`ma_san_pham`, `ma_khach_hang`, `ten_san_pham`, `mo_ta`, `gia`, `status`, `ma_loai_san_pham`, `so_luong`, `ngay_dang`, `so_luong_da_ban`) VALUES
('0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', 6, 'Bộ máy tính I5 Siêu nhanh', '  Cấu hình phù hợp mọi Game thủ Chơi,đồ họa mượt  LoL, đột kích ,truy kích,free fire  ,văn phòng cấu hình cao', 4890000, 0, 20, 15, '2021-12-31 21:58:04', 0),
('130ea67a-6528-11ec-b702-7845f2f0d96e', 15, 'Áo vest nam DARNELL', 'Áo vest Darnell với thiết kế form vừa vặn, cấu trúc đường may tỉ mỉ, đẹp mắt, giúp bạn thể hiện phong cách thời trang nơi công sở. Bạn dễ dàng phối với áo sơ mi, áo polo,...item không thể thiếu trong tủ đồ của phái nam.\n\nChất liệu: Nhung Dạ\nMàu kẻ đơn giản, sang trọng\nKiểu dáng trẻ trung\nForm vừa vặn\nCông sở là quá chuẩn\nMàu sắc: Đen Trơn, Ghi Kẻ Ô, Kẻ Tím, Đen Nhung, Xanh Xước\nKích thước : S – M - L - XL\nThương hiệu: Darnell\nXuất xứ: Việt Nam\n\nĐẶC TÍNH VƯỢT TRỘI:\nChất vải co giãn, đẹp, bền, không bai, không xù, không bám dính\nĐường may tinh tế, chỉn chu, khéo léo\nMàu sắc đa dạng, trẻ trung\nChất lượng sản phẩm tốt, giá cả hợp lý', 1189000, 0, 13, 10, '2021-12-13 04:19:07', 0),
('1e717293-652c-11ec-b702-7845f2f0d96e', 6, 'Áo phao lông vũ nam ', 'áo ba lỗ siêu nhẹ, giữ nhiệt tốt cản nước cản gió', 679000, 0, 15, 12, '2021-12-23 03:12:11', 0),
('35a99f29-64da-11ec-bb14-8378cfa7d63d', 15, 'Apple MacBook Air (2020)', 'Máy tính xách tay mỏng và nhẹ nhất của Apple, nay siêu mạnh mẽ với chip Apple M1. Xử lý công việc giúp bạn với CPU 8 lõi nhanh như chớp. Đưa các ứng dụng và game có đồ họa khủng lên một tầm cao mới với GPU 8 lõi. Đồng thời, tăng tốc các tác vụ máy học với Neural Engine 16 lõi. Tất cả gói gọn trong một thiết kế không quạt, giảm thiểu tiếng ồn, thời lượng pin dài nhất từ trước đến nay lên đến 18 giờ. (1) MacBook Air. Vẫn cực kỳ cơ động. Mà mạnh mẽ hơn nhiều.\n \nTính năng nổi bật \n•       Chip M1 do Apple thiết kế tạo ra một cú nhảy vọt về hiệu năng máy học, CPU và GPU \n•       Tăng thời gian sử dụng với thời lượng pin lên đến 18 giờ (1) \n•       CPU 8 lõi cho tốc độ nhanh hơn đến 3.5x, xử lý công việc nhanh chóng hơn bao giờ hết (2)  \n•       GPU lên đến 8 lõi với tốc độ xử lý đồ họa nhanh hơn đến 5x cho các ứng dụng và game đồ họa khủng (2)  \n•       Neural Engine 16 lõi cho công nghệ máy học hiện đại \n•       Bộ nhớ thống nhất 8GB giúp bạn làm việc gì cũng nhanh chóng và trôi chảy  \n•       Ổ lưu trữ SSD siêu nhanh giúp mở các ứng dụng và tập tin chỉ trong tích tắc \n•       Thiết kế không quạt giảm tối đa tiếng ồn khi sử dụng  \n•       Màn hình Retina 13.3 inch với dải màu rộng P3 cho hình ảnh sống động và chi tiết ấn tượng (3)\n•       Camera FaceTime HD với bộ xử lý tín hiệu hình ảnh tiên tiến cho các cuộc gọi video đẹp hình, rõ tiếng hơn \n•       Bộ ba micro phối hợp tập trung thu giọng nói của bạn, không thu tạp âm môi trường \n•       Wi-Fi 6 thế hệ mới giúp kết nối nhanh hơn \n•       Hai cổng Thunderbolt / USB 4 để sạc và kết nối phụ kiện \n•       Bàn phím Magic Keyboard có đèn nền và Touch ID giúp mở khóa và thanh toán an toàn hơn \n•       macOS Big Sur với thiết kế mới đầy táo bạo cùng nhiều cập nhật quan trọng cho các ứng dụng Safari, Messages và Maps \n•       Hiện có màu vàng kim, xám bạc và bạc \n\nPháp lý \nHiện có sẵn các lựa chọn để nâng cấp. \n(1) Thời lượng pin khác nhau tùy theo cách sử dụng và cấu hình. Truy cập apple.com/batteries để biết thêm thông tin. \n(2) So với thế hệ máy trước. \n(3) Kích thước màn hình tính theo đường chéo. ', 30700000, 0, 21, 20, '2021-12-24 00:00:00', 0),
('560c2201-6ef1-11ec-9aea-4c52b493c111', 6, 'Chuột không dây Bluetooth SIDOTECH', 'CHUỘT KHÔNG DÂY BLUETOOTH TỰ SẠC PIN SIDOTECH M1P\n\nƯU ĐIỂM NỔI BẬT CHUỘT KHÔNG DÂY BLUETOOTH SẠC PIN SIDOTECH M1P\n• Không cần sử dụng pin tiểu AAA\n• Sạc trực tiếp thông qua cổng USB\n• Một lần sạc đầy khoảng 2h và sử dụng trong 1 tuần liên tục\n• Có thể vừa sạc vừa dùng như một chiếc chuột có dây thông thường\n• Kết nối xa 10 mét\n• Độ nhạy DPI 1000/1200/1600 điều chỉnh thông qua nút\n• 3 chế độ kết nối, dùng cổng USB, Bluetooth, cổng Sạc\n• Thiết kế hiện đại thoải mái sang trọng như chuột của Apple\n• Nhiều màu để lựa chọn', 119000, 2, 22, 63, '2022-01-06 20:05:35', 0),
('5dfb7106-651f-11ec-b702-7845f2f0d96e', 16, 'Xe Đạp Trẻ Em LanQ FD', '- Tên sản phẩm : Xe Đạp Trẻ Em LanQ FD Có Giảm Xóc\n- Nhập khẩu và phân phối : Công Ty TNHH Toykid Việt Nam\n- Địa chỉ : 414 Bạch Đằng, phường Chương Dương, quận Hoàn Kiếm, Hà Nội\n- Xuất Xứ : Trung Quốc\n- Lưu ý : Không dùng cho trẻ em dưới 12 tháng tuổi', 1990000, 0, 25, 15, '2021-12-25 00:00:00', 0),
('983c63d6-6d77-11ec-b3a9-b7f6ab7445fb', 6, 'Áo lót mút mỏng chất ren BOM SISTER BR009', 'Đặc điểm sản phẩm:\n\n Chất liệu: Vải ren mềm mại, không gây mẫn ngứa ( Nhà cung cấp có quyền đổi chi tiết ren nếu loại ren trên hình hết- Không thay đổi cấu trúc áo và màu)\n▪ Thiết kế: Gia công bởi người Việt, đường kim mũi chỉ chắc chắn\n▪ Ứng dụng: Thích hợp mặc vào \"ngày đèn đỏ\" cực dễ chịu\n▪ Được bảo hành đường kim - mũi chỉ trọn đời', 128000, 0, 18, 14, '2022-01-04 23:01:36', 0),
('a862b5ce-64d6-11ec-bb14-8378cfa7d63d', 15, 'Hub cổng chuyển đổi', 'Hub chuyển đa năng Baseus CAHUB-BG0G 16 trong 1 được trang bị gần như tất cả các cổng chuyển đổi cơ bản và hỗ trợ hầu hết các thiết bị số hiện nay từ Macbook, smartphone/ Tablet/ Laptop Android/Windows... HUB chuyển đổi có ngõ vào là cổng USB Type C và ngỏ ra là hầu hết các chuẩn giao tiếp phổ thông hiện nay như : USB 2.0/ USB 3.0 / HDMI/ VGA/ USB 3.0/ Card Reader/ RJ-45/AUX 3.5mm ...\n- Với Hub chuyển đa năng Baseus Working Station 16 in 1 Multifunctional bạn có thể dễ dàng xuất hình ảnh, âm thanh độ nét cao qua cổng HDMI/ VGA/ AUX 3.5mm, đồng bộ và chia sẽ dữ liệu/ hình ảnh từ thẻ nhớ, các thiết bị lưu trữ. Ngoài ra bộ HUB còn được trang bị cổng mạng LAN có dây (RJ-45) cho phép bạn có thể kết nối mạng ở những nơi không có Wifi . Bạn sẽ không còn phải gặp các phiền phức và bất tiện do thiếu cổng kết nối.\n- Sản phẩm được trang bị cổng HDMI và VGA hỗ trợ 4K ở ngõ ra cho phép bạn xuất hình ảnh từ Laptop ra 2 màn hình lớn khác nhau ở cả 2 chế độ song song hoặc mở rộng (chia/ghép màn hình) .\n- Trang bị chip xử lý thông minh, đảm bảo tốc độ truyền tải nhanh và ổn định , tương thích với hầu hết các thiết bị Type C . Chỉ việc cắm và sử dụng (Plug & Play) không cần phải thực hiện các thao tác cài đặt phức tạp .\n- Thiết kế dạng dock tiện dụng, bề mặt bằng nhôm tinh chế với tông màu xám sang trong và đẳng cấp tạo sự đồng bộ với thiết kế của Macbook.\nThông số kỹ thuật sản phẩm \n- Chất liệu: Hợp kim nhôm\n- Kích thước: 80mm * 86mm * 162mm\n- Trọng lượng: 400g\n- Giao diện: Loại-CPD (đầu vào) + Loại-C (đầu ra) + USB2.0 * 2 + VGA * 1 + 4K HD * 1 + - Bộ nguồn DC * 1 + RJ45 * 1 + SD + TF * 1 + USB 3.0 * 3+\n- Jack âm thanh loại: C + 3,5mm\n- Đầu vào PD loại C: 100W\n- 4K HD: Hỗ trợ màn hình 4K @ 30HZ HD\n- VGA: Hỗ trợ màn hình HD 1080P @ 60HZ\n- RJ45: Tốc độ mạng Gigabit, kết nối mạng có dây, kết nối mạng cho máy tính, máy tính bảng và điện thoại di động\n- USB3.0 * 3: đọc và ghi dữ liệu tốc độ cao của đĩa flash, tương thích hướng xuống với USB2.0 và USB1.1\n- USB2.0 * 2: đọc và ghi dữ liệu tốc độ cao của đĩa flash, tương thích hướng xuống với USB1.1\n- SD / TF: Hỗ trợ đọc dữ liệu tối đa 2T (không hỗ trợ đọc đồng thời, ưu tiên thẻ được đọc trước tiên)\n- Giắc âm thanh 3,5 mm: Hỗ trợ đầu vào và đầu ra\n- Loại-C * 2: Hỗ trợ đọc từ đĩa flash Loại-C (Không hỗ trợ đầu ra âm thanh Loại-c)\n- Giao diện: Loại-CPD (đầu vào) + Loại-C (đầu ra) + USB2.0 * 2 + VGA * 1 + 4K HD * 1 + Bộ nguồn DC * 1 + RJ45 * 1 + SD + TF * 1 + USB 3.0 * 3+\n- 16 in 1 Model (CAHUB-BG0G): USB 3.0*3 + USB 2.0*2 + HDMI +VGA +TF/SD Card Reader + RJ45 +PD + PC +3.5mm Jack + DC Port + PD Cable + UK/EU Plug + CN DC Cord (Suit)\nLưu ý \n- Do giới hạn về hệ thống của MACOS, MacBook không hỗ trợ tách thành 3 màn hình khác nhau.\n- Khi sử dụng một cổng HDMI, chất lượng hình ảnh là 4K / 60Hz, khi sử dụng hai cổng HDMI, chất lượng hình ảnh là 4K / 30Hz, khi sử dụng 3 cổng HDMI, chất lượng hình ảnh là 1080p\n- Khi nguồ', 2105000, 0, 22, 500, '2021-12-24 00:00:00', 0),
('aa76ecd2-6b6b-11ec-8506-571a7e295222', 6, 'Xe đạp điện DK Bike 133M ', 'Thông số kỹ thuật\nKích cỡ/Sizes	1593mm x 635mm x 1015mm\nMàu sắc/Colors	Đỏ, Ghi, Xanh cửu long, Cam\nẮc quy/Battery	Chì axit kín khí 48V-12A\nCông suất động cơ/Motor	600W\nVận tốc tối đa/Max speed	35 – 40 km/h\nQuãng đường di chuyển/Range	50 – 60km/lần sạc\nBánh xe trước/Front Wheel	Lốp 16″x3.0\nBánh xe sau/Rear Tires	Lốp 16″x3.0\nThắng trước/Front Brakes	Thắng tang trống\nThắng sau/Rear Brakes	Thắng tang trống\nSạc điện/Charge	Ắc quy tự ngắt khi sạc đầy', 25000000, 0, 25, 20, '2022-01-02 08:31:10', 0),
('bc49e268-6528-11ec-b702-7845f2f0d96e', 17, 'Quần lót ren lọt khe form T No Limit Miss K', '✨ Chất liệu ren nhập khẩu, thoáng mát và mềm mại\n✨ Form T lọt khe gợi cảm\n✨ Phù hợp với nhiều trang phục', 30000, 0, 18, 10, '2021-12-25 00:00:00', 0),
('d23e005c-6ef6-11ec-9aea-4c52b493c111', 6, 'Đế Tản Nhiệt Máy Tính Xách Tay INPHIC R2 Với Hai Quạt Siêu Mát Dùng Cho Máy 14 đến 17 inch', 'Thương hiệu: INPHIC\nKích thước gói: 40,6 x 31,7 x 3,7 cm\nTrọng lượng: 760 ± 20 g\nModel: R2\nKích thước phù hợp cho máy tính: 14 đến 17 inch\nTấm làm mát máy tính xách tay R2 được phát triển bởi đội ngũ thiết kế sản phẩm xuất sắc của INPHIC. Hai quạt hoạt động mạnh mẽ nhưng cực kỳ yên tĩnh để bạn cảm thấy thoải mái nhất khi làm việc hoặc giải trí.\n- Kích thước sản phẩm: 390 x 280 x 28 mm.\n- Số lượng và kích thước quạt: 2 quạt lớn\n- Đường kính quạt: 125 mm \n- Tốc độ quạt: 1500 vòng / phút \n- Nguồn điện: USB 5 V DC.\n- Dòng điện làm việc: 1 A.\n- Công suất nguồn: 5 W.\nĐặc trưng:\n- Chân đế điều chỉnh 4 mức.\n- Phù hợp với các loại laptop từ 14 - 17 inch.\n- Mặt lưới kim loại.', 150000, 0, 22, 52, '2022-01-06 20:44:50', 0),
('e4f55954-652b-11ec-b702-7845f2f0d96e', 6, 'Quần jean nam LB, vải jean denim co giãn, màu xám đen trơn, phom slim fit DNBB5226', 'hông tin sản phẩm quần jeans dài nam LBjean 5226\nThương hiệu : LB\n Xuất xứ : được thiết kế và gia công tại xưỡng sản xuất LBjean,tp HCM – Việt Nam\n- Chất liệu : 100% jean Cotton\n- Chất jean cotton loại 1 dày dặn lên dáng chuẩn lắm các bạn nhé \n- Thiết kế cạp cao cá tính mặc vào tôn dáng lắm luôn nà \n- Dễ dàng phối đồ với áo polo thích hợp 4 mùa, mặc đi chơi dạo phố đều rất đẹp nà\n- Thiết kế theo xu hướng thời trang mới nhất.\n- Phong cách hàn quốc phù hợp mọi lứa tuổi \n- Quần jeans được làm màu bằng công nghệ mới giúp vãi mềm mịn và tươi màu, không bị phai bạc màu khi sữ dụng nên bạn yên tâm hàng luôn bền màu như mới nhé', 299000, 0, 14, 2626, '2021-12-25 00:00:00', 0),
('SP001', 15, 'Xe máy Honda Vision 2021 - Phiên bản cao cấp Smartkey', '\nĐặc điểm Honda Vision 2021\n\nThiết kế phía trước hiện đại và năng động\nThiết kế phía trước kế thừa sự năng động vốn có của Vision nhưng ấn tượng hơn với diện mạo hoàn toàn mới.\n\nLogo 3D nổi trên thân xe với đường nét liền mạch, rõ ràng mang đến hình ảnh trẻ trung và năng động\n', 36900000, 0, 24, 20, '2021-01-13 00:00:00', 0),
('test', 15, 'Sạc Dự Phòng TOPK I1007P 20W PD QC3.0 10000mAh Sạc Nhanh Hiển Thị Màn Hình Điện Tử', '  Chi tiết sản phẩm: \n  ☀ Tên sản phẩm: Sạc dự phòng TOPK I1006P màn hình kỹ thuật số 10000mAh\n  ☀ Màu sắc: Xanh Navy/Bạc\n  ☀ Kích thước: 135 * 66.2 * 14.7mm\n  ☀ Cổng đầu ra: Type-C (PD3.0), USB\n  ☀ Cổng đầu vào: Type-C, Micro\n  ☀ Dung lượng định mức: 6500mAh\n  ☀ Dung lượng: 10000mAh\n  ☀ Tương thích cho tất cả các thiết bị Apple, Type C và Android.\n  \n  Đặc trưng:\n  ☀ Sạc nhanh 20W PD3.0 + QC 3.0: Cổng USB-C phân phối nhanh hai chiều, hỗ trợ cung cấp năng lượng lên đến 20W ở đầu vào và đầu ra. Đầu ra QC 3.0 hỗ trợ 5V/3A, 9V/2A, 12V/1.66A. Bạn có thể sạc 2 điện thoại cùng một lúc, pin sẽ sạc nhanh cho các thiết bị của bạn khi sử dụng tất cả 2 đầu ra cùng nhau.\n  ☀ Kích thước nhỏ dung lượng lớn: Sạc dự phòng DP 10000mAh 20W có thể sạc cho iPhone 8 3.5 lần, iPhone 12 XS 2.7 lần, Xiaomi Mi 9 2.2 lần. Thích hợp cho công việc, du lịch, đi chơi, bạn không phải lo lắng về việc điện thoại bị hết pin.\n  ☀ Vỏ bằng hợp kim nhôm với màn hình LED: Thiết kế chống trượt và chống vân tay với màn hình LED để hiển thị dung lượng pin còn lại. Màn hình sẽ hiển thị \"PD\" khi sạc nhanh thiết bị của bạn. Khi lượng pin của iPhone bạn được sạc lên tới 80%, pin sạc dự phòng sẽ sạc iPhone của bạn ở tốc độ thường nhờ hệ thống an toàn IOS.\n  ☀ Bảo vệ an toàn: Các biện pháp bảo vệ tích hợp giúp bảo vệ các thiết bị của bạn khỏi cường độ điện quá mức, quá nhiệt và quá tải.', 259000, 0, 12, 100000, '2021-12-21 00:00:00', 25);

-- --------------------------------------------------------

--
-- Table structure for table `san_pham_yeu_thich`
--

CREATE TABLE `san_pham_yeu_thich` (
  `ma_khach_hang` int NOT NULL,
  `ma_san_pham` varchar(36) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Table structure for table `tai_khoan`
--

CREATE TABLE `tai_khoan` (
  `id` int NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `ngay_tao` datetime NOT NULL,
  `so_dien_thoai` varchar(20) NOT NULL,
  `ngay_sinh` date NOT NULL,
  `gioi_tinh` tinyint(1) NOT NULL,
  `so_lan_canh_cao` int NOT NULL DEFAULT '0',
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `ma_quyen` varchar(30) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `tai_khoan`
--

INSERT INTO `tai_khoan` (`id`, `username`, `password`, `email`, `ngay_tao`, `so_dien_thoai`, `ngay_sinh`, `gioi_tinh`, `so_lan_canh_cao`, `status`, `ma_quyen`, `avatar`) VALUES
(4, 'thienlam782', '$2a$12$1L.ptNS2HeHXFLYdebBt9eP4IzbIRQz8ZSQX0uoVGtdxt43LH0oBa', 'thien@gmail.com', '2021-12-21 00:00:00', '0828504336', '2021-12-09', 0, 0, 0, 'KH', '1.png'),
(5, 'provjpzz123', '$2a$12$1L.ptNS2HeHXFLYdebBt9eP4IzbIRQz8ZSQX0uoVGtdxt43LH0oBa', 'fafa.gagag@gmail.com', '2021-12-22 00:00:00', '62626262', '2021-12-21', 0, 0, 0, 'KH', '2.png'),
(7, 'admin', '$2a$12$1L.ptNS2HeHXFLYdebBt9eP4IzbIRQz8ZSQX0uoVGtdxt43LH0oBa', 'kingofika123@gmail.com', '2021-12-23 00:00:00', '5226262626', '2021-12-15', 0, 0, 0, 'admin', '3.png'),
(10, 'trantest', '$2a$12$1L.ptNS2HeHXFLYdebBt9eP4IzbIRQz8ZSQX0uoVGtdxt43LH0oBa', 'x@x.x', '2021-12-23 00:00:00', '0987654321', '2021-12-23', 1, 0, 0, 'KH', '4.png'),
(13, 'hohieuluc', '$2a$12$1L.ptNS2HeHXFLYdebBt9eP4IzbIRQz8ZSQX0uoVGtdxt43LH0oBa', '525ffa@ga.q', '2021-12-23 00:00:00', '636372726', '2021-12-08', 0, 0, 0, 'KH', '6.png'),
(18, 'test1234', '$2a$12$1L.ptNS2HeHXFLYdebBt9eP4IzbIRQz8ZSQX0uoVGtdxt43LH0oBa', '52ddad5ffa@ga.q', '2021-12-24 00:00:00', '5252626262', '2021-12-15', 1, 0, 1, 'KH', '9.png'),
(19, 'testtest123', '$2a$12$1L.ptNS2HeHXFLYdebBt9eP4IzbIRQz8ZSQX0uoVGtdxt43LH0oBa', '5hfhfha@ghaaf.fafaf', '2021-12-24 00:00:00', '526377262', '2021-12-08', 1, 0, 1, 'KH', '8.png'),
(23, 'nguyenthitest', '$2a$12$1L.ptNS2HeHXFLYdebBt9eP4IzbIRQz8ZSQX0uoVGtdxt43LH0oBa', 'gakgag@gahgag.agag', '2021-12-24 00:00:00', '526372626', '2021-12-01', 0, 0, 1, 'KH', '10.png'),
(24, 'khtest123', '$2a$12$1L.ptNS2HeHXFLYdebBt9eP4IzbIRQz8ZSQX0uoVGtdxt43LH0oBa', 'khtest@ktktkkttk.ttqjtkqkt', '2021-12-25 00:00:00', '74736373737', '2021-12-16', 0, 0, 1, 'KH', '11.png'),
(25, 'minhthienmap', '$2a$12$1L.ptNS2HeHXFLYdebBt9eP4IzbIRQz8ZSQX0uoVGtdxt43LH0oBa', 'lamminhthien02012000@gmail.com', '2021-12-25 00:00:00', '08285043336', '2006-02-14', 0, 0, 1, 'KH', 'kequing.png'),
(26, 'fafafafafaffa', '$2a$12$1L.ptNS2HeHXFLYdebBt9eP4IzbIRQz8ZSQX0uoVGtdxt43LH0oBa', 'gagaggg@gg.a.gag', '2021-12-25 00:00:00', '525252525', '2021-12-10', 0, 0, 1, 'KH', '12.png'),
(27, 'johntest', '$2a$12$1L.ptNS2HeHXFLYdebBt9eP4IzbIRQz8ZSQX0uoVGtdxt43LH0oBa', 'johntest2252@s.g.s.g.s', '2021-12-26 00:00:00', '5555252525', '2021-12-15', 0, 0, 1, 'KH', '13.png'),
(29, 'thienlam1100', '$2a$12$1L.ptNS2HeHXFLYdebBt9eP4IzbIRQz8ZSQX0uoVGtdxt43LH0oBa', 'thien1100@gmail.com', '2021-12-27 11:36:24', '08285043336', '2021-11-30', 0, 0, 1, 'KH', '14.png'),
(30, 'testlan6', '$2a$12$1L.ptNS2HeHXFLYdebBt9eP4IzbIRQz8ZSQX0uoVGtdxt43LH0oBa', 'fag@gga.hhq', '2021-12-31 21:59:08', '112254646', '2021-12-15', 0, 0, 1, 'KH', '15.png'),
(31, 'testlan3', '$2a$12$1L.ptNS2HeHXFLYdebBt9eP4IzbIRQz8ZSQX0uoVGtdxt43LH0oBa', 'taga@ga.gaga', '2022-01-01 22:12:00', '5525255555', '2022-01-06', 0, 0, 1, 'KH', '16.png'),
(32, 'taikhoanmoi', '$2a$12$1L.ptNS2HeHXFLYdebBt9eP4IzbIRQz8ZSQX0uoVGtdxt43LH0oBa', 'hsg@gg.hhwttq', '2022-01-02 09:41:07', '25252525666', '2022-01-04', 0, 0, 1, 'KH', '17.png');

-- --------------------------------------------------------

--
-- Table structure for table `thong_bao`
--

CREATE TABLE `thong_bao` (
  `ma_tb` int NOT NULL,
  `id_nguoi_nhan` int NOT NULL,
  `id_nguoi_gui` int NOT NULL,
  `noi_dung` text NOT NULL,
  `ngay_tao` datetime NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `thong_bao`
--

INSERT INTO `thong_bao` (`ma_tb`, `id_nguoi_nhan`, `id_nguoi_gui`, `noi_dung`, `ngay_tao`, `status`) VALUES
(1, 4, 24, 'Vì \"nội dung\" nên bạn bị cảnh cáo và còn 2 lần cảnh cáo nữa sẽ khóa tài khoản', '2022-01-11 14:27:47', 1),
(4, 4, 7, 'Vì \"nội dung\" nên bạn bị nhắc nhở mà không tăng số lần cảnh cáo. Nếu tái phạm sẽ bị cảnh cáo', '2022-01-12 18:32:11', 1),
(5, 4, 5, 'abc', '2022-01-11 10:19:16', 1),
(6, 4, 10, '412412', '2022-01-11 10:19:34', 1),
(9, 7, 32, 'aaaaa', '2022-01-11 15:45:13', 1);

-- --------------------------------------------------------

--
-- Table structure for table `thong_tin_lien_he`
--

CREATE TABLE `thong_tin_lien_he` (
  `ma_khach_hang` int NOT NULL,
  `twitter_link` varchar(255) DEFAULT NULL,
  `facebook_link` varchar(255) DEFAULT NULL,
  `personal_link` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `thong_tin_lien_he`
--

INSERT INTO `thong_tin_lien_he` (`ma_khach_hang`, `twitter_link`, `facebook_link`, `personal_link`) VALUES
(21, '', '', ''),
(22, '', '', ''),
(23, '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `thu_phi`
--

CREATE TABLE `thu_phi` (
  `ma_khach_hang` int NOT NULL,
  `ngay_thanh_toan` datetime NOT NULL,
  `phi` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

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
  MODIFY `ma_anh` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `bao_cao_nguoi_dung`
--
ALTER TABLE `bao_cao_nguoi_dung`
  MODIFY `ma_bao_cao` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `danh_gia_san_pham`
--
ALTER TABLE `danh_gia_san_pham`
  MODIFY `ma_danh_gia` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `dat_hang`
--
ALTER TABLE `dat_hang`
  MODIFY `ma_dat_hang` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- AUTO_INCREMENT for table `khach_hang`
--
ALTER TABLE `khach_hang`
  MODIFY `ma_khach_hang` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `loai_san_pham`
--
ALTER TABLE `loai_san_pham`
  MODIFY `ma_loai_sp` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `log`
--
ALTER TABLE `log`
  MODIFY `id_log` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `phan_hoi_danh_gia_sp`
--
ALTER TABLE `phan_hoi_danh_gia_sp`
  MODIFY `ma_phan_hoi` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `tai_khoan`
--
ALTER TABLE `tai_khoan`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `thong_bao`
--
ALTER TABLE `thong_bao`
  MODIFY `ma_tb` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

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
