SET SQL_SAFE_UPDATES = 1;
UPDATE `対象DB名`.`対象TABLE名`
SET
    `対象Field名1` = '登録値1',
    `対象Field名2` = '登録値2'
/*条件*/
WHERE `主キーにするフィールド名` = 'フィールドに登録されている値';
