package com.example.lyl.myapplication.customview.draw_circle_percent;

/**
 * @author lyl
 * @date 2017/12/19.
 * 数据
 */

public class Model {

    // 用户关心数据
    public String name;        // 名字
    public float value;        // 数值
    public float percentage;   // 百分比

    // 非用户关心数据
    public int color = 0;      // 颜色
    public float angle = 0;    // 角度

    public Model(String name, float value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
