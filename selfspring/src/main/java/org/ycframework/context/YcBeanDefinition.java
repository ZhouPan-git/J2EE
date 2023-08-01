package org.ycframework.context;

import org.springframework.beans.factory.config.BeanDefinition;

/**
 * @Author zp
 * @Date 2023/7/27 17:10
 * @PackageName:org.ycframework.context
 * @ClassName: YcBeanDefinition
 * @Description:
 * @Version 1.0
 */
//对一个bean的特征的包装的类
    //特征：scope （singleton/prototype/...）
    //      lazy：(true/false) 懒加载
    //      primary： 主实例     @AutoWired+@Qualifier
                               // @AutoWired+@Primary
public class YcBeanDefinition {
    private boolean isLazy;
    private String scope="singleton";
    private boolean isPrimary;

    private String classInfo;  //类的实例信息

    public boolean isLazy() {
        return isLazy;
    }

    public void setLazy(boolean lazy) {
        isLazy = lazy;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public String getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(String classInfo) {
        this.classInfo = classInfo;
    }
}