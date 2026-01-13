package com.example.api.dto;

import lombok.Data;

import java.time.LocalDate;

/* DTO（Data Transfer Object）
* APIなどでデータを受け渡しするための入れ物
* */
@Data
public class TopicDto {
    private Integer registrationId;
    private String userId;
    private LocalDate visitDate;
    private String topicTitle;
    private String topicContent;
}
