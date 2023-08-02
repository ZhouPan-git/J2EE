package com.yc.staticproxy;

/**
 * @Author zp
 * @Date 2023/8/1 18:48
 * @PackageName:com.yc.staticproxy
 * @ClassName: Test
 * @Description:
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        OrderBiz ob=new StaticProxy(new OrderBizImpl());
        ob.addOrder(1,100);
    }
}