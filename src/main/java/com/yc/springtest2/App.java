package com.yc.springtest2;

import com.yc.springtest2.user.BankAccount;
import com.yc.springtest2.user.BankAccountDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author zp
 * @Date 2023/7/26 17:17
 * @PackageName:com.yc.springtest2
 * @ClassName: App
 * @Description:
 * @Version 1.0
 */
public class App {
    public static void main(String[] args) throws SQLException {
        ApplicationContext ac=new AnnotationConfigApplicationContext(Config.class);
//        DataSource ds= (DataSource) ac.getBean("myDataSource");
//
//        Connection conn=ds.getConnection();
//        System.out.println(conn);
        BankAccountDao dao= (BankAccountDao) ac.getBean("bankAccountDao");
        List<BankAccount> list=dao.findAll();
        for (BankAccount ba:list) {
            System.out.println(ba);
        }
    }
}