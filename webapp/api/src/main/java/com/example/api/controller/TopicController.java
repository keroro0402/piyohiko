package com.example.api.controller;

import com.example.api.form.TopicRegistrationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TopicController {
    /* Topic登録画面表示リクエスト */
    @GetMapping("/show-topic-form")
    public String showTopicForm(Model model){
        /* 手動で model に TopicRegistrationForm を追加する方法 */
        /* TopicRegistrationForm 型の変数 formInfo を宣言して、その中に新しい TopicRegistrationForm オブジェクトを格納する */
        /*  */
        TopicRegistrationForm formInfo = new TopicRegistrationForm();
        /* Thymeleafから、 attributeName で参照できるように、 attributeValue を Model 詰める作業 */
        model.addAttribute(
                "topicRegistrationForm", formInfo
        );
        model.addAttribute("title", "トピック登録");
        model.addAttribute("registrationId", "【登録ID】");
        model.addAttribute("userId", "【ユーザーID】");
        model.addAttribute("visitDate", "【登録日時】");
        model.addAttribute("topicTitle", "【トピックタイトル】");
        model.addAttribute("topicContent", "【内容】");
        model.addAttribute("messageToConfirmRegister", "登録内容を確認する");
        return "register-topic";
    }

    /* Topic登録画面表示リクエスト（トピック登録内容確認画面からの戻り） */
    @PostMapping("/show-topic-form-re")
    public String showTopicFormRe(@ModelAttribute TopicRegistrationForm formInfo, Model model){
        model.addAttribute("title", "トピック登録");
        model.addAttribute("registrationId", "【登録ID】");
        model.addAttribute("userId", "【ユーザーID】");
        model.addAttribute("visitDate", "【登録日時】");
        model.addAttribute("topicTitle", "【トピックタイトル】");
        model.addAttribute("topicContent", "【内容】");
        model.addAttribute("messageToConfirmRegister", "登録内容を確認する");
        return "register-topic";
    }

    /* Topic登録リクエスト（トピック登録画面から遷移） */
    @PostMapping("/confirm-register-topic")
    /*
    * @ModelAttribute TopicRegistrationForm
    * 入力された情報を自動で TopicRegistrationForm のフィールドにマッピング（データバインディング）されて formInfo に格納される
    * HTMLの <input name="topicTitle"> → TopicRegistrationForm.topicTitle に自動でセット
    * */
    public String registerTopic(
            /* @Validated
            * TopicRegistrationForm に設定された バリデーション（@NotNull, @Size など）を実行
            * バリデーション結果は BindingResult 型のオブジェクトの中に格納される
            * */
            @Validated
            @ModelAttribute TopicRegistrationForm formInfo, /* 自動で model に TopicRegistrationForm を追加する方法*/
            BindingResult result, // バリデーションの結果を result に格納
            Model model
    ){
        /* 入力エラーがある場合の処理：トピック入力画面へ遷移 */
        if(result.hasErrors()){
            model.addAttribute("title", "トピック登録内容確認");
            model.addAttribute("registrationId", "【登録ID】");
            model.addAttribute("userId", "【ユーザーID】");
            model.addAttribute("visitDate", "【登録日時】");
            model.addAttribute("topicTitle", "【トピックタイトル】");
            model.addAttribute("topicContent", "【内容】");
            model.addAttribute("messageToConfirmRegister", "登録内容を確認する");
            return "register-topic";
        }

        /* 入力エラーがない場合の処理：トピック登録内容確認ページへ遷移 */
        model.addAttribute("title", "トピック登録内容確認");
        model.addAttribute("registrationId", "【登録ID】");
        model.addAttribute("userId", "【ユーザーID】");
        model.addAttribute("visitDate", "【登録日時】");
        model.addAttribute("topicTitle", "【トピックタイトル】");
        model.addAttribute("topicContent", "【内容】");
        model.addAttribute("messageExplanation", "登録を実行するボタンの押下で以下の内容を登録します");
        model.addAttribute("messageToCompleteRegister", "登録を実行する");
        model.addAttribute("messageToRegister", "入力ページへ戻る");
        System.out.println(formInfo); /* 変数 formInfo の中身をコンソール画面で確認 */
        return "confirm-register-topic";
    }

    /* Topic登録リクエスト（トピック登録内容確認画面から遷移） */
    @PostMapping("/complete-register-topic")
    /* formInfo をDBに登録するために引数として受け取る、model経由で complete-register-topic に登録完了を通知するため model を入れる*/
    public String confirmRegisterTopic(
            @Validated TopicRegistrationForm formInfo,
            BindingResult result, // バリデーションの結果を result に格納
            Model model){

        /* 入力エラーがある場合の処理：トピック入力画面へ遷移 */
        if(result.hasErrors()){
            return "register-topic";
        }
        //
        //  ここにDB登録処理を書く
        //
        model.addAttribute("title", "トピック登録完了");
        model.addAttribute("messageComplete", "トピックの登録が完了しました");
        model.addAttribute("messageToRegister", "入力ページへ");
        return "complete-register-topic";
    }
}