package com.transaction.demo3;

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
        int i = 1 / 0;
        accountDao.inMoney(in, money);
    }

}
