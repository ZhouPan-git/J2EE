package com.yc.spring.test3_factorybean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zp
 * @Date 2023/7/25 21:23
 * @PackageName:com.yc.spring.test3
 * @ClassName: PearConfig
 * @Description:
 * @Version 1.0
 */
@Configuration  //配置类
public class PearConfig {
    @Bean
    public FruitFactoryBean ffb(){
        return new FruitFactoryBean();
    }
}