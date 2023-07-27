package com.yc.spring.test2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author zp
 * @Date 2023/7/25 20:38
 * @PackageName:com.yc.spring.test2
 * @ClassName: TestMain
 * @Description:
 * @Version 1.0
 */
public class TestMain {
    public static void main(String[] args) {
        //AnnotationConfigApplicationContext  基于注解@Configuration 的配置类
        //IOC 完成 DI 完成
        ApplicationContext ac=new AnnotationConfigApplicationContext(AppleConfig.class);
        Apple apple= (Apple) ac.getBean("a");
        System.out.println(apple);
    }
}