package org.ycframework.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author zp
 * @Date 2023/7/27 15:13
 * @PackageName:org.ycframework.annotation
 * @ClassName: YcRepository
 * @Description:
 * @Version 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface YcRepository {
    String value() default "";
}