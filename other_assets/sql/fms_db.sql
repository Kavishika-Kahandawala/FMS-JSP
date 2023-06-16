-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 07, 2023 at 04:05 AM
-- Server version: 8.0.31
-- PHP Version: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fms_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `fd_acc`
--

DROP TABLE IF EXISTS `fd_acc`;
CREATE TABLE IF NOT EXISTS `fd_acc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `account_id` int NOT NULL,
  `value` float NOT NULL,
  `created_date` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_time` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fd_time` int NOT NULL,
  `fd_interest_rate` double NOT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `fd_acc`
--

INSERT INTO `fd_acc` (`id`, `username`, `account_id`, `value`, `created_date`, `created_time`, `fd_time`, `fd_interest_rate`, `status`) VALUES
(3, 'kavishika', 91839838, 87867, '2023-05-27', '23:11:03', 24, 12, 1),
(41, 'kavishika', 18374316, 4545, '2023-05-28', '10:56:48', 12, 10, 1),
(40, 'kavishika', 92597821, 23125, '2023-05-28', '10:48:32', 12, 10, 1),
(39, 'kavishika', 94874848, 456486, '2023-05-28', '10:14:06', 24, 12, 1),
(38, 'kavishika', 52048982, 4545, '2023-05-28', '10:04:14', 12, 10, 1),
(32, 'kavishika', 13949794, 4545, '2023-05-28', '00:35:48', 24, 12, 1),
(31, 'kavishika', 70386224, 45455, '2023-05-28', '00:29:32', 36, 14, 1),
(42, 'kavishika', 75420512, 454554, '2020-05-28', '11:05:15', 24, 12, 1),
(43, 'kavishika', 80297453, 546545, '2023-05-28', '11:07:11', 12, 10, 1);

-- --------------------------------------------------------

--
-- Table structure for table `loan_acc`
--

DROP TABLE IF EXISTS `loan_acc`;
CREATE TABLE IF NOT EXISTS `loan_acc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `account_id` int NOT NULL,
  `value` float NOT NULL,
  `created_date` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_time` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `loan_time` int NOT NULL,
  `loan_interest_rate` double NOT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `loan_acc`
--

INSERT INTO `loan_acc` (`id`, `username`, `account_id`, `value`, `created_date`, `created_time`, `loan_time`, `loan_interest_rate`, `status`) VALUES
(3, 'kavishika', 10506663, 1212, '2023-05-27', '23:10:51', 12, 8, 1),
(4, 'kavishika', 18060684, 11221, '2023-05-28', '01:06:52', 12, 8, 1),
(5, 'kavishika', 31515352, 4545, '2023-05-28', '01:08:28', 12, 8, 1),
(6, 'kavishika', 22865788, 54646, '2023-04-28', '10:19:16', 24, 10, 1),
(7, 'kavishika', 43731189, 54545, '2023-05-28', '10:44:38', 99, 99, 0),
(8, 'kavishika', 64707077, 42343, '2023-05-28', '10:49:35', 12, 8, 1),
(9, 'kavishika', 79807406, 54656, '2023-05-28', '11:05:33', 36, 12, 1),
(10, 'kavishika', 69817306, 1000, '2023-05-28', '11:05:33', 12, 12, 1),
(11, 'kavishika', 84908234, 12, '2023-05-27', '13:20:00', 3, 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `loan_repayment`
--

DROP TABLE IF EXISTS `loan_repayment`;
CREATE TABLE IF NOT EXISTS `loan_repayment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `account_id` int NOT NULL,
  `value` float NOT NULL,
  `date` varchar(15) NOT NULL,
  `time` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `loan_repayment`
--

INSERT INTO `loan_repayment` (`id`, `username`, `account_id`, `value`, `date`, `time`) VALUES
(1, 'kavishika', 69817306, 2, '2023-05-26', '22:28:02'),
(2, 'kavishika', 69817306, 100, '2023-05-26', '22:28:33'),
(3, 'kavishika', 69817306, 1000, '2023-05-26', '22:28:59'),
(4, 'kavishika', 79807406, 10000, '2023-05-26', '23:47:52');

