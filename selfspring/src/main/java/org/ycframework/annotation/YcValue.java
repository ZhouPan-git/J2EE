package org.ycframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author zp
 * @Date 2023/7/27 15:48
 * @PackageName:org.ycframework.annotation
 * @ClassName: YcValue
 * @Description:
 * @Version 1.0
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface YcValue {
    String value();   //没有默认值 则说明 在用这个注解时 一定用指定值
}

