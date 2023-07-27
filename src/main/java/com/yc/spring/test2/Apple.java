package com.yc.spring.test2;

/**
 * @Author zp
 * @Date 2023/7/25 20:32
 * @PackageName:com.yc.spring.test2
 * @ClassName: Apple
 * @Description:
 * @Version 1.0
 */
public class Apple {
    private int id;

    public Apple() {
        System.out.println("Apple构造");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "id=" + id +
                '}';
    }
}