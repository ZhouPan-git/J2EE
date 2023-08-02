package com.yc.test1.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Author zp
 * @Date 2023/8/1 11:38
 * @PackageName:com.yc.test1.aspects
 * @ClassName: HelloAspect
 * @Description:
 * @Version 1.0
 */
//@Aspect
//@Component
//@Order(100)
public class HelloAspect {
    @Pointcut("execution(* com.yc.test1.biz.OrderBiz*.findPid(..))")
    private void a(){}

    @Around("a()")
    public Object show(ProceedingJoinPoint pjp){
        System.out.println("Hello 的show的前面");
        Object obj=null;
        try {
            obj= pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("Hello 的show的后面");
        return obj;
    }
}