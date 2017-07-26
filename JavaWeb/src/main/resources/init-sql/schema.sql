/*
Navicat MySQL Data Transfer

Source Server         : 本地mysql
Source Server Version : 50636
Source Host           : localhost:3306
Source Database       : java_explore

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2017-07-25 22:58:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `userId` int(8) NOT NULL AUTO_INCREMENT COMMENT '用户Id',
  `nickName` varchar(32) DEFAULT '' COMMENT '用户名',
  `mobilePhone` varchar(20) NOT NULL COMMENT '手机号',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `isVIP` tinyint(3) NOT NULL DEFAULT '0' COMMENT '0不是VIP 1是VIP',
  `endTime` timestamp NULL DEFAULT NULL COMMENT 'VIP结束时间',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', 'admin', '11000000001', '15614ec99e6849620ce21f2f97d23925f70075b63f23228a', '1', '2019-08-04 17:02:01', '2017-04-26 19:33:22');
INSERT INTO `account` VALUES ('2', 'root', '11000000002', '16f67749077ad5939873d94644943194c79fa9e75d67180e', '1', '2018-09-01 15:39:01', '2017-05-16 11:31:05');
