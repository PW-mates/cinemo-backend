-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cinemo
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cinemo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cinemo` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `cinemo` ;

-- -----------------------------------------------------
-- Table `cinemo`.`movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemo`.`movie` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `director` VARCHAR(255) NULL DEFAULT NULL,
  `distributor` VARCHAR(255) NULL DEFAULT NULL,
  `duration` BIGINT NULL DEFAULT NULL,
  `poster_photo` VARCHAR(255) NULL DEFAULT NULL,
  `rating` BIGINT NULL DEFAULT NULL,
  `release_date` BIGINT NULL DEFAULT NULL,
  `showing_from` BIGINT NULL DEFAULT NULL,
  `showing_to` BIGINT NULL DEFAULT NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `trailer_url` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemo`.`movie_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemo`.`movie_category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `slug` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemo`.`movie_has_categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemo`.`movie_has_categories` (
  `movie_id` BIGINT NOT NULL,
  `movie_category_id` BIGINT NOT NULL,
  PRIMARY KEY (`movie_id`, `movie_category_id`),
  INDEX `FKhj5lj9jr3ko8l5txyhb2p4u9g` (`movie_category_id` ASC) VISIBLE,
  CONSTRAINT `FK6rpml6y51ljebwd28mdr6na6k`
    FOREIGN KEY (`movie_id`)
    REFERENCES `cinemo`.`movie` (`id`),
  CONSTRAINT `FKhj5lj9jr3ko8l5txyhb2p4u9g`
    FOREIGN KEY (`movie_category_id`)
    REFERENCES `cinemo`.`movie_category` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemo`.`payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemo`.`payment` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `account` BIGINT NULL DEFAULT NULL,
  `currency` VARCHAR(255) NULL DEFAULT NULL,
  `payment_method` VARCHAR(255) NULL DEFAULT NULL,
  `reference` VARCHAR(255) NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemo`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemo`.`role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemo`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemo`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `bio` VARCHAR(255) NULL DEFAULT NULL,
  `birth_date` BIGINT NULL DEFAULT NULL,
  `country` VARCHAR(255) NULL DEFAULT NULL,
  `created_at` BIGINT NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `first_name` VARCHAR(255) NULL DEFAULT NULL,
  `gender` VARCHAR(255) NULL DEFAULT NULL,
  `language` VARCHAR(255) NULL DEFAULT NULL,
  `last_name` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `personal_website` VARCHAR(255) NULL DEFAULT NULL,
  `phone` VARCHAR(255) NULL DEFAULT NULL,
  `profile_picture` VARCHAR(255) NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  `updated_at` BIGINT NULL DEFAULT NULL,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemo`.`theater`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemo`.`theater` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  `city` VARCHAR(255) NULL DEFAULT NULL,
  `country` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `latitude` FLOAT NOT NULL,
  `longitude` FLOAT NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `phone` VARCHAR(255) NULL DEFAULT NULL,
  `website` VARCHAR(255) NULL DEFAULT NULL,
  `manager_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKnixtebap8ufyg5wgvhjneoax0` (`manager_id` ASC) VISIBLE,
  CONSTRAINT `FKnixtebap8ufyg5wgvhjneoax0`
    FOREIGN KEY (`manager_id`)
    REFERENCES `cinemo`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemo`.`room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemo`.`room` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `number_of_rows` INT NOT NULL,
  `seats_count` INT NOT NULL,
  `seats_per_row` INT NOT NULL,
  `theater_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK4t0svqpm93ar7fqqp7399w17r` (`theater_id` ASC) VISIBLE,
  CONSTRAINT `FK4t0svqpm93ar7fqqp7399w17r`
    FOREIGN KEY (`theater_id`)
    REFERENCES `cinemo`.`theater` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemo`.`screening`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemo`.`screening` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `date` BIGINT NULL DEFAULT NULL,
  `open_sale` BIGINT NULL DEFAULT NULL,
  `movie_id` BIGINT NULL DEFAULT NULL,
  `room_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKrghid578c0o08s562sn4m27jm` (`movie_id` ASC) VISIBLE,
  INDEX `FK6n31slq86oxleenqku7qa227i` (`room_id` ASC) VISIBLE,
  CONSTRAINT `FK6n31slq86oxleenqku7qa227i`
    FOREIGN KEY (`room_id`)
    REFERENCES `cinemo`.`room` (`id`),
  CONSTRAINT `FKrghid578c0o08s562sn4m27jm`
    FOREIGN KEY (`movie_id`)
    REFERENCES `cinemo`.`movie` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemo`.`seat_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemo`.`seat_type` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemo`.`seat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemo`.`seat` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `column` INT NOT NULL,
  `row` INT NOT NULL,
  `room_id` BIGINT NULL DEFAULT NULL,
  `type_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKrlwwa3qrr0wvtyh4e0kq69712` (`room_id` ASC) VISIBLE,
  INDEX `FKkpvintfu95xbx7wcgv1wewhei` (`type_id` ASC) VISIBLE,
  CONSTRAINT `FKkpvintfu95xbx7wcgv1wewhei`
    FOREIGN KEY (`type_id`)
    REFERENCES `cinemo`.`seat_type` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `FKrlwwa3qrr0wvtyh4e0kq69712`
    FOREIGN KEY (`room_id`)
    REFERENCES `cinemo`.`room` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemo`.`ticket_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemo`.`ticket_type` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `price` FLOAT NOT NULL,
  `seat_type_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKpxuhpn3vvv1usrm8316y583r0` (`seat_type_id` ASC) VISIBLE,
  CONSTRAINT `FKpxuhpn3vvv1usrm8316y583r0`
    FOREIGN KEY (`seat_type_id`)
    REFERENCES `cinemo`.`seat_type` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemo`.`ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemo`.`ticket` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(255) NULL DEFAULT NULL,
  `create_at` BIGINT NULL DEFAULT NULL,
  `payment_method` VARCHAR(255) NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  `total_price` FLOAT NOT NULL,
  `payment_id` BIGINT NULL DEFAULT NULL,
  `screening_id` BIGINT NULL DEFAULT NULL,
  `seller_id` BIGINT NULL DEFAULT NULL,
  `ticket_type_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK9bay9a8ghms1wubqg2fb4qcfr` (`payment_id` ASC) VISIBLE,
  INDEX `FKdfxu6q43et1c453xgywrft941` (`screening_id` ASC) VISIBLE,
  INDEX `FKnt30vhmxmji7f0ud25xd2wof7` (`seller_id` ASC) VISIBLE,
  INDEX `FKrl9y7t6i3fw8r5e10kc24gqe9` (`ticket_type_id` ASC) VISIBLE,
  CONSTRAINT `FK9bay9a8ghms1wubqg2fb4qcfr`
    FOREIGN KEY (`payment_id`)
    REFERENCES `cinemo`.`payment` (`id`),
  CONSTRAINT `FKdfxu6q43et1c453xgywrft941`
    FOREIGN KEY (`screening_id`)
    REFERENCES `cinemo`.`screening` (`id`),
  CONSTRAINT `FKnt30vhmxmji7f0ud25xd2wof7`
    FOREIGN KEY (`seller_id`)
    REFERENCES `cinemo`.`user` (`id`),
  CONSTRAINT `FKrl9y7t6i3fw8r5e10kc24gqe9`
    FOREIGN KEY (`ticket_type_id`)
    REFERENCES `cinemo`.`ticket_type` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemo`.`ticket_seats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemo`.`ticket_seats` (
  `ticket_id` BIGINT NOT NULL,
  `seats_id` BIGINT NOT NULL,
  PRIMARY KEY (`ticket_id`, `seats_id`),
  INDEX `FK922i36xd0lu82hf2r5hvd72c7` (`seats_id` ASC) VISIBLE,
  CONSTRAINT `FK922i36xd0lu82hf2r5hvd72c7`
    FOREIGN KEY (`seats_id`)
    REFERENCES `cinemo`.`seat` (`id`),
  CONSTRAINT `FKyyy80of962k3bdcbcxdb4inm`
    FOREIGN KEY (`ticket_id`)
    REFERENCES `cinemo`.`ticket` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemo`.`user_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemo`.`user_roles` (
  `user_id` BIGINT NOT NULL,
  `roles_id` BIGINT NOT NULL,
  INDEX `FKo2rmqkw5pucpp44p39quu5al5` (`roles_id` ASC) VISIBLE,
  INDEX `FK40cm955hgg5oxf1oax8mqw0c4` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK40cm955hgg5oxf1oax8mqw0c4`
    FOREIGN KEY (`user_id`)
    REFERENCES `cinemo`.`user` (`id`),
  CONSTRAINT `FKo2rmqkw5pucpp44p39quu5al5`
    FOREIGN KEY (`roles_id`)
    REFERENCES `cinemo`.`role` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
