-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 27, 2019 at 01:32 PM
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
  `score_0` varchar(2000) NOT NULL,
  `score_0_images` varchar(1000) NOT NULL,
  `score_1` varchar(2000) NOT NULL,
  `score_1_images` varchar(1000) NOT NULL,
  `score_2` varchar(2000) NOT NULL,
  `score_2_images` varchar(1000) NOT NULL,
  `score_3` varchar(2000) NOT NULL,
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
(11, b'0', 1, 'C1. Is the content accurate?*', '<a class=\"evaluate\">To evaluate this question: <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">Consider the content across all components of the learning object like dynamic visualization, learning activities, examples etc. and score on the overall content accuracy. </span>', 2, 'Content is not accurate at all since it contains many incorrect facts, explanations, graphs or examples.', '', 'Some of the content is accurate, but there are one or more major errors such as incorrect explanations or examples or inaccurate activity solutions or incorrect graphical representation; or the way the content is presented is likely to cause major misconception.', '', 'Most of the content is accurate and contains correct facts, graphs, explanations and examples, and presents accurate activity solutions. However, there may be a few minor errors; or the way the content is presented may lead to minor ambiguity.', '', 'All the content is accurate, and contains correct facts, explanations, examples, graphs and activity solutions.', ''),
(12, b'0', 1, 'C2.  Is the content sufficient and up-to-date with respect to relevant advances in the topic in a grade appropriate manner?', '<a class=\"evaluate\">To evaluate this question: <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> i) Consider the content in the following components of the learning object - video/animation/simulation + learning activities, solved examples.<br> ii) Consider only advances in the topic that are appropriate for the corresponding grade, i.e. if there are advances in the topic which are understandable only in higher grades, don’t consider them while scoring this question. </br> iii)  Score on the overall content sufficiency & up-to-date level. <br> NOTE : Example of grade appropriate content is - introducing 5 states of matter in Grade 9 but not in Grade 5. </span>', 2, 'Content is not sufficient to explain the topic completely.', '', 'Content is sufficient to explain the topic but does not include any mention of grade-appropriate relevant advances in the topic.', '', 'Content is sufficient to explain the topic and contains some description of grade-appropriate relevant advances in the topic, but the description may be incomplete or contain minor errors.', '', 'Content is complete and sufficient to explain the topic, and it includes clear and correct descriptions of grade-appropriate relevant advances in the topic.', ''),
(13, b'0', 1, 'C3. Are the assessment questions and their solutions correct, clear and unambiguous?', '<a class=\"evaluate\">What does ‘correct’ mean? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">The question as well as the solution should contain correct facts and accurate scientific explanations. </span>  <a class=\"evaluate\">What does clear and unambiguous mean? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">The wording of the assessment questions and solutions should be clear and complete for the learner. The question should not be open to more than one interpretation. An example of an ambiguous question is: Write a short note on WWII. </span>', 2, 'Assessment questions are missing. ', '', 'Assessment questions are factually inaccurate; or solutions are wrong.', '', 'Most of the assessment questions are factually correct. \n\nHowever, they may be slightly ambiguous in their wording leading to lack of clarity in interpretation; or, solutions may contain minor errors. ', '', 'All assessment questions and their solutions are factually correct and unambiguous. ', ''),
(14, b'0', 1, 'C4. Is the spelling, grammar, pronunciation correct and the formatting consistent across various components of the learning object?', '<a class=\"evaluate\">Examples <i class=\"fas fa-caret-right\"></i></a><span class=\"hidden\">Examples of inconsistency in formatting include arbitrarily varying font sizes, bold/italic, justification etc.</span>', 2, 'There are serious errors in all of spelling, grammar and pronunciation leading to difficulty in comprehension.', '', 'There are some errors in spelling, grammar, pronunciation or the formatting is inconsistent which may lead to negative effect on comprehension such as misinterpretation, or need of significant effort to comprehend.', '', 'The content is free of spelling, grammar, pronunciation and formatting errors for majority of the time. However, there may be minor errors such as typos. These errors do not have any negative impact on comprehension.', '', 'Spelling, grammar, pronunciation and formatting are correct all the time.', ''),
(15, b'1', 1, 'C5. Is the language used in the learning object comprehensible to the intended learners? ', '<a class=\"evaluate\">What does ‘language used in the learning object is comprehensible’ mean? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\">It means the language used to explain the topic or to frame the assessment questions and activity is easily understandable by the target group of learners.  To ensure language comprehensibility, use of short simple sentences and familiar vocabulary i.e. words and phrases familiar to the target learner group, are recommended.</span>', 2, 'The language used in the learning object is not comprehensible to learners. It is difficult to understand and the sentences used are tough to follow. \nFor example, the vocabulary used is unfamiliar to the learners. Also, the sentences spoken or those appearing on-screen are too long and convoluted, making it difficult for the learners to get the meaning out of it.', '', 'The language used in the learning object is difficult to understand for the learners. \n\nFor example, The words/phrases used in the learning object is unfamiliar to the learners. They have to do additional work of finding the meaning of the words and phrases before they can start understanding the content or the assessment question. \n', '', 'The language used in the learning object is not easy to follow for the learners.\n\nFor example, The words/phrases used is familiar to the learners. So they do not have to do additional work of searching for the meaning of the words used. However, the sentences used are too long and convoluted. Students still have to put in extra effort to parse the sentences and make meaning out of it. ', '', 'The language used in the learning object is comprehensible to learners. It is not difficult to understand and the sentences used are easy to follow. \nFor example, the vocabulary used is familiar to the learners. Also, the sentences spoken or those appearing on-screen are short and simple to follow. Students do not need to spend extra time in comprehending the language used. ', ''),
(16, b'0', 2, 'P1. Are learning objectives stated explicitly?', '<a class=\"evaluate\">What does “stated explicitly” mean? <i class=\"fas fa-caret-right\"></i></a>\n<span class=\"hidden\">\nThe learning objectives are written as text or orally spoken. \nConsider any of the following places to locate the learning objectives: video/animation/simulation or, lesson plan or, activity (or its solution). \n</span>', 2, 'None of the learning objectives are stated explicitly.', '', 'Only some learning objectives are stated explicitly.', '', 'All necessary learning objectives are stated explicitly, but they cannot be easily found in expected places like at the beginning of the dynamic   visualization or lesson plan.', '', 'All necessary learning objectives are stated explicitly. They can be easily found in expected places like at the beginning of the dynamic visualization or lesson plan. ', ''),
(17, b'0', 2, 'P2. Are learning objectives stated correctly?', '<a class=\"evaluate\">What does this question mean?<i class=\"fas fa-caret-right\"></i></a>\n<span class=\"hidden\">\nA learning objective should satisfy the following criteria: <br>\nIs learner-centred <br>\nUses measurable action verbs <br>\nSpecifies conditions, wherever possible, under which performance is to be carried out. <br>\nThe first two criteria are particularly important while stating learning objectives.   <br>\n\nNote: If you feel that list of learning objectives stated does not include all the necessary ones, then P1 is the correct criteria to capture it. P2 only evaluates if the learning objectives have been stated correctly.\n</span>', 2, 'None of the learning objectives is stated correctly.', '', 'Few of the learning objectives are stated correctly ', '', 'Most of the learning objectives are stated correctly. ', '', 'All the learning objectives are stated correctly ', ''),
(18, b'0', 2, 'P3. Have the prerequisite topics been stated?', '<a class=\"evaluate\">Note: \n<i class=\"fas fa-caret-right\"></i></a>\n<span class=\"hidden\">\nIt is sufficient to state prerequisite topics which are immediately required at one previous level to understand the content in this learning object. There is no need to go back several levels.\n</span>', 2, 'No prerequisite topics, required to comprehend the current content, has been stated.', '', 'Only few of the key prerequisite topics, required for comprehending the current content, has been stated.', '', 'Most of the key prerequisite topics required to comprehend the current content, has been stated but there may be a few minor ones, which are not included.', '', 'All the key prerequisite topics required to comprehend the current content, has been stated.', ''),
(19, b'0', 2, 'P4. Is the content situated in an appropriate context?', '<a class=\"evaluate\">To evaluate this question: <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> Consider the content in the following components of the learning object - video/animation/simulation + learning activities, solved examples.  Score on the overall quality of context provided</span>  <a class=\"evaluate\">What does ‘context’ mean? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> A context is something that motivates the learner to care about the topic. For example, a motivational introductory scenario or a real life example or application of the topic. The context is appropriate when it is relevant and sufficient for the specific content.  </span>', 2, 'No context is provided for the content. ', '', 'Some context is occasionally provided. However, it is irrelevant to the content. ', '', 'A relevant context is provided for the content. However, the context may not be sufficient for the specific topic.', '', 'A relevant and sufficient context is provided wherever required. ', ''),
(20, b'0', 2, 'P5. Does the content in the learning object include instances under multiple conditions?', '<a class=\"evaluate\">What does this question mean? <i class=\"fas fa-caret-right\"></i></a>\n<span class=\"hidden\">\nIn order to understand a topic, a learner needs to see multiple instances i.e. multiple examples and applications of the same topic. The examples and applications should be meaningful for the topic. The more diverse they are, more effective is the learning. \n</span>\n\n<a class=\"evaluate\">Examples in science: <i class=\"fas fa-caret-right\"></i></a>\n<span class=\"hidden\">\ni) To show examples of a metal, show multiple colours (grey, golden, black, white), different textures, lustres etc. \nii) Effect of changing multiple values of a variable (such as temperature, chemical composition etc.) on the behaviour of a system. For example: While showing change in states of matter, show the effect of a range of temperature values so that all states of matter are seen.  \n</span>\n\n<a class=\"evaluate\">Example in maths: <i class=\"fas fa-caret-right\"></i></a>\n<span class=\"hidden\">\nIn measurement of length, show measurement of different objects – small as well as large, horizontal as well as vertical as well as at an angle, measuring breadth/height of rectangular objects as well as diameter of circular objects.\n</span>', 2, 'The content includes only a single instance.', '', 'The content shows a few instances, but they are insufficient or incorrectly chosen instances.', '', 'The content shows instances under multiple conditions but a key condition is missing, or there is an error in the instances shown. ', '', 'The content shows instances under all necessary and meaningful conditions.', ''),
(21, b'0', 2, 'P6. Does the content in the learning object effectively resolve common learner misconceptions?', '<a class=\"evaluate\">To evaluate this question: <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> Consider the content in the entire learning object including dynamic visualization + learning activities presented in the learning object, and solved examples. </span>  <a class=\"evaluate\">How to score this question? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> It is useful to have an idea of common learner misconceptions in a given topic, so that one can judge whether the content addresses them. This can be found by discussion with teachers or in education research literature. </span>', 2, 'There is no mention anywhere of any of the common learner misconceptions.', '', 'There are explanations to address some common learner misconceptions, but they are not effectively resolved, or key misconceptions are not addressed.', '', 'There are interactive activities that learners can use in an attempt to resolve common learner misconceptions. However, interactive activities are missing for some of the key misconceptions.', '', 'There are interactive activities that learners can use in order to elicit and resolve all the major misconceptions themselves. ', ''),
(22, b'0', 2, 'P7. Is the dynamic visualization (video/ animation) organized in segments rather than as a continuous unit?', '<a class=\"evaluate\">What does this question mean? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> Video/animation for a topic should be organized as a set of segments. There should be pauses at these segments. The segments should also be sequenced properly (such as from simple to complex content). Besides, the segmentation should be in the following way: (i) The topic in the learning object should be further divided into smaller segments based on sub-topics, (ii) Segments should be interleaved with learning activities, These are activities within the learning object that the student is supposed to do like Reflection spots,  (iii) Segments should not only be logically separate, but also appear as physically separate units.   </span>', 2, 'The dynamic   visualization is not segmented and is presented as a continuous unit.', '', 'The dynamic   visualization is segmented into a set of sub-topics but the segments are not sequenced properly. \nOr, content is not interleaved with activities, that is, a number of content segments appear one after another, with the activities appearing only towards the end.  \n', '', 'The dynamic   visualization is properly segmented into a set of sub-topics and sequenced.  Each content segment is interleaved with an activity but these segments appear as a physically continuous unit. ', '', 'The dynamic   visualization is properly segmented and sequenced into a set of sub-topics. Each content segment is interleaved with an activity. Additionally, these segments appear as physically separate units. \nOne way to make this happen is that the learning object automatically pauses after each segment containing content and presents an activity for learners. \n', ''),
(23, b'0', 2, 'P8. Are higher order thinking skills (HOTS) being addressed in the dynamic   visualization or examples or activities? ', '<a class=\"evaluate\">What does this question mean? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> Higher order thinking skill (HOTS) covers apply level and above of Revised Bloom’s Taxonomy. Ideally, dynamic visualization should include those HOTS examples or learning activities that are important for the topic.  </span>  <a class=\"evaluate\">NOTE before scoring: <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> The HOTS level of an example, activity or assessment depends on how the content has been presented to the learners. For example, an activity or assessment requires application of a formula for solution. But the formula has already been displayed in the learning object. Then all that the learners are required to do is remember that formula and plug in the given values to solve the given problem. In such cases, the cognitive level of the learning activity or assessment is no longer Apply, but comes down to Understand level. </span>', 2, 'HOTS are not being addressed in the dynamic   visualization, examples or activities and they are limited to Recall only. ', '', 'HOTS are not being addressed, but the dynamic   visualization, examples or activities address upto Understand level with learners doing some interpretation or explanation. ', '', 'HOTS are being addressed to some extent in the dynamic   visualization, examples or activities. However, some of the important HOTS relevant for the topic are missing. ', '', 'HOTS are being sufficiently addressed in the dynamic   visualization, examples or activities. \n\n(Sufficiently addressed means the HOTS important for the topic has been included.)\n', ''),
(24, b'0', 2, 'P9. Are the activities in the lesson plan aligned to the learning objectives?', '<a class=\"evaluate\">Why is this question important? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> The activities must be such that they should achieve the purpose of the learning objectives. Thus, learning objectives at higher levels need corresponding activities that allows them to go beyond recall and understand. </span>', 2, 'The lesson plan does not contain activities that are at the same cognitive level as any of the learning objectives.', '', 'The lesson plan contains activities that are at the same cognitive level for some of the learning objectives. However, a majority of learning objectives, especially at HOTS levels, are not addressed by the activities in the lesson plan.  \nOr, the activities seem like they are at the same cognitive level as the learning objectives, but the activity is unlikely to achieve the purpose of the learning objective.\n', '', 'The lesson plan contains activities that are at the same cognitive level for most learning objectives. However, a few learning objectives are missing lesson plan activities at the corresponding level.', '', 'The lesson plan contains one or more activities that are at the same cognitive level for all learning objectives.', ''),
(25, b'0', 2, 'P10. Does the learning object promote learner-centric learning?', '<a class=\"evaluate\">To evaluate this question: <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> Consider the content in the following components of learning object - dynamic visualization, learning activities and lesson plan, whichever is present. Give overall score on learner-centricity of the learning object.    </span>  <a class=\"evaluate\">What does ‘promote learner-centric learning’ mean’ and why is it important? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> The dynamic   visualization and activities should allow learners to construct understanding of the topic on their own by connecting the content to what they already know, organizing and making sense of the content, testing and revising their understanding, applying the content and so on.  This can be promoted by providing sufficient relevant activities where learners are required to do the above. Such activities are essential in getting learners engaged with the content and for effective learning.  In addition, the lesson plan should guide the teacher to go beyond mere transmission of information and demo of animation/video, while teaching with the learning object.  </span>', 2, 'The learning object does not allow the learners to work in a learner-centric manner. There is only show and tell.', '', 'There are some activities in the learning object but these are very structured and guided. Learners may wind up following the procedural steps while performing these activities. Thus, there is limited opportunity for learners to construct their own understanding.\nOr,\nthe majority of the lesson plan is teacher-centric, i.e. they are based on teachers doing transmission of information and demo of animation/video.\n', '', 'There are activities that are intended to promote learner-centric learning. However some of the important features of learner-centric learning are missing in the design of such activities, or, there are a few instances in the lesson plan which should be converted from teacher-centric to learner-centric. \n\nFor example, the activity is worded in such a way that it explains the probable solution without requiring the learners to think and express their reasoning, or, connection between the activity and the content is not clearly brought out, or learners are not required to reflect and make sense.  \n', '', 'There are sufficient activities relevant to the content that allow learners to construct their own understanding. The lesson plan, if present, requires learners to interact with these activities and do as many of the following as relevant: connect the content to what they already know, organize and make sense of the content, express their reasoning, test and revise their understanding, apply the content, do problem solving, and so on. ', ''),
(26, b'0', 2, 'P11. Does the lesson plan provide opportunities to diagnose learners’ learning gaps and fix them?', '<a class=\"evaluate\">What does ‘learning gap’ mean? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> Learning gap refers to the difference between what the learner has learnt and what he/she was expected to learn. This gap may be because learners have not grasped certain sub-concepts within the topic. Due to this inadequate grasping, learners may end up making errors in application of the concept. Some targeted help is required to fill this gap. </span>', 2, 'The lesson plan provides no opportunities for diagnosis of learners’ learning gaps by neither the teacher nor the learners themselves.', '', 'The lesson plan provides opportunities where teachers can diagnose learners’ learning gaps (like doing unstructured activities e.g. asking for doubts). It does not however equip the learners to diagnose themselves.', '', 'The lesson plan provides opportunities where learners themselves can diagnose their learning gaps (like doing structured activities like small group activities that lets them compare their solution with their classmates). However, it does not equip them to fix the gaps on their own. ', '', 'The lesson plan provides opportunities where learners can themselves diagnose their learning gaps and subsequently fix them (like discussing and reflecting on their solution with their classmates in a group).', ''),
(27, b'0', 2, 'P12. Does the lesson plan provide opportunities to learners to do group activity involving the learning object?', '<a class=\"evaluate\">What does ‘group activity involving the learning object’ mean? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> It means students sit in small groups of 2-4, discuss and debate each other’s solutions to the given activity & reasoning with their group members. ‘Involving the learning object’ means the activity questions are designed such that the feedback to the activity can be shown through the dynamic visualization component of learning object. </span>  <a class=\"evaluate\">Why this question is important? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> Students’ learn a great deal by explaining their ideas and solutions to their peers. Additionally, this hones their interpersonal skills that is critical in the 21st century. </span>', 2, 'Lesson plan does not provide any opportunities to the learners to do group activity involving the learning object. \n\n\n\nFor example, there are group activities in the lesson plan. But they do not involve the learning object. Or, there are activities in the lesson plan involving the learning object. But they are meant to be done individually by the learners. \n', '', 'Lesson plan provides opportunities to the learners to do group activity involving the learning object. But the likelihood of all learners participating is low due to the structure of the activity.\n\n\n\nFor example, teacher poses the activity question to the whole class. After a few minutes of discussion within the students, teacher calls for responses from the class. The activity question involves the learning object, of course.\n', '', 'Lesson plan provides opportunities to the learners to do group activity involving the learning object. The structure of the activity is such that there is a high likelihood that all learners in the class will participate. However, the activity does not require the learners to engage in logical argumentation with their group members.\n\nFor example, the group activity involves small learner groups (2-4) discussing the activity question posed by the teacher. This enhances likelihood of all the learners participating in the activity.\nBut the group activity simply involves learners comparing each other’s solutions & checking if they match or not. The activity does not prompt the learners to think and discuss the logical reasoning behind their solutions with their group members.\n', '', 'Lesson plan provides opportunities to the learners to do group activity involving the learning object.  The structure of the activity is such that there is a high likelihood that all learners in the class will participate. The activity also requires the learners to engage in logical argumentation with their group members.\n\nFor example, the group activity requires the learners to compare each other’s solutions, think and discuss with their group members the logical reasoning behind their solutions.\n', ''),
(28, b'0', 2, 'P13. Does the lesson plan integrate various components of the learning object - dynamic   visualization, activities, examples and assessment questions - in a meaningful way? ', '<a class=\"evaluate\">What does ‘integrate components of a learning object in a meaningful way’ mean? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> The lesson plan should use the various components of the learning object together to enhance learning, instead of treating the components in an isolated manner.  Examples of meaningful integration of various components:  i) Some of the assessment questions can be designed such that the feedback to the question refers to the animation/video on that topic. Along with the verbal explanation in the feedback, the learner is asked to watch a specific part of the animation/video that contains the corresponding visual explanation. ii) Insert an activity in between an animation / video instead of relegating it to the end.  Show animation/video for a certain time, pause it, pose an activity question and resume the animation/ video to provide solution to activity. </span>  <a class=\"evaluate\">Why is this question important? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> Such an integration will lead to effective use and reuse of the components created in the learning object. This will help learners since they will then be able to connect various parts of the learning object. This will also help e-learning companies, as it will allow them to reuse what they have already created.  </span>', 2, 'The lesson plan does not attempt to integrate various components of the learning object at all. ', '', 'The lesson plan attempts to integrate components of the learning object. However, the integration is not meaningful. That is, when one component is being used, it arbitrarily refers to another component, but such an attempt at integration is unlikely to enhance learning. ', '', 'There are some instances where the lesson plan integrates components of the learning object in a meaningful way.  However, a few meaningful integrations have been missed.', '', 'The lesson plan integrates components of the learning object wherever meaningful, i.e. when one component is being used, it calls in and uses another component in a way that is likely to enhance learning. ', ''),
(29, b'0', 2, 'P14.  Are the assessment questions aligned to the learning objectives?', '<a class=\"evaluate\">Why is this question important? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> If assessment questions are at a lower cognitive level than the learning objectives, then there is no source of evidence to test if the learning objectives have been achieved. On the other hand, if the assessment questions are higher than the learning objectives, then it will not be fair to learners. </span>', 2, 'None of the learning objectives has assessment questions aligned at the corresponding cognitive level.', '', 'Only some learning objectives have assessment questions aligned to the corresponding cognitive level.', '', 'A majority of learning objectives have assessment questions aligned to the corresponding cognitive level. ', '', 'All learning objectives have assessment questions aligned at corresponding cognitive level. ', ''),
(30, b'0', 2, 'P15. Do the assessment questions contain an appropriate context?', '<a class=\"evaluate\">What does ‘appropriate context’ mean? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> An appropriate context in assessment questions can be a relevant real-world scenario or an application on which the question is based. Note that a context is not required in all assessment questions. However, there should be sufficient questions containing a relevant and       meaningful context so that learners realize the application of the topic.  </span>', 2, 'No context is provided in any assessment question.', '', 'A context is occasionally provided in a few assessment questions but a context is missing in most assessment questions. ', '', 'A context is provided in sufficient assessment questions. However, in some questions, the context may not be meaningful. ', '', 'An appropriate and meaningful context is present in sufficient number of assessment questions.', ''),
(31, b'0', 2, 'P16. Do the assessment questions or activities in the learning object provide adequate corrective and explanatory feedback to learners?', '<a class=\"evaluate\">What does this question mean? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> Corrective and explanatory feedback goes beyond saying whether an answer is correct or wrong. It must contain an explanation of why a particular answer is wrong and what a learner can do to correct their understanding. </span>', 2, 'No feedback is provided to learners in the assessment questions or activities.', '', 'No feedback is provided to learners in the assessment questions or activities.', '', 'Feedback is provided by most of the assessment questions or activities.  The feedback          informs learner of whether their response is correct or incorrect and why so.', '', 'Feedback is provided by most of the assessment questions or activities. The feedback informs learners not only of the correct response along with explanation, but also remedial help is provided. This guides learners to revisit specific content portions for better understanding.', ''),
(32, b'0', 2, 'P17. Have any segments from the dynamic   visualization been reused, such as in the activities or assessment questions? ', '<a class=\"evaluate\">Why is this question important? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> For effective learning, it is important for a learner to be able to apply the concepts in multiple situations. Reusing segments from the dynamic visualization in the activities and assessment question will provide such an opportunity. This will also ensure that the usability of the resource can be extended.  </span>  <a class=\"evaluate\">Note: <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> This reuse may occur in the activities or assessment questions in a different learning object than the one being evaluated. This is perfectly acceptable, and this question should be given a high score. Hence, it is recommended that the same person score all learning objects of a chapter, so that they become aware of what segments are being reused across a learning object.   </span>', 2, 'No segments from the dynamic   visualization have been reused. ', '', 'A few segments from the dynamic   visualization have been reused in the activities or assessment questions. However, there are many other pedagogical opportunities for reuse, which have not been used. ', '', 'Some segments from the dynamic   visualization have been reused in the activities or assessment questions. There are a few others not reused, but could have been reused for effective learning.', '', 'Sufficient relevant segments from the dynamic   visualization have been reused in the activities or assessment questions.', ''),
(33, b'0', 2, 'P18. Is there adequate representation of diversity in the learning object? ', '<a class=\"evaluate\">What does this question mean? <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> It is important for learning materials to contain representation of diverse socio-economic groups, similar to what can be found among the learners in a classroom. Hence, the various components of a learning object – animation/video, activities and assessment questions should contain an adequate representation of different sections of society in terms of gender, race, socio-economic class, religion, looks etc.  </span>  <a class=\"evaluate\">Note: <i class=\"fas fa-caret-right\"></i></a> <span class=\"hidden\"> This question may be answered by considering all learning objects in a chapter together. You may decide to give the same score to all learning objects. However, please make sure you do evaluate each learning object. </span>', 2, 'No consideration is given to include diversity in terms of gender, race, socio-economic class, religion, looks etc.', '', 'An attempt has been made to include diversity in the learning object, but major sections of society have been missed out, leading to an inadequate representation.  ', '', 'Serious consideration has been given to include diversity, and most sections of society have adequate representation. However, a few are missing. ', '', 'A systematic effort has been implemented to include diversity in various components of the learning object. There is adequate representation of the relevant sections of society in terms of gender, race, socio-economic class, religion, looks etc.', ''),
(34, b'0', 3, 'D1. Do the graphics and sound in the learning object serve an instructional or motivational purpose and not merely cosmetic? ', '<a class=\"evaluate\">Why is this question important? <i class=\"fas fa-caret-right\"></i></a>  <span class=\"hidden\"> Graphics and sound must serve a clear purpose, preferably an instructional one. People may consider pictures and animation (such as flashing words) to be motivational but they can become distracting especially if used often. Similarly, people may consider certain sounds as motivational (such as happy/sad sounds while giving feedback) – but that becomes annoying if repeated too many times. Graphics used for cosmetic reasons (such as many colors/ fonts), or sounds such as background music can be distracting. Such technology overload can adversely affect learning.  </span>  <a class=\"evaluate\">To evaluate this question: <i class=\"fas fa-caret-right\"></i></a>  <span class=\"hidden\"> Consider the content in all parts of the learning object:  dynamic   visualization, activities, solved examples, assessments and their solutions, etc. </span>', 2, 'Graphics and sound have been used merely for cosmetic reasons, and serve no instructional or motivational purpose. ', '', 'Some but not all graphics and sound seem to serve instructional or motivational purposes. \nHowever: \ni) Their specific purpose and how they support learning may not be clear or,\nii) There exist a fair number of graphics or sound, which are unnecessary and can lead to distraction.\nOR\nSufficient amount of graphics have not been used to make the content better understandable\n', '', 'Many graphics and sound serve clear and important instructional purposes, for example, the content is better understandable due to the presence of the graphic. However, there exist some unnecessary graphics or sound that may be distracting and do not clearly support an instructional or motivational goal. ', '', 'i) Mostly all graphics and sound serve clear and important instructional purposes, for example, the content is better understandable due to the presence of the graphic. \nAND\nii) If any graphic or sound is used for motivational purposes alone, it is done sparingly. Also, they are likely to support better learning (such as higher attention).', '');
INSERT INTO `question_master` (`id`, `optional`, `quality_dimension_id`, `question`, `question_meta`, `rubrik_type`, `score_0`, `score_0_images`, `score_1`, `score_1_images`, `score_2`, `score_2_images`, `score_3`, `score_3_images`) VALUES
(35, b'0', 3, 'D2. Does the dynamic visualization component avoid adding on-screen text explanations to narrated graphics?  ', '<a class=\"evaluate\">Why this question is important? <i class=\"fas fa-caret-right\"></i></a>  <span class=\"hidden\"> Learners learn effectively from dynamic visualization  when content is explained either by voice-over (speech narration) or on-screen text explanation but not both simultaneously. Use of all three modes of communication, to present identical information, would unnecessarily place a cognitive burden on the learner. The learner is then required to decipher the visual action on-screen and also do reading and listening at the same time.  Between voice-over and on-screen text explanation, voice-over is preferred as visual and text explanation compete for the same sensory channel in humans.  </span>  <a class=\"evaluate\">However, exceptions to be made in following cases: <i class=\"fas fa-caret-right\"></i></a>  <span class=\"hidden\"> A. You can use 3 modes of communication simultaneously when you have to display: <br> i) text labels or mathematical formulas Or, <br> ii) when on-screen text differs from the voice-over. <br>  B. You can use on-screen text instead of voice-over if : <br> i) the spoken language is difficult to comprehend for the learners or, <br> ii) when static visualization like a picture or chart is being explained or, <br> iii) if voice-over becomes too lengthy. </span>', 2, 'No. \nThe dynamic visualization presents explanations to the same content as narrated graphics as well as through on-screen text. \nThis is likely to affect learning as learners have to decipher the movement on-screen (dynamic visualization) and resolve the competing pull of the textual and verbal explanation simultaneously presented to them. \n', '', 'For the most part, the dynamic visualization presents explanations as either visuals and voice-over or, as visuals and on-screen text explanation. But, in a few places it adds on-screen text to narrated visuals. Also, these few cases do not fall under the exception cases listed. This makes it difficult for learners to comprehend the content.  \n\nOr, If it is any of the exception cases, the other way round i.e. on-screen text without voiceover would have made it easier for learners to comprehend.\n', '', 'The dynamic visualization presents content explanations as on-screen text. There is no voice-over. \nBut, use of voice-over in place of on-screen text would have made it easier for the learners to comprehend the content.\nOr, If it is any of the exception cases, the other way round i.e. on-screen text without voiceover would have made it easier for learners to comprehend.\n', '', 'The dynamic visualization present explanations as voice-over rather than on-screen text, unless it is an exception case where on-screen text is preferred over voice-over. \nHowever, it does not add on-screen text to the narrated visuals. This makes it easier for the learners to comprehend the content.\n', ''),
(36, b'0', 3, 'D3. Does the dynamic visualization include cues that highlight the organization of the essential content on-screen?', '<a class=\"evaluate\">What does ‘cues’ mean? <i class=\"fas fa-caret-right\"></i></a>  <span class=\"hidden\"> Cues mean ways in which the on-screen organization of the essential content can be highlighted. They can be visual or verbal.   Examples of visual cues are circling, bulleting points, using contrasting colors, or boxing. Like boxing the part of the onion whose cross-sectional view is being shown under the microscope. Examples of verbal cues are giving stresses in the voice-over on the important points or using indicator words like first, second, third. </span>  <a class=\"evaluate\">Why this question is important? <i class=\"fas fa-caret-right\"></i></a>  <span class=\"hidden\"> Learners learn effectively when multimedia materials like dynamic visualizations contain visual and verbal cues that highlight the organization of the essential points on-screen. Cues make the link between the different parts of the on-screen content explicit to the learner, thereby aiding the learning process. </span>', 2, 'No. None of the important information in the dynamic visualization is highlighted by using cues (visual or verbal). Thus, there is a chance that learners might overlook the important information presented on-screen.', '', 'Some but not all of the important information in the dynamic visualization is highlighted by using cues (visual or verbal). \n\nHowever, some unimportant information has unnecessarily been highlighted. This can lead to  distraction for the learners.\n', '', 'Some but not all of the important information in the dynamic visualization is highlighted by using cues (visual or verbal). However, unimportant information has not been unnecessarily highlighted. \n\nSo, learners might overlook some of the important information that was not highlighted. But they are not distracted by highlighting of unnecessary information.\n', '', 'The dynamic visualization uses cues (visual or verbal) to focus learners’ attention on all the important information presented in the dynamic visualization. It does not highlight any unimportant information.  \n\nSo, learners do not overlook any of the important information presented in the dynamic visualization. Also, they are distracted by highlighting of any unimportant information.\n', ''),
(37, b'0', 3, 'D4. Does the learning object enable the learners to process the relevant information quickly?', '<a class=\"evaluate\">Why this question is important?<i class=\"fas fa-caret-right\"></i></a>\r\n<span class=\"hidden\"> \r\nLearners learn effectively when all the associated information is placed together or next to each other and the voice-over is synchronized with the dynamic visualization. In case audio is not available, then printed words are placed near the corresponding graphics. In all cases, the screen should not get so visually cluttered that it gets hard to process the meaning of what is being shown. Thus look for : <br>\r\ni) Text labels appearing next to the corresponding graphics <br> \r\nii)Solutions to assessment questions appear alongside the relevant question <br>\r\niii) Voice-over synchronized with corresponding animation. <br>\r\niv) Learners can see the relevant information without having to scroll up and down or wait for the voice-over to begin. <br>\r\nv) Visually the screen is not cluttered & does not hamper processing of information by learners\r\n</span>', 2, 'No. The learning object does not assist learners to process relevant information quickly.\r\n\r\nFor example, the text labels and the corresponding graphics are not placed next to each other. Also, the screen appears cluttered and hard to process. \r\nAlso, the assessment solutions are presented without the corresponding assessment questions. \r\nAdditionally, the audio, if present, is not synchronized with the dynamic visualization.', '', 'The learning object only partially assists learners to process relevant information quickly.\r\nFor example, the text labels and the corresponding graphics are not placed next to each other Or, The screen appears cluttered and hard to process. Or, The assessment solutions are not presented with the corresponding assessment questions. \r\nOr, the audio, when present, is not synchronized with the dynamic visualization.  This may require learners to pause till the audio starts.  ', '', 'The learning object majorly assists learners to process relevant information quickly. \r\n\r\nFor example, text labels or assessment solutions are placed next to the corresponding graphics or assessment question. The screen does not appear cluttered. Audio, if present, is synchronized with dynamic visualization.\r\nBut, learners have to scroll up and down to locate the relevant information. This disturbs the learning flow of the learners', '', 'The learning object enables learners to process relevant information quickly.\r\n\r\nFor example, text labels or assessment solutions are placed next to the corresponding graphics or assessment question. The screen does not appear cluttered. Audio, if present, is synchronized with dynamic visualization. Additionally,\r\nlearners do not need to scroll up and down to locate relevant information', ''),
(38, b'0', 3, 'D5. Does the learning object use a conversational style and include on-screen coaches?', '<a class=\"evaluate\">Why is this question important? <i class=\"fas fa-caret-right\"></i></a>\r\n<span class=\"hidden\"> \r\nIt has been shown that when e-learning content uses conversational style than a formal speech, learners learn more effectively. Usage of active voice and words such as ‘us’ and ‘we’ is preferred to passive voice. For example: “Let us now see what happens…” is preferred to “It can be seen that…” Similarly, the use of on-screen coaches/tutors or ‘pedagogical agents’, such as age-appropriate cartoon character or teacher-like character (like Boojho & Paheli in NCERT textbooks), is known to be effective for giving instructions and feedback.\r\n</span>', 2, 'The style of speech is formal and didactic and there is no on-screen coach.', '', 'There is an attempt to use conversational style occasionally, but it is not present in many parts of the learning object. \r\nNo on-screen coach or pedagogical agent is present.', '', 'A serious effort has been made to use conversational style, but it is not always present. An on-screen coach or pedagogical agent is present for some tasks (such as giving motivation), but is not present for an important task (such as giving feedback). ', '', 'A conversational style has been used throughout the learning object, to give instructions and feedback. In addition, an on-screen coach or pedagogical agent is present which motivates and guides the learner along with giving instructions and feedback. ', ''),
(41, b'0', 3, 'D6. Are all the related visual elements in the learning object placed together on screen?', '<a class=\"evaluate\">To evaluate this question: <i class=\"fas fa-caret-right\"></i></a>\r\n<span class=\"hidden\"> \r\nConsider the content in the entire learning object including dynamic   visualization, activities and assessments.\r\nWhat does this question mean? <br>\r\nVisual elements should be sorted into groups based on their purpose. Each group should be placed away from other groups to enable the learner to achieve the purpose/s.<br>\r\n\r\nExample of a group of visual elements can be all the buttons needed for manipulating video parameters like brightness, contrast, color. Another group of buttons can be for manipulating the audio parameters like volume, bass, treble. So, all video parameters should appear near each other as a group but separated from the set of audio parameters. \r\n</span>', 2, 'Visual elements with different purposes are mixed up on-screen i.e. the elements are visually unsorted. \r\n\r\nFor example, the brightness button is placed next to the volume button but their purpose is different.', 'imgs/D6_S0.png', 'Visual elements are grouped but the grouping is random, not based on a purpose.\r\n\r\n \r\nFor example, the visual elements in the display area like play-pause buttons and the elements in the interactive area like input boxes, slider bars are sorted into separate groups. But the elements within the interactive area are not further grouped based on a purpose. ', 'imgs/D6_S1.png', 'Visual elements are grouped based on a purpose. However, lack of visual distance between the groups makes them appear related to each other.\r\n\r\nFor example, the intended purpose is to show a pair of input box and a slider bar for each variable and replicate them for multiple variables. If similar visual distance is kept between an input box-slider bar  (in a row) and input box-input box (in subsequent rows), then learners may erroneously perceive it as two columns: One of input boxes and other of slider bars. ', 'imgs/D6_S2.png', 'Visual elements are grouped together based on a purpose and each group is visually separated on-screen.\r\n\r\nFor example, the intended purpose is to show a set of input box and slider bar for each variable. The visual space between each pair (of input box-slider bar) is more than the space between the components of the pair (input box and slider bar). This helps the learner perceive each pair of input box- slider bar as a distinct pair. ', 'imgs/D6_S3.png'),
(42, b'0', 3, 'D7. Are all the visual elements, needed for doing a given task, clearly visible without extraneous distractions?', '<a class=\"evaluate\">What does this question mean?<i class=\"fas fa-caret-right\"></i></a>\r\n<span class=\"hidden\"> \r\nIt means the user interface provides the user with visual elements like buttons, toolbars, menus etc., that are easy to find for the user. Also, their appearance makes their functionality evident like two double arrows to indicate fast forward etc.\r\nThe interface does not confuse the user with unnecessary or extraneous distractions like irrelevant buttons or flashing graphics. \r\n</span>', 2, 'The user cannot easily find the visual elements required to do the given task. It is also not clear what their functionality is.\r\n\r\nFor example, The visual elements irrelevant for the task are not dimmed/ hidden making it difficult for the user to find the relevant ones. Also, their appearance does not make their functionality evident like the search button has only ‘S’ written on it.', 'imgs/D7_S0.jpg', 'The user cannot easily find the visual elements required to do the given task. However, once found, it is clear what the functionality of the visual element is. \r\n\r\nFor example, The visual elements irrelevant for the task are not dimmed/hidden making it difficult for the user to find the relevant ones. However, their functionality is evident from their appearance like the search button has image of a magnifying glass.', 'imgs/D7_S1.jpg', 'The user can easily find the visual elements required to do the given task. However, once found, it is not clear what the functionality of the visual element is. \r\n\r\nFor example, The visual elements irrelevant for the task are dimmed /hidden. Only the relevant ones are visible and prominent. But their appearance does not make their functionality evident like the search button has only ‘S’ written on it.', 'imgs/D7_S2.jpg', 'The user can easily find the visual elements required to do the given task. It is also clear what the functionality of the visual element is. \r\n\r\nFor example, the visual elements irrelevant for the task are dimmed / hidden.  Only the relevant ones are visible and prominent. The caption or symbol used to represent the element, like Up arrow to scroll up or, next button to move to next page or, magnifying glass label for search, makes its functionality evident.', 'imgs/D7_S3.jpg'),
(43, b'0', 3, 'D8. Does the interface contain appropriate cues to make the user aware of possible interactions?', '<a class=\"evaluate\">What do ‘cues’ mean? <i class=\"fas fa-caret-right\"></i></a>\r\n<span class=\"hidden\"> \r\nCues are visual indications to the users about possible interaction with the interface. For example, from the visual look of each element in the interface, it should be clear whether it is clickable or not or, is it draggable or not. Example - a blinking button to indicate that the user can click the button. Other clickable elements are buttons, sliders, hyperlinks, navigation buttons. <br>\r\nNote: \r\nYou may decide to give the same score to all learning objects for this question. However, please give make sure you do evaluate each learning object.\r\n</span>', 2, 'The interface contains no cues to make the user aware of possible interactions. \r\n\r\nFor example, there is no difference in the appearance of clickable versus non-clickable elements in the interface. ', 'imgs/D8_S0.jpg', 'The interface contains some cues to indicate the expected output but the cues are insufficient or not useful in terms of where / when they are provided. \r\n\r\nFor example,  Having an answer-box without a cue whether it expects alphabetic or numeric input is insufficient (Image 1) , Showing an error message about the type of input after the user submits their answer is not very useful (Image 2).', 'imgs/D8_S1.png,imgs/D8_S1_Image_2.png', 'The interface contains sufficient cues, which inform the user about the possibility of interaction, but they are not clear in communicating what will be the effect of the interaction. \r\n\r\nFor example, there is a clickable button which when clicked pauses the AV. However, no conventional symbols (such as two vertical lines) or explicit labels (saying ‘Pause’) have been used to indicate the function of the button.', 'imgs/D8_S2.jpg', 'The interface contains sufficient and clear cues which inform the user about the possibility of interaction and also communicate what will be the effect of the interaction. \r\n\r\nFor example, a clickable button whose purpose is to pause the AV is accompanied by conventional symbols (such as two vertical lines for pause) or explicit labels (saying ‘Pause’) to indicate that pressing this button would pause the AV. ', 'imgs/D8_S3.jpg'),
(45, b'0', 3, 'D9. Does the interface provide appropriate response (textual, auditory or visual) upon user action?', '<a class=\"evaluate\">Examples of appropriate response:<i class=\"fas fa-caret-right\"></i></a>\r\n<span class=\"hidden\"> \r\nInvalid or wrong input by the user should result in one or more of the following responses that indicate error:<br>\r\nan error message popup box (textual), or, <br>\r\nan alarm sound (auditory), or,<br>\r\nappearance of symbols such as red cross (visual)<br>\r\nNote: \r\nYou may decide to give the same score to all learning objects for this question. However, please give make sure you do evaluate each learning object.\r\n</span>', 2, 'For example,  User submits an assessment solution (Image 1) but\r\ndoes not get any confirmation about submission / non-submission, or correct/incorrect (Image 2).', 'imgs/D9_S0.png,imgs/D9_S0_Image_2.png', 'Examples of inappropriate responses:\r\nResponses which are delayed, placed far away from user’s action area (Image 1), too big (Image 2) / small (Image 3) as compared to other content, wrong or non-conventional (Image 4)  graphics (showing broken wire image for network error instead of yellow triangle)', 'imgs/D9_S1.png,imgs/D9_S1_Image_2.png,imgs/D9_S1_Image_3.png,imgs/D9_S1_Image_4.png', 'from further interaction. \r\n\r\nFor example, Flashy, oversized pop-ups that covers the screen and obstructs the user from interacting further.', 'imgs/D9_S2.png', 'For example, Warning or success notifications are displayed immediately following user action, are in proximity of the interaction area or, does not visually obstruct the other information on the screen.', 'imgs/D9_S3.png'),
(46, b'0', 3, 'D10. Are the look and feel and the on-screen placement of the visual elements consistent throughout the learning object?', '<a class=\"evaluate\">What does ‘look and feel’ mean?<i class=\"fas fa-caret-right\"></i></a>\r\n<span class=\"hidden\">\r\nLook and feel means the colour, shape, layout as well as the behaviour of the visual elements on-screen. For example, the behaviour of the slider bar is normally if you drag the slider to the right, it increases the value. But if at other times same action results in decrease in value then that is termed inconsistency in behaviour of the visual element – slider bar.\r\n</span>', 2, 'Learners need to spend time to familiarize themselves with the learning object interface since the look and feel and on-screen placement of the visual elements are inconsistent throughout the learning object.  \r\n\r\nFor example, The back button is sometimes blue (Image 1), sometimes green (Image 2), in colour Or, sometimes circular (Image 1) , sometimes rectangular in shape (Image 2). Simultaneously, it is placed at different points on the screen across different parts of the learning object. ', 'imgs/D10_S0.png,imgs/D10_S0_Image_2.png ', 'Learners need to spend time in finding out the visual elements on-screen since their placement is not consistent. However, their look and feel is consistent throughout the learning object.   \r\n\r\n\r\n\r\n\r\nFor example, On-screen placement of the slider bar changes across the learning object. In some places, it is placed to the right of the screen \r\n(Image 1), and in other places it is placed at the bottom of the screen (Image 2). So, learners have to search for the slider bar each time.\r\nOnce located, the functionality of the slider bar remains consistent i.e. dragging slider to the right will increase value', 'imgs/D10_S1.png,imgs/D10_S1_Image_2.png ', 'It is easy for the learners to find the visual elements on-screen since their placement is consistent throughout the learning object. However, the changing look and feel hampers usability of the learning object. \r\n\r\n\r\n\r\nFor example, On-screen placement of the slider bar is consistent across the learning object. So, learners can quickly find the slider bar. However, the behaviour of the slider bar is not consistent throughout the learning object. Sometimes dragging the slider bar up or to the right causes increase in value (Image 1) but at other times same action results in decrease in value (Image 2).', 'imgs/D10_S2.png,imgs/D10_S2_Image_2.png ', 'It is easy for the learners to find the visual elements on-screen and interact with them since their look and feel and behaviour is consistent throughout the learning object.\r\n\r\n\r\n\r\nFor example, The dragging of the slider bar up or to the right causes increase in value (Image 1,2 &3). Also, the slider bar is always to the left of the screen throughout the learning object (Image 1, 2 &3). ', 'imgs/D10_S3.png,imgs/D10_S3_Image_2.png,imgs/D10_S3_Image_3.png'),
(47, b'0', 4, 'T1. Are the types of visualizations in the learning object chosen such that they are suitable for the corresponding content type?', '<a class=\"evaluate\">What does ‘visualization’ mean?<i class=\"fas fa-caret-right\"></i></a>\n<span class=\"hidden\"> \nThe term ‘visualization’ refers to diagrams, videos, animations or simulations. \n</span>\n\n<a class=\"evaluate\">To evaluate this question<i class=\"fas fa-caret-right\"></i></a>\n<span class=\"hidden\"> \nConsider dynamic visualizations in all parts of the learning object. \n</span>\n\n<a class=\"evaluate\">Why is this question important?<i class=\"fas fa-caret-right\"></i></a>\n<span class=\"hidden\"> \nFor learning to be effective, a particular content type should be mapped to its corresponding suitable type of visualization. For example, use:  Diagram if content type is fact Animation or video if content type is process <br>\nSimulation if content type is concept/principle \n</span>', 2, 'In none of the visualizations present in the learning object, the visualization type suitably maps to the content type. ', '', 'In less than half of the visualizations present in the learning object, the visualization type suitably maps to the content type.', '', 'In half or more of the visualizations present in the learning object, the visualization type suitably maps to the content type.', '', 'In all the visualizations present in the learning object, the visualization type suitably maps to the content type.', ''),
(48, b'0', 4, 'T2. Do the dynamic   visualization, activities, or assessments include interactivity features that are meaningful for learning the corresponding content?  ', '<a class=\"evaluate\">Examples <i class=\"fas fa-caret-right\"></i></a>\n<span class=\"hidden\">Examples of interactivity features are slider bars, input boxes, drag & drop, drop-down, activity question popping in between content presentation.\n‘Meaningful for learning’ means that the choice of interactivity features is determined by the learning purpose they serve. For example, \nfor navigation – use back/next, play/pause buttons<br>\nfor choosing from a set of values – <br>\nif no. of values < 5 , use radio buttons;  <br>\nif no. of values >5, use drop-down<br>\nfor control/modify variables – use slider bars<br>\nfor matching and selection – use drag & drop</span>', 2, 'No interactivity features are included.', '', 'Some interactivity features may be included. However, they are insufficient or not meaningful for learning the content.', '', 'Most necessary interactivity features are included and they are meaningful for learning the content. However, some interactivity features included are superfluous.', '', 'All necessary interactivity features are included in the dynamic   visualization, activities, or assessments. They are all meaningful for learning the content. Superfluous interactivities are avoided. ', ''),
(49, b'0', 4, 'T3. Is there sufficient guidance provided to teachers on how to effectively use the various components of the learning object together? ', '<a class=\"evaluate\">Why is this question important?<i class=\"fas fa-caret-right\"></i></a>\r\n<span class=\"hidden\"> \r\nGuidance should be provided to teachers on how to make effective use of the various components of the learning object together. \r\nConsider various components such as the dynamic   visualization, activities, examples, assessment questions. Effective use of various components means teachers should be able to use one or more components of the learning object with another to enhance learner learning. For example, teachers can pose assessment questions, feedback to which can be visually shown through the Dynamic   visualization. \r\nNote: This guidance should go beyond user help for technology features.\r\n</span>', 2, 'Provides no guidance to teachers on how to use the various components of the learning object together.', '', 'Provides guidance to teachers on which components of the learning object can be used together. However, no guidance provided on how to use the components together.\r\n\r\nFor example, the guidance will include the following instructions:\r\nPose this activity question from the learning object and use the dynamic   visualization along with the activity.', '', 'Provides some stepwise guidance to teachers on which components of the learning object can be used together and how. However, the steps are not detailed enough, or do not contain sufficient information to help the teacher design and implement a lesson to meaningfully use the technology features with suitable pedagogical strategy for the content.   Example1: The following guidance is provided to teach with an animation in the learning object:  Step 1 – Play the animation  Step 2 – Pause the animation and pose this prediction activity  Step 3 – Ask learners to make the prediction   Step 4 – Resume playing the animation. Some details are missing – when exactly to pause the animation, should learners make predictions individually or in groups, what to do after resuming etc.   Example 2: The following guidance is provided to teach with a simulation in the learning object:  Step 1 - Pose the prediction activity question from the learning object Step 2 - Ask learners answer the question Step 3 - Use the simulation to show learners what will happen. Some details are missing –  should learners make predictions individually or in groups, which values to input in the simulation etc. ', '', 'Provides detailed, stepwise guidance to teachers not only on which components of the learning object can be used together, but also on how and when. Guidance is provided on how to meaningfully use the technological features. With this guidance, the teacher can design and implement the lesson to make effective use of the technology features (like interactivity) with a suitable pedagogical strategy for the content in the given class time.   Example1: The following guidance is provided to teach with an animation in the learning object:  Step 1 – Play the animation  Step 2 – Pause the animation at specific time-stamps, such as, just before the animation shows a change in behaviour of the system  Step 3 - Pose the activity question, such as, predict what will happen to the behaviour of the system  Step 4 – Ask learners to work in groups to make the prediction and explain the reasoning behind it  Step 5 – Resume playing the animation to show what will happen to the behaviour of the system. Along with it, explain why this change occurred.  Example 2: The following guidance is provided to teach with a simulation in the learning object:  Step 1 - Pose the prediction activity question   Step 2 - Let learners work in groups and make the prediction of the behaviour of the system Step 3 – Use the simulation to give feedback. Move the slider bar to specific values and show the effect on behaviour of the system  For example:  a) (science) The behaviour of water at temperature values of -10, 100 and 1000 C  b) (maths) The type of angles formed when clock hands display 12:05,12:15, 12:40 and 6:00. Step 4 - Compare and discuss the effects. ', ''),
(50, b'0', 4, 'T4. Is the interface easy to use for a new user?', '<a class=\"evaluate\">What does ‘easy-to-use’ mean? <i class=\"fas fa-caret-right\"></i></a>\r\n<span class=\"hidden\">\r\nThe interface should be intuitive to a new user, information should be easy to find, organization and hierarchy of content should be clear and consistent, screens and buttons should be consistently placed, labels and legends should be placed near the graphics.\r\n</span>', 2, 'The interface is not intuitive, information cannot be found easily, and there is severe inconsistency.  ', '', 'Some tasks within the interface are intuitive and possible to do without assistance. Some information is easy to find in expected places. But for a large number of required tasks (such as going to the previous screen or finding out the organization of content in the learning object), the interface is not easy to use. ', '', 'Most tasks within the interface are intuitive. Information can be found at most times, but occasionally some effort is needed. There is some lack of consistency, for example, in terms of placement of buttons. ', '', 'The interface is organized and easy to use. It allows users to do tasks by interacting with the various components of the learning object without difficulty or assistance related to information seeking and navigation.', ''),
(51, b'0', 4, 'T5. Does the user have appropriate control of navigation and pace within the learning object?', '<a class=\"evaluate\">Why is this question important? <i class=\"fas fa-caret-right\"></i></a>\r\n<span class=\"hidden\"> \r\nIt is important that the user be able to go from one part of the learning object to another as desired, go back & forth, interact with the learning object at their desired rate (for example slow down / speed up an animation) and contain obvious exit options.\r\n</span>', 2, 'The user has no control or flexibility. All decisions related to navigation and pace are controlled by the program. ', '', 'The user has some but not a lot of flexibility and control of navigation and pace. \r\n\r\nFor example, certain navigation paths are allowed, but other required ones are not allowed. Or, only pause and play (but not rewind / forward) are allowed as controls within an animation. ', '', 'The user has a fair amount of flexibility and control of navigation and pace. However, a few minor controls may not be possible. \r\n\r\nFor example, it may be possible to pause, restart, rewind and forward an animation but not change its speed.', '', 'The user has adequate flexibility in terms of navigation and pace control. The user can go from one part of the learning object to another as desired, go back and forth, and interact with the learning object at their desired rate. ', '');

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
(1, '2019-04-26 18:13:04.000000', 'LOBE_LITE_1', 'sample', 1, '2019-04-27 15:12:14.000000', 'ankitx@gmail.com'),
(2, '2019-04-26 18:13:38.000000', 'LOBE_PREMIUM_2', 'sample', 2, '2019-04-26 18:13:38.000000', 'ankitx@gmail.com');

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

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`email`, `name`, `password`, `status`, `affiliation`, `created_ts`) VALUES
('ankitx@gmail.com', 'ankit', 'ZehL4zUy+3hMSBKWdfnv86aCsnFowOp0Syz1juAjN8U=', 'ACTIVE', 'xaviers', '2019-04-26 18:05:50.000000'),
('theankitgajra@gmail.com', 'ankit', 'ZehL4zUy+3hMSBKWdfnv86aCsnFowOp0Syz1juAjN8U=', 'ACTIVE', 'xaviers', '2019-04-27 17:01:21.000000');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `rubrik`
--
ALTER TABLE `rubrik`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

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
