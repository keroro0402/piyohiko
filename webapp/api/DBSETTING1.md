# DB 環境構築

## 1. MySQL の確認

MySQL クライアントがインストールされていることを確認する

### 手順
1. ターミナルを開く
2. 以下のコマンドを実行する
````
mysql --version
````
### 達成条件
ターミナルに MySQL のバージョン情報が出ること（以下例）
````
mysql  Ver 9.1.0 -snip-
````

## 2. MySQL サーバへのログイン確認

MySQL がサーバとして起動しており、実際に操作できる状態かを確認する

### 手順
1. 以下のコマンドを実行する
````
mysql -u root -p
````
2. パスワードを入力する

### 達成条件
ログインが成功し、ターミナルが以下の形になること
````
mysql> 
````

## 3. DB 作成・確認（DDL）

DB を作成し、作成した DB の存在を確認する

### 手順
1. MySQL にログイン後、以下のコマンドで DB を作成する
````
CREATE DATABASE topic_db CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
````
2. 以下のコマンドで、作成した DB の存在を確認する
````
SHOW DATABASES;
````

### 達成条件
表示された DB 一覧に作成した DB が存在すること
````
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sys                |
| test               |
| topic_db           | 　<-- 作成したDB
+--------------------+
````

## 4. DBユーザ 作成・権限付与（DDL）

DBユーザ を作成し、作成した DBユーザ に権限を付与する

### 手順
1. MySQL にログイン後、以下のコマンドで DBユーザ を作成する
````
CREATE USER IF NOT EXISTS topic_user IDENTIFIED BY 'topic';
````
2. 以下のコマンドで、作成した DBユーザ に権限を付与する
````
GRANT ALL PRIVILEGES ON topic_db.* TO 'topic_user';
````

### 達成条件
以下コマンドで、作成した DBユーザ で 作成したDB にログインできること
````
mysql -u topic_user -p topic_db
````
ログインした後は、ターミナルが以下の形になること
````
mysql> 
````

## 5. テーブル 作成（DDL）

テーブル を作成する

### 手順
1. MySQL に 作成した DBユーザ でログイン後、以下のコマンドで DB を選択する
````
USE topic_db
````
2. 以下コマンドで テーブル を作成する
````
/* トピックマスタ作成 */
CREATE TABLE m_category (
    category_id INT         NOT NULL AUTO_INCREMENT,
    category_title  VARCHAR(20) NOT NULL,
    CHECK (CHAR_LENGTH(category_title) >= 1),
    catch_phrase    VARCHAR(50) NOT NULL,
    CHECK (CHAR_LENGTH(catch_phrase) >= 1),
    PRIMARY KEY(category_id)
    );
/* トピックテーブル作成 */
CREATE TABLE t_topic (
    registration_id INT         NOT NULL AUTO_INCREMENT,
    category_id     INT         NOT NULL,
    user_id         VARCHAR(10) NOT NULL,
    CHECK (CHAR_LENGTH(user_id) >= 4),
    register_date     DATE      NOT NULL,
    topic_title     VARCHAR(50) NOT NULL,
    CHECK (CHAR_LENGTH(topic_title) >= 1),
    topic_content   TEXT        NOT NULL,
    PRIMARY KEY(registration_id),
    FOREIGN KEY(category_id) REFERENCES m_category (category_id)
    );
 ````
3. 以下のコマンドで、作成した テーブル の存在を確認する
````
SHOW TABLES;
````

### 達成条件
表示された テーブル 一覧に作成した テーブル が存在すること
````
+--------------------+
| Tables_in_topic_db |
+--------------------+
| m_topic            |　<-- 作成したテーブル
| t_topic            |　<-- 作成したテーブル
+--------------------+
````

## 6. データ 登録（DML）

テーブル に データ を登録する

### 手順
1. MySQL に 作成した DBユーザ でログイン後、以下のコマンドで DB を選択する
````
USE topic_db
````
2. 以下コマンドで テーブル を作成する
````
/* m_topic にデータ登録 */
INSERT INTO m_topic
 ````
3. 以下のコマンドで、作成した テーブル の存在を確認する
````
SHOW TABLES;
````

### 達成条件
表示された テーブル 一覧に作成した テーブル が存在すること
````
+--------------------+
| Tables_in_topic_db |
+--------------------+
| m_topic            |　<-- 作成したテーブル
| t_topic            |　<-- 作成したテーブル
+--------------------+
````