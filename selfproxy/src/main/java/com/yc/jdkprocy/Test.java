package com.yc.jdkprocy;

import com.yc.staticproxy.OrderBiz;
import com.yc.staticproxy.OrderBizImpl;

/**
 * @Author zp
 * @Date 2023/8/1 19:24
 * @PackageName:com.yc.jdkprocy
 * @ClassName: Test
 * @Description:
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        //设置代理生成的字节码 保存到当前目录
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        JdkProxyTool jpt=new JdkProxyTool(new OrderBizImpl());
        OrderBiz ob= (OrderBiz) jpt.createProxy();
        System.out.println("生成代理类对象："+ob);

        ob.findOrder();
        ob.addOrder(1,99);
    }
}