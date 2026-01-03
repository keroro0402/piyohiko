package com.example.api.form;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TopicRegistrationForm {
    private Integer registrationId;
    private String userId;
    private LocalDate visitDate;
    private String topicTitle;
    private List<String> topicCategory;
    private String topicContent;
}
