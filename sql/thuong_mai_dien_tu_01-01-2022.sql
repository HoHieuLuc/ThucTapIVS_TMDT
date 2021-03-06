-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 01, 2022 at 12:23 PM
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
(9, '35a99f29-64da-11ec-bb14-8378cfa7d63d', '1.jfif'),
(10, '35a99f29-64da-11ec-bb14-8378cfa7d63d', '2.jfif'),
(11, '35a99f29-64da-11ec-bb14-8378cfa7d63d', '3.jfif'),
(12, '35a99f29-64da-11ec-bb14-8378cfa7d63d', '9.png'),
(13, '35a99f29-64da-11ec-bb14-8378cfa7d63d', '10.png'),
(14, '5dfb7106-651f-11ec-b702-7845f2f0d96e', '11.png'),
(15, '5dfb7106-651f-11ec-b702-7845f2f0d96e', '12.png'),
(16, '5dfb7106-651f-11ec-b702-7845f2f0d96e', '13.png'),
(17, '5dfb7106-651f-11ec-b702-7845f2f0d96e', '14.png'),
(18, '5dfb7106-651f-11ec-b702-7845f2f0d96e', '15.png'),
(19, '130ea67a-6528-11ec-b702-7845f2f0d96e', '16.png'),
(20, '130ea67a-6528-11ec-b702-7845f2f0d96e', '17.png'),
(21, '130ea67a-6528-11ec-b702-7845f2f0d96e', '18.png'),
(22, '130ea67a-6528-11ec-b702-7845f2f0d96e', '19.png'),
(23, 'bc49e268-6528-11ec-b702-7845f2f0d96e', '20.png'),
(24, 'bc49e268-6528-11ec-b702-7845f2f0d96e', '21.png'),
(25, 'bc49e268-6528-11ec-b702-7845f2f0d96e', '22.png'),
(26, 'e4f55954-652b-11ec-b702-7845f2f0d96e', '30.png'),
(27, 'e4f55954-652b-11ec-b702-7845f2f0d96e', '29.png'),
(28, 'e4f55954-652b-11ec-b702-7845f2f0d96e', '28.png'),
(29, 'e4f55954-652b-11ec-b702-7845f2f0d96e', '27.png'),
(30, 'e4f55954-652b-11ec-b702-7845f2f0d96e', '31.jfif'),
(31, '1e717293-652c-11ec-b702-7845f2f0d96e', '26.png'),
(32, '1e717293-652c-11ec-b702-7845f2f0d96e', '25.png'),
(33, '1e717293-652c-11ec-b702-7845f2f0d96e', '24.png'),
(34, '1e717293-652c-11ec-b702-7845f2f0d96e', '23.png'),
(35, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', '1640962684887_KeqingWhatIsWrongWithYou.png'),
(36, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', '1640962684910_keqingTalk.png'),
(37, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', '1640962684924_keqingTalk2.png'),
(38, '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', '1640962684938_keqingthink.png');

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

-- --------------------------------------------------------

--
-- Table structure for table `chi_tiet_dat_hang`
--

CREATE TABLE `chi_tiet_dat_hang` (
  `ma_dat_hang` int(11) NOT NULL,
  `ma_san_pham` varchar(36) NOT NULL,
  `so_luong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
('giamdoc', 'Gi??m ?????c');

-- --------------------------------------------------------

--
-- Table structure for table `danh_gia_khach_hang`
--

CREATE TABLE `danh_gia_khach_hang` (
  `ma_danh_gia` int(11) NOT NULL,
  `ma_kh_danh_gia` int(11) NOT NULL,
  `ma_kh_duoc_danh_gia` int(11) NOT NULL,
  `noi_dung` text DEFAULT NULL,
  `so_sao` int(11) NOT NULL,
  `ngay_tao` datetime NOT NULL,
  `ngay_sua` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `danh_gia_khach_hang`
--

INSERT INTO `danh_gia_khach_hang` (`ma_danh_gia`, `ma_kh_danh_gia`, `ma_kh_duoc_danh_gia`, `noi_dung`, `so_sao`, `ngay_tao`, `ngay_sua`) VALUES
(2, 19, 6, 'r???t ok', 5, '2022-01-01 17:39:59', NULL),
(3, 19, 15, 'f', 4, '2022-01-01 17:50:43', NULL),
(4, 6, 15, 'dadad', 3, '2022-01-01 18:15:01', NULL);

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
(5, 3, 3, 'gsgsg', 'SP001', '2021-12-23 12:09:00', '2021-12-23 18:13:00'),
(6, 3, 3, 'gagag6262', 'test', '2021-12-23 14:12:18', '2021-12-23 06:15:31'),
(7, 2, 1, 'gagag', 'SP001', '2021-12-23 05:27:46', '2021-12-23 20:08:34'),
(8, 2, 1, 'f', 'SP001', '2021-12-23 08:20:48', '2021-12-27 09:46:16'),
(9, 15, 1, 'fafaf', '35a99f29-64da-11ec-bb14-8378cfa7d63d', '2021-12-25 14:38:19', '2021-12-25 08:44:26'),
(10, 6, 5, 'ok', '35a99f29-64da-11ec-bb14-8378cfa7d63d', '2021-12-25 10:22:48', '2022-01-01 18:15:37'),
(11, 6, 5, 'fafafaf', 'a862b5ce-64d6-11ec-bb14-8378cfa7d63d', '2021-12-25 07:41:22', '2021-12-27 11:16:47'),
(12, 6, 4, 'r???t ok', '5dfb7106-651f-11ec-b702-7845f2f0d96e', '2021-12-25 08:39:13', '2021-12-26 11:36:47'),
(13, 15, 1, 'adad', '5dfb7106-651f-11ec-b702-7845f2f0d96e', '2021-12-25 08:13:00', '2021-12-27 07:30:47'),
(14, 15, 1, 'alo thi???n ??i', 'a862b5ce-64d6-11ec-bb14-8378cfa7d63d', '2021-12-25 12:00:00', '2021-12-27 11:24:47'),
(15, 17, 4, '??? n??m nay tai th??? ng???n h??n r???i', '35a99f29-64da-11ec-bb14-8378cfa7d63d', '2021-12-25 05:08:00', '2021-12-27 11:18:47'),
(16, 15, 4, 'hello', '130ea67a-6528-11ec-b702-7845f2f0d96e', '2021-12-25 06:11:14', '2021-12-27 11:20:47'),
(17, 6, 5, '???ng h???', 'bc49e268-6528-11ec-b702-7845f2f0d96e', '2021-12-25 05:14:00', '2021-12-31 18:50:25'),
(18, 17, 5, 'M??n h??nh to th???t, xem phim gi???i tr?? th???t th??ch', 'bc49e268-6528-11ec-b702-7845f2f0d96e', '2021-12-25 10:23:17', '2021-12-27 06:12:47'),
(19, 6, 5, 'jgkhgkshggag\r\nfaf\r\nafa', '1e717293-652c-11ec-b702-7845f2f0d96e', '2021-12-25 06:26:37', '2021-12-31 21:57:03'),
(20, 19, 4, 'gsgsgdadfafaf', '1e717293-652c-11ec-b702-7845f2f0d96e', '2021-12-26 10:10:29', '2021-12-26 06:28:30'),
(21, 18, 5, '4214', '1e717293-652c-11ec-b702-7845f2f0d96e', '2021-12-27 03:08:41', '2021-12-27 03:08:41'),
(22, 1, 5, '1234', '130ea67a-6528-11ec-b702-7845f2f0d96e', '2021-12-27 06:16:39', '2021-12-28 08:26:34'),
(23, 1, 1, '9999999999999', '35a99f29-64da-11ec-bb14-8378cfa7d63d', '2021-12-27 14:32:18', '2021-12-27 14:33:16'),
(24, 1, 1, '41242w112212', 'test', '2021-12-28 08:05:52', '2021-12-28 08:05:52'),
(25, 1, 5, 'aa', '1e717293-652c-11ec-b702-7845f2f0d96e', '2021-12-28 08:13:54', '2021-12-28 08:14:04'),
(26, 1, 1, '412412', 'bc49e268-6528-11ec-b702-7845f2f0d96e', '2021-12-28 09:53:24', '2021-12-28 10:53:13'),
(27, 1, 1, '4214', 'e4f55954-652b-11ec-b702-7845f2f0d96e', '2021-12-28 12:11:30', '2021-12-28 12:11:30'),
(28, 21, 5, 'r???t\r\nl??\r\nok', '0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', '2021-12-31 21:59:29', '2021-12-31 21:59:29');

-- --------------------------------------------------------

--
-- Table structure for table `dat_hang`
--

CREATE TABLE `dat_hang` (
  `ma_dat_hang` int(11) NOT NULL,
  `ma_khach_hang` int(11) NOT NULL,
  `ngay_dat` datetime NOT NULL,
  `tong_tien` double NOT NULL,
  `tinh_trang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `gio_hang`
--

CREATE TABLE `gio_hang` (
  `ma_khach_hang` int(11) NOT NULL,
  `ma_san_pham` varchar(36) NOT NULL,
  `so_luong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
(1, 'L??m Minh Thi???n', '324 Minh M???ng, ???????ng 16/7,TX. Ninh H??a', 4, 0, '????y l?? gi???i thi???u c???a Store L??m Minh Thi???n'),
(2, 'H??? Hi???u L???c', 'Newyork', 5, 0, '????y l?? gi???i thi???u store H??? Hi???u L???c'),
(3, 'Nguy???n Ng???c V??', 'B?? R???a V??ng T??u', 7, 0, '????y l?? gi???i thi???u store c???a V??'),
(6, 'Quang Th??? B???o', 'Cam Ranh', 10, 0, '????y l?? gi???i thi???u store c???a Quang Th??? B???o'),
(8, 'Tr?????ng Gia Minh', 'Ninh Di??m', 13, 0, '????y l?? gi???i thi???u store c???a Tr?????ng Gia Minh'),
(9, 'H??? Ch??u Minh', 'Ninh Trung', 16, 0, '????y l?? gi???i thi???u c???a H??? Ch??u Minh'),
(10, 'gaga gagagag', 'fafafa', 18, 0, 'gsgsg'),
(11, 'fafafaf afa faf', 'dfafafaf', 19, 0, 'afafafa'),
(15, 'Nguy???n Th??? Test', 'gsgsgsg', 23, 0, 'ggggggggggggggg'),
(16, 'Kh??ch H??ng', 'asd', 24, 0, 'gg'),
(17, 'L??m Minh Thi???n', 'Ninh H??a, Kh??nh H??a, Vi???t Nam', 25, 0, 'Tui l??m ng?????i ??am m?? c??ng ngh???, v???c v???ch'),
(18, 'fafa', '', 26, 0, ''),
(19, 'John', 'a', 27, 0, 'a'),
(20, 'L??m Ch???n Khang', '', 29, 0, ''),
(21, 'ng?????i m???i', 'fafafa', 30, 0, 'ok ok o k\r\nok ok\r\nok');

-- --------------------------------------------------------

--
-- Table structure for table `loai_san_pham`
--

CREATE TABLE `loai_san_pham` (
  `ma_loai_sp` int(11) NOT NULL,
  `ten_loai_sp` varchar(155) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `loai_san_pham`
--

INSERT INTO `loai_san_pham` (`ma_loai_sp`, `ten_loai_sp`) VALUES
(6, '???m Th???c'),
(3, 'N???i Th???t'),
(9, 'Phim ???nh'),
(7, 'S??ch'),
(8, 'Th??? Thao'),
(2, 'Th???i Trang'),
(5, 'Th???c U???ng'),
(4, 'Xe C???'),
(1, '????? C??ng Ngh???');

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
('1test', 'H??? Hi???u L???c', 'giamdoc', 7, 100000000, 'fafafa');

-- --------------------------------------------------------

--
-- Table structure for table `phan_hoi_danh_gia_kh`
--

CREATE TABLE `phan_hoi_danh_gia_kh` (
  `ma_phan_hoi` int(11) NOT NULL,
  `ma_danh_gia` int(11) NOT NULL,
  `noi_dung` varchar(255) NOT NULL,
  `ma_khach_hang` int(11) NOT NULL,
  `ngay_tao` datetime NOT NULL,
  `ngay_sua` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Ph???n h???i ????nh gi?? s???n ph???m';

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
(11, 17, 'cf', 6, '2021-12-31 18:50:09', NULL),
(12, 17, 'ok\r\nok', 6, '2021-12-31 18:51:52', NULL),
(13, 25, 'ff', 6, '2021-12-31 20:12:32', NULL),
(14, 25, 'ffgaga', 6, '2021-12-31 20:12:35', NULL),
(15, 19, 'afaf', 6, '2021-12-31 21:08:34', NULL),
(16, 25, 'm???i n??', 6, '2021-12-31 21:13:57', NULL),
(17, 27, 'sorry', 6, '2021-12-31 21:31:45', NULL),
(18, 27, 'x???n m??', 6, '2021-12-31 21:38:17', NULL),
(19, 25, 'm???i m???i\r\nm???i\r\nm???i', 6, '2021-12-31 21:40:30', NULL),
(20, 28, 'ukm', 21, '2021-12-31 21:59:35', NULL),
(21, 16, 'gaag', 21, '2021-12-31 22:03:10', NULL),
(22, 10, 'ok', 6, '2022-01-01 18:15:25', NULL),
(23, 10, 'ukm', 6, '2022-01-01 18:15:29', NULL);

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
('KH', 'Kh??ch H??ng', 0);

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
('0eb9971b-6a4a-11ec-a83a-bbd4f44da03f', 6, 'Titanic', 'phim titanic', 500000, 0, 9, 15, '2021-12-31 21:58:04', 0),
('130ea67a-6528-11ec-b702-7845f2f0d96e', 15, 'Galaxy Bud', 'Tai nghe kh??ng d??y true wireless', 99999, 0, 1, 10, '2021-12-13 04:19:07', 0),
('1e717293-652c-11ec-b702-7845f2f0d96e', 6, '??o Kho??c TOTORA', '??o kho??c nh??n hi???u totora b???n ch???c', 33567, 0, 2, 12, '2021-12-23 03:12:11', 0),
('35a99f29-64da-11ec-bb14-8378cfa7d63d', 15, 'Sofa G??? Tr???m H????ng ', 'M?? t??? c???a Sofa G??? Tr???m H????ng ', 150000, 0, 3, 20, '2021-12-24 00:00:00', 0),
('5dfb7106-651f-11ec-b702-7845f2f0d96e', 16, 'TV Samsung', 'gshsshwt wjtklw jkwjgklwjgkwg', 63636363, 0, 4, 15, '2021-12-25 00:00:00', 0),
('a862b5ce-64d6-11ec-bb14-8378cfa7d63d', 15, 'test', 'test24252', 60000, 0, 5, 500, '2021-12-24 00:00:00', 0),
('bc49e268-6528-11ec-b702-7845f2f0d96e', 17, 'Galaxy S22', 'Chip exynos si??u m???nh', 31000000, 0, 6, 10, '2021-12-25 00:00:00', 0),
('e4f55954-652b-11ec-b702-7845f2f0d96e', 6, 'iphone 55', 'fafaf', 525252, 0, 7, 2626, '2021-12-25 00:00:00', 0),
('gggggg', 1, 'gagag6 252', 'gagagsgs 2 626', 262626, 0, 1, 10, '2021-12-24 00:00:00', 25),
('SP001', 15, 'Galaxy Note 21 Ultra', '??i???n tho???i samsung S21 ultra', 27000000, 0, 2, 20, '2021-01-13 00:00:00', 0),
('test', 15, 'tsgsgsg', 'gqgqgqgqgqgqg', 552525, 0, 3, 100000, '2021-12-21 00:00:00', 25);

-- --------------------------------------------------------

--
-- Table structure for table `san_pham_yeu_thich`
--

CREATE TABLE `san_pham_yeu_thich` (
  `ma_khach_hang` int(11) NOT NULL,
  `ma_san_pham` varchar(36) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
(4, 'thienlam782', '$2a$12$0ytCrPt6M1p42IqBuqQii.kQqAmboSY7.QuuoJLQLTSxNOVH.Dq3O', 'thien@gmail.com', '2021-12-21 00:00:00', '0828504336', '2021-12-09', 0, 0, 0, 'KH', '1.png'),
(5, 'provjpzz123', '$2a$12$pcX7tMe.BwfyWz2vNIdyf.ngTQ.wqqnNoC4O675i51Dv8SCfV4jVO', 'fafa.gagag@gmail.com', '2021-12-22 00:00:00', '62626262', '2021-12-21', 0, 0, 0, 'KH', '2.png'),
(7, 'provjpzz', '$2a$12$9qyfq7hBPbEa3YfPdsllCe61oYhON.PzOtn3KIOVyhRpP3A0psmaa', 'kingofika123@gmail.com', '2021-12-23 00:00:00', '5226262626', '2021-12-15', 0, 0, 0, 'admin', '3.png'),
(10, 'trantest', '$2a$12$4/poxN.Ahd5CFQeSS7x2b.Khl0ejAtoqBuMczDO59KHYKI4dkIpKu', 'x@x.x', '2021-12-23 00:00:00', '0987654321', '2021-12-23', 1, 0, 0, 'KH', '4.png'),
(12, 'testtest', '$2a$12$O4zi/e0dJbCJjiNZdkQDJuAkhQp/81WufqKJn1ZRo6Ul1XWPt4BHK', 'ffff@fff.ff', '2021-12-23 00:00:00', '515152525', '1245-11-11', 1, 0, 0, 'KH', '5.png'),
(13, 'hohieuluc', '$2a$12$C4x/jGEWoAXEdQxdqMRwj.dG0nn5Dhz0Lc6frI1GOt8lRSXEPotYe', '525ffa@ga.q', '2021-12-23 00:00:00', '636372726', '2021-12-08', 0, 0, 0, 'KH', '6.png'),
(16, 'testtran', '$2a$12$nhPsO973ByyDXC0e.eG3oeiRotUEpP5.dbekeFVVTXodG2OoQO3J6', 'ggggg@ggg.ggg', '2021-12-23 00:00:00', '6374848363', '2021-12-15', 1, 0, 1, 'KH', '7.png'),
(18, 'test1234', '$2a$12$LWYhyR2dOJkU6IsmNhCpOu0Fp83shjJb9mzKKKxTBzW83Qp1D7dIa', '52ddad5ffa@ga.q', '2021-12-24 00:00:00', '5252626262', '2021-12-15', 1, 0, 1, 'KH', '9.png'),
(19, 'testtest123', '$2a$12$1jXwSOWpuWe8EDYpU..v.eHnCU2YuCZyrybF3.NT6xIVGNG/OjCmy', '5hfhfha@ghaaf.fafaf', '2021-12-24 00:00:00', '526377262', '2021-12-08', 1, 0, 1, 'KH', '8.png'),
(23, 'nguyenthitest', '$2a$12$NBx/wl9nSHDnjZsmmIbAHeawvhPheebQRYv9nQNPb8W0Q5OIy7q0G', 'gakgag@gahgag.agag', '2021-12-24 00:00:00', '526372626', '2021-12-01', 0, 0, 1, 'KH', '10.png'),
(24, 'khtest123', '$2a$12$XPUUHOwCscgVCm.pp.a/y.RFL5FdzpGeNjKC5bkGxtFndyXr3e4iK', 'khtest@ktktkkttk.ttqjtkqkt', '2021-12-25 00:00:00', '74736373737', '2021-12-16', 0, 0, 1, 'KH', '11.png'),
(25, 'minhthienmap', '$2a$12$3u/NmSw0RJPRSei42MSGse5BlysA3QheQO5g2u0yyy5tjBuxSobdS', 'lamminhthien02012000@gmail.com', '2021-12-25 00:00:00', '08285043336', '2006-02-14', 0, 0, 1, 'KH', 'kequing.png'),
(26, 'fafafafafaffa', '$2a$12$6yYDlJwABf06FuvuOYGDfeKId8mWPCtwHvUVKTs7H2to.nKDyhvf2', 'gagaggg@gg.a.gag', '2021-12-25 00:00:00', '525252525', '2021-12-10', 0, 0, 1, 'KH', '12.png'),
(27, 'johntest', '$2a$12$0BzcLjSzSy3D0I21rpJsJ.nrV7oaEG52UiMfVRRKfThuXVvRxaADC', 'johntest2252@s.g.s.g.s', '2021-12-26 00:00:00', '5555252525', '2021-12-15', 0, 0, 1, 'KH', '13.png'),
(29, 'thienlam1100', '$2a$12$5fHgrx2sMGH3Pd8slw54v.3p0syEfAKWa6QX6rXIPofVr0rDWxhMi', 'thien1100@gmail.com', '2021-12-27 11:36:24', '08285043336', '2021-11-30', 0, 0, 1, 'KH', '1640579784993_Screenshot (57).png'),
(30, 'testlan6', '$2a$12$dkie4QtgsGUQPb4iPpB3audIjfiIXqmSMwquJz19tbDHf2AGzLlXm', 'fag@gga.hhq', '2021-12-31 21:59:08', '112254646', '2021-12-15', 0, 0, 1, 'KH', '1640962748589_love.png');

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
(21, '', '', '');

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
-- Indexes for table `danh_gia_khach_hang`
--
ALTER TABLE `danh_gia_khach_hang`
  ADD PRIMARY KEY (`ma_danh_gia`),
  ADD KEY `dgkh_ibfk_1_idx` (`ma_kh_danh_gia`),
  ADD KEY `dgkh_ibfk_2_idx` (`ma_kh_duoc_danh_gia`);

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
  ADD UNIQUE KEY `ten_loai_sp_UNIQUE` (`ten_loai_sp`);

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
-- Indexes for table `phan_hoi_danh_gia_kh`
--
ALTER TABLE `phan_hoi_danh_gia_kh`
  ADD PRIMARY KEY (`ma_phan_hoi`),
  ADD KEY `phdgkh_fk_1` (`ma_khach_hang`),
  ADD KEY `phdgkh_fk_3` (`ma_danh_gia`);

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
  MODIFY `ma_anh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `bao_cao_nguoi_dung`
--
ALTER TABLE `bao_cao_nguoi_dung`
  MODIFY `ma_bao_cao` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `danh_gia_khach_hang`
--
ALTER TABLE `danh_gia_khach_hang`
  MODIFY `ma_danh_gia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `danh_gia_san_pham`
--
ALTER TABLE `danh_gia_san_pham`
  MODIFY `ma_danh_gia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `dat_hang`
--
ALTER TABLE `dat_hang`
  MODIFY `ma_dat_hang` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `khach_hang`
--
ALTER TABLE `khach_hang`
  MODIFY `ma_khach_hang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `loai_san_pham`
--
ALTER TABLE `loai_san_pham`
  MODIFY `ma_loai_sp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `log`
--
ALTER TABLE `log`
  MODIFY `id_log` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `phan_hoi_danh_gia_kh`
--
ALTER TABLE `phan_hoi_danh_gia_kh`
  MODIFY `ma_phan_hoi` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `phan_hoi_danh_gia_sp`
--
ALTER TABLE `phan_hoi_danh_gia_sp`
  MODIFY `ma_phan_hoi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `tai_khoan`
--
ALTER TABLE `tai_khoan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `thong_bao`
--
ALTER TABLE `thong_bao`
  MODIFY `ma_tb` int(11) NOT NULL AUTO_INCREMENT;

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
-- Constraints for table `danh_gia_khach_hang`
--
ALTER TABLE `danh_gia_khach_hang`
  ADD CONSTRAINT `dgkh_ibfk_1` FOREIGN KEY (`ma_kh_danh_gia`) REFERENCES `khach_hang` (`ma_khach_hang`),
  ADD CONSTRAINT `dgkh_ibfk_2` FOREIGN KEY (`ma_kh_duoc_danh_gia`) REFERENCES `khach_hang` (`ma_khach_hang`);

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
-- Constraints for table `phan_hoi_danh_gia_kh`
--
ALTER TABLE `phan_hoi_danh_gia_kh`
  ADD CONSTRAINT `phdgkh_fk_1` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`ma_khach_hang`) ON DELETE CASCADE,
  ADD CONSTRAINT `phdgkh_fk_2` FOREIGN KEY (`ma_danh_gia`) REFERENCES `danh_gia_khach_hang` (`ma_danh_gia`) ON DELETE CASCADE,
  ADD CONSTRAINT `phdgkh_fk_3` FOREIGN KEY (`ma_danh_gia`) REFERENCES `danh_gia_khach_hang` (`ma_danh_gia`) ON DELETE CASCADE;

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
