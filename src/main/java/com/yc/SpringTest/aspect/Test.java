package com.yc.SpringTest.aspect;

import com.yc.SpringTest.Config;
import com.yc.SpringTest.JdbcUserBiz;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

/**
 * @Author zp
 * @Date 2023/7/30 14:42
 * @PackageName:com.yc.SpringTest.aspect
 * @ClassName: Test
 * @Description:
 * @Version 1.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
public class Test {
    @Resource(name = "jub")
    private JdbcUserBiz biz;


    @org.junit.jupiter.api.Test
    public void test1(){
        biz.addUse("zp");
        //biz.add();
    }
}