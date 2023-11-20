use enjoytrip;
show tables;

CREATE TABLE user (
    user_id VARCHAR(20) PRIMARY KEY,
    user_name VARCHAR(5) NOT NULL,
    password VARCHAR(100) NOT NULL,
    mobile VARCHAR(11) NOT NULL,
    email_id VARCHAR(20) NOT NULL,
    email_domain VARCHAR(20) NOT NULL,
    gender ENUM('MALE', 'FEMALE', 'UNDEFINED') NOT NULL,
    authority ENUM('ROLE_USER', 'ROLE_ADMIN') NOT NULL,
    join_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    social_id VARCHAR(20),
    img_url VARCHAR(200) default 'https://instagramimages16.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B3%B8+%ED%94%84%EC%82%AC.jfif'
);
