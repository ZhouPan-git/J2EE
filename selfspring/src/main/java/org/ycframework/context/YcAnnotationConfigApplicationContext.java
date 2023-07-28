package org.ycframework.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ycframework.annotation.YcComponentScan;

import java.util.*;

/**
 * @Author zp
 * @Date 2023/7/27 16:00
 * @PackageName:org.ycframework.context
 * @ClassName: YcAnnotationConfigApplicationContext
 * @Description:
 * @Version 1.0
 */
public class YcAnnotationConfigApplicationContext implements YcApplicationContext{
    private Logger logger= LoggerFactory.getLogger(YcAnnotationConfigApplicationContext.class);
    //存每个带托管的bean的定义信息
    private Map<String, YcBeanDefinition> beanDefinitionMap = new HashMap<String, YcBeanDefinition>();
    //存每个实例化的bean
    private Map<String,Object> beanMap=new HashMap<String,Object>();
    //存系统属性  db.properties
    private Properties pros;

    public YcAnnotationConfigApplicationContext (Class... configClasses){
        //读取系统的属性
        pros=System.getProperties();
        List<String> toScanPackagePath=new ArrayList<String>();
        for (Class cls:configClasses){
            if (cls.isAnnotationPresent(YcComponentScan.class)==false){
                continue;
            }
            //扫描配置类上的  @YcComponentScan注解  读取扫描的包
            if (cls.isAnnotationPresent(YcComponentScan.class)){
                //如果有则说明 此配置类上有@YcComponentScan 则读取basePackages
                YcComponentScan ycComponentScan= (YcComponentScan) cls.getAnnotation(YcComponentScan.class);
                String[] basePackages= ycComponentScan.basePackages();
                if (basePackages==null||basePackages.length<=0){
                    basePackages=new String[1];
                    basePackages[0]=cls.getPackage().getName();
                }
                logger.info(cls.getName()+"类上有@YcComponentScan注解，它要扫描的路径为："+basePackages[0]);
            }
            //将这些包中带有IOC注解的类 加载到map中

        }
    }
    @Override
    public Object getBean(String beanid) {
        return null;
    }
}