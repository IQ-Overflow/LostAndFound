/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.49 : Database - lostandfound
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lostandfound` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `lostandfound`;

/*Table structure for table `card` */

DROP TABLE IF EXISTS `card`;

CREATE TABLE `card` (
  `stuID` char(10) NOT NULL COMMENT '学号，主键',
  `college` varchar(10) DEFAULT NULL COMMENT '学院',
  `stuName` varchar(10) DEFAULT NULL COMMENT '姓名',
  `uID` varchar(64) DEFAULT NULL COMMENT '发布人的uID，外键',
  `flag` tinyint(1) DEFAULT NULL COMMENT 'TURE表示发布人是拾得人，FALSE表示失主',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发布时间，当前时间',
  PRIMARY KEY (`stuID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `card` */

insert  into `card`(`stuID`,`college`,`stuName`,`uID`,`flag`,`time`) values ('123456789','计算机学院','张三','1',1,'2020-11-10 16:15:32'),('3118005379','土木工程学院','李四','3',0,'2020-11-10 16:17:20');

/*Table structure for table `others` */

DROP TABLE IF EXISTS `others`;

CREATE TABLE `others` (
  `oID` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(32) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `pic` varchar(256) DEFAULT NULL COMMENT '图片（可空）',
  `uID` varchar(64) DEFAULT NULL COMMENT '发布人的UID，外键',
  `flag` tinyint(1) DEFAULT NULL COMMENT 'TURE表示发布人是拾得人，FALSE表示失主',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发布时间，当前时间',
  PRIMARY KEY (`oID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `others` */

insert  into `others`(`oID`,`title`,`content`,`pic`,`uID`,`flag`,`time`) values (1,'水瓶丢失',NULL,NULL,'1',1,'2020-11-11 16:21:18');

/*Table structure for table `reason` */

DROP TABLE IF EXISTS `reason`;

/*
CREATE TABLE `reason` (
  `flag` tinyint(1) DEFAULT NULL COMMENT 'TURE表示寻卡贴，FALSE表示寻物贴',
  `ID` varchar(32) DEFAULT NULL COMMENT 'stuID或oID',
  `Message` text COMMENT '申请理由'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
*/

CREATE TABLE reason
(`pID` VARCHAR(64) COMMENT '物品的id，为stuID或oID',
 `fID` VARCHAR(64) COMMENT '申请人的uid',
 `tID` VARCHAR(64) COMMENT '被申请人的uid',
 `message` VARCHAR(128) COMMENT '申请理由',
 `state` int(1) COMMENT '状态标记：0进行中，1已同意，2已拒绝',
 PRIMARY KEY(pID,fID,tID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `reason` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uID` char(64) NOT NULL,
  `userName` varchar(64) DEFAULT NULL,
  `wxAccount` varchar(64) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  PRIMARY KEY (`uID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`uID`,`userName`,`wxAccount`,`sex`) values ('1','张三','123456789','男'),('2','王五','123456','男'),('3','李四','456789','男');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
