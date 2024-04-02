-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: localhost    Database: capstone_car_rental
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `booking_detail_booking_detail_id` int DEFAULT NULL,
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `car_id` int NOT NULL,
  `feedback_feedback_id` int DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  `member_id` int NOT NULL,
  `total_price` double NOT NULL,
  `booking_actual_return_time` datetime(6) DEFAULT NULL,
  `booking_pickup_time` datetime(6) NOT NULL,
  `booking_request_time` datetime(6) NOT NULL,
  `booking_return_time` datetime(6) NOT NULL,
  `car_model` varchar(50) NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'PENDING',
  `feedback_id` int DEFAULT NULL,
  PRIMARY KEY (`booking_id`),
  UNIQUE KEY `UK_kpp7815hcn5ssh0tiqh2qad96` (`booking_detail_booking_detail_id`),
  UNIQUE KEY `UK_hiw2of9y0y4hvyppq383p92vw` (`feedback_feedback_id`),
  UNIQUE KEY `UK_oga0a4y01qe7bvdjfwu35rseh` (`feedback_id`),
  KEY `FKd9p8qdy5sj4ym0bmksdx7yrwj` (`car_id`),
  KEY `FKr200vemsolh30s8wvtold760v` (`member_id`),
  CONSTRAINT `FK8qojwbcd1q58me26ry5thu3aj` FOREIGN KEY (`feedback_id`) REFERENCES `feedback` (`feedback_id`),
  CONSTRAINT `FKd9p8qdy5sj4ym0bmksdx7yrwj` FOREIGN KEY (`car_id`) REFERENCES `car` (`car_id`),
  CONSTRAINT `FKeoxf786q9yplvfg4hfoax6oij` FOREIGN KEY (`feedback_feedback_id`) REFERENCES `feedback` (`feedback_id`),
  CONSTRAINT `FKnxokdl781t8s5n3bwrsw347g0` FOREIGN KEY (`booking_detail_booking_detail_id`) REFERENCES `booking_detail` (`booking_detail_id`),
  CONSTRAINT `FKr200vemsolh30s8wvtold760v` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,1,1,NULL,0,3,400,NULL,'2024-03-23 09:35:00.000000','2024-03-22 09:35:39.143257','2024-03-24 09:39:00.000000','Toyota Vios 2023','CANCELLED_AUTOMATIC',NULL),(2,2,2,NULL,0,3,800,'2024-03-22 09:38:14.782144','2024-03-25 09:35:00.000000','2024-03-22 09:36:03.065609','2024-03-30 09:39:00.000000','Toyota Veloz Cross 2023','DONE',1),(3,3,1,NULL,0,3,1200,NULL,'2024-03-25 09:36:00.000000','2024-03-22 09:37:00.890901','2024-04-04 09:36:00.000000','Toyota Vios 2023','APPROVED',NULL),(4,4,1,NULL,0,3,100,NULL,'2024-03-23 09:38:00.000000','2024-03-22 09:39:07.209196','2024-03-24 09:38:00.000000','Toyota Vios 2023','APPROVED',NULL),(5,5,8,NULL,0,3,800,NULL,'2024-03-24 09:39:00.000000','2024-03-22 09:39:38.304397','2024-03-30 09:39:00.000000','BMW 520i 2016','APPROVED',NULL),(6,6,6,NULL,0,3,700,NULL,'2024-03-25 09:39:00.000000','2024-03-22 09:40:02.215367','2024-03-30 09:39:00.000000','BMW 520i 2018','APPROVED',NULL),(7,7,7,NULL,0,3,800,'2024-03-22 09:57:24.313748','2024-03-30 09:40:00.000000','2024-03-22 09:40:23.367172','2024-04-05 09:40:00.000000','BMW 528i 2014','DONE',2),(8,8,9,NULL,0,3,600,NULL,'2024-03-30 09:42:00.000000','2024-03-22 09:42:07.814984','2024-04-03 09:42:00.000000','MAZDA 3 Deluxe 2020','APPROVED',NULL);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_detail`
--

DROP TABLE IF EXISTS `booking_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking_detail` (
  `base_price` double NOT NULL,
  `booking_detail_id` int NOT NULL AUTO_INCREMENT,
  `extra_fee` double NOT NULL DEFAULT '0',
  `feedback_id` int DEFAULT NULL,
  `pickup_fee` double NOT NULL DEFAULT '0',
  `phone_number` varchar(15) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `pickup_address` varchar(255) NOT NULL,
  `payment_method` varchar(20) NOT NULL DEFAULT 'PAYMENT_ON_PICKUP',
  `pickup_type` varchar(20) NOT NULL DEFAULT 'PICKUP_AT_STORE',
  PRIMARY KEY (`booking_detail_id`),
  UNIQUE KEY `UK_qpm6o9ked7g0ax4470eg03a7d` (`feedback_id`),
  CONSTRAINT `FK5bxfpq9d3oo5roolcwt45lurw` FOREIGN KEY (`feedback_id`) REFERENCES `feedback` (`feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_detail`
--

LOCK TABLES `booking_detail` WRITE;
/*!40000 ALTER TABLE `booking_detail` DISABLE KEYS */;
INSERT INTO `booking_detail` VALUES (100,1,0,NULL,200,'0978564321','John Doe','Phu Tho, Vietnam','PAYMENT_ON_PICKUP','PICKUP_AT_HOME'),(100,2,0,NULL,200,'0978564321','John Doe','Phu Tho, Vietnam','PAYMENT_ON_PICKUP','PICKUP_AT_HOME'),(100,3,0,NULL,200,'0978564321','John Doe','Phu Tho, Vietnam','PAYMENT_ON_PICKUP','PICKUP_AT_HOME'),(100,4,0,NULL,0,'0978564321','John Doe','Phu Tho, Vietnam','PAYMENT_ON_PICKUP','PICKUP_AT_STORE'),(100,5,0,NULL,200,'0978564321','John Doe','Phu Tho, Vietnam','PAYMENT_ON_PICKUP','PICKUP_AT_HOME'),(100,6,0,NULL,200,'0978564321','John Doe','Phu Tho, Vietnam','PAYMENT_ON_PICKUP','PICKUP_AT_HOME'),(100,7,0,NULL,200,'0978564321','John Doe','Phu Tho, Vietnam','PAYMENT_ON_PICKUP','PICKUP_AT_HOME'),(100,8,0,NULL,200,'0978564321','John Doe','Phu Tho, Vietnam','PAYMENT_ONLINE','PICKUP_AT_HOME');
/*!40000 ALTER TABLE `booking_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `brand_id` int NOT NULL AUTO_INCREMENT,
  `brand_logo_id` int DEFAULT NULL,
  `brand_name` varchar(100) NOT NULL,
  `brand_description` text,
  PRIMARY KEY (`brand_id`),
  UNIQUE KEY `UK_hsu7w3m7wxvplg49ip7g0v5rr` (`brand_name`),
  UNIQUE KEY `UK_kdjiapkeen9hki699f6hu408f` (`brand_logo_id`),
  CONSTRAINT `FK70k45oyumkfdnlxqs3k0g8ky3` FOREIGN KEY (`brand_logo_id`) REFERENCES `image` (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,1,'BMW','The fascination of the BMW Group lies not just in our products and technologies but in our history too, which has been written by inventors, pioneers and engineers.  Today, the BMW Group is the world’s leading manufacturer of premium cars and motorcycles and a provider of premium financial and mobility services. We operate over 30 production sites around the world and a global sales network.'),(2,2,'Chevrolet','Founded in 1911 in Detroit, Chevrolet is now one of the world\'s largest car brands, doing business in more than 100 countries and selling more than 4.0 million cars and trucks a year. Chevrolet provides customers with fuel-efficient vehicles that feature engaging performance, design that makes the heart beat, passive and active safety features and easy-to-use technology, all at a value.'),(3,3,'Ford','Ford is a family company, one that spans the globe and has shared ideals. We value service to each other and the world as much as to our customers. Generations have made their memories with us and included us in their hopes and dreams. After 120 years, we’re used to adapting to and leading change. That’s why we are evolving to focus on services, experiences and software as well as vehicles.'),(4,4,'Hyundai','Hyundai Motor Company has served as the trailblazer of Korea’s automobile industry since rolling out its Pony, developed with its own exclusive technology. Hyundai Motor Company has risen as a globally recognized automobile manufacturer that exports its branded vehicles to over 200 countries'),(5,5,'Kia','Kia Motors Corp. engages in the manufacturing and sales of motor vehicles. It operates through the following segment: Passenger Cars, Recreational Vehicles and Commercial Vehicles. It is also involved in leasing vehicles, providing vehicle maintenance services and automobile Parts. The company was founded on December 10, 1944 and is headquartered in Seoul, South Korea.'),(6,6,'Mazda','Fast forward to the end of the 20th century, the current Mazda brand symbol was adopted in June 1997. The symbol stands for Mazda’s determination to “pursue ongoing improvements to drive powerful, continuous growth”, expressed by a pair of wings shaped like a letter M in an oval. The V-shaped wings are also suggestive of Mazda’s flexible thinking, creativity, vitality, kindness and resilience. Daring to stand up to seemingly impossible challenges, and standing against all odds to pursue its dream. Committed to making cars with excellent quality with a firm belief that new technology can only be developed from overcoming tough challenges, the spirit of Mazda lives on in its brand symbol.'),(7,7,'Mercedes','Mercedes-Benz was founded in 1926 by Karl Benz, Gottlieb Daimler, Wilhelm Maybach and Emil Jellinek, whose daughter Mercedes is our original namesake.'),(8,8,'Toyota','Toyota strives to be a strong corporate citizen, engaging with and earning the trust of its stakeholders, and to contribute to the creation of a prosperous society through all its business operations.  Our corporate principles form the basis of our initiatives, reflect values that enable action, and drive our mindset.'),(9,9,'Vinfast','VinFast is a member of Vingroup - one of the leading private conglomerates in the region. By always putting our customers first, VinFast relentlessly innovates to create high-quality products and exceptional experiences for everyone.');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car` (
  `base_price` double NOT NULL,
  `brand_id` int NOT NULL,
  `car_id` int NOT NULL AUTO_INCREMENT,
  `deposit` double NOT NULL,
  `feature_image_id` int DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  `mileage` int NOT NULL,
  `number_of_seats` int NOT NULL,
  `owner_id` int NOT NULL,
  `production_year` int NOT NULL,
  `fuel_consumption` varchar(10) NOT NULL,
  `car_color` varchar(20) NOT NULL,
  `car_license_plate` varchar(20) NOT NULL,
  `car_model` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `additional_function` varchar(255) DEFAULT NULL,
  `description` text,
  `term_of_use` text,
  `fuel_type` enum('PETROL','DIESEL','ELECTRIC','HYBRID') NOT NULL,
  `status` enum('PENDING','APPROVED','DONE','REJECTED','CANCELLED','CANCELLED_AUTOMATIC','OUT_OF_TIME') NOT NULL,
  `transmission_type` enum('AUTOMATIC','MANUAL') NOT NULL,
  PRIMARY KEY (`car_id`),
  UNIQUE KEY `UK_d2e3a1o1796eoik3fwqdwnf3k` (`feature_image_id`),
  KEY `FKj1mws2ruu9q6k2sa4pwlxthxn` (`brand_id`),
  KEY `FKe9cv7261ou0yt8nfq8oolnu7v` (`owner_id`),
  CONSTRAINT `FKe9cv7261ou0yt8nfq8oolnu7v` FOREIGN KEY (`owner_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKj1mws2ruu9q6k2sa4pwlxthxn` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`brand_id`),
  CONSTRAINT `FKq4qb7fvwgbt9a4okwciu3es3t` FOREIGN KEY (`feature_image_id`) REFERENCES `image` (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (100,8,1,100,14,NULL,0,4,2,2023,'10L/100km','White','123-456','Toyota Vios 2023','Hanoi','Air conditioner, Radio, GPS, Camera','<p>Good car for rent</p>\r\n','You need to carefully','PETROL','APPROVED','AUTOMATIC'),(100,8,2,100,17,NULL,0,7,2,2023,'10L/100km','Black','351-333','Toyota Veloz Cross 2023','Hanoi','Air conditioner, Radio, GPS, Camera','<p>Good car for rent</p>\r\n','You need to carefully','PETROL','APPROVED','AUTOMATIC'),(100,8,3,100,20,NULL,0,4,2,2014,'10L/100km','Black','561-644','TOYOTA CAMRY 2.0G 2014','Hanoi','Air conditioner, Radio, GPS, Camera','<p>Good car for rent</p>\r\n','You need to carefully','PETROL','APPROVED','AUTOMATIC'),(100,8,4,100,23,NULL,0,4,2,2023,'10L/100km','Red','261-344','TOYOTA VIOS 2023','Hanoi','Air conditioner, Radio, GPS, Camera','<p>Good car for rent</p>\r\n','You need to carefully','PETROL','APPROVED','AUTOMATIC'),(100,8,5,100,26,NULL,0,4,2,2011,'10L/100km','Black','126-666','TOYOTA CAMRY 2.0G 2011','Hanoi','Air conditioner, Radio, GPS, Camera','<p>Good car for rent</p>\r\n','You need to carefully','PETROL','APPROVED','AUTOMATIC'),(100,1,6,100,32,NULL,0,5,2,2018,'10L/100km','Black','491-555','BMW 520i 2018','Hanoi','Air conditioner, Radio, GPS, Camera','<p>Good car for rent</p>\r\n','You need to carefully','PETROL','APPROVED','AUTOMATIC'),(100,1,7,100,35,NULL,0,5,2,2014,'10L/100km','Black','111-111','BMW 528i 2014','Hanoi','Air conditioner, Radio, GPS, Camera','<p>Good car for rent</p>\r\n','You need to carefully','PETROL','APPROVED','AUTOMATIC'),(100,1,8,100,38,NULL,0,4,2,2016,'10L/100km','Black','173-377','BMW 520i 2016','Hanoi','Air conditioner, Radio, GPS, Camera','<p>Good car for rent</p>\r\n','You need to carefully','PETROL','APPROVED','AUTOMATIC'),(100,6,9,100,41,NULL,0,5,4,2020,'10L/100km','Blue','813-133','MAZDA 3 Deluxe 2020','Hanoi','Air conditioner, Radio, GPS, Camera','<p>Good car for rent</p>\r\n','You need to carefully','PETROL','APPROVED','AUTOMATIC'),(100,6,10,100,44,NULL,0,5,4,2019,'10L/100km','Black','111-112','MAZDA 3 Deluxe 2019','Hanoi','Air conditioner, Radio, GPS, Camera','<p>Good car for rent</p>\r\n','You need to carefully','PETROL','APPROVED','AUTOMATIC'),(100,6,11,100,47,NULL,0,5,4,2015,'10L/100km','Red','123-456','MAZDA 6 Luxury 2015','Hanoi','Air conditioner, Radio, GPS, Camera','<p>Good car for rent</p>\r\n','You need to carefully','PETROL','APPROVED','AUTOMATIC'),(100,6,12,100,50,NULL,0,5,4,2022,'10L/100km','White','648-466','MAZDA CX5 Deluxe 2022','Hanoi','Air conditioner, Radio, GPS, Camera','<p>Good car for rent</p>\r\n','You need to carefully','PETROL','APPROVED','AUTOMATIC');
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_images`
--

