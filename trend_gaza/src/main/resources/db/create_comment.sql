use enjoytrip;
show tables;

CREATE TABLE comment (
    comment_idx INT PRIMARY KEY auto_increment,
    content VARCHAR(1000) NOT NULL,
    register_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modify_date TIMESTAMP,
    user_id VARCHAR(20) NOT NULL,
    review_idx int NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (review_idx) REFERENCES review(review_idx)
);

select * from comment;