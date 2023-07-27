package com.yc.spring.test6_conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Author zp
 * @Date 2023/7/26 16:36
 * @PackageName:com.yc.spring.test6_conditional
 * @ClassName: SystemConditional
 * @Description:
 * @Version 1.0
 */

public class SystemCondition implements Condition {
    /**
     * @Description: 匹配方法： 看操作系统 《- Environment <-Spring容器
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //Environment  jdk-->System.getEnvironment
        Environment env = context.getEnvironment();
          //系统变量   Windows
          //命令参数    Linux
        // 程序   运行配置 -Dos.name=Linux
        String osname=env.getProperty("os.name");
        System.out.println(env.getProperty("user.dir"));
        System.out.println(env.getProperty("user.home"));
        if (osname.contains("Windows")){
            return true;
        }
        return false;
    }
}