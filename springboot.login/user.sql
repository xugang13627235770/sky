/*
Navicat MySQL Data Transfer

Source Server         : 192.168.20.54
Source Server Version : 50634
Source Host           : 192.168.20.54:3306
Source Database       : ptkfz_zjj_xg

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2019-03-25 15:47:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(18) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `age` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'hello', '123', '16');
