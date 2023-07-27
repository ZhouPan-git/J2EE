package com.yc.springtest1.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zp
 * @Date 2023/7/26 9:36
 * @PackageName:com.yc.springtest1
 * @ClassName: Student
 * @Description:
 * @Version 1.0
 */
@Data
@AllArgsConstructor  //带所有参数的构造方法
@NoArgsConstructor  //不带参数的构造方法
public class Student {
    private String name;
    private double height;
    private double weight;
}