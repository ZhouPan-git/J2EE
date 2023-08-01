package com.yc.jdkProcy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * @Author zp
 * @Date 2023/7/30 16:51
 * @PackageName:com.yc.jdkProcy
 * @ClassName: CustomInvocationHandle
 * @Description:
 * @Version 1.0
 */
public class CustomInvocationHandler implements InvocationHandler {
    private Object target;//目标类

    public CustomInvocationHandler(Object target) {
        this.target = target;
    }

    //生成代理 对象的方法
    public Object createProxy(){
        //jdk中提供了Proxy类 有一个方法专门用于根据接口生成代理类对象的方法
        Object proxy = Proxy.newProxyInstance(CustomInvocationHandler.class.getClassLoader(),target.getClass().getInterfaces(), this);
        return proxy;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        if (method.getName().indexOf("say")>=0) {
            showTime();   //解析@Pointcut
        }
//        showTime();
        //利用反射机制调用目标类的方法
        Object returnValue = method.invoke(target,args);
        return returnValue;
    }

    //增强的方法
    public void showTime(){
        System.out.println("时间为："+new Date());
    }
}