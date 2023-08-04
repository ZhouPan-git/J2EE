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
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * Description: AccountBizImplTest
 * Author: heyv
 * Time: 2023 2023/8/3 19:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class, DataSourceConfig.class})
@Log4j2
public class AccountBizImplTest extends TestCase {
@Autowired
private  AccountBiz accountBiz;
    @Test
    public void testOpenAccount() {
        Account  a = accountBiz.openAccount(92);
        Assert.assertNotNull(a);
        log.info(a);
    }
    @Test
    public void testDesposit() {
        Account  a = accountBiz.desposit(2,1);
        Assert.assertNotNull(a);
        log.info(a);
    }
//    @Transactional
    @Test
//    @Rollback(false)
    public void testWithdrow() {
        Account  a = accountBiz.withdrow(2,20);
        Assert.assertNotNull(a);
        log.info(a);
    }
    @Test
    public void testTranfer() {
        Account  a = accountBiz.tranfer(2,10,4);
        Assert.assertNotNull(a);
        log.info(a);
    }

    @Test
    public void testFindAccount() {
        Account  a = accountBiz.findAccount(2);
        Assert.assertNotNull(a);
        log.info(a);
    }
}