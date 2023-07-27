package com.yc.spring.test4_annotation;

import org.springframework.stereotype.Repository;

/**
 * @Author zp
 * @Date 2023/7/26 15:19
 * @PackageName:com.yc.spring.test4_annotation
 * @ClassName: ProductMybaticDaoImpl
 * @Description:
 * @Version 1.0
 */
@Repository
public class ProductMybaticDaoImpl implements ProductDao{
    public ProductMybaticDaoImpl() {
        System.out.println("ProductMybaticDaoImpl构造了");
    }

    @Override
    public void add() {
        System.out.println("ProductMybaticDaoImpl的add（）");
    }

    @Override
    public void find() {
        System.out.println("ProductMybaticDaoImpl的find（）");
    }

    @Override
    public void update() {
        System.out.println("ProductMybaticDaoImpl的update（）");
    }
}