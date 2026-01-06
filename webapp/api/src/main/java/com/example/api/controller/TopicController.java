package com.example.api.controller;

import com.example.api.form.TopicRegistrationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TopicController {
    /* Topic登録画面表示リクエスト */
    @GetMapping("/show-topic-form")
    public String showTopicForm(Model model){
        /* 手動で model に TopicRegistrationForm を追加する方法 */
        /* TopicRegistrationForm 型の変数 form を宣言して、その中に新しい TopicRegistrationForm オブジェクトを格納する */
        TopicRegistrationForm formInfo = new TopicRegistrationForm();
        /* Thymeleafから、 attributeName で参照できるように、 attributeValue を Model 詰める作業 */
        model.addAttribute(
                "topicRegistrationForm", formInfo
        );
        model.addAttribute("templateTitle", "トピック登録");
        model.addAttribute("templateRegistrationId", "【登録ID】");
        model.addAttribute("templateUserId", "【ユーザーID】");
        model.addAttribute("templateVisitDate", "【登録日時】");
        model.addAttribute("templateTopicTitle", "【トピックタイトル】");
        model.addAttribute("templateTopicContent", "【内容】");
        model.addAttribute("templateRegister", "登録する");
        return "register-topic";
    }

    /* Topic登録画面表示リクエスト（登録確認画面からの戻り） */
    @PostMapping("/show-topic-form-re")
    public String showTopicFormRe(@ModelAttribute TopicRegistrationForm formInfo){
        return "register-topic";
    }

    /* Topic登録リクエスト（Topic登録画面から遷移） */
    @PostMapping("/register-topic")
    /*
    * @ModelAttribute TopicRegistrationForm
    * 入力された情報を自動で TopicRegistrationForm のフィールドにマッピング（データバインディング）されて formInfo に格納される
    * HTMLの <input name="topicTitle"> → TopicRegistrationForm.topicTitle に自動でセット
    * */
    public String registerTopic(
            @ModelAttribute TopicRegistrationForm formInfo, /* 自動で model に TopicRegistrationForm を追加する方法*/
            Model model
    ){
        model.addAttribute("templateTitle", "トピック登録内容確認");
        model.addAttribute("templateRegistrationId", "【登録ID】");
        model.addAttribute("templateUserId", "【ユーザーID】");
        model.addAttribute("templateVisitDate", "【登録日時】");
        model.addAttribute("templateTopicTitle", "【トピックタイトル】");
        model.addAttribute("templateTopicContent", "【内容】");
        model.addAttribute("templateToRegister", "入力ページへ");
        System.out.println(formInfo); /* 変数 formInfo の中身をコンソール画面で確認 */
        return "confirm-register-topic";
    }

    /* Topic登録リクエスト（登録確認画面から遷移） */
    @PostMapping("confirm-register-topic")
    public String confirmRegisterTopic(TopicRegistrationForm formInfo, Model model){
        //
        //  ここにDb登録処理を書く
        //
        return "complete-register-topic";
    }
}