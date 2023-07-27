package com.yc.spring.test3_factorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author zp
 * @Date 2023/7/25 21:24
 * @PackageName:com.yc.spring.test3
 * @ClassName: TestMain
 * @Description: FactoryBean 的使用
 * @Version 1.0
 */
public class Testmain {
    public static void main(String[] args) {
        //1.创造容器
        //容器启动  FruitFactoryBean  Pear
        ApplicationContext context = new AnnotationConfigApplicationContext(PearConfig.class);
        Object obj=context.getBean("ffb"); //beanid: ffb->产品pear
        System.out.println(obj.hashCode());

        Object obj2=context.getBean("&ffb"); //beanid: &ffb->工厂bean
        System.out.println(obj);

        //2.pear 是单例还是多例
        obj=context.getBean("ffb"); //beanid: ffb->产品pear
        System.out.println(obj.hashCode());

        //3.  获取spring容器中所有的托管bean
        String[] beanNames=context.getBeanDefinitionNames();
        for (String beanName : beanNames){
            System.out.println(beanName);
        }
    }
}