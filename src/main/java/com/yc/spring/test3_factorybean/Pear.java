package com.yc.spring.test3_factorybean;

/**
 * @Author zp
 * @Date 2023/7/25 20:50
 * @PackageName:com.yc.spring.test3
 * @ClassName: Pear
 * @Description:
 * @Version 1.0
 */
public class Pear {
    private int id;

    public Pear() {
        System.out.println("构造了pear");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pear{" +
                "id=" + id +
                '}';
    }
}