package com.yc.jdkProcy;

/**
 * @Author zp
 * @Date 2023/7/30 17:21
 * @PackageName:com.yc.jdkProcy
 * @ClassName: Test
 * @Description:
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        Hello target=new HelloImpl();//目标类
        CustomInvocationHandler handler=new CustomInvocationHandler(target);
        //生成代理类
        Object proxy=handler.createProxy();
        System.out.println(proxy);

        Hello hi= (Hello) proxy;
        hi.saybyebye();
        hi.saybyebye();
    }
}