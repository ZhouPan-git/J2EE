package com.yc.spring.test5_import;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author zp
 * @Date 2023/7/26 16:11
 * @PackageName:com.yc.spring.test5
 * @ClassName: FruitNameImportBeanDefinitionRegister
 * @Description:
 * @Version 1.0
 */

//根据条件：  是否已经加载了pear  判断是否加载 grape到容器 且指定beanid
public class FruitNameImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {
    /**
     * @Description:
     * @param annotationMetadata:当前扫描的了类
     *        beanDefinitionRegistry：已经注册好的bean的容器
     * @return
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        //先判断是否已经有了pear加载到容器
        boolean bean =beanDefinitionRegistry.containsBeanDefinition("com.yc.spring.test3_factorybean.Pear");
        //如果有 再创建 grape bean加载到容器
        if (bean){
            RootBeanDefinition d=new RootBeanDefinition(Grape.class);
            //并指定键为grape
            beanDefinitionRegistry.registerBeanDefinition("grape",d);
        }
    }
}