package com.yc;

import org.ycframework.annotation.YcRepository;

/**
 * @Author zp
 * @Date 2023/7/27 15:55
 * @PackageName:com.yc
 * @ClassName: UserDaoImpl
 * @Description:
 * @Version 1.0
 */
@YcRepository
public class UserDaoImpl implements UserDao{

    @Override
    public void add(String uname) {
        System.out.println("添加了"+uname);
    }
}