-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema assignment2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema assignment2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `assignment2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `assignment2` ;

-- -----------------------------------------------------
-- Table `assignment2`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assignment2`.`category` (
  `id` INT NOT NULL,
  `category` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `assignment2`.`menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assignment2`.`menu` (
  `id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `assignment2`.`food`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assignment2`.`food` (
  `id` INT NOT NULL,
  `food` VARCHAR(255) NULL DEFAULT NULL,
  `list_of_ingredients` VARCHAR(255) NULL DEFAULT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `id_category` INT NULL DEFAULT NULL,
  `id_menu` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKo4igwdepmk6gaqrl8idiutweg` (`id_category` ASC) VISIBLE,
  INDEX `FK4ixtpdr5urpwu0mrol9k41uwe` (`id_menu` ASC) VISIBLE,
  CONSTRAINT `FK4ixtpdr5urpwu0mrol9k41uwe`
    FOREIGN KEY (`id_menu`)
    REFERENCES `assignment2`.`menu` (`id`),
  CONSTRAINT `FKo4igwdepmk6gaqrl8idiutweg`
    FOREIGN KEY (`id_category`)
    REFERENCES `assignment2`.`category` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `assignment2`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assignment2`.`user` (
  `dtype` VARCHAR(31) NOT NULL,
  `id` INT NOT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `assignment2`.`restaurant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assignment2`.`restaurant` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `id_menu` INT NULL DEFAULT NULL,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  `id_admin` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKo467fehvnber9hnynm2v7qaof` (`id_menu` ASC) VISIBLE,
  INDEX `FK362mmk3fackjp8gy8073swtmo` (`id_admin` ASC) VISIBLE,
  CONSTRAINT `FK362mmk3fackjp8gy8073swtmo`
    FOREIGN KEY (`id_admin`)
    REFERENCES `assignment2`.`user` (`id`),
  CONSTRAINT `FKo467fehvnber9hnynm2v7qaof`
    FOREIGN KEY (`id_menu`)
    REFERENCES `assignment2`.`menu` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `assignment2`.`order_`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assignment2`.`order_` (
  `id` INT NOT NULL,
  `id_status` VARCHAR(255) NULL DEFAULT NULL,
  `id_customer` INT NULL DEFAULT NULL,
  `id_restaurant` INT NULL DEFAULT NULL,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKmnbkohv4wsu6vd7qa0ctkbwm1` (`id_customer` ASC) VISIBLE,
  INDEX `FK6m1ab0wvqrmf7s5eywodbh34b` (`id_restaurant` ASC) VISIBLE,
  CONSTRAINT `FK6m1ab0wvqrmf7s5eywodbh34b`
    FOREIGN KEY (`id_restaurant`)
    REFERENCES `assignment2`.`restaurant` (`id`),
  CONSTRAINT `FKmnbkohv4wsu6vd7qa0ctkbwm1`
    FOREIGN KEY (`id_customer`)
    REFERENCES `assignment2`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `assignment2`.`orderdetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assignment2`.`orderdetails` (
  `id` INT NOT NULL,
  `quantity` INT NULL DEFAULT NULL,
  `id_order` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK5clvx1flwviiibon9sy38amrj` (`id_order` ASC) VISIBLE,
  CONSTRAINT `FK5clvx1flwviiibon9sy38amrj`
    FOREIGN KEY (`id_order`)
    REFERENCES `assignment2`.`order_` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `assignment2`.`food_order_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assignment2`.`food_order_details` (
  `id_food` INT NOT NULL,
  `id_order_details` INT NOT NULL,
  INDEX `FKd2blr7vltpmbv33k1imtmic19` (`id_order_details` ASC) VISIBLE,
  INDEX `FKdn6elvluypyt0jtsfb4xr7ou5` (`id_food` ASC) VISIBLE,
  CONSTRAINT `FKd2blr7vltpmbv33k1imtmic19`
    FOREIGN KEY (`id_order_details`)
    REFERENCES `assignment2`.`orderdetails` (`id`),
  CONSTRAINT `FKdn6elvluypyt0jtsfb4xr7ou5`
    FOREIGN KEY (`id_food`)
    REFERENCES `assignment2`.`food` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `assignment2`.`hibernate_sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assignment2`.`hibernate_sequence` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `assignment2`.`zone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assignment2`.`zone` (
  `id` INT NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `assignment2`.`restaurant_zone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `assignment2`.`restaurant_zone` (
  `id_restaurant` INT NOT NULL,
  `id_zone` INT NOT NULL,
  INDEX `FK8vhgrx5wlyiajfrvdlw75r67m` (`id_zone` ASC) VISIBLE,
  INDEX `FK4k7rtqbmwp8ifhou7hug36yf7` (`id_restaurant` ASC) VISIBLE,
  CONSTRAINT `FK4k7rtqbmwp8ifhou7hug36yf7`
    FOREIGN KEY (`id_restaurant`)
    REFERENCES `assignment2`.`restaurant` (`id`),
  CONSTRAINT `FK8vhgrx5wlyiajfrvdlw75r67m`
    FOREIGN KEY (`id_zone`)
    REFERENCES `assignment2`.`zone` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