-- --------------------------------------------------------

--
-- Table structure for table `rates`
--

DROP TABLE IF EXISTS `rates`;
CREATE TABLE IF NOT EXISTS `rates` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_type` varchar(10) NOT NULL,
  `time` int NOT NULL,
  `rate` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `rates`
--

INSERT INTO `rates` (`id`, `account_type`, `time`, `rate`) VALUES
(1, 'savings', 0, 1.5),
(2, 'fd', 3, 8),
(3, 'fd', 6, 9),
(4, 'fd', 12, 10),
(5, 'fd', 24, 12),
(6, 'fd', 36, 14),
(7, 'loan', 3, 6),
(8, 'loan', 6, 7),
(9, 'loan', 12, 8),
(10, 'loan', 24, 10),
(11, 'loan', 36, 12);

-- --------------------------------------------------------

--
-- Table structure for table `saving_acc`
--

DROP TABLE IF EXISTS `saving_acc`;
CREATE TABLE IF NOT EXISTS `saving_acc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `account_id` int NOT NULL,
  `value` float NOT NULL,
  `created_date` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_time` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `saving_acc`
--

INSERT INTO `saving_acc` (`id`, `username`, `account_id`, `value`, `created_date`, `created_time`, `status`) VALUES
(2, 'kavishika', 50561624, 64, '2023-05-27', '23:11:20', 1),
(3, 'kavishika', 86300243, 4545, '2023-05-27', '23:45:48', 1);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
CREATE TABLE IF NOT EXISTS `transactions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `account_id` int NOT NULL,
  `type` varchar(11) NOT NULL,
  `value` float NOT NULL,
  `date` varchar(15) NOT NULL,
  `time` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`id`, `username`, `account_id`, `type`, `value`, `date`, `time`) VALUES
(1, 'kavishika', 50561624, 'withdraw', 12, '2023-05-28', '17:13:07'),
(2, 'kavishika', 75420512, 'fd-withdraw', 168185, '2023-05-28', '17:44:30');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `uid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=MyISAM AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`uid`, `username`, `full_name`, `password`, `email`) VALUES
(35, 'jason', 'jason mikes', '$2a$10$pBK4W2X1FDuB1hWstjhrJuKb/xczRhTpfaCJ122N.hlpm1gUhxrb6', 'jasonm@gmail.com'),
(38, 'chathura', 'chathura alvis', '$2a$10$NimQYzz2zmUYXvSYo8KeGub7qf48/zibmaAnRxyb//8ImYY1IoyUW', 'chathura1234@gmail.com'),
(39, 'paul', 'paul willson', '$2a$10$dov/XytALMVW2Edw0SvP8OFKfEmdxD.B1X.Qow8h3.LXYUDM/j8HC', 'paul@hotmail.com'),
(34, 'nayana', 'nayana samaraweera', '$2a$10$jMuEnKv7GnIoTp/C6hrHn.ApqMAN3L87R/vGbBVQm6TLZ3LS8OyHm', 'nayana@gmail.com'),
(36, 'upul', 'upul karunathilake', '$2a$10$5e5y8fPGHoG76C8veUgQxeVYWiMsvTpLdmjtBqueqRA6ABADLJWGS', 'upul@gmail.com'),
(33, 'Harsha', 'harsha karunarathne', '$2a$10$dLAOwVxM6XGRDfBc7Og8ROoeIenS.4JDXfd2IYyuM43.AvmPCi9Ra', 'harsha@gmail.com'),
(30, 'kasun', 'kasun karunathilake', '$2a$10$ESblWbBTsS4npDgcQdpN8e7mKSA8DZMeHL/IwNHI/6GVTk.9NadAW', 'kasun@gmail.com'),
(31, 'kavishika', 'kavishika kahandawala', '$2a$10$MesCMsV1HhCAMFzrlGAn7.gsaZddD4bXMvoqYKJ8Tm3hwCWNWjqpi', 'kavishika@gmail.com');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
