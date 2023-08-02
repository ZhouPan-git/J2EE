package com.yc.test1.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author zp
 * @Date 2023/8/1 9:08
 * @PackageName:com.yc.aspects
 * @ClassName: MyAspects
 * @Description:
 * @Version 1.0
 */
@Aspect
@Component
public class MyAspects {
    private Logger logger= LoggerFactory.getLogger(MyAspects.class);
    //切入点表达式
    // ..:代表有或没有都可以 （0或n）
    // *：
    /** 切入点符号：
     *           execution:执行
     */
    @Pointcut("execution(* com.yc.test1.biz.OrderBizImpl.makeOrder(..))")
    private void a(){}

    @Before("a()")
    public void doAccessCheck1(){
        Date date = new Date();
        System.out.println("下单时间："+date);
    }

//    @AfterReturning("a()")
//    public void doAccessCheck2(){
//        System.out.println("发送邮件");
//    }

    @AfterReturning("a()")//获取连接点的信息
    public void showInfo(JoinPoint jp){
        System.out.println("连接点的信息如下：");
        System.out.println("目标类："+jp.getTarget()+"方法的签名："+jp.getSignature());
        System.out.println("加了增强的方法的参数值：");

        Object[] objs=jp.getArgs();
        if (objs!=null && objs.length>0){
            for (Object o : objs){
                logger.info("参数信息："+o);
//                System.out.println(o);
            }
        }
    }

    @Pointcut("execution(* com.yc.test1.biz.*.findOrderId(..))")
    private void b(){}

    //TODO:正常是要访问redis，商品名，次数
    private Map<String,Long> map=new ConcurrentHashMap<>();
    @AfterReturning("b()")//获取连接点的信息
    public void recordPnameCount(JoinPoint jp){
        Object[] objs=jp.getArgs();
        String pname=(String) objs[0];
        Long num=1L;
        if (map.containsKey(pname)) {
            num = map.get(pname);
            num++;
        }
        map.put(pname,num);
        System.out.println("统计结果："+map);
    }

    @Pointcut("execution(* com.yc.test1.biz.*.findPid(..))")
    private void c(){}
    private Map<String,Long> map2=new ConcurrentHashMap<String, Long>();

    @AfterReturning(pointcut = "c()",returning = "retVal")//获取连接点的信息
    public void recordPnameCount2(JoinPoint jp,int retVal){
        Object[] objs=jp.getArgs();
        String pname=(String) objs[0];
        Long num=1L;
        if (map2.containsKey(pname+":"+retVal)) {
            num = map2.get(pname+":"+retVal);
            num++;
        }
        map2.put(pname+":"+retVal,num);
        System.out.println("统计结果："+map2);
    }

    @AfterThrowing(value = "a()",throwing = "ex")
    public void recordException(JoinPoint jp,RuntimeException ex){
        System.out.println("********************************");
        System.out.println(ex.getMessage());
        System.out.println(jp.getArgs()[0]+"\t"+jp.getArgs()[1]);
        System.out.println("********************************");
    }

    @Pointcut("execution(int com.yc.test1.biz.*.findPid(String))")
    private void d(){}

    @Around("d()")
    public Object doBasicProFiling(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object retVal=pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("方法执行时长为："+(end-start));
        return retVal;
    }
}