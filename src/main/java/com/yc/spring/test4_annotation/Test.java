package com.yc.spring.test4_annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author zp
 * @Date 2023/7/26 15:34
 * @PackageName:com.yc.spring.test4_annotation
 * @ClassName: Test
 * @Description:
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(AppConfig.class);
        ProductBiz pb= (ProductBiz) context.getBean("productBizImpl");   //类名首字母小写就是beanID
        pb.takein();
    }
}