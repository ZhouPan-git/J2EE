package com.yc.springtest1;

import com.yc.springtest1.system.Container;
import com.yc.springtest1.user.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author zp
 * @Date 2023/7/26 10:23
 * @PackageName:com.yc.springtest1
 * @ClassName: App
 * @Description:
 * @Version 1.0
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context= new AnnotationConfigApplicationContext(Config.class);
        Container container= (Container) context.getBean("container");

        container.add(new Student("张三",1.7,60));
        container.add(new Student("李四",1.8,70));
        container.add(new Student("王五",1.6,50));

        System.out.println(container.getMax());
        System.out.println(container.getMin());
        System.out.println(container.getAvg());
        System.out.println(container.size());
    }
}