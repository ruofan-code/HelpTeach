/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : helpteach

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2020-07-21 22:18:17
*/
create database helpteach;

use helpteach;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `teacher_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `teacher_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `field1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `field2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for homework_student
-- ----------------------------
DROP TABLE IF EXISTS `homework_student`;
CREATE TABLE `homework_student` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL,
  `student_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '外键',
  `student_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `homework_teacher_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '外键',
  `homework_teacher_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `course_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `course_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `finish_time` datetime DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `filesrc` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for homework_teacher
-- ----------------------------
DROP TABLE IF EXISTS `homework_teacher`;
CREATE TABLE `homework_teacher` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL,
  `teacher_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `teacher_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `course_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `course_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `filesrc` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `finish_count` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL,
  `teacher_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '外键',
  `teacher_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `student_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '外键',
  `student_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `course_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '外键',
  `course_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `filesrc` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '1教师 2学生',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for signup_student
-- ----------------------------
DROP TABLE IF EXISTS `signup_student`;
CREATE TABLE `signup_student` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL,
  `student_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `student_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `signup_teacher_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `signup_teacher_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `sign_time` datetime DEFAULT NULL,
  `course_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `course_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for signup_teacher
-- ----------------------------
DROP TABLE IF EXISTS `signup_teacher`;
CREATE TABLE `signup_teacher` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL,
  `teacher_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `teacher_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `course_id` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `course_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `signup_count` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for student_course
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course` (
  `id` varchar(255) NOT NULL,
  `course_id` varchar(255) DEFAULT NULL,
  `course_name` varchar(255) DEFAULT NULL,
  `teacher_id` varchar(255) DEFAULT NULL,
  `teacher_name` varchar(255) DEFAULT NULL,
  `student_id` varchar(255) DEFAULT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `field1` varchar(255) DEFAULT NULL,
  `field2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(50) COLLATE utf8_bin NOT NULL,
  `role_code` int(50) DEFAULT NULL COMMENT '外键',
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sex` char(1) COLLATE utf8_bin DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;
