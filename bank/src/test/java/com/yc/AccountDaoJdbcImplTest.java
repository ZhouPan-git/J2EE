package com.yc;

import com.yc.bean.Account;
import com.yc.configs.Config;
import com.yc.configs.DataSourceConfig;
import com.yc.dao.AccountDao;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author zp
 * @Date 2023/8/3 10:32
 * @PackageName:com.yc
 * @ClassName: AccountDaoJdbcImplTest
 * @Description:
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, Config.class})
@Log4j2
public class AccountDaoJdbcImplTest extends TestCase {
    @Autowired
    private AccountDao accountDao;

    @Test
    public void update(){
        accountDao.update(3,10);
    }

    @Test
    public void findCount(){
        int total=accountDao.findCount();
        Assert.assertEquals(6,total);
    }

    @Test
    public void findAll(){
        List<Account> list=this.accountDao.findAll();
        log.info(list);
    }

    @Test
    public void findById(){
        Account account=this.accountDao.findById(5);
        log.info(account);
    }

    @Test
    public void insert(){
        int accountid=accountDao.insert(100);
        log.info("新开账户为："+accountid);
        Assert.assertNotNull(accountid);
    }

    @Test
    public void delete(){
        accountDao.delete(4);
    }
}