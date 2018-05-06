package com.ietree.xmlpullparser;

/**
 * Created by Root on 2018/5/2.
 */
public class Product {
    private String type;
    private String phonenum;
    private String location;
    private String phoneJx;

    public Product() {
    }

    public Product(String phonenum, String location, String phoneJx, String type) {
        this.phonenum = phonenum;
        this.location = location;
        this.phoneJx = phoneJx;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneJx() {
        return phoneJx;
    }

    public void setPhoneJx(String phoneJx) {
        this.phoneJx = phoneJx;
    }

    @Override
    public String toString() {
        return "Product{" +
                "type='" + type + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", location='" + location + '\'' +
                ", phoneJx='" + phoneJx + '\'' +
                '}';
    }
}
