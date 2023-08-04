package com.yc;

import com.alibaba.druid.pool.DruidDataSource;
import com.yc.configs.Config;
import com.yc.configs.DataSourceConfig;
import junit.framework.TestCase;
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
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author zp
 * @Date 2023/8/2 15:10
 * @PackageName:com.yc
 * @ClassName: Test2_DataSourceConfig
 * @Description:
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class,Config.class})
@Log4j2
public class Test2_DataSourceConfig extends TestCase {
    //可通过调整@Primary 注解的位置来测试你想测试的类
    @Autowired
    private DataSourceConfig dsc;

    @Autowired
    private Environment env;

    @Autowired
    @Qualifier("dataSource")
    private DataSource ds1;

    @Autowired
    @Qualifier("dbcpDataSource")
    private DataSource ds2;

    @Autowired
    @Qualifier("druidDataSource")
    private DataSource ds3;

    @Autowired
    private TransactionManager tx;

    @Test
    public void testTransactionManager(){
        log.info(tx);
    }

    @Test
    public void testPropertySource(){
        Assert.assertEquals("root",dsc.getUsername());
        log.info(dsc.getUsername());
    }

    @Test
    public void testEnvironment(){
        log.info(env.getProperty("jdbc.password")+"\t"+env.getProperty("user.home"));
    }

    @Test
    public void testConnection() throws SQLException {
        Assert.assertNotNull(ds1.getConnection());
        Connection con=ds1.getConnection();
        log.info(con);
    }

    @Test
    public void testDbcpConnection() throws SQLException {
        Assert.assertNotNull(ds2.getConnection());
        Connection con=ds2.getConnection();
        log.info(con);
    }

    @Test
    public void testDruidConnection() throws SQLException {
        Assert.assertNotNull(ds3.getConnection());
        Connection con=ds3.getConnection();
        log.info(con+"\t"+((DruidDataSource)ds3).getInitialSize());
    }
}