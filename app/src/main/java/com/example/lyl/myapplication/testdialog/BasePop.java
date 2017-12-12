package com.example.lyl.myapplication.testdialog;

import android.view.View;

/**
 * Created by lyl on 2017/7/23.
 * 该接口提供两个功能：

 得到popup的view(即我们需要inflate的xml)
 得到需要播放动画的view
 */

public interface BasePop {

    View getPopView();
    View getAnimView();
}
