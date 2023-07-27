package com.yc.SpringTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author zp
 * @Date 2023/7/25 16:26
 * @PackageName:com.yc.SpringTest
 * @ClassName: JdbcUserBiz
 * @Description:
 * @Version 1.0
 */
@Service
public class JdbcUserBiz implements UserBiz {
    public JdbcUserBiz() {
        System.out.println("JdbcUserBiz构造了");
    }

    //将dao层的对象注入 到biz  DI依赖注入（将spring容器中托管的userdao对象传到此处）
    //@Resource(name = "dao")  //由spring容器根据name为dao 到容器中找到这个实例并注入
    @Autowired   //根据类型注入  在spring容器中 根据userDao接口类型找实例
    @Qualifier("jdbcUserDAO")
    private UserDAO userDAO;


    @Override
    public void addUser(String name) {
        userDAO.add(name);
    }
}