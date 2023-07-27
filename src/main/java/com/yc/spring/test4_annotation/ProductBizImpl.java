package com.yc.spring.test4_annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author zp
 * @Date 2023/7/26 15:26
 * @PackageName:com.yc.spring.test4_annotation
 * @ClassName: ProductBizImpl
 * @Description:
 * @Version 1.0
 */
@Service
public class ProductBizImpl implements ProductBiz{
    public ProductBizImpl() {
        System.out.println("ProductBizImpl构造了");
    }

    @Autowired
    private ProductDao dao;    //业务层依赖dao层  但最好依赖接口
    @Override
    public void takein() {
        dao.find();
        int i=1;
        if (i==1) {
            dao.update();
        }  else {
            dao.add();
        }
    }
}