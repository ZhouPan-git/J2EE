package com.yc.bean;

/**
 * @Author zp
 * @Date 2023/8/3 9:25
 * @PackageName:com.yc.bean
 * @ClassName: OpType
 * @Description:
 * @Version 1.0
 */
//enum: 枚举 enumration
public enum OpType {
    WITHDRAW("withdraw",1),DEPOSITE("deposite",2),TRANSFER("transfer",3);
    private String key;
    private int value;

    OpType(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}