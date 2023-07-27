package com.yc.spring.test4_annotation;

/**
 * @Author zp
 * @Date 2023/7/26 15:23
 * @PackageName:com.yc.spring.test4_annotation
 * @ClassName: ProductBiz
 * @Description:
 * @Version 1.0
 */
public interface ProductBiz {
    //入库  1.先查  find()  2.再增加（数量  update()还是类型  add()）
    public void takein();
}