package com.yc.staticproxy;

/**
 * @Author zp
 * @Date 2023/8/1 18:44
 * @PackageName:com.yc.staticproxy
 * @ClassName: StaticProxy
 * @Description:
 * @Version 1.0
 */
public class StaticProxy implements OrderBiz{
    //目标类的引用  利用set方法或构造方法 注入
    private OrderBiz orderBiz;

    public StaticProxy(OrderBiz orderBiz) {
        this.orderBiz = orderBiz;
    }

    @Override
    public void addOrder(int pid, int num) {
        //前置增强
        showHello();
        this.orderBiz.addOrder(pid, num);
        showByebye();
        //后置增强
    }

    @Override
    public void findOrder() {

    }

    public void showHello(){
        System.out.println("hello");
    }
    public void showByebye(){
        System.out.println("bye");
    }
}