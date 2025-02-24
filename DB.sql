-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: localhost    Database: feedback
-- ------------------------------------------------------
-- Server version	5.7.44-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_admin`
--

DROP TABLE IF EXISTS `t_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_admin` (
  `account` varchar(100) NOT NULL COMMENT '账号',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `gender` int(11) DEFAULT NULL COMMENT '性别',
  `phone` varchar(100) DEFAULT NULL COMMENT '手机号',
  `birthday` varchar(100) DEFAULT NULL COMMENT '出生年月',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `nativePlace` varchar(100) DEFAULT NULL COMMENT '籍贯',
  `picture` varchar(100) DEFAULT NULL COMMENT '照片',
  `role` varchar(100) DEFAULT 'admin' COMMENT '角色：admin',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='管理员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_admin`
--

LOCK TABLES `t_admin` WRITE;
/*!40000 ALTER TABLE `t_admin` DISABLE KEYS */;
INSERT INTO `t_admin` VALUES ('10001','张强',1,'17878789898','2001-10-12 19:34:2','123456','广东广州','/picture','admin',1),('10002','王强',1,'18928282828','2023-12-31','123456','天津市',NULL,'admin',2),('13','34',0,'34','2023-12-31','3434','34',NULL,'admin',3),('343','343',0,'343','2023-12-24','34','34',NULL,'admin',4),('333','333',0,'34','2023-12-04','3','3',NULL,'admin',5),('34','34',1,'33','2023-12-15','33','33',NULL,'admin',6);
/*!40000 ALTER TABLE `t_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_course`
--

DROP TABLE IF EXISTS `t_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '课程名称',
  `description` varchar(100) DEFAULT NULL COMMENT '课程描述',
  `credit` int(11) DEFAULT NULL COMMENT '学分',
  `textbook` varchar(100) DEFAULT NULL COMMENT '教材',
  `type` varchar(100) DEFAULT NULL COMMENT '课程类型：必修课、选修课、专业课',
  `hour` int(11) DEFAULT NULL COMMENT '学时',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='课程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_course`
--

LOCK TABLES `t_course` WRITE;
/*!40000 ALTER TABLE `t_course` DISABLE KEYS */;
INSERT INTO `t_course` VALUES (1,'高等数学','学高等数学',4,'高等数学（上）','必修课',64),(15,'线性代数123','线性代数12',3,'线性代数12','线性代数12',4),(16,'线性代数2','线性代数2',2,'线性代数2','线性代数2',2),(17,'线性代数3','线性代数3',2,'线性代数3','线性代数3',2),(18,'离散数学','离散数学',4,'离散数学','离散数学',64),(19,'概率论与统计','概率论与统计',4,'概率论与统计','概率论与统计',64);
/*!40000 ALTER TABLE `t_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_index`
--

DROP TABLE IF EXISTS `t_index`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_index` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(100) DEFAULT NULL COMMENT '指标内容',
  `createTime` varchar(100) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='评教指标表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_index`
--

LOCK TABLES `t_index` WRITE;
/*!40000 ALTER TABLE `t_index` DISABLE KEYS */;
INSERT INTO `t_index` VALUES (1,'否能够激发学生的学习兴趣，鼓励学生积极参与课堂活动','date()'),(2,'瞎扯。。。呀呀呀呀','2023-12-18 02:35:52'),(3,'下下下啊澈','2023-12-18 02:36:05'),(4,'dfdf','2023-12-18 02:36:10'),(5,'dfd的地方','2023-12-18 02:36:14'),(6,'打发打发','2023-12-18 02:36:17');
/*!40000 ALTER TABLE `t_index` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_notice`
--

DROP TABLE IF EXISTS `t_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` varchar(200) DEFAULT NULL COMMENT '内容',
  `createTime` datetime DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='系统公告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_notice`
--

LOCK TABLES `t_notice` WRITE;
/*!40000 ALTER TABLE `t_notice` DISABLE KEYS */;
INSERT INTO `t_notice` VALUES (8,'公告1','这是公告1这是公告1这是公告1这是公告1这是公告1这是公告1这是公告1这是公告1这是公告1这是公告1这是公告1这是公告1这是公告1这是公告1这是公告1这是公告1这是公告1这是公告1这是公告1这是公告1这是公告1这是公告1','2023-12-20 20:24:49'),(9,'地方','发','2023-12-20 20:44:59'),(10,'系统公告','明天大扫除','2023-12-20 20:45:19'),(11,'的','1','2023-12-20 20:46:09'),(12,'d','dd','2023-12-20 20:46:12'),(13,'dfd','dfd','2023-12-20 20:46:15');
/*!40000 ALTER TABLE `t_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_student`
--

DROP TABLE IF EXISTS `t_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_student` (
  `no` varchar(100) NOT NULL COMMENT '学号',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `gender` int(11) DEFAULT NULL COMMENT '性别',
  `phone` varchar(100) DEFAULT NULL COMMENT '手机号',
  `birthday` varchar(100) DEFAULT NULL COMMENT '出生年月',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `className` varchar(100) DEFAULT NULL COMMENT '班级名称',
  `picture` varchar(100) DEFAULT NULL COMMENT '照片',
  `nativePlace` varchar(100) DEFAULT NULL COMMENT '籍贯',
  `role` varchar(100) DEFAULT 'student' COMMENT '角色: student',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='学生表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_student`
--

LOCK TABLES `t_student` WRITE;
/*!40000 ALTER TABLE `t_student` DISABLE KEYS */;
INSERT INTO `t_student` VALUES ('20001','张名',1,'19089896767','2001-10-1 10:13:2','123456','计科203','/picture','安徽','student',1),('20002','高名',0,'18927274747','2001-10-23','123456','d',NULL,'d','student',2),('df123','fd123',0,'dfd123','2023-12-19','fd13','df123',NULL,'fd123','student',3),('df','fdfd',1,'fd','2023-12-25','df','df',NULL,'df','student',4),('dfd','dfdf',1,'dfdf','2023-12-26','fdf','dfd',NULL,'dfd','student',5),('dfdf','fdf',0,'dfdf','2023-12-26','dfd','dfdf',NULL,'dfdf','student',6);
/*!40000 ALTER TABLE `t_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_student_course`
--

DROP TABLE IF EXISTS `t_student_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_student_course` (
  `studentId` int(11) NOT NULL COMMENT '学号',
  `teacherCourseId` varchar(100) NOT NULL COMMENT '教师任课id',
  `createTime` varchar(100) DEFAULT NULL COMMENT '创建时间',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `status` int(11) DEFAULT '0' COMMENT '评教状态：0未评教；1已评教',
  `score` int(11) DEFAULT NULL COMMENT '课程评教分数',
  `content` varchar(300) DEFAULT NULL COMMENT '评教内容',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_student_course_un` (`studentId`,`teacherCourseId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='学生课程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_student_course`
--

LOCK TABLES `t_student_course` WRITE;
/*!40000 ALTER TABLE `t_student_course` DISABLE KEYS */;
INSERT INTO `t_student_course` VALUES (1,'3','2023-12-19 23:04:48',5,1,84,'老师很专业'),(1,'10','2023-12-19 23:05:36',6,1,100,'对方的'),(2,'10','2023-12-19 23:15:12',7,1,60,'hhh'),(2,'8','2023-12-19 23:15:14',8,1,71,'haoba'),(2,'3','2023-12-19 23:15:16',9,0,NULL,NULL),(6,'3','2023-12-19 23:16:04',11,0,NULL,NULL),(2,'5','2023-12-20 12:41:51',16,0,NULL,NULL),(2,'9','2023-12-20 12:46:58',19,0,NULL,NULL),(2,'6','2023-12-20 12:47:00',21,0,NULL,NULL),(1,'5','2023-12-20 19:44:31',25,1,88,'老师很优秀！');
/*!40000 ALTER TABLE `t_student_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_student_index`
--

DROP TABLE IF EXISTS `t_student_index`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_student_index` (
  `score` int(11) DEFAULT NULL COMMENT '评教分数',
  `createTime` varchar(100) DEFAULT NULL COMMENT '评教时间',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentCourseId` int(11) DEFAULT NULL COMMENT '学生选课id',
  `indexId` int(11) DEFAULT NULL COMMENT '评教指标id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='学生评教表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_student_index`
--

LOCK TABLES `t_student_index` WRITE;
/*!40000 ALTER TABLE `t_student_index` DISABLE KEYS */;
INSERT INTO `t_student_index` VALUES (100,'2023-12-20 12:41:03',13,6,1),(100,'2023-12-20 12:41:03',14,6,2),(100,'2023-12-20 12:41:03',15,6,3),(100,'2023-12-20 12:41:03',16,6,4),(100,'2023-12-20 12:41:03',17,6,5),(100,'2023-12-20 12:41:03',18,6,6),(60,'2023-12-20 12:44:45',19,7,1),(60,'2023-12-20 12:44:46',20,7,2),(60,'2023-12-20 12:44:46',21,7,3),(60,'2023-12-20 12:44:46',22,7,4),(60,'2023-12-20 12:44:46',23,7,5),(60,'2023-12-20 12:44:46',24,7,6),(60,'2023-12-20 12:45:34',25,8,1),(75,'2023-12-20 12:45:34',26,8,2),(60,'2023-12-20 12:45:34',27,8,3),(75,'2023-12-20 12:45:34',28,8,4),(60,'2023-12-20 12:45:34',29,8,5),(100,'2023-12-20 12:45:34',30,8,6),(75,'2023-12-20 19:41:05',31,5,1),(85,'2023-12-20 19:41:05',32,5,2),(100,'2023-12-20 19:41:05',33,5,3),(85,'2023-12-20 19:41:05',34,5,4),(85,'2023-12-20 19:41:05',35,5,5),(75,'2023-12-20 19:41:05',36,5,6),(60,'2023-12-20 19:58:19',37,25,1),(85,'2023-12-20 19:58:19',38,25,2),(85,'2023-12-20 19:58:19',39,25,3),(100,'2023-12-20 19:58:19',40,25,4),(100,'2023-12-20 19:58:19',41,25,5),(100,'2023-12-20 19:58:19',42,25,6);
/*!40000 ALTER TABLE `t_student_index` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_teacher`
--

DROP TABLE IF EXISTS `t_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_teacher` (
  `no` varchar(100) NOT NULL COMMENT '教工号',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `gender` int(11) DEFAULT NULL COMMENT '性别',
  `phone` varchar(100) DEFAULT NULL COMMENT '手机号',
  `birthday` varchar(100) DEFAULT NULL COMMENT '出生年月',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `title` varchar(100) DEFAULT NULL COMMENT '职称',
  `nativePlace` varchar(100) DEFAULT NULL COMMENT '籍贯',
  `picture` varchar(100) DEFAULT NULL COMMENT '照片',
  `role` varchar(100) DEFAULT 'teacher' COMMENT '角色：teacher',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='教师表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_teacher`
--

LOCK TABLES `t_teacher` WRITE;
/*!40000 ALTER TABLE `t_teacher` DISABLE KEYS */;
INSERT INTO `t_teacher` VALUES ('30001','高明',1,'17889898989','1989-09-29','123456','副教授','北京','/picture','teacher',1),('30003','李明子',1,'18989898989','1991-02-28','123456','副教授','天津市',NULL,'teacher',2),('30002','对方的',1,'1234','2023-12-19','343','33','343',NULL,'teacher',3),('4343','34',0,'3431','2023-12-25','3434','343','3434',NULL,'teacher',4),('34343431','1113',0,'12334','2023-12-21','22','11','11',NULL,'teacher',5),('43','23',0,'234','2023-12-26','2','2343','23',NULL,'teacher',6);
/*!40000 ALTER TABLE `t_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_teacher_course`
--

DROP TABLE IF EXISTS `t_teacher_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_teacher_course` (
  `teacherId` int(11) NOT NULL COMMENT '任课教师',
  `courseId` int(11) NOT NULL COMMENT '课程id',
  `createTime` varchar(100) DEFAULT NULL COMMENT '创建时间',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_teacher_course_un` (`teacherId`,`courseId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='教师任课表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_teacher_course`
--

LOCK TABLES `t_teacher_course` WRITE;
/*!40000 ALTER TABLE `t_teacher_course` DISABLE KEYS */;
INSERT INTO `t_teacher_course` VALUES (1,1,'2023-12-19 21:13:26',3),(2,18,'2023-12-19 21:14:37',5),(3,1,'2023-12-19 21:38:43',6),(1,18,'2023-12-19 21:45:05',8),(1,16,'2023-12-19 21:45:08',9),(1,19,'2023-12-19 23:05:25',10);
/*!40000 ALTER TABLE `t_teacher_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'feedback'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-20 21:08:12
