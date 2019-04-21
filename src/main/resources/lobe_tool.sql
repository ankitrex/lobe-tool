-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 21, 2019 at 11:57 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
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
  `chapter` varchar(100) DEFAULT NULL,
  `created_ts` datetime(6) NOT NULL,
  `grade` varchar(100) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `repository_name` varchar(100) DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `module_name` varchar(100) DEFAULT NULL,
  `updated_ts` datetime(6) DEFAULT NULL,
  `rubrik_id` int(11) NOT NULL,
  `assigned_by` varchar(100) NOT NULL,
  `assigned_to` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `learning_object`
--

INSERT INTO `learning_object` (`code`, `chapter`, `created_ts`, `grade`, `name`, `repository_name`, `status`, `subject`, `module_name`, `updated_ts`, `rubrik_id`, `assigned_by`, `assigned_to`) VALUES
('asdas-1648ce1f-ce5a-454b-9449-8fa7f00a29b9', NULL, '2019-04-20 22:46:24.000000', NULL, 'asdas', NULL, 'assigned', NULL, NULL, NULL, 12, 'ankitx@gmail.com', 'theankitgajra@gmail.com'),
('asdasd-7b13f493-5144-432a-b152-3b16dd0ac00a', NULL, '2019-04-20 22:52:20.000000', NULL, 'asdasd', NULL, 'assigned', NULL, NULL, NULL, 12, 'ankitx@gmail.com', 'theankitgajra@gmail.com'),
('bio-7f519ad4-c457-4368-8a1e-87ea41a6afac', NULL, '2019-04-20 22:44:22.000000', NULL, 'bio', NULL, 'assigned', NULL, NULL, NULL, 12, 'ankitx@gmail.com', 'theankitgajra@gmail.com'),
('LOBE_CUSTOM_15_TEST-1500af92-bde8-4b9d-9267-54b4415b3fe5', NULL, '2019-04-07 13:59:57.000000', NULL, 'TEST', NULL, 'assigned', NULL, NULL, NULL, 14, 'ankitx@gmail.com', 'ankiteval@gmail.com'),
('LOBE_CUSTOM_16_TEST-d35207ac-fa2b-4a82-9069-9a74f3b79ac7', NULL, '2019-04-07 13:47:34.000000', NULL, 'TEST', NULL, 'assigned', NULL, NULL, NULL, 16, 'ankitx@gmail.com', 'ankiteval@gmail.com'),
('LOBE_CUSTOM_16_TESTX-9b525e38-150c-410f-bf1a-d6cb0167f76c', 'organic', '2019-04-07 16:14:58.000000', 'A', 'TESTX', 'qwerty', 'complete', 'Chemistry', 'my mod', '2019-04-07 18:10:28.000000', 16, 'ankitx@gmail.com', 'theankitgajra@gmail.com'),
('LOBE_CUSTOM_17_TECH-5f07a799-9274-49b5-aa8b-8c8dcccb0e98', 'database', '2019-04-09 17:51:45.000000', 'A', 'TECH', 'qwerty', 'complete', 'IT', 'my mod', '2019-04-09 17:55:34.000000', 17, 'ankitx@gmail.com', 'theankitgajra@gmail.com'),
('LOBE_CUSTOM_17_TECH2', 'ds', '2019-04-09 18:05:29.000000', 'A', 'TECH2', 'qwerty', 'complete', 'IT', 'my mod', '2019-04-09 18:06:39.000000', 17, 'ankitx@gmail.com', 'theankitgajra@gmail.com'),
('LOBE_CUSTOM_17_TECH2-2418b969-7dc0-407f-ad90-a50e3f02d917', NULL, '2019-04-20 22:51:46.000000', NULL, 'TECH2', NULL, 'assigned', NULL, NULL, NULL, 17, 'ankitx@gmail.com', 'theankitgajra@gmail.com'),
('LOBE_CUSTOM_17_TECH2-62781ef4-7fd6-45d6-9066-42488d389a72', NULL, '2019-04-20 22:51:43.000000', NULL, 'TECH2', NULL, 'assigned', NULL, NULL, NULL, 17, 'ankitx@gmail.com', 'theankitgajra@gmail.com'),
('LOBE_CUSTOM_17_TECH2-9cdb3e87-1651-4f04-8424-9799117061ed', 'ds', '2019-04-13 16:15:57.000000', 'A', 'TECH2', 'qwerty', 'incomplete', 'IT', 'my mod', '2019-04-13 16:16:30.000000', 17, 'ankitx@gmail.com', 'theankitgajra@gmail.com'),
('maths-8ff639df-b5ee-4dda-ae2b-9fc203b577e5', NULL, '2019-04-20 22:42:56.000000', NULL, 'maths', NULL, 'assigned', NULL, NULL, NULL, 11, 'ankitx@gmail.com', 'theankitgajra@gmail.com'),
('science-b-1a714a68-0adb-4402-aad8-c5b2c8b919ec', NULL, '2019-04-20 22:28:06.000000', NULL, 'science b', NULL, 'assigned', NULL, NULL, NULL, 12, 'ankitx@gmail.com', 'theankitgajra@gmail.com'),
('yfuy-fc5397d4-de58-4805-994b-be82d6d54818', NULL, '2019-04-20 22:47:15.000000', NULL, 'yfuy', NULL, 'assigned', NULL, NULL, NULL, 12, 'ankitx@gmail.com', 'theankitgajra@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `lobe_scores`
--

CREATE TABLE `lobe_scores` (
  `score` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `learning_object_id` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lobe_scores`
--

INSERT INTO `lobe_scores` (`score`, `question_id`, `learning_object_id`) VALUES
(3, 16, 'LOBE_CUSTOM_17_TECH-5f07a799-9274-49b5-aa8b-8c8dcccb0e98'),
(3, 16, 'LOBE_CUSTOM_17_TECH2'),
(0, 17, 'LOBE_CUSTOM_16_TESTX-9b525e38-150c-410f-bf1a-d6cb0167f76c'),
(1, 17, 'LOBE_CUSTOM_17_TECH-5f07a799-9274-49b5-aa8b-8c8dcccb0e98'),
(1, 17, 'LOBE_CUSTOM_17_TECH2'),
(1, 18, 'LOBE_CUSTOM_16_TESTX-9b525e38-150c-410f-bf1a-d6cb0167f76c'),
(0, 18, 'LOBE_CUSTOM_17_TECH-5f07a799-9274-49b5-aa8b-8c8dcccb0e98'),
(0, 18, 'LOBE_CUSTOM_17_TECH2'),
(0, 19, 'LOBE_CUSTOM_16_TESTX-9b525e38-150c-410f-bf1a-d6cb0167f76c'),
(2, 19, 'LOBE_CUSTOM_17_TECH-5f07a799-9274-49b5-aa8b-8c8dcccb0e98'),
(2, 19, 'LOBE_CUSTOM_17_TECH2'),
(1, 20, 'LOBE_CUSTOM_17_TECH-5f07a799-9274-49b5-aa8b-8c8dcccb0e98'),
(1, 20, 'LOBE_CUSTOM_17_TECH2'),
(3, 21, 'LOBE_CUSTOM_17_TECH-5f07a799-9274-49b5-aa8b-8c8dcccb0e98'),
(3, 21, 'LOBE_CUSTOM_17_TECH2'),
(1, 26, 'LOBE_CUSTOM_17_TECH-5f07a799-9274-49b5-aa8b-8c8dcccb0e98'),
(1, 26, 'LOBE_CUSTOM_17_TECH2'),
(0, 29, 'LOBE_CUSTOM_17_TECH-5f07a799-9274-49b5-aa8b-8c8dcccb0e98'),
(0, 29, 'LOBE_CUSTOM_17_TECH2'),
(2, 30, 'LOBE_CUSTOM_17_TECH-5f07a799-9274-49b5-aa8b-8c8dcccb0e98'),
(2, 30, 'LOBE_CUSTOM_17_TECH2'),
(1, 31, 'LOBE_CUSTOM_17_TECH-5f07a799-9274-49b5-aa8b-8c8dcccb0e98'),
(1, 31, 'LOBE_CUSTOM_17_TECH2'),
(0, 32, 'LOBE_CUSTOM_17_TECH-5f07a799-9274-49b5-aa8b-8c8dcccb0e98'),
(0, 32, 'LOBE_CUSTOM_17_TECH2'),
(2, 34, 'LOBE_CUSTOM_17_TECH-5f07a799-9274-49b5-aa8b-8c8dcccb0e98'),
(2, 34, 'LOBE_CUSTOM_17_TECH2'),
(2, 35, 'LOBE_CUSTOM_17_TECH-5f07a799-9274-49b5-aa8b-8c8dcccb0e98'),
(2, 35, 'LOBE_CUSTOM_17_TECH2'),
(1, 36, 'LOBE_CUSTOM_17_TECH-5f07a799-9274-49b5-aa8b-8c8dcccb0e98'),
(2, 36, 'LOBE_CUSTOM_17_TECH2');

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
(16, b'0', 1, 'q1', 'Some of the content is accurate, but there are one or more major errors such as incorrect explanations or examples or inaccurate activity solutions; or the way the content is presented is likely to cause major misconception.', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(17, b'0', 1, 'q2', 'Some of the content is accurate, but there are one or more major errors such as incorrect explanations or examples or inaccurate activity solutions; or the way the content is presented is likely to cause major misconception.', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(18, b'0', 1, 'q3', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(19, b'0', 1, 'q4', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(20, b'0', 1, 'q5', 'Some of the content is accurate, but there are one or more major errors such as incorrect explanations or examples or inaccurate activity solutions; or the way the content is presented is likely to cause major misconception.', 2, 'sda', 'imgs/img1.jpg,imgs/img2.jpg', 'adfs', 'imgs/img1.jpg', 'dasvx', '', 'czxvw', ''),
(21, b'0', 1, 'q6', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(22, b'0', 1, 'q7', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(23, b'0', 1, 'q8', '', 2, 'sda', 'imgs/img1.jpg', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(24, b'0', 1, 'q9', '', 2, 'sda', 'imgs/img1.jpg,imgs/img2.jpg', 'adfs', '', 'dasvx', '', 'czxvw', ''),
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
(11, '2019-04-04 16:19:37.000000', 'LOBE_LITE_11', 'complete', 1, '2019-04-04 16:19:37.000000', 'ankitx@gmail.com'),
(12, '2019-04-04 16:24:42.000000', 'LOBE_PREMIUM_12', 'complete', 2, '2019-04-20 13:34:19.000000', 'ankitx@gmail.com'),
(13, '2019-04-04 16:28:39.000000', 'LOBE_CUSTOM_13', 'complete', 3, '2019-04-04 16:28:39.000000', 'ankitx@gmail.com'),
(14, '2019-04-05 18:45:34.000000', 'LOBE_PREMIUM_14', 'complete', 2, '2019-04-05 18:45:34.000000', 'ankitx@gmail.com'),
(15, '2019-04-05 18:56:04.000000', 'LOBE_CUSTOM_15', 'complete', 3, '2019-04-20 13:44:12.000000', 'ankitx@gmail.com'),
(16, '2019-04-07 12:06:09.000000', 'LOBE_CUSTOM_16', 'complete', 3, '2019-04-07 12:06:10.000000', 'ankitx@gmail.com'),
(17, '2019-04-09 17:40:38.000000', 'LOBE_CUSTOM_17', 'complete', 3, '2019-04-09 17:50:37.000000', 'ankitx@gmail.com'),
(18, '2019-04-13 14:30:14.000000', 'LOBE_PREMIUM_18', 'complete', 2, '2019-04-14 15:51:01.000000', 'ankitx@gmail.com'),
(19, '2019-04-13 14:31:07.000000', 'LOBE_LITE_19', 'complete', 1, '2019-04-14 15:47:28.000000', 'ankitx@gmail.com'),
(20, '2019-04-13 15:07:36.000000', 'LOBE_CUSTOM_20', 'complete', 3, '2019-04-20 17:41:12.000000', 'ankitx@gmail.com'),
(21, '2019-04-13 15:33:13.000000', 'LOBE_CUSTOM_21', 'complete', 3, '2019-04-20 17:42:52.000000', 'ankitx@gmail.com'),
(22, '2019-04-14 15:53:25.000000', 'LOBE_LITE_22', 'incomplete', 1, '2019-04-20 21:32:06.000000', 'ankitx@gmail.com'),
(23, '2019-04-14 15:56:05.000000', 'LOBE_LITE_23', 'incomplete', 1, '2019-04-20 13:34:49.000000', 'ankitx@gmail.com'),
(24, '2019-04-14 15:57:15.000000', 'LOBE_LITE_24', 'incomplete', 1, '2019-04-14 15:57:21.000000', 'ankitx@gmail.com'),
(25, '2019-04-14 15:57:39.000000', 'LOBE_PREMIUM_25', 'complete', 2, '2019-04-14 15:57:48.000000', 'ankitx@gmail.com'),
(26, '2019-04-20 16:38:51.000000', 'LOBE_PREMIUM_26', 'incomplete', 2, '2019-04-20 16:38:51.000000', 'ankitx@gmail.com'),
(27, '2019-04-20 17:44:23.000000', 'LOBE_CUSTOM_27', 'complete', 3, '2019-04-20 17:45:01.000000', 'ankitx@gmail.com'),
(28, '2019-04-20 20:39:54.000000', 'LOBE_CUSTOM_28', 'complete', 3, '2019-04-20 21:38:49.000000', 'ankitx@gmail.com'),
(29, '2019-04-20 20:39:57.000000', 'LOBE_CUSTOM_29', 'complete', 3, '2019-04-20 21:47:07.000000', 'ankitx@gmail.com'),
(30, '2019-04-20 20:41:09.000000', 'LOBE_CUSTOM_30', 'incomplete', 3, '2019-04-20 20:41:09.000000', 'ankitx@gmail.com'),
(31, '2019-04-20 20:41:50.000000', 'LOBE_CUSTOM_31', 'incomplete', 3, '2019-04-20 20:48:38.000000', 'ankitx@gmail.com'),
(32, '2019-04-20 21:26:06.000000', 'LOBE_CUSTOM_32', 'complete', 3, '2019-04-20 21:26:24.000000', 'ankitx@gmail.com');

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
(13, 2),
(14, 1),
(14, 2),
(14, 3),
(14, 4),
(15, 1),
(15, 2),
(15, 3),
(15, 4),
(16, 1),
(16, 2),
(16, 3),
(16, 4),
(17, 1),
(17, 2),
(17, 3),
(17, 4),
(18, 1),
(18, 2),
(18, 3),
(18, 4),
(19, 1),
(19, 2),
(19, 3),
(19, 4),
(20, 1),
(20, 2),
(20, 3),
(21, 1),
(21, 2),
(22, 1),
(22, 2),
(22, 3),
(22, 4),
(23, 1),
(23, 2),
(23, 3),
(23, 4),
(24, 1),
(24, 2),
(24, 3),
(24, 4),
(25, 1),
(25, 2),
(25, 3),
(25, 4),
(26, 1),
(26, 2),
(26, 3),
(26, 4),
(27, 1),
(27, 2),
(27, 4),
(28, 1),
(28, 2),
(28, 3),
(28, 4),
(29, 1),
(29, 2),
(29, 3),
(29, 4),
(30, 2),
(30, 3),
(31, 1),
(31, 2),
(31, 3),
(32, 2),
(32, 3),
(32, 4);

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
(11, 15),
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
(12, 36),
(13, 16),
(13, 17),
(13, 18),
(13, 19),
(14, 16),
(14, 17),
(14, 18),
(14, 19),
(14, 20),
(14, 21),
(14, 22),
(14, 23),
(14, 24),
(14, 25),
(14, 26),
(14, 27),
(14, 28),
(14, 29),
(14, 30),
(14, 31),
(14, 32),
(14, 33),
(14, 34),
(14, 35),
(14, 36),
(15, 17),
(15, 18),
(15, 19),
(16, 17),
(16, 18),
(16, 19),
(17, 16),
(17, 17),
(17, 18),
(17, 19),
(17, 20),
(17, 21),
(17, 26),
(17, 29),
(17, 30),
(17, 31),
(17, 32),
(17, 34),
(17, 35),
(17, 36),
(18, 16),
(18, 17),
(18, 18),
(18, 19),
(18, 20),
(18, 21),
(18, 22),
(18, 23),
(18, 24),
(18, 25),
(18, 26),
(18, 27),
(18, 28),
(18, 29),
(18, 30),
(18, 31),
(18, 32),
(18, 33),
(18, 34),
(18, 35),
(18, 36),
(19, 1),
(19, 2),
(19, 3),
(19, 4),
(19, 5),
(19, 6),
(19, 7),
(19, 8),
(19, 9),
(19, 10),
(19, 11),
(19, 12),
(19, 13),
(19, 14),
(20, 16),
(20, 17),
(20, 18),
(20, 19),
(20, 20),
(20, 21),
(20, 24),
(21, 16),
(21, 17),
(21, 18),
(21, 19),
(21, 26),
(21, 27),
(22, 1),
(22, 2),
(22, 3),
(22, 4),
(22, 5),
(22, 6),
(22, 7),
(22, 8),
(22, 9),
(22, 10),
(22, 11),
(22, 12),
(22, 13),
(22, 14),
(23, 1),
(23, 2),
(23, 3),
(23, 4),
(23, 5),
(23, 6),
(23, 7),
(23, 8),
(23, 9),
(23, 10),
(23, 11),
(23, 12),
(23, 13),
(23, 14),
(23, 15),
(24, 1),
(24, 2),
(24, 3),
(24, 4),
(24, 5),
(24, 6),
(24, 7),
(24, 8),
(24, 9),
(24, 10),
(24, 11),
(24, 12),
(24, 13),
(24, 14),
(24, 15),
(25, 16),
(25, 17),
(25, 18),
(25, 19),
(25, 20),
(25, 21),
(25, 22),
(25, 23),
(25, 24),
(25, 25),
(25, 26),
(25, 27),
(25, 28),
(25, 29),
(25, 30),
(25, 31),
(25, 32),
(25, 33),
(25, 34),
(25, 35),
(25, 36),
(26, 16),
(26, 17),
(26, 18),
(26, 19),
(26, 20),
(26, 21),
(26, 22),
(26, 23),
(26, 24),
(26, 25),
(26, 26),
(26, 27),
(26, 28),
(26, 29),
(26, 30),
(26, 31),
(26, 32),
(26, 33),
(26, 34),
(26, 35),
(26, 36),
(27, 16),
(27, 17),
(27, 26),
(27, 35),
(27, 36),
(28, 16),
(28, 17),
(28, 20),
(28, 32),
(29, 16),
(29, 17),
(29, 18),
(29, 26),
(29, 27),
(29, 28),
(29, 32),
(29, 33),
(29, 35),
(31, 16),
(31, 17),
(31, 18),
(31, 19),
(31, 20),
(31, 21),
(32, 26),
(32, 27),
(32, 28),
(32, 32),
(32, 33),
(32, 35);

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
('ankit2@gmail.com', 'ankit', 'ZehL4zUy+3hMSBKWdfnv86aCsnFowOp0Syz1juAjN8U=', 'ACTIVE', 'xaviers', '2019-04-13 13:56:07.000000'),
('ankit@gmail.com', 'ankit', 'ZehL4zUy+3hMSBKWdfnv86aCsnFowOp0Syz1juAjN8U=', 'ACTIVE', 'xaviers', '2019-04-13 13:49:32.000000'),
('ankiteval@gmail.com', 'ankit', 'ZehL4zUy+3hMSBKWdfnv86aCsnFowOp0Syz1juAjN8U=', 'ACTIVE', 'xaviers', '2019-04-07 13:12:51.000000'),
('ankitx@gmail.com', 'ankit', 'ZehL4zUy+3hMSBKWdfnv86aCsnFowOp0Syz1juAjN8U=', 'ACTIVE', 'xaviers', '2019-03-29 17:33:28.000000'),
('theankitgajra@gmail.com', 'ankit', 'ZehL4zUy+3hMSBKWdfnv86aCsnFowOp0Syz1juAjN8U=', 'ACTIVE', 'xaviers', '2019-04-07 16:13:59.000000');

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
('ankit2@gmail.com', 2),
('ankit@gmail.com', 2),
('ankiteval@gmail.com', 2),
('ankitx@gmail.com', 3),
('theankitgajra@gmail.com', 2);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
