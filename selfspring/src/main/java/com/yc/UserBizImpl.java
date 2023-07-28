package com.yc;

import org.ycframework.annotation.YcResource;
import org.ycframework.annotation.YcService;

/**
 * @Author zp
 * @Date 2023/7/27 15:57
 * @PackageName:com.yc
 * @ClassName: UserBizImpl
 * @Description:
 * @Version 1.0
 */
@YcService
public class UserBizImpl implements UserBiz{
    @YcResource(name = "userDaoImpl")
    private UserDao userDao;

    @Override
    public void add(String uname) {
        userDao.add(uname);
    }
}