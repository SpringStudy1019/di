CREATE TABLE bookmark (
    bookmark_idx INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(20) NOT NULL,
    content_id INT NOT NULL,
    register_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (content_id) REFERENCES attraction_info(content_id)
);