package com.example.api.form;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
/* formクラス作成
* クラス = どういうデータが入るかを定義した設計図
* TopicRegistrationForm では
* registrationId は Integer　型
* userId　は String 型で受け取ることなど定義している
* */

/*
* クラス名 = ファイル名
* publicクラスは1ファイルに1つしか書けない
* */
public class TopicRegistrationForm {
    /* @NotNull
    * 必須入力チェック（Integerの時は@NotNullでNullのみチェックすればOK。
    * （文字列として入力されたものを Integer に変換するときに型変換エラーになるため）
    * */
    @NotNull(message = "入力してください")
    /* @Min
    * チェック対象の型が数値型の時に最小をチェックするときに使う
    * */
    @Min(value = 10000, message = "5桁の正の整数を入力してください") /* 5桁の整数の最小値 = 10000以上の整数 をチェック */
    /* @Max
     * チェック対象の型が数値型の時に最大をチェックするときに使う
     * */
    @Max(value = 99999, message = "5桁の正の整数を入力してください") /* 5桁の整数の最大値 = 99999以下の整数 をチェック */
    private Integer registrationId;
    /* @Pattern
    * 正規表現をつかってチェックするときに使う
    * */
    @Pattern(regexp = "\\S{4,10}", message = "4から10文字で入力してください（スペース不可）")
    private String userId;
    @NotNull(message = "入力してください")
    /* @Past
    * 現在より前の日付になっているかをチェックする
    * */
    @Past(message = "今日以前の日付を選択してください")
    private LocalDate visitDate;
    /* @Size
     * スペースを含めたmin ~ max 間の文字数（桁数）をチェックするときに使う
     * */
    @Size(min = 1, max = 50, message = "1から50文字で入力してください（スペース可）")
    private String topicTitle;
    /* @NotBlank
     * 必須入力チェック（Stringの時は@NotBlankでスペースのみの入力をチェック）
     * "ab c" などスペースがあっても文字があればOK
     * 文字列の長さはチェックしない
     * */
    @NotBlank(message = "入力してください")
    private String topicContent;
}
