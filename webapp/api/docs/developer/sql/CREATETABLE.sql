CREATE TABLE `api_db`.`t_user` (
    user_id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `api_db`.`t_password_reset_request` (
    user_id INT NOT NULL,
    token VARCHAR(36) NOT NULL,
    expiry_date DATETIME NOT NULL,
    is_used BOOLEAN NOT NULL DEFAULT FALSE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (token) , -- 同ユーザが複数回りクエストする可能性を考慮し、主キーはtoken
    CONSTRAINT fk_password_reset_user_id FOREIGN KEY (user_id)
        REFERENCES t_user(user_id)
);