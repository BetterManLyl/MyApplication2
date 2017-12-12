package com.example.lyl.myapplication.customview;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ShareActionProvider;

import com.example.lyl.myapplication.BaseActivity;
import com.example.lyl.myapplication.R;

/**
 * @author lyl
 * @date 2017/11/7.
 */

public class CustomView extends BaseActivity {
    private MyViewpager myViewpager;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int[] ids = {R.mipmap.ic_launcher,
            R.mipmap.menu3, R.mipmap.menu3_grey, R.mipmap.menu4, R.mipmap.menu4_grey};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customview);
        myViewpager = (MyViewpager) findViewById(R.id.viewpager);
        sharedPreferences=getSharedPreferences("lyl", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        save();
        gets();
        //自定义viewpager
        for (int i = 0; i < ids.length; i++) {
            ImageView image = new ImageView(this);
            image.setBackgroundResource(ids[i]);
            //为viewpager添加view
            myViewpager.addView(image);
        }
    }

    public void save(){
        editor.putString("cml","陈曼丽");
        editor.commit();
    }

    public String gets(){

        Log.e(TAG, sharedPreferences.getString("cml","0"));
        return sharedPreferences.getString("cml","0");
    }
}
