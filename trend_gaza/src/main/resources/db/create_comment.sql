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
    FOREIGN KEY (review_idx) REFERENCES review(review_idx) ON DELETE CASCADE
);

select * from comment;

/*
ALTER TABLE comment
DROP FOREIGN KEY comment_ibfk_2;  -- 제약 조건명 (에러 로그에서 확인함)

ALTER TABLE comment
ADD CONSTRAINT comment_ibfk_2
FOREIGN KEY (review_idx) REFERENCES review(review_idx) ON DELETE CASCADE;
*/