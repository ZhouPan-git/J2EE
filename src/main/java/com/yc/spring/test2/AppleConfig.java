package com.yc.spring.test2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zp
 * @Date 2023/7/25 20:34
 * @PackageName:com.yc.spring.test2
 * @ClassName: AppleConfig
 * @Description: 相当于beans.xml文件
 * @Version 1.0
 */
@Configuration  //此注解 表示这个类是一个配置类 相当于beans.xml文件
public class AppleConfig {
    @Bean  //    <bean id="s" class="com.yc.spring.test2.Apple" >
    public Apple a(){
        Apple apple=new Apple();
        apple.setId(1);
        return apple;
    }
    //Apple对象 以a作键名 存到ApplicationContext容器中
}