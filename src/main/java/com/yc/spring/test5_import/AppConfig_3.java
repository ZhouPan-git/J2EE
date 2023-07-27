package com.yc.spring.test5_import;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author zp
 * @Date 2023/7/26 16:22
 * @PackageName:com.yc.spring.test5
 * @ClassName: AppConfig_3
 * @Description:
 * @Version 1.0
 */
@Configuration           //pear的托管                  //grape的托管   只有pear托管 grape才会托管
@Import({Banana.class,FruitImportSelector.class,FruitNameImportBeanDefinitionRegister.class})  //beanid  全路径名  -->对象
public class AppConfig_3 {
    public static void main(String[] args) {
        ApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig_3.class);
        String[] names=ac.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

    }
}