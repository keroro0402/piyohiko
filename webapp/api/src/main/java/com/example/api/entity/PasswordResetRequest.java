package com.example.api.entity;

import java.time.LocalDateTime;

/*
* レコードクラス
* 「データを運ぶことだけ」に特化した特別なクラス
* Lombok（@Data や @Getter）不要で同様の操作が保証される
* 自動的にすべて final になる
* */
public record PasswordResetRequest(Integer userId, String secretCode, LocalDateTime expiryDate) {
}
