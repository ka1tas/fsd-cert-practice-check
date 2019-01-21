SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `signupv1` ;
CREATE SCHEMA IF NOT EXISTS `signupv1` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `signupv1` ;

-- -----------------------------------------------------
-- Table `signupv1`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `signupv1`.`user` ;

CREATE  TABLE IF NOT EXISTS `signupv1`.`user` (
  `us_id` INT NOT NULL AUTO_INCREMENT ,
  `us_name` VARCHAR(45) NULL ,
  `us_email` VARCHAR(45) NULL ,
  `us_password` VARCHAR(45) NULL ,
  PRIMARY KEY (`us_id`) ,
  UNIQUE INDEX `us_email_UNIQUE` (`us_email` ASC) )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
