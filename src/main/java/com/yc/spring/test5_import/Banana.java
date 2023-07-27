package com.yc.spring.test5_import;

/**
 * @Author zp
 * @Date 2023/7/26 15:51
 * @PackageName:com.yc.spring.test5
 * @ClassName: Banana
 * @Description:
 * @Version 1.0
 */
public class Banana {
    private int id;

    public Banana() {
        System.out.println("香蕉");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Banana{" +
                "id=" + id +
                '}';
    }
}