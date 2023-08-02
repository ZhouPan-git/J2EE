package com.yc.test1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author zp
 * @Date 2023/8/1 8:56
 * @PackageName:com.yc.biz
 * @ClassName: Config
 * @Description:
 * @Version 1.0
 */
@Configuration
@ComponentScan
@EnableAspectJAutoProxy  //表示启用Aspectj支持
public class Config {
}