DROP TABLE IF EXISTS `car_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_images` (
  `car_car_id` int NOT NULL,
  `images_image_id` int NOT NULL,
  UNIQUE KEY `UK_m6inxq8bwakxchs4c5m7ekkc7` (`images_image_id`),
  KEY `FKhjwlbkob1i7bglvjv5ehvxamf` (`car_car_id`),
  CONSTRAINT `FK91b3p8snwduv94voqe97r3o8r` FOREIGN KEY (`images_image_id`) REFERENCES `image` (`image_id`),
  CONSTRAINT `FKhjwlbkob1i7bglvjv5ehvxamf` FOREIGN KEY (`car_car_id`) REFERENCES `car` (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_images`
--

LOCK TABLES `car_images` WRITE;
/*!40000 ALTER TABLE `car_images` DISABLE KEYS */;
INSERT INTO `car_images` VALUES (1,15),(1,16),(2,18),(2,19),(3,21),(3,22),(4,24),(4,25),(5,27),(5,28),(6,33),(6,34),(7,36),(7,37),(8,39),(8,40),(9,42),(9,43),(10,45),(10,46),(11,48),(11,49),(12,51),(12,52);
/*!40000 ALTER TABLE `car_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `feedback_id` int NOT NULL AUTO_INCREMENT,
  `feedback_point` int NOT NULL,
  `feedback_date` datetime(6) NOT NULL,
  `feedback_updated_at` datetime(6) DEFAULT NULL,
  `feedback_content` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,5,'2024-03-22 09:58:40.365492',NULL,'Great experience with this. Very nice'),(2,5,'2024-03-22 09:59:35.406952',NULL,'No1 car of the world. Out standing experience');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `image_id` int NOT NULL AUTO_INCREMENT,
  `image_alt` varchar(1000) DEFAULT NULL,
  `image_name` varchar(1000) NOT NULL,
  `image_url` text NOT NULL,
  `image_type` enum('PNG','WEBP','JPG','JPEG') NOT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,'bmw_brand_logo','bmw_brand_logo.png','uploads/c855a0b7-9ec0-438a-be53-3650ca41d802-bmw_brand_logo.png','PNG'),(2,'chevrolet_brand_logo','chevrolet_brand_logo.jpg','uploads/7042b730-0411-462b-a843-6257b0c37cbb-chevrolet_brand_logo.jpg','JPEG'),(3,'ford_logo','ford_logo.jpg','uploads/1a1b624e-3434-4831-bfb3-1b779455b2e1-ford_logo.jpg','JPEG'),(4,'hyundai_logo','hyundai_logo.jpg','uploads/767b7c40-85ae-473a-8ea8-323bf7cc1125-hyundai_logo.jpg','JPEG'),(5,'kia_brand_logo','kia_brand_logo.png','uploads/b20b5695-2324-4282-868c-4433d5251729-kia_brand_logo.png','PNG'),(6,'mazda_brand_logo','mazda_brand_logo.jpg','uploads/67e1a79e-4a9b-4f98-801c-b90a8d74b128-mazda_brand_logo.jpg','JPEG'),(7,'mercedes_brand_logo','mercedes_brand_logo.jpg','uploads/4bbb71e0-a5cd-4cce-8ffb-929370a143c9-mercedes_brand_logo.jpg','JPEG'),(8,'toyota_brand_logo','toyota_brand_logo.jpg','uploads/498e1ba9-b571-4f20-8521-9d801538e64e-toyota_brand_logo.jpg','JPEG'),(9,'vinfast_brand_logo','vinfast_brand_logo.png','uploads/8462d646-aadd-4c3e-b9ca-4e406855d6d4-vinfast_brand_logo.png','PNG'),(10,'toyota-vios-13','toyota-vios-13.jpeg','uploads/132b2aa2-2a25-491c-9280-d1119797c3e5-toyota-vios-13.jpeg','JPEG'),(11,'toyota-vios-07','toyota-vios-07.jpeg','uploads/491a8b52-f724-4964-83b0-fd138b8dcf73-toyota-vios-07.jpeg','JPEG'),(12,'toyota-vios-08','toyota-vios-08.jpeg','uploads/50e5bf5b-109c-456d-888f-6dbfa9bdcf42-toyota-vios-08.jpeg','JPEG'),(13,'toyota-vios-13','toyota-vios-13.jpeg','uploads/d270dae5-b0e2-4703-9abe-9ec08adea8f6-toyota-vios-13.jpeg','JPEG'),(14,'toyota_vios_2023_0','toyota_vios_2023_0.jpg','uploads/05dd2946-2bec-4a34-b30d-1e82c4df502e-toyota_vios_2023_0.jpg','JPEG'),(15,'toyota_vios_2023_1','toyota_vios_2023_1.jpg','uploads/83f12017-f984-4b57-a767-ffab7f4d2cfa-toyota_vios_2023_1.jpg','JPEG'),(16,'toyota_vios_2023_2','toyota_vios_2023_2.jpg','uploads/54fa63a2-5de4-411f-b616-d4dacc508473-toyota_vios_2023_2.jpg','JPEG'),(17,'toyota-veloz-cross-2023','toyota-veloz-cross-2023.jpg','uploads/8b2bfcc1-1d54-474b-8864-99a0edd0ccec-toyota-veloz-cross-2023.jpg','JPEG'),(18,'toyota-veloz-cross-2023-2','toyota-veloz-cross-2023-2.jpg','uploads/6a7e1ba4-ee39-4fdd-b19c-aaf8cc2dad90-toyota-veloz-cross-2023-2.jpg','JPEG'),(19,'toyota-veloz-cross-2023-1','toyota-veloz-cross-2023-1.jpg','uploads/c682af4d-7d16-4cab-b232-2d146374cb0a-toyota-veloz-cross-2023-1.jpg','JPEG'),(20,'toyota-camry','toyota-camry.jpg','uploads/cb3dd5b5-d120-4bac-b93c-76d6e7d379cc-toyota-camry.jpg','JPEG'),(21,'toyota-camry-2','toyota-camry-2.jpg','uploads/7d4fd0f7-f198-4cd1-b2ae-27a7a1fd06a7-toyota-camry-2.jpg','JPEG'),(22,'toyota-camry-1','toyota-camry-1.jpg','uploads/2387619b-5518-4124-bcfa-47298b64cdc0-toyota-camry-1.jpg','JPEG'),(23,'toyota_vios_2023_red','toyota_vios_2023_red.jpg','uploads/dbb68c99-7bd0-407d-a953-ecdf2fe5a145-toyota_vios_2023_red.jpg','JPEG'),(24,'toyota_vios_2023_red_2','toyota_vios_2023_red_2.jpg','uploads/c791c1dd-f564-4eee-9697-ac808915493c-toyota_vios_2023_red_2.jpg','JPEG'),(25,'toyota_vios_2023_red_1','toyota_vios_2023_red_1.jpg','uploads/62b2e338-8fe1-412b-9164-f7f94f2ef7f5-toyota_vios_2023_red_1.jpg','JPEG'),(26,'toyota-camry-2011-1','toyota-camry-2011-1.jpg','uploads/c6b312e0-a1c8-4017-b9cb-9ae1225defd0-toyota-camry-2011-1.jpg','JPEG'),(27,'toyota-camry-2011-3','toyota-camry-2011-3.jpg','uploads/7c7111ab-d83f-4efb-b48c-5d6fd1649f7f-toyota-camry-2011-3.jpg','JPEG'),(28,'toyota-camry-2011-2','toyota-camry-2011-2.jpg','uploads/2d10abdf-3798-4642-b48a-4435358a1928-toyota-camry-2011-2.jpg','JPEG'),(29,'bmw-520-2018','bmw-520-2018.jpg','uploads/f0cf9005-871e-4fe1-8d2e-c892b2fa9b7e-bmw-520-2018.jpg','JPEG'),(30,'bmw-520-2018-2','bmw-520-2018-2.jpg','uploads/caf21ed0-08c7-48f8-9913-2f7ecfdc0901-bmw-520-2018-2.jpg','JPEG'),(31,'bmw-520-2018-1','bmw-520-2018-1.jpg','uploads/9b1bd289-d26d-4afc-8ad9-67ff12f708cb-bmw-520-2018-1.jpg','JPEG'),(32,'bmw-520-2018','bmw-520-2018.jpg','uploads/23b0706d-d104-45f3-9c14-29e1ac19f407-bmw-520-2018.jpg','JPEG'),(33,'bmw-520-2018-2','bmw-520-2018-2.jpg','uploads/1163d386-da3d-44d1-ae7b-4be1cd2c840d-bmw-520-2018-2.jpg','JPEG'),(34,'bmw-520-2018-1','bmw-520-2018-1.jpg','uploads/739b65b2-b580-4eab-982e-9aa669e4d0c6-bmw-520-2018-1.jpg','JPEG'),(35,'bmw-528-2014','bmw-528-2014.jpg','uploads/758dc100-cda9-4971-9964-59756774cc5e-bmw-528-2014.jpg','JPEG'),(36,'bmw-528-2014-2','bmw-528-2014-2.jpg','uploads/07d22601-e304-4e6b-9aff-23e6e24ef2c2-bmw-528-2014-2.jpg','JPEG'),(37,'bmw-528-2014-1','bmw-528-2014-1.jpg','uploads/77ef7ae3-fab5-4e75-a3ea-29cc159e6a19-bmw-528-2014-1.jpg','JPEG'),(38,'bmw-520i-2016','bmw-520i-2016.jpg','uploads/7e8e60f2-a53a-49de-9108-9caf3ffbe638-bmw-520i-2016.jpg','JPEG'),(39,'bmw-520i-2016-1','bmw-520i-2016-1.jpg','uploads/be16cdeb-b6c2-4f5c-b363-790d49faeec2-bmw-520i-2016-1.jpg','JPEG'),(40,'bmw-520i-2016-2','bmw-520i-2016-2.jpg','uploads/fa8962e6-f8e1-4489-be75-c67ae4683fc4-bmw-520i-2016-2.jpg','JPEG'),(41,'mazda3-deluxe-2020','mazda3-deluxe-2020.jpg','uploads/86d468a4-0c84-492b-b694-3c344b482af9-mazda3-deluxe-2020.jpg','JPEG'),(42,'mazda3-deluxe-2020-2','mazda3-deluxe-2020-2.jpg','uploads/c7afb34e-5815-4b6a-8377-1b84ad614248-mazda3-deluxe-2020-2.jpg','JPEG'),(43,'mazda3-deluxe-2020-1','mazda3-deluxe-2020-1.jpg','uploads/fc6d89df-20c6-47a7-8d44-c34cf78d02aa-mazda3-deluxe-2020-1.jpg','JPEG'),(44,'mazda3-deluxe-2019-2','mazda3-deluxe-2019-2.jpg','uploads/6b13edee-0ae8-4d7d-a358-80ae5482921d-mazda3-deluxe-2019-2.jpg','JPEG'),(45,'mazda3-deluxe-2019-1','mazda3-deluxe-2019-1.jpg','uploads/e1760e12-943d-4557-89a3-878c240fe582-mazda3-deluxe-2019-1.jpg','JPEG'),(46,'mazda3-deluxe-2019','mazda3-deluxe-2019.jpg','uploads/51770407-97de-4f6d-ac45-8908e7a3f338-mazda3-deluxe-2019.jpg','JPEG'),(47,'mazda6-luxury-2015','mazda6-luxury-2015.jpg','uploads/bc611503-9f7e-4900-b4cd-ac2b5c5bd957-mazda6-luxury-2015.jpg','JPEG'),(48,'mazda6-luxury-2015-3','mazda6-luxury-2015-3.jpg','uploads/e2957806-6e6e-47f0-90ae-695ba21d5d04-mazda6-luxury-2015-3.jpg','JPEG'),(49,'mazda6-luxury-2015-2','mazda6-luxury-2015-2.jpg','uploads/11862db7-a901-4444-94f9-56e1ef33b90a-mazda6-luxury-2015-2.jpg','JPEG'),(50,'mazda-cx5-deluxe-2022','mazda-cx5-deluxe-2022.jpg','uploads/30a6bff7-d549-48fa-b954-6663d4db3179-mazda-cx5-deluxe-2022.jpg','JPEG'),(51,'mazda-cx5-deluxe-2022-2','mazda-cx5-deluxe-2022-2.jpg','uploads/5bf528b6-a16c-4678-8657-5616b2d11f2f-mazda-cx5-deluxe-2022-2.jpg','JPEG'),(52,'mazda-cx5-deluxe-2022-1','mazda-cx5-deluxe-2022-1.jpg','uploads/6f30776e-c2e4-4d26-b244-38000cbf6098-mazda-cx5-deluxe-2022-1.jpg','JPEG'),(53,'ford_logo','ford_logo.jpg','uploads/f4870ee9-d31c-46e4-9522-cee62d5fc3f7-ford_logo.jpg','JPEG'),(54,'mazda_brand_logo','mazda_brand_logo.jpg','uploads/daf29017-2a26-4a39-b620-30a25ed30767-mazda_brand_logo.jpg','JPEG'),(55,'bmw_brand_logo','bmw_brand_logo.png','uploads/f562bf90-68b7-4cf6-a737-5bdbee470410-bmw_brand_logo.png','PNG'),(56,'vinfast_brand_logo','vinfast_brand_logo.png','uploads/046a9ae6-90c9-4a71-909a-08ddce741cd5-vinfast_brand_logo.png','PNG'),(57,'kia_brand_logo','kia_brand_logo.png','uploads/8ae4b4e2-9c32-43ba-9c55-4e1669053f7a-kia_brand_logo.png','PNG'),(58,'mazda-cx5-deluxe-2022','mazda-cx5-deluxe-2022.jpg','uploads/a2abe565-fa41-4bfe-8783-e3e41dcf262d-mazda-cx5-deluxe-2022.jpg','JPEG'),(59,'mazda_brand_logo','mazda_brand_logo.jpg','uploads/b1f636bf-0b17-4e79-b809-892552a0cc6d-mazda_brand_logo.jpg','JPEG'),(60,'mazda_brand_logo','mazda_brand_logo.jpg','uploads/6281882a-c121-4b7a-9b46-32e69556bbec-mazda_brand_logo.jpg','JPEG'),(61,'mazda6-luxury-2015-3','mazda6-luxury-2015-3.jpg','uploads/49336b5e-50f6-44a6-8826-4ac96c4d5122-mazda6-luxury-2015-3.jpg','JPEG'),(62,'mazda3-deluxe-2019-1','mazda3-deluxe-2019-1.jpg','uploads/a72c15df-a2e0-4157-9472-f8e30a5f7b87-mazda3-deluxe-2019-1.jpg','JPEG'),(63,'kia_brand_logo','kia_brand_logo.png','uploads/6d48e0e8-2742-4099-8f98-affce4f373b6-kia_brand_logo.png','PNG'),(64,'mazda6-luxury-2015-2','mazda6-luxury-2015-2.jpg','uploads/66177409-5270-41f1-bbe0-2e0c903c78e3-mazda6-luxury-2015-2.jpg','JPEG'),(65,'mazda3-deluxe-2019','mazda3-deluxe-2019.jpg','uploads/cfcc1885-9a55-463c-bfac-b01ddb3274a5-mazda3-deluxe-2019.jpg','JPEG'),(66,'chevrolet_brand_logo','chevrolet_brand_logo.jpg','uploads/d895d7bf-8906-42a8-91a1-8e240b965c9d-chevrolet_brand_logo.jpg','JPEG'),(67,'mazda3-deluxe-2020-2','mazda3-deluxe-2020-2.jpg','uploads/4e4ba74d-f216-4653-a777-752ad7c3cab6-mazda3-deluxe-2020-2.jpg','JPEG'),(68,'mazda3-deluxe-2019-1','mazda3-deluxe-2019-1.jpg','uploads/61b853e7-1c3e-4724-8144-0eb5b5b6f5d4-mazda3-deluxe-2019-1.jpg','JPEG'),(69,'vinfast_brand_logo','vinfast_brand_logo.png','uploads/d81952a9-be30-4263-b5af-d3429b1aca62-vinfast_brand_logo.png','PNG'),(70,'mazda-cx5-deluxe-2022-2','mazda-cx5-deluxe-2022-2.jpg','uploads/9985d070-1be4-47c9-8387-27898bef2d66-mazda-cx5-deluxe-2022-2.jpg','JPEG'),(71,'mazda6-luxury-2015','mazda6-luxury-2015.jpg','uploads/91a6df15-893c-47c2-9908-4709486dd2d6-mazda6-luxury-2015.jpg','JPEG'),(72,'mazda3-deluxe-2020','mazda3-deluxe-2020.jpg','uploads/6b45565c-6e8e-45d6-bc6c-3c35fb1056c2-mazda3-deluxe-2020.jpg','JPEG'),(73,'mazda3-deluxe-2019-2','mazda3-deluxe-2019-2.jpg','uploads/9ad09f39-36fa-49a9-adb2-881991893644-mazda3-deluxe-2019-2.jpg','JPEG'),(74,'toyota-camry','toyota-camry.jpg','uploads/0467801b-184e-4382-9caf-4d3c903d1dba-toyota-camry.jpg','JPEG'),(75,'bmw-520i-2016-2','bmw-520i-2016-2.jpg','uploads/25ea2d21-f013-4d60-92c6-6d84b27ffefb-bmw-520i-2016-2.jpg','JPEG'),(76,'chevrolet_brand_logo','chevrolet_brand_logo.jpg','uploads/b76da4d3-49b0-4ba8-8808-002253fe4ee8-chevrolet_brand_logo.jpg','JPEG'),(77,'mazda3-deluxe-2019-2','mazda3-deluxe-2019-2.jpg','uploads/5507d28b-92df-4792-9402-4a0d6a437352-mazda3-deluxe-2019-2.jpg','JPEG'),(78,'mazda6-luxury-2015-3','mazda6-luxury-2015-3.jpg','uploads/abf9181b-50da-4328-8d17-3911effdc98c-mazda6-luxury-2015-3.jpg','JPEG'),(79,'mazda3-deluxe-2020-2','mazda3-deluxe-2020-2.jpg','uploads/b744a79c-2b6e-4d6d-8612-a959041526b5-mazda3-deluxe-2020-2.jpg','JPEG'),(80,'mazda3-deluxe-2020-2','mazda3-deluxe-2020-2.jpg','uploads/e57f2aba-f3cc-4459-ab12-cc197788b966-mazda3-deluxe-2020-2.jpg','JPEG'),(82,'mazda3-deluxe-2019-1','mazda3-deluxe-2019-1.jpg','uploads/58f6d062-2f58-4c33-b5bd-e08925c308f9-mazda3-deluxe-2019-1.jpg','JPEG'),(83,'mazda3-deluxe-2020-2','mazda3-deluxe-2020-2.jpg','uploads/ca161661-9d49-402b-9230-b877963c9c71-mazda3-deluxe-2020-2.jpg','JPEG');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `date_of_birth` date DEFAULT NULL,
  `feature_image_id` int DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  `is_verified` tinyint(1) DEFAULT '0',
  `member_id` int NOT NULL AUTO_INCREMENT,
  `role` tinyint NOT NULL,
  `wallet_balance` double NOT NULL DEFAULT '0',
  `created_date` datetime(6) NOT NULL,
  `last_modified_date` datetime(6) NOT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `full_name` varchar(20) NOT NULL,
  `national_id` varchar(20) DEFAULT NULL,
  `user_name` varchar(20) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `UK_ta4mcbv6lkyy444jcfe8dj1co` (`user_name`),
  UNIQUE KEY `UK_mbmcqelty0fbrvxp1q58dn57t` (`email`),
  UNIQUE KEY `UK_5vbi7yy67a6ke3ih33e4drl4l` (`feature_image_id`),
  UNIQUE KEY `UK_n2qryhkfoqeel6njfhrcq6k7u` (`phone_number`),
  UNIQUE KEY `UK_sbq47p44sjt19kaq2exkjqqou` (`national_id`),
  CONSTRAINT `FK3ghx72cs13omgmdtu13n8ecja` FOREIGN KEY (`feature_image_id`) REFERENCES `image` (`image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('2024-03-23',NULL,1,1,1,2,0,'2024-03-23 16:33:59.833337','2024-03-23 16:33:59.833337','0123456789','Admin','0123456789','admin','Vietnam','admin@gmail.com','$2a$10$1IIRWbdr9tzE6fzSe2.Djeg0utCGIwD9aoW09O3oaCo/KVQQYm942'),('2024-03-23',NULL,1,1,2,1,500,'2024-03-23 16:33:59.909338','2024-03-23 16:33:59.909338','02839284751','Adam Smith','0256321686','owner','Da nang, Vietnam','carowner@gmail.com','$2a$10$qHoOM2ktv404XiJ68eogz.JujKii5Vk8wPkoOom7G7TRZCHJkAkdW'),('2024-03-23',NULL,1,1,3,0,80,'2024-03-23 16:33:59.986340','2024-03-23 16:33:59.986340','0978564321','John Doe','0453378645','customer','Phu Tho, Vietnam','customer@gmail.com','$2a$10$yShDF/6XgHsvAEbG5tjlieltsIIoZB9szJ8RfglnnF.5cI7YyuvYu'),('2024-03-23',NULL,1,1,4,1,1000,'2024-03-23 16:34:00.060336','2024-03-23 16:34:00.060336','0987654321','Mark Wilson','9321872412','owner2','Vietnam','carowner2@gmail.com','$2a$10$6kcBqabxrBcR2nWaT6naWuqmlKT5PfpoDk36OdFdPshtRlCZbq5G.'),('2024-03-23',NULL,1,1,5,0,5000,'2024-03-23 16:34:00.134336','2024-03-23 16:34:00.134336',NULL,'Selena Gomez','0453444644','customer2','Hanoi, Vietnam','customer2@gmail.com','$2a$10$/LYx2.TZLl06TpUWdoqN4O3A2POwwBL1lYtuN.jeydcmSB0H6xn42');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `author_member_id` int DEFAULT NULL,
  `post_featured_image_image_id` int DEFAULT NULL,
  `post_id` int NOT NULL AUTO_INCREMENT,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `post_title` varchar(256) NOT NULL,
  `slug` varchar(256) NOT NULL,
  `content` text NOT NULL,
  `post_status` varchar(10) NOT NULL DEFAULT 'DRAFT',
  PRIMARY KEY (`post_id`),
  UNIQUE KEY `UK_1cg7fr7ckgm878v5j9bwjixis` (`slug`),
  UNIQUE KEY `UK_f610c17vhyj9g4is43h4i7dsi` (`post_featured_image_image_id`),
  KEY `FKnmkvwr50iw7wgbcg4d2lwty1y` (`author_member_id`),
  CONSTRAINT `FKbvu0xqdnsgyledv29m7i5vpg0` FOREIGN KEY (`post_featured_image_image_id`) REFERENCES `image` (`image_id`),
  CONSTRAINT `FKnmkvwr50iw7wgbcg4d2lwty1y` FOREIGN KEY (`author_member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,53,1,'2024-03-22 02:44:03',NULL,'2024-03-23 08:39:12','The fascination of the BMW Group','The fascination of the BMW Group','<p>The fascination of the BMW Group lies not just in our products and technologies but in our history too, which has been written by inventors, pioneers and engineers. &nbsp;Today, the BMW Group is the world&rsquo;s leading manufacturer of premium cars and motorcycles and a provider of premium financial and mobility services. We operate over 30 production sites around the world and a global sales network.</p>\r\n\r\n<div id=\"gtx-trans\" style=\"position: absolute; left: 179px; top: 38.3333px;\">\r\n<div class=\"gtx-trans-icon\">&nbsp;</div>\r\n</div>\r\n','PUBLISH'),(1,54,2,'2024-03-22 02:44:34',NULL,'2024-03-22 02:44:34','Mazda','mazda','<p>Fast forward to the end of the 20th century, the current Mazda brand symbol was adopted in June 1997. The symbol stands for Mazda&rsquo;s determination to &ldquo;pursue ongoing improvements to drive powerful, continuous growth&rdquo;, expressed by a pair of wings shaped like a letter M in an oval. The V-shaped wings are also suggestive of Mazda&rsquo;s flexible thinking, creativity, vitality, kindness and resilience. Daring to stand up to seemingly impossible challenges, and standing against all odds to pursue its dream. Committed to making cars with excellent quality with a firm belief that new technology can only be developed from overcoming tough challenges, the spirit of Mazda lives on in its brand symbol.</p>\r\n','PUBLISH'),(1,55,3,'2024-03-22 02:44:52',NULL,'2024-03-22 02:44:52','BMW','bmw','<p>The fascination of the BMW Group lies not just in our products and technologies but in our history too, which has been written by inventors, pioneers and engineers. &nbsp;Today, the BMW Group is the world&rsquo;s leading manufacturer of premium cars and motorcycles and a provider of premium financial and mobility services. We operate over 30 production sites around the world and a global sales network.</p>\r\n','TRASH'),(1,56,4,'2024-03-22 02:45:28',NULL,'2024-03-22 02:45:28','Vinfast','vinfast','<p>VinFast is a member of Vingroup - one of the leading private conglomerates in the region. By always putting our customers first, VinFast relentlessly innovates to create high-quality products and exceptional experiences for everyone.</p>\r\n','PUBLISH'),(1,57,5,'2024-03-22 02:45:49',NULL,'2024-03-22 02:45:49','KIA ','kia-','<p>Kia Motors Corp. engages in the manufacturing and sales of motor vehicles. It operates through the following segment: Passenger Cars, Recreational Vehicles and Commercial Vehicles. It is also involved in leasing vehicles, providing vehicle maintenance services and automobile Parts. The company was founded on December 10, 1944 and is headquartered in Seoul, South Korea.</p>\r\n','DRAFT'),(1,70,6,'2024-03-22 03:41:07',NULL,'2024-03-22 03:41:07','Mazda CX5 Deluxe 2022','mazda-cx5-deluxe-2022','<p>Very good car to rent</p>\r\n','PUBLISH'),(1,71,7,'2024-03-22 03:41:53',NULL,'2024-03-23 08:39:03','Suspendisse at elementum mi. Sed id velit gravida','Suspendisse at elementum mi. Sed id velit gravida','<p>Morbi arcu leo, molestie non sem vitae, malesuada feugiat neque. Praesent condimentum neque nec neque porttitor, sit amet scelerisque eros molestie. Sed sagittis faucibus arcu, non posuere risus porttitor id. Aenean bibendum consectetur eros, vel facilisis eros tempor quis. Donec condimentum vitae est nec ultrices. Etiam consequat commodo elit a viverra. Nunc quis elit a magna semper pharetra non eget lectus. Nunc nec massa at felis varius suscipit ut nec nunc. Sed sodales eu purus ut pharetra. Donec dui turpis, congue eu bibendum id, gravida a lacus. Curabitur molestie, velit non faucibus porttitor, tellus ex tristique justo, sed pulvinar orci felis eget ante. Pellentesque aliquet dictum tincidunt. Aenean eu cursus mi.</p>\r\n\r\n<p>Suspendisse at elementum mi. Sed id velit gravida, consequat lorem ac, gravida nunc. Curabitur facilisis molestie dapibus. Proin a nulla sagittis, rhoncus mi quis, interdum felis. Fusce a dignissim ligula, vel porttitor leo. Sed faucibus ut arcu eu eleifend. Vivamus tincidunt posuere mi at ornare. Quisque vestibulum euismod tempus.</p>\r\n\r\n<p>Praesent vehicula fringilla tempus. Suspendisse potenti. Ut a posuere tortor. Integer quis nisl volutpat, pretium turpis id, faucibus dolor. In nibh nibh, fringilla eget augue eget, pellentesque blandit ex. Integer ultrices dignissim mollis. Nam sit amet sapien tortor. Praesent eu iaculis metus. Donec quis varius sapien, nec tempus eros. Duis non iaculis dui, at aliquam velit. Suspendisse non quam congue, dignissim dolor ac, maximus nisi. Aenean vitae arcu nec magna vulputate efficitur eget in ipsum. Fusce bibendum quam sit amet sollicitudin viverra. Phasellus fermentum lorem libero, a pulvinar lectus dapibus nec.</p>\r\n\r\n<div id=\"gtx-trans\" style=\"position: absolute; left: 283px; top: 112.104px;\">\r\n<div class=\"gtx-trans-icon\">&nbsp;</div>\r\n</div>\r\n','PUBLISH'),(1,72,8,'2024-03-22 03:42:46',NULL,'2024-03-22 03:42:46','Mazda 3 Deluxe 2020','mazda-3-deluxe-2020','<p>Great car for you.</p>\r\n','PUBLISH'),(1,73,9,'2024-03-22 03:43:17',NULL,'2024-03-23 08:38:55','Nulla facilisi. Sed tempus sem sit amet faucibus accumsan','Nulla facilisi. Sed tempus sem sit amet faucibus accumsan','<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam ut ipsum sollicitudin, dignissim nisl in, ullamcorper elit. Phasellus vulputate eros nec purus pharetra varius. Vestibulum vel dui nec leo sodales sagittis. Nulla erat mauris, commodo id varius bibendum, hendrerit at arcu. In ac magna sit amet nulla pellentesque malesuada vel ac ipsum. Sed id pellentesque orci, vel rutrum sapien. Integer lectus felis, venenatis ultricies velit eu, egestas pretium ex. Duis a neque ac lorem porttitor viverra. Morbi quis venenatis tellus. Aliquam in enim sit amet urna tempor faucibus. Integer lacus sem, pretium non condimentum condimentum, viverra ut lacus. Nunc quis lacus ut lorem euismod consectetur. In egestas pulvinar ornare.</p>\r\n\r\n<p>Nulla facilisi. Sed tempus sem sit amet faucibus accumsan. Aliquam erat volutpat. Cras fermentum nulla pellentesque vestibulum condimentum. Integer ac ex quis turpis sagittis egestas at eu odio. Aenean finibus ipsum eu augue lobortis cursus. Pellentesque laoreet consectetur arcu, ut condimentum orci elementum nec. Nunc suscipit laoreet libero, quis commodo libero vestibulum lacinia. Nam nibh eros, rutrum in fermentum eu, semper nec justo. Fusce pretium scelerisque dui, nec auctor elit varius quis.</p>\r\n\r\n<p>Suspendisse nulla ipsum, faucibus vitae gravida eu, viverra sit amet enim. Maecenas feugiat est turpis, sit amet consequat urna feugiat eleifend. Cras feugiat velit ut lorem blandit, ac tristique urna porta. Cras gravida elementum volutpat. Quisque ac nisl nec purus semper hendrerit. Aliquam vel tempus felis. Vestibulum tincidunt maximus ante eget sollicitudin. Curabitur sed pulvinar ante. In fringilla aliquam nulla quis lacinia. Nunc in tincidunt purus. Suspendisse viverra, neque vel auctor venenatis, nunc erat euismod tellus, non mollis lorem orci non libero. Proin gravida nibh at nisl vulputate molestie. Donec ac est hendrerit, tincidunt ligula ut, molestie nunc. Nullam dapibus, tortor non pellentesque blandit, lorem nisl suscipit magna, et semper arcu tortor ac nibh. Morbi ullamcorper faucibus placerat. Integer tristique quis erat imperdiet interdum.</p>\r\n\r\n<div id=\"gtx-trans\" style=\"position: absolute; left: 320px; top: 112.104px;\">\r\n<div class=\"gtx-trans-icon\">&nbsp;</div>\r\n</div>\r\n','PUBLISH'),(1,74,10,'2024-03-22 03:44:08',NULL,'2024-03-23 08:38:48',' Lorem Ipsum has been the industry\'s standard',' Lorem Ipsum has been the industry\'s standard','<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>\r\n\r\n<h2>Why do we use it?</h2>\r\n\r\n<p>It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using &#39;Content here, content here&#39;, making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for &#39;lorem ipsum&#39; will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<h2>Where does it come from?</h2>\r\n\r\n<p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of &quot;de Finibus Bonorum et Malorum&quot; (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, &quot;Lorem ipsum dolor sit amet..&quot;, comes from a line in section 1.10.32.</p>\r\n\r\n<p>The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from &quot;de Finibus Bonorum et Malorum&quot; by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.</p>\r\n\r\n<h2>Where can I get some?</h2>\r\n\r\n<p>There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don&#39;t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn&#39;t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.</p>\r\n\r\n<div id=\"gtx-trans\" style=\"position: absolute; left: 708px; top: 38.3333px;\">\r\n<div class=\"gtx-trans-icon\">&nbsp;</div>\r\n</div>\r\n','PUBLISH'),(1,75,11,'2024-03-22 03:44:57',NULL,'2024-03-22 03:44:57','BMW 520i 2016','bmw-520i-2016','<p>Perfect.&nbsp;</p>\r\n','DRAFT'),(1,76,12,'2024-03-22 03:45:29',NULL,'2024-03-22 03:45:29','Chevrolet','chevrolet','<p>Trash car</p>\r\n','TRASH');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_option`
--

DROP TABLE IF EXISTS `system_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_option` (
  `option_id` int NOT NULL AUTO_INCREMENT,
  `option_alias_name` varchar(100) NOT NULL,
  `option_key` varchar(100) NOT NULL,
  `option_value` varchar(1000) NOT NULL,
  PRIMARY KEY (`option_id`),
  UNIQUE KEY `UK_5txcif6632n8m3r6edyqsepgx` (`option_alias_name`),
  UNIQUE KEY `UK_nl71od14lvgwfp2tmtnn24f40` (`option_key`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_option`
