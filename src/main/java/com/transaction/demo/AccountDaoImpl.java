package com.transaction.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @author xiaolei hu
 * @date 2018/8/24 19:46
 **/
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {
    /**
     * @param out
     * @param money
     */
    @Override
    public void outMoney(String out, Double money) {
       String sql = "update account set money = money - ? where name = ?";
       this.getJdbcTemplate().update(sql, money, out);
    }

    /**
     * @param in
     * @param money
     */
    @Override
    public void inMoney(String in, Double money) {
        String sql = "update account set money = money + ? where name = ?";
        this.getJdbcTemplate().update(sql, money, in);
    }
}
