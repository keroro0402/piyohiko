package com.example.api.repository;

import com.example.api.dto.TopicDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
@RequiredArgsConstructor
public class RegisterRepositoryImpl implements RegisterRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void add(TopicDto topic){
        String sql = "INSERT INTO t_topic " +
                     "(category_id, user_id, registration_date, topic_title, topic_content) " +
                     "VALUES (?, ?, ?, ?, ?)";

        String sql2 = "INSERT INTO sdd" +
        jdbcTemplate.update(
                sql,
                topic.getRegistrationId(),
                topic.getUserId(),
                topic.getRegistrationDate(),
                topic.getTopicTitle(),
                topic.getTopicContent()
                );
    }
}
