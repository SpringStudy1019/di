CREATE TABLE `file_info` (
  `file_idx` INT AUTO_INCREMENT NOT NULL,
  `save_folder` VARCHAR(45) NULL,
  `original_file` VARCHAR(50) NULL,
  `save_file` VARCHAR(100) NULL,
  `review_idx` INT NOT NULL,
  PRIMARY KEY (`file_idx`),
  FOREIGN KEY (`review_idx`) REFERENCES `review` (`review_idx`)
);
