package com.example.api.form;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
/*  formクラス作成
* クラス = どういうデータが入るかを定義した設計図
* TopicRegistrationForm では
* registrationId は Integer　型
* userId　は String 型で受け取ることなど定義している
*  */

/*
* クラス名 = ファイル名
* publicクラスは1ファイルに1つしか書けない
* */
public class TopicRegistrationForm {
    private Integer registrationId;
    private String userId;
    private LocalDate visitDate;
    private String topicTitle;
    private String topicContent;
}
