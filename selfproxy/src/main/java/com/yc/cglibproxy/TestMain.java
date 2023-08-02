package com.yc.cglibproxy;

/**
 * @Author zp
 * @Date 2023/8/1 21:17
 * @PackageName:com.yc.jdkprocy
 * @ClassName: TestMain
 * @Description:
 * @Version 1.0
 */
public class TestMain {
    public static void main(String[] args) {
        CglibProxyTool jpt=new CglibProxyTool(new OrderBizImpl());
        OrderBizImpl ob= (OrderBizImpl) jpt.createProxy();
        System.out.println("生成代理对象："+ob);

        ob.findOrder();
        ob.addOrder(1,99);
    }
}