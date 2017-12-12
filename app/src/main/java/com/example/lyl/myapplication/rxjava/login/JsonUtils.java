package com.example.lyl.myapplication.rxjava.login;

import com.example.lyl.myapplication.rxjava.login.entity.Login;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author lyl
 * @date 2017/11/17.
 */

public class JsonUtils {




    public static  void analyseJson(JSONObject jsonObject,Login login){
        try {
            String headCompany =jsonObject.has("headCompany")?jsonObject.getString("headCompany"):"";
            login.setHeadCompany(headCompany);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
