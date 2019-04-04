-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 04, 2019 at 04:16 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lobe_tool`
--

-- --------------------------------------------------------

--
-- Table structure for table `learning_object`
--

CREATE TABLE `learning_object` (
  `code` varchar(200) NOT NULL,
  `chapter` varchar(100) NOT NULL,
  `created_ts` datetime(6) NOT NULL,
  `grade` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `repository_name` varchar(100) NOT NULL,
  `status` varchar(20) NOT NULL,
  `subject` varchar(100) NOT NULL,
  `updated_ts` datetime(6) NOT NULL,
  `rubrik_id` int(11) NOT NULL,
  `assigned_by` varchar(100) NOT NULL,
  `assigned_to` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `lobe_scores`
--

CREATE TABLE `lobe_scores` (
  `score` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `learning_object_id` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `permission`
--

CREATE TABLE `permission` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `permission`
--

INSERT INTO `permission` (`id`, `name`) VALUES
(1, 'assign_rubrik'),
(2, 'create_rubrik'),
(3, 'evaluate_rubrik'),
(4, 'evaluator_analytics'),
(5, 'generator_analytics');

-- --------------------------------------------------------

--
-- Table structure for table `quality_dimension_master`
--

CREATE TABLE `quality_dimension_master` (
  `id` int(11) NOT NULL,
  `dimension_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `quality_dimension_master`
--

INSERT INTO `quality_dimension_master` (`id`, `dimension_name`) VALUES
(1, 'Content Quality'),
(2, 'Pedagogical Alignment'),
(3, 'Design Efficacy'),
(4, 'Technological Integration');

-- --------------------------------------------------------

--
-- Table structure for table `question_master`
--

