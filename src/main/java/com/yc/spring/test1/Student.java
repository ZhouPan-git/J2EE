package com.yc.spring.test1;

/**
 * @Author zp
 * @Date 2023/7/25 19:46
 * @PackageName:com.yc.spring
 * @ClassName: Student
 * @Description:
 * @Version 1.0
 */
public class Student {
    private int id;
    private String name;
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Student() {
        System.out.println("构造了");
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        System.out.println("setid:"+id);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("setname:"+name);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}