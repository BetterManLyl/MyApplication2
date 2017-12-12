package com.example.lyl.myapplication.rxandroid;

import java.io.Serializable;

/**
 * @author lyl
 * @date 2017/11/16.
 */

public class Appinfo implements Serializable {

    private String appName;
    private int id;
    private int pageCount;

    public Appinfo(String appName, int id, int pageCount) {
        this.appName = appName;
        this.id = id;
        this.pageCount = pageCount;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
