package com.yc.jdkprocy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author zp
 * @Date 2023/8/1 19:18
 * @PackageName:com.yc.jdkprocy
 * @ClassName: JdkProxyTool
 * @Description:
 * @Version 1.0
 */
public class JdkProxyTool implements InvocationHandler {
    //目标类
    private Object target;

    public JdkProxyTool(Object target) {
        this.target = target;
    }

    //生成代理对象的方法
    public Object createProxy(){
        Object proxy = Proxy.newProxyInstance(JdkProxyTool.class.getClassLoader(),target.getClass().getInterfaces(),this);
        return proxy;
    }

    //当在主程序中调用生成的proxy的方法，会自动回调这个invoke（）在这个invoke（）加入之前，切面这些功能
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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