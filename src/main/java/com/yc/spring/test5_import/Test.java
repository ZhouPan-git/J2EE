package com.yc.spring.test5_import;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author zp
 * @Date 2023/7/26 16:06
 * @PackageName:com.yc.spring.test5
 * @ClassName: Test
 * @Description:
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig_2.class);

        String[] names=ac.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

    }
}