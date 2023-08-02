package com.yc.staticproxy;

/**
 * @Author zp
 * @Date 2023/8/1 18:42
 * @PackageName:com.yc.staticproxy
 * @ClassName: OrderBiz
 * @Description:
 * @Version 1.0
 */
public interface OrderBiz {
    public void addOrder(int pid,int num );
    public void findOrder( );
}