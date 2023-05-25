-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema communtrip
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema communtrip
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `communtrip` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `communtrip` ;

-- -----------------------------------------------------
-- Table `communtrip`.`sido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `communtrip`.`sido` ;

CREATE TABLE IF NOT EXISTS `communtrip`.`sido` (
  `sido_code` INT NOT NULL,
  `sido_name` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `communtrip`.`gugun`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `communtrip`.`gugun` ;

CREATE TABLE IF NOT EXISTS `communtrip`.`gugun` (
  `gugun_code` INT NOT NULL,
  `gugun_name` VARCHAR(30) NULL DEFAULT NULL,
  `sido_code` INT NOT NULL,
  PRIMARY KEY (`gugun_code`, `sido_code`),
  INDEX `gugun_to_sido_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
  CONSTRAINT `gugun_to_sido_sido_code_fk`
    FOREIGN KEY (`sido_code`)
    REFERENCES `communtrip`.`sido` (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `communtrip`.`attraction_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `communtrip`.`attraction_info` ;

CREATE TABLE IF NOT EXISTS `communtrip`.`attraction_info` (
  `content_id` INT NOT NULL,
  `content_type_id` INT NULL DEFAULT NULL,
  `title` VARCHAR(100) NULL DEFAULT NULL,
  `addr1` VARCHAR(100) NULL DEFAULT NULL,
  `addr2` VARCHAR(50) NULL DEFAULT NULL,
  `zipcode` VARCHAR(50) NULL DEFAULT NULL,
  `tel` VARCHAR(50) NULL DEFAULT NULL,
  `first_image` VARCHAR(200) NULL DEFAULT NULL,
  `first_image2` VARCHAR(200) NULL DEFAULT NULL,
  `readcount` INT NULL DEFAULT NULL,
  `sido_code` INT NULL DEFAULT NULL,
  `gugun_code` INT NULL DEFAULT NULL,
  `latitude` DECIMAL(20,17) NULL DEFAULT NULL,
  `longitude` DECIMAL(20,17) NULL DEFAULT NULL,
  `mlevel` VARCHAR(2) NULL DEFAULT NULL,
  `hit` INT NOT NULL,
  PRIMARY KEY (`content_id`),
  INDEX `attraction_to_content_type_id_fk_idx` (`content_type_id` ASC) VISIBLE,
  INDEX `attraction_to_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
  INDEX `attraction_to_gugun_code_fk_idx` (`gugun_code` ASC) VISIBLE,
  CONSTRAINT `attraction_to_content_type_id_fk`
    FOREIGN KEY (`content_type_id`)
    REFERENCES `communtrip`.`content_type` (`content_type_id`),
  CONSTRAINT `attraction_to_gugun_code_fk`
    FOREIGN KEY (`gugun_code`)
    REFERENCES `communtrip`.`gugun` (`gugun_code`),
  CONSTRAINT `attraction_to_sido_code_fk`
    FOREIGN KEY (`sido_code`)
    REFERENCES `communtrip`.`sido` (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `communtrip`.`attraction_description`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `communtrip`.`attraction_description` ;

CREATE TABLE IF NOT EXISTS `communtrip`.`attraction_description` (
  `content_id` INT NOT NULL,
  `homepage` VARCHAR(100) NULL DEFAULT NULL,
  `overview` VARCHAR(10000) NULL DEFAULT NULL,
  `telname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_attraciton_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `communtrip`.`attraction_info` (`content_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `communtrip`.`attraction_detail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `communtrip`.`attraction_detail` ;

CREATE TABLE IF NOT EXISTS `communtrip`.`attraction_detail` (
  `content_id` INT NOT NULL,
  `cat1` VARCHAR(3) NULL DEFAULT NULL,
  `cat2` VARCHAR(5) NULL DEFAULT NULL,
  `cat3` VARCHAR(9) NULL DEFAULT NULL,
  `created_time` VARCHAR(14) NULL DEFAULT NULL,
  `modified_time` VARCHAR(14) NULL DEFAULT NULL,
  `booktour` VARCHAR(5) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_basic_content_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `communtrip`.`attraction_info` (`content_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `communtrip`.`board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `communtrip`.`board` ;

CREATE TABLE IF NOT EXISTS `communtrip`.`board` (
  `bno` INT NOT NULL AUTO_INCREMENT,
  `writer` VARCHAR(30) NOT NULL,
  `subject` VARCHAR(30) NOT NULL,
  `content` TEXT NULL DEFAULT NULL,
  `registerTime` DATETIME NOT NULL,
  `boardType` INT NOT NULL,
  `tradeState` VARCHAR(10) NULL DEFAULT NULL,
  `tradeName` VARCHAR(50) NULL DEFAULT NULL,
  `tradePrice` INT NULL DEFAULT NULL,
  `personnelCount` INT NULL DEFAULT NULL,
  `totalPerson` INT NULL DEFAULT NULL,
  PRIMARY KEY (`bno`))
ENGINE = InnoDB
AUTO_INCREMENT = 50
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `communtrip`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `communtrip`.`user` ;

CREATE TABLE IF NOT EXISTS `communtrip`.`user` (
  `id` VARCHAR(20) NOT NULL,
  `pw` VARCHAR(20) NOT NULL,
  `name` VARCHAR(30) NOT NULL,
  `age` INT NOT NULL,
  `local` VARCHAR(30) NOT NULL,
  `token` VARCHAR(1000) NULL DEFAULT NULL,
  `type` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `communtrip`.`comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `communtrip`.`comment` ;

CREATE TABLE IF NOT EXISTS `communtrip`.`comment` (
  `cno` INT NOT NULL AUTO_INCREMENT,
  `bno` INT NOT NULL,
  `id` VARCHAR(45) NOT NULL,
  `content` TEXT NOT NULL,
  `regist_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`cno`),
  INDEX `user_to_comment_id_fk_idx` (`id` ASC) VISIBLE,
  INDEX `board_to_comment_bno_fk_idx` (`bno` ASC) VISIBLE,
  CONSTRAINT `comment_ibfk_1`
    FOREIGN KEY (`bno`)
    REFERENCES `communtrip`.`board` (`bno`)
    ON DELETE CASCADE,
  CONSTRAINT `user_to_comment_id_fk`
    FOREIGN KEY (`id`)
    REFERENCES `communtrip`.`user` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 26
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `communtrip`.`detailcomment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `communtrip`.`detailcomment` ;

CREATE TABLE IF NOT EXISTS `communtrip`.`detailcomment` (
  `dno` INT NOT NULL AUTO_INCREMENT,
  `content_id` INT NOT NULL,
  `id` VARCHAR(45) NOT NULL,
  `content` TEXT NOT NULL,
  `registerTime` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`dno`),
  INDEX `attr_to_detailc_content_id_fk_idx` (`content_id` ASC) VISIBLE,
  INDEX `user_to_detailc_id_fk_idx` (`id` ASC) VISIBLE,
  CONSTRAINT `attr_to_detailc_content_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `communtrip`.`attraction_info` (`content_id`),
  CONSTRAINT `user_to_detailc_id_fk`
    FOREIGN KEY (`id`)
    REFERENCES `communtrip`.`user` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `communtrip`.`jjim`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `communtrip`.`jjim` ;

CREATE TABLE IF NOT EXISTS `communtrip`.`jjim` (
  `jno` INT NOT NULL AUTO_INCREMENT,
  `id` VARCHAR(45) NOT NULL,
  `content_id` INT NOT NULL,
  PRIMARY KEY (`jno`),
  INDEX `user_to_comment_id_fk_idx` (`id` ASC) VISIBLE,
  INDEX `attr_to_jjim_content_id_fk_idx` (`content_id` ASC) VISIBLE,
  CONSTRAINT `attr_to_jjim_content_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `communtrip`.`attraction_info` (`content_id`),
  CONSTRAINT `jjim_ibfk_1`
    FOREIGN KEY (`id`)
    REFERENCES `communtrip`.`user` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `communtrip`.`prefertype`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `communtrip`.`prefertype` ;

CREATE TABLE IF NOT EXISTS `communtrip`.`prefertype` (
  `type` VARCHAR(50) NOT NULL,
  `typename` VARCHAR(20) NOT NULL,
  `image` VARCHAR(200) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `addr1` VARCHAR(100) NOT NULL,
  `latitude` DECIMAL(20,17) NULL DEFAULT NULL,
  `longitude` DECIMAL(20,17) NULL DEFAULT NULL,
  PRIMARY KEY (`title`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `communtrip`.`tradealert`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `communtrip`.`tradealert` ;

CREATE TABLE IF NOT EXISTS `communtrip`.`tradealert` (
  `tno` INT NOT NULL AUTO_INCREMENT,
  `bno` INT NOT NULL,
  `id` VARCHAR(30) NOT NULL,
  `content` VARCHAR(50) NOT NULL,
  `registerTime` DATETIME NULL DEFAULT NULL,
  `writer` VARCHAR(30) NULL DEFAULT NULL,
  `type` INT NULL DEFAULT NULL,
  PRIMARY KEY (`tno`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
