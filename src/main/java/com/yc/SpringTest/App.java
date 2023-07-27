package com.yc.SpringTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author zp
 * @Date 2023/7/25 22:18
 * @PackageName:com.yc.SpringTest
 * @ClassName: App
 * @Description:
 * @Version 1.0
 */
public class App {
    public static void main(String[] args) {
        //首先创建容器
        //ClassPathXmlApplicationContext   类路径下有一个xml文件来配置，来生成容器
        //FileSystemXmlApplicationContext   任意路径 文件系统路径下的xml配置文件来生成容器
        //AnnotationConfigApplicationContext   读取注解的类来  生成容器
        ApplicationContext context=new AnnotationConfigApplicationContext(Config.class);
        //容器已经创建好了这个类
        UserDAO ud= (UserDAO) context.getBean("jdbcUserDAO");
        ud.add("张三");
        //取业务层的类
        UserBiz biz= (UserBiz) context.getBean("jdbcUserBiz");
        biz.addUser("李四");

    }
}