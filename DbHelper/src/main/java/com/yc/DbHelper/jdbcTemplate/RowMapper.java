package com.yc.DbHelper.jdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author zp
 * @Date 2023/7/27 10:12
 * @PackageName:com.yc.springtest2.jdbcTemplate
 * @ClassName: RowMapper
 * @Description:
 * @Version 1.0
 */
public interface RowMapper<T> {
    //对ResultSet的第i行 转换为T对象 ：具体实现由用户决定
    public T mapper(ResultSet rs,int i) throws SQLException;
}