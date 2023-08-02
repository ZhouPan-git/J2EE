package com.yc.cglibproxy;

/**
 * @Author zp
 * @Date 2023/8/1 18:43
 * @PackageName:com.yc.staticproxy
 * @ClassName: OrderBizImpl
 * @Description:
 * @Version 1.0
 */
public class OrderBizImpl {
    public void addOrder(int pid, int num) {
        System.out.println("添加了："+pid+","+"添加了："+num);
    }

    public void findOrder( ) {
        System.out.println("查询订单");
    }
}