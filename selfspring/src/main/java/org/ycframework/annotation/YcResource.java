package org.ycframework.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author zp
 * @Date 2023/7/27 15:45
 * @PackageName:org.ycframework.annotation
 * @ClassName: YcResource
 * @Description:
 * @Version 1.0
 */
@Target({TYPE, FIELD, METHOD})
@Retention(RUNTIME)
public @interface YcResource {
    String name() default "";   //指定要装配的对象的id名
}