use enjoytrip;
show tables;

CREATE TABLE likes (
    like_idx INT PRIMARY KEY AUTO_INCREMENT,
    review_idx INT NOT NULL,
    user_id VARCHAR(20) NOT NULL,
    FOREIGN KEY (review_idx) REFERENCES review(review_idx),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

select * from likes;