package com.transaction.demo2;

/**
 * @author xiaolei hu
 * @date 2018/8/24 19:43
 **/
public interface AccountService {
    /**
     * @param out 转出
     * @param in  转入
     * @param money 转账金额
     */
    void transfer(String out, String in, Double money);;
}
