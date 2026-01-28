package com.example.api.repository;

import com.example.api.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RegisterRepositoryImpl implements RegisterRepository{

    /*JDBCテンプレート用フィールド作成*/
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void add(Review review){
        /*DB更新SQL*/
        String sql =
                "INSERT INTO t_review" +
                "(restaurant_id, user_id, visit_date, rating, comment)" +
                "VALUES(?, ?, ?, ?, ?)";
        /*JDBCテンプレートでDB更新*/
        jdbcTemplate.update(sql,review.getRestaurantId(),
                                review.getUserId(),
                                review.getVisitDate(),
                                review.getRating(),
                                review.getComment());
    }
}
