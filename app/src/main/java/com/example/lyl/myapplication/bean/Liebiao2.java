package com.example.lyl.myapplication.bean;

import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by lyl on 2017/9/12.
 */

public  class Liebiao2 implements Serializable{
    private int position;
    private ImageView imageView;

    public Liebiao2(ImageView imageView){
        this.imageView=imageView;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
