package com.yc.SpringTest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author zp
 * @Date 2023/7/25 22:10
 * @PackageName:com.yc.SpringTest
 * @ClassName: Config
 * @Description:
 * @Version 1.0
 */
@EnableAspectJAutoProxy
@Configuration  //配置类，声明容器运行时的一些配置信息 （1.扫描的路径，这个路径所有带有@Component @Repository @Service @Controller 这样的注解的类会被spring托管
@ComponentScan(basePackages={"com.yc.SpringTest"})
public class Config {
//    @Resource(name = "jud")
//    public JdbcUserDAO dao(){
//        return new JdbcUserDAO();
//    }
//    @Autowired
//    private JdbcUserBiz biz;
//
//    @Test
//    public void test(){
//        assert biz != null;
//        biz.add();
//    }
//@Autowired
//ApplicationContext ctx;
//
//    @Test
//    public void test(){
//        JdbcUserBiz biz = (JdbcUserBiz) ctx.getBean("ub");
//        biz.add();
//    }
}