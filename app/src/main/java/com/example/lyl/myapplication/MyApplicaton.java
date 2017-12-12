package com.example.lyl.myapplication;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.Utils;
import com.example.lyl.myapplication.bean.User;

import java.util.HashMap;
import java.util.List;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by lyl on 2017/8/11.
 */

public class MyApplicaton extends Application {

    public HashMap<String, Object> localHashMap = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         *
         * 工具类
         * 调用init方法同时开始执行 activity 的添加和移除
         *
         */
        Utils.init(this);
        CustomActivityOnCrash.install(this);
        User user = new User();
        user.setAge(15);
        user.setName("lyl");
        localHashMap.put("user", user);
        //第一：默认初始化
        Bmob.initialize(this, "bf9d4dc0e5c77a22f59df84c6984a13b");
    }


}
