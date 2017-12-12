package com.example.lyl.myapplication.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lyl on 2017/9/12.
 */

public class Liebiao implements Serializable {


    private int position;
    private List<Liebiao2> liebiao2List;
    private String message;

    public Liebiao(String message) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<Liebiao2> getLiebiao2List() {
        return liebiao2List;
    }

    public void setLiebiao2List(List<Liebiao2> liebiao2List) {
        this.liebiao2List = liebiao2List;
    }
}
