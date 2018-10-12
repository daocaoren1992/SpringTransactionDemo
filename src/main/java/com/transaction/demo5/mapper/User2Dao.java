package com.transaction.demo5.mapper;

import com.transaction.demo5.model.User2;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class User2Dao extends JdbcDaoSupport {
    public void insert(User2 user2) {
        String sql = "insert into user2(`name`) values(?)";
        this.getJdbcTemplate().update(sql, user2.getName());
    }
}
