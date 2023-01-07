--liquibase formatted sql

--comment: Add basic about theater, rooms, seats

--changeset Giang:3

INSERT INTO theater VALUES
(1, 'Ludwika Warynskiego 12', 'Warsaw', 'PL', 'cinemo.pl@gmail.com', 52.21627, 21.0162, 'Zlote trasy', '+48 777 333 444', 'cinemo.pl', 1);

INSERT INTO `room` VALUES (1,'Room 1',5,25,5,1),(2,'Room 2',5,25,5,1);

INSERT INTO `seat` VALUES
(1,1,1,1,1),(2,2,1,1,1),(3,3,1,1,1),(4,4,1,1,1),(5,5,1,1,1),
(6,1,2,1,1),(7,2,2,1,1),(8,3,2,1,1),(9,4,2,1,1),(10,5,2,1,1),
(11,1,3,1,1),(12,2,3,1,1),(13,3,3,1,1),(14,4,3,1,1),(15,5,3,1,1),
(16,1,4,1,1),(17,2,4,1,1),(18,3,4,1,1),(19,4,4,1,1),(20,5,4,1,1),
(21,1,5,1,1),(22,2,5,1,1),(23,3,5,1,1),(24,4,5,1,1),(25,5,5,1,1),
(26,1,1,2,1),(27,2,1,2,1),(28,3,1,2,1),(29,4,1,2,1),(30,5,1,2,1),
(31,1,2,2,1),(32,2,2,2,1),(33,3,2,2,1),(34,4,2,2,1),(35,5,2,2,1),
(36,1,3,2,1),(37,2,3,2,1),(38,3,3,2,1),(39,4,3,2,1),(40,5,3,2,1),
(41,1,4,2,1),(42,2,4,2,1),(43,3,4,2,1),(44,4,4,2,1),(45,5,4,2,1),
(46,1,5,2,1),(47,2,5,2,1),(48,3,5,2,1),(49,4,5,2,1),(50,5,5,2,1);