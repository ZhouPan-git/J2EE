package org.ycframework.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author zp
 * @Date 2023/7/27 15:00
 * @PackageName:org.ycframework.annotation
 * @ClassName: YcConfigration
 * @Description:
 * @Version 1.0
 */
@Target(ElementType.TYPE)   //TYPE:类，接口
@Retention(RetentionPolicy.RUNTIME)   //Source 源码  Class  字节码   RunTime  运行期
public @interface YcConfiguration {
    @AliasFor(
            annotation = Component.class
    )
    String value() default "";

    boolean proxyBeanMethods() default true;
}