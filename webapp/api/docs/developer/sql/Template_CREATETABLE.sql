CREATE TABLE `対象DB名`.`対象TABLE名` (
    `INT型、NOTNULL、自動採番で登録するフィールド名` INT NOT NULL AUTO_INCREMENT,
    `INT型、NOTNULLで登録するフィールド名` INT NOT NULL,
    `VARCHAR型、NOTNULLで登録する短文用フィールド名` VARCHAR(255) NOT NULL,
    `TEXT型、長文用のフィールド名` TEXT,
    `DATE型、日付用のフィールド名` DATE,
    `DATETIME型、日時用のフィールド名` DATETIME,
    `DECIMAL型、小数用のフィールド名` DECIMAL(10,2),
    `FLOAT型、小数用のフィールド名` FLOAT,
    `BOOLEAN型、フラグ用のフィールド名` TINYINT(1),
    `CHAR型、固定文字列用のフィールド名` CHAR(10),
    PRIMARY KEY (主キーにするフィールド名)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
