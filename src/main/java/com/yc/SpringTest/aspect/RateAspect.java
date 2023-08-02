package com.yc.SpringTest.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author zp
 * @Date 2023/7/30 16:38
 * @PackageName:com.yc.SpringTest.aspect
 * @ClassName: RateAspect
 * @Description:
 * @Version 1.0
 */
@Component
@Aspect
@Order(2
)
public class RateAspect {
    //切入点表达式
    @Pointcut("execution(* com.yc.SpringTest.JdbcUserBiz.add*(..))")
    private void abc(){}

    @Around("abc()")
    public Object show4(ProceedingJoinPoint pjp) throws Throwable {//ProceedingJoinPoint 代表连接点
        System.out.println("RateAspect进来了");
        long start=System.currentTimeMillis();
        Object obj=pjp.proceed();
        long end=System.currentTimeMillis();
        System.out.println("方法运行了："+(end-start));
        return obj;
    }
}