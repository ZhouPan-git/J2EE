package com.yc.biz;

import org.springframework.stereotype.Service;

/**
 * @Author zp
 * @Date 2023/8/2 14:15
 * @PackageName:com.yc.biz
 * @ClassName: AccountBizImpl
 * @Description:
 * @Version 1.0
 */
@Service
public class AccountBizImpl implements AccountBiz{
    @Override
    public void addAccount(int accountId, double money) {
        System.out.println(accountId+"账号充值"+money);
    }
}