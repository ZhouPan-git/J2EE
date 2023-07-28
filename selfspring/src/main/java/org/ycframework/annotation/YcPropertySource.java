package org.ycframework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author zp
 * @Date 2023/7/27 15:50
 * @PackageName:org.ycframework.annotation
 * @ClassName: YcPropertySource
 * @Description:
 * @Version 1.0
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface YcPropertySource {
    String[] value();  //表示用了此注解一定要指定value值
}
