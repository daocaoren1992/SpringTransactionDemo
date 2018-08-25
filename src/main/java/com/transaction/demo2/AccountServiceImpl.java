package com.transaction.demo2;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author xiaolei hu
 * @date 2018/8/24 19:45
 **/
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
    public void transfer(String out, String in, Double money) {
        accountDao.outMoney(out, money);
        //int i = 1 / 0;
        accountDao.inMoney(in, money);
    }

    public static void main(String[] args) {
        try {
            int i =  1 / 0;
        } catch (Exception e) {
            System.out.println("发生了一个异常" + e.getMessage());
        }
        System.out.println("catch后面的代码块");
    }
}
