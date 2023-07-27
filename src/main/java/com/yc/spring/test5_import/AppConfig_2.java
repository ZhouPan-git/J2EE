package com.yc.spring.test5_import;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author zp
 * @Date 2023/7/26 16:01
 * @PackageName:com.yc.spring.test5
 * @ClassName: AppConfig_2
 * @Description:
 * @Version 1.0
 */
@Configuration
@Import({Banana.class,FruitImportSelector.class})  //beanid  全路径名  -->对象
public class AppConfig_2 {
}