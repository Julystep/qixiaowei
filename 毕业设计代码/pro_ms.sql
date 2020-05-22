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

 Date: 22/05/2020 20:37:04
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
  `userId` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`bid`) USING BTREE,
  INDEX `userid`(`userId`) USING BTREE,
  CONSTRAINT `userid` FOREIGN KEY (`userId`) REFERENCES `p_user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_buildings
-- ----------------------------
INSERT INTO `p_buildings` VALUES (1, '1号楼', 'wy02');
INSERT INTO `p_buildings` VALUES (2, '2号楼', 'wy03');
INSERT INTO `p_buildings` VALUES (3, '3号楼', NULL);
INSERT INTO `p_buildings` VALUES (4, '4号楼', NULL);
INSERT INTO `p_buildings` VALUES (5, '5号楼', NULL);
INSERT INTO `p_buildings` VALUES (6, '6号楼', NULL);
INSERT INTO `p_buildings` VALUES (7, '7号楼', NULL);
INSERT INTO `p_buildings` VALUES (8, '8号楼', NULL);
INSERT INTO `p_buildings` VALUES (9, '9号楼', NULL);
INSERT INTO `p_buildings` VALUES (10, '10号楼', NULL);
INSERT INTO `p_buildings` VALUES (11, '11号楼', NULL);
INSERT INTO `p_buildings` VALUES (12, '12号楼', NULL);
INSERT INTO `p_buildings` VALUES (13, '13号楼', NULL);
INSERT INTO `p_buildings` VALUES (14, '14号楼', NULL);
INSERT INTO `p_buildings` VALUES (15, '15号楼', NULL);
INSERT INTO `p_buildings` VALUES (16, '16号楼', NULL);
INSERT INTO `p_buildings` VALUES (17, '17号楼', NULL);
INSERT INTO `p_buildings` VALUES (18, '18号楼', NULL);
INSERT INTO `p_buildings` VALUES (19, '19号楼', NULL);
INSERT INTO `p_buildings` VALUES (20, '20号楼', NULL);
INSERT INTO `p_buildings` VALUES (21, '21号楼', NULL);
INSERT INTO `p_buildings` VALUES (22, '22号楼', NULL);
INSERT INTO `p_buildings` VALUES (23, '23号楼', NULL);
INSERT INTO `p_buildings` VALUES (24, '24号楼', NULL);

-- ----------------------------
-- Table structure for p_charge
-- ----------------------------
DROP TABLE IF EXISTS `p_charge`;
CREATE TABLE `p_charge`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `waterFee` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `electricityFee` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `propertyCosts` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `state` int(1) NOT NULL DEFAULT 0,
  `subtime` date NOT NULL COMMENT '发布时间(仅存储年份和月份)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userId123`(`userId`) USING BTREE,
  CONSTRAINT `userId123` FOREIGN KEY (`userId`) REFERENCES `p_user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_charge
-- ----------------------------
INSERT INTO `p_charge` VALUES (1, '120', '120', '30', '12102', 1, '2020-04-17');
INSERT INTO `p_charge` VALUES (2, '50', '50', '120', '11502', 0, '2020-04-17');
INSERT INTO `p_charge` VALUES (3, '100', '100', '100', '21202', 0, '2020-04-17');
INSERT INTO `p_charge` VALUES (4, '30', '70', '100', '12102', 0, '2020-02-29');
INSERT INTO `p_charge` VALUES (5, '30', '70', '100', '12102', 0, '2020-02-29');
INSERT INTO `p_charge` VALUES (6, '30', '70', '100', '12102', 0, '2020-02-29');

-- ----------------------------
-- Table structure for p_device
-- ----------------------------
DROP TABLE IF EXISTS `p_device`;
CREATE TABLE `p_device`  (
  `deviceId` int(20) NOT NULL AUTO_INCREMENT,
  `deviceName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`deviceId`) USING BTREE,
  INDEX `deviceId`(`deviceId`, `deviceName`) USING BTREE,
  INDEX `deviceName`(`deviceName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_device
-- ----------------------------
INSERT INTO `p_device` VALUES (2, '水管');
INSERT INTO `p_device` VALUES (3, '煤气');
INSERT INTO `p_device` VALUES (1, '电路');
INSERT INTO `p_device` VALUES (4, '网络');

-- ----------------------------
-- Table structure for p_house
-- ----------------------------
DROP TABLE IF EXISTS `p_house`;
CREATE TABLE `p_house`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `hname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `hArea` double(20, 3) NOT NULL,
  `status` tinyint(10) NULL DEFAULT NULL,
  `bid` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `hid`) USING BTREE,
  INDEX `house_bid`(`bid`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  CONSTRAINT `house_bid` FOREIGN KEY (`bid`) REFERENCES `p_buildings` (`bid`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_house
-- ----------------------------
INSERT INTO `p_house` VALUES (1, '1-1-101', '一号楼一单元一零一', 87.000, 1, 1);
INSERT INTO `p_house` VALUES (2, '1-1-102', '一号楼一单元一零二', 90.000, 0, 1);
INSERT INTO `p_house` VALUES (3, '1-2-102', '一号楼二单元一零二', 99.000, 0, 1);
INSERT INTO `p_house` VALUES (6, '2-1-202', '二号楼一单元二零二', 87.000, 0, 2);
INSERT INTO `p_house` VALUES (7, '2-2-301', '二号楼二单元三零一', 105.000, 0, 2);
INSERT INTO `p_house` VALUES (8, '3-1-501', '三号楼一单元五零一', 87.000, 0, 3);
INSERT INTO `p_house` VALUES (9, '4-2-201', '四号楼四单元二零一', 105.000, 0, 4);
INSERT INTO `p_house` VALUES (10, '4-2-302', '四号楼二单元三零二', 87.000, 0, 4);
INSERT INTO `p_house` VALUES (11, '5-2-502', '五号楼二单元五零二', 87.000, 0, 5);
INSERT INTO `p_house` VALUES (21, '1-1-502', '一号楼一单元五零一', 99.000, 0, 1);
INSERT INTO `p_house` VALUES (22, '1-1-301', '一号楼一单元三零一', 102.000, 0, 1);
INSERT INTO `p_house` VALUES (23, '1-1-401', '一号楼一单元四零一', 98.000, 0, 1);
INSERT INTO `p_house` VALUES (24, '1-2-101', '一号楼二单元一零一', 100.000, 0, 1);
INSERT INTO `p_house` VALUES (25, '1-2-501', '一号楼二单元五零一', 100.000, 0, 1);
INSERT INTO `p_house` VALUES (26, '2-1-101', '二号楼一单元一零一', 120.000, 0, 2);
INSERT INTO `p_house` VALUES (27, '2-1-502', '二号楼一单元五零二', 120.000, 0, 2);
INSERT INTO `p_house` VALUES (28, '2-1-301', '二号楼一单元三零一', 120.000, 0, 2);
INSERT INTO `p_house` VALUES (29, '3-1-101', '三号楼一单元一零一', 120.000, 0, 2);

-- ----------------------------
-- Table structure for p_information
-- ----------------------------
DROP TABLE IF EXISTS `p_information`;
CREATE TABLE `p_information`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `head` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(8000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `infotime` datetime(6) NOT NULL,
  `userId` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` int(5) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `info_publisher`(`type`) USING BTREE,
  INDEX `info_sort`(`userId`) USING BTREE,
  CONSTRAINT `info_publisher` FOREIGN KEY (`userId`) REFERENCES `p_user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_information
-- ----------------------------
INSERT INTO `p_information` VALUES (2, '停电通知', '通知一号楼一二单元4月30日停电一天，停电时间早7:00-晚5:00', '2020-05-07 00:53:10.673000', 'wy02', 3);
INSERT INTO `p_information` VALUES (3, '失物招领', '今天早上某业主在一号楼附近拾到一部黑色苹果手机，失主联系物业取手机', '2020-05-03 08:25:21.459000', 'wy02', 1);
INSERT INTO `p_information` VALUES (4, '寻物启事', '今天傍晚某业主在一号楼附近丢失一个黑色钱包，拾到者可联系物业', '2020-05-04 05:30:00.945000', 'wy02', 2);

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
-- Table structure for p_repair
-- ----------------------------
DROP TABLE IF EXISTS `p_repair`;
CREATE TABLE `p_repair`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `detail` varchar(8000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `time` datetime(6) NOT NULL,
  `userid` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` int(10) NOT NULL,
  `status` tinyint(1) NULL DEFAULT NULL,
  `picture` json NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_repair
-- ----------------------------
INSERT INTO `p_repair` VALUES (1, '下水道堵塞', '2020-05-02 06:04:15.352000', '12102', 1, 1, '[]');
INSERT INTO `p_repair` VALUES (2, '今天早上突然断电，电费余额充足，邻居家有电', '2020-05-02 06:06:26.273000', '12102', 2, 0, NULL);
INSERT INTO `p_repair` VALUES (12, '煤气故障，煤气费充足', '2020-05-02 08:57:51.256000', '21202', 3, 0, NULL);
INSERT INTO `p_repair` VALUES (13, '下班回家回家突然停网了', '2020-05-05 06:27:25.968000', '21202', 4, 0, NULL);
INSERT INTO `p_repair` VALUES (22, 'asdasdafsdfsgdf\n', '2020-05-22 12:00:56.303000', '12102', 2, 0, '[{\"imgUrl\": \"/repair/img/22/40ba455e-2567-481a-81ff-9772db1ab2a4.jpeg\"}, {\"imgUrl\": \"/repair/img/22/f0f4e7b5-15f7-42ef-a89f-caa89f31f27d.jpg\"}, {\"imgUrl\": \"/repair/img/22/567710cd-a2df-41e9-b9f5-05af9f854468.jpg\"}]');

-- ----------------------------
-- Table structure for p_user
-- ----------------------------
DROP TABLE IF EXISTS `p_user`;
CREATE TABLE `p_user`  (
  `userId` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `loginName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userName` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `idCard` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `telephone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `homeplace` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `workplace` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `limitid` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  `houseid` int(11) NULL DEFAULT NULL,
  `avator` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
  PRIMARY KEY (`userId`) USING BTREE,
  INDEX `house_id`(`houseid`) USING BTREE,
  INDEX `limit_id`(`limitid`) USING BTREE,
  INDEX `userName`(`userName`) USING BTREE,
  CONSTRAINT `userhouseid` FOREIGN KEY (`houseid`) REFERENCES `p_house` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `limit` FOREIGN KEY (`limitid`) REFERENCES `p_limit` (`lid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of p_user
-- ----------------------------
INSERT INTO `p_user` VALUES ('1000', 'admin', '王明', 'admin', '男', '22', '211382199909280089', '15621345643', '9876789@qq.com', '辽宁朝阳', '辽宁沈阳', '1', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png');
INSERT INTO `p_user` VALUES ('11502', 'vue', '李晨', '123456', '男', '35', '211352198509120012', '15523627621', '1735805659@qq.com', '辽宁锦州', '辽宁沈阳', '3', 21, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png');
INSERT INTO `p_user` VALUES ('12102', 'qa', '李四', '12345678', '男', '24', '211321199708080089', '13256744433', '12678544@qq.com', '辽宁朝阳', '辽宁沈阳', '3', 1, '/img/12102/95b3b6a7-9507-4dad-82fe-36d9ec258c67.jpg');
INSERT INTO `p_user` VALUES ('21202', 'asd', '孙俪', '12345678', '女', '32', '21132119891230092x', '18909098756', '11674544@qq.com', '辽宁朝阳', '辽宁沈阳', '3', 2, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png');
INSERT INTO `p_user` VALUES ('42302', 'zmd', '张怡', '123456', '女', '32', '211321198911120089', '13256744433', '11674544@qq.com', '辽宁朝阳', '辽宁沈阳', '3', 6, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png');
INSERT INTO `p_user` VALUES ('wy01', 'qwe', '李丽', '123456', '女', '23', '211321199809090012', '13421345643', '15815403421@163.com', '辽宁朝阳', '辽宁沈阳', '1', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png');
INSERT INTO `p_user` VALUES ('wy02', 'qaz', '孙杨', '123456', '男', '34', '211321198702030034', '15521232323', '1581540342@163.com', '辽宁朝阳', '辽宁沈阳', '2', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png');
INSERT INTO `p_user` VALUES ('wy03', 'wsx', '黄浩', '123456', '男', '35', '211342178602030023', '13234343232', '269039241@qq.com', '辽宁朝阳', '辽宁沈阳', '2', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png');
INSERT INTO `p_user` VALUES ('wy04', 'wer', '张三', '1234', '男', '41', '211382198002030023', '18990909090', '2690399241@qq.com', '辽宁朝阳', '辽宁沈阳', '2', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png');
INSERT INTO `p_user` VALUES ('wy06', 'azq', '赵薇', '123456', '女', '34', '211353198709280013', '13899990000', '117667039@qq.com', '辽宁朝阳', '辽宁沈阳', '2', NULL, 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png');

-- ----------------------------
-- Event structure for sess_cleanup
-- ----------------------------
DROP EVENT IF EXISTS `sess_cleanup`;
delimiter ;;
CREATE EVENT `sess_cleanup`
ON SCHEDULE
EVERY '15' MINUTE STARTS '2018-06-03 19:56:47'
DO DELETE FROM `sessions` WHERE id IN (SELECT temp.id FROM (SELECT `id` FROM `sessions` WHERE `expires` > 0 AND `expires` < UNIX_TIMESTAMP()) AS temp)
;
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
