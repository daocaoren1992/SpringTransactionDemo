package com.transaction.demo4;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @author xiaolei hu
 * @date 2018/8/24 19:45
 **/

/**
 * @Transactional 注解中的属性
 * propagation :事务的传播行为
 * isolation   :事务的隔离级别
 * readOnly    : 是否只读
 * rollbackFor : 发生哪些异常回滚
 * noRollbackFor : 发生哪些异常不回滚
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    /**
     * @param out   转出
     * @param in    转入
     * @param money 转账金额
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public void transfer(String out, String in, Double money) {
        accountDao.outMoney(out, money);
        //throw new RuntimeException("发生了异常");
        int i = 1 / 0;
        accountDao.inMoney(in, money);
    }

}
