package com.yc.spring.test6_conditional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zp
 * @Date 2023/7/26 16:42
 * @PackageName:com.yc.spring.test6_conditional
 * @ClassName: AppConfig
 * @Description:
 * @Version 1.0
 */
@Configuration
@ComponentScan({"com.yc.spring.test6_conditional"})
public class AppConfig {
    public static void main(String[] args) {
        ApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);
        String[] names=ac.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }
}