--

LOCK TABLES `system_option` WRITE;
/*!40000 ALTER TABLE `system_option` DISABLE KEYS */;
INSERT INTO `system_option` VALUES (1,'System balance','system_balance','570.0'),(2,'System deposit out','system_deposit','2000.0'),(3,'System name','system_name','Retal System'),(4,'System meta description','system_meta_description','Lorem Ipsum is simply dummy text of the printing and typesetting industry.'),(5,'System logo URL','system_logo','https://picsum.photos/200'),(6,'System favicon URL','system_favicon','https://picsum.photos/200'),(7,'System Introduction','system_introduction','Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.'),(8,'System email','system_email','admin@carrental.com'),(9,'System base URL','system_url','https://localhost:8080'),(10,'System contact','system_contact','0123456789'),(11,'System address','system_address','Hoa Lac, Ha Noi'),(12,'System footer text','system_footer','Copyright © 2024 Retal Car System. All rights reserved.');
/*!40000 ALTER TABLE `system_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `amount` double NOT NULL,
  `deposit_request_id` int NOT NULL AUTO_INCREMENT,
  `member_id` int DEFAULT NULL,
  `request_time` datetime(6) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `status` enum('PENDING','APPROVED','REJECTED','AUTO') NOT NULL,
  `type` enum('AUTO_IN','AUTO_OUT','REQUEST_IN','REQUEST_OUT') NOT NULL,
  PRIMARY KEY (`deposit_request_id`),
  KEY `FK8g210cmyp5dyikvxf9ek6ub30` (`member_id`),
  CONSTRAINT `FK8g210cmyp5dyikvxf9ek6ub30` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1000,1,3,'2024-03-22 09:35:06.621280','Deposit into wallet balance','PENDING','REQUEST_IN'),(1000,2,2,'2024-03-22 09:37:51.650105','Deposit into wallet balance','APPROVED','REQUEST_IN'),(150,3,2,'2024-03-22 09:38:06.875062','Fee from booking 3','AUTO','AUTO_OUT'),(90,4,2,'2024-03-22 09:38:09.150438','Fee from booking 2','AUTO','AUTO_OUT'),(0,5,2,'2024-03-22 09:38:14.784137','Extra fee from booking 2','AUTO','AUTO_OUT'),(1000,6,3,'2024-03-22 09:41:26.674793','Deposit into wallet balance','APPROVED','REQUEST_IN'),(600,7,3,'2024-03-22 09:42:25.349987','Payment for booking 8','AUTO','AUTO_OUT'),(600,8,4,'2024-03-22 09:42:25.349987','Payment for booking 8','AUTO','AUTO_IN'),(60,9,4,'2024-03-22 09:42:25.357939','Fee from booking 8','AUTO','AUTO_OUT'),(1000,10,2,'2024-03-22 09:47:43.268636','Deposit into wallet balance','PENDING','REQUEST_IN'),(540,11,4,'2024-03-22 09:48:04.875491','Withdraw from wallet balance','PENDING','REQUEST_OUT'),(15,12,2,'2024-03-22 09:57:14.526805','Fee from booking 4','AUTO','AUTO_OUT'),(90,13,2,'2024-03-22 09:57:21.211720','Fee from booking 7','AUTO','AUTO_OUT'),(0,14,2,'2024-03-22 09:57:24.315742','Extra fee from booking 7','AUTO','AUTO_OUT'),(75,15,2,'2024-03-23 16:12:19.587441','Fee from booking 6','AUTO','AUTO_OUT'),(90,16,2,'2024-03-23 16:12:46.010409','Fee from booking 5','AUTO','AUTO_OUT');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wish_item`
--

DROP TABLE IF EXISTS `wish_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wish_item` (
  `car_id` int NOT NULL,
  `member_id` int NOT NULL,
  PRIMARY KEY (`car_id`,`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wish_item`
--

LOCK TABLES `wish_item` WRITE;
/*!40000 ALTER TABLE `wish_item` DISABLE KEYS */;
INSERT INTO `wish_item` VALUES (1,3),(2,3),(6,3),(9,3);
/*!40000 ALTER TABLE `wish_item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-23 16:52:13
