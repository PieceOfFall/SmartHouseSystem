/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : 127.0.0.1:3306
 Source Schema         : smart_house

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 13/02/2023 21:15:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for abnormal
-- ----------------------------
DROP TABLE IF EXISTS `abnormal`;
CREATE TABLE `abnormal`  (
  `start_time` datetime NOT NULL,
  `end_time` datetime NULL DEFAULT NULL,
  `risk_index` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`start_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of abnormal
-- ----------------------------

-- ----------------------------
-- Table structure for electric_appliance
-- ----------------------------
DROP TABLE IF EXISTS `electric_appliance`;
CREATE TABLE `electric_appliance`  (
  `light_bed_a` int(3) NULL DEFAULT NULL,
  `light_bed_b` int(3) NULL DEFAULT NULL,
  `light_living_room` int(3) NULL DEFAULT NULL,
  `light_bathroom` int(3) NULL DEFAULT NULL,
  `switch_a` int(3) NULL DEFAULT NULL,
  `switch_b` int(3) NULL DEFAULT NULL,
  `switch_c` int(3) NULL DEFAULT NULL,
  `curtain_a` int(3) NULL DEFAULT NULL,
  `curtain_b` int(3) NULL DEFAULT NULL,
  `warn_light` int(3) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of electric_appliance
-- ----------------------------
INSERT INTO `electric_appliance` VALUES (0, 0, 0, 0, 0, 0, 0, 0, 0, 1);

-- ----------------------------
-- Table structure for electric_history
-- ----------------------------
DROP TABLE IF EXISTS `electric_history`;
CREATE TABLE `electric_history`  (
  `time` datetime NOT NULL,
  `history_id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `electric_type` tinyint(4) NULL DEFAULT NULL,
  `operation_type` tinyint(4) NULL DEFAULT NULL,
  `electric_id` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`history_id`, `time`) USING BTREE,
  INDEX `account&time`(`time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 504 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of electric_history
-- ----------------------------

-- ----------------------------
-- Table structure for sensor
-- ----------------------------
DROP TABLE IF EXISTS `sensor`;
CREATE TABLE `sensor`  (
  `time` datetime NOT NULL,
  `gas` double(16, 2) NULL DEFAULT NULL,
  `smog` double(16, 2) NULL DEFAULT NULL,
  `temperature` double(16, 2) NULL DEFAULT NULL,
  `humidity` double(16, 2) NULL DEFAULT NULL,
  `shake` double(16, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sensor
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creat_time` datetime NULL DEFAULT NULL,
  `e_mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` int(4) NULL DEFAULT NULL,
  PRIMARY KEY (`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('347934921', 'bfc3792c3f6a6b3a652a8c92dec8ee69', '2023-01-14 12:41:02', '3479349206@qq.com', 0);
INSERT INTO `user` VALUES ('940313262', 'bfc3792c3f6a6b3a652a8c92dec8ee69', '2022-12-09 20:54:58', '940313262@qq.com', 2);

SET FOREIGN_KEY_CHECKS = 1;
