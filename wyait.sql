/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : wyait

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 09/07/2020 15:16:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for combo
-- ----------------------------
DROP TABLE IF EXISTS `combo`;
CREATE TABLE `combo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '套餐id',
  `combo_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '套餐名字',
  `new_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '套餐表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of combo
-- ----------------------------
INSERT INTO `combo` VALUES (15, '开办美容美发店', '2020-07-07 16:43:11', '2020-07-07 16:43:58');

-- ----------------------------
-- Table structure for combo_dooo
-- ----------------------------
DROP TABLE IF EXISTS `combo_dooo`;
CREATE TABLE `combo_dooo`  (
  `combo_id` int(11) NOT NULL COMMENT '套餐ID',
  `dooo_id` int(11) NOT NULL COMMENT '事项ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of combo_dooo
-- ----------------------------
INSERT INTO `combo_dooo` VALUES (15, 12);
INSERT INTO `combo_dooo` VALUES (15, 13);
INSERT INTO `combo_dooo` VALUES (15, 14);
INSERT INTO `combo_dooo` VALUES (15, 16);
INSERT INTO `combo_dooo` VALUES (15, 17);
INSERT INTO `combo_dooo` VALUES (15, 18);
INSERT INTO `combo_dooo` VALUES (15, 19);
INSERT INTO `combo_dooo` VALUES (15, 20);
INSERT INTO `combo_dooo` VALUES (15, 21);
INSERT INTO `combo_dooo` VALUES (15, 24);
INSERT INTO `combo_dooo` VALUES (15, 22);
INSERT INTO `combo_dooo` VALUES (15, 32);
INSERT INTO `combo_dooo` VALUES (15, 23);
INSERT INTO `combo_dooo` VALUES (15, 25);
INSERT INTO `combo_dooo` VALUES (15, 26);
INSERT INTO `combo_dooo` VALUES (15, 27);
INSERT INTO `combo_dooo` VALUES (15, 28);
INSERT INTO `combo_dooo` VALUES (15, 29);

-- ----------------------------
-- Table structure for combo_situation
-- ----------------------------
DROP TABLE IF EXISTS `combo_situation`;
CREATE TABLE `combo_situation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `situation_describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '情形描述',
  `combo_id` int(11) NULL DEFAULT NULL COMMENT '套餐id',
  `new_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '套餐详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of combo_situation
-- ----------------------------
INSERT INTO `combo_situation` VALUES (6, '您要开办的企业类型为？', 15, '2020-07-08 17:13:04', '2020-07-08 17:13:04');
INSERT INTO `combo_situation` VALUES (7, '您是法定代表人还是授权委托人？', 15, '2020-07-09 09:07:20', '2020-07-09 09:07:20');
INSERT INTO `combo_situation` VALUES (8, '您的经营场所是否已进行自主申报？', 15, '2020-07-09 09:07:39', '2020-07-09 09:07:39');
INSERT INTO `combo_situation` VALUES (9, '是否办理涉税事项？', 15, '2020-07-09 09:08:03', '2020-07-09 09:08:03');
INSERT INTO `combo_situation` VALUES (10, '营业场所是否安装集中空调通风系统？', 15, '2020-07-09 09:08:21', '2020-07-09 09:08:21');

