package org.ycframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author zp
 * @Date 2023/7/27 15:09
 * @PackageName:org.ycframework.annotation
 * @ClassName: YcComponent
 * @Description:
 * @Version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface YcComponent {
    String value() default "";
}