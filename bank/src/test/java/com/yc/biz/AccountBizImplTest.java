package com.yc.biz;

import com.yc.bean.Account;
import com.yc.configs.Config;
import com.yc.configs.DataSourceConfig;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author zp
 * @Date 2023/8/3 19:52
 * @PackageName:com.yc.biz
 * @ClassName: AccountBizImplTest
 * @Description:
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, Config.class})
@Log4j2
public class AccountBizImplTest extends TestCase {
    @Autowired
    private AccountBiz accountBiz;

    @Test
    public void openAccount() {
        Account a=accountBiz.openAccount(100);
        Assert.assertNotNull(a);
        log.info(a);
    }

    @Test
    public void findAccount() {
        Account a=accountBiz.findAccount(5);
        Assert.assertNotNull(a);
        log.info(a);
    }

    @Test
    public void deposite() {
        Account a=accountBiz.deposite(5,7);
        Assert.assertNotNull(a);
        log.info(a);
    }

    @Test
    public void withdraw() {
        Account a=accountBiz.withdraw(1,1000);
        Assert.assertNotNull(a);
        log.info(a);
    }

    @Test
    public void transfer() {
        Account a=accountBiz.transfer(7,5,6);
        Assert.assertNotNull(a);
        log.info(a);
    }
}