CREATE TABLE `question_master` (
  `id` int(11) NOT NULL,
  `optional` bit(1) NOT NULL,
  `quality_dimension_id` int(11) NOT NULL,
  `question` varchar(100) NOT NULL,
  `question_meta` varchar(1000) NOT NULL,
  `rubrik_type` int(11) NOT NULL,
  `score_0` varchar(1000) NOT NULL,
  `score_0_images` varchar(1000) NOT NULL,
  `score_1` varchar(1000) NOT NULL,
  `score_1_images` varchar(1000) NOT NULL,
  `score_2` varchar(1000) NOT NULL,
  `score_2_images` varchar(1000) NOT NULL,
  `score_3` varchar(1000) NOT NULL,
  `score_3_images` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `question_master`
--

INSERT INTO `question_master` (`id`, `optional`, `quality_dimension_id`, `question`, `question_meta`, `rubrik_type`, `score_0`, `score_0_images`, `score_1`, `score_1_images`, `score_2`, `score_2_images`, `score_3`, `score_3_images`) VALUES
(1, b'0', 1, 'q1', '', 1, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(2, b'0', 1, 'q1', '', 1, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(3, b'0', 1, 'q2', '', 1, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(4, b'0', 1, 'q3', '', 1, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(5, b'0', 1, 'q4', '', 1, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(6, b'0', 1, 'q5', '', 1, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(7, b'0', 1, 'q6', '', 1, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(8, b'0', 1, 'q7', '', 1, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(9, b'0', 2, 'w1', '', 1, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(10, b'0', 2, 'w2', '', 1, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(11, b'0', 2, 'w3', '', 1, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(12, b'0', 2, 'w4', '', 1, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(13, b'0', 3, 'e1', '', 1, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(14, b'0', 4, 'e2', '', 1, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(15, b'1', 4, 'e3', '', 1, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(16, b'0', 1, 'q1', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(17, b'0', 1, 'q2', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(18, b'0', 1, 'q3', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(19, b'0', 1, 'q4', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(20, b'0', 1, 'q5', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(21, b'0', 1, 'q6', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(22, b'0', 1, 'q7', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(23, b'0', 1, 'q8', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(24, b'0', 1, 'q9', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(25, b'0', 1, 'q10', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(26, b'0', 2, 'w1', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(27, b'0', 2, 'w2', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(28, b'0', 2, 'w3', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(29, b'0', 2, 'w4', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(30, b'0', 2, 'w5', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(31, b'0', 2, 'w6', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(32, b'0', 3, 'e1', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(33, b'0', 3, 'e2', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(34, b'0', 3, 'e3', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(35, b'0', 4, 'e4', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(36, b'0', 4, 'e5', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', '');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `description` varchar(100) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `description`, `name`) VALUES
(1, '', 'admin'),
(2, '', 'evaluator'),
(3, '', 'generator');

-- --------------------------------------------------------

--
-- Table structure for table `role_permission`
--

CREATE TABLE `role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role_permission`
--

INSERT INTO `role_permission` (`role_id`, `permission_id`) VALUES
(2, 3),
(2, 4),
(3, 1),
(3, 2),
(3, 5);

-- --------------------------------------------------------

--
-- Table structure for table `rubrik`
--

CREATE TABLE `rubrik` (
  `id` int(11) NOT NULL,
  `created_ts` datetime(6) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  `type` int(11) NOT NULL,
  `updated_ts` datetime(6) DEFAULT NULL,
  `user_email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rubrik`
--

INSERT INTO `rubrik` (`id`, `created_ts`, `name`, `status`, `type`, `updated_ts`, `user_email`) VALUES
(11, '2019-04-04 16:19:37.000000', 'LOBE_LITE_11', 'incomplete', 1, '2019-04-04 16:19:37.000000', 'ankitx@gmail.com'),
(12, '2019-04-04 16:24:42.000000', 'LOBE_PREMIUM_12', 'incomplete', 2, '2019-04-04 16:24:42.000000', 'ankitx@gmail.com'),
(13, '2019-04-04 16:28:39.000000', 'LOBE_CUSTOM_13', 'incomplete', 3, '2019-04-04 16:28:39.000000', 'ankitx@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `rubrik_quality_dimensions`
--

CREATE TABLE `rubrik_quality_dimensions` (
  `rubrik_id` int(11) NOT NULL,
  `quality_dimension_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rubrik_quality_dimensions`
--

INSERT INTO `rubrik_quality_dimensions` (`rubrik_id`, `quality_dimension_id`) VALUES
(11, 1),
(11, 2),
(11, 3),
(11, 4),
(12, 1),
(12, 2),
(12, 3),
(12, 4),
(13, 1),
(13, 2);

-- --------------------------------------------------------

--
-- Table structure for table `rubrik_questions`
--

CREATE TABLE `rubrik_questions` (
  `rubrik_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rubrik_questions`
--

INSERT INTO `rubrik_questions` (`rubrik_id`, `question_id`) VALUES
(11, 1),
(11, 2),
(11, 3),
(11, 4),
(11, 5),
(11, 6),
(11, 7),
(11, 8),
(11, 9),
(11, 10),
(11, 11),
(11, 12),
(11, 13),
(11, 14),
(12, 16),
(12, 17),
(12, 18),
(12, 19),
(12, 20),
(12, 21),
(12, 22),
(12, 23),
(12, 24),
(12, 25),
(12, 26),
(12, 27),
(12, 28),
(12, 29),
(12, 30),
(12, 31),
(12, 32),
(12, 33),
(12, 34),
(12, 35),
(12, 36);

-- --------------------------------------------------------

--
-- Table structure for table `rubrik_type_master`
--

CREATE TABLE `rubrik_type_master` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rubrik_type_master`
--

INSERT INTO `rubrik_type_master` (`id`, `name`) VALUES
(1, 'Lobe Lite'),
(2, 'Lobe Premium'),
(3, 'Lobe Custom');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `email` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `status` varchar(20) NOT NULL,
  `affiliation` varchar(100) NOT NULL,
  `created_ts` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`email`, `name`, `password`, `status`, `affiliation`, `created_ts`) VALUES
('ankitx@gmail.com', 'ankit', 'ZehL4zUy+3hMSBKWdfnv86aCsnFowOp0Syz1juAjN8U=', 'ACTIVE', 'xaviers', '2019-03-29 17:33:28.000000');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `user_email` varchar(100) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`user_email`, `role_id`) VALUES
('ankitx@gmail.com', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `learning_object`
--
ALTER TABLE `learning_object`
  ADD PRIMARY KEY (`code`),
  ADD KEY `FKkna01q2o2cr3ykfb3br0pxvkj` (`rubrik_id`),
  ADD KEY `FK2407ulg8hipch79mwdynftgeq` (`assigned_by`),
  ADD KEY `FKsbv2j6maegp2poc9d5r2xxsr1` (`assigned_to`);

--
-- Indexes for table `lobe_scores`
--
ALTER TABLE `lobe_scores`
  ADD PRIMARY KEY (`question_id`,`learning_object_id`),
  ADD KEY `FKfg3o9d9xect9bhsyf9ra5hj8k` (`learning_object_id`);

--
-- Indexes for table `permission`
--
ALTER TABLE `permission`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `quality_dimension_master`
--
ALTER TABLE `quality_dimension_master`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `question_master`
--
ALTER TABLE `question_master`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rubrik_type` (`rubrik_type`),
  ADD KEY `quality_dimension_id` (`quality_dimension_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `role_permission`
--
ALTER TABLE `role_permission`
  ADD PRIMARY KEY (`role_id`,`permission_id`),
  ADD KEY `FKf8yllw1ecvwqy3ehyxawqa1qp` (`permission_id`);

--
-- Indexes for table `rubrik`
--
ALTER TABLE `rubrik`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjyl38v93cb6dlciic0cve563u` (`user_email`),
  ADD KEY `type` (`type`);

--
-- Indexes for table `rubrik_quality_dimensions`
--
ALTER TABLE `rubrik_quality_dimensions`
  ADD PRIMARY KEY (`rubrik_id`,`quality_dimension_id`),
  ADD KEY `FK77crt6ksumsapcb3kttgfpve8` (`quality_dimension_id`);

--
-- Indexes for table `rubrik_questions`
--
ALTER TABLE `rubrik_questions`
  ADD PRIMARY KEY (`rubrik_id`,`question_id`),
  ADD KEY `FK9ru0atxwh9bem27npxe8vl45s` (`question_id`);

--
-- Indexes for table `rubrik_type_master`
--
ALTER TABLE `rubrik_type_master`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`email`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_email`,`role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `quality_dimension_master`
--
ALTER TABLE `quality_dimension_master`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `question_master`
--
ALTER TABLE `question_master`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `rubrik`
--
ALTER TABLE `rubrik`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `rubrik_type_master`
--
ALTER TABLE `rubrik_type_master`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `learning_object`
--
ALTER TABLE `learning_object`
  ADD CONSTRAINT `FK2407ulg8hipch79mwdynftgeq` FOREIGN KEY (`assigned_by`) REFERENCES `user` (`email`),
  ADD CONSTRAINT `FKkna01q2o2cr3ykfb3br0pxvkj` FOREIGN KEY (`rubrik_id`) REFERENCES `rubrik` (`id`),
  ADD CONSTRAINT `FKsbv2j6maegp2poc9d5r2xxsr1` FOREIGN KEY (`assigned_to`) REFERENCES `user` (`email`);

--
-- Constraints for table `lobe_scores`
--
ALTER TABLE `lobe_scores`
  ADD CONSTRAINT `FK5shyltby4fg0qa4y8mcc5brrd` FOREIGN KEY (`question_id`) REFERENCES `question_master` (`id`),
  ADD CONSTRAINT `FKfg3o9d9xect9bhsyf9ra5hj8k` FOREIGN KEY (`learning_object_id`) REFERENCES `learning_object` (`code`);

--
-- Constraints for table `question_master`
--
ALTER TABLE `question_master`
  ADD CONSTRAINT `question_master_ibfk_1` FOREIGN KEY (`rubrik_type`) REFERENCES `rubrik_type_master` (`id`),
  ADD CONSTRAINT `question_master_ibfk_2` FOREIGN KEY (`quality_dimension_id`) REFERENCES `quality_dimension_master` (`id`);

--
-- Constraints for table `role_permission`
--
ALTER TABLE `role_permission`
  ADD CONSTRAINT `FKa6jx8n8xkesmjmv6jqug6bg68` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FKf8yllw1ecvwqy3ehyxawqa1qp` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`);

--
-- Constraints for table `rubrik`
--
ALTER TABLE `rubrik`
  ADD CONSTRAINT `FKjyl38v93cb6dlciic0cve563u` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`),
  ADD CONSTRAINT `rubrik_ibfk_1` FOREIGN KEY (`type`) REFERENCES `rubrik_type_master` (`id`);

--
-- Constraints for table `rubrik_quality_dimensions`
--
ALTER TABLE `rubrik_quality_dimensions`
  ADD CONSTRAINT `FK77crt6ksumsapcb3kttgfpve8` FOREIGN KEY (`quality_dimension_id`) REFERENCES `quality_dimension_master` (`id`),
  ADD CONSTRAINT `FKlu1xfu989e0kipr27hpc14i29` FOREIGN KEY (`rubrik_id`) REFERENCES `rubrik` (`id`);

--
-- Constraints for table `rubrik_questions`
--
ALTER TABLE `rubrik_questions`
  ADD CONSTRAINT `FK9ru0atxwh9bem27npxe8vl45s` FOREIGN KEY (`question_id`) REFERENCES `question_master` (`id`),
  ADD CONSTRAINT `FKp2askl8jl61kc7vf5b8i0vpdy` FOREIGN KEY (`rubrik_id`) REFERENCES `rubrik` (`id`);

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FKdc28wohvgju313d3pisccud2` FOREIGN KEY (`user_email`) REFERENCES `user` (`email`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
