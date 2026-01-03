package com.example.api.controller;

import com.example.api.form.TopicRegistrationForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TopicController {
    /* Topic登録画面表示 */
    @GetMapping("/show-topic-form")
    public String showTopicForm(){
        return "register-topic";
    }

    /* Topic登録リクエスト（Topic登録画面から遷移） */
    @PostMapping("/register-topic")
    /*
    * @ModelAttribute TopicRegistrationForm
    * 入力された情報を自動で TopicRegistrationForm のフィールドにマッピング（データバインディング）されて formInfo に格納される
    * HTMLの <input name="topicTitle"> → TopicRegistrationForm.topicTitle に自動でセット
    * */
    public String registerTopic(@ModelAttribute TopicRegistrationForm formInfo){
        System.out.println(formInfo); /* 変数 formInfo の中身を確認 */
        return "confirm-register-topic";
    }
}
