package com.yc.test1.biz;

/**
 * @Author zp
 * @Date 2023/8/1 8:54
 * @PackageName:com.yc.biz
 * @ClassName: OrderBIiz
 * @Description:
 * @Version 1.0
 */
public interface OrderBiz {
    public void makeOrder(int pid,int num);
    public int findOrderId(String pname);
    public int findPid(String pname);
}