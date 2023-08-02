package com.yc.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author zp
 * @Date 2023/8/1 21:14
 * @PackageName:com.yc.cglibproxy
 * @ClassName: CglibProxyTool
 * @Description:
 * @Version 1.0
 */
public class CglibProxyTool implements MethodInterceptor {
    //目标类
    private Object target;

    public CglibProxyTool(Object target) {
        this.target = target;
    }

    //生成代理对象的方法
    public Object createProxy(){
        //Object proxy = Proxy.newProxyInstance(JdkProxyTool.class.getClassLoader(),target.getClass().getInterfaces(),this);
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback( this);
        Object proxy =enhancer.create();
        return proxy;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        //以反射方式读取@Pointcut的切入点表达式  =>spring底层，用aspectj来读取表达式
        if (method.getName().startsWith("add")) {
            showHello();//前置增强
        }

        Object returnVal=method.invoke( target,args);//调用目标类的方法
        return returnVal;
    }

    public void showHello(){
        System.out.println("Hello");
    }
}