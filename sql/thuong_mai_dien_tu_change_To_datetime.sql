-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 27, 2021 at 01:06 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.0.13

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
(1, 'SP001', 'test.jpg'),
(2, 'SP001', '131.jpg'),
(3, 'test', 'gsgsgs.jpg'),
(4, 'test', 'aaa.jpg'),
(5, 'a862b5ce-64d6-11ec-bb14-8378cfa7d63d', '1640363365336_keqcall.png'),
(6, 'a862b5ce-64d6-11ec-bb14-8378cfa7d63d', '1640363365359_keqpain.png'),
(7, 'a862b5ce-64d6-11ec-bb14-8378cfa7d63d', '1640363365376_keqparty.png'),
(8, 'a862b5ce-64d6-11ec-bb14-8378cfa7d63d', '1640363365390_keqPat.png'),
(9, '35a99f29-64da-11ec-bb14-8378cfa7d63d', '1640364890769_goodjob.png'),
(10, '35a99f29-64da-11ec-bb14-8378cfa7d63d', '1640364890789_love.png'),
(11, '35a99f29-64da-11ec-bb14-8378cfa7d63d', '1640364890798_meplease.png'),
(12, '35a99f29-64da-11ec-bb14-8378cfa7d63d', '1640364890808_myKeqing.png'),
(13, '35a99f29-64da-11ec-bb14-8378cfa7d63d', '1640364890817_notlikekeq.png'),
(14, '5dfb7106-651f-11ec-b702-7845f2f0d96e', '1640394593687_keqdesk.png'),
(15, '5dfb7106-651f-11ec-b702-7845f2f0d96e', '1640394593698_love.png'),
(16, '5dfb7106-651f-11ec-b702-7845f2f0d96e', '1640394593710_meplease.png'),
(17, '5dfb7106-651f-11ec-b702-7845f2f0d96e', '1640394593723_myKeqing.png'),
(18, '5dfb7106-651f-11ec-b702-7845f2f0d96e', '1640394593738_notlikekeq.png'),
(19, '130ea67a-6528-11ec-b702-7845f2f0d96e', '1640398333455_1anemo.png'),
(20, '130ea67a-6528-11ec-b702-7845f2f0d96e', '1640398333469_1dendro.png'),
(21, '130ea67a-6528-11ec-b702-7845f2f0d96e', '1640398333481_1electro.png'),
(22, '130ea67a-6528-11ec-b702-7845f2f0d96e', '1640398333489_1geo.png'),
(23, 'bc49e268-6528-11ec-b702-7845f2f0d96e', '1640398617375_ảnh s22.png'),
(24, 'bc49e268-6528-11ec-b702-7845f2f0d96e', '1640398617390_hình 1.jfif'),
(25, 'bc49e268-6528-11ec-b702-7845f2f0d96e', '1640398617403_hình 2.jpg'),
(26, 'e4f55954-652b-11ec-b702-7845f2f0d96e', '1640399974096_adminabooz.png'),
(27, 'e4f55954-652b-11ec-b702-7845f2f0d96e', '1640399974109_adminabooz.png'),
(28, 'e4f55954-652b-11ec-b702-7845f2f0d96e', '1640399974117_banhammer.png'),
(29, 'e4f55954-652b-11ec-b702-7845f2f0d96e', '1640399974131_blacksword.png'),
(30, 'e4f55954-652b-11ec-b702-7845f2f0d96e', '1640399974143_chadoug.png'),
(31, '1e717293-652c-11ec-b702-7845f2f0d96e', '1640400070538_crown_of_insight.png'),
(32, '1e717293-652c-11ec-b702-7845f2f0d96e', '1640400070550_crystal.png'),
(33, '1e717293-652c-11ec-b702-7845f2f0d96e', '1640400070561_deletethat.png');

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
('giamdoc', 'Giám đốc');

-- --------------------------------------------------------

--
-- Table structure for table `danh_gia_khach_hang`
--

