package com.example.lyl.myapplication.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lyl.myapplication.BaseActivity2;
import com.example.lyl.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author lyl
 * @date 2017/12/4.
 */

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_recycler_ont, R.id.btn_baserecycler_adapter, R.id.btn_baserecycler_muliadapter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_recycler_ont:
                startActivity(new Intent(this, RecyclerOne.class));
                break;
            case R.id.btn_baserecycler_adapter:
                startActivity(new Intent(this, BaseRecyclerViewAdapterHelperActivity.class));
                break;
            case R.id.btn_baserecycler_muliadapter:
                startActivity(new Intent(this, BaseAdapterMultipeActivity.class));
                break;
            default:
                break;
        }
    }
}
