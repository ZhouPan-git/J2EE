package com.yc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.ycframework.annotation.YcComponent;
import org.ycframework.annotation.YcComponentScan;
import org.ycframework.annotation.YcConfiguration;

import javax.annotation.Resource;

/**
 * @Author zp
 * @Date 2023/7/27 15:06
 * @PackageName:com.yc
 * @ClassName: MyConfig
 * @Description:
 * @Version 1.0
 */
@YcConfiguration
@YcComponentScan(basePackages="com.yc")
public class MyConfig {
//    @Autowired
//    @Qualifier
//    @Value()
//    @Bean
//    @Resource
//    @PropertySource()
//    @Lazy
//    @Scope
}