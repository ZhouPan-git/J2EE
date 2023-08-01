package com.yc.DbHelper;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author zp
 * @Date 2023/7/26 17:17
 * @PackageName:com.yc.springtest2
 * @ClassName: Config
 * @Description:
 * @Version 1.0
 */
@Configuration
@ComponentScan
@PropertySource(value = "classpath:db.properties") //spring启动时 PropertySource 类扫描 classpath:db.properties
                                                   //以键值对
public class Config {
}