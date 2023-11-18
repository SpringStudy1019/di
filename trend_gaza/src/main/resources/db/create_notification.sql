CREATE TABLE notification (
    notification_idx INT PRIMARY KEY AUTO_INCREMENT,
    message VARCHAR(50) NOT NULL,
    push_date TIMESTAMP NOT NULL,
    push_status ENUM('COMPLETE', 'INCOMPLETE'),
    push_case ENUM('COMMENT', 'LIKE', 'SYSTEM', 'PERSONAL'),
    path_id VARCHAR(100),
    user_id_from VARCHAR(20) NOT NULL,
    user_id_to VARCHAR(20) NOT NULL,
    FOREIGN KEY (user_id_from) REFERENCES user(user_id),
    FOREIGN KEY (user_id_to) REFERENCES user(user_id)
);
