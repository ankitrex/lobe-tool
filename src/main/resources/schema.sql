-- phpMyAdmin SQL Dump
    -- version 4.8.5
    -- https://www.phpmyadmin.net/
    --

    -- Host: 127.0.0.1
    -- Generation Time: Mar 23, 2019 at 03:08 PM
    -- Server version: 10.1.38-MariaDB
    -- PHP Version: 7.3.2
SET
    SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET
    AUTOCOMMIT = 0;
START TRANSACTION
    ;
SET
    time_zone = "+00:00";
    /*!40101
SET
    @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
    /*!40101
SET
    @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
    /*!40101
SET
    @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
    /*!40101
SET NAMES
    utf8mb4 */;
    --

    -- Database: `lobe_tool`
    --

    -- --------------------------------------------------------
    --

    -- Table structure for table `learning_object`
    --

CREATE TABLE `learning_object`(
    `code` VARCHAR(200) NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    `rubrik_id` INT(11) NOT NULL,
    `assigned_by` VARCHAR(100) NOT NULL,
    `assigned_to` VARCHAR(100) NOT NULL,
    `grade` VARCHAR(100) NOT NULL,
    `subject` VARCHAR(100) NOT NULL,
    `chapter` VARCHAR(100) NOT NULL,
    `repository_name` VARCHAR(100) NOT NULL,
    `created_ts` DATETIME NOT NULL,
    `updated_ts` DATETIME NOT NULL,
    `status` VARCHAR(20) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
--

-- Dumping data for table `learning_object`
--

INSERT INTO `learning_object`(
    `code`,
    `name`,
    `rubrik_id`,
    `assigned_by`,
    `assigned_to`,
    `grade`,
    `subject`,
    `chapter`,
    `repository_name`,
    `created_ts`,
    `updated_ts`,
    `status`
)
VALUES(
    'zxc',
    '',
    1,
    'ankit@gmail.com',
    'ankit@gmail.com',
    '',
    '',
    '',
    '',
    '0000-00-00 00:00:00',
    '0000-00-00 00:00:00',
    ''
);
-- --------------------------------------------------------
--

-- Table structure for table `lobe_scores`
--

CREATE TABLE `lobe_scores`(
    `learning_object_id` VARCHAR(200) NOT NULL,
    `question_id` INT(11) NOT NULL,
    `score` INT(11) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
--

-- Dumping data for table `lobe_scores`
--

INSERT INTO `lobe_scores`(
    `learning_object_id`,
    `question_id`,
    `score`
)
VALUES('zxc', 1, 0);
-- --------------------------------------------------------
--

-- Table structure for table `permission`
--

CREATE TABLE `permission`(
    `id` INT(11) NOT NULL,
    `name` VARCHAR(20) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
--

-- Dumping data for table `permission`
--

INSERT INTO `permission`(`id`, `name`)
VALUES(1, 'assign_rubrik'),(2, 'create_rubrik'),(3, 'evaluate_rubrik'),(4, 'evaluator_analytics'),(5, 'generator_analytics');
-- --------------------------------------------------------
--

-- Table structure for table `quality_dimension_master`
--

CREATE TABLE `quality_dimension_master`(
    `id` INT(11) NOT NULL,
    `dimension_name` VARCHAR(50) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
--

-- Dumping data for table `quality_dimension_master`
--

INSERT INTO `quality_dimension_master`(`id`, `dimension_name`)
VALUES(1, 'Content Quality'),(2, 'Pedagogical Alignment'),(3, 'Design Efficacy'),(4, 'Technological Integration');
-- --------------------------------------------------------
--

-- Table structure for table `question_master`
--

CREATE TABLE `question_master`(
    `id` INT(11) NOT NULL,
    `question` VARCHAR(100) NOT NULL,
    `question_meta` VARCHAR(1000) NOT NULL,
    `score_0` VARCHAR(1000) NOT NULL,
    `score_0_images` VARCHAR(1000) NOT NULL,
    `score_1` VARCHAR(1000) NOT NULL,
    `score_1_images` VARCHAR(1000) NOT NULL,
    `score_2` VARCHAR(1000) NOT NULL,
    `score_2_images` VARCHAR(1000) NOT NULL,
    `score_3` VARCHAR(1000) NOT NULL,
    `score_3_images` VARCHAR(1000) NOT NULL,
    `rubrik_type` VARCHAR(20) NOT NULL,
    `quality_dimension` VARCHAR(50) NOT NULL,
    `optional` TINYINT(1) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
--

-- Dumping data for table `question_master`
--

INSERT INTO `question_master`(
    `id`,
    `question`,
    `question_meta`,
    `score_0`,
    `score_0_images`,
    `score_1`,
    `score_1_images`,
    `score_2`,
    `score_2_images`,
    `score_3`,
    `score_3_images`,
    `rubrik_type`,
    `quality_dimension`,
    `optional`
)
VALUES(1, '', '', '', '', '', '', '', '', '', '', '', '', 0);
-- --------------------------------------------------------
--

-- Table structure for table `role`
--

CREATE TABLE `role`(
    `id` INT(11) NOT NULL,
    `description` VARCHAR(100) NOT NULL,
    `name` VARCHAR(20) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
--

-- Dumping data for table `role`
--

INSERT INTO `role`(`id`, `description`, `name`)
VALUES(1, '', 'admin'),(2, '', 'evaluator'),(3, '', 'generator');
-- --------------------------------------------------------
--

-- Table structure for table `role_permission`
--

CREATE TABLE `role_permission`(
    `role_id` INT(11) NOT NULL,
    `permission_id` INT(11) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
-- --------------------------------------------------------
--

-- Table structure for table `rubrik`
--

CREATE TABLE `rubrik`(
    `id` INT(11) NOT NULL,
    `user_email` VARCHAR(100) NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    `type` VARCHAR(20) NOT NULL,
    `status` VARCHAR(20) NOT NULL,
    `created_ts` DATETIME NOT NULL,
    `updated_ts` DATETIME NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
--

-- Dumping data for table `rubrik`
--

INSERT INTO `rubrik`(
    `id`,
    `user_email`,
    `name`,
    `type`,
    `status`,
    `created_ts`,
    `updated_ts`
)
VALUES(
    1,
    'ankit@gmail.com',
    '',
    '',
    '',
    '0000-00-00 00:00:00',
    '0000-00-00 00:00:00'
);
-- --------------------------------------------------------
--

-- Table structure for table `rubrik_quality_dimensions`
--

CREATE TABLE `rubrik_quality_dimensions`(
    `rubrik_id` INT(11) NOT NULL,
    `quality_dimension_id` INT(11) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
-- --------------------------------------------------------
--

-- Table structure for table `rubrik_questions`
--

CREATE TABLE `rubrik_questions`(
    `id` INT(11) NOT NULL,
    `rubrik_id` INT(11) NOT NULL,
    `question_id` INT(11) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
--

-- Dumping data for table `rubrik_questions`
--

INSERT INTO `rubrik_questions`(`id`, `rubrik_id`, `question_id`)
VALUES(1, 1, 1);
-- --------------------------------------------------------
--

-- Table structure for table `user`
--

CREATE TABLE `user`(
    `email` VARCHAR(100) NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `afiliation` VARCHAR(100) NOT NULL,
    `status` VARCHAR(20) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
--

-- Dumping data for table `user`
--

INSERT INTO `user`(
    `email`,
    `name`,
    `password`,
    `afiliation`,
    `status`
)
VALUES(
    'ankit@gmail.com',
    'ankit',
    'asdasdasd',
    'xaviers',
    'active'
);
-- --------------------------------------------------------
--

-- Table structure for table `user_role`
--

CREATE TABLE `user_role`(
    `user_email` VARCHAR(100) NOT NULL,
    `role_id` INT(11) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = latin1;
--

-- Indexes for dumped tables
--

--

-- Indexes for table `learning_object`
--

ALTER TABLE
    `learning_object` ADD PRIMARY KEY(`code`),
    ADD KEY `assigned_by`(`assigned_by`),
    ADD KEY `assigned_to`(`assigned_to`),
    ADD KEY `rubrik_id`(`rubrik_id`);
    --

    -- Indexes for table `lobe_scores`
    --

ALTER TABLE
    `lobe_scores` ADD PRIMARY KEY(`learning_object_id`, `question_id`),
    ADD KEY `question_id`(`question_id`);
    --

    -- Indexes for table `permission`
    --

ALTER TABLE
    `permission` ADD PRIMARY KEY(`id`);
    --

    -- Indexes for table `quality_dimension_master`
    --

ALTER TABLE
    `quality_dimension_master` ADD PRIMARY KEY(`id`);
    --

    -- Indexes for table `question_master`
    --

ALTER TABLE
    `question_master` ADD PRIMARY KEY(`id`);
    --

    -- Indexes for table `role`
    --

ALTER TABLE
    `role` ADD PRIMARY KEY(`id`);
    --

    -- Indexes for table `role_permission`
    --

ALTER TABLE
    `role_permission` ADD PRIMARY KEY(`role_id`, `permission_id`),
    ADD KEY `permission_id`(`permission_id`);
    --

    -- Indexes for table `rubrik`
    --

ALTER TABLE
    `rubrik` ADD PRIMARY KEY(`id`),
    ADD KEY `user_email`(`user_email`);
    --

    -- Indexes for table `rubrik_quality_dimensions`
    --

ALTER TABLE
    `rubrik_quality_dimensions` ADD PRIMARY KEY(`rubrik_id`, `quality_dimension_id`),
    ADD KEY `quality_dimension_id`(`quality_dimension_id`);
    --

    -- Indexes for table `rubrik_questions`
    --

ALTER TABLE
    `rubrik_questions` ADD PRIMARY KEY(`rubrik_id`, `question_id`),
    ADD UNIQUE KEY `id`(`id`),
    ADD KEY `question_id`(`question_id`);
    --

    -- Indexes for table `user`
    --

ALTER TABLE
    `user` ADD PRIMARY KEY(`email`);
    --

    -- Indexes for table `user_role`
    --

ALTER TABLE
    `user_role` ADD PRIMARY KEY(`user_email`, `role_id`),
    ADD KEY `role_id`(`role_id`);
    --

    -- AUTO_INCREMENT for dumped tables
    --

    --

    -- AUTO_INCREMENT for table `quality_dimension_master`
    --

ALTER TABLE
    `quality_dimension_master` MODIFY `id` INT(11) NOT NULL AUTO_INCREMENT,
    AUTO_INCREMENT = 5;
    --

    -- AUTO_INCREMENT for table `question_master`
    --

ALTER TABLE
    `question_master` MODIFY `id` INT(11) NOT NULL AUTO_INCREMENT,
    AUTO_INCREMENT = 2;
    --

    -- AUTO_INCREMENT for table `role`
    --

ALTER TABLE
    `role` MODIFY `id` INT(11) NOT NULL AUTO_INCREMENT,
    AUTO_INCREMENT = 4;
    --

    -- AUTO_INCREMENT for table `rubrik`
    --

ALTER TABLE
    `rubrik` MODIFY `id` INT(11) NOT NULL AUTO_INCREMENT,
    AUTO_INCREMENT = 2;
    --

    -- AUTO_INCREMENT for table `rubrik_questions`
    --

ALTER TABLE
    `rubrik_questions` MODIFY `id` INT(11) NOT NULL AUTO_INCREMENT,
    AUTO_INCREMENT = 2;
    --

    -- Constraints for dumped tables
    --

    --

    -- Constraints for table `learning_object`
    --

ALTER TABLE
    `learning_object` ADD CONSTRAINT `learning_object_ibfk_1` FOREIGN KEY(`assigned_by`) REFERENCES `user`(`email`),
    ADD CONSTRAINT `learning_object_ibfk_2` FOREIGN KEY(`assigned_to`) REFERENCES `user`(`email`),
    ADD CONSTRAINT `learning_object_ibfk_3` FOREIGN KEY(`rubrik_id`) REFERENCES `rubrik`(`id`);
    --

    -- Constraints for table `lobe_scores`
    --

ALTER TABLE
    `lobe_scores` ADD CONSTRAINT `lobe_scores_ibfk_1` FOREIGN KEY(`learning_object_id`) REFERENCES `learning_object`(`code`),
    ADD CONSTRAINT `lobe_scores_ibfk_2` FOREIGN KEY(`question_id`) REFERENCES `question_master`(`id`);
    --

    -- Constraints for table `role_permission`
    --

ALTER TABLE
    `role_permission` ADD CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY(`role_id`) REFERENCES `role`(`id`),
    ADD CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY(`permission_id`) REFERENCES `permission`(`id`);
    --

    -- Constraints for table `rubrik`
    --

ALTER TABLE
    `rubrik` ADD CONSTRAINT `rubrik_ibfk_1` FOREIGN KEY(`user_email`) REFERENCES `user`(`email`);
    --

    -- Constraints for table `rubrik_quality_dimensions`
    --

ALTER TABLE
    `rubrik_quality_dimensions` ADD CONSTRAINT `rubrik_quality_dimensions_ibfk_1` FOREIGN KEY(`rubrik_id`) REFERENCES `rubrik`(`id`),
    ADD CONSTRAINT `rubrik_quality_dimensions_ibfk_2` FOREIGN KEY(`quality_dimension_id`) REFERENCES `quality_dimension_master`(`id`);
    --

    -- Constraints for table `rubrik_questions`
    --

ALTER TABLE
    `rubrik_questions` ADD CONSTRAINT `rubrik_questions_ibfk_1` FOREIGN KEY(`rubrik_id`) REFERENCES `rubrik`(`id`),
    ADD CONSTRAINT `rubrik_questions_ibfk_2` FOREIGN KEY(`question_id`) REFERENCES `question_master`(`id`),
    ADD CONSTRAINT `rubrik_questions_ibfk_3` FOREIGN KEY(`rubrik_id`) REFERENCES `rubrik`(`id`);
    --

    -- Constraints for table `user_role`
    --

ALTER TABLE
    `user_role` ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY(`user_email`) REFERENCES `user`(`email`),
    ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY(`role_id`) REFERENCES `role`(`id`);
COMMIT
    ;
    /*!40101
SET
    CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
    /*!40101
SET
    CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
    /*!40101
SET
    COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;