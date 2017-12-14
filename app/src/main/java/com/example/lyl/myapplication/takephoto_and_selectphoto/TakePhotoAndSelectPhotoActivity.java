package com.example.lyl.myapplication.takephoto_and_selectphoto;

import android.widget.Button;

import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author lyl
 * @date 2017/12/14.
 */

public class TakePhotoAndSelectPhotoActivity extends BaseActivity {
    @BindView(R.id.btn_take_photo)
    Button btnTakePhoto;

    @Override
    public int getLayoutId() {
        return R.layout.take_photo;
    }


    @OnClick(R.id.btn_take_photo)
    public void onViewClicked() {
    }
}
