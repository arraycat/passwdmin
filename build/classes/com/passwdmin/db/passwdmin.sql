CREATE DATABASE `passwdmin` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

/*****此数据库中所有表类似下表*****/
CREATE TABLE `root` (
   `pid` int NOT NULL AUTO_INCREMENT,
   `account_name` varchar(128) DEFAULT NULL,
   `account_passwd` varchar(128) DEFAULT NULL,
   `account_type` varchar(10) DEFAULT NULL,
   `account_desc` text,
   `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (`pid`)
 ) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 


CREATE DATABASE `passwdmin_user` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
/*******用户表*********/
CREATE TABLE `customer` (
  `uid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL,
  `password` char(128) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `last_logintime` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_ipaddr` varchar(40) DEFAULT '0.0.0.0',
  `create_time` datetime DEFAULT '2020-01-01 00:00:00',
  `aes_key` varchar(128) NOT NULL,
  `level` int DEFAULT '1',
  PRIMARY KEY (`uid`,`username`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000072 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
