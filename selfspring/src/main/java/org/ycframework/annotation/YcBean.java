package org.ycframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author zp
 * @Date 2023/7/27 15:38
 * @PackageName:org.ycframework.annotation
 * @ClassName: YcBean
 * @Description:
 * @Version 1.0
 */
// 加载在配置类的方法  用于托管第三方类
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface YcBean {
    String[] name() default {};//修改在spring容器中的id名
}
