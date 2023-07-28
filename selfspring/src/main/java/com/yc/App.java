package com.yc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ycframework.context.YcAnnotationConfigApplicationContext;
import org.ycframework.context.YcApplicationContext;

/**
 * @Author zp
 * @Date 2023/7/27 14:31
 * @PackageName:com.yc
 * @ClassName: TestMain
 * @Description:
 * @Version 1.0
 */
public class App {
    public static void main(String[] args) {
        YcApplicationContext ac=new YcAnnotationConfigApplicationContext(MyConfig.class);
//        UserBiz ub= (UserBiz) ac.getBean("userBizImpl");
//        ub.add("张三");
    }
}