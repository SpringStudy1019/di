CREATE TABLE `follow` (
  `follow_idx` INT AUTO_INCREMENT NOT NULL,
  `followee_id` VARCHAR(20) NOT NULL,
  `follower_id` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`follow_idx`),
  FOREIGN KEY (`followee_id`) REFERENCES `USER` (`user_id`),
  FOREIGN KEY (`follower_id`) REFERENCES `USER` (`user_id`)
);

select * from follow;