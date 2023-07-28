package org.ycframework.context;

/**
 * @Author zp
 * @Date 2023/7/27 15:59
 * @PackageName:org.ycframework.context
 * @ClassName: YcApplicationContext
 * @Description:
 * @Version 1.0
 */
public interface YcApplicationContext {
    public Object getBean(String beanid);
}