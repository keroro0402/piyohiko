CREATE TABLE `api_db`.`t_user` (
    user_id INT NOT NULL AUTO_INCREMENT,
    login_id VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
