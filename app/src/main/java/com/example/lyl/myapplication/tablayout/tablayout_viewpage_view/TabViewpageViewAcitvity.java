package com.example.lyl.myapplication.tablayout.tablayout_viewpage_view;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.base.BaseActivity;
import com.example.lyl.myapplication.testdialog.GridAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author lyl
 * @date 2017/12/8.
 */

public class TabViewpageViewAcitvity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tab_ll)
    LinearLayout tabLl;
    private int length;
    private int[] views;
    private String[] titles;
    private MyPageAdapter myPageAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.viewpager;
    }


    @Override
    public void getExtraValue() {
        super.getExtraValue();
        length = intent.getIntExtra("position", 1);
        views = new int[length];
        titles = new String[length];
    }


    @Override
    public void initView() {
        super.initView();
        myPageAdapter = new MyPageAdapter();
        for (int i = 0; i < views.length; i++) {
            myPageAdapter.addView(i);
        }
        viewpager.setAdapter(myPageAdapter);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LogUtils.eTag("test","onPageSelected");
                updateTv(position);
                updataTabImageView(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        llView();
        updateTv(0);
        updataTabImageView(0);

    }

    private void updataTabImageView(int position) {
        tabLl.removeAllViews();
        for (int i = 0; i < length; i++) {
            ImageView image = new ImageView(this);
            if (i==position){
                image.setImageResource(R.drawable.circle_blue);
            }else{
                image.setImageResource(R.drawable.circle_black);
            }

            tabLl.addView(image);
        }
    }

    public void llView() {
        for (int i = 0; i < length; i++) {
            ImageView image = new ImageView(this);
            image.setImageResource(R.drawable.tab_image_selector);
            tabLl.addView(image);
        }
    }

    public void updateTv(int position) {
        View view = myPageAdapter.getCurrentView(position);
        TextView te = (TextView) view.findViewById(R.id.tv_tab_viewpage);
         te.setText("当前页为第" + position + "页");


    }

    class MyPageAdapter extends PagerAdapter {

        private List<View> viewList = new ArrayList<>();
        private List<String> titles = new ArrayList<>();
        private List<Integer> list = new ArrayList<>();


        public void addView(int position) {
            View viwe = LayoutInflater.from(TabViewpageViewAcitvity.this).inflate(R.layout.tab_view1, null);
            viewList.add(viwe);
            titles.add("第" + position + "个");
            list.add(R.drawable.tab_image_selector);
        }

        public View getCurrentView(int position) {
            return viewList.get(position);
        }

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));//添加页卡
            return viewList.get(position);

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));//删除页卡
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }
}
