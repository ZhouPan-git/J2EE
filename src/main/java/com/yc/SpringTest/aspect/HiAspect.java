package com.yc.SpringTest.aspect;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;

/**
 * @Author zp
 * @Date 2023/7/30 14:58
 * @PackageName:com.yc.SpringTest.aspect
 * @ClassName: HiAspect
 * @Description:
 * @Version 1.0
 */
//@Component
//@Aspect
public class HiAspect implements Ordered {
    //切入点表达式
    @Pointcut("execution(* com.yc.SpringTest.JdbcUserBiz.add*(..))")
    private void abc(){} //the pointcut signature

    //下面的方法是一个要加入的增强功能的方法，它会被 加到abc（）的注解指定位置
    @Before("abc()")
    public void doAccessCheck(){
        System.out.println("Hi");
    }

    @Override
    public int getOrder() {
        return 3;
    }
}