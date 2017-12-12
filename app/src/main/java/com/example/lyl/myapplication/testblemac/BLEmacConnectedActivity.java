package com.example.lyl.myapplication.testblemac;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lyl.myapplication.BaseActivity;
import com.example.lyl.myapplication.MyApplicaton;
import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.bean.User;

import java.util.HashMap;

/**
 * Created by lyl on 2017/8/11.
 * BLE根据外设名称连接
 */

public class BLEmacConnectedActivity extends BaseActivity {
    private Toolbar toolbar;
    private EditText ed_text;
    private Button btn_send;
    private TextView tv_rece;
    private TextInputLayout inputEditText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mac_connected);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ed_text = (EditText) findViewById(R.id.ed_text);
        btn_send = (Button) findViewById(R.id.btn_send);
        inputEditText = (TextInputLayout) findViewById(R.id.tv_receiver);
        inputEditText.setHint("hint");
        btn_send.setOnClickListener(onClickListener);
        setToolBar(toolbar);
        setProgressBar(false);

        //application保存数据  对象
        MyApplicaton myApplicaton = (MyApplicaton) getApplication();
        User user = (User) myApplicaton.localHashMap.get("user");
        showToast(user.getName());
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!TextUtils.isEmpty(ed_text.getText().toString().trim())) {
                if (ed_text.getText().toString().length() < 6) {
                    inputEditText.setErrorEnabled(true);
                    inputEditText.setError("请输入6位以上");
                }
            } else {
                showToast("请输入内容");
            }
        }
    };
}
