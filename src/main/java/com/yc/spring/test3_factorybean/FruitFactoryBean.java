package com.yc.spring.test3_factorybean;

import org.springframework.beans.factory.FactoryBean;


/**
 * @Author zp
 * @Date 2023/7/25 20:53
 * @PackageName:com.yc.spring.test3
 * @ClassName: FruitFactoryBean
 * @Description:
 * @Version 1.0
 */
public class FruitFactoryBean implements FactoryBean<Pear> {
    public FruitFactoryBean() {
        System.out.println("****************构造");
    }

    @Override
    public Pear getObject() throws Exception {
        return new Pear();
    }

    @Override
    public Class<?> getObjectType() {
        return Pear.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}