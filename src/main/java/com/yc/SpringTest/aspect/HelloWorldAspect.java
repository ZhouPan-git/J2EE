package com.yc.SpringTest.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author zp
 * @Date 2023/7/30 9:53
 * @PackageName:com.yc.DbHelper.aspect
 * @ClassName: HelloWorldAspect
 * @Description:
 * @Version 1.0
 */
@Component
@Aspect
@Order(1)
public class HelloWorldAspect {
    //切入点表达式
    @Pointcut("execution(* com.yc.SpringTest.JdbcUserBiz.add*(..))")
    private void abc(){}

//    @Before("abc()")
//    public void doAccessCheck(){
//        System.out.println("HelloWorld");
//    }
//    @AfterReturning(pointcut = "abc()",returning = "retVal")//正常返回，这个增强才起作用
//    public void show1(Object retVal){
//        System.out.println("returning,被增强的方法返回的结果为："+retVal);
//    }
//
//    @AfterThrowing(pointcut = "abc()",throwing = "ex")//正常返回，这个增强才起作用
//    public void show2(RuntimeException ex){
//        System.out.println("throwing,被增强的方法有异常："+ex);
//    }
//
//    @After("abc()")
//    public void show3(){
//        System.out.println("只要方法执行完即可，不管正常或异常");
//    }
    @Around("abc()")
    public Object show4(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("在原方法前的代码");
        Object obj=pjp.proceed();
        System.out.println("在原方法后的代码");
        return obj;
   }
}