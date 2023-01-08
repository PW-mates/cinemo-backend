--liquibase formatted sql

--changeset Giang:1

-- -----------------------------------------------------
-- Table `movie`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movie` (
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
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `movie_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movie_category` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `slug` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `movie_has_categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `movie_has_categories` (
  `movie_id` BIGINT NOT NULL,
  `movie_category_id` BIGINT NOT NULL,
  PRIMARY KEY (`movie_id`, `movie_category_id`),
    FOREIGN KEY (`movie_id`)
    REFERENCES `movie` (`id`),
    FOREIGN KEY (`movie_category_id`)
    REFERENCES `movie_category` (`id`));


-- -----------------------------------------------------
-- Table `payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `payment` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `amount` FLOAT NULL DEFAULT NULL,
  `currency` VARCHAR(255) NULL DEFAULT NULL,
  `payment_method` VARCHAR(255) NULL DEFAULT NULL,
  `reference` VARCHAR(255) NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
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
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `theater`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `theater` (
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
    FOREIGN KEY (`manager_id`)
    REFERENCES `user` (`id`));


-- -----------------------------------------------------
-- Table `room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `room` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `number_of_rows` INT NOT NULL,
  `seats_count` INT NOT NULL,
  `seats_per_row` INT NOT NULL,
  `theater_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
    FOREIGN KEY (`theater_id`)
    REFERENCES `theater` (`id`)
    ON DELETE CASCADE);


-- -----------------------------------------------------
-- Table `screening`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `screening` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `date` BIGINT NULL DEFAULT NULL,
  `open_sale` BIGINT NULL DEFAULT NULL,
  `movie_id` BIGINT NULL DEFAULT NULL,
  `room_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
    FOREIGN KEY (`room_id`)
    REFERENCES `room` (`id`),
    FOREIGN KEY (`movie_id`)
    REFERENCES `movie` (`id`));


-- -----------------------------------------------------
-- Table `seat_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `seat_type` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `color` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `seat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `seat` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `column` INT NOT NULL,
  `row` INT NOT NULL,
  `room_id` BIGINT NULL DEFAULT NULL,
  `type_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
    FOREIGN KEY (`type_id`)
    REFERENCES `seat_type` (`id`)
    ON DELETE CASCADE,
    FOREIGN KEY (`room_id`)
    REFERENCES `room` (`id`)
    ON DELETE CASCADE);


-- -----------------------------------------------------
-- Table `ticket_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ticket_type` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `price` FLOAT NOT NULL,
  `seat_type_id` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
    FOREIGN KEY (`seat_type_id`)
    REFERENCES `seat_type` (`id`));


-- -----------------------------------------------------
-- Table `ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ticket` (
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
    FOREIGN KEY (`payment_id`)
    REFERENCES `payment` (`id`),
    FOREIGN KEY (`screening_id`)
    REFERENCES `screening` (`id`),
    FOREIGN KEY (`seller_id`)
    REFERENCES `user` (`id`),
    FOREIGN KEY (`ticket_type_id`)
    REFERENCES `ticket_type` (`id`));


-- -----------------------------------------------------
-- Table `ticket_seats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ticket_seats` (
  `ticket_id` BIGINT NOT NULL,
  `seats_id` BIGINT NOT NULL,
  PRIMARY KEY (`ticket_id`, `seats_id`),
    FOREIGN KEY (`seats_id`)
    REFERENCES `seat` (`id`),
    FOREIGN KEY (`ticket_id`)
    REFERENCES `ticket` (`id`));


-- -----------------------------------------------------
-- Table `user_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_id` BIGINT NOT NULL,
  `roles_id` BIGINT NOT NULL,
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`),
    FOREIGN KEY (`roles_id`)
    REFERENCES `role` (`id`));
