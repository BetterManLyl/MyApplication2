package com.example.lyl.myapplication.bean;

import java.io.Serializable;

/**
 * Created by lyl on 2017/8/17.
 */

public class User implements Serializable {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
