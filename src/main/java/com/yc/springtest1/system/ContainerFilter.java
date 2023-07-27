package com.yc.springtest1.system;

/**
 * @Author zp
 * @Date 2023/7/26 9:03
 * @PackageName:com.yc.springtest1
 * @ClassName: ContainerFilter
 * @Description:  容器过滤接口
 * @Version 1.0
 */
public interface ContainerFilter {
    //判断此对象是否为有效对象
    public boolean doFilter(Object obj);
}