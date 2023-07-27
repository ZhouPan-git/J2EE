package com.yc.spring.test1;

/**
 * @Author zp
 * @Date 2023/7/25 20:20
 * @PackageName:com.yc.spring
 * @ClassName: Address
 * @Description:
 * @Version 1.0
 */
public class Address {
    private String province;
    private String city;

    public Address() {
        System.out.println("地址构造");
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        System.out.println("setProvince:"+province);
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        System.out.println("setCity:"+city);
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}