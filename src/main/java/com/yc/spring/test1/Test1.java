package com.yc.spring.test1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author zp
 * @Date 2023/7/25 19:44
 * @PackageName:com.yc.spring
 * @ClassName: Test1
 * @Description:
 * @Version 1.0
 */
public class Test1 {
    public static void main(String[] args) {
        //引入spring的ioc机制
        //ioc：控制反转 由spring创建对象
        //ClassPathXmlApplicationContext:类路径下的xml文件
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //上面这句 ：启动容器 读取class路径下的beans.xml文件，并获取beans配置的class，实例化对象存到容器中
        //ApplicationContext context = new FileSystemXmlApplicationContext("classpath:beans.xml");
        //第三种：
//        Resource res=new ClassPathResource("beans.xml");
//        BeanFactory context=new XmlBeanFactory(res);
//        Student st= (Student) context.getBean("s");
//        System.out.println(st);
//
//        //容器的好处 容器来管理bean单例：
//        Student s1= (Student) context.getBean("s");
//
//        Student s2= (Student) context.getBean("s");
//        System.out.println(s1.hashCode()+"\t"+s2.hashCode());

        //TODO:让属性有值  DI->Dependency Injection 依赖注入
        Student st= (Student) context.getBean("s");
        System.out.println(st);
    }
}