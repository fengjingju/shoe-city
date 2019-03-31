/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50132
Source Host           : localhost:3306
Source Database       : shoe_city

Target Server Type    : MYSQL
Target Server Version : 50132
File Encoding         : 65001

Date: 2018-12-24 21:20:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_category
-- ----------------------------
DROP TABLE IF EXISTS `tbl_category`;
CREATE TABLE `tbl_category` (
  `cid` char(32) NOT NULL,
  `category_name` varchar(256) NOT NULL,
  `isDisplayIndex` tinyint(2) NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_category
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_product
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product`;
CREATE TABLE `tbl_product` (
  `pid` char(32) NOT NULL,
  `cids` varchar(1000) DEFAULT NULL,
  `product_name` varchar(256) NOT NULL,
  `isIncludePostage` tinyint(2) NOT NULL,
  `price` double(10,2) NOT NULL,
  `size` varchar(100) NOT NULL,
  `publish_time` bigint(20) NOT NULL,
  `image_url` varchar(1000) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `detail_image_url` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_product
-- ----------------------------
INSERT INTO `tbl_product` VALUES ('e460342ac3bc49448703e6b4e12a9f29', null, '789', '0', '789.00', '789', '1541844292636', '\\images\\product\\index\\2018\\11\\aff46d063ab143ceb98f7afa37cb6024_1541844292540.jpg;', '', null);

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `uid` char(32) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES ('A6043E4CB32A40698F64449655D2EFB2', 'jiangdongxiao', 'jiangdongxiao');
