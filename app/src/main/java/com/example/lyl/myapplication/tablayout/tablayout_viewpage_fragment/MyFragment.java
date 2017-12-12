package com.example.lyl.myapplication.tablayout.tablayout_viewpage_fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.tablayout.event.FragmentEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

/**
 * @author lyl
 * @date 2017/12/8.
 * MyFragment
 */

public class MyFragment extends BaseLazyFragment {
    @BindView(R.id.tv_fragment)
    TextView tvFragment;
    private int position;


    @Override
    public int getLayoutId() {
        return R.layout.lazy_fragment;
    }

    @Override
    public void initView(View view) {
        EventBus.getDefault().register(this);
        Bundle bundle = this.getArguments();
        position = bundle.getInt("position", 0);
        tvFragment.setText("第" + position + "个");
        super.initView(view);
    }

    @Subscribe
    public void onEvent(FragmentEvent event){
        LogUtils.eTag("lyl1","onEvent");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
