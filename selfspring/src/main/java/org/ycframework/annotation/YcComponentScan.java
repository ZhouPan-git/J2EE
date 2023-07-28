package org.ycframework.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author zp
 * @Date 2023/7/27 15:05
 * @PackageName:org.ycframework.annotation
 * @ClassName: YcComponentScan
 * @Description:
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@YcComponent
public @interface YcComponentScan {
    String[] basePackages() default {};
}