-- ----------------------------
-- Table structure for combo_situation_details
-- ----------------------------
DROP TABLE IF EXISTS `combo_situation_details`;
CREATE TABLE `combo_situation_details`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `details_describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详情描述',
  `combo_situation_id` int(11) NULL DEFAULT NULL COMMENT '套餐详情id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '套餐情形详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of combo_situation_details
-- ----------------------------
INSERT INTO `combo_situation_details` VALUES (27, '个体工商户', 6);
INSERT INTO `combo_situation_details` VALUES (28, '内资责任有限公司', 6);
INSERT INTO `combo_situation_details` VALUES (29, '内资分公司', 6);
INSERT INTO `combo_situation_details` VALUES (30, '外商投资有限责任公司', 6);
INSERT INTO `combo_situation_details` VALUES (31, '外商投资的公司分公司', 6);
INSERT INTO `combo_situation_details` VALUES (32, '个人独资企业', 6);
INSERT INTO `combo_situation_details` VALUES (33, '个人独资企业分支机构', 6);
INSERT INTO `combo_situation_details` VALUES (34, '合伙企业', 6);
INSERT INTO `combo_situation_details` VALUES (35, '合伙企业分支机构', 6);
INSERT INTO `combo_situation_details` VALUES (36, '法定代表人', 7);
INSERT INTO `combo_situation_details` VALUES (37, '授权委托人', 7);
INSERT INTO `combo_situation_details` VALUES (38, '是', 8);
INSERT INTO `combo_situation_details` VALUES (39, '否', 8);
INSERT INTO `combo_situation_details` VALUES (40, '是', 9);
INSERT INTO `combo_situation_details` VALUES (41, '否', 9);
INSERT INTO `combo_situation_details` VALUES (42, '是', 10);
INSERT INTO `combo_situation_details` VALUES (43, '否', 10);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `department_id` int(5) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `department_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `type` int(1) NOT NULL COMMENT '是否删除: 1、删除 0、正常',
  `new_time` datetime(0) NOT NULL COMMENT '添加时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`department_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (15, '区卫生健康局', 0, '2020-07-07 16:27:30', '2020-07-07 16:27:30');
INSERT INTO `department` VALUES (16, '区市场监管局', 0, '2020-07-07 16:27:44', '2020-07-07 16:27:44');
INSERT INTO `department` VALUES (17, '税务局', 0, '2020-07-07 16:27:56', '2020-07-07 16:27:56');
INSERT INTO `department` VALUES (18, '区公安分局治安大队', 0, '2020-07-07 16:28:06', '2020-07-07 16:28:06');
INSERT INTO `department` VALUES (19, '广州市番禺区住房和城乡建设局', 0, '2020-07-09 14:46:50', '2020-07-09 14:46:50');

-- ----------------------------
-- Table structure for dooo
-- ----------------------------
DROP TABLE IF EXISTS `dooo`;
CREATE TABLE `dooo`  (
  `dooo_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dooo_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '事项名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `type` int(1) NULL DEFAULT NULL COMMENT '状态: 0.正常 1.删除',
  `department_id` int(5) NULL DEFAULT NULL COMMENT '部门ID',
  `dooo_condition` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '受理条件',
  `new_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`dooo_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '事项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dooo
-- ----------------------------
INSERT INTO `dooo` VALUES (12, '公共场所卫生许可（1520）', '无', 0, 15, '无', '2020-07-07 16:28:38', '2020-07-07 16:28:38');
INSERT INTO `dooo` VALUES (13, '个体工商户注册登记', '无', 0, 16, '无', '2020-07-07 16:28:54', '2020-07-07 16:35:45');
INSERT INTO `dooo` VALUES (14, '内资有限责任公司设立登记', '无', 0, 16, '无', '2020-07-07 16:29:11', '2020-07-07 16:29:11');
INSERT INTO `dooo` VALUES (15, '内资分公司设立登记', '无', 0, 16, '无', '2020-07-07 16:29:19', '2020-07-07 16:29:19');
INSERT INTO `dooo` VALUES (16, '外商投资有限责任公司设立登记', '无', 0, 16, '无', '2020-07-07 16:29:47', '2020-07-07 16:29:47');
INSERT INTO `dooo` VALUES (17, '外商投资的公司分公司设立登记', '无', 0, 16, '无', '2020-07-07 16:29:56', '2020-07-07 16:29:56');
INSERT INTO `dooo` VALUES (18, '个人独资企业设立登记', '无', 0, 16, '无', '2020-07-07 16:30:18', '2020-07-07 16:30:18');
INSERT INTO `dooo` VALUES (19, '个人独资企业分支机构设立登记', '无', 0, 16, '无', '2020-07-07 16:30:28', '2020-07-07 16:30:28');
INSERT INTO `dooo` VALUES (20, '合伙企业设立登记', '无', 0, 16, '无', '2020-07-07 16:30:57', '2020-07-07 16:30:57');
INSERT INTO `dooo` VALUES (21, '合伙企业分支机构设立登记', '无', 0, 16, '无', '2020-07-07 16:31:07', '2020-07-07 16:31:07');
INSERT INTO `dooo` VALUES (22, '一照一码登记信息确认（单位纳税人适用）', '无', 0, 17, '无', '2020-07-07 16:31:21', '2020-07-07 16:31:21');
INSERT INTO `dooo` VALUES (23, '税（费）种认定', '无', 0, 17, '无', '2020-07-07 16:31:31', '2020-07-07 16:31:31');
INSERT INTO `dooo` VALUES (24, '存款账户账号报告', '无', 0, 16, '无', '2020-07-07 16:31:42', '2020-07-07 16:31:42');
INSERT INTO `dooo` VALUES (25, '网签《委托银行（金融机构）划缴税费款三方协议》', '无', 0, 17, '无', '2020-07-07 16:31:50', '2020-07-07 16:31:50');
INSERT INTO `dooo` VALUES (26, '财务会计制度及核算软件备案', '无', 0, 17, '无', '2020-07-07 16:32:02', '2020-07-07 16:32:02');
INSERT INTO `dooo` VALUES (27, '增值税一般纳税人登记', '无', 0, 17, '无', '2020-07-07 16:32:13', '2020-07-07 16:32:13');
INSERT INTO `dooo` VALUES (28, '发票票种核定', '无', 0, 17, '无', '2020-07-07 16:32:21', '2020-07-07 16:32:21');
INSERT INTO `dooo` VALUES (29, '增值税专用发票最高开票限额申请', '无', 0, 17, '无', '2020-07-07 16:32:29', '2020-07-07 16:32:29');
INSERT INTO `dooo` VALUES (30, '防伪税控设备申购', '无', 0, 17, '无', '2020-07-07 16:32:37', '2020-07-07 16:32:37');
INSERT INTO `dooo` VALUES (31, '发放Ukey', '无', 0, 17, '无', '2020-07-07 16:32:45', '2020-07-07 16:32:45');
INSERT INTO `dooo` VALUES (32, '印章刻制备案', '无', 0, 18, '无', '2020-07-07 16:32:52', '2020-07-07 16:32:52');
INSERT INTO `dooo` VALUES (33, '工程规划许可证和施工许可证并联审批（社会投资简易低风险项目） ', '无', 0, 19, '无', '2020-07-09 14:47:08', '2020-07-09 14:47:08');

-- ----------------------------
-- Table structure for log_system
-- ----------------------------
DROP TABLE IF EXISTS `log_system`;
CREATE TABLE `log_system`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '操作人的id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作内容',
  `new_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态：1、部门 2、事项 3、情形 4、套餐 5、人员变动',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日志系统' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log_system
-- ----------------------------
INSERT INTO `log_system` VALUES (12, 1, 'admin新增了一个部门，命名为：国家税务局广州番禺区分局', '2020-07-05 23:04:52', '1');
INSERT INTO `log_system` VALUES (13, 1, '用户（admin）删除了部门：ceshi', '2020-07-05 23:38:26', '1');
INSERT INTO `log_system` VALUES (14, 1, '用户（admin）新增了一个部门，命名为：广州市公安局', '2020-07-06 09:18:34', '1');
INSERT INTO `log_system` VALUES (15, 1, '用户（admin）删除了部门：测试部门', '2020-07-06 09:19:24', '1');
INSERT INTO `log_system` VALUES (16, 1, '用户（admin）删除了部门：测试部门', '2020-07-06 09:19:30', '1');
INSERT INTO `log_system` VALUES (17, 1, '用户（admin）删除了部门：测试部门', '2020-07-06 09:19:43', '1');
INSERT INTO `log_system` VALUES (18, 1, '用户（admin）删除了部门：测试部门', '2020-07-06 09:50:17', '1');
INSERT INTO `log_system` VALUES (19, 1, '用户（admin）删除了部门：标准化部门', '2020-07-06 11:40:55', '1');
INSERT INTO `log_system` VALUES (20, 1, '用户（admin）删除了部门：测试部门', '2020-07-06 11:40:57', '1');
INSERT INTO `log_system` VALUES (21, 1, '用户（admin）删除了部门：真是爱你', '2020-07-06 11:41:00', '1');
INSERT INTO `log_system` VALUES (22, 1, '用户（admin）删除了部门：1313', '2020-07-06 11:41:01', '1');
INSERT INTO `log_system` VALUES (23, 1, '用户（admin）删除了部门：1231', '2020-07-06 11:41:03', '1');
INSERT INTO `log_system` VALUES (24, 1, '用户（admin）删除了部门：谢谢谢谢', '2020-07-06 11:41:06', '1');
INSERT INTO `log_system` VALUES (25, 1, '用户（admin）删除了部门：测试日志', '2020-07-06 11:41:08', '1');
INSERT INTO `log_system` VALUES (26, 1, '用户（admin）删除了部门：测试111', '2020-07-06 11:41:12', '1');
INSERT INTO `log_system` VALUES (27, 1, '用户（admin）删除了部门：现在是修改了', '2020-07-06 11:41:14', '1');
INSERT INTO `log_system` VALUES (28, 1, '用户（admin）删除了部门：1', '2020-07-07 16:25:56', '1');
INSERT INTO `log_system` VALUES (29, 1, '用户（admin）删除了部门：1', '2020-07-07 16:26:01', '1');
INSERT INTO `log_system` VALUES (30, 1, '用户（admin）删除了部门：1', '2020-07-07 16:26:19', '1');
INSERT INTO `log_system` VALUES (31, 1, '用户（admin）删除了部门：国家税务局广州番禺区分局', '2020-07-07 16:26:21', '1');
INSERT INTO `log_system` VALUES (32, 1, '用户（admin）删除了部门：广州市公安局', '2020-07-07 16:26:23', '1');
INSERT INTO `log_system` VALUES (33, 1, '用户（admin）删除了部门：广州市公安局', '2020-07-07 16:26:54', '1');
INSERT INTO `log_system` VALUES (34, 1, '用户（admin）新增了一个部门，命名为：区卫生健康局', '2020-07-07 16:27:30', '1');
INSERT INTO `log_system` VALUES (35, 1, '用户（admin）新增了一个部门，命名为：区市场监管局', '2020-07-07 16:27:44', '1');
INSERT INTO `log_system` VALUES (36, 1, '用户（admin）新增了一个部门，命名为：税务局', '2020-07-07 16:27:56', '1');
INSERT INTO `log_system` VALUES (37, 1, '用户（admin）新增了一个部门，命名为：区公安分局治安大队', '2020-07-07 16:28:06', '1');
INSERT INTO `log_system` VALUES (38, 1, '用户（admin）新增了一个部门，命名为：广州市番禺区住房和城乡建设局', '2020-07-09 14:46:50', '1');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父菜单id',
  `zindex` int(2) NULL DEFAULT NULL COMMENT '菜单排序',
  `istype` int(1) NULL DEFAULT NULL COMMENT '权限分类（0 菜单；1 功能）',
  `descpt` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单编号',
  `icon` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标名称',
  `page` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单url',
  `insert_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '权限管理', 0, 100, 0, '系统管理', 'system', '', '/', '2017-12-20 16:22:43', '2020-07-08 14:35:24');
INSERT INTO `permission` VALUES (2, '用户管理', 1, 1100, 0, '用户管理', 'usermanage', '', '/user/userList', '2017-12-20 16:27:03', '2018-01-09 19:26:30');
INSERT INTO `permission` VALUES (3, '角色管理', 1, 1200, 0, '角色管理', 'rolemanage', '', '/auth/roleManage', '2017-12-20 16:27:03', '2018-01-09 19:26:42');
INSERT INTO `permission` VALUES (4, '权限设置', 1, 1300, 0, '权限管理', 'permmanage', NULL, '/auth/permList', '2017-12-30 19:17:32', '2020-07-08 14:35:31');
INSERT INTO `permission` VALUES (5, '部门管理', 0, 200, 0, '部门管理', 'shops', NULL, '/department/departmentList', '2017-12-30 19:17:50', '2020-07-03 17:26:00');
INSERT INTO `permission` VALUES (6, '事项管理', 0, 300, 0, '事项管理', 'channel', NULL, '/dooo/doooList', '2018-01-01 11:07:17', '2018-01-09 19:05:42');
INSERT INTO `permission` VALUES (8, '情形管理', 0, 400, 0, '情形管理', 'orders', NULL, '/situation/situationList', '2018-01-09 09:26:53', '2018-01-09 19:20:40');
INSERT INTO `permission` VALUES (9, '套餐管理', 0, 500, 0, '套餐管理', 'taocan', NULL, '/', '2020-06-22 16:49:34', '2020-06-22 16:49:37');
INSERT INTO `permission` VALUES (10, '事项列表', 6, 2200, 0, '渠道信息列表', 'channelPage', NULL, '/dooo/doooList', '2018-01-09 19:07:05', '2020-06-29 14:59:19');
INSERT INTO `permission` VALUES (11, '情形列表', 8, 2300, 0, '渠道会员列表', 'channelUsers', NULL, '/situation/situationList', '2018-01-09 19:07:52', '2018-01-18 14:08:08');
INSERT INTO `permission` VALUES (13, '部门列表', 5, 3100, 0, '商品列表', 'shopPage', NULL, '/department/departmentList', '2018-01-09 19:33:53', '2020-06-29 14:58:18');
INSERT INTO `permission` VALUES (14, '套餐列表', 9, 4100, 0, '商品订单列表', 'orderPage', NULL, '/combo/comboList', '2018-01-09 19:34:33', '2018-04-22 21:17:58');
INSERT INTO `permission` VALUES (15, '系统设置', 0, 600, 0, '系统设置', 'system', NULL, '/welcome-2', '2020-07-04 12:44:38', '2020-07-04 12:44:41');
INSERT INTO `permission` VALUES (16, '数据统计', 15, 6100, 0, '数据统计', 'combo', NULL, '/welcome-2', '2020-07-04 14:03:21', '2020-07-04 14:03:24');
INSERT INTO `permission` VALUES (17, '日志管理', 15, 700, 0, '日志管理', 'logSystem', NULL, '/logSystem/logSystemList', '2020-07-05 00:30:10', '2020-07-05 00:30:13');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `descpt` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编号',
  `insert_uid` int(11) NULL DEFAULT NULL COMMENT '操作用户id',
  `insert_time` datetime(0) NULL DEFAULT NULL COMMENT '添加数据时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理', '超级管理员', 'superman', NULL, '2018-01-09 19:28:53', '2020-07-05 22:55:38');
INSERT INTO `role` VALUES (6, '科长', '科长级别', '1231', NULL, '2020-07-07 16:19:16', NULL);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `permit_id` int(5) NOT NULL AUTO_INCREMENT,
  `role_id` int(5) NOT NULL,
  PRIMARY KEY (`permit_id`, `role_id`) USING BTREE,
  INDEX `perimitid`(`permit_id`) USING BTREE,
  INDEX `roleid`(`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, 1);
