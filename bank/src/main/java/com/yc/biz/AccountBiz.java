package com.yc.biz;

import com.yc.bean.Account;

/**
 * @Author zp
 * @Date 2023/8/2 14:15
 * @PackageName:com.yc.biz
 * @ClassName: AccountBiz
 * @Description:
 * @Version 1.0
 */
public interface AccountBiz {

    public Account openAccount(double money);

    public Account findAccount(int accountId);

    public Account deposite(int accountId,double money);

    public Account deposite(int accountId,double money,Integer transferId);

    public Account withdraw(int accountId,double money);

    public Account withdraw(int accountId,double money,Integer transferId);

    public Account transfer(int accountId,double money,int toAccountId);
}