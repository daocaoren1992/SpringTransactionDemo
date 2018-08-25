package com.transaction.demo3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Spring 的声明式事务管理的方式二：基于 AspectJ 的 XML 配置方式
 * @author xiaolei hu
 * @date 2018/8/25 10:34
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext3.xml")
public class AccountServiceImplTest {

    @Resource(name = "accountService")
    private AccountService accountService;

    @Test
    public void transfer() {
        accountService.transfer("aaa", "bbb", 200d);
    }
}