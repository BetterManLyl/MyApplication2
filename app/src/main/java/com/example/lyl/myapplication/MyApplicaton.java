package com.example.lyl.myapplication;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.example.lyl.myapplication.bean.User;
import com.example.lyl.myapplication.takephoto_and_selectphoto.GlideImageLoader;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;

import java.util.HashMap;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cn.bmob.v3.Bmob;

/**
 * Created by lyl on 2017/8/11.
 */

public class MyApplicaton extends Application {

    public HashMap<String, Object> localHashMap = new HashMap<>();
                                       //得到ImageOptions对象

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




        //图片选择
        ImagePicker imagePicker
                = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
      //  imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素

    }


}
