/*
utf8mb4 : 日本語・絵文字・記号すべて対応
例：INSERT INTO test (name) VALUES ('😄');　絵文字😄も登録できる

COLLATE utf8mb4_0900_ai_ci : MySQL 8.0 系のデフォルト
ai：アクセント無視
例：SELECT 'café' は SELECT 'cafe' と同じ扱いになる
ci：大文字小文字無視
例：SQLで SELECT は select と同じ扱いになる
*/
CREATE DATABASE t_user CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;