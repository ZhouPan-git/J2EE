package com.yc.SpringTest;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author zp
 * @Date 2023/7/25 16:17
 * @PackageName:com.yc.SpringTest
 * @ClassName: UserBiz
 * @Description:
 * @Version 1.0
 */
@Service
public interface UserBiz {
    public void addUser(String name);
}