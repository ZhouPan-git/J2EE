package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author zp
 * @Date 2023/8/3 9:25
 * @PackageName:com.yc.bean
 * @ClassName: OpRecord
 * @Description:
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OpRecord {
    private int id;;
    private int accountid;
    private double opmoney;
    private String optime;  //数据库是datetime 在java中转为string
    private OpType optype;//Optype 类型
    private Integer transferid;
}