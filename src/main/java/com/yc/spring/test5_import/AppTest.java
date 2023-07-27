package com.yc.spring.test5_import;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author zp
 * @Date 2023/7/26 15:51
 * @PackageName:com.yc.spring.test5
 * @ClassName: AppTest
 * @Description:
 * @Version 1.0
 */
public class AppTest {
    public static void main(String[] args) {
        ApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);

        String[] names=ac.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

        Banana b1= (Banana) ac.getBean("com.yc.spring.test5_import.Banana");
        System.out.println(b1);
        Banana b2=ac.getBean(Banana.class);
        System.out.println(b2);
    }
}