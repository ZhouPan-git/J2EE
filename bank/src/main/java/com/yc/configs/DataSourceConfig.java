package com.yc.configs;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @Author zp
 * @Date 2023/8/2 15:05
 * @PackageName:com.yc.configs
 * @ClassName: DataSourceConfig
 * @Description:
 * @Version 1.0
 */
@Configuration
@PropertySource("classpath:db.properties")
@Data
@Log4j2
@EnableTransactionManagement
public class DataSourceConfig {
    //利用DI将db.properties中的内容注入
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.driverclass}")
    private String driverClass;
    //以上属性从db.properties中读取出来后，都存到了spring容器的Environment的变量（系统环境变量）
    @Value("#{T(Runtime).getRuntime().availableProcessors()*2}")
    //spEL-> SPRING expression language
    private int cpuCount;


    @Bean
    public TransactionManager dataSourceTransactionManager(
            @Autowired
            @Qualifier("druidDataSource")
                    DataSource ds
    ){
        DataSourceTransactionManager tx = new DataSourceTransactionManager();
        tx.setDataSource(ds);
        return tx;
    }
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
    @Bean
    public DataSource dbcpDataSource(){
        BasicDataSource dataSource=new BasicDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
        //TODO:更多参数
    }
    //参数：第三方框架的类 用@Bean托管
    @Bean(initMethod = "init",destroyMethod = "close")//DruidDataSource 中提供了 init初始化方法 init用于初始化连接池，close用于关闭前销毁
    public DruidDataSource druidDataSource(){  //注意：idea会对这个方法的返回值进行解析 判断是否有init
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        //以上只是配置了参数，并没有创建连接池在这个类的init（）中完成了连接池的创建
        //log.info("配置druid的连接池的大小："+cpuCount);
        dataSource.setInitialSize(cpuCount);
        dataSource.setMaxActive(cpuCount*2);
        return dataSource;
    }
}