package com.example.api.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TopicDto {
    private Integer registrationId;
    private String userId;
    private LocalDate registrationDate;
    private String topicTitle;
    private String topicContent;
}
