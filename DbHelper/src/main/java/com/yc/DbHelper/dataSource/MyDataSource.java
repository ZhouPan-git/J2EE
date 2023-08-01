package com.yc.DbHelper.dataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

/**
 * @Author zp
 * @Date 2023/7/26 17:16
 * @PackageName:com.yc.springtest2
 * @ClassName: MyDataSource
 * @Description:
 * @Version 1.0
 */
@Component
@EnableAspectJAutoProxy  //启用aspectj支持
public class MyDataSource implements DataSource {
    private ConcurrentLinkedQueue<MyConnection> pool;

    //配置后注入  结合属性文件实现注入操作
    //从属性文件中取
    @Value("${coreSize}")
    private int coreSize;
    @Value("${mysql.username}")
    private String username;
    @Value("${mysql.password}")
    private String password;
    @Value("${url}")
    private String url;

    public MyDataSource(){
        System.out.println("MyDataSource构造方法");
    }
    @PostConstruct  //此方法在构造方法后执行  =》即初始化连接池
    public void init() throws SQLException {
        System.out.println("MyDataSource的init()");
        pool = new ConcurrentLinkedQueue<MyConnection>();
        for (int i = 0; i < coreSize;i++){
            MyConnection mc=new MyConnection();
            mc.statue=false;
            mc.conn= DriverManager. getConnection(url,username,password);
            pool.add(mc);
        }
    }
    class MyConnection {
        Connection conn;
        boolean statue;  //true 在用  false空闲
    }

    @Override
    public Connection getConnection() throws SQLException {
        do{
            MyConnection mc=this.pool.poll();
            if (mc==null){
                return null;
            }
            if (!mc.statue){
                return mc.conn;
            }
        }while (true);
    }
    public void returnConnection(Connection conn){
        MyConnection mc=new MyConnection();
        mc.statue=false;
        mc.conn=conn;
        this.pool.add(mc);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
