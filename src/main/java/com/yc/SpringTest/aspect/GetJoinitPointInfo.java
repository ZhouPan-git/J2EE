package com.yc.SpringTest.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author zp
 * @Date 2023/7/30 16:17
 * @PackageName:com.yc.SpringTest.aspect
 * @ClassName: GetJoinitPointInfo
 * @Description: 获取加了增加的连接点的信息
 * @Version 1.0
 */
@Component
@Aspect
public class GetJoinitPointInfo {

    //切入点表达式
    @Pointcut("execution(* com.yc.SpringTest.JdbcUserBiz.add*(..))")
    private void abc(){}

    @Before("abc()")//获取连接点的信息
    public void showInfo(JoinPoint jp){
        System.out.println("连接点的信息如下：");
        System.out.println("目标类："+jp.getTarget()+"方法的签名："+jp.getSignature());
        System.out.println("加了增强的方法的参数值：");

        Object[] objs=jp.getArgs();
        if (objs!=null && objs.length>0){
            for (Object o : objs){
                System.out.println(o);
            }
        }
    }
}