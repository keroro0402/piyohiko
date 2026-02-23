package com.example.api.sandbox.service;

import com.example.api.sandbox.dto.YesConstructorYesNoArgs1Dto;
import com.example.api.sandbox.dto.YesConstructorYesNoArgs1Status;
import org.springframework.stereotype.Service;

@Service
public class YesConstructorYesNoArgs1Service {
    private YesConstructorYesNoArgs1Dto yesConstructorYesNoArgs1Dto;

    public YesConstructorYesNoArgs1Dto getStatus(){
        YesConstructorYesNoArgs1Dto dto = new YesConstructorYesNoArgs1Dto(YesConstructorYesNoArgs1Status.MEMBER_AREA); // 引数ありコンストラクタをnewする
        YesConstructorYesNoArgs1Dto dto2 = new YesConstructorYesNoArgs1Dto(); // NoArgsConstructor（Lambok）の引数なしコンストラクタをnewする
        dto2.setStatus(YesConstructorYesNoArgs1Status.MEMBER_ADDRESS); // 引数なしなのでセッターで値を設定
        Integer count = 10;
        if(count != null && count > 0){
            return dto2;
        }
        return dto;
    }

}
