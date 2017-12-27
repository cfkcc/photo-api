/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : photos

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-12-27 19:01:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pay_channel
-- ----------------------------
DROP TABLE IF EXISTS `pay_channel`;
CREATE TABLE `pay_channel` (
  `channel_id` varchar(32) NOT NULL,
  `is_abroad` tinyint(4) DEFAULT NULL COMMENT '是否支持海外市场：0：海外 1：国内',
  `channel_name` varchar(32) DEFAULT NULL COMMENT '支付渠道名称',
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  `system_type` tinyint(4) DEFAULT NULL COMMENT '系统类型',
  `app_id` varchar(256) DEFAULT NULL,
  `package_name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付渠道表';

-- ----------------------------
-- Records of pay_channel
-- ----------------------------

-- ----------------------------
-- Table structure for pay_order
-- ----------------------------
DROP TABLE IF EXISTS `pay_order`;
CREATE TABLE `pay_order` (
  `order_no` varchar(50) NOT NULL COMMENT '订单号',
  `user_id` varchar(50) NOT NULL COMMENT '用户id',
  `product_id` varchar(32) NOT NULL COMMENT '商品id',
  `order_amount` decimal(32,2) DEFAULT NULL COMMENT '订单金额',
  `channel_id` varchar(32) NOT NULL COMMENT '支付渠道ID。applePay，googlePay，bluePay。',
  `order_desc` varchar(512) DEFAULT '' COMMENT '订单描述',
  `order_state` tinyint(4) DEFAULT '0' COMMENT '订单状态.0:待支付，1:支付中，2:支付成功，-1:支付失败,4:支付取消',
  `deliver_state` tinyint(4) DEFAULT '0' COMMENT '发货状态：-1 发货失败, 0 待发货, 1 发货中, 2 发货成功',
  `pay_status_code` int(6) DEFAULT NULL COMMENT '交易结果状态码',
  `pay_status_msg` varchar(50) DEFAULT NULL COMMENT '交易结果状态名称',
  `transaction_id` varchar(50) DEFAULT NULL COMMENT '第三方的交易号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `end_time` datetime DEFAULT NULL COMMENT '完成时间',
  `replenish_states` tinyint(4) DEFAULT '0' COMMENT '补单，0 未补单 1 已补单',
  `replenish_desc` varchar(100) DEFAULT NULL COMMENT '补单描述',
  `system_type` tinyint(4) DEFAULT NULL COMMENT '系统类型',
  `passback_params` varchar(128) DEFAULT NULL COMMENT '公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。支付宝会在异步通知时将该参数原样返回。本参数必须进行UrlEncode之后才可以发送给支付宝',
  `app_id` varchar(256) DEFAULT NULL,
  `package_name` varchar(256) DEFAULT NULL,
  `pay_type` tinyint(4) DEFAULT NULL COMMENT '1:GOOGOL;2:WEIXIN;3:ZHIFUBAO',
  PRIMARY KEY (`order_no`),
  KEY `app_id_index` (`app_id`(32)),
  KEY `user_id_index` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付订单表';

-- ----------------------------
-- Records of pay_order
-- ----------------------------

-- ----------------------------
-- Table structure for photo
-- ----------------------------
DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo` (
  `photo_id` varchar(32) NOT NULL COMMENT '图片主键id',
  `img_url` varchar(512) DEFAULT NULL COMMENT '图片url',
  `group_id` varchar(32) DEFAULT NULL COMMENT '套图id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_hot` tinyint(4) DEFAULT '0' COMMENT '是否热门 0：正常 1：热门',
  `status` tinyint(4) DEFAULT '1' COMMENT '使用状态 0：禁用 1：启用',
  `flag` tinyint(4) DEFAULT '0' COMMENT '删除状态标识 -1：删除 0：未删除',
  `sort` int(11) DEFAULT NULL COMMENT '图片在套图中的展现顺序，数值越小越靠前，升序排列',
  `is_free` tinyint(4) DEFAULT '1' COMMENT '是否免费使用 0:免费 1:收费',
  `coins` decimal(11,2) DEFAULT NULL COMMENT '用户的金币数量',
  `discount` double(2,2) unsigned DEFAULT '0.00' COMMENT '被打的折扣，比如7折，被打的折扣为3折，默认被打的折扣为0折，即不打折',
  `app_id` varchar(256) DEFAULT NULL,
  `package_name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`photo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of photo
-- ----------------------------
INSERT INTO `photo` VALUES ('0000012e4e0c4f3c8c18a88ca55f26fa', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0004c8748gtf434697add98d47cde5f', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '1', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0000014a8cc34799b2cf5af38009ed19', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0004c8748gtf434697add98d47cde5f', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '2', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0000041cfd6f49a8bbb80e586d581de1', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0004c8748gtf434697add98d47cde5f', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '3', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0000043ac9714e66a81fd9205c1e4a88', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0004c8748gtf434697add98d47cde5f', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '4', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00000474f66c43d68a41a7b566526e02', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0004c8748gtf434697add98d47cde5f', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '5', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00000704ab6d4f58a187e7b740b1fdea', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0004c8748gtf434697add98d47cde5f', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '6', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00000be7d62c46f3974227ae260927fd', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0004c8748gtf434697add98d47cde5f', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '7', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00000ff5656e403bbd482b428caf6184', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0004c8748gtf434697add98d47cde5f', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '8', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00001048094341c2988ab814b80e1c9c', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0004c8748gtf434697add98d47cde5f', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '9', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('000013eaaa914e5a8c35b76b1ecbb91a', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074503148b464385adbb1de8115b89', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '1', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('000015e4f7a747bfab050f6e2e30b386', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074503148b464385adbb1de8115b89', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '2', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00001b536889420f9daea97f7e23981e', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074503148b464385adbb1de8115b89', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '3', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00001c63ec0a44ed98db4f6053b5a28e', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074503148b464385adbb1de8115b89', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '4', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00001c6d76434545abc6679f4c5e1e28', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074503148b464385adbb1de8115b89', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '5', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00001d2ea81d4f6cbb0878ce5861c4d6', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074503148b464385adbb1de8115b89', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '6', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00001e2e206249e2a6ac8c86c314668e', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074503148b464385adbb1de8115b89', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '7', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00001e8a3eae4cf4b230872a2a72b223', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074503148b464385adbb1de8115b89', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '8', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00001eca0c7142b4ade5b020c0070f05', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074503148b464385adbb1de8115b89', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '9', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0000202382e048bc9d8eeecacc9c3a9d', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '000746ffe26340548289b153e633f1d5', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '1', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00002173097e4d61b746a468f1d3201e', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '000746ffe26340548289b153e633f1d5', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '2', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00002407af984000a1f37e5b4454926e', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '000746ffe26340548289b153e633f1d5', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '3', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('000024900e744fe795818e53c3c22cca', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '000746ffe26340548289b153e633f1d5', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '4', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0000250555454347bf9a0265628b350f', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '000746ffe26340548289b153e633f1d5', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '5', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0000281e825746cfb9a5fdd27ffc9cdb', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '000746ffe26340548289b153e633f1d5', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '6', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00002a5c90dd4cef89918abb3f1506a7', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '000746ffe26340548289b153e633f1d5', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '7', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00002a65401846b9bbd42878f48c6c68', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '000746ffe26340548289b153e633f1d5', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '8', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00002a74eb0b4aa0afb7633b43b678e8', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '000746ffe26340548289b153e633f1d5', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '9', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00002a7b8ec741fe9e3bd3ac385cfe54', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '000747d6363843308f1078007a7612c5', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '1', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00002d41062d4d73975d8e3e7216f9ee', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '000747d6363843308f1078007a7612c5', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '2', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('000032a252354360a6bb59d13e218f0e', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '000747d6363843308f1078007a7612c5', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '3', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0000354a336449bab751ba3b6b14a34d', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '000747d6363843308f1078007a7612c5', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '4', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('000036064a6b4ae49078aa7907d58d96', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '000747d6363843308f1078007a7612c5', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '5', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('000039b45cee4f51a4f1f07c77e790be', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '000747d6363843308f1078007a7612c5', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '6', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00003b2fd5974446bf3332971475d5e2', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '000747d6363843308f1078007a7612c5', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '7', '1', '5.26', '0.00', null, null);
INSERT INTO `photo` VALUES ('000045fe739c411bb8056461486326ec', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '000747d6363843308f1078007a7612c5', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '8', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00004b7913f448a68ad6150705eb01be', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '000747d6363843308f1078007a7612c5', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '9', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00004b85c4804c4ba2b57469b4fa0d8d', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074a85731041f7abdc212f412bb0cc', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '1', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001a1f201d9446daf98d30631b9469a', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074a85731041f7abdc212f412bb0cc', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '2', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001a1fa18774ceab4deb06f12b2c0b3', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074a85731041f7abdc212f412bb0cc', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '3', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001a337a85c45cd927298938d6809e8', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074a85731041f7abdc212f412bb0cc', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '4', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001a91f62dc44a0a9ca33fa313aecb7', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074a85731041f7abdc212f412bb0cc', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '5', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001a9ec57a34f03b4be888aedd09b7c', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074a85731041f7abdc212f412bb0cc', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '6', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001aeeae2c743158f7af8a6e96f273d', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074a85731041f7abdc212f412bb0cc', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '7', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001b3ac97d3437980fa58ab1d2eed4a', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074a85731041f7abdc212f412bb0cc', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '8', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001b4c7dbf6461cb9f828b5068bf14f', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074a85731041f7abdc212f412bb0cc', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '9', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001bc8249314ecd8f1170c581c672bf', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074fc92dbd4b6a9b51163504c98983', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '1', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001bd92b7e3469b9c8f3c6772209448', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074fc92dbd4b6a9b51163504c98983', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '2', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001bf4e36b3472c9680a59a865c146c', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074fc92dbd4b6a9b51163504c98983', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '3', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001d41c23034a83bdfd265b7b18c16d', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074fc92dbd4b6a9b51163504c98983', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '4', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001db322a8c406d8a3a29f0a5744103', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074fc92dbd4b6a9b51163504c98983', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '5', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001dc6dcbf64585aabbe6eea352994e', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074fc92dbd4b6a9b51163504c98983', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '6', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001dfc414f94e82b7cd01ce528a3637', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074fc92dbd4b6a9b51163504c98983', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '7', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001e4b93ca845db91f36a7e5b188ae6', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074fc92dbd4b6a9b51163504c98983', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '8', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001ea87b0464568add7520163b04f2b', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00074fc92dbd4b6a9b51163504c98983', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '9', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001ee42060c420294aca95198c6d0d0', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0007540afc514ca69d58a6f4b6f60bb3', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '1', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001eec8047f41fea66eb5f348ea02f2', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0007540afc514ca69d58a6f4b6f60bb3', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '2', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001f106af834f3ca80f2c5d7b8acf74', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0007540afc514ca69d58a6f4b6f60bb3', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '3', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001f108b9f44adcb53ec39d78721d18', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0007540afc514ca69d58a6f4b6f60bb3', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '4', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001f410ce514db8b32b7052f120bed0', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0007540afc514ca69d58a6f4b6f60bb3', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '5', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001fa9e0a6e4cd09c32d8182d74d477', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '0007540afc514ca69d58a6f4b6f60bb3', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '6', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001fc367cdc4fd69269ce8defd26f68', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '0007540afc514ca69d58a6f4b6f60bb3', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '7', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001fc95ae2e40bf9dc267f068eb80ef', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '0007540afc514ca69d58a6f4b6f60bb3', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '8', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001fce792d04b2d9e1a4f7794a39c96', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '0007540afc514ca69d58a6f4b6f60bb3', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '9', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0001fe162abd4921a4e7b8631ff8d9d5', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '0007565a47f44392981af63050b25c13', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '1', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006bb3b4d5545c2b80ac1cebbed56c8', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0007565a47f44392981af63050b25c13', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '2', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006bdb247d94aa4a0a370f90eea31c8', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0007565a47f44392981af63050b25c13', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '3', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006be474af541278687d04199fef9a3', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0007565a47f44392981af63050b25c13', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '4', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006c189a9384fdb97704dc1f8b177ca', 'http://img0.imgtn.bdimg.com/it/u=2223195431,2795933770&fm=27&gp=0.jpg', '0007565a47f44392981af63050b25c13', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '5', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006c1d6567b4085af1f4f9d55a608ab', 'http://img0.imgtn.bdimg.com/it/u=2223195431,2795933770&fm=27&gp=0.jpg', '0007565a47f44392981af63050b25c13', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '6', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006c35e9dcf4936850d77d10adfde70', 'http://img0.imgtn.bdimg.com/it/u=2223195431,2795933770&fm=27&gp=0.jpg', '0007565a47f44392981af63050b25c13', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '7', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006c36013894794ac0202ec96d0f76c', 'http://img0.imgtn.bdimg.com/it/u=2223195431,2795933770&fm=27&gp=0.jpg', '0007565a47f44392981af63050b25c13', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '8', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006c747602c42e79a44f6e3c692bb7f', 'http://img0.imgtn.bdimg.com/it/u=2223195431,2795933770&fm=27&gp=0.jpg', '0007565a47f44392981af63050b25c13', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '9', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006c9d085d249899033e293b39fa0be', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '0007567e749245eda79161882891a21d', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '1', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006ceed0f474f04b68d58303e6d2288', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '0007567e749245eda79161882891a21d', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '2', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006d008673941609e02e99fafb504db', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '0007567e749245eda79161882891a21d', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '3', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006d16a062c41ea92524b75294000ad', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '0007567e749245eda79161882891a21d', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '4', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006d1835e614b51baf607c7750ab452', 'http://img0.imgtn.bdimg.com/it/u=2223195431,2795933770&fm=27&gp=0.jpg', '0007567e749245eda79161882891a21d', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '5', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006d69fe4904245b279f7f2fec99a36', 'http://img0.imgtn.bdimg.com/it/u=2223195431,2795933770&fm=27&gp=0.jpg', '0007567e749245eda79161882891a21d', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '6', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006dab8da7c4d15b360019e91898151', 'http://img0.imgtn.bdimg.com/it/u=2223195431,2795933770&fm=27&gp=0.jpg', '0007567e749245eda79161882891a21d', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '7', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006dea5d65a4359ae9f03463759a05c', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0007567e749245eda79161882891a21d', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '8', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006e0b2f1f54736a90fca84c6b9a501', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0007567e749245eda79161882891a21d', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '9', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006e1ac028a42378487502b0278e045', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00075696eedd49a1b4b97920c7368d55', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '1', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006e324238243eab4a0df72367309de', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '00075696eedd49a1b4b97920c7368d55', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '2', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006e58e4a89485ab79345e3f9f045e0', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '00075696eedd49a1b4b97920c7368d55', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '3', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006e717c8844b08bda3aa667680c149', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '00075696eedd49a1b4b97920c7368d55', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '4', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006eaa8e78043b0a0de1d705e6a6299', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00075696eedd49a1b4b97920c7368d55', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '5', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006edc8ee8446329a0d715919fbe898', 'http://img1.imgtn.bdimg.com/it/u=3135746479,2415760633&fm=27&gp=0.jpg', '00075696eedd49a1b4b97920c7368d55', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '6', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006ee8a0c9245ac998f190f80243c6b', 'http://img1.imgtn.bdimg.com/it/u=3135746479,2415760633&fm=27&gp=0.jpg', '00075696eedd49a1b4b97920c7368d55', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '7', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006f073407942a58bb270a924d5c4a7', 'http://img1.imgtn.bdimg.com/it/u=3135746479,2415760633&fm=27&gp=0.jpg', '00075696eedd49a1b4b97920c7368d55', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '8', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006f0dfb13c4dfaac59d278b2aad5de', 'http://img1.imgtn.bdimg.com/it/u=3135746479,2415760633&fm=27&gp=0.jpg', '00075696eedd49a1b4b97920c7368d55', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '9', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006f16d7e7243c18fd69d10544e6a39', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '0007570cd7e74711a2fb21a0d3400b7c', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '9', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006f2703a164057a99d4399714845dd', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '0007570cd7e74711a2fb21a0d3400b7c', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '1', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006f73280cb4c7597cb7da604a6036c', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '0007570cd7e74711a2fb21a0d3400b7c', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '2', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006f88b119447439732ee4971ed0857', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0007570cd7e74711a2fb21a0d3400b7c', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '3', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006fb79c16a4927bfe933b6ac75dbc8', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0007570cd7e74711a2fb21a0d3400b7c', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '4', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006fbea0cbd4ff58ce5cab304060935', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0007570cd7e74711a2fb21a0d3400b7c', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '5', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006ff1fbd5f4e238e4b352930c96547', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0007570cd7e74711a2fb21a0d3400b7c', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '6', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0006ffe83afa48d5b8d67cc837ce6198', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0007570cd7e74711a2fb21a0d3400b7c', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '7', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('000702b267b44eb4a6fd821e52c807e6', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0007570cd7e74711a2fb21a0d3400b7c', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '8', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00070449a75c45839a1399ba7aa8e254', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '0007578b511b4f08a5ce8b09e1f8ac06', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '1', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00070640e5114446ac69a1abc68a7001', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '0007578b511b4f08a5ce8b09e1f8ac06', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '2', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00070721038b429d95f64bd7f9bd281d', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '0007578b511b4f08a5ce8b09e1f8ac06', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '3', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00070b50c6d840ed8b887fe47dc44cdb', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '0007578b511b4f08a5ce8b09e1f8ac06', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '4', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00070bbe8daa4ca391ad6a86e8aca797', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '0007578b511b4f08a5ce8b09e1f8ac06', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '5', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00070ce02b324e7394418959c0936b91', 'http://img4.imgtn.bdimg.com/it/u=4259452041,2878290499&fm=27&gp=0.jpg', '0007578b511b4f08a5ce8b09e1f8ac06', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '6', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00070e91015a4791bcc6116841591022', 'http://img4.imgtn.bdimg.com/it/u=4259452041,2878290499&fm=27&gp=0.jpg', '0007578b511b4f08a5ce8b09e1f8ac06', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '7', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('000716f936ff49d38fe8bd05c2f3c5fe', 'http://img4.imgtn.bdimg.com/it/u=4259452041,2878290499&fm=27&gp=0.jpg', '0007578b511b4f08a5ce8b09e1f8ac06', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '8', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0007172a36cc4a9584908d8deeadd573', 'http://img4.imgtn.bdimg.com/it/u=4259452041,2878290499&fm=27&gp=0.jpg', '0007578b511b4f08a5ce8b09e1f8ac06', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '9', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('000717a7cd3b4df98106ce34de5be71b', 'http://img4.imgtn.bdimg.com/it/u=4259452041,2878290499&fm=27&gp=0.jpg', '00075af25f3a48469bc38a5eef281262', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '1', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('000718542184471ab0a615e12e28120b', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '00075af25f3a48469bc38a5eef281262', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '2', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('000719bb81a647279367c2d50fafae19', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '00075af25f3a48469bc38a5eef281262', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '3', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00071c75c3754c2bb77819e8fa3d0ec5', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '00075af25f3a48469bc38a5eef281262', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '4', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00071c804b4345c18c867f25fe57785f', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '00075af25f3a48469bc38a5eef281262', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '5', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00071f9ebcf64f9aa9abe18e95085b18', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00075af25f3a48469bc38a5eef281262', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '6', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('000721c2371945fbab812b8f9b4cd9a6', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00075af25f3a48469bc38a5eef281262', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '7', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00072b5fab8348e29b2e5c5ecdc56856', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00075af25f3a48469bc38a5eef281262', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '8', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00072bf78201460293afa01f2173936d', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00075af25f3a48469bc38a5eef281262', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '9', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00072f09da45415c90107a3cee125eff', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00075ce892344f49829ab72661f933ea', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '1', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00073265187c4a4681736d59f1edb052', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00075ce892344f49829ab72661f933ea', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '2', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('000732ff83a3465ba27c4effbf0ae295', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00075c0d1fcd429fb611664554eb4919', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '1', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('000733a833374bfca66a953d0d372c72', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '00075c0d1fcd429fb611664554eb4919', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '2', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('000734947ce348498ed68b2b16e851e3', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '00075d92a51b4f219b9d5ce6afa63411', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '1', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('000735013ff6405f97e3facea3d7b033', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00075d92a51b4f219b9d5ce6afa63411', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '2', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00073d8494ce449ca0959a4306346aa9', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00075d92a51b4f219b9d5ce6afa63411', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '3', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00073dcc07c644878ab2264a7eab9dd9', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '00075d92a51b4f219b9d5ce6afa63411', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '4', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('00073f105e2e435c860e3e23fdd58045', 'http://img2.imgtn.bdimg.com/it/u=2626198443,3438980370&fm=27&gp=0.jpg', '00075e1739c147dcaf0e724b75d58557', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '1', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('0007402c1ce6494b8d6656bddeb5224b', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00075e1739c147dcaf0e724b75d58557', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '2', '1', '5.00', '0.00', null, null);
INSERT INTO `photo` VALUES ('000741de0ad240adb03f4b9b41a900b6', 'http://img2.imgtn.bdimg.com/it/u=3746075707,1914896074&fm=214&gp=0.jpg', '00075e1739c147dcaf0e724b75d58557', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:18', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:20:20', '0', '1', '0', '3', '1', '5.00', '0.00', null, null);

-- ----------------------------
-- Table structure for photo_buy_record
-- ----------------------------
DROP TABLE IF EXISTS `photo_buy_record`;
CREATE TABLE `photo_buy_record` (
  `photo_id` varchar(32) NOT NULL COMMENT '购买的图片id',
  `user_id` varchar(32) NOT NULL COMMENT '图片购买者的用户id',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态 -1：删除 0：购买失败 1：已购买成功 2：购买出错',
  `create_time` datetime DEFAULT NULL COMMENT '图片购买记录创建时间',
  `choice` tinyint(4) DEFAULT NULL COMMENT '购买方式 0：购买图片 1：购买套图',
  `app_id` varchar(256) DEFAULT NULL,
  `package_name` varchar(256) DEFAULT NULL,
  UNIQUE KEY `record_id` (`photo_id`,`user_id`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of photo_buy_record
-- ----------------------------
INSERT INTO `photo_buy_record` VALUES ('0000012e4e0c4f3c8c18a88ca55f26fa', '00004c879b3d4697add98d47cde5f1d0', '0', '2017-11-22 15:00:11', null, null, null);
INSERT INTO `photo_buy_record` VALUES ('0006c9d085d249899033e293b39fa0be', '05117794f7b544239a241aa3432479aa', '0', '2017-11-22 17:41:03', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006ceed0f474f04b68d58303e6d2288', '05117794f7b544239a241aa3432479aa', '0', '2017-11-22 17:41:03', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006d008673941609e02e99fafb504db', '05117794f7b544239a241aa3432479aa', '0', '2017-11-22 17:41:03', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006d16a062c41ea92524b75294000ad', '05117794f7b544239a241aa3432479aa', '0', '2017-11-22 17:41:03', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006d1835e614b51baf607c7750ab452', '05117794f7b544239a241aa3432479aa', '0', '2017-11-22 17:41:03', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006d69fe4904245b279f7f2fec99a36', '05117794f7b544239a241aa3432479aa', '0', '2017-11-22 17:41:03', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006dab8da7c4d15b360019e91898151', '05117794f7b544239a241aa3432479aa', '0', '2017-11-22 17:41:03', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006dea5d65a4359ae9f03463759a05c', '05117794f7b544239a241aa3432479aa', '0', '2017-11-22 17:41:03', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006e0b2f1f54736a90fca84c6b9a501', '05117794f7b544239a241aa3432479aa', '0', '2017-11-22 17:41:03', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006f2703a164057a99d4399714845dd', '00159e20c6764525b53d2d709114ab48', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006f2703a164057a99d4399714845dd', '0018f5dac5a647bbaf1a387aea40f1f4', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006f73280cb4c7597cb7da604a6036c', '00159e20c6764525b53d2d709114ab48', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006f73280cb4c7597cb7da604a6036c', '0018f5dac5a647bbaf1a387aea40f1f4', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006f88b119447439732ee4971ed0857', '00159e20c6764525b53d2d709114ab48', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006f88b119447439732ee4971ed0857', '0018f5dac5a647bbaf1a387aea40f1f4', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006fb79c16a4927bfe933b6ac75dbc8', '00159e20c6764525b53d2d709114ab48', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006fb79c16a4927bfe933b6ac75dbc8', '0018f5dac5a647bbaf1a387aea40f1f4', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006fbea0cbd4ff58ce5cab304060935', '00159e20c6764525b53d2d709114ab48', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006fbea0cbd4ff58ce5cab304060935', '0018f5dac5a647bbaf1a387aea40f1f4', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006ff1fbd5f4e238e4b352930c96547', '00159e20c6764525b53d2d709114ab48', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006ff1fbd5f4e238e4b352930c96547', '0018f5dac5a647bbaf1a387aea40f1f4', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006ffe83afa48d5b8d67cc837ce6198', '00159e20c6764525b53d2d709114ab48', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0006ffe83afa48d5b8d67cc837ce6198', '0018f5dac5a647bbaf1a387aea40f1f4', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('000702b267b44eb4a6fd821e52c807e6', '00159e20c6764525b53d2d709114ab48', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('000702b267b44eb4a6fd821e52c807e6', '0018f5dac5a647bbaf1a387aea40f1f4', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('00070449a75c45839a1399ba7aa8e254', '00159e20c6764525b53d2d709114ab48', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('00070449a75c45839a1399ba7aa8e254', '0018f5dac5a647bbaf1a387aea40f1f4', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('00070640e5114446ac69a1abc68a7001', '00159e20c6764525b53d2d709114ab48', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('00070640e5114446ac69a1abc68a7001', '0018f5dac5a647bbaf1a387aea40f1f4', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('00070721038b429d95f64bd7f9bd281d', '00159e20c6764525b53d2d709114ab48', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('00070721038b429d95f64bd7f9bd281d', '0018f5dac5a647bbaf1a387aea40f1f4', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('00070b50c6d840ed8b887fe47dc44cdb', '00159e20c6764525b53d2d709114ab48', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('00070b50c6d840ed8b887fe47dc44cdb', '0018f5dac5a647bbaf1a387aea40f1f4', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('00070bbe8daa4ca391ad6a86e8aca797', '00159e20c6764525b53d2d709114ab48', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('00070bbe8daa4ca391ad6a86e8aca797', '0018f5dac5a647bbaf1a387aea40f1f4', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('00070ce02b324e7394418959c0936b91', '00159e20c6764525b53d2d709114ab48', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('00070ce02b324e7394418959c0936b91', '0018f5dac5a647bbaf1a387aea40f1f4', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('00070e91015a4791bcc6116841591022', '00159e20c6764525b53d2d709114ab48', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('00070e91015a4791bcc6116841591022', '0018f5dac5a647bbaf1a387aea40f1f4', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0007578b511b4f08a5ce8b09e1f8ac06', '00159e20c6764525b53d2d709114ab48', '1', '2017-11-14 15:12:45', '1', null, null);
INSERT INTO `photo_buy_record` VALUES ('0007578b511b4f08a5ce8b09e1f8ac06', '0018f5dac5a647bbaf1a387aea40f1f4', '1', '2017-11-14 15:12:45', '1', null, null);

-- ----------------------------
-- Table structure for photo_group
-- ----------------------------
DROP TABLE IF EXISTS `photo_group`;
CREATE TABLE `photo_group` (
  `group_id` varchar(32) NOT NULL COMMENT '套图id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '所属者',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT NULL COMMENT '创建时间',
  `coins` decimal(11,2) DEFAULT NULL COMMENT '金币兑换数量',
  `is_hot` tinyint(4) DEFAULT '0' COMMENT '是否热门 0：正常 1：热门',
  `status` tinyint(4) DEFAULT '1' COMMENT '使用状态 0：禁用 1：启用',
  `head_photo_id` varchar(32) DEFAULT NULL COMMENT '套图封面图id',
  `flag` tinyint(4) DEFAULT '0' COMMENT '删除状态标识 -1：删除 0：未删除',
  `abroad` tinyint(4) DEFAULT NULL COMMENT '是否为海外版本 0：国内版 1：国外版',
  `discount` double(2,2) unsigned DEFAULT '0.00' COMMENT '被打的折扣，比如7折，被打的折扣为3折，默认被打的折扣为0折，即不打折',
  `app_id` varchar(256) DEFAULT NULL,
  `package_name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of photo_group
-- ----------------------------
INSERT INTO `photo_group` VALUES ('0004c8748gtf434697add98d47cde5f', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 01:18:23', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 01:18:23', '17.23', '1', '1', '11fe13341w1fv51', '0', '0', '0.20', null, null);
INSERT INTO `photo_group` VALUES ('00074503148b464385adbb1de8115b89', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 02:18:23', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 02:18:23', '27.34', '1', '1', '11fe13341w1fv51', '0', '0', '0.22', null, null);
INSERT INTO `photo_group` VALUES ('000746ffe26340548289b153e633f1d5', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 03:18:23', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 03:18:23', '25.34', '1', '1', '11fe13341w1fv51', '0', '0', '0.32', null, null);
INSERT INTO `photo_group` VALUES ('000747d6363843308f1078007a7612c5', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 04:18:23', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 04:18:23', '29.34', '1', '1', '11fe13341w1fv51', '0', '0', '0.25', null, null);
INSERT INTO `photo_group` VALUES ('00074a85731041f7abdc212f412bb0cc', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 05:18:23', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 05:18:23', '37.21', '1', '1', '11fe13341w1fv51', '0', '0', '0.52', null, null);
INSERT INTO `photo_group` VALUES ('00074fc92dbd4b6a9b51163504c98983', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 06:18:23', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 06:18:23', '33.90', '1', '1', '11fe13341w1fv51', '0', '0', '0.11', null, null);
INSERT INTO `photo_group` VALUES ('0007540afc514ca69d58a6f4b6f60bb3', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 07:18:23', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 07:18:23', '34.56', '1', '1', '11fe13341w1fv51', '0', '0', '0.30', null, null);
INSERT INTO `photo_group` VALUES ('0007565a47f44392981af63050b25c13', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 08:18:23', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 08:18:23', '41.26', '1', '1', '11fe13341w1fv51', '0', '0', '0.50', null, null);
INSERT INTO `photo_group` VALUES ('0007567e749245eda79161882891a21d', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 09:18:23', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 09:18:23', '47.58', '1', '1', '11fe13341w1fv51', '0', '1', '0.23', null, null);
INSERT INTO `photo_group` VALUES ('00075696eedd49a1b4b97920c7368d55', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 10:18:23', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 10:18:23', '59.67', '1', '1', '11fe13341w1fv51', '0', '1', '0.27', null, null);
INSERT INTO `photo_group` VALUES ('0007570cd7e74711a2fb21a0d3400b7c', '00004c879b3d4697add98d47cde5f1d0', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 11:18:23', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 11:18:23', '67.24', '1', '1', '11fe13341w1fv51', '0', '1', '0.82', null, null);
INSERT INTO `photo_group` VALUES ('0007578b511b4f08a5ce8b09e1f8ac06', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 12:18:23', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 12:18:23', '2.46', '1', '1', '11fe13341w1fv51', '0', '1', '0.90', null, null);
INSERT INTO `photo_group` VALUES ('00075af25f3a48469bc38a5eef281262', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 13:18:23', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 13:18:23', '3.56', '1', '1', '11fe13341w1fv51', '0', '1', '0.26', null, null);
INSERT INTO `photo_group` VALUES ('00075c0d1fcd429fb611664554eb4919', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:18:23', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 14:18:23', '7.54', '1', '1', '11fe13341w1fv51', '0', '1', '0.72', null, null);
INSERT INTO `photo_group` VALUES ('00075ce892344f49829ab72661f933ea', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 15:18:23', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 15:18:23', '11.24', '0', '1', '11fe13341w1fv51', '0', '0', '0.42', null, null);
INSERT INTO `photo_group` VALUES ('00075d92a51b4f219b9d5ce6afa63411', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 16:18:23', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 16:18:23', '13.57', '0', '1', '11fe13341w1fv51', '0', '0', '0.12', null, null);
INSERT INTO `photo_group` VALUES ('00075e1739c147dcaf0e724b75d58557', '893bd102c1d5481e8c1c9fe065bfe6d8', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 17:18:23', '00004c879b3d4697add98d47cde5f1d0', '2017-11-14 17:18:23', '19.67', '0', '1', '11fe13341w1fv51', '0', '0', '0.29', null, null);

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `product_id` varchar(50) NOT NULL COMMENT '商品id',
  `product_name` varchar(32) DEFAULT NULL COMMENT '商品名称',
  `product_desc` varchar(200) DEFAULT NULL COMMENT '商品描述',
  `product_price` decimal(20,2) DEFAULT NULL COMMENT '商品默认价格',
  `coins` decimal(20,2) DEFAULT NULL COMMENT '基础商品价值。如500金币/钻石。',
  `discount_ratio` decimal(2,2) DEFAULT NULL COMMENT '优惠比例。大于或等于100%的比率值；',
  `product_type` tinyint(2) DEFAULT '0' COMMENT '商品类型。0表示钻石，目前需求只提供钻石一种类型，预留扩展；',
  `channel_id` varchar(32) DEFAULT NULL COMMENT '发行渠道，即支付渠道；不同平台的终端获取的数据有差异；如iOS只获取applePay渠道的商品列表；',
  `currency` varchar(20) DEFAULT NULL COMMENT '币种',
  `product_state` tinyint(2) DEFAULT NULL COMMENT '0:下架,1:正常,2:促销中',
  `product_img` varchar(200) DEFAULT NULL COMMENT '商品图片',
  `discount_desc` varchar(30) DEFAULT NULL COMMENT '优惠描述',
  `discount_start_time` datetime DEFAULT NULL COMMENT '优惠活动开始时间',
  `discount_end_time` datetime DEFAULT NULL COMMENT '优惠活动截止时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(32) DEFAULT NULL,
  `app_id` varchar(256) DEFAULT NULL,
  `package_name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `app_id_index` (`app_id`(32)) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息表';

-- ----------------------------
-- Records of product_info
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `nick_name` varchar(20) NOT NULL COMMENT '昵称',
  `head_img` varchar(300) DEFAULT NULL COMMENT '头像',
  `user_type` tinyint(4) DEFAULT '1' COMMENT '用户类型；0：系统内部用户；第三方类型: 1:QQ,2:微信,3:微博,4:facebook,5:twitter,6:google,7:line,8:instagram',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态；0:拉黑；1：可用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `coins` decimal(11,2) DEFAULT NULL COMMENT '用户的金币数量',
  `sign` varchar(255) DEFAULT NULL COMMENT '用户签名',
  `email` varchar(255) DEFAULT NULL COMMENT '用户绑定的邮箱',
  `password` varchar(255) DEFAULT NULL,
  `sex` tinyint(4) DEFAULT NULL,
  `bir` datetime DEFAULT NULL,
  `app_id` varchar(256) DEFAULT NULL,
  `package_name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('00004c879b3d4697add98d47cde5f1d0', 'Fandy', 'http://p.3761.com/pic/25681432169575.jpg', '1', '1', '2017-12-26 18:37:36', '', '2017-12-26 18:40:28', '0.00', '', '', '', null, '2017-12-26 18:40:37', '', '');
INSERT INTO `user` VALUES ('5a018c783e75463fb3b8242c7bb10158', 'Fandy Feng', 'https://fb-s-c-a.akamaihd.net/h-ak-fbx/v/t1.0-1/p50x50/22491786_131305727525474_5297543855450502669_n.jpg?oh=1edebbe5cc219bc8d19a00c97ccfe7da&oe=5ABCB9CF&__gda__=1526153720_cfdce42dc50ad00cc6d42c5f322d415e', '1', '1', '2017-12-26 19:46:12', null, null, '0.00', null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('893bd102c1d5481e8c1c9fe065bfe6d8', 'Fandy', 'http://image.game.uc.cn/2015/3/25/10351407.jpg', '1', '1', '2017-12-26 18:37:36', null, null, '0.00', null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for user_audit
-- ----------------------------
DROP TABLE IF EXISTS `user_audit`;
CREATE TABLE `user_audit` (
  `audit_id` varchar(32) NOT NULL COMMENT '审核id',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `nickname` varchar(20) NOT NULL COMMENT '用户昵称',
  `head_img` varchar(300) NOT NULL COMMENT '头像',
  `type` tinyint(4) NOT NULL COMMENT '变更类型：1-头像；2-昵称',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '审核状态：0-待审核；1-通过；2-不通过；',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(32) DEFAULT '' COMMENT '更新用户',
  PRIMARY KEY (`audit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_audit
-- ----------------------------
INSERT INTO `user_audit` VALUES ('1', '00004c879b3d4697add98d47cde5f1d0', 'hh', ' ', '1', '0', '2017-10-31 14:54:44', '2017-10-31 15:16:31', 'test');

-- ----------------------------
-- Table structure for user_email_code
-- ----------------------------
DROP TABLE IF EXISTS `user_email_code`;
CREATE TABLE `user_email_code` (
  `code_id` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态,0为最新的待使用验证，\r\n1为无效验证码(过期或添加了更新的)，-1为已使用验证码',
  `type` tinyint(4) DEFAULT '0' COMMENT '0为绑定',
  `email` varchar(100) DEFAULT NULL COMMENT '用户绑定的邮箱',
  `code` varchar(6) DEFAULT NULL COMMENT '验证码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='手机验证码';

-- ----------------------------
-- Records of user_email_code
-- ----------------------------
INSERT INTO `user_email_code` VALUES ('2e8b20fd8e9a4eb6a60b654a3e235ebd', '5d9e246ccac64c8d810ac9bcb04682be', '-1', '0', 'chris.chen@3dzed.com', 'pj9efx', '2017-11-29 17:21:41', '2017-11-29 17:21:41');
INSERT INTO `user_email_code` VALUES ('65385e71538f46218e45fc9ad15582ef', '00004c879b3d4697add98d47cde5f1d0', '-1', '0', 'chris.chen@3dzed.com', '22afey', '2017-11-29 16:43:04', '2017-11-29 16:43:04');
INSERT INTO `user_email_code` VALUES ('735401dbb61c4abe9b585c78cf576a08', null, '-1', '0', 'chris.chen@3dzed.com', 'bniozk', '2017-11-29 17:02:38', '2017-11-29 17:02:38');
INSERT INTO `user_email_code` VALUES ('739fd2be70e14e4abadf5cc5074d3565', null, '-1', '0', 'chris.chen@3dzed.com', 'l3j5m7', '2017-11-29 16:58:46', '2017-11-29 16:58:46');
INSERT INTO `user_email_code` VALUES ('95828cad397b43e1978b9947438af486', null, '-1', '0', 'chris.chen@3dzed.com', 'b7rc6r', '2017-11-29 16:59:10', '2017-11-29 16:59:10');
INSERT INTO `user_email_code` VALUES ('a754e2ebc62d4fa09902931a15520593', '00004c879b3d4697add98d47cde5f1d0', '-1', '0', 'chris.chen@3dzed.com', 'gz69mv', '2017-11-29 16:36:33', '2017-11-29 16:36:33');
INSERT INTO `user_email_code` VALUES ('abe78ca702984d2aba28f568d88488ff', '00004c879b3d4697add98d47cde5f1d0', '1', '0', 'anthonyandrew78.cc@gmail.com', 'usvy8u', '2017-11-24 18:10:39', '2017-11-24 18:10:39');
INSERT INTO `user_email_code` VALUES ('b183d9a797154a09be6095744d48a85e', null, '1', '0', 'chris.chen@3dzed.com', 'dxzals', '2017-11-29 17:13:35', '2017-11-29 17:13:35');
INSERT INTO `user_email_code` VALUES ('d429e0757c454d829f76fb56acec9610', null, '1', '0', 'chris.chen@3dzed.com', 'ksd84i', '2017-11-29 17:15:10', '2017-11-29 17:15:10');
INSERT INTO `user_email_code` VALUES ('e9e2ce308cfb444c999b7470859a438e', '00004c879b3d4697add98d47cde5f1d0', '-1', '0', 'chris.chen@3dzed.com', '503zix', '2017-11-29 16:42:23', '2017-11-29 16:42:23');
INSERT INTO `user_email_code` VALUES ('fb3dde05cd3d4e4db93b3852457958aa', null, '-1', '0', 'chris.chen@3dzed.com', 'eilkgz', '2017-11-29 17:01:13', '2017-11-29 17:01:13');

-- ----------------------------
-- Table structure for user_fans
-- ----------------------------
DROP TABLE IF EXISTS `user_fans`;
CREATE TABLE `user_fans` (
  `fans_id` varchar(32) NOT NULL COMMENT '粉丝id',
  `user_id` varchar(32) NOT NULL COMMENT '被订阅者id',
  `status` tinyint(4) DEFAULT NULL COMMENT '订阅状态 0：未订阅 1：已订阅',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `app_id` varchar(256) DEFAULT NULL,
  `package_name` varchar(256) DEFAULT NULL,
  UNIQUE KEY `user_fans_id` (`fans_id`,`user_id`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_fans
-- ----------------------------
INSERT INTO `user_fans` VALUES ('00004c879b3d4697add98d47cde5f1d0', '0005bc3c224544fe9ea7ec6e71afc64f', '1', '2017-11-13 16:00:50', '2017-11-13 16:00:50', null, null);
INSERT INTO `user_fans` VALUES ('00004c879b3d4697add98d47cde5f1d0', '0005df670c894c4b9b0a7d7307bbd6cd', '1', '2017-11-13 16:00:50', '2017-11-13 16:00:50', null, null);
INSERT INTO `user_fans` VALUES ('00004c879b3d4697add98d47cde5f1d0', '00060851bd6a44e48b4c72af57c147fb', '1', '2017-11-13 16:00:50', '2017-11-13 16:00:50', null, null);
INSERT INTO `user_fans` VALUES ('00004c879b3d4697add98d47cde5f1d0', '00062d0042ad45e18de56e3ec3d542d1', '1', '2017-11-13 16:00:50', '2017-11-13 16:00:50', null, null);
INSERT INTO `user_fans` VALUES ('00004c879b3d4697add98d47cde5f1d0', '000644a0bfc646c793decad750e134d7', '1', '2017-11-13 16:00:50', '2017-11-13 16:00:50', null, null);
INSERT INTO `user_fans` VALUES ('00004c879b3d4697add98d47cde5f1d0', '00077260bb98424b8a60fadf36442eb2', '1', '2017-11-13 16:00:50', '2017-11-13 16:00:50', null, null);
INSERT INTO `user_fans` VALUES ('00004c879b3d4697add98d47cde5f1d0', '000844df9eff42bb8d58fb90e92b671a', '1', '2017-11-13 16:00:50', '2017-11-13 16:00:50', null, null);
INSERT INTO `user_fans` VALUES ('00004c879b3d4697add98d47cde5f1d0', '00092f4b09eb4290b9133aa46b5b8e20', '1', '2017-11-13 16:00:50', '2017-11-13 16:00:50', null, null);
INSERT INTO `user_fans` VALUES ('00004c879b3d4697add98d47cde5f1d0', '0009ccdfc98044e2a109246ee31f4ec4', '1', '2017-11-13 16:00:50', '2017-11-13 16:00:50', null, null);
INSERT INTO `user_fans` VALUES ('00004c879b3d4697add98d47cde5f1d0', '000d8e5eb5164ef7b697e838dea5d99c', '1', '2017-11-13 16:00:50', '2017-11-13 16:00:50', null, null);
INSERT INTO `user_fans` VALUES ('00004c879b3d4697add98d47cde5f1d0', '000efe03a29f4977b27536378ce2a211', '1', '2017-11-13 16:00:50', '2017-11-13 16:00:50', null, null);
INSERT INTO `user_fans` VALUES ('00004c879b3d4697add98d47cde5f1d0', '000f15e978e940e79c72e217d3d948c7', '1', '2017-11-13 16:00:50', '2017-11-13 16:00:50', null, null);
INSERT INTO `user_fans` VALUES ('00004c879b3d4697add98d47cde5f1d0', '000fe2853e9249b8988e288f7c8263fd', '1', '2017-11-13 16:00:50', '2017-11-13 16:00:50', null, null);
INSERT INTO `user_fans` VALUES ('00004c879b3d4697add98d47cde5f1d0', '0013c9900ac84a49ba3298c291f5da4e', '1', '2017-11-13 16:00:50', '2017-11-13 16:00:50', null, null);
INSERT INTO `user_fans` VALUES ('00004c879b3d4697add98d47cde5f1d0', '00140f23a74a470595453061b3207a62', '1', '2017-11-13 16:00:50', '2017-11-13 16:00:50', null, null);
INSERT INTO `user_fans` VALUES ('00004c879b3d4697add98d47cde5f1d0', '001596208d16443094327fb2234fe04f', '1', '2017-11-13 16:00:50', '2017-11-13 16:00:50', null, null);
INSERT INTO `user_fans` VALUES ('00004c879b3d4697add98d47cde5f1d0', '00159c35f44940b5990ee05a977b699b', '1', '2017-11-13 16:00:50', '2017-11-13 16:00:50', null, null);
INSERT INTO `user_fans` VALUES ('00004c879b3d4697add98d47cde5f1d0', '00159e20c6764525b53d2d709114ab48', '1', '2017-11-13 16:00:50', '2017-11-13 16:00:50', null, null);
INSERT INTO `user_fans` VALUES ('00004c879b3d4697add98d47cde5f1d0', '00162a0c172f45629f8c66d4712b3e9b', '1', '2017-11-13 16:00:50', '2017-11-13 16:00:50', null, null);
INSERT INTO `user_fans` VALUES ('00004c879b3d4697add98d47cde5f1d0', '0018f5dac5a647bbaf1a387aea40f1f4', '1', '2017-11-13 16:00:50', '2017-11-13 16:00:50', null, null);
INSERT INTO `user_fans` VALUES ('00004c879b3d4697add98d47cde5f1d0', '001a1807c9094fa0a02fbbb0604bba55', '1', '2017-11-13 16:00:51', '2017-11-13 16:00:51', null, null);
INSERT INTO `user_fans` VALUES ('0005bc3c224544fe9ea7ec6e71afc64f', '00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 16:03:13', '2017-11-13 16:03:13', null, null);
INSERT INTO `user_fans` VALUES ('0005df670c894c4b9b0a7d7307bbd6cd', '00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 16:03:13', '2017-11-13 16:03:13', null, null);
INSERT INTO `user_fans` VALUES ('00060851bd6a44e48b4c72af57c147fb', '00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 16:03:13', '2017-11-13 16:03:13', null, null);
INSERT INTO `user_fans` VALUES ('00062d0042ad45e18de56e3ec3d542d1', '00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 16:03:13', '2017-11-13 16:03:13', null, null);
INSERT INTO `user_fans` VALUES ('000644a0bfc646c793decad750e134d7', '00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 16:03:13', '2017-11-13 16:03:13', null, null);
INSERT INTO `user_fans` VALUES ('00077260bb98424b8a60fadf36442eb2', '00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 16:03:13', '2017-11-13 16:03:13', null, null);
INSERT INTO `user_fans` VALUES ('000844df9eff42bb8d58fb90e92b671a', '00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 16:03:13', '2017-11-13 16:03:13', null, null);
INSERT INTO `user_fans` VALUES ('00092f4b09eb4290b9133aa46b5b8e20', '00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 16:03:13', '2017-11-13 16:03:13', null, null);
INSERT INTO `user_fans` VALUES ('0009ccdfc98044e2a109246ee31f4ec4', '00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 16:03:13', '2017-11-13 16:03:13', null, null);
INSERT INTO `user_fans` VALUES ('000d8e5eb5164ef7b697e838dea5d99c', '00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 16:03:13', '2017-11-13 16:03:13', null, null);
INSERT INTO `user_fans` VALUES ('000efe03a29f4977b27536378ce2a211', '00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 16:03:13', '2017-11-13 16:03:13', null, null);
INSERT INTO `user_fans` VALUES ('000f15e978e940e79c72e217d3d948c7', '00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 16:03:13', '2017-11-13 16:03:13', null, null);
INSERT INTO `user_fans` VALUES ('000fe2853e9249b8988e288f7c8263fd', '00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 16:03:13', '2017-11-13 16:03:13', null, null);
INSERT INTO `user_fans` VALUES ('0013c9900ac84a49ba3298c291f5da4e', '00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 16:03:13', '2017-11-13 16:03:13', null, null);
INSERT INTO `user_fans` VALUES ('00140f23a74a470595453061b3207a62', '00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 16:03:14', '2017-11-13 16:03:14', null, null);
INSERT INTO `user_fans` VALUES ('001596208d16443094327fb2234fe04f', '00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 16:03:14', '2017-11-13 16:03:14', null, null);
INSERT INTO `user_fans` VALUES ('00159c35f44940b5990ee05a977b699b', '00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 16:03:14', '2017-11-13 16:03:14', null, null);
INSERT INTO `user_fans` VALUES ('00159e20c6764525b53d2d709114ab48', '00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 16:03:14', '2017-11-13 16:03:14', null, null);
INSERT INTO `user_fans` VALUES ('00162a0c172f45629f8c66d4712b3e9b', '00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 16:03:14', '2017-11-13 16:03:14', null, null);
INSERT INTO `user_fans` VALUES ('0018f5dac5a647bbaf1a387aea40f1f4', '00004c879b3d4697add98d47cde5f1d0', '0', '2017-11-13 16:02:20', '2017-11-13 16:02:20', null, null);
INSERT INTO `user_fans` VALUES ('001a1807c9094fa0a02fbbb0604bba55', '00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 16:03:14', '2017-11-13 16:03:14', null, null);
INSERT INTO `user_fans` VALUES ('5a018c783e75463fb3b8242c7bb10158', '00004c879b3d4697add98d47cde5f1d0', '0', '2017-12-26 20:17:13', '2017-12-26 20:17:13', null, null);

-- ----------------------------
-- Table structure for user_like
-- ----------------------------
DROP TABLE IF EXISTS `user_like`;
CREATE TABLE `user_like` (
  `liker_id` varchar(32) NOT NULL COMMENT '点赞者',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '点赞状态 0：未点赞 1：已点赞',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` varchar(32) NOT NULL COMMENT '被点赞用户',
  `app_id` varchar(256) DEFAULT NULL,
  `package_name` varchar(256) DEFAULT NULL,
  UNIQUE KEY `likeId` (`liker_id`,`user_id`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_like
-- ----------------------------
INSERT INTO `user_like` VALUES ('00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 15:21:32', '2017-11-13 15:21:32', '0005bc3c224544fe9ea7ec6e71afc64f', null, null);
INSERT INTO `user_like` VALUES ('00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 15:21:33', '2017-11-13 15:21:33', '0005df670c894c4b9b0a7d7307bbd6cd', null, null);
INSERT INTO `user_like` VALUES ('00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 15:21:33', '2017-11-13 15:21:33', '00060851bd6a44e48b4c72af57c147fb', null, null);
INSERT INTO `user_like` VALUES ('00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 15:21:33', '2017-11-13 15:21:33', '00062d0042ad45e18de56e3ec3d542d1', null, null);
INSERT INTO `user_like` VALUES ('00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 15:21:33', '2017-11-13 15:21:33', '000644a0bfc646c793decad750e134d7', null, null);
INSERT INTO `user_like` VALUES ('00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 15:21:33', '2017-11-13 15:21:33', '00077260bb98424b8a60fadf36442eb2', null, null);
INSERT INTO `user_like` VALUES ('00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 15:21:33', '2017-11-13 15:21:33', '000844df9eff42bb8d58fb90e92b671a', null, null);
INSERT INTO `user_like` VALUES ('00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 15:21:33', '2017-11-13 15:21:33', '00092f4b09eb4290b9133aa46b5b8e20', null, null);
INSERT INTO `user_like` VALUES ('00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 15:21:33', '2017-11-13 15:21:33', '0009ccdfc98044e2a109246ee31f4ec4', null, null);
INSERT INTO `user_like` VALUES ('00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 15:21:33', '2017-11-13 15:21:33', '000d8e5eb5164ef7b697e838dea5d99c', null, null);
INSERT INTO `user_like` VALUES ('00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 15:21:33', '2017-11-13 15:21:33', '000efe03a29f4977b27536378ce2a211', null, null);
INSERT INTO `user_like` VALUES ('00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 15:21:33', '2017-11-13 15:21:33', '000f15e978e940e79c72e217d3d948c7', null, null);
INSERT INTO `user_like` VALUES ('00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 15:21:33', '2017-11-13 15:21:33', '000fe2853e9249b8988e288f7c8263fd', null, null);
INSERT INTO `user_like` VALUES ('00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 15:21:33', '2017-11-13 15:21:33', '0013c9900ac84a49ba3298c291f5da4e', null, null);
INSERT INTO `user_like` VALUES ('00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 15:21:33', '2017-11-13 15:21:33', '00140f23a74a470595453061b3207a62', null, null);
INSERT INTO `user_like` VALUES ('00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 15:21:33', '2017-11-13 15:21:33', '001596208d16443094327fb2234fe04f', null, null);
INSERT INTO `user_like` VALUES ('00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 15:21:34', '2017-11-13 15:21:34', '00159c35f44940b5990ee05a977b699b', null, null);
INSERT INTO `user_like` VALUES ('00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 15:21:34', '2017-11-13 15:21:34', '00159e20c6764525b53d2d709114ab48', null, null);
INSERT INTO `user_like` VALUES ('00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 15:21:34', '2017-11-13 15:21:34', '00162a0c172f45629f8c66d4712b3e9b', null, null);
INSERT INTO `user_like` VALUES ('00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 15:21:34', '2017-11-13 15:21:34', '0018f5dac5a647bbaf1a387aea40f1f4', null, null);
INSERT INTO `user_like` VALUES ('00004c879b3d4697add98d47cde5f1d0', '1', '2017-11-13 15:21:34', '2017-11-13 15:21:34', '001a1807c9094fa0a02fbbb0604bba55', null, null);
INSERT INTO `user_like` VALUES ('00022758da034680b19453d01b12eeef', '1', '2017-11-13 15:05:14', '2017-11-13 15:05:14', '00004c879b3d4697add98d47cde5f1d0', null, null);
INSERT INTO `user_like` VALUES ('0005bc3c224544fe9ea7ec6e71afc64f', '1', '2017-11-13 15:12:27', '2017-11-13 15:12:27', '00004c879b3d4697add98d47cde5f1d0', null, null);
INSERT INTO `user_like` VALUES ('0005df670c894c4b9b0a7d7307bbd6cd', '1', '2017-11-13 15:12:28', '2017-11-13 15:12:28', '00004c879b3d4697add98d47cde5f1d0', null, null);
INSERT INTO `user_like` VALUES ('00060851bd6a44e48b4c72af57c147fb', '1', '2017-11-13 15:12:28', '2017-11-13 15:12:28', '00004c879b3d4697add98d47cde5f1d0', null, null);
INSERT INTO `user_like` VALUES ('00062d0042ad45e18de56e3ec3d542d1', '1', '2017-11-13 15:12:28', '2017-11-13 15:12:28', '00004c879b3d4697add98d47cde5f1d0', null, null);
INSERT INTO `user_like` VALUES ('000644a0bfc646c793decad750e134d7', '1', '2017-11-13 15:12:28', '2017-11-13 15:12:28', '00004c879b3d4697add98d47cde5f1d0', null, null);
INSERT INTO `user_like` VALUES ('00077260bb98424b8a60fadf36442eb2', '1', '2017-11-13 15:12:29', '2017-11-13 15:12:29', '00004c879b3d4697add98d47cde5f1d0', null, null);
INSERT INTO `user_like` VALUES ('000844df9eff42bb8d58fb90e92b671a', '1', '2017-11-13 15:12:29', '2017-11-13 15:12:29', '00004c879b3d4697add98d47cde5f1d0', null, null);
INSERT INTO `user_like` VALUES ('00092f4b09eb4290b9133aa46b5b8e20', '1', '2017-11-13 15:12:29', '2017-11-13 15:12:29', '00004c879b3d4697add98d47cde5f1d0', null, null);
INSERT INTO `user_like` VALUES ('0009ccdfc98044e2a109246ee31f4ec4', '1', '2017-11-13 15:12:29', '2017-11-13 15:12:29', '00004c879b3d4697add98d47cde5f1d0', null, null);
INSERT INTO `user_like` VALUES ('000d8e5eb5164ef7b697e838dea5d99c', '1', '2017-11-13 15:12:29', '2017-11-13 15:12:29', '00004c879b3d4697add98d47cde5f1d0', null, null);
INSERT INTO `user_like` VALUES ('000efe03a29f4977b27536378ce2a211', '1', '2017-11-13 15:12:29', '2017-11-13 15:12:29', '00004c879b3d4697add98d47cde5f1d0', null, null);
INSERT INTO `user_like` VALUES ('000f15e978e940e79c72e217d3d948c7', '1', '2017-11-13 15:12:29', '2017-11-13 15:12:29', '00004c879b3d4697add98d47cde5f1d0', null, null);
INSERT INTO `user_like` VALUES ('000fe2853e9249b8988e288f7c8263fd', '1', '2017-11-13 15:12:29', '2017-11-13 15:12:29', '00004c879b3d4697add98d47cde5f1d0', null, null);
INSERT INTO `user_like` VALUES ('0013c9900ac84a49ba3298c291f5da4e', '1', '2017-11-13 15:12:29', '2017-11-13 15:12:29', '00004c879b3d4697add98d47cde5f1d0', null, null);
INSERT INTO `user_like` VALUES ('00140f23a74a470595453061b3207a62', '1', '2017-11-13 15:12:29', '2017-11-13 15:12:29', '00004c879b3d4697add98d47cde5f1d0', null, null);
INSERT INTO `user_like` VALUES ('001596208d16443094327fb2234fe04f', '1', '2017-11-13 15:12:29', '2017-11-13 15:12:29', '00004c879b3d4697add98d47cde5f1d0', null, null);
INSERT INTO `user_like` VALUES ('00159c35f44940b5990ee05a977b699b', '1', '2017-11-13 15:12:29', '2017-11-13 15:12:29', '00004c879b3d4697add98d47cde5f1d0', null, null);
INSERT INTO `user_like` VALUES ('00159e20c6764525b53d2d709114ab48', '1', '2017-11-13 15:12:29', '2017-11-13 15:12:29', '00004c879b3d4697add98d47cde5f1d0', null, null);
INSERT INTO `user_like` VALUES ('00162a0c172f45629f8c66d4712b3e9b', '1', '2017-11-13 15:12:29', '2017-11-13 15:12:29', '00004c879b3d4697add98d47cde5f1d0', null, null);
INSERT INTO `user_like` VALUES ('0018f5dac5a647bbaf1a387aea40f1f4', '1', '2017-11-13 15:12:29', '2017-11-13 15:12:29', '00004c879b3d4697add98d47cde5f1d0', null, null);
INSERT INTO `user_like` VALUES ('001a1807c9094fa0a02fbbb0604bba55', '1', '2017-11-13 15:12:29', '2017-11-13 15:12:29', '00004c879b3d4697add98d47cde5f1d0', null, null);

-- ----------------------------
-- Table structure for user_login_record
-- ----------------------------
DROP TABLE IF EXISTS `user_login_record`;
CREATE TABLE `user_login_record` (
  `login_record_id` varchar(32) NOT NULL COMMENT '唯 一标识',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) DEFAULT NULL COMMENT '创建者',
  `login_ip` varchar(100) DEFAULT NULL COMMENT '登录IP',
  `login_type` tinyint(4) DEFAULT NULL COMMENT '登录类型 0:邮箱登录,1:QQ,2:微信,3:微博,4:facebook,5:twitter,6:google,7:line,8:instagram',
  `token` varchar(100) DEFAULT NULL,
  `app_id` varchar(256) DEFAULT NULL,
  `package_name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`login_record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_login_record
-- ----------------------------
INSERT INTO `user_login_record` VALUES ('0cd0b92420194757854e09bcfceab87c', null, '2017-12-26 18:37:36', null, null, '4', 'c1e49peW7rhEu0BhQeaTteSZhAcLAmoHWtT3jxZpIKPf9X5VdKoRgs5p/BoDxZ0xlreVuBkfT+546GDjLgt5mQ==', null, null);
INSERT INTO `user_login_record` VALUES ('1379814037da4d5ea0dd593a9ff0ee1d', null, '2017-12-26 18:54:11', null, null, '4', 'WrsVhMx/Tq9a7ImgmvbiAqJGrwaiXEGFNPCXL8foCUlPYsfL3pjH7+F3AwOkGlkY5jnqhR47yq0Nv5Oj5tj6Tg==', null, null);
INSERT INTO `user_login_record` VALUES ('36f4b88f5fa54cd5b321068f390de769', null, '2017-12-26 19:07:02', null, null, '4', 'ywjTQCGGxzh61Bjadia0I2gTbUYQWYMkeJN8MAEEnz8tB+xMmGp5WY2ulyOLygGh4DLJ9s0bj5dMG6AWy0/32g==', null, null);
INSERT INTO `user_login_record` VALUES ('641254ad77884de1ba811da15b6ad507', null, '2017-12-26 19:09:54', 'ddcd22ddb0c14edf8c0fe522fc95813b', null, '4', '1Fd2/JvgA0YL24kj/krfDtywjejZ4NsBciYMgZxlK35nenfWjpe+nZFjNMyvzxw2DhRDqe8YJyslDAFd+StUwQ==', null, null);
INSERT INTO `user_login_record` VALUES ('6b76232a16ad4e47b8ac8435d2f67f5a', null, '2017-12-26 19:00:55', null, null, '4', 'EbecPe+AzGZ9jUvRQFZDwZKZcK3TMi9KwHpqO+CeNG61q+y0grto0CrzWaCqIxyAzDkectolXmshKAFd8dpmfA==', null, null);
INSERT INTO `user_login_record` VALUES ('6dcbe96a9d4847aca587946560a5efa8', 'f9f235edb59545c8b59d5496c124fe02', '2017-12-26 19:15:54', 'f9f235edb59545c8b59d5496c124fe02', null, '4', 'xqGcG35u8jDqMhWgtZ8Up91iF/te9jUWiNNelx91PV5PrO5ulxYtekhjdo+AcS6U9ejaxg1JvYAKH01AuHwYEg==', null, null);
INSERT INTO `user_login_record` VALUES ('78e2f70dc1db4a4b9abf5ed1cbd1d2b8', 'b78f0f368edc4a8e9b82157fa93be74a', '2017-12-26 19:42:11', 'b78f0f368edc4a8e9b82157fa93be74a', null, '4', 'ucQrwHJK8QTPPYeW8sQAydAJ0dFSx9rtiWdQPpHpS4a3E5RbD+RgqtfmRE/eBfPNuDbenK+SEkVHBJ/2c1MrBA==', null, null);
INSERT INTO `user_login_record` VALUES ('8517430c34754ad2a12b546e756d5209', 'fa471dd63d4447bbaadcfc5379ba85e6', '2017-12-26 19:43:19', 'fa471dd63d4447bbaadcfc5379ba85e6', null, '4', 'QPR95d6dLULDI8vB2mnq6ZxEywA1Icf2Hz2oa5myGfF2kaAQRTyhb01xVUST9RCNkxHHTifOEPm/Ow/8RoJo5A==', null, null);
INSERT INTO `user_login_record` VALUES ('89cf5f1c8c394750bcd219a599e39256', null, '2017-12-26 19:13:09', '5004461a639b48f09b9a444f739110ae', null, '4', 'gCtEyU+XAh1sazDMl5YRpNaBg9p1EWD5QOUNM526sqfIijxCfU8KowD2ztpaAFEhP+Ze5U4wJIEI/OrGAtKGfw==', null, null);
INSERT INTO `user_login_record` VALUES ('976f1690747f4b15a7b954050edfc3ed', '780dbebeecfc47bd97a506b6ec517203', '2017-12-26 19:32:16', '780dbebeecfc47bd97a506b6ec517203', null, '4', 'LjG50oDoQc26cl2u89GMo1r4hQGpGDyMWvEuWqX3L+byQXotV1hc5N6RbaELxVzUfqd/SXx9GCs0O9ofO9kHkA==', null, null);
INSERT INTO `user_login_record` VALUES ('9f638fe8ddc74245b8609c10c5dd022e', null, '2017-12-26 18:57:57', null, null, '4', '4VO/5qDUwph2Kpl57kfy3MVQ1tPlp3lif3vjgOycldJj9FT1BSVEnyFHqzhznlCskdJeDLeYITuUIoyKAyRfNQ==', null, null);
INSERT INTO `user_login_record` VALUES ('b6739738042e42a3b03f9f5d7762ffa8', 'a24eff46b69648bab42303f7661e54ee', '2017-12-26 19:23:14', 'a24eff46b69648bab42303f7661e54ee', null, '4', 'AChGOyDVM8q+HFqrXbMF4hNftHMsK5eJ+ieEklY4cbJHIBDtzNfPkpDqQ4Yp1CBFrYYKmUaWLjIULvAPA5cMKQ==', null, null);
INSERT INTO `user_login_record` VALUES ('b76e23343fde41549134c007c3e45dec', null, '2017-12-26 19:10:45', '67c1505134ed4dd496895286674d7caf', null, '4', 'XYK0SZ90Yd1HJwcKARFNLXC4oRorIvq6Ls7chsIjv/JizYbrjY01Chgn6NHyEl45MnqihlG0RCzlTh3MLZG0AA==', null, null);
INSERT INTO `user_login_record` VALUES ('bbe65da072f9450ea4084d9bea879207', '5a018c783e75463fb3b8242c7bb10158', '2017-12-26 19:46:12', '5a018c783e75463fb3b8242c7bb10158', null, '4', 'x58ug/uUbUU8R52inT2EcP42CYXiOMDKUKDYWEqx23ObugVqSuYDdpVkHRz812MMGAyCKQEnkQ4MkdAHftzaOw==', null, null);
INSERT INTO `user_login_record` VALUES ('c3637797835749ae94a5768a7153819c', 'cd476f0765b3418590a6fea5a0fb5b30', '2017-12-26 19:23:46', 'cd476f0765b3418590a6fea5a0fb5b30', null, '4', '0AZrJMsopU1YrWkTVPVJRdOtcJeSgTcSR01Oor/G39MFTBET5yQN1hJ1rhXsfICi9h/hxT7uvmlE5lvcVwyeQQ==', null, null);
INSERT INTO `user_login_record` VALUES ('c8e548b8b4d04884bfb2e3ccb5571b4c', 'c9d0cc27680346129c798318262ee2ff', '2017-12-26 19:24:47', 'c9d0cc27680346129c798318262ee2ff', null, '4', 'PHia6c/PGcWnzxr4f1ACugwPN1Xtsc3/JFxcgW5SdYiHF21q/imk3/4Y/bZSjFeEN7sSRjzTCSS2D2gK3yFpIg==', null, null);
INSERT INTO `user_login_record` VALUES ('d225d250f7db41afa07e6610d27907db', '7ea75ffc1cc74ba9b1aca00109ddd5fe', '2017-12-26 19:32:48', '7ea75ffc1cc74ba9b1aca00109ddd5fe', null, '4', 'bIhY4tMg+xMA8TvCoLkNq7PZFA92I1H4S5S7yKZ904QT/2vc0k3UhhN4enu3NsPY7QrT6NgRKpOEit7uen7UUA==', null, null);
INSERT INTO `user_login_record` VALUES ('d509b59ce402464dae252be529aa5c63', null, '2017-12-26 18:58:57', null, null, '4', 'POU72FrXgSChaFU382boZvceg0ih+h9TosZbmP5M2Rhd+BuLWkK6wYVkuHcnlLENui1KG0UAIJuo9oHHrpBShA==', null, null);
INSERT INTO `user_login_record` VALUES ('d650065ba4c640719cbcc02f9f6c3492', null, '2017-12-26 19:00:04', null, null, '4', 'x3A/CSUmoNhywI+c0Zi596iw/kYHptc9KqqZNQmh7XXsl4UcH8lTjGXSuY0Y+RFAsuWQrhgA1V67z7KvhUJScw==', null, null);
INSERT INTO `user_login_record` VALUES ('e750efdbf3a6419c9ea10a9c94cf3148', '165ee38f9c5b4a70853db79abd744d8c', '2017-12-26 19:30:15', '165ee38f9c5b4a70853db79abd744d8c', null, '4', 'dhDTOGe70dV4KRdfMP3NRTLAbd0XgnoyUJj3Rbo0PJXDxbDoLp/pFJyAtVbe8P946314CIbphLj8XKNT3JNKBA==', null, null);

-- ----------------------------
-- Table structure for user_oauth
-- ----------------------------
DROP TABLE IF EXISTS `user_oauth`;
CREATE TABLE `user_oauth` (
  `oauth_id` varchar(32) NOT NULL COMMENT 'ID',
  `user_id` varchar(100) DEFAULT NULL COMMENT '用户ID',
  `oid` varchar(100) DEFAULT NULL COMMENT '第三方用户识别符',
  `type` tinyint(4) DEFAULT NULL COMMENT '第三方类型: 1:QQ,2:微信,3:微博,4:facebook,5:twitter,6:google,7:line,8:instagram',
  `access_token` text COMMENT '认证访问token',
  `create_time` datetime DEFAULT NULL COMMENT '状态',
  `status` int(11) DEFAULT NULL COMMENT '-2:异常\\错误\\失败；-1:已经删除；0:未使用\\非最新；1:使用中\\最新；2:处理中；3:处理暂停；4:处理放弃',
  `open_icon` varchar(255) DEFAULT NULL,
  `open_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`oauth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_oauth
-- ----------------------------
INSERT INTO `user_oauth` VALUES ('f3a60715815341deb9bcd219843a02b1', '5a018c783e75463fb3b8242c7bb10158', '125232248132822', '4', 'EAACJT2KZCgfIBAHp9L0G23FOAS1xlacHHgO34kiJTTuRzqfCYDevE95q9jiwGvqXWVlOWZB9LQ3c7fA1e8ky2yOl0oGF78ZApKSxenGcVqrvPrTWn22w68yJvxOPMyJIAUwiDAaKcZBZBPZBYrGnYRb2tAMxdQYzS6PvwZBJCkaVYB45Bl9nY2Heg5f9lrJ5VftD5ZAg8Ou7uxqXpZAjpqxDumXfyZCCF0hKcZD', null, '1', 'https://fb-s-c-a.akamaihd.net/h-ak-fbx/v/t1.0-1/p50x50/22491786_131305727525474_5297543855450502669_n.jpg?oh=1edebbe5cc219bc8d19a00c97ccfe7da&oe=5ABCB9CF&__gda__=1526153720_cfdce42dc50ad00cc6d42c5f322d415e', 'Fandy Feng');

-- ----------------------------
-- Table structure for version
-- ----------------------------
DROP TABLE IF EXISTS `version`;
CREATE TABLE `version` (
  `version_id` varchar(32) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '状态1在用，0老版本',
  `version_code` varchar(100) DEFAULT NULL COMMENT '版本号',
  `system_type` varchar(100) DEFAULT NULL COMMENT '系统类型： IOS ， ANDROID',
  `download_url` text COMMENT '下载地址',
  `description` text COMMENT '描述',
  `is_must` tinyint(4) DEFAULT NULL COMMENT '0:强制升级；1:非强制升级',
  `MD5` varchar(255) DEFAULT NULL,
  `app_type` varchar(20) DEFAULT NULL COMMENT '//1：正常版本，2：player精简版.....其他',
  `channel` varchar(100) DEFAULT NULL COMMENT '渠道',
  `tips` varchar(500) DEFAULT NULL COMMENT '升级提示语',
  `create_user` varchar(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(20) DEFAULT NULL COMMENT '修改者',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `flag` tinyint(4) DEFAULT '1' COMMENT '标志性删除位,-1:无效,1:有效',
  `ban` tinyint(4) DEFAULT '0' COMMENT '标志性删除  0:启用(默认) 1:禁用 2:不可更改',
  `app_id` varchar(255) DEFAULT NULL,
  `package_name` varchar(255) DEFAULT NULL,
  UNIQUE KEY `3d_version_id_unique` (`version_id`,`channel`,`flag`) USING BTREE,
  KEY `3d_version_verid` (`version_id`) USING BTREE,
  KEY `3d_version_channel` (`channel`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='版本信息表';

-- ----------------------------
-- Records of version
-- ----------------------------
INSERT INTO `version` VALUES ('vf151v55165svdf', '1', '1.0.1', 'ANDROID', 'http://www.baidu.com', '测试版本', '0', 'vf1s6f1v56s1df15vsd1fv6s', '1', 'testChannel', '是否升级？', 't', '2017-10-18 10:41:59', 't', '2017-10-18 10:41:59', '1', '0', null, null);
INSERT INTO `version` VALUES ('d1141sd1vsdf15', '1', '1.0.2', 'ANDROID', 'http://www.baidu.com', '测试版本', '0', 'vf1s6f1v56s1df15vsd1fv6s', '1', 'testChannel', '是否升级？', 't', '2017-10-18 10:41:59', 't', '2017-10-18 10:41:59', '1', '0', null, null);
INSERT INTO `version` VALUES ('1csd16415v1f61vs6d', '1', '1.0.3', 'ANDROID', 'http://www.baidu.com', '测试版本', '0', 'vf1s6f1v56s1df15vsd1fv6s', '1', 'testChannel', '是否升级？', 't', '2017-10-18 10:41:59', 't', '2017-10-18 10:41:59', '1', '0', null, null);
INSERT INTO `version` VALUES ('d1fv5s1465451', '1', '1.0.4', 'ANDROID', 'http://www.baidu.com', '测试版本', '0', 'vf1s6f1v56s1df15vsd1fv6s', '1', 'testChannel', '是否升级？', 't', '2017-10-18 10:41:59', 't', '2017-10-18 10:41:59', '1', '0', null, null);
INSERT INTO `version` VALUES ('vf1565d1te51gb1', '1', '1.0.1', 'IOS', 'http://www.baidu.com', '测试版本', '0', 'vf1s6f1v56s1df15vsd1fv6s', '1', 'testChannel', '是否升级？', 't', '2017-10-18 10:41:59', 't', '2017-10-18 10:41:59', '1', '0', null, null);
INSERT INTO `version` VALUES ('b15r1y1wev5', '1', '1.0.2', 'IOS', 'http://www.baidu.com', '测试版本', '0', 'vf1s6f1v56s1df15vsd1fv6s', '1', 'testChannel', '是否升级？', 't', '2017-10-18 10:41:59', 't', '2017-10-18 10:41:59', '1', '0', null, null);
INSERT INTO `version` VALUES ('b1t5hn161h5h61hhb', '1', '1.0.3', 'IOS', 'http://www.baidu.com', '测试版本', '0', 'vf1s6f1v56s1df15vsd1fv6s', '1', 'testChannel', '是否升级？', 't', '2017-10-18 10:41:59', 't', '2017-10-18 10:41:59', '1', '0', null, null);
INSERT INTO `version` VALUES ('b1fdg5n161uj5161j', '1', '1.0.4', 'IOS', 'http://www.baidu.com', '测试版本', '0', 'vf1s6f1v56s1df15vsd1fv6s', '1', 'testChannel', '是否升级？', 't', '2017-10-18 10:41:59', 't', '2017-10-18 10:41:59', '1', '0', null, null);
