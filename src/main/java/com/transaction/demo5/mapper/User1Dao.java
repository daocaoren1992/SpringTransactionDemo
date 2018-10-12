package com.transaction.demo5.mapper;

import com.transaction.demo5.model.User1;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class User1Dao extends JdbcDaoSupport  {
    public void insert(User1 user1) {
        String sql = "insert into user1(`name`) values(?)";
        this.getJdbcTemplate().update(sql, user1.getName());
    }
}
