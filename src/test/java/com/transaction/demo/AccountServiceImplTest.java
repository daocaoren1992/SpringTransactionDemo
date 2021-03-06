package com.transaction.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author xiaolei hu
 * @date 2018/8/24 19:58
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class AccountServiceImplTest {

    @Resource(name = "accountService")
    private AccountService accountService;

    /**
     * 注入代理类：以为代理类进行增强的概念
     */

    @Test
    public void transfer() {
        accountService.transfer("aaa", "bbb", 200d);
    }
}