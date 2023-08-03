package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author zp
 * @Date 2023/8/3 9:24
 * @PackageName:com.yc.bean
 * @ClassName: Account
 * @Description:
 * @Version 1.0
 */
//Serializable: 此类可以
@Data
@AllArgsConstructor  //带所有参
@NoArgsConstructor  //无参数
@ToString  //tostring（）
public class Account implements Serializable {
    private int accountid;
    private double balance;
}