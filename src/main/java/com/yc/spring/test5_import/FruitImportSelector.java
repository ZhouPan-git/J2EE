package com.yc.spring.test5_import;

import com.yc.spring.test3_factorybean.Pear;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author zp
 * @Date 2023/7/26 16:02
 * @PackageName:com.yc.spring.test5
 * @ClassName: FruitImportSelector
 * @Description:
 * @Version 1.0
 */
public class FruitImportSelector implements ImportSelector {
    @Override  //回调
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // TODO：扫描一些jar包  配置文件  --》指定第三方类的路径
        return new String[]{Pear.class.getName()};
    }
}