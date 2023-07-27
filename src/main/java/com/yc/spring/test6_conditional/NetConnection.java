package com.yc.spring.test6_conditional;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * @Author zp
 * @Date 2023/7/26 16:35
 * @PackageName:com.yc.spring.test6_conditional
 * @ClassName: NetConnection
 * @Description:
 * @Version 1.0
 */
@Component
@Conditional(SystemCondition.class)
public class NetConnection {
}