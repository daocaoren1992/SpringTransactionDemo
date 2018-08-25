package com.transaction.demo4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Spring 的声明式事务管理的方式三：基于注解的事务管理方式
 * @author xiaolei hu
 * @date 2018/8/25 10:53
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext4.xml")
public class AccountServiceImplTest {

    @Resource(name = "accountService")
    private AccountService accountService;

    @Test
    public void transfer() {
        accountService.transfer("aaa", "bbb", 200d);
    }
}