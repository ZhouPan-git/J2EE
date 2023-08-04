package com.yc;

import com.yc.bean.OpRecord;
import com.yc.bean.OpType;
import com.yc.configs.Config;
import com.yc.configs.DataSourceConfig;
import com.yc.dao.OpRecordDao;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author zp
 * @Date 2023/8/3 11:44
 * @PackageName:com.yc
 * @ClassName: OpRecordDaoJdbcImplTest
 * @Description:
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class, Config.class})
@Log4j2
public class OpRecordDaoJdbcTemplateImplTest {
    @Autowired
    private OpRecordDao opRecordDao;

    @Test
    public void insertOpRecord(){
        OpRecord opRecord=new OpRecord();
        opRecord.setAccountid(6);
        opRecord.setOpmoney(5);
        opRecord.setOptype(OpType.DEPOSITE);
        this.opRecordDao.insertOpRecord(opRecord);
    }

    @Test
    public void findOpRecord(){
        List<OpRecord> list=this.opRecordDao.findOpRecord(69);
        System.out.println(list);
    }

    @Test
    public void testFindOpRecord(){
        List<OpRecord> list=this.opRecordDao.findOpRecord(6,"DEPOSITE");
        System.out.println(list);
    }

    @Test
    public void testFindOpRecord1(){
    }
}