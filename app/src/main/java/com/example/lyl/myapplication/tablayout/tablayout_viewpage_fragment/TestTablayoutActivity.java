package com.example.lyl.myapplication.tablayout.tablayout_viewpage_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.widget.Button;

import com.blankj.utilcode.util.LogUtils;
import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.base.BaseActivity;
import com.example.lyl.myapplication.tablayout.event.FragmentEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author lyl
 * @date 2017/12/8.
 */

public class TestTablayoutActivity extends BaseActivity {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.btn_event)
    Button btnEvent;
    private int length;

    private int[] fragments;

    private MyFragmentPageAdapter myFragmentPageAdapter;

    private String[] titles;

    @Override
    public void getExtraValue() {
        super.getExtraValue();
        Intent intent = this.getIntent();
        length = intent.getIntExtra("position", 1);
        fragments = new int[length];
        titles = new String[length];
    }

    @Override
    public void initView() {
        super.initView();
        myFragmentPageAdapter = new MyFragmentPageAdapter(getSupportFragmentManager(), this);
        for (int i = 0; i < fragments.length; i++) {
            myFragmentPageAdapter.addFragment(i);
        }
        viewpager.setAdapter(myFragmentPageAdapter);
        tablayout.setupWithViewPager(viewpager);
        //要在setAdapter和setupWithViewPager之后
        for (int i = 0; i < fragments.length; i++) {
            TabLayout.Tab tab = tablayout.getTabAt(i);
            tab.setCustomView(myFragmentPageAdapter.getTabView(i));
        }
        tablayout.setTabGravity(Gravity.CENTER);
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LogUtils.eTag(BaseLazyFragment.TAG, tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_tablayout;
    }



    @OnClick(R.id.btn_event)
    public void onViewClicked() {
        EventBus.getDefault().post(new FragmentEvent());
    }
}
