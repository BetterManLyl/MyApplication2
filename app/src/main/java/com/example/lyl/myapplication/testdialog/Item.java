package com.example.lyl.myapplication.testdialog;

import java.io.Serializable;

/**
 * Created by lyl on 2017/8/23.
 */

public class Item implements Serializable {
    private int position;
    private String msg;


    public Item(int position,String msg){
        this.position=position;
        this.msg=msg;
    }
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
