package com.example.lyl.myapplication.bmob;

import cn.bmob.v3.BmobObject;

/**
 * @author lyl
 * @date 2017/12/11.
 */

public class Person extends BmobObject {
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
