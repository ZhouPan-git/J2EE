package com.yc.DbHelper.user;

import com.yc.DbHelper.jdbcTemplate.JdbcTemplate;
import com.yc.DbHelper.jdbcTemplate.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author zp
 * @Date 2023/7/27 10:11
 * @PackageName:com.yc.springtest2.user
 * @ClassName: BankAccountDao
 * @Description:
 * @Version 1.0
 */
@Repository
public class BankAccountDao extends JdbcTemplate {
    @Autowired   //按类型注入 DataSource 接口-》MyDataSource 实现类
    public BankAccountDao(DataSource dataSource) {
        super(dataSource);
    }
    public List<BankAccount> findAll() {
        return super.executeQuery("select * from bank where id = ?", new RowMapper<BankAccount>() {
            @Override
            public BankAccount mapper(ResultSet rs, int i) throws SQLException {
                BankAccount ba = new BankAccount();
                ba.setId(rs.getInt(1));
                ba.setBalance(rs.getInt(2));
                return ba;
            }
        }, 1);
    }
}