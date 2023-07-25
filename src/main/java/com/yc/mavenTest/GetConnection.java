package com.yc.mavenTest;

import com.yc.commons.DbHelper;
import com.yc.commons.MyProperties;

import java.sql.*;

/**
 * @Author zp
 * @Date 2023/7/21 20:56
 * @PackageName:com.yc.mavenTest
 * @ClassName: GetConnection
 * @Description:
 * @Version 1.0
 */
public class GetConnection {
    private Connection conn;//连接对象
    private PreparedStatement stmt;//预编译对象
    private ResultSet rs;//Jieguo集对象


    //静态块
    static {
        try {
            //Class.forName("")
            //创建驱动类
            com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
            //注册驱动
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(new DbHelper().getConn());

    }

    //获取连接对象   通过驱动管理器获取的
    public Connection getConn() {
        try {

            conn = DriverManager.getConnection(MyProperties.getInstance().getProperty("url"),
                    MyProperties.getInstance());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //关闭所有的资源   Connection   stmt  rs
    public void closeAll(Connection conn, PreparedStatement stmt, ResultSet rs) {
        if (null != rs) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (null != stmt) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}