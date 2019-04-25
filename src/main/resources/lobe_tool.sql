-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2019 at 05:56 AM
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
  `question` varchar(1000) NOT NULL,
  `question_meta` varchar(2000) NOT NULL,
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
(1, b'0', 1, 'C1. Is the content accurate and grade appropriate?', '<a class=\"evaluate\">Which components to consider? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">Consider the content across all components of the learning object like dynamic visualization, learning activities, examples, graphs if any etc. and score on the overall content accuracy. </span>  <a class=\"evaluate\">What is ‘Target’ for this question? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">The content contains correct facts, explanations, examples or graphical representations. The content presentation is unlikely to cause any misconception for the learner. </span>  <a class=\"evaluate\">What does ‘grade appropriate content’ mean? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">Also, the content is grade appropriate. For example, introducing the 5 states of matter is grade appropriate for Grade 9 but not for Grade 5. </span>', 1, '', '', '', '', '', '', '', ''),
(2, b'0', 1, 'C2. Are the assessment questions in the learning object and their solutions correct, clear, unambiguous and grade appropriate?', '<a class=\"evaluate\">What is ‘Target’ for this question? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">The assessment questions as well as their solution contain correct facts and accurate scientific explanations. The wording of the questions and solutions should be clear and complete for the learner. The question should not be open to more than one interpretation.  An example of an ambiguous question is: Write a short note on WWII. </span>  <a class=\"evaluate\">What does ‘grade appropriate content’ mean? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">Grade appropriate means the difficulty level of the question should be commensurate to the skill level of the students of that grade. </span>', 1, '', '', '', '', '', '', '', ''),
(3, b'0', 2, 'P1. Does the content and the assessment questions contain appropriate context?', '<a class=\"evaluate\">To evaluate this question: <i class=\"fas fa-caret-right\"></i></a>\r\n<span class=\"hidden\">Consider the content in the following components of the learning object - dynamic visualization, learning activities, examples. </span>\r\n\r\n<a class=\"evaluate\">What is ‘Target’ for this question? <i class=\"fas fa-caret-right\"></i></a>\r\n<span class=\"hidden\">Context in content: The context should motivate the learner to care about the topic. For example, a motivational introductory scenario or a real life example or application of the topic.<br> \r\nContext in assessment question: The context is appropriate when it is relevant and sufficient for the specific question. For e.g., if a word problem in Mathematics is set in context of a baseball game, then the context is not meaningful for Indian students.\r\n</span>\r\n\r\n<a class=\"evaluate\">Note before scoring: <i class=\"fas fa-caret-right\"></i></a>\r\n<span class=\"hidden\">\r\nScore on the overall quality of context provided. If you find contextualization completely missing in either one of content or assessment question, please mark as Inadequate.<br>\r\nContext is not required in all assessment questions. However, there should be sufficient questions containing a relevant and meaningful context.\r\n</span>', 1, '', '', '', '', '', '', '', ''),
(4, b'0', 2, 'P2. Are the assessment questions in the learning object aligned to the stated learning objectives?', '<a class=\"evaluate\">Why is this question important? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">If assessment questions are at a lower cognitive level than the learning objectives, then there is no source of evidence to test if the learning objectives have been achieved. On the other hand, if the assessment questions are higher than the learning objectives, then it will not be fair to learners. </span>   <a class=\"evaluate\">What is ‘Target’ for this question? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">All assessment questions should be aligned to the corresponding level of the stated learning objectives. </span>', 1, '', '', '', '', '', '', '', ''),
(5, b'0', 2, 'P3. Has both higher order thinking skills (HOTS) and lower order thinking skills (LOTS) been sufficiently addressed in the learning object?', '<a class=\"evaluate\">To evaluate this question: <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">Consider the content in the following components of the learning object - examples, learning activities, assessment questions.</span>  <a class=\"evaluate\">What is ‘HOTS’ in this question? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">Higher order thinking skill (HOTS) covers apply level and above of Revised Bloom’s Taxonomy. Ideally, dynamic visualization should include those HOTS examples or learning activities that are important for the topic. <br> The learning object should include HOTS important for the topic. </span>  <a class=\"evaluate\">NOTE before scoring: <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">The HOTS level of an example, activity or assessment depends on how the content has been presented to the learners. For example, an activity or assessment requires application of a formula for solution. But the formula has already been displayed in the learning object. Then all that the learners are required to do is remember that formula and plug in the given values to solve the given problem. In such cases, the cognitive level of the learning activity or assessment is no longer Apply, but comes down to Understand level.</span>', 1, '', '', '', '', '', '', '', ''),
(6, b'0', 2, 'P4. Does the learning object promote learner-centric learning?', '<a class=\"evaluate\">To evaluate this question: <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">Consider the content in the following components of learning object - dynamic visualization & learning activities. </span>  <a class=\"evaluate\">What is ‘Target’ for this question? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">The dynamic visualization and learning activities should allow learners to construct understanding of the topic on their own by connecting the content to what they already know, organizing and making sense of the content, testing and revising their understanding, applying the content and so on. This can be promoted by providing sufficient relevant activities where learners are required to do the above. Such activities are essential in getting learners engaged with the content and for effective learning. </span>  <a class=\"evaluate\">NOTE before scoring: <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">Give overall score on learner-centricity of the learning object. </span>', 1, '', '', '', '', '', '', '', ''),
(7, b'1', 2, 'P5. Does the learning object provide support to learners to do group activity? ', '<a class=\"evaluate\">To evaluate this question: <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">Consider the following components of the learning object - learning activities, assessment questions. </span>  <a class=\"evaluate\">What is ‘Target’ for this question? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">The structure of the learning/assessment activity should be such that there is high probability that all learners in the class will participate in small-group (2-3 members) activity. The group activity should ensure learners engage in logical argumentation with their group members. For example, calculating & showing group scores for an assessment activity.  </span>', 1, '', '', '', '', '', '', '', ''),
(8, b'0', 4, 'T1. Does the learning object include interactivity features that are meaningful for learning the corresponding content?', '<a class=\"evaluate\">To evaluate this question : <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">Consider the following components of a learning object - dynamic   visualization, learning activities, or assessments. </span>  <a class=\"evaluate\">Examples of interactivity features : <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">Slider bars, input boxes, drag & drop, drop-down, activity question popping in between content presentation. </span>  <a class=\"evaluate\">What is ‘Target’ for this question? <i class=\"fas fa-caret-right\"></i></a>  <span class=\"hidden\">The choice of interactivity features in the learning object should be determined by the learning purpose they serve (Refer the examples below). Also, superfluous interactivities should be avoided. <br> Examples of interactivity mapped to purpose: <br> for navigation – use back/next, play/pause buttons <br> for choosing from a set of values – <br> if no. of values < 5 , use radio buttons;  <br> if no. of values >5, use drop-down <br> for control/modify variables – use slider bars <br> for matching and selection – use drag & drop </span>', 1, '', '', '', '', '', '', '', ''),
(9, b'0', 4, 'T2. Does the user have appropriate control of navigation and pace within the learning object?', '<a class=\"evaluate\">What is ‘Target’ for this question? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">The learning object should provide the user the flexibility to - <br> go from one part of the learning object to another as desired, <br>	 go back & forth, <br> interact with the learning object at their desired rate (for example slow down / speed up an animation) <br> contain obvious exit options. </span>', 1, '', '', '', '', '', '', '', ''),
(10, b'0', 4, 'T3. Is the interface easy to use for a new user?', '<a class=\"evaluate\">What is ‘Target’ for this question? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">The interface of the learning object should be: <br> intuitive to a new user, <br> information should be easy to find, <br> organization and hierarchy of content should be clear and consistent, <br> screens and buttons should be consistently placed, <br> labels and legends should be placed near the graphics. </span>', 1, '', '', '', '', '', '', '', ''),
(11, b'0', 1, '', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(12, b'0', 2, 'w4', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(13, b'0', 3, 'e1', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(14, b'0', 4, 'e2', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
(15, b'1', 4, 'e3', '', 2, 'sda', '', 'adfs', '', 'dasvx', '', 'czxvw', ''),
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

-- --------------------------------------------------------

--
-- Table structure for table `rubrik_quality_dimensions`
--

CREATE TABLE `rubrik_quality_dimensions` (
  `rubrik_id` int(11) NOT NULL,
  `quality_dimension_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `rubrik_questions`
--

CREATE TABLE `rubrik_questions` (
  `rubrik_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `user_email` varchar(100) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

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
