package com.transaction.demo2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Spring 的声明式事务管理的方式一的测试类
 * @author xiaolei hu
 * @date 2018/8/25 9:38
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext2.xml")
public class AccountServiceImplTest {

    @Resource(name = "accountServiceProxy")
    private AccountService accountService;

    @Test
    public void transfer() {
        accountService.transfer("aaa", "bbb", 200d);
    }
}