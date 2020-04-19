/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : pro_ms

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 17/04/2020 16:34:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for p_buildings
-- ----------------------------
DROP TABLE IF EXISTS `p_buildings`;
CREATE TABLE `p_buildings`  (
  `bid` int(20) NOT NULL AUTO_INCREMENT,
  `bname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createTime` datetime(6) NOT NULL,
  `endTime` datetime(6) NOT NULL,
  `bHouseSum` int(20) NOT NULL,
  `bPersonHSum` int(20) NOT NULL,
  `bug` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`bid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_buildings
-- ----------------------------
INSERT INTO `p_buildings` VALUES (1, '1号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 20, '无');
INSERT INTO `p_buildings` VALUES (2, '2号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 10, '无');
INSERT INTO `p_buildings` VALUES (3, '3号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 4, '无');
INSERT INTO `p_buildings` VALUES (4, '4号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 0, '无');
INSERT INTO `p_buildings` VALUES (5, '5号楼', '2016-12-31 16:00:00.000000', '2020-04-30 16:00:00.000000', 24, 0, '无');
INSERT INTO `p_buildings` VALUES (6, '6号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 5, '无');
INSERT INTO `p_buildings` VALUES (7, '7号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 0, '无');
INSERT INTO `p_buildings` VALUES (8, '8号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 0, '无');
INSERT INTO `p_buildings` VALUES (9, '9号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 0, '无');
INSERT INTO `p_buildings` VALUES (10, '10号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 0, '无');
INSERT INTO `p_buildings` VALUES (11, '11号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 12, '无');
INSERT INTO `p_buildings` VALUES (12, '12号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 8, '无');
INSERT INTO `p_buildings` VALUES (13, '13号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 6, '无');
INSERT INTO `p_buildings` VALUES (14, '14号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 6, '无');
INSERT INTO `p_buildings` VALUES (15, '15号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 6, '无');
INSERT INTO `p_buildings` VALUES (16, '16号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 6, '无');
INSERT INTO `p_buildings` VALUES (17, '17号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 6, '无');
INSERT INTO `p_buildings` VALUES (18, '18号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 0, '无');
INSERT INTO `p_buildings` VALUES (19, '19号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 6, '无');
INSERT INTO `p_buildings` VALUES (20, '20号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 0, '无');
INSERT INTO `p_buildings` VALUES (21, '21号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 4, '无');
INSERT INTO `p_buildings` VALUES (22, '22号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 6, '无');
INSERT INTO `p_buildings` VALUES (23, '23号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 3, '无');
INSERT INTO `p_buildings` VALUES (24, '24号楼', '2017-01-01 08:18:10.000000', '2020-05-01 15:18:34.000000', 24, 7, '无');

-- ----------------------------
-- Table structure for p_charge
-- ----------------------------
DROP TABLE IF EXISTS `p_charge`;
CREATE TABLE `p_charge`  (
  `chargeId` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `chargeName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `chargePrice` double(10, 2) NOT NULL,
  `chargeTypeId` int(10) NOT NULL,
  `chargeTime` datetime(6) NOT NULL,
  PRIMARY KEY (`chargeId`) USING BTREE,
  INDEX `charge_type`(`chargeTypeId`) USING BTREE,
  INDEX `chargePrice`(`chargePrice`) USING BTREE,
  CONSTRAINT `charge_type` FOREIGN KEY (`chargeTypeId`) REFERENCES `p_chargetype` (`chargeTypeId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for p_chargetype
-- ----------------------------
DROP TABLE IF EXISTS `p_chargetype`;
CREATE TABLE `p_chargetype`  (
  `chargeTypeId` int(10) NOT NULL,
  `typeName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`chargeTypeId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_chargetype
-- ----------------------------
INSERT INTO `p_chargetype` VALUES (1, '水费');
INSERT INTO `p_chargetype` VALUES (2, '电费');
INSERT INTO `p_chargetype` VALUES (3, '燃气费');
INSERT INTO `p_chargetype` VALUES (4, '取暖费');
INSERT INTO `p_chargetype` VALUES (5, '其他费用');

-- ----------------------------
-- Table structure for p_device
-- ----------------------------
DROP TABLE IF EXISTS `p_device`;
CREATE TABLE `p_device`  (
  `deviceId` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `deviceName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deviceNum` int(50) NULL DEFAULT NULL,
  PRIMARY KEY (`deviceId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for p_house
-- ----------------------------
DROP TABLE IF EXISTS `p_house`;
CREATE TABLE `p_house`  (
  `hid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `hname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `hArea` double(20, 3) NOT NULL,
  `status` tinyint(10) NOT NULL,
  `bid` int(10) NOT NULL,
  PRIMARY KEY (`hid`) USING BTREE,
  INDEX `house_bid`(`bid`) USING BTREE,
  CONSTRAINT `house_bid` FOREIGN KEY (`bid`) REFERENCES `p_buildings` (`bid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_house
-- ----------------------------
INSERT INTO `p_house` VALUES ('1-1-101', '一号楼一单元一零一', 87.000, 1, 1);
INSERT INTO `p_house` VALUES ('1-1-102', '一号楼一单元一零二', 90.000, 1, 1);
INSERT INTO `p_house` VALUES ('1-1-301', '一号楼一单元三零一', 109.000, 1, 1);
INSERT INTO `p_house` VALUES ('1-2-102', '一号楼二单元一零二', 87.000, 1, 1);
INSERT INTO `p_house` VALUES ('2-1-202', '二号楼一单元二零二', 87.000, 1, 2);
INSERT INTO `p_house` VALUES ('2-2-301', '二号楼二单元三零一', 105.000, 1, 2);
INSERT INTO `p_house` VALUES ('3-1-501', '三号楼一单元五零一', 87.000, 1, 3);
INSERT INTO `p_house` VALUES ('4-2-201', '四号楼四单元二零一', 105.000, 1, 4);
INSERT INTO `p_house` VALUES ('4-2-302', '四号楼二单元三零二', 87.000, 1, 4);
INSERT INTO `p_house` VALUES ('5-2-502', '五号楼二单元五零二', 87.000, 1, 5);

-- ----------------------------
-- Table structure for p_information
-- ----------------------------
DROP TABLE IF EXISTS `p_information`;
CREATE TABLE `p_information`  (
  `infoId` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `head` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `infoTime` datetime(6) NOT NULL,
  `sortId` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uid` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`infoId`) USING BTREE,
  INDEX `info_publisher`(`uid`) USING BTREE,
  INDEX `info_sort`(`sortId`) USING BTREE,
  CONSTRAINT `info_publisher` FOREIGN KEY (`uid`) REFERENCES `p_user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `info_sort` FOREIGN KEY (`sortId`) REFERENCES `p_infosort` (`sortId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for p_infosort
-- ----------------------------
DROP TABLE IF EXISTS `p_infosort`;
CREATE TABLE `p_infosort`  (
  `sortId` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sortName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`sortId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_infosort
-- ----------------------------
INSERT INTO `p_infosort` VALUES ('1', '新鲜事');
INSERT INTO `p_infosort` VALUES ('2', '失物招领');
INSERT INTO `p_infosort` VALUES ('3', '小区通知');

-- ----------------------------
-- Table structure for p_limit
-- ----------------------------
DROP TABLE IF EXISTS `p_limit`;
CREATE TABLE `p_limit`  (
  `lid` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `lname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`lid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_limit
-- ----------------------------
INSERT INTO `p_limit` VALUES ('1', '超级管理员');
INSERT INTO `p_limit` VALUES ('2', '管理员');
INSERT INTO `p_limit` VALUES ('3', '住户');

-- ----------------------------
-- Table structure for p_pay
-- ----------------------------
DROP TABLE IF EXISTS `p_pay`;
CREATE TABLE `p_pay`  (
  `payId` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `paySum` double(50, 3) NOT NULL,
  `payDate` datetime(6) NOT NULL,
  `payPrice` double(10, 2) NOT NULL,
  `chargeType` int(10) NOT NULL,
  `houseid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userid` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`payId`) USING BTREE,
  INDEX `pay_price`(`payPrice`) USING BTREE,
  INDEX `pay_item`(`chargeType`) USING BTREE,
  INDEX `pay_user`(`userid`) USING BTREE,
  INDEX `house_hid`(`houseid`) USING BTREE,
  CONSTRAINT `pay_item` FOREIGN KEY (`chargeType`) REFERENCES `p_chargetype` (`chargeTypeId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pay_price` FOREIGN KEY (`payPrice`) REFERENCES `p_charge` (`chargePrice`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pay_user` FOREIGN KEY (`userid`) REFERENCES `p_user1` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for p_repair
-- ----------------------------
DROP TABLE IF EXISTS `p_repair`;
CREATE TABLE `p_repair`  (
  `repairId` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `repairName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `repairInfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `repairTime` datetime(6) NOT NULL,
  `deviceid` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`repairId`) USING BTREE,
  INDEX `device_id`(`deviceid`) USING BTREE,
  INDEX `re_username`(`username`) USING BTREE,
  CONSTRAINT `device_id` FOREIGN KEY (`deviceid`) REFERENCES `p_device` (`deviceId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `re_username` FOREIGN KEY (`username`) REFERENCES `p_user1` (`userName`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for p_user
-- ----------------------------
DROP TABLE IF EXISTS `p_user`;
CREATE TABLE `p_user`  (
  `userId` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `loginName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `idCard` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `telephone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `homeplace` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `workplace` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `limitid` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  `houseid` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE,
  INDEX `limit_id`(`limitid`) USING BTREE,
  INDEX `house_id`(`houseid`) USING BTREE,
  CONSTRAINT `house_id` FOREIGN KEY (`houseid`) REFERENCES `p_house` (`hid`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `limit_id` FOREIGN KEY (`limitid`) REFERENCES `p_limit` (`lid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_user
-- ----------------------------
INSERT INTO `p_user` VALUES ('1000', 'admin', '王明', 'admin', '男', '22', '211382199909280089', '15621345643', '9876789@qq.com', '辽宁朝阳', '辽宁沈阳', '1', NULL);
INSERT INTO `p_user` VALUES ('wy01', 'qwe', '李丽', '123456', '女', '23', '211321199809090012', '13421345643', '15815403421@163.com', '辽宁朝阳', '辽宁沈阳', '1', NULL);
INSERT INTO `p_user` VALUES ('wy02', 'qaz', '孙杨', '123456', '男', '34', '211321198702030034', '15521232323', '1581540342@163.com', '辽宁朝阳', '辽宁沈阳', '2', NULL);
INSERT INTO `p_user` VALUES ('wy03', 'wsx', '黄浩', '123456', '男', '35', '211342178602030023', '13234343232', '269039241@qq.com', '辽宁朝阳', '辽宁沈阳', '2', NULL);
INSERT INTO `p_user` VALUES ('wy04', 'wer', '张三', '1234', '男', '41', '211382198002030023', '18990909090', '2690399241@qq.com', '辽宁朝阳', '辽宁沈阳', '2', NULL);
INSERT INTO `p_user` VALUES ('wy05', 'asd', '李爽', '123', '女', '32', '211432198902040000', '18790909898', '117667039@163.com', '辽宁朝阳', '辽宁沈阳', '3', NULL);
INSERT INTO `p_user` VALUES ('wy06', 'azq', '赵薇', '123456', '女', '34', '211353198709280013', '13899990000', '117667039@qq.com', '辽宁朝阳', '辽宁沈阳', '3', NULL);

-- ----------------------------
-- Table structure for p_user1
-- ----------------------------
DROP TABLE IF EXISTS `p_user1`;
CREATE TABLE `p_user1`  (
  `userId` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `loginName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `upassword` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` int(20) NOT NULL,
  `card` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uemail` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `homeplace` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `workplace` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `houseid` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `limitid` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  PRIMARY KEY (`userId`) USING BTREE,
  INDEX `room_id`(`houseid`) USING BTREE,
  INDEX `userlimit_id`(`limitid`) USING BTREE,
  INDEX `userName`(`userName`) USING BTREE,
  CONSTRAINT `userlimit_id` FOREIGN KEY (`limitid`) REFERENCES `p_limit` (`lid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_user1
-- ----------------------------
INSERT INTO `p_user1` VALUES ('11101', 'qq', '李琦', '123', '女', 32, '211322198909170089', '13234323433', '12345654@qq.com', '辽宁朝阳', '辽宁沈阳', '1-1-101', NULL);
INSERT INTO `p_user1` VALUES ('11102', 'li', '李浩', '123456', '男', 24, '211321199709100089', '18890900000', '28372487@qq.com', '辽宁朝阳', '辽宁沈阳', '1-1-102', NULL);
INSERT INTO `p_user1` VALUES ('12102', 'qa', '李四', '123456', '男', 24, '211321199708080089', '13256744433', '12678544@qq.com', '辽宁朝阳', '辽宁沈阳', '1-2-102', NULL);
INSERT INTO `p_user1` VALUES ('21202', 'asd', '孙俪', '123456', '女', 32, '21132119891230092x', '18909098756', '11674544@qq.com', '辽宁朝阳', '辽宁沈阳', '2-1-202', NULL);
INSERT INTO `p_user1` VALUES ('31501', 'df', '琪琪', '123456', '女', 32, '21138219891230092x', '15643545555', '675655512@qq.com', '辽宁凌源', '辽宁沈阳', '3-1-501', NULL);
INSERT INTO `p_user1` VALUES ('42302', 'zmd', '张怡', '123456', '女', 32, '211321198911120089', '13256744433', '11674544@qq.com', '辽宁朝阳', '辽宁沈阳', '4-2-302', NULL);
INSERT INTO `p_user1` VALUES ('52502', 'spring', '孙莉', '123123', '女', 30, '21138219900918001x', '18892389244', '8374835@qq.com', '辽宁朝阳', '辽宁沈阳', '5-2-502', NULL);

-- ----------------------------
-- Event structure for sess_cleanup
-- ----------------------------
DROP EVENT IF EXISTS `sess_cleanup`;
delimiter ;;
CREATE EVENT `sess_cleanup`
ON SCHEDULE
EVERY '15' MINUTE STARTS '2018-06-03 19:56:47'
DO DELETE FROM `sessions` WHERE id IN (SELECT temp.id FROM (SELECT `id` FROM `sessions` WHERE `expires` > 0 AND `expires` < UNIX_TIMESTAMP()) AS temp)
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
