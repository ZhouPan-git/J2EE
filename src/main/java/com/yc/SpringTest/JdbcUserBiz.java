package com.yc.SpringTest;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @Author zp
 * @Date 2023/7/25 16:26
 * @PackageName:com.yc.SpringTest
 * @ClassName: JdbcUserBiz
 * @Description:
 * @Version 1.0
 */
@Service("jub")
public class JdbcUserBiz implements UserBiz{
    public JdbcUserBiz() {
        System.out.println("JdbcUserBiz构造了");
    }

    //将dao层的对象注入 到biz  DI依赖注入（将spring容器中托管的userdao对象传到此处）
    //@Resource(name = "dao")  //由spring容器根据name为dao 到容器中找到这个实例并注入
//    @Autowired   //根据类型注入  在spring容器中 根据userDao接口类型找实例
//    @Qualifier("jdbcUserDAO")
    @Resource(name = "jud")
    private UserDAO userDAO;


    public int addUse(String name) {
        Random r=new Random();
        int a=r.nextInt(2);
        if (a==0){
            //throw new RuntimeException("异常");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("添加了用户："+name);
        return 999;
    }

//    @Override
//    public void addUser(String name) {
//
//    }

    public void add(){
        System.out.println("add");
    }

    @Override
    public void addUser(String name) {
        userDAO.add(name);
    }

//    @Override
//    public void addUser(String name) {
//
//    }
}