-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 18.143.78.214    Database: nutrimeal
-- ------------------------------------------------------
-- Server version	8.0.37-0ubuntu0.24.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `addresses` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `district` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `ward` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `apartment_number` varchar(255) DEFAULT NULL,
  `default_address` bit(1) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `addresses_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (17,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','Nguyễn Thanh Tùng','0384370666',_binary '','Quận Long Biên','Phường Gia Thụy','Hàm Cá Mập',_binary '',_binary ''),(21,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','Vũ Gia Khánh','0384370666',_binary '','Quận Long Biên','Phường Long Biên','57 Bát Khối',_binary '\0',_binary ''),(23,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','Khánh cá vip pro','0123456789',_binary '','Quận Đống Đa','Phường Ngã Tư Sở','Ngã Tư Sở',_binary '\0',_binary ''),(26,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','Vũ Gia Khánh Vip','0372454714',_binary '','Quận Long Biên','Phường Phúc Đồng','Ngõ 57 Bát Khối',_binary '',_binary ''),(27,'0675899f-e0be-4d29-b9b7-4b8599d311bf','Đỗ Thùy Dương','0333311100',_binary '\0','Quận Hoàn Kiếm','Phường Lý Thái Tổ','abcd',_binary '',_binary ''),(28,'0675899f-e0be-4d29-b9b7-4b8599d311bf','Đỗ Thùy Dương','0333311102',_binary '','Quận Long Biên','Phường Giang Biên','abcd',_binary '\0',_binary ''),(30,'0675899f-e0be-4d29-b9b7-4b8599d311bf','Nguyễn Thanh Tùng','0333311102',_binary '','Quận Hoàn Kiếm','Phường Lý Thái Tổ','12AB',_binary '\0',_binary ''),(33,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','Lưu Trọng Phụng','0123456789',NULL,'Quận Đống Đa','Phường Phương Mai','150 Lương Định Của',_binary '\0',_binary ''),(34,'2a8d6e1f-7a2e-4f8a-a20a-85b70dccbe82','Beoo','0981581000',_binary '','Quận Hoàn Kiếm','Phường Chương Dương','35',_binary '',_binary ''),(37,'d4eb6b07-f46d-4627-885b-80cf8002e9c4','Nguyen Thanh Tung','0333311102',_binary '\0','Quận Long Biên','Phường Việt Hưng','gdsfgsdf',_binary '\0',NULL),(38,'4043757a-3df8-40af-a056-26efb28b5c40','Vũ Gia Khánh Vip','0372454714',_binary '','Quận Long Biên','Phường Gia Thụy','Ngõ 57 Bát Khối',_binary '\0',_binary ''),(39,'4043757a-3df8-40af-a056-26efb28b5c40','giakhanh','0384370666',_binary '','Quận Hoàn Kiếm','Phường Hàng Đào','Ngõ 57 Bát Khối',_binary '',_binary ''),(40,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','1','0384370666',_binary '','Quận Hoàn Kiếm','Chọn phường','qw',_binary '\0',NULL),(41,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','1','0384370666',_binary '','Quận Long Biên','Phường Gia Thụy','1',_binary '\0',_binary '\0'),(42,'d4eb6b07-f46d-4627-885b-80cf8002e9c4','e21q','0384370665',NULL,'Quận Long Biên','Phường Hàng Bài','dsafsda',_binary '\0',_binary ''),(49,'d4eb6b07-f46d-4627-885b-80cf8002e9c4','Nguyen Thanh Tung','0384370666',_binary '','Quận Hoàn Kiếm','Phường Hàng Mã','vxczvzxcv',_binary '\0',_binary ''),(54,'d4eb6b07-f46d-4627-885b-80cf8002e9c4','Nguyen Thanh Tung','0333311102',_binary '\0','Quận Hoàn Kiếm','Phường Phúc Tân','fasdfasd',_binary '',_binary ''),(55,'2a8d6e1f-7a2e-4f8a-a20a-85b70dccbe82','tuandu','0999999999',_binary '','Quận Long Biên','Phường Cự Khối','123',_binary '\0',_binary ''),(56,'2a8d6e1f-7a2e-4f8a-a20a-85b70dccbe82','tuandu','0999999999',_binary '','Quận Long Biên','Phường Cự Khối','123',_binary '\0',_binary '\0'),(57,'a278196e-6053-4027-ad8f-18b2dd5d88da','tuandu','0981581000',_binary '','Quận Long Biên','Phường Gia Thụy','35',_binary '\0',_binary ''),(58,'a278196e-6053-4027-ad8f-18b2dd5d88da','Beoo','0981581000',NULL,'Quận Hoàn Kiếm','Phường Chương Dương','123',_binary '\0',_binary ''),(59,'a278196e-6053-4027-ad8f-18b2dd5d88da','Biển','0999999999',_binary '','Quận Hoàn Kiếm','Phường Trần Hưng Đạo','26 ok',_binary '',_binary ''),(60,'0923f64d-73ae-4e85-911d-93394c539504','Phạm Văn Đồng','0331231235',_binary '','Quận Hoàn Kiếm','Phường Hàng Buồm','Hồ Gươm',_binary '',_binary ''),(61,'86939090-6934-42f7-8efa-e3bee331aac1','Hoàng Ly','0916986874',_binary '\0','Quận Hoàn Kiếm','Phường Đồng Xuân','110',_binary '\0',_binary ''),(62,'86939090-6934-42f7-8efa-e3bee331aac1','Hoàng Ly','0916986874',_binary '\0','Quận Hoàn Kiếm','Phường Lý Thái Tổ','110',_binary '\0',_binary ''),(63,'86939090-6934-42f7-8efa-e3bee331aac1','Hoàng Ly','0916986874',_binary '\0','Quận Hoàn Kiếm','Phường Lý Thái Tổ','110',_binary '\0',_binary '\0'),(64,'2a8d6e1f-7a2e-4f8a-a20a-85b70dccbe82','Tuấn Dự','0123456781',_binary '','Quận Hoàn Kiếm','Phường Chương Dương','123',_binary '',_binary '\0'),(65,'2a8d6e1f-7a2e-4f8a-a20a-85b70dccbe82','Duong Tuan Du','0981581001',_binary '','Quận Hoàn Kiếm','Phường Chương Dương','12',_binary '\0',_binary ''),(66,'86939090-6934-42f7-8efa-e3bee331aac1','Hoàng Ly','0916986874',_binary '\0','Quận Long Biên','Phường Gia Thụy','123',_binary '',_binary ''),(67,'ea7ab4c0-26ec-41f9-b3a6-7a75a1fd06c2','Phương Hải Đăng','0335380952',_binary '','Quận Đống Đa','Phường Láng Hạ','aaa',_binary '',_binary '');
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `combo`
--

DROP TABLE IF EXISTS `combo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `combo` (
  `combo_id` int NOT NULL AUTO_INCREMENT,
  `combo_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `combo_description` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci,
  `combo_image` varchar(255) DEFAULT NULL,
  `combo_price_7days` int DEFAULT NULL,
  `combo_price_30days` int DEFAULT NULL,
  `combo_calories` int DEFAULT NULL,
  `combo_type_id` int DEFAULT NULL,
  `combo_time` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`combo_id`),
  KEY `combo_type_id` (`combo_type_id`),
  CONSTRAINT `combo_ibfk_1` FOREIGN KEY (`combo_type_id`) REFERENCES `combo_type` (`combo_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `combo`
--

LOCK TABLES `combo` WRITE;
/*!40000 ALTER TABLE `combo` DISABLE KEYS */;
INSERT INTO `combo` VALUES (4,'GÓI LUNCH','Thích hợp cho người ăn kiêng bận rộn, chỉ có nhu cầu dùng 1 bữa trưa. Nhiều rau củ, kèm trái cây tráng miệng và nước thanh nhiệt. ','http://res.cloudinary.com/dhwh3rgra/image/upload/v1716829405/6d313339-eb19-4045-9f2c-87570ae55f28.png',350000,1260000,2500,2,'L',_binary ''),(6,'GÓI FULL','Gói 3 bữa SÁNG - TRƯA - TỐI','http://res.cloudinary.com/dhwh3rgra/image/upload/v1716829857/6a30bb04-3456-41d6-9550-feeebed383bf.jpg',825000,2970000,2700,2,'BLD',_binary ''),(7,'GÓI FIT1','Gói 2 bữa SÁNG - TRƯA\r\n- Giao 02 phần ăn tận nơi mỗi ngày\r\n- Calories dao động từ 1000 - 1100 Kcal mỗi ngày\r\n- Kèm tinh bột phức, ít đường, đảm bảo ko bột ngọt, nhiều rau củ và chất đạm','http://res.cloudinary.com/dhwh3rgra/image/upload/v1716920981/b4677c8e-eeea-4adc-a050-7d40b5ff2624.jpg',650000,2340000,1100,2,'BL',_binary ''),(9,'GÓI FIT 2','Gói 2 bữa SÁNG - TỐI\r\nSử dụng thực đơn 2 bữa SÁNG - TỐI\r\nGiao 02 phần ăn tận nơi mỗi ngày, tất cả các ngày trong tuần\r\nCalories dao động từ 1000 - 1100 Kcal mỗi ngày','http://res.cloudinary.com/dhwh3rgra/image/upload/v1717500235/0f768f9e-6849-4d07-8cb1-c3ac8e3dc14d.jpg',650000,2340000,1100,2,'BD',_binary ''),(10,'GÓI SLIM','Gói SLIM gồm 2 bữa TRƯA - TỐI (KHÔNG tinh bột, GẤP ĐÔI rau)','http://res.cloudinary.com/dhwh3rgra/image/upload/v1717500413/6eb7488c-897d-40ee-8879-c54d9581d252.png',600000,2160000,800,2,'LD',_binary ''),(11,'GÓI FIT 3','Gói 2 bữa TRƯA - TỐI (Best Seller)\r\n','http://res.cloudinary.com/dhwh3rgra/image/upload/v1717500489/436c05bc-bdc7-4578-961a-88b2f21cb81b.jpg',650000,2340000,1150,2,'LD',_binary ''),(12,'GÓI MEAT','Gói 2 bữa TRƯA - TỐI (gấp đôi thịt)','http://res.cloudinary.com/dhwh3rgra/image/upload/v1717500581/0704a415-9acb-4ff4-9d3d-43a049bb2253.jpg',950000,3420000,2100,2,'LD',_binary ''),(13,'GÓI MEAT-S','Gói 02 bữa TRƯA - TỐI gấp đôi Đạm + 01 bữa ăn Sáng\r\n\r\n','http://res.cloudinary.com/dhwh3rgra/image/upload/v1717500704/3205f348-72a7-4c35-846f-f357de78c672.jpg',1225000,4410000,2500,2,'LD',_binary ''),(15,'GÓI CHAY','Gói 3 bữa chay \r\n\r\n','http://res.cloudinary.com/dhwh3rgra/image/upload/v1717501164/f463050f-3e67-4d34-8504-1439e97e2899.jpg',650000,2340000,1500,1,'BLD',_binary '');
/*!40000 ALTER TABLE `combo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `combo_type`
--

DROP TABLE IF EXISTS `combo_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `combo_type` (
  `combo_type_id` int NOT NULL AUTO_INCREMENT,
  `combo_type_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `combo_type_description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`combo_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `combo_type`
--

LOCK TABLES `combo_type` WRITE;
/*!40000 ALTER TABLE `combo_type` DISABLE KEYS */;
INSERT INTO `combo_type` VALUES (1,'V','Ăn chay',NULL),(2,'N','Ăn thường',NULL);
/*!40000 ALTER TABLE `combo_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `daily_menu`
--

DROP TABLE IF EXISTS `daily_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `daily_menu` (
  `daily_menu_id` int NOT NULL AUTO_INCREMENT,
  `daily_menu_date` date DEFAULT NULL,
  `daily_menu_type` varchar(255) DEFAULT NULL,
  `dish_id_breakfast` int DEFAULT NULL,
  `dish_id_dinner` int DEFAULT NULL,
  `dish_id_lunch` int DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `daily_menu_day` date DEFAULT NULL,
  PRIMARY KEY (`daily_menu_id`),
  KEY `FKj86p8o1rkbyodq6o7wk0qbfke` (`dish_id_breakfast`),
  KEY `FK3pendbgpfvqxa0qh2mtnh93vq` (`dish_id_dinner`),
  KEY `FK1ryo0aeu1ffqxn2v1it8tb0hg` (`dish_id_lunch`),
  CONSTRAINT `FK1ryo0aeu1ffqxn2v1it8tb0hg` FOREIGN KEY (`dish_id_lunch`) REFERENCES `dish` (`dish_id`),
  CONSTRAINT `FK3pendbgpfvqxa0qh2mtnh93vq` FOREIGN KEY (`dish_id_dinner`) REFERENCES `dish` (`dish_id`),
  CONSTRAINT `FKj86p8o1rkbyodq6o7wk0qbfke` FOREIGN KEY (`dish_id_breakfast`) REFERENCES `dish` (`dish_id`)
) ENGINE=InnoDB AUTO_INCREMENT=256 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daily_menu`
--

LOCK TABLES `daily_menu` WRITE;
/*!40000 ALTER TABLE `daily_menu` DISABLE KEYS */;
INSERT INTO `daily_menu` VALUES (176,'2024-07-08','N',4,97,90,_binary '',NULL),(177,'2024-07-09','N',126,4,2,_binary '',NULL),(178,'2024-07-10','N',6,93,90,_binary '',NULL),(179,'2024-07-11','N',6,100,99,_binary '',NULL),(180,'2024-07-12','N',96,94,6,_binary '',NULL),(181,'2024-07-13','N',96,93,98,_binary '',NULL),(182,'2024-07-14','N',97,6,97,_binary '',NULL),(183,'2024-07-15','N',94,92,94,_binary '',NULL),(184,'2024-07-16','N',4,92,89,_binary '',NULL),(185,'2024-07-17','N',94,94,6,_binary '',NULL),(186,'2024-07-18','N',99,96,97,_binary '',NULL),(187,'2024-07-19','N',126,100,91,_binary '',NULL),(188,'2024-07-20','N',100,91,91,_binary '',NULL),(189,'2024-07-21','N',100,99,91,_binary '',NULL),(190,'2024-07-22','N',89,99,98,_binary '',NULL),(191,'2024-07-23','N',98,95,100,_binary '',NULL),(192,'2024-07-24','N',95,100,126,_binary '',NULL),(193,'2024-07-25','N',92,99,90,_binary '',NULL),(194,'2024-07-26','N',97,95,89,_binary '',NULL),(195,'2024-07-27','N',100,6,96,_binary '',NULL),(196,'2024-07-28','N',91,99,99,_binary '',NULL),(197,'2024-07-29','N',99,4,126,_binary '',NULL),(198,'2024-07-30','N',91,95,126,_binary '',NULL),(199,'2024-07-31','N',97,2,94,_binary '',NULL),(200,'2024-08-01','N',95,91,97,_binary '',NULL),(201,'2024-08-02','N',96,96,97,_binary '',NULL),(202,'2024-08-03','N',99,94,96,_binary '',NULL),(203,'2024-08-04','N',93,88,4,_binary '',NULL),(204,'2024-08-05','N',126,95,95,_binary '',NULL),(205,'2024-08-06','N',98,4,100,_binary '',NULL),(206,'2024-08-07','N',93,99,126,_binary '',NULL),(207,'2024-08-08','N',89,100,99,_binary '',NULL),(208,'2024-08-09','N',4,98,98,_binary '',NULL),(209,'2024-08-10','N',99,93,88,_binary '',NULL),(210,'2024-08-11','N',92,91,93,_binary '',NULL),(211,'2024-08-12','N',97,6,90,_binary '',NULL),(212,'2024-08-13','N',97,100,99,_binary '',NULL),(213,'2024-08-14','N',90,98,2,_binary '',NULL),(214,'2024-08-15','N',89,88,93,_binary '',NULL),(215,'2024-08-16','N',98,93,99,_binary '',NULL),(216,'2024-07-08','V',129,104,111,_binary '',NULL),(217,'2024-07-09','V',109,117,101,_binary '',NULL),(218,'2024-07-10','V',103,109,115,_binary '',NULL),(219,'2024-07-11','V',127,128,117,_binary '',NULL),(220,'2024-07-12','V',118,117,105,_binary '',NULL),(221,'2024-07-13','V',106,108,118,_binary '',NULL),(222,'2024-07-14','V',129,115,117,_binary '',NULL),(223,'2024-07-15','V',104,118,128,_binary '',NULL),(224,'2024-07-16','V',115,105,105,_binary '',NULL),(225,'2024-07-17','V',104,129,129,_binary '',NULL),(226,'2024-07-18','V',107,101,129,_binary '',NULL),(227,'2024-07-19','V',104,111,101,_binary '',NULL),(228,'2024-07-20','V',107,111,101,_binary '',NULL),(229,'2024-07-21','V',115,108,109,_binary '',NULL),(230,'2024-07-22','V',107,120,103,_binary '',NULL),(231,'2024-07-23','V',108,107,118,_binary '',NULL),(232,'2024-07-24','V',103,115,102,_binary '',NULL),(233,'2024-07-25','V',127,116,106,_binary '',NULL),(234,'2024-07-26','V',101,104,118,_binary '',NULL),(235,'2024-07-27','V',116,106,102,_binary '',NULL),(236,'2024-07-28','V',116,118,109,_binary '',NULL),(237,'2024-07-29','V',117,106,101,_binary '',NULL),(238,'2024-07-30','V',116,120,128,_binary '',NULL),(239,'2024-07-31','V',127,106,118,_binary '',NULL),(240,'2024-08-01','V',105,120,108,_binary '',NULL),(241,'2024-08-02','V',105,103,115,_binary '',NULL),(242,'2024-08-03','V',104,119,105,_binary '',NULL),(243,'2024-08-04','V',128,116,101,_binary '',NULL),(244,'2024-08-05','V',105,101,107,_binary '',NULL),(245,'2024-08-06','V',104,129,106,_binary '',NULL),(246,'2024-08-07','V',117,106,108,_binary '',NULL),(247,'2024-08-08','V',105,129,108,_binary '',NULL),(248,'2024-08-09','V',105,116,109,_binary '',NULL),(249,'2024-08-10','V',104,105,127,_binary '',NULL),(250,'2024-08-11','V',115,103,108,_binary '',NULL),(251,'2024-08-12','V',101,128,102,_binary '',NULL),(252,'2024-08-13','V',102,119,129,_binary '',NULL),(253,'2024-08-14','V',119,119,107,_binary '',NULL),(254,'2024-08-15','V',102,105,127,_binary '',NULL),(255,'2024-08-16','V',127,120,104,_binary '',NULL);
/*!40000 ALTER TABLE `daily_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery`
--

DROP TABLE IF EXISTS `delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery` (
  `delivery_id` int NOT NULL AUTO_INCREMENT,
  `delivery_date` date DEFAULT NULL,
  `delivery_status` varchar(255) DEFAULT NULL,
  `delivery_note` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `delivery_update_time` datetime DEFAULT NULL,
  `delivery_address` varchar(255) DEFAULT NULL,
  `delivery_price` int DEFAULT NULL,
  `delivery_time` varchar(255) DEFAULT NULL,
  `delivery_phone` varchar(255) DEFAULT NULL,
  `shipper_id` varchar(255) DEFAULT NULL,
  `is_bonus` bit(1) DEFAULT NULL,
  PRIMARY KEY (`delivery_id`),
  KEY `order_id` (`order_id`),
  KEY `user_id` (`user_id`),
  KEY `FKkvlyeigyj1265vx8jupqjycnx` (`shipper_id`),
  CONSTRAINT `delivery_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `delivery_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKkvlyeigyj1265vx8jupqjycnx` FOREIGN KEY (`shipper_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1080 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery`
--

LOCK TABLES `delivery` WRITE;
/*!40000 ALTER TABLE `delivery` DISABLE KEYS */;
INSERT INTO `delivery` VALUES (979,'2024-07-09','DELAYED','DELAYED',177,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','2024-07-08 15:12:33','150 Lương Định Của, Phường Phương Mai, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(980,'2024-07-10','DELAYED','DELAYED',177,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','2024-07-09 10:33:27','150 Lương Định Của, Phường Phương Mai, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(981,'2024-07-11','NOT_DELIVERED',NULL,177,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc',NULL,'150 Lương Định Của, Phường Phương Mai, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(982,'2024-07-12','NOT_DELIVERED',NULL,177,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc',NULL,'150 Lương Định Của, Phường Phương Mai, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(983,'2024-07-13','NOT_DELIVERED',NULL,177,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc',NULL,'150 Lương Định Của, Phường Phương Mai, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(984,'2024-07-14','NOT_DELIVERED',NULL,177,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc',NULL,'150 Lương Định Của, Phường Phương Mai, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(985,'2024-07-15','NOT_DELIVERED',NULL,177,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc',NULL,'150 Lương Định Của, Phường Phương Mai, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(986,'2024-07-16','NOT_DELIVERED','DELAYED FROM 2024-07-15',177,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','2024-07-08 15:12:33','150 Lương Định Của, Phường Phương Mai, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(987,'2024-07-17','NOT_DELIVERED','DELAYED FROM 2024-07-10',177,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','2024-07-09 10:33:27','150 Lương Định Của, Phường Phương Mai, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary ''),(988,'2024-07-09','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',6715000,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(989,'2024-07-10','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(990,'2024-07-11','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(991,'2024-07-12','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(992,'2024-07-13','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(993,'2024-07-14','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(994,'2024-07-15','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(995,'2024-07-16','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(996,'2024-07-17','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(997,'2024-07-18','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(998,'2024-07-19','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(999,'2024-07-20','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(1000,'2024-07-21','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(1001,'2024-07-22','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(1002,'2024-07-23','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(1003,'2024-07-24','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(1004,'2024-07-25','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(1005,'2024-07-26','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(1006,'2024-07-27','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(1007,'2024-07-28','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(1008,'2024-07-29','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(1009,'2024-07-30','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(1010,'2024-07-31','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(1011,'2024-08-01','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(1012,'2024-08-02','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(1013,'2024-08-03','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(1014,'2024-08-04','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(1015,'2024-08-05','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(1016,'2024-08-06','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary '\0'),(1017,'2024-08-07','NOT_DELIVERED',NULL,176,'0675899f-e0be-4d29-b9b7-4b8599d311bf',NULL,'abcd, Phường Lý Thái Tổ, Quận Hoàn Kiếm',0,'8:00 - 8:30 am','0333311100','c36c5c1d-2569-4227-b304-0f06a672d8f8',_binary ''),(1018,'2024-07-10','DELAYED','DELAYED',188,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09 19:00:41','Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1019,'2024-07-11','NOT_DELIVERED',NULL,188,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1020,'2024-07-12','NOT_DELIVERED',NULL,188,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1021,'2024-07-13','NOT_DELIVERED',NULL,188,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1022,'2024-07-14','NOT_DELIVERED',NULL,188,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1023,'2024-07-15','NOT_DELIVERED',NULL,188,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1024,'2024-07-16','NOT_DELIVERED',NULL,188,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1025,'2024-07-10','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',12550000,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1026,'2024-07-11','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1027,'2024-07-12','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1028,'2024-07-13','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1029,'2024-07-14','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1030,'2024-07-15','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1031,'2024-07-16','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1032,'2024-07-17','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1033,'2024-07-18','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1034,'2024-07-19','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1035,'2024-07-20','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1036,'2024-07-21','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1037,'2024-07-22','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1038,'2024-07-23','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1039,'2024-07-24','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1040,'2024-07-25','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1041,'2024-07-26','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1042,'2024-07-27','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1043,'2024-07-28','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1044,'2024-07-29','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1045,'2024-07-30','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1046,'2024-07-31','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1047,'2024-08-01','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1048,'2024-08-02','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1049,'2024-08-03','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1050,'2024-08-04','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1051,'2024-08-05','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1052,'2024-08-06','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1053,'2024-08-07','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1054,'2024-08-08','NOT_DELIVERED',NULL,189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary ''),(1055,'2024-07-17','NOT_DELIVERED','DELAYED FROM 2024-07-10',188,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09 18:57:15','Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1056,'2024-07-18','NOT_DELIVERED','DELAYED FROM 2024-07-10',188,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09 18:59:02','Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1057,'2024-07-19','NOT_DELIVERED','DELAYED FROM 2024-07-10',188,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09 18:59:08','Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary '\0'),(1058,'2024-07-09','IN_TRANSIT',NULL,188,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09 19:17:36','Ngã Tư Sở, Phường Ngã Tư Sở, Quận Đống Đa',0,'8:00 - 8:30 am','0123456789','49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',_binary ''),(1059,'2024-07-10','NOT_DELIVERED',NULL,190,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngõ 57 Bát Khối, Phường Phúc Đồng, Quận Long Biên',443000,'8:00 - 8:30 am','0372454714','0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',_binary '\0'),(1060,'2024-07-11','NOT_DELIVERED',NULL,190,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngõ 57 Bát Khối, Phường Phúc Đồng, Quận Long Biên',0,'8:00 - 8:30 am','0372454714','0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',_binary '\0'),(1061,'2024-07-12','NOT_DELIVERED',NULL,190,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngõ 57 Bát Khối, Phường Phúc Đồng, Quận Long Biên',0,'8:00 - 8:30 am','0372454714','0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',_binary '\0'),(1062,'2024-07-13','NOT_DELIVERED',NULL,190,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngõ 57 Bát Khối, Phường Phúc Đồng, Quận Long Biên',0,'8:00 - 8:30 am','0372454714','0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',_binary '\0'),(1063,'2024-07-14','NOT_DELIVERED',NULL,190,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngõ 57 Bát Khối, Phường Phúc Đồng, Quận Long Biên',0,'8:00 - 8:30 am','0372454714','0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',_binary '\0'),(1064,'2024-07-15','NOT_DELIVERED',NULL,190,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngõ 57 Bát Khối, Phường Phúc Đồng, Quận Long Biên',0,'8:00 - 8:30 am','0372454714','0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',_binary '\0'),(1065,'2024-07-09','DELIVERED',NULL,190,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09 21:45:55','Ngõ 57 Bát Khối, Phường Phúc Đồng, Quận Long Biên',0,'8:00 - 8:30 am','0372454714','0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',_binary ''),(1066,'2024-07-10','NOT_DELIVERED',NULL,196,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngõ 57 Bát Khối, Phường Phúc Đồng, Quận Long Biên',598800,'8:00 - 8:30 am','0372454714','0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',_binary '\0'),(1067,'2024-07-11','NOT_DELIVERED',NULL,196,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngõ 57 Bát Khối, Phường Phúc Đồng, Quận Long Biên',0,'8:00 - 8:30 am','0372454714','0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',_binary '\0'),(1068,'2024-07-12','NOT_DELIVERED',NULL,196,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngõ 57 Bát Khối, Phường Phúc Đồng, Quận Long Biên',0,'8:00 - 8:30 am','0372454714','0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',_binary '\0'),(1069,'2024-07-13','NOT_DELIVERED',NULL,196,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngõ 57 Bát Khối, Phường Phúc Đồng, Quận Long Biên',0,'8:00 - 8:30 am','0372454714','0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',_binary '\0'),(1070,'2024-07-14','NOT_DELIVERED',NULL,196,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngõ 57 Bát Khối, Phường Phúc Đồng, Quận Long Biên',0,'8:00 - 8:30 am','0372454714','0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',_binary '\0'),(1071,'2024-07-15','NOT_DELIVERED',NULL,196,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngõ 57 Bát Khối, Phường Phúc Đồng, Quận Long Biên',0,'8:00 - 8:30 am','0372454714','0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',_binary '\0'),(1072,'2024-07-16','NOT_DELIVERED',NULL,196,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngõ 57 Bát Khối, Phường Phúc Đồng, Quận Long Biên',0,'8:00 - 8:30 am','0372454714','0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',_binary ''),(1073,'2024-07-10','NOT_DELIVERED',NULL,198,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngõ 57 Bát Khối, Phường Phúc Đồng, Quận Long Biên',1621700,'8:00 - 8:30 am','0372454714','0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',_binary '\0'),(1074,'2024-07-11','NOT_DELIVERED',NULL,198,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngõ 57 Bát Khối, Phường Phúc Đồng, Quận Long Biên',0,'8:00 - 8:30 am','0372454714','0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',_binary '\0'),(1075,'2024-07-12','NOT_DELIVERED',NULL,198,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngõ 57 Bát Khối, Phường Phúc Đồng, Quận Long Biên',0,'8:00 - 8:30 am','0372454714','0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',_binary '\0'),(1076,'2024-07-13','NOT_DELIVERED',NULL,198,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngõ 57 Bát Khối, Phường Phúc Đồng, Quận Long Biên',0,'8:00 - 8:30 am','0372454714','0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',_binary '\0'),(1077,'2024-07-14','NOT_DELIVERED',NULL,198,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngõ 57 Bát Khối, Phường Phúc Đồng, Quận Long Biên',0,'8:00 - 8:30 am','0372454714','0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',_binary '\0'),(1078,'2024-07-15','NOT_DELIVERED',NULL,198,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',NULL,'Ngõ 57 Bát Khối, Phường Phúc Đồng, Quận Long Biên',0,'8:00 - 8:30 am','0372454714','0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',_binary '\0'),(1079,'2024-07-16','DELIVERED',NULL,198,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09 21:48:28','Ngõ 57 Bát Khối, Phường Phúc Đồng, Quận Long Biên',0,'8:00 - 8:30 am','0372454714','0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',_binary '');
/*!40000 ALTER TABLE `delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_detail`
--

DROP TABLE IF EXISTS `delivery_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery_detail` (
  `delivery_detail_id` int NOT NULL AUTO_INCREMENT,
  `delivery_id` int DEFAULT NULL,
  `delivery_detail_quantity` int DEFAULT NULL,
  `delivery_detail_combo` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `dish_breakfast_id` int DEFAULT NULL,
  `dish_dinner_id` int DEFAULT NULL,
  `dish_lunch_id` int DEFAULT NULL,
  PRIMARY KEY (`delivery_detail_id`),
  KEY `delivery_id` (`delivery_id`),
  KEY `FKioc3cg2uft6o8xkwli23ujjgx` (`dish_breakfast_id`),
  KEY `FKhn5g7uqec1lkwdrjlrc1ad2hd` (`dish_dinner_id`),
  KEY `FKhu52aeg37bwxff67i0a8lnv6q` (`dish_lunch_id`),
  CONSTRAINT `delivery_detail_ibfk_1` FOREIGN KEY (`delivery_id`) REFERENCES `delivery` (`delivery_id`),
  CONSTRAINT `FKhn5g7uqec1lkwdrjlrc1ad2hd` FOREIGN KEY (`dish_dinner_id`) REFERENCES `dish` (`dish_id`),
  CONSTRAINT `FKhu52aeg37bwxff67i0a8lnv6q` FOREIGN KEY (`dish_lunch_id`) REFERENCES `dish` (`dish_id`),
  CONSTRAINT `FKioc3cg2uft6o8xkwli23ujjgx` FOREIGN KEY (`dish_breakfast_id`) REFERENCES `dish` (`dish_id`)
) ENGINE=InnoDB AUTO_INCREMENT=730 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_detail`
--

LOCK TABLES `delivery_detail` WRITE;
/*!40000 ALTER TABLE `delivery_detail` DISABLE KEYS */;
INSERT INTO `delivery_detail` VALUES (571,979,1,'GÓI FULL',126,4,2),(572,980,1,'GÓI FULL',6,93,90),(573,981,1,'GÓI FULL',6,100,99),(574,982,1,'GÓI FULL',96,94,6),(575,983,1,'GÓI FULL',96,93,98),(576,984,1,'GÓI FULL',97,6,97),(577,985,1,'GÓI FULL',94,92,94),(578,986,1,'GÓI FULL',4,92,89),(579,987,1,'GÓI FULL',94,94,6),(580,988,1,'GÓI FULL',126,4,2),(581,988,2,'GÓI FULL',126,4,2),(582,989,1,'GÓI FULL',6,93,90),(583,989,2,'GÓI FULL',6,93,90),(584,990,1,'GÓI FULL',6,100,99),(585,990,2,'GÓI FULL',6,100,99),(586,991,1,'GÓI FULL',96,94,6),(587,991,2,'GÓI FULL',96,94,6),(588,992,1,'GÓI FULL',96,93,98),(589,992,2,'GÓI FULL',96,93,98),(590,993,1,'GÓI FULL',97,6,97),(591,993,2,'GÓI FULL',97,6,97),(592,994,1,'GÓI FULL',94,92,94),(593,994,2,'GÓI FULL',94,92,94),(594,995,2,'GÓI FULL',4,92,89),(595,996,2,'GÓI FULL',94,94,6),(596,997,2,'GÓI FULL',99,96,97),(597,998,2,'GÓI FULL',126,100,91),(598,999,2,'GÓI FULL',100,91,91),(599,1000,2,'GÓI FULL',100,99,91),(600,1001,2,'GÓI FULL',89,99,98),(601,1002,2,'GÓI FULL',98,95,100),(602,1003,2,'GÓI FULL',95,100,126),(603,1004,2,'GÓI FULL',92,99,90),(604,1005,2,'GÓI FULL',97,95,89),(605,1006,2,'GÓI FULL',100,6,96),(606,1007,2,'GÓI FULL',91,99,99),(607,1008,2,'GÓI FULL',99,4,126),(608,1009,2,'GÓI FULL',91,95,126),(609,1010,2,'GÓI FULL',97,2,94),(610,1011,2,'GÓI FULL',95,91,97),(611,1012,2,'GÓI FULL',96,96,97),(612,1013,2,'GÓI FULL',99,94,96),(613,1014,2,'GÓI FULL',93,88,4),(614,1015,2,'GÓI FULL',126,95,95),(615,1016,2,'GÓI FULL',98,4,100),(616,1017,2,'GÓI FULL',93,99,126),(617,1018,2,'GÓI FIT1',6,NULL,90),(618,1019,2,'GÓI FIT1',6,NULL,99),(619,1020,2,'GÓI FIT1',96,NULL,6),(620,1021,2,'GÓI FIT1',96,NULL,98),(621,1022,2,'GÓI FIT1',97,NULL,97),(622,1023,2,'GÓI FIT1',94,NULL,94),(623,1024,2,'GÓI FIT1',4,NULL,89),(624,1025,1,'GÓI FULL',6,93,90),(625,1025,2,'GÓI FULL',6,93,90),(626,1025,1,'GÓI MEAT-S',NULL,93,90),(627,1025,1,'GÓI MEAT-S',NULL,93,90),(628,1026,1,'GÓI FULL',6,100,99),(629,1026,2,'GÓI FULL',6,100,99),(630,1026,1,'GÓI MEAT-S',NULL,100,99),(631,1026,1,'GÓI MEAT-S',NULL,100,99),(632,1027,1,'GÓI FULL',96,94,6),(633,1027,2,'GÓI FULL',96,94,6),(634,1027,1,'GÓI MEAT-S',NULL,94,6),(635,1027,1,'GÓI MEAT-S',NULL,94,6),(636,1028,1,'GÓI FULL',96,93,98),(637,1028,2,'GÓI FULL',96,93,98),(638,1028,1,'GÓI MEAT-S',NULL,93,98),(639,1028,1,'GÓI MEAT-S',NULL,93,98),(640,1029,1,'GÓI FULL',97,6,97),(641,1029,2,'GÓI FULL',97,6,97),(642,1029,1,'GÓI MEAT-S',NULL,6,97),(643,1029,1,'GÓI MEAT-S',NULL,6,97),(644,1030,1,'GÓI FULL',94,92,94),(645,1030,2,'GÓI FULL',94,92,94),(646,1030,1,'GÓI MEAT-S',NULL,92,94),(647,1030,1,'GÓI MEAT-S',NULL,92,94),(648,1031,1,'GÓI FULL',4,92,89),(649,1031,2,'GÓI FULL',4,92,89),(650,1031,1,'GÓI MEAT-S',NULL,92,89),(651,1031,1,'GÓI MEAT-S',NULL,92,89),(652,1032,2,'GÓI FULL',94,94,6),(653,1032,1,'GÓI MEAT-S',NULL,94,6),(654,1033,2,'GÓI FULL',99,96,97),(655,1033,1,'GÓI MEAT-S',NULL,96,97),(656,1034,2,'GÓI FULL',126,100,91),(657,1034,1,'GÓI MEAT-S',NULL,100,91),(658,1035,2,'GÓI FULL',100,91,91),(659,1035,1,'GÓI MEAT-S',NULL,91,91),(660,1036,2,'GÓI FULL',100,99,91),(661,1036,1,'GÓI MEAT-S',NULL,99,91),(662,1037,2,'GÓI FULL',89,99,98),(663,1037,1,'GÓI MEAT-S',NULL,99,98),(664,1038,2,'GÓI FULL',98,95,100),(665,1038,1,'GÓI MEAT-S',NULL,95,100),(666,1039,2,'GÓI FULL',95,100,126),(667,1039,1,'GÓI MEAT-S',NULL,100,126),(668,1040,2,'GÓI FULL',92,99,90),(669,1040,1,'GÓI MEAT-S',NULL,99,90),(670,1041,2,'GÓI FULL',97,95,89),(671,1041,1,'GÓI MEAT-S',NULL,95,89),(672,1042,2,'GÓI FULL',100,6,96),(673,1042,1,'GÓI MEAT-S',NULL,6,96),(674,1043,2,'GÓI FULL',91,99,99),(675,1043,1,'GÓI MEAT-S',NULL,99,99),(676,1044,2,'GÓI FULL',99,4,126),(677,1044,1,'GÓI MEAT-S',NULL,4,126),(678,1045,2,'GÓI FULL',91,95,126),(679,1045,1,'GÓI MEAT-S',NULL,95,126),(680,1046,2,'GÓI FULL',97,2,94),(681,1046,1,'GÓI MEAT-S',NULL,2,94),(682,1047,2,'GÓI FULL',95,91,97),(683,1047,1,'GÓI MEAT-S',NULL,91,97),(684,1048,2,'GÓI FULL',96,96,97),(685,1048,1,'GÓI MEAT-S',NULL,96,97),(686,1049,2,'GÓI FULL',99,94,96),(687,1049,1,'GÓI MEAT-S',NULL,94,96),(688,1050,2,'GÓI FULL',93,88,4),(689,1050,1,'GÓI MEAT-S',NULL,88,4),(690,1051,2,'GÓI FULL',126,95,95),(691,1051,1,'GÓI MEAT-S',NULL,95,95),(692,1052,2,'GÓI FULL',98,4,100),(693,1052,1,'GÓI MEAT-S',NULL,4,100),(694,1053,2,'GÓI FULL',93,99,126),(695,1053,1,'GÓI MEAT-S',NULL,99,126),(696,1054,2,'GÓI FULL',89,100,99),(697,1054,1,'GÓI MEAT-S',NULL,100,99),(698,1055,2,'GÓI FIT1',94,NULL,6),(699,1056,2,'GÓI FIT1',99,NULL,97),(700,1057,2,'GÓI FIT1',126,NULL,91),(701,1058,2,'GÓI FIT1',100,NULL,91),(702,1059,2,'GÓI LUNCH',NULL,NULL,90),(703,1059,1,'GÓI FULL',6,93,90),(704,1060,2,'GÓI LUNCH',NULL,NULL,99),(705,1060,1,'GÓI FULL',6,100,99),(706,1061,2,'GÓI LUNCH',NULL,NULL,6),(707,1061,1,'GÓI FULL',96,94,6),(708,1062,2,'GÓI LUNCH',NULL,NULL,98),(709,1062,1,'GÓI FULL',96,93,98),(710,1063,2,'GÓI LUNCH',NULL,NULL,97),(711,1063,1,'GÓI FULL',97,6,97),(712,1064,2,'GÓI LUNCH',NULL,NULL,94),(713,1064,1,'GÓI FULL',94,92,94),(714,1065,2,'GÓI LUNCH',NULL,NULL,89),(715,1065,1,'GÓI FULL',4,92,89),(716,1066,1,'GÓI FULL',6,93,90),(717,1067,1,'GÓI FULL',6,100,99),(718,1068,1,'GÓI FULL',96,94,6),(719,1069,1,'GÓI FULL',96,93,98),(720,1070,1,'GÓI FULL',97,6,97),(721,1071,1,'GÓI FULL',94,92,94),(722,1072,1,'GÓI FULL',4,92,89),(723,1073,5,'GÓI LUNCH',NULL,NULL,90),(724,1074,5,'GÓI LUNCH',NULL,NULL,99),(725,1075,5,'GÓI LUNCH',NULL,NULL,6),(726,1076,5,'GÓI LUNCH',NULL,NULL,98),(727,1077,5,'GÓI LUNCH',NULL,NULL,97),(728,1078,5,'GÓI LUNCH',NULL,NULL,94),(729,1079,5,'GÓI LUNCH',NULL,NULL,89);
/*!40000 ALTER TABLE `delivery_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_time`
--

DROP TABLE IF EXISTS `delivery_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery_time` (
  `delivery_time_id` int NOT NULL AUTO_INCREMENT,
  `delivery_time` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`delivery_time_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_time`
--

LOCK TABLES `delivery_time` WRITE;
/*!40000 ALTER TABLE `delivery_time` DISABLE KEYS */;
INSERT INTO `delivery_time` VALUES (1,'8:00 - 8:30 am',NULL),(2,'8:30 - 9:00 am',NULL),(3,'9:00 - 9:30 am',NULL),(4,'9:30 - 10:00 am',NULL),(5,'10:00 - 10:30 am',NULL),(6,'10:30 - 11:00 am',NULL);
/*!40000 ALTER TABLE `delivery_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dish`
--

DROP TABLE IF EXISTS `dish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dish` (
  `dish_id` int NOT NULL AUTO_INCREMENT,
  `dish_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `dish_description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `dish_image` varchar(255) DEFAULT NULL,
  `dish_proteins` int DEFAULT NULL,
  `dish_fats` int DEFAULT NULL,
  `dish_carb` int DEFAULT NULL,
  `dish_calories` int DEFAULT NULL,
  `dish_type` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`dish_id`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dish`
--

LOCK TABLES `dish` WRITE;
/*!40000 ALTER TABLE `dish` DISABLE KEYS */;
INSERT INTO `dish` VALUES (2,'MIẾN HÀN QUỐC XÀO BÒ','Stir-Fry Beef W Glass Noodles\r\n','http://res.cloudinary.com/dhwh3rgra/image/upload/v1716747229/4121a179-aa8b-4016-9919-978b5c58d39b.jpg',38,42,39,540,'N',_binary ''),(4,'NUI NẤU SƯỜN NON','SƯỜN, NUI, CÀ RỐT, CỦ CẢI, MỌC NẤM, RAU CẢI ','http://res.cloudinary.com/dhwh3rgra/image/upload/v1716901256/f0c7ab1b-db41-4bbb-893a-2491aa227997.png',40,32,25,550,'N',_binary ''),(6,'NUI RAU CỦ XÀO THỊT BẰM','Stir-fried Minced Pork W Veggie Pasta','http://res.cloudinary.com/dhwh3rgra/image/upload/v1717003705/60e3467a-d3f7-4fe2-9842-2b5e32041098.png',42,18,32,506,'N',_binary ''),(88,'CÁ SỐT KIỂU THÁI + LỨC LÚA MẠCH','Grilled Fish Thai Style W Brown Rice','http://res.cloudinary.com/dhwh3rgra/image/upload/v1717070074/819c06f3-1147-4e70-ab72-7faa96af142e.png',42,6,44,439,'N',_binary ''),(89,'GÀ XÀO LÁ QUẾ + BÚN RAU CỦ','Thai Chicken Basil with Veggie','http://res.cloudinary.com/dhwh3rgra/image/upload/v1717070145/f8d9022e-43d7-4d31-871b-26480b386abb.png',52,17,49,598,'N',_binary ''),(90,'SALAD BÒ NƯỚNG SỐT BALSAMIC','Beef Salad W Balsamic Dijon','http://res.cloudinary.com/dhwh3rgra/image/upload/v1717070214/6f4c74f5-864f-42a1-bc40-75bdf3883481.png',34,21,32,462,'N',_binary ''),(91,'CÁ SỐT TẮC HỦ TIẾU LỨC','Kumquat fish with brown noodles','http://res.cloudinary.com/dhwh3rgra/image/upload/v1717070272/30227df2-252e-4587-bfff-5eca3ece6fcf.png',40,15,28,415,'N',_binary ''),(92,'BÚN CÁ LÓC','Vietnamese fish noodle soup','http://res.cloudinary.com/dhwh3rgra/image/upload/v1717070312/3f71c745-6c9e-4abe-a0c6-f9a48819091b.png',40,15,36,450,'N',_binary ''),(93,'GÀ SỐT ME + GẠO LỨC','Chicken Tamarind W Brown Rice','http://res.cloudinary.com/dhwh3rgra/image/upload/v1717070352/61553542-842c-4c1a-b8ae-2177bec401f4.png',40,19,33,513,'N',_binary ''),(94,'BÒ GOCHUJANG BÚN CHÙM NGÂY','Beef gochujang with noodles','http://res.cloudinary.com/dhwh3rgra/image/upload/v1717070381/8282240a-3fe2-49e5-ad00-8a1ff00ca5de.png',37,25,29,505,'N',_binary ''),(95,'SALAD GÀ KALE SỐT CHANH DÂY','Chicken kale salad with passion fruit dressing','http://res.cloudinary.com/dhwh3rgra/image/upload/v1717070445/c0279cb8-20c2-4536-a413-105b168c1036.png',28,13,39,390,'N',_binary ''),(96,'HEO BÍ ĐỎ + COUS COUS','Pumpkin Pork with Cous cous','http://res.cloudinary.com/dhwh3rgra/image/upload/v1717070478/f1b1fc4a-8341-4f5f-a649-0d228df89c66.png',46,22,37,578,'N',_binary ''),(97,'TÔM HOISIN + CƠM GẠO LỨC','Shrimp Hoisin sauce served with brown rice','http://res.cloudinary.com/dhwh3rgra/image/upload/v1717070507/2dd9b5a4-9245-4d01-900b-5558ec477e40.png',30,15,40,420,'N',_binary ''),(98,'HEO VIÊN BUFFALO + RAU CỦ','Bufalo Meatball W Veggie','http://res.cloudinary.com/dhwh3rgra/image/upload/v1717070536/07985439-919f-4715-9064-e96a807e4c69.png',32,17,31,480,'N',_binary ''),(99,'HẢI SẢN HẤP THÁI + BÚN RAU CỦ','Thai Steamed Seafood + Veggie Vermicelli','http://res.cloudinary.com/dhwh3rgra/image/upload/v1717070562/37709e51-ba04-47a5-be69-9f12566b02fb.png',42,20,25,482,'N',_binary ''),(100,'GÀ TERYAKI KIỂU BENTO','Teriyaki chicken bento style','http://res.cloudinary.com/dhwh3rgra/image/upload/v1717070586/17e7662d-4043-4224-b377-0c3bac2630f2.png',35,30,33,542,'N',_binary ''),(101,'Bún gạo lứt xào ','bún, nấm, giá, rau','http://res.cloudinary.com/dhwh3rgra/image/upload/v1718685000/b9c97cdc-96dc-418b-9870-fdf6ec61c218.png',12,12,23,180,'V',_binary ''),(102,'Nấm nướng xá xíu','nấm, cà chua, đậu, cơm trắng','http://res.cloudinary.com/dhwh3rgra/image/upload/v1718685113/5c7a29f9-b9f9-494c-ad7d-939b64cc39cf.png',12,13,23,310,'V',_binary ''),(103,'Đậu hũ sốt mắm','đậu hũ, nấm, mướp, cơm trắng','http://res.cloudinary.com/dhwh3rgra/image/upload/v1718685432/deaafade-1963-4cb5-85b4-4c44972233bd.png',20,10,30,180,'V',_binary ''),(104,'Gỏi nấm diếp cá','Trứng, ớt, nấm, cơm ','http://res.cloudinary.com/dhwh3rgra/image/upload/v1718685499/eb2bec24-2ce9-4fd6-920b-80fcc1af7906.png',15,20,30,330,'V',_binary ''),(105,'Salad dưa leo tương mè','rau, đậu hũ, cơm trắng','http://res.cloudinary.com/dhwh3rgra/image/upload/v1718685560/f4848e9c-c2a4-40af-8d50-01eafdb3d851.png',30,20,30,365,'V',_binary ''),(106,'Bắp nấm xào bơ tỏi','bắp, nấm, cà chua, xà lách','http://res.cloudinary.com/dhwh3rgra/image/upload/v1718685625/9148d813-213f-4c24-b839-da2a321e68a8.png',40,20,30,393,'V',_binary ''),(107,'Đậu hũ rong biển ','đậu, rong biển, cà rốt, đậu bắp','http://res.cloudinary.com/dhwh3rgra/image/upload/v1718685680/b4aabaf2-adc8-4d21-834f-950a54dcafde.png',16,29,30,384,'V',_binary ''),(108,'Đậu hũ kho nấm rơm','đậu, nấm, dưa leo, cơm','http://res.cloudinary.com/dhwh3rgra/image/upload/v1718685762/3cf61dd1-33dc-4f02-a44d-1a9ee4526be0.png',30,20,30,300,'V',_binary ''),(109,'Nấm đùi gà áp chảo','nấm, kim chi, probi, cơm chiên','http://res.cloudinary.com/dhwh3rgra/image/upload/v1718685861/2ed70886-2ad6-47df-b6fc-dab5457508bd.png',30,20,40,400,'V',_binary ''),(111,'Khổ qua xào trứng ','khổ qua, trứng, nấm, cơm trắng','http://res.cloudinary.com/dhwh3rgra/image/upload/v1718686174/155ab264-d37f-4732-b03a-0f5665c2573b.png',40,20,30,410,'V',_binary ''),(115,'Ớt chuông mix nấm ','ớt chuống , nấm , đậu, cơm','http://res.cloudinary.com/dhwh3rgra/image/upload/v1718686348/83b15120-0a55-48a0-98a4-87faddbb557b.png',30,20,30,380,'V',_binary ''),(116,'Mì trứng chả giò','mì, chả giò, dưa, xà lách','http://res.cloudinary.com/dhwh3rgra/image/upload/v1718686425/ab17be02-a3bc-4be5-8a74-4bdaa300bb18.png',30,10,30,310,'V',_binary ''),(117,'Trứng chiên cà chua','trứng, cà chua, xà lách, cơm ','http://res.cloudinary.com/dhwh3rgra/image/upload/v1718686481/1debd777-7685-43c5-bd44-2deb201e390f.png',20,12,30,289,'V',_binary ''),(118,'Nấm áp chảo bơ tỏi','nấm, trứng, cà chua, salad','http://res.cloudinary.com/dhwh3rgra/image/upload/v1718686527/f0c95620-9ca3-45cd-bf38-e51090794f63.png',30,24,30,370,'V',_binary ''),(119,'Trứng rán bí ngòi','trứng, bí, càchua, giá, cơm trắng','http://res.cloudinary.com/dhwh3rgra/image/upload/v1718686592/e5fb0c87-d421-46c0-be41-a2c77d24c40c.png',23,15,30,350,'V',_binary ''),(120,'Đậu hũ kho thơm ','đậu, thơm, cơm, mướp','http://res.cloudinary.com/dhwh3rgra/image/upload/v1718686650/c8e0d28d-a8f3-4e6b-a77a-f4ff72ba04fd.png',20,17,30,340,'V',_binary '\0'),(126,'MÌ Ý CÁ NGỪ SỐT RAU CỦ','Tuna Spaghetti W Veggie Sauce','http://res.cloudinary.com/dhwh3rgra/image/upload/v1718708277/f1559af7-bc56-41f0-855f-e89eb367b4bd.png',31,19,18,397,'N',_binary ''),(127,'Đậu chả cuốn bún ','rau bún chả đậu','http://res.cloudinary.com/dhwh3rgra/image/upload/v1718759635/3c6dfa1d-f2ca-4009-963b-eae7c193f07f.png',23,45,20,324,'V',_binary ''),(128,'Bún nưa gà xé','Bún nưa gà xé','http://res.cloudinary.com/dhwh3rgra/image/upload/v1718760526/c8dabca8-668f-4dd1-95d1-2a3add1c6cf4.png',12,32,20,320,'V',_binary ''),(129,'Bún nưa gà xé','Bún nưa gà xé','http://res.cloudinary.com/dhwh3rgra/image/upload/v1718760510/e74f27e3-3a6f-4b72-bf03-a55afe9d462f.png',12,32,20,320,'V',_binary '');
/*!40000 ALTER TABLE `dish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dish_ids`
--

DROP TABLE IF EXISTS `dish_ids`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dish_ids` (
  `dish_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dish_ids`
--

LOCK TABLES `dish_ids` WRITE;
/*!40000 ALTER TABLE `dish_ids` DISABLE KEYS */;
/*!40000 ALTER TABLE `dish_ids` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expense`
--

DROP TABLE IF EXISTS `expense`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expense` (
  `expense_id` int NOT NULL AUTO_INCREMENT,
  `ingredient_name` varchar(255) NOT NULL,
  `quantity` int NOT NULL,
  `unit_price` int NOT NULL,
  `supplier` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `purchase_date` date DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`expense_id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense`
--

LOCK TABLES `expense` WRITE;
/*!40000 ALTER TABLE `expense` DISABLE KEYS */;
INSERT INTO `expense` VALUES (1,'Cà rốt',10,13000,'Vegan','2024-07-09','2024-06-28',_binary ''),(2,'Bò kobe siêu ngon',11,200000,'WINMART','2024-06-26','2024-06-28',_binary ''),(3,'Rau xà lách',5,15000,'Sendo','2024-07-07','2024-07-09',_binary ''),(4,'Gạo ST25',50,23000,'Gạo Kim Ngân','2024-07-07','2024-11-19',_binary ''),(5,'Cá hồi',20,500000,'CoCo Mart','2024-07-07','2024-07-30',_binary ''),(6,'Cá chép ',13,70000,'CoCo Mart','2024-07-07','2024-07-30',_binary ''),(7,'Dưa chuột',14,10000,'CoCo Mart','2024-07-08','2024-07-11',_binary ''),(8,'Cá hồi',20,500000,'CoCo Mart','2024-07-07','2024-07-30',_binary ''),(9,'Cá kìm',20,175000,'DeliMeat','2024-07-07','2024-07-30',_binary ''),(10,'Cá hồi',20,500000,'CoCo Mart','2024-07-07','2024-07-30',_binary ''),(11,'Rau cải ',15,15000,'CoCo Mart','2024-07-07','2024-07-30',_binary ''),(12,'Cá hồi',2123,500000,'CoCo Mart','2024-07-11','2024-07-30',_binary ''),(13,'Nấm bào ngư',32,136000,'CoCo Mart','2024-07-07','2024-07-30',_binary ''),(14,'Cá hồi',20,500000,'CoCo Mart','2024-07-12','2024-07-30',_binary ''),(15,'Cá hồi',20,500000,'CoCo Mart','2024-07-07','2024-07-30',_binary ''),(16,'Cá hồi',20,500000,'CoCo Mart','2024-07-07','2024-07-30',_binary ''),(17,'Cá hồi',20,500000,'CoCo Mart','2024-07-07','2024-07-30',_binary ''),(18,'Cá hồi',20,500000,'CoCo Mart','2024-07-07','2024-07-30',_binary ''),(19,'Cá hồi',20,500000,'CoCo Mart','2024-07-07','2024-07-30',_binary ''),(20,'Cá hồi',20,500000,'CoCo Mart','2024-07-07','2024-07-30',_binary ''),(21,'Cá hồi',20,500000,'CoCo Mart','2024-07-07','2024-07-30',_binary ''),(22,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(23,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(24,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(25,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(26,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(27,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(28,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(29,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(30,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(31,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(32,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(33,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(34,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(35,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(36,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(37,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(38,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(39,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(40,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(41,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(42,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(43,'Cá hồi',1,490000,'CoCo Mart','2024-07-11','2024-07-27',_binary ''),(44,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(45,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(46,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(47,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(48,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(49,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(50,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(51,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(52,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(53,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(54,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(55,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(56,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(57,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(58,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(59,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(60,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(61,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(62,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(63,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(64,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(65,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(66,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(67,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(68,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(69,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(70,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(71,'Cá hồi',1,490000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(72,'Cá hồi',1,153544635,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(73,'Cá hồi',1,153544635,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(74,'Cá hồi',1,200000,'CoCo Mart','2024-07-07','2024-07-07',_binary ''),(75,'Dương sằng wa',1,2000000,'Thanh Oai corporation','2024-07-07','2024-07-07',_binary '');
/*!40000 ALTER TABLE `expense` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forget_token`
--

DROP TABLE IF EXISTS `forget_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forget_token` (
  `forget_token_id` int NOT NULL AUTO_INCREMENT,
  `forget_token` varchar(255) NOT NULL,
  `forget_token_created` datetime(6) NOT NULL,
  `forget_token_expired` datetime(6) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`forget_token_id`),
  KEY `FKbtyxtekkjkbp3e33xay2al57` (`user_id`),
  CONSTRAINT `FKbtyxtekkjkbp3e33xay2al57` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forget_token`
--

LOCK TABLES `forget_token` WRITE;
/*!40000 ALTER TABLE `forget_token` DISABLE KEYS */;
INSERT INTO `forget_token` VALUES (10,'58acabc0-998a-4178-8a81-481c2a3d6716','2024-06-02 21:32:39.684669','2024-06-02 22:32:39.685658','8140eed3-ac3e-42e5-a709-1413210f0754'),(16,'53701c07-1410-41af-b2e5-f326d057da00','2024-06-11 11:21:31.291583','2024-06-11 12:21:31.291583','86939090-6934-42f7-8efa-e3bee331aac1');
/*!40000 ALTER TABLE `forget_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_basket`
--

DROP TABLE IF EXISTS `order_basket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_basket` (
  `order_basket_id` int NOT NULL AUTO_INCREMENT,
  `combo_id` int DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `order_basket_quantity` int DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `order_basket_day` int DEFAULT NULL,
  PRIMARY KEY (`order_basket_id`),
  KEY `user_id` (`user_id`),
  KEY `combo_id` (`combo_id`),
  CONSTRAINT `order_basket_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `order_basket_ibfk_2` FOREIGN KEY (`combo_id`) REFERENCES `combo` (`combo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=316 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_basket`
--

LOCK TABLES `order_basket` WRITE;
/*!40000 ALTER TABLE `order_basket` DISABLE KEYS */;
INSERT INTO `order_basket` VALUES (241,4,'0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',1,NULL,7),(282,6,'2a8d6e1f-7a2e-4f8a-a20a-85b70dccbe82',1,NULL,7),(315,6,'86939090-6934-42f7-8efa-e3bee331aac1',1,NULL,7);
/*!40000 ALTER TABLE `order_basket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `order_detail_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int DEFAULT NULL,
  `order_detail_quantity` int DEFAULT NULL,
  `order_detail_price` int DEFAULT NULL,
  `combo_id` int DEFAULT NULL,
  `combo_day` int DEFAULT NULL,
  PRIMARY KEY (`order_detail_id`),
  KEY `combo_id` (`combo_id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`combo_id`) REFERENCES `combo` (`combo_id`),
  CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=237 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (200,175,1,350000,4,7),(201,176,1,825000,6,7),(202,176,2,5940000,6,30),(203,177,1,825000,6,7),(204,178,1,825000,6,7),(205,179,2,1650000,6,7),(206,179,1,825000,6,7),(207,179,1,2970000,6,30),(208,180,1,2970000,6,30),(209,186,1,825000,6,7),(210,186,2,5940000,6,30),(211,187,1,350000,4,7),(212,187,1,1260000,4,30),(213,188,2,1300000,7,7),(214,189,1,825000,6,7),(215,189,2,5940000,6,30),(216,189,1,1225000,13,7),(217,189,1,4410000,13,30),(218,190,2,700000,4,7),(219,190,1,825000,6,7),(220,191,1,825000,6,7),(221,193,3,8910000,6,30),(222,194,1,650000,9,7),(223,195,1,650000,7,7),(224,196,1,825000,6,7),(225,197,1,825000,6,7),(226,198,5,1750000,4,7),(227,199,5,3250000,7,7),(228,200,1,350000,4,7),(229,201,1,350000,4,7),(230,202,1,825000,6,7),(231,203,1,350000,4,7),(232,204,1,650000,9,7),(233,205,1,825000,6,7),(234,205,1,2160000,10,30),(235,206,1,825000,6,7),(236,207,1,650000,7,7);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `order_temp_price` int DEFAULT NULL,
  `order_delivery_price` int DEFAULT NULL,
  `order_discount` int DEFAULT NULL,
  `order_total_price` int DEFAULT NULL,
  `order_status` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `order_note` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `address_id` int DEFAULT NULL,
  `payment_method_id` int DEFAULT NULL,
  `delivery_time_id` int DEFAULT NULL,
  `point` int DEFAULT NULL,
  `delay` int DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `payment_method_id` (`payment_method_id`),
  KEY `user_id` (`user_id`),
  KEY `address_id` (`address_id`),
  KEY `FKddgkbxe5mxhv7uaayspn5rey9` (`delivery_time_id`),
  CONSTRAINT `FKddgkbxe5mxhv7uaayspn5rey9` FOREIGN KEY (`delivery_time_id`) REFERENCES `delivery_time` (`delivery_time_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_method` (`payment_method_id`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (175,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','2024-07-08',350000,35000,0,385000,'SHIPPED','',33,1,1,0,1),(176,'0675899f-e0be-4d29-b9b7-4b8599d311bf','2024-07-08',6765000,150000,200000,6715000,'SHIPPED','',27,1,1,200,3),(177,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','2024-07-08',825000,35000,0,860000,'SHIPPED','',33,1,1,0,1),(178,'86939090-6934-42f7-8efa-e3bee331aac1','2024-07-08',825000,35000,0,860000,'PROCESSING','',66,1,1,0,1),(179,'ea7ab4c0-26ec-41f9-b3a6-7a75a1fd06c2','2024-07-08',5445000,150000,0,5595000,'PENDING','',67,2,3,NULL,NULL),(180,'ea7ab4c0-26ec-41f9-b3a6-7a75a1fd06c2','2024-07-08',5445000,150000,0,5595000,'PENDING','',67,2,3,NULL,NULL),(181,'ea7ab4c0-26ec-41f9-b3a6-7a75a1fd06c2','2024-07-08',5445000,150000,0,5595000,'PROCESSING','',67,1,3,0,1),(182,'ea7ab4c0-26ec-41f9-b3a6-7a75a1fd06c2','2024-07-08',5445000,150000,0,5595000,'PROCESSING','',67,1,3,0,1),(183,'ea7ab4c0-26ec-41f9-b3a6-7a75a1fd06c2','2024-07-08',5445000,150000,0,5595000,'PROCESSING','',67,1,3,0,1),(184,'ea7ab4c0-26ec-41f9-b3a6-7a75a1fd06c2','2024-07-08',5445000,150000,0,5595000,'PROCESSING','',67,1,3,0,1),(185,'ea7ab4c0-26ec-41f9-b3a6-7a75a1fd06c2','2024-07-08',5445000,150000,0,5595000,'PROCESSING','',67,1,3,0,1),(186,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','2024-07-09',6765000,150000,335000,6580000,'CANCELLED','',17,1,1,335,3),(187,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09',1610000,150000,20000,1740000,'CANCELLED','',23,1,1,20,3),(188,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09',1300000,35000,20000,1315000,'SHIPPED','',23,1,1,20,1),(189,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09',12400000,150000,0,12550000,'SHIPPED','',23,1,1,0,3),(190,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09',1525000,35000,1117000,443000,'SHIPPED','',26,1,1,1117,1),(191,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09',825000,35000,0,860000,'CANCELLED','',23,1,1,0,1),(192,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09',825000,35000,627000,233000,'CANCELLED','',23,1,1,627,1),(193,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09',8910000,150000,2732000,6328000,'CANCELLED','',26,1,1,2732,3),(194,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09',650000,35000,273200,411800,'CANCELLED','',26,1,1,273,1),(195,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09',650000,35000,290200,394800,'CANCELLED','',26,1,1,290,1),(196,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09',825000,35000,261200,598800,'SHIPPED','',26,1,1,2612,1),(197,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09',825000,35000,119000,741000,'CANCELLED','',26,1,1,1190,1),(198,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09',1750000,35000,163300,1621700,'SHIPPED','',26,1,1,1633,1),(199,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09',3250000,35000,162100,3122900,'CANCELLED','',26,1,1,1621,1),(200,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09',350000,35000,0,385000,'CANCELLED','',26,2,1,NULL,NULL),(201,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09',350000,35000,294000,91000,'CANCELLED','',26,1,1,2940,1),(202,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09',825000,35000,627000,233000,'CANCELLED','',26,1,1,6270,1),(203,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09',350000,35000,294000,91000,'PROCESSING','',26,1,1,2940,1),(204,'0675899f-e0be-4d29-b9b7-4b8599d311bf','2024-07-09',650000,35000,0,685000,'PROCESSING','',27,1,1,0,1),(205,'0675899f-e0be-4d29-b9b7-4b8599d311bf','2024-07-09',2985000,150000,0,3135000,'PROCESSING','',27,1,1,0,3),(206,'0675899f-e0be-4d29-b9b7-4b8599d311bf','2024-07-09',825000,35000,0,860000,'PENDING','',27,2,1,NULL,NULL),(207,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','2024-07-09',650000,35000,33500,651500,'PROCESSING','',17,1,1,335,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_method` (
  `payment_method_id` int NOT NULL AUTO_INCREMENT,
  `payment_method_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `payment_method_description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`payment_method_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
INSERT INTO `payment_method` VALUES (1,'Thanh toán bằng tiền mặt','COD',_binary ''),(2,'Thanh toán bằng thẻ','ATM',_binary '');
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `point_history`
--

DROP TABLE IF EXISTS `point_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `point_history` (
  `point_history_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  `point_history_date` date NOT NULL,
  `point_history_description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `point_history_point` int NOT NULL,
  `point_history_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`point_history_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `point_history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `point_history`
--

LOCK TABLES `point_history` WRITE;
/*!40000 ALTER TABLE `point_history` DISABLE KEYS */;
INSERT INTO `point_history` VALUES (4,'0675899f-e0be-4d29-b9b7-4b8599d311bf','2024-07-07','Đặt hàng đơn hàng #165',20,'MINUS'),(6,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','2024-07-07','Đặt hàng đơn hàng #166',20,'MINUS'),(7,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','2024-07-07','Hoàn xu đơn hàng #166',20,'BONUS'),(8,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','2024-07-07','Đặt hàng đơn hàng #167',20,'MINUS'),(9,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','2024-07-07','Hoàn xu đơn hàng #167',20,'BONUS'),(10,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','2024-07-07','Đặt hàng đơn hàng #168',20,'MINUS'),(11,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','2024-07-07','Hoàn thành đơn hàng #168',350,'BONUS'),(12,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','2024-07-07','Đặt hàng đơn hàng #169',350,'MINUS'),(13,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','2024-07-07','Hoàn thành đơn hàng #169',335,'BONUS'),(77,'0675899f-e0be-4d29-b9b7-4b8599d311bf','2024-07-08','Đặt hàng đơn hàng #173',200,'MINUS'),(78,'0675899f-e0be-4d29-b9b7-4b8599d311bf','2024-07-08','Hoàn điểm đơn hàng #173',200,'BONUS'),(79,'0675899f-e0be-4d29-b9b7-4b8599d311bf','2024-07-08','Hoàn điểm đơn hàng #174',0,'BONUS'),(80,'0675899f-e0be-4d29-b9b7-4b8599d311bf','2024-07-08','Đặt hàng đơn hàng #176',200,'MINUS'),(81,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','2024-07-09','Đặt hàng đơn hàng #186',335,'MINUS'),(82,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Đặt hàng đơn hàng #187',20,'MINUS'),(83,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Hoàn điểm đơn hàng #187',20,'BONUS'),(84,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Đặt hàng đơn hàng #188',20,'MINUS'),(85,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Hoàn thành đơn hàng #188',1315,'BONUS'),(86,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Hoàn thành đơn hàng #188',1315,'BONUS'),(87,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Hoàn thành đơn hàng #188',1315,'BONUS'),(88,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Đặt hàng đơn hàng #190',1117,'MINUS'),(89,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Hoàn thành đơn hàng #190',443,'BONUS'),(90,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Hoàn thành đơn hàng #190',44,'BONUS'),(91,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Hoàn thành đơn hàng #190',44,'BONUS'),(92,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Đặt hàng đơn hàng #192',627,'MINUS'),(93,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Đặt hàng đơn hàng #193',2732,'MINUS'),(94,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Hoàn điểm đơn hàng #193',2732,'BONUS'),(95,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Đặt hàng đơn hàng #194',273,'MINUS'),(96,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Hoàn thành đơn hàng #190',443,'BONUS'),(97,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Đặt hàng đơn hàng #195',2902,'MINUS'),(98,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Đặt hàng đơn hàng #196',2612,'MINUS'),(99,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Hoàn điểm đơn hàng #195',290,'BONUS'),(100,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Hoàn điểm đơn hàng #194',273,'BONUS'),(101,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Hoàn điểm đơn hàng #192',627,'BONUS'),(102,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Đặt hàng đơn hàng #197',1190,'MINUS'),(103,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Hoàn điểm đơn hàng #197',1190,'BONUS'),(104,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Hoàn thành đơn hàng #190',443,'BONUS'),(105,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Đặt hàng đơn hàng #198',1633,'MINUS'),(106,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Hoàn thành đơn hàng #198',1621,'BONUS'),(107,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Đặt hàng đơn hàng #199',1621,'MINUS'),(108,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Hoàn điểm đơn hàng #199',1621,'BONUS'),(109,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Đặt hàng đơn hàng #201',2940,'MINUS'),(110,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Hoàn điểm đơn hàng #201',2940,'BONUS'),(111,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Đặt hàng đơn hàng #202',6270,'MINUS'),(112,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Hoàn điểm đơn hàng #202',6270,'BONUS'),(113,'71aba0d4-ce02-47b1-8f39-abab5587f6ce','2024-07-09','Đặt hàng đơn hàng #203',2940,'MINUS'),(114,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','2024-07-09','Hoàn điểm đơn hàng #186',335,'BONUS'),(115,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','2024-07-09','Đặt hàng đơn hàng #207',335,'MINUS');
/*!40000 ALTER TABLE `point_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotion` (
  `promotion_id` int NOT NULL AUTO_INCREMENT,
  `promotion_code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `promotion_quantity` int DEFAULT NULL,
  `promotion_status` bit(1) DEFAULT NULL,
  `promotion_description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `promotion_discount` int DEFAULT NULL,
  PRIMARY KEY (`promotion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
INSERT INTO `promotion` VALUES (1,'NM2024',100,_binary '\0','Discount 50% ',50),(2,'NM99',111,_binary '','Discount 12%',12),(3,'VC1',1,_binary '','Discount 50%',50),(4,'GIAKHANHDZ',98,_binary '\0','DEP ZAI PRO VIP',14),(6,'NM2027',29,_binary '','Discount 15%',12),(7,'NM2026',13,_binary '','Discount 13%',13),(8,'TuanDu',7,_binary '\0','20%',20),(9,'NM27',30,_binary '\0','Discount 27%',276),(14,'Beoo6677667',30,_binary '','ok nha',66),(15,'GIAKHANHPRO9x',70,_binary '\0','TAO DEP ZAI NHAT THE GIOI',40),(16,'BEOO',3,_binary '\0','ok',1000),(17,'tuandu',2,_binary '\0','20%',233435),(18,'lysan',2,_binary '\0','20%',-283),(19,'Giakhanh',123,_binary '','Siêu khuyến mãi 50%',50),(20,'tuanduvip',20,_binary '\0','20%',102),(21,'tuandudeptrai',23,_binary '\0','20%',101),(22,'NMngonn',20,_binary '','Discount 6%',6),(23,'tuandudeptrai',20,_binary '','Discount 15%',15),(24,'a',123,_binary '\0','123',333),(25,'123',123,_binary '\0','123',123),(26,'312',312,_binary '\0','312',0),(27,'3',3,_binary '\0','3',0),(28,'2',2,_binary '\0','2',0),(29,'tuanduvip1',23,_binary '\0','Discount 15%',123),(30,'okooo',25,_binary '\0','Discount 15%',15),(31,'tuanduSea',24,_binary '','20%',20),(32,'beobot',9,_binary '','Discount 50%',50),(33,'NMomg',5,_binary '','Discount 70%',70),(34,'tuandu26',25,_binary '\0','20%',20);
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` enum('ROLE_ADMIN','ROLE_CUSTOMER','ROLE_MANAGER','ROLE_SHIPPER') DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_MANAGER'),(3,'ROLE_CUSTOMER'),(4,'ROLE_SHIPPER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_promotion`
--

DROP TABLE IF EXISTS `user_promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_promotion` (
  `user_promotion_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `promotion_id` int DEFAULT NULL,
  PRIMARY KEY (`user_promotion_id`),
  KEY `user_id` (`user_id`),
  KEY `promotion_id` (`promotion_id`),
  CONSTRAINT `user_promotion_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `user_promotion_ibfk_2` FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`promotion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_promotion`
--

LOCK TABLES `user_promotion` WRITE;
/*!40000 ALTER TABLE `user_promotion` DISABLE KEYS */;
INSERT INTO `user_promotion` VALUES (1,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc',1),(2,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc',2),(3,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc',3),(4,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc',3),(5,'0675899f-e0be-4d29-b9b7-4b8599d311bf',3),(6,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',4),(7,'bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc',4),(8,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',1),(9,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',9),(10,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',3),(11,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',6),(12,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',32),(13,'71aba0d4-ce02-47b1-8f39-abab5587f6ce',31);
/*!40000 ALTER TABLE `user_promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` varchar(255) NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES ('8140eed3-ac3e-42e5-a709-1413210f0754',1),('7be61bab-83a3-4ab7-a566-f66ab9ec466a',2),('0675899f-e0be-4d29-b9b7-4b8599d311bf',3),('0923f64d-73ae-4e85-911d-93394c539504',3),('2a8d6e1f-7a2e-4f8a-a20a-85b70dccbe82',3),('32d48683-0e40-455e-b4d1-706c7acbbb57',3),('4043757a-3df8-40af-a056-26efb28b5c40',3),('4e7f27b0-4695-4ebb-994b-7bd93913279d',3),('4ff044cd-ffd1-4c60-ae8f-fa89722ed206',3),('68c156d0-c73e-4d18-b47f-8ccd08f97c97',3),('71aba0d4-ce02-47b1-8f39-abab5587f6ce',3),('86939090-6934-42f7-8efa-e3bee331aac1',3),('8f44162c-0b63-46bb-8ce6-1f7432f81d15',3),('a278196e-6053-4027-ad8f-18b2dd5d88da',3),('bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc',3),('d4eb6b07-f46d-4627-885b-80cf8002e9c4',3),('dbb553f5-6c9a-4e78-bcb2-68094e6b2607',3),('e892e81c-91b5-4862-b86e-d5daccd8b6b9',3),('ea7ab4c0-26ec-41f9-b3a6-7a75a1fd06c2',3),('0a6459d4-ac39-49dc-9b2b-e0ead9edefcf',4),('49b95143-9ba2-4b33-8a30-a5ad13ba5a7b',4),('aabaf07e-f1c0-4135-9ea7-7ac0a35ed0f7',4),('c36c5c1d-2569-4227-b304-0f06a672d8f8',4);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `point` int DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('0675899f-e0be-4d29-b9b7-4b8599d311bf','duongdthe170945@fpt.edu.vn','$2a$10$ihMK6wB4rOmzSqf.Vcvq.eOakz9eUhotnVZfj4vhlkLcTu9G/U/7i','Thùy Duong','0333311102',_binary '\0','2003-09-17',0,_binary '','thuyduong','http://res.cloudinary.com/dhwh3rgra/image/upload/v1717037566/3502f194-e921-433b-8597-544edd800f42.png'),('0923f64d-73ae-4e85-911d-93394c539504','tungvipns123@gmail.com',NULL,'Thanh Tùng','0355498556',NULL,NULL,20,_binary '',NULL,'http://res.cloudinary.com/dhwh3rgra/image/upload/v1717684913/c5946e2c-ed84-407a-80ac-ac96bc96c662.jpg'),('0a6459d4-ac39-49dc-9b2b-e0ead9edefcf','shipper1@gmail.com','$2a$10$73xvGmm7t0FpW/9qfb6vfOb0V8vMy0GKE54qqrHd.cX1Jxq1n8XNe','Shipper Long Biên',NULL,NULL,NULL,20,_binary '','shipper1','https://static.chotot.com/storage/chotot-kinhnghiem/c2c/2021/12/b3d01a73-lam-shipper-cho-cong-ty-nao-tot-nhat-thumb.jpeg'),('2a8d6e1f-7a2e-4f8a-a20a-85b70dccbe82','tuanduvip1@gmail.com','$2a$10$Jh/SQzpsXQLHqNDzhOq4lOaIAxa0xy0iKD6pHX6xSurvmnH1KGEIO','Dự Dương Tuấn','0981581000',_binary '','2024-06-15',20,_binary '','Beoo','http://res.cloudinary.com/dhwh3rgra/image/upload/v1720195267/d199c857-c594-44b0-9235-849ca8f2d59b.jpg'),('32d48683-0e40-455e-b4d1-706c7acbbb57','testswt@gmail.com','$2a$10$TeI545SsV2Y4hwyr1p8rYujMLxFnCKDU6JCn7UhJ3O7AgsMJrVLKa','Tung Nguyen Thanh','0384370666',_binary '\0',NULL,20,_binary '','testswt','https://img.freepik.com/premium-vector/avatar-icon0002_750950-43.jpg?size=338&ext=jpg&ga=GA1.1.1224184972.1715212800&semt=ais'),('4043757a-3df8-40af-a056-26efb28b5c40','khanhvghe170815@fpt.edu.vn',NULL,'Vũ Gia Khánh','0372454714',NULL,NULL,20,_binary '',NULL,'https://lh3.googleusercontent.com/a/ACg8ocL0-OUAB4r7b1CDraoWg1Td12wIX-EmkT4o92Uk2E4tpwmFtQ=s96-c'),('49b95143-9ba2-4b33-8a30-a5ad13ba5a7b','shipper4@gmail.com','$2a$10$JraPEhoelyDxOUtN4nIXU.R56CzxoKD/iY7.v6qtsgUIkIjktYJ5G','Shipper Đống Đa',NULL,NULL,NULL,20,_binary '','shipper4','https://static.chotot.com/storage/chotot-kinhnghiem/c2c/2021/12/b3d01a73-lam-shipper-cho-cong-ty-nao-tot-nhat-thumb.jpeg'),('4e7f27b0-4695-4ebb-994b-7bd93913279d','dothuytrang17092003@gmail.com','$2a$10$glaca4VG0LCQxylOUiAR9.iOfDMzoL5wPOuzKhHid87Ar2.aXudXy','Duong Do',NULL,NULL,NULL,20,_binary '','thuyduong123','https://img.freepik.com/premium-vector/avatar-icon0002_750950-43.jpg?size=338&ext=jpg&ga=GA1.1.1224184972.1715212800&semt=ais'),('4ff044cd-ffd1-4c60-ae8f-fa89722ed206','nutrimeal@gmail.com','$2a$10$bhQ0SwPpgalBhzQwvnbi0Oa/wJt26YilK/.yW7GNFDFjbVBc6uB/S','nutrimeal',NULL,NULL,NULL,20,_binary '','nutrimeal','https://img.freepik.com/premium-vector/avatar-icon0002_750950-43.jpg?size=338&ext=jpg&ga=GA1.1.1224184972.1715212800&semt=ais'),('68c156d0-c73e-4d18-b47f-8ccd08f97c97','haidang02032003@gmail.com',NULL,'Đăng Phương Hải',NULL,NULL,NULL,0,_binary '',NULL,'https://lh3.googleusercontent.com/a/ACg8ocL8t5DHK6SNujaa4COLieaeOM-0UWDANnNgEW_PMcfWYBtRUA4=s96-c'),('71aba0d4-ce02-47b1-8f39-abab5587f6ce','giakhanh27403@gmail.com','$2a$10$oKo1LRfomkdCXzPEPzhr/.BpPhGA.Vduoj3LyTcpy88VCRkoOi7RC','Vũ Gia Khánh','0372454714',_binary '','1924-04-27',159160,_binary '','giakhanh','http://res.cloudinary.com/dhwh3rgra/image/upload/v1719121262/63cc5fbb-e282-4852-90f4-73c026baaaab.png'),('7be61bab-83a3-4ab7-a566-f66ab9ec466a','manager@gmail.com','$2a$10$SV9yzklY.dLFHeudv0sAFO7BkbQKECKfuJtqm2NhDLxlbQ1fcdM/u','manager','0123456789',_binary '\0',NULL,20,_binary '','manager','http://res.cloudinary.com/dhwh3rgra/image/upload/v1718119131/cef2889c-a194-4215-bc33-302d916a2780.jpg'),('8140eed3-ac3e-42e5-a709-1413210f0754','admin@gmail.com','$2a$10$33j9UNApqIoiId27JtZK5.tGv.xEu1df45HaljyyeMLYuX1KBpkdG','admin',NULL,NULL,NULL,20,_binary '','admin',NULL),('86939090-6934-42f7-8efa-e3bee331aac1','lyhtkhe176744@fpt.edu.vn','$2a$10$452Zk.TgJPq7nqpAad0cbuZc8FVOCS7ar7C5Dvwt.elpvqIuCq04K','Hoàng Ly','0916986877',_binary '\0',NULL,20,_binary '','ly176744','https://img.freepik.com/premium-vector/avatar-icon0002_750950-43.jpg?size=338&ext=jpg&ga=GA1.1.1224184972.1715212800&semt=ais'),('8f44162c-0b63-46bb-8ce6-1f7432f81d15','','$2a$10$74GLyEwsBSZDf.q1Jf.7Y.OfTEppfMXGRnBU0ytNqQ/ZuLA2XF/S.','',NULL,NULL,NULL,20,_binary '','','https://img.freepik.com/premium-vector/avatar-icon0002_750950-43.jpg?size=338&ext=jpg&ga=GA1.1.1224184972.1715212800&semt=ais'),('a278196e-6053-4027-ad8f-18b2dd5d88da','tuanduvip2@gmail.com','$2a$10$6aBcPUNHa1DfYNJhsidQheGoyj2kF4yxxfpmG4KTc/Ds49qb7w4CG','Dương Tuấn Dự',NULL,NULL,NULL,20,_binary '','tuandu','https://img.freepik.com/premium-vector/avatar-icon0002_750950-43.jpg?size=338&ext=jpg&ga=GA1.1.1224184972.1715212800&semt=ais'),('aabaf07e-f1c0-4135-9ea7-7ac0a35ed0f7','shipper3@gmail.com','$2a$10$ZGLCnWAYaE8BtSW8Nt96W.2Y95iQ/TZroz9YD4fK7v9iOttJhMIJi','Shipper Hai Bà Trưng',NULL,NULL,NULL,20,_binary '','shipper3','https://static.chotot.com/storage/chotot-kinhnghiem/c2c/2021/12/b3d01a73-lam-shipper-cho-cong-ty-nao-tot-nhat-thumb.jpeg'),('bcf2e6ab-5ccf-43f6-bdb3-98396ee914cc','nguyenthanhtungclc@gmail.com','$2a$10$09nFJqmuN7h10.Lx6ucPLews6mVnIu7rHJbp5kju/pM0cEfHWXX7.','Thanh Tùng','0384370666',_binary '','2003-09-19',0,_binary '','thanhtung','http://res.cloudinary.com/dhwh3rgra/image/upload/v1716958545/c95bcd83-d9d3-4058-ae0e-7bbdebf0809b.jpg'),('c36c5c1d-2569-4227-b304-0f06a672d8f8','shipper2@gmail.com','$2a$10$3ipDqmwC5RbbGGKi.Ay5quG0vCUF.WbIqDKjbxBsEEjajHRIX.OhS','Shipper Hoàn Kiếm',NULL,NULL,NULL,20,_binary '','shipper2','https://static.chotot.com/storage/chotot-kinhnghiem/c2c/2021/12/b3d01a73-lam-shipper-cho-cong-ty-nao-tot-nhat-thumb.jpeg'),('d4eb6b07-f46d-4627-885b-80cf8002e9c4','tungnthe173204@fpt.edu.vn',NULL,'Nguyen Thanh Tung','0313214123',_binary '','2000-01-01',20,_binary '',NULL,'http://res.cloudinary.com/dhwh3rgra/image/upload/v1717429700/7d5311c0-eeba-44eb-83e4-f1d30279907b.png'),('dbb553f5-6c9a-4e78-bcb2-68094e6b2607','thuyduong17092003@gmail.com','$2a$10$2iakoFP17X1QDyAciUihwugtsTlGmImE4CVxVJTloFUjOXbSvTHba','duong',NULL,NULL,NULL,20,_binary '','thuyduong1','https://img.freepik.com/premium-vector/avatar-icon0002_750950-43.jpg?size=338&ext=jpg&ga=GA1.1.1224184972.1715212800&semt=ais'),('e892e81c-91b5-4862-b86e-d5daccd8b6b9','hoangnguyenvu1420@gmail.com',NULL,'Vũ Hoàng',NULL,NULL,NULL,20,_binary '',NULL,'https://lh3.googleusercontent.com/a/ACg8ocIEymmyGvQjH7iiC31H7Lg9p2ekaV2p-ctpZ5S5ZdLDKoCAiIA=s96-c'),('ea7ab4c0-26ec-41f9-b3a6-7a75a1fd06c2','haidang2003@gmail.com','$2a$10$UI/pp1v84LJ33zJvS4vAAO5RF88hCbc6ZRRp/LtF2UPnjgayWy2US','hai dang la toi','0333380922',_binary '','2003-03-02',0,_binary '','haidaqn','https://img.freepik.com/premium-vector/avatar-icon0002_750950-43.jpg?size=338&ext=jpg&ga=GA1.1.1224184972.1715212800&semt=ais');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-10 14:20:44