INSERT INTO `role_permission` VALUES (2, 1);
INSERT INTO `role_permission` VALUES (3, 1);
INSERT INTO `role_permission` VALUES (4, 1);
INSERT INTO `role_permission` VALUES (5, 1);
INSERT INTO `role_permission` VALUES (6, 1);
INSERT INTO `role_permission` VALUES (6, 6);
INSERT INTO `role_permission` VALUES (8, 1);
INSERT INTO `role_permission` VALUES (9, 1);
INSERT INTO `role_permission` VALUES (10, 1);
INSERT INTO `role_permission` VALUES (10, 6);
INSERT INTO `role_permission` VALUES (11, 1);
INSERT INTO `role_permission` VALUES (13, 1);
INSERT INTO `role_permission` VALUES (14, 1);
INSERT INTO `role_permission` VALUES (15, 1);
INSERT INTO `role_permission` VALUES (15, 6);
INSERT INTO `role_permission` VALUES (16, 1);
INSERT INTO `role_permission` VALUES (16, 6);
INSERT INTO `role_permission` VALUES (17, 1);

-- ----------------------------
-- Table structure for situation
-- ----------------------------
DROP TABLE IF EXISTS `situation`;
CREATE TABLE `situation`  (
  `situation_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dooo_id` int(11) NULL DEFAULT NULL COMMENT '事项id',
  `department_id` int(11) NULL DEFAULT NULL COMMENT '部门id',
  `situation_describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '情形描述',
  `new_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`situation_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '情形表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of situation
-- ----------------------------
INSERT INTO `situation` VALUES (1, 13, 16, '您是个人经营还是家庭经营？', '2020-07-09 09:35:19', '2020-07-09 09:35:19');
INSERT INTO `situation` VALUES (2, 13, 16, '您是否需要刻制公章？', '2020-07-09 09:35:43', '2020-07-09 09:35:43');
INSERT INTO `situation` VALUES (3, 20, 16, '您的合伙人性质为？', '2020-07-09 09:36:44', '2020-07-09 09:36:44');
INSERT INTO `situation` VALUES (4, 12, 15, '您是法人还是授权委托人', '2020-07-09 14:48:27', '2020-07-09 14:48:27');
INSERT INTO `situation` VALUES (5, 12, 15, '营业场所是否安装集中空调通风系统？', '2020-07-09 14:48:47', '2020-07-09 14:48:47');
INSERT INTO `situation` VALUES (6, 12, 15, '营业场所场地是否在50个房间以上？', '2020-07-09 14:52:23', '2020-07-09 14:52:23');
INSERT INTO `situation` VALUES (7, 13, 16, '申请登记的经营范围中是否有法律、行政法规和国务院决定规定必须在登记前报经批准的项目？', '2020-07-09 14:53:34', '2020-07-09 14:53:34');
INSERT INTO `situation` VALUES (8, 13, 16, '您是否从事网络经营？', '2020-07-09 14:53:58', '2020-07-09 14:53:58');
INSERT INTO `situation` VALUES (9, 13, 16, '您的经营场所性质是？', '2020-07-09 14:54:22', '2020-07-09 14:54:22');
INSERT INTO `situation` VALUES (10, 13, 16, '您的企业名称是否符合以下情况？', '2020-07-09 14:54:55', '2020-07-09 14:54:55');
INSERT INTO `situation` VALUES (11, 18, 16, '您的企业名称是否涉及以下情形？', '2020-07-09 14:56:57', '2020-07-09 14:56:57');
INSERT INTO `situation` VALUES (12, 18, 16, '您是否从事法律、行政法规规定必须报经有关部门审批的业务？', '2020-07-09 14:57:18', '2020-07-09 14:57:18');
INSERT INTO `situation` VALUES (13, 19, 16, '您的经营场所属于什么性质？', '2020-07-09 14:58:11', '2020-07-09 14:58:11');
INSERT INTO `situation` VALUES (14, 19, 16, '您是否从事法律、行政法规规定必须报经有关部门审批的业务', '2020-07-09 14:59:18', '2020-07-09 14:59:18');

-- ----------------------------
-- Table structure for situation_details
-- ----------------------------
DROP TABLE IF EXISTS `situation_details`;
CREATE TABLE `situation_details`  (
  `situation_details_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `situation_id` int(11) NULL DEFAULT NULL COMMENT '情形id',
  `details_describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详情描述',
  `new_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`situation_details_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '情形详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of situation_details
-- ----------------------------
INSERT INTO `situation_details` VALUES (1, 1, '个人经营', '2020-07-09 09:35:19', '2020-07-09 09:35:19');
INSERT INTO `situation_details` VALUES (2, 1, '家庭经营', '2020-07-09 09:35:19', '2020-07-09 09:35:19');
INSERT INTO `situation_details` VALUES (3, 2, '是', '2020-07-09 09:35:43', '2020-07-09 09:35:43');
INSERT INTO `situation_details` VALUES (4, 2, '否', '2020-07-09 09:35:43', '2020-07-09 09:35:43');
INSERT INTO `situation_details` VALUES (5, 3, '企业', '2020-07-09 09:36:44', '2020-07-09 09:36:44');
INSERT INTO `situation_details` VALUES (6, 3, '事业法人', '2020-07-09 09:36:44', '2020-07-09 09:36:44');
INSERT INTO `situation_details` VALUES (7, 3, '社会团体', '2020-07-09 09:36:44', '2020-07-09 09:36:44');
INSERT INTO `situation_details` VALUES (8, 3, '民办非企业单位', '2020-07-09 09:36:44', '2020-07-09 09:36:44');
INSERT INTO `situation_details` VALUES (9, 3, '自然人', '2020-07-09 09:36:44', '2020-07-09 09:36:44');
INSERT INTO `situation_details` VALUES (10, 3, '其他合伙人', '2020-07-09 09:36:44', '2020-07-09 09:36:44');
INSERT INTO `situation_details` VALUES (11, 4, '法定代表人', '2020-07-09 14:48:27', '2020-07-09 14:48:27');
INSERT INTO `situation_details` VALUES (12, 4, '授权委托人', '2020-07-09 14:48:27', '2020-07-09 14:48:27');
INSERT INTO `situation_details` VALUES (13, 5, '是', '2020-07-09 14:48:47', '2020-07-09 14:48:47');
INSERT INTO `situation_details` VALUES (14, 5, '否', '2020-07-09 14:48:47', '2020-07-09 14:48:47');
INSERT INTO `situation_details` VALUES (15, 6, '是', '2020-07-09 14:52:23', '2020-07-09 14:52:23');
INSERT INTO `situation_details` VALUES (16, 6, '否', '2020-07-09 14:52:23', '2020-07-09 14:52:23');
INSERT INTO `situation_details` VALUES (17, 7, '是', '2020-07-09 14:53:34', '2020-07-09 14:53:34');
INSERT INTO `situation_details` VALUES (18, 7, '否', '2020-07-09 14:53:34', '2020-07-09 14:53:34');
INSERT INTO `situation_details` VALUES (19, 8, '是', '2020-07-09 14:53:58', '2020-07-09 14:53:58');
INSERT INTO `situation_details` VALUES (20, 8, '否', '2020-07-09 14:53:58', '2020-07-09 14:53:58');
INSERT INTO `situation_details` VALUES (21, 9, '自有房产', '2020-07-09 14:54:22', '2020-07-09 14:54:22');
INSERT INTO `situation_details` VALUES (22, 9, '非自有房产', '2020-07-09 14:54:22', '2020-07-09 14:54:22');
INSERT INTO `situation_details` VALUES (23, 9, '无房屋产权证明', '2020-07-09 14:54:22', '2020-07-09 14:54:22');
INSERT INTO `situation_details` VALUES (24, 9, '使用宾馆、饭店', '2020-07-09 14:54:22', '2020-07-09 14:54:22');
INSERT INTO `situation_details` VALUES (25, 9, '使用军队房产', '2020-07-09 14:54:22', '2020-07-09 14:54:22');
INSERT INTO `situation_details` VALUES (26, 9, '网络经营场所', '2020-07-09 14:54:22', '2020-07-09 14:54:22');
INSERT INTO `situation_details` VALUES (27, 10, '名称使用了其他非营利法人简称或者特定称谓', '2020-07-09 14:54:55', '2020-07-09 14:54:55');
INSERT INTO `situation_details` VALUES (28, 10, '名称使用了另一个企业名称的简称或者特定称谓', '2020-07-09 14:54:55', '2020-07-09 14:54:55');
INSERT INTO `situation_details` VALUES (29, 10, '名称使用了国家市场监督管理总局曾经给予驰名商标保护的规范汉字作为同行业个体工商户名称的字号。', '2020-07-09 14:54:55', '2020-07-09 14:54:55');
INSERT INTO `situation_details` VALUES (30, 11, '名称依法须经国家市场监督管理总局预先登记', '2020-07-09 14:56:57', '2020-07-09 14:56:57');
INSERT INTO `situation_details` VALUES (31, 11, '通过企业名称自主申报服务系统成功申报企业名称的', '2020-07-09 14:56:57', '2020-07-09 14:56:57');
INSERT INTO `situation_details` VALUES (32, 11, '名称使用了其他非营利法人简称或者特定称谓', '2020-07-09 14:56:57', '2020-07-09 14:56:57');
INSERT INTO `situation_details` VALUES (33, 11, '名称使用了另一个企业名称的简称或者特定称谓', '2020-07-09 14:56:57', '2020-07-09 14:56:57');
INSERT INTO `situation_details` VALUES (34, 11, '名称使用了国家市场监督管理总局曾经给予驰名商标保护的规范汉字作为同行业企业名称的字号', '2020-07-09 14:56:57', '2020-07-09 14:56:57');
INSERT INTO `situation_details` VALUES (35, 11, '名称与他人登记在先的企业名称存在《广东省工商行政管理局推行企业名称自主申报服务实施办法》第十八条第（三）项、第（四）项、第（五）项和第（六）项情形且有投资关系的', '2020-07-09 14:56:57', '2020-07-09 14:56:57');
INSERT INTO `situation_details` VALUES (36, 11, '以上情形均未涉及', '2020-07-09 14:56:57', '2020-07-09 14:56:57');
INSERT INTO `situation_details` VALUES (37, 12, '是', '2020-07-09 14:57:18', '2020-07-09 14:57:18');
INSERT INTO `situation_details` VALUES (38, 12, '否', '2020-07-09 14:57:18', '2020-07-09 14:57:18');
INSERT INTO `situation_details` VALUES (39, 13, '自有房产', '2020-07-09 14:58:11', '2020-07-09 14:58:11');
INSERT INTO `situation_details` VALUES (40, 13, '使用非自有房产的', '2020-07-09 14:58:11', '2020-07-09 14:58:11');
INSERT INTO `situation_details` VALUES (41, 13, '使用宾馆、饭店', '2020-07-09 14:58:11', '2020-07-09 14:58:11');
INSERT INTO `situation_details` VALUES (42, 13, '使用军队房产', '2020-07-09 14:58:11', '2020-07-09 14:58:11');
INSERT INTO `situation_details` VALUES (43, 14, '是', '2020-07-09 14:59:18', '2020-07-09 14:59:18');
INSERT INTO `situation_details` VALUES (44, 14, '否', '2020-07-09 14:59:18', '2020-07-09 14:59:18');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户名',
  `mobile` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '邮箱',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `insert_uid` int(11) NULL DEFAULT NULL COMMENT '添加该用户的用户id',
  `insert_time` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_del` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除（0：正常；1：已删）',
  `is_job` tinyint(1) NULL DEFAULT 0 COMMENT '是否在职（0：正常；1，离职）',
  `mcode` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '短信验证码',
  `send_time` datetime(0) NULL DEFAULT NULL COMMENT '短信发送时间',
  `version` int(10) NULL DEFAULT 0 COMMENT '更新版本',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`username`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  INDEX `mobile`(`mobile`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '13169113260', '', 'c33367701511b4f6020ec61ded352059', 1, '2018-05-02 16:56:41', '2020-06-22 16:43:25', 0, 0, NULL, NULL, 24);
INSERT INTO `user` VALUES (28, 'admin1', '13122333223', '', 'c33367701511b4f6020ec61ded352059', 1, '2020-06-24 11:34:40', NULL, 0, 0, NULL, NULL, 0);
INSERT INTO `user` VALUES (29, '王科长', '13169113266', '123456', 'e10adc3949ba59abbe56e057f20f883e', 1, '2020-07-07 16:20:01', NULL, 0, 0, NULL, NULL, 0);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` int(11) NOT NULL,
  `role_id` int(5) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `userid`(`user_id`) USING BTREE,
  INDEX `roleid`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (3, 5);
INSERT INTO `user_role` VALUES (12, 5);
INSERT INTO `user_role` VALUES (19, 3);
INSERT INTO `user_role` VALUES (20, 2);
INSERT INTO `user_role` VALUES (21, 4);
INSERT INTO `user_role` VALUES (22, 5);
INSERT INTO `user_role` VALUES (23, 3);
INSERT INTO `user_role` VALUES (24, 5);
INSERT INTO `user_role` VALUES (25, 2);
INSERT INTO `user_role` VALUES (26, 5);
INSERT INTO `user_role` VALUES (27, 1);
INSERT INTO `user_role` VALUES (28, 1);
INSERT INTO `user_role` VALUES (29, 6);

SET FOREIGN_KEY_CHECKS = 1;
