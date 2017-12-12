package com.example.lyl.myapplication.text_database;

/**
 * Created by lyl on 2017/8/16.
 */

public class Citys {
    private String parentCity;
    private String childCity;
    private String pinyin;
    private String phoneCode;
    private String cityID;
    private String areaCode;

    public Citys(String parentCity, String childCity, String pinyin, String phoneCode, String cityID, String areaCode) {
        this.parentCity = parentCity;
        this.childCity = childCity;
        this.pinyin = pinyin;
        this.phoneCode = phoneCode;
        this.cityID = cityID;
        this.areaCode = areaCode;
    }

    public String getParentCity() {
        return parentCity;
    }

    public void setParentCity(String parentCity) {
        this.parentCity = parentCity;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getChildCity() {
        return childCity;
    }

    public void setChildCity(String childCity) {
        this.childCity = childCity;
    }
}