CREATE TABLE `danh_gia_khach_hang` (
  `ma_danh_gia` int(11) NOT NULL,
  `ma_kh_danh_gia` int(11) NOT NULL,
  `ma_kh_duoc_danh_gia` int(11) NOT NULL,
  `so_sao` int(11) NOT NULL,
  `ngay_tao` datetime NOT NULL,
  `ngay_sua` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
(1, 1, 5, 'Oh My God, That is so cold', 'SP001', '2021-12-22 00:00:00', '2021-12-22 00:00:00'),
(5, 3, 3, 'gsgsg', 'SP001', '2021-12-23 00:00:00', '2021-12-23 00:00:00'),
(6, 3, 3, 'gagag6262', 'test', '2021-12-23 00:00:00', '2021-12-23 00:00:00'),
(7, 2, 1, 'gagag', 'SP001', '2021-12-23 00:00:00', '2021-12-23 00:00:00'),
(8, 2, 1, 'f', 'SP001', '2021-12-23 00:00:00', '2021-12-23 00:00:00'),
(9, 15, 1, 'fafaf', '35a99f29-64da-11ec-bb14-8378cfa7d63d', '2021-12-25 00:00:00', '2021-12-25 00:00:00'),
(10, 6, 3, 'ok', '35a99f29-64da-11ec-bb14-8378cfa7d63d', '2021-12-25 00:00:00', NULL),
(11, 6, 5, 'fafafaf', 'a862b5ce-64d6-11ec-bb14-8378cfa7d63d', '2021-12-25 00:00:00', NULL),
(12, 6, 4, 'rất ok', '5dfb7106-651f-11ec-b702-7845f2f0d96e', '2021-12-25 00:00:00', NULL),
(13, 15, 1, 'adad', '5dfb7106-651f-11ec-b702-7845f2f0d96e', '2021-12-25 00:00:00', NULL),
(14, 15, 1, 'alo thiện ơi', 'a862b5ce-64d6-11ec-bb14-8378cfa7d63d', '2021-12-25 00:00:00', NULL),
(15, 17, 4, 'ồ năm nay tai thỏ ngắn hơn rồi', '35a99f29-64da-11ec-bb14-8378cfa7d63d', '2021-12-25 00:00:00', NULL),
(16, 15, 4, 'hello', '130ea67a-6528-11ec-b702-7845f2f0d96e', '2021-12-25 00:00:00', NULL),
(17, 6, 5, 'ủng hộ', 'bc49e268-6528-11ec-b702-7845f2f0d96e', '2021-12-25 00:00:00', NULL),
(18, 17, 5, 'Màn hình to thật, xem phim giải trí thật thích', 'bc49e268-6528-11ec-b702-7845f2f0d96e', '2021-12-25 00:00:00', NULL),
(19, 6, 1, 'jgkhgkshggag', '1e717293-652c-11ec-b702-7845f2f0d96e', '2021-12-25 00:00:00', NULL),
(20, 19, 4, 'gsgsgdadfafaf', '1e717293-652c-11ec-b702-7845f2f0d96e', '2021-12-26 00:00:00', '2021-12-26 00:00:00');

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
(1, 'Lâm Minh Thiện', '421412', 4, 0, '412421'),
(2, 'gagagafafafafaf', 'gsgag', 5, 0, 'gagagaga'),
(3, 'gagag gagagag', 'fafaf', 7, 0, 'agagagag'),
(6, 'Hồ Hiểu Lực', 'fafaf g252', 10, 0, 'Viết giới thiệu về bản thân'),
(8, 'fafgsg gsgsg', '', 13, 0, 'gsgsg'),
(9, 'Nguyễn Test', 'fafaf g252', 16, 0, 'babaj sgg\r\ngjajgaga\r\ngakgajga\r\nggag'),
(10, 'gaga gagagag', 'fafafa', 18, 0, 'gsgsg'),
(11, 'fafafaf afa faf', 'dfafafaf', 19, 0, 'afafafa'),
(15, 'Nguyễn Thị Test', 'gsgsgsg', 23, 0, 'ggggggggggggggg'),
(16, 'Khách Hàng', 'asd', 24, 0, 'gg'),
(17, 'Lâm Minh Thiện', 'Ninh Hòa, Khánh Hòa, Việt Nam', 25, 0, 'Tui làm người đam mê công nghệ, vọc vạch'),
(18, 'fafa', '', 26, 0, ''),
(19, 'John', 'a', 27, 0, 'a');

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
(1, 'Điện thoại');

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
('1test', 'Hồ Hiểu Lực', 'giamdoc', 7, 100000000, 'fafafa');

-- --------------------------------------------------------

--
-- Table structure for table `phan_hoi_danh_gia`
--

CREATE TABLE `phan_hoi_danh_gia` (
  `ma_danh_gia` int(11) NOT NULL,
  `noi_dung` varchar(255) NOT NULL,
  `ma_khach_hang` int(11) NOT NULL,
  `ngay_tao` datetime NOT NULL,
  `ngay_sua` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
('130ea67a-6528-11ec-b702-7845f2f0d96e', 15, 'Alo alo alo', 'fffffffffffffffffffff', 252525, 0, 1, 62, '2021-12-25 00:00:00', 0),
('1e717293-652c-11ec-b702-7845f2f0d96e', 6, 'new', 'new', 1111, 0, 1, 11111, '2021-12-25 00:00:00', 0),
('35a99f29-64da-11ec-bb14-8378cfa7d63d', 15, 'Iphone 13', 'Iphone 13 rất xịn rất ngon rất mạnh\r\nok?', 150000, 0, 1, 20, '2021-12-24 00:00:00', 0),
('5dfb7106-651f-11ec-b702-7845f2f0d96e', 16, 'TV Samsung', 'gshsshwt wjtklw jkwjgklwjgkwg', 63636363, 0, 1, 15, '2021-12-25 00:00:00', 0),
('a862b5ce-64d6-11ec-bb14-8378cfa7d63d', 15, 'test', 'test24252', 60000, 0, 1, 500, '2021-12-24 00:00:00', 0),
('bc49e268-6528-11ec-b702-7845f2f0d96e', 17, 'Galaxy S22', 'Chip exynos siêu mạnh', 31000000, 0, 1, 10, '2021-12-25 00:00:00', 0),
('e4f55954-652b-11ec-b702-7845f2f0d96e', 6, 'iphone 55', 'fafaf', 525252, 0, 1, 2626, '2021-12-25 00:00:00', 0),
('gggggg', 1, 'gagag6 252', 'gagagsgs 2 626', 262626, 0, 1, 10, '2021-12-24 00:00:00', 25),
('SP001', 15, 'Galaxy Note 21 Ultra', 'Điện thoại samsung S21 ultra', 27000000, 0, 1, 20, '2021-01-13 00:00:00', 0),
('test', 15, 'tsgsgsg', 'gqgqgqgqgqgqg', 552525, 0, 1, 100000, '2021-12-21 00:00:00', 25);

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
(4, 'thienlam782', '$2a$12$0ytCrPt6M1p42IqBuqQii.kQqAmboSY7.QuuoJLQLTSxNOVH.Dq3O', 'thien@gmail.com', '2021-12-21', '0828504336', '2021-12-09', 0, 0, 0, 'KH', ''),
(5, 'provjpzz123', '$2a$12$pcX7tMe.BwfyWz2vNIdyf.ngTQ.wqqnNoC4O675i51Dv8SCfV4jVO', 'fafa.gagag@gmail.com', '2021-12-22', '62626262', '2021-12-21', 0, 0, 0, 'KH', 'null'),
(7, 'provjpzz', '$2a$12$9qyfq7hBPbEa3YfPdsllCe61oYhON.PzOtn3KIOVyhRpP3A0psmaa', 'kingofika123@gmail.com', '2021-12-23', '5226262626', '2021-12-15', 0, 0, 0, 'admin', 'null'),
(10, 'trantest', '$2a$12$4/poxN.Ahd5CFQeSS7x2b.Khl0ejAtoqBuMczDO59KHYKI4dkIpKu', 'x@x.x', '2021-12-23', '0987654321', '2021-12-23', 1, 0, 0, 'KH', 'null'),
(12, 'testtest', '$2a$12$O4zi/e0dJbCJjiNZdkQDJuAkhQp/81WufqKJn1ZRo6Ul1XWPt4BHK', 'ffff@fff.ff', '2021-12-23', '515152525', '1245-11-11', 1, 0, 0, 'KH', 'null'),
(13, 'hohieuluc', '$2a$12$C4x/jGEWoAXEdQxdqMRwj.dG0nn5Dhz0Lc6frI1GOt8lRSXEPotYe', '525ffa@ga.q', '2021-12-23', '636372726', '2021-12-08', 0, 0, 0, 'KH', 'null'),
(16, 'testtran', '$2a$12$nhPsO973ByyDXC0e.eG3oeiRotUEpP5.dbekeFVVTXodG2OoQO3J6', 'ggggg@ggg.ggg', '2021-12-23', '6374848363', '2021-12-15', 1, 0, 1, 'KH', 'null'),
(18, 'test1234', '$2a$12$LWYhyR2dOJkU6IsmNhCpOu0Fp83shjJb9mzKKKxTBzW83Qp1D7dIa', '52ddad5ffa@ga.q', '2021-12-24', '5252626262', '2021-12-15', 1, 0, 1, 'KH', 'keqcopium.png'),
(19, 'testtest123', '$2a$12$1jXwSOWpuWe8EDYpU..v.eHnCU2YuCZyrybF3.NT6xIVGNG/OjCmy', '5hfhfha@ghaaf.fafaf', '2021-12-24', '526377262', '2021-12-08', 1, 0, 1, 'KH', 'keqhmm.png'),
(23, 'nguyenthitest', '$2a$12$NBx/wl9nSHDnjZsmmIbAHeawvhPheebQRYv9nQNPb8W0Q5OIy7q0G', 'gakgag@gahgag.agag', '2021-12-24', '526372626', '2021-12-01', 0, 0, 1, 'KH', '1640340243592_keqBorgar.png'),
(24, 'khtest123', '$2a$12$XPUUHOwCscgVCm.pp.a/y.RFL5FdzpGeNjKC5bkGxtFndyXr3e4iK', 'khtest@ktktkkttk.ttqjtkqkt', '2021-12-25', '74736373737', '2021-12-16', 0, 0, 1, 'KH', '1640394521424_keqingSmug.jpg'),
(25, 'minhthienmap', '$2a$12$3u/NmSw0RJPRSei42MSGse5BlysA3QheQO5g2u0yyy5tjBuxSobdS', 'lamminhthien02012000@gmail.com', '2021-12-25', '08285043336', '2006-02-14', 0, 0, 1, 'KH', '1640398277859_ganyu-voi-keqing-genshin-impact-tro-choi-dien-tu-anime-hinh-nen-2800x2100_28.jpg'),
(26, 'fafafafafaffa', '$2a$12$6yYDlJwABf06FuvuOYGDfeKId8mWPCtwHvUVKTs7H2to.nKDyhvf2', 'gagaggg@gg.a.gag', '2021-12-25', '525252525', '2021-12-10', 0, 0, 1, 'KH', '1640401090469_crystal.png'),
(27, 'johntest', '$2a$12$0BzcLjSzSy3D0I21rpJsJ.nrV7oaEG52UiMfVRRKfThuXVvRxaADC', 'johntest2252@s.g.s.g.s', '2021-12-26', '5555252525', '2021-12-15', 0, 0, 1, 'KH', '1640505602458_FNA6INk.png');

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
-- Indexes for table `phan_hoi_danh_gia`
--
ALTER TABLE `phan_hoi_danh_gia`
  ADD PRIMARY KEY (`ma_danh_gia`),
  ADD KEY `phdg_fk_1_idx` (`ma_khach_hang`);

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
  MODIFY `ma_anh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `bao_cao_nguoi_dung`
--
ALTER TABLE `bao_cao_nguoi_dung`
  MODIFY `ma_bao_cao` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `danh_gia_khach_hang`
--
ALTER TABLE `danh_gia_khach_hang`
  MODIFY `ma_danh_gia` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `danh_gia_san_pham`
--
ALTER TABLE `danh_gia_san_pham`
  MODIFY `ma_danh_gia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `dat_hang`
--
ALTER TABLE `dat_hang`
  MODIFY `ma_dat_hang` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `khach_hang`
--
ALTER TABLE `khach_hang`
  MODIFY `ma_khach_hang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `loai_san_pham`
--
ALTER TABLE `loai_san_pham`
  MODIFY `ma_loai_sp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `log`
--
ALTER TABLE `log`
  MODIFY `id_log` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tai_khoan`
--
ALTER TABLE `tai_khoan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

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
-- Constraints for table `phan_hoi_danh_gia`
--
ALTER TABLE `phan_hoi_danh_gia`
  ADD CONSTRAINT `phdg_fk_1` FOREIGN KEY (`ma_khach_hang`) REFERENCES `khach_hang` (`ma_khach_hang`),
  ADD CONSTRAINT `phdg_fk_2` FOREIGN KEY (`ma_danh_gia`) REFERENCES `danh_gia_khach_hang` (`ma_danh_gia`) ON DELETE CASCADE,
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
