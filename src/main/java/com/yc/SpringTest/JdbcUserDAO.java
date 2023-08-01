package com.yc.SpringTest;

import org.springframework.stereotype.Repository;

/**
 * @Author zp
 * @Date 2023/7/25 16:23
 * @PackageName:com.yc.SpringTest
 * @ClassName: JdbcUserDAO
 * @Description:
 * @Version 1.0
 */
@Repository("jud")   //这是一个dao层的类 由spring托管
              //Component       由spring托管
public class JdbcUserDAO implements UserDAO{
    public JdbcUserDAO() {
        System.out.println("JdbcUserDAO构造");
    }

    @Override
    public void add(String uname) {
        System.out.println("添加了："+uname);
    }
}