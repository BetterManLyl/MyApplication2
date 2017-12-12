package com.example.lyl.myapplication.rxjava.login.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author lyl
 * @date 2017/11/17.
 */

public class Login implements Serializable {

    /**
     * headCompany : 热力总公司
     * identification : 20170607085554
     * menu : ["单站监测","运行排名","我的工单","工单派工","协助工单","团队工单","网络设备监测","站表监测","楼表监测","楼栋阀门监测","住户监测"]
     * message : 登录成功
     * realName : 芒果
     * realNameId : 4
     * result : true
     * token : FB3609316B11484F30B1B4DCACF5A065
     * userName : zlq
     */

    private String headCompany;
    private String identification;
    private String message;
    private String realName;
    private int realNameId;
    private boolean result;
    private String token;
    private String userName;
    private List<String> menu;

    public String getHeadCompany() {
        return headCompany;
    }

    public void setHeadCompany(String headCompany) {
        this.headCompany = headCompany;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getRealNameId() {
        return realNameId;
    }

    public void setRealNameId(int realNameId) {
        this.realNameId = realNameId;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getMenu() {
        return menu;
    }

    public void setMenu(List<String> menu) {
        this.menu = menu;
    }
}
