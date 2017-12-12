package com.example.lyl.myapplication.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lyl.myapplication.BaseActivity;
import com.example.lyl.myapplication.R;

/**
 * @author lyl
 * @date 2017/11/16.
 */

public class RxBusActivity extends BaseActivity {
    private Button rxbus;
    private EditText ed_text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxbus);
        rxbus = (Button) findViewById(R.id.rxbus);
        ed_text = (EditText) findViewById(R.id.ed_text);
        rxbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在此处相当于被观察者发出了消息
                RxBus.getInstance().post(ed_text.getText().toString());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
