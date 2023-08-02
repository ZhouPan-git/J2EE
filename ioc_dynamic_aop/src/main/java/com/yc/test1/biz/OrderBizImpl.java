package com.yc.test1.biz;

import org.springframework.stereotype.Service;

/**
 * @Author zp
 * @Date 2023/8/1 8:55
 * @PackageName:com.yc.biz
 * @ClassName: OrderBizImpl
 * @Description:
 * @Version 1.0
 */
@Service
public class OrderBizImpl implements OrderBiz{
    @Override
    public void makeOrder(int pid, int num) {
        if (num>5){
            throw new RuntimeException("库存不足");
        }
        System.out.println("makeOrder下单:"+pid+"\t"+num);
    }

    @Override
    public int findOrderId(String pname) {
        System.out.println("findOrderId查找对应订单:"+pname);
        return 1;
    }

    @Override
    public int findPid(String pname) {
        System.out.println("findPid查找对应订单:"+pname);
        return (int) (Math.random()*10);
    }
}