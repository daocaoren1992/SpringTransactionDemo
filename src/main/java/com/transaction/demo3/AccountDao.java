package com.transaction.demo3;

/**
 * @author xiaolei hu
 * @date 2018/8/24 19:45
 **/
public interface AccountDao {
    /**
     * @param out
     * @param money
     */
    void outMoney(String out, Double money);

    /**
     * @param in
     * @param money
     */
    void inMoney(String in, Double money);
}
