package com.yc.test1;

import com.yc.test1.biz.OrderBiz;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author zp
 * @Date 2023/8/1 8:56
 * @PackageName:com.yc.biz
 * @ClassName: App
 * @Description:
 * @Version 1.0
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext ac=new AnnotationConfigApplicationContext(Config.class);
        OrderBiz ob=ac.getBean(OrderBiz.class);
        ob.makeOrder(1,3);
//        ob.findOrderId("apple");
//        ob.findOrderId("apple");
//        ob.findOrderId("apple");
//        ob.findOrderId("apple");
//        ob.findOrderId("pear");
//        ob.findOrderId("pear");
//        ob.findOrderId("pear");
//        ob.findOrderId("pear");


//        ob.findPid("apple");
//        ob.findPid("apple");
//        ob.findPid("apple");
//        ob.findPid("apple");
//        ob.findPid("pear");
//        ob.findPid("pear");
//        ob.findPid("pear");
//        ob.findPid("pear");

//        ob.makeOrder(1,99);
    }
}