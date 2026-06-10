CREATE TABLE `api_db`.`t_user` (
    user_id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `api_db`.`t_password_reset_request` (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    secret_code VARCHAR(3) NOT NULL, -- 012など0始まりを弾くためにVARCHAR
    expiry_date DATETIME NOT NULL,
    is_used BOOLEAN NOT NULL DEFAULT FALSE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id) , -- 同じユーザが複数回リクエストしてsecret_codeが重複する可能性を考慮し、主キー（サロゲートキー）にidを指定
    CONSTRAINT fk_password_reset_user_id FOREIGN KEY (user_id)
        REFERENCES `api_db`.`t_user`(user_id)
);