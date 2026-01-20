package com.example.api.controller;

import com.example.api.dto.TopicDto;
import com.example.api.form.TopicRegistrationForm;
import com.example.api.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TopicController {
    private final RegisterService registerService;

    /* Topic登録画面表示リクエスト */
    @GetMapping("/show-topic-form")
    public String showTopicForm(Model model){
        TopicRegistrationForm formInfo = new TopicRegistrationForm();
        model.addAttribute(
                "topicRegistrationForm", formInfo
        );
        model.addAttribute("title", "トピック登録");
        model.addAttribute("registrationId", "【登録ID】");
        model.addAttribute("userId", "【ユーザーID】");
        model.addAttribute("registrationDate", "【登録日時】");
        model.addAttribute("topicTitle", "【トピックタイトル】");
        model.addAttribute("topicContent", "【内容】");
        model.addAttribute("messageToConfirmRegister", "登録内容を確認する");
        return "register-topic";
    }

    /* Topic登録画面表示リクエスト（トピック登録内容確認画面からの戻り） */
    @PostMapping("/show-topic-form-re")
    public String showTopicFormRe(@ModelAttribute TopicRegistrationForm formInfo, Model model){
        model.addAttribute("topicRegistrationForm", formInfo); // この記述は不要だが、formInfo未使用の警告を消すため、明記する
        model.addAttribute("title", "トピック登録");
        model.addAttribute("registrationId", "【登録ID】");
        model.addAttribute("userId", "【ユーザーID】");
        model.addAttribute("registrationDate", "【登録日時】");
        model.addAttribute("topicTitle", "【トピックタイトル】");
        model.addAttribute("topicContent", "【内容】");
        model.addAttribute("messageToConfirmRegister", "登録内容を確認する");
        return "register-topic";
    }

    /* Topic登録リクエスト（トピック登録画面から遷移） */
    @PostMapping("/confirm-register-topic")
    public String registerTopic(
            @Validated
            @ModelAttribute TopicRegistrationForm formInfo,
            BindingResult result,
            Model model
    ){
        /* 入力エラーがある場合の処理：トピック入力画面へ遷移 */
        if(result.hasErrors()){
            model.addAttribute("title", "トピック登録内容確認");
            model.addAttribute("registrationId", "【登録ID】");
            model.addAttribute("userId", "【ユーザーID】");
            model.addAttribute("registrationDate", "【登録日時】");
            model.addAttribute("topicTitle", "【トピックタイトル】");
            model.addAttribute("topicContent", "【内容】");
            model.addAttribute("messageToConfirmRegister", "登録内容を確認する");
            return "register-topic";
        }

        /* 入力エラーがない場合の処理：トピック登録内容確認ページへ遷移 */
        model.addAttribute("title", "トピック登録内容確認");
        model.addAttribute("registrationId", "【登録ID】");
        model.addAttribute("userId", "【ユーザーID】");
        model.addAttribute("registrationDate", "【登録日時】");
        model.addAttribute("topicTitle", "【トピックタイトル】");
        model.addAttribute("topicContent", "【内容】");
        model.addAttribute("messageExplanation", "登録を実行するボタンの押下で以下の内容を登録します");
        model.addAttribute("messageToCompleteRegister", "登録を実行する");
        model.addAttribute("messageToRegister", "入力ページへ戻る");
        System.out.println(formInfo);
        return "confirm-register-topic";
    }

    /* Topic登録リクエスト（トピック登録内容確認画面から遷移） */
    @PostMapping("/complete-register-topic")
    public String confirmRegisterTopic(
            @Validated TopicRegistrationForm formInfo,
            BindingResult result,
            Model model){

        /* 入力エラーがある場合の処理：トピック入力画面へ遷移 */
        if(result.hasErrors()){
            model.addAttribute("topicRegistrationForm", formInfo);
            model.addAttribute("title", "トピック登録内容確認");
            model.addAttribute("registrationId", "【登録ID】");
            model.addAttribute("userId", "【ユーザーID】");
            model.addAttribute("registrationDate", "【登録日時】");
            model.addAttribute("topicTitle", "【トピックタイトル】");
            model.addAttribute("topicContent", "【内容】");
            model.addAttribute("messageToConfirmRegister", "登録内容を確認する");
            return "register-topic";
        }
        TopicDto t = new TopicDto();
        t.setRegistrationId(formInfo.getRegistrationId());
        t.setUserId(formInfo.getUserId());
        t.setRegistrationDate(formInfo.getRegistrationDate());
        t.setTopicTitle(formInfo.getTopicTitle());
        t.setTopicContent(formInfo.getTopicContent());
        registerService.register(t);
        model.addAttribute("title", "トピック登録完了");
        model.addAttribute("messageComplete", "登録完了しました");
        model.addAttribute("messageToRegister", "入力ページへ戻る");
        return "complete-register-topic";
    }
}