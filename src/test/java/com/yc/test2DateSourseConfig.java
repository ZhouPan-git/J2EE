package com.yc;

import com.alibaba.druid.pool.DruidDataSource;
import com.yc.configs.Config;
import com.yc.configs.DataSourceConfig;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

/**
 * Description: Test2DateSourseConfig
 * Author: heyv
 * Time: 2023 2023/8/2 15:10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class, DataSourceConfig.class})
@Log4j2
public class test2DateSourseConfig {

    @Autowired
    private DataSourceConfig dsc;

    @Autowired
    private Environment env;// TODO：

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Autowired
    @Qualifier("dbcpDataSource")
    private DataSource dbcpDataSource;

    @Autowired
    @Qualifier("druiddataSource")
    private DataSource  druiddataSource;

    @Autowired
    private TransactionManager tx;

    @Test
    public  void testTransactionManager(){
        log.info(tx);
    }


    @Test
    public void test1(){
//        Assert.assertEquals("root", dsc);
        log.info("666");

    }
    @Test
    public void test2(){

        Assert.assertNotNull(dataSource);
        Assert.assertNotNull(dbcpDataSource);
        Assert.assertNotNull(druiddataSource);
        log.info("--------------------------------");
    }
}
