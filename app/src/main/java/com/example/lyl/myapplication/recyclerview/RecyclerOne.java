package com.example.lyl.myapplication.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.rxandroid.DividerItemDecoration;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lyl
 * @date 2017/12/4.
 */

public class RecyclerOne extends AppCompatActivity {

    @BindView(R.id.recyclerview_one)
    RecyclerView recyclerviewOne;
    private RecycleroneAdapter adapter;
    private List<String> list = new ArrayList<>();
    private List<Integer> images = new ArrayList<>();
    private Banner banner;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_one);
        ButterKnife.bind(this);
        initRecyclerView();
        initClick();
        setHeadView(recyclerviewOne);
        setFootView(recyclerviewOne);
        initData();
        //添加头部轮播图
        initBanner();
    }

    private void initBanner() {
        for (int i = 0; i < 5; i++) {
            images.add(R.mipmap.ic_launcher);
        }
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    //底部
    private void setFootView(RecyclerView view) {
        View footView = LayoutInflater.from(this).inflate(R.layout.foot_view, view, false);
        TextView text = (TextView) footView.findViewById(R.id.tv_foot);
        text.setText("foot title");
        adapter.setFootView(footView);
        footView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RecyclerOne.this, "点击了底部", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //头部
    private void setHeadView(RecyclerView view) {
        View headView = LayoutInflater.from(this).inflate(R.layout.head_view, view, false);
        TextView text = (TextView) headView.findViewById(R.id.tv_head);
        banner = (Banner) headView.findViewById(R.id.banner);
        text.setText("head title");
        adapter.setHeadView(headView);
        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RecyclerOne.this, "点击了头部", Toast.LENGTH_SHORT).show();
            }
        });
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(RecyclerOne.this, "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initClick() {
        if (adapter != null) {
            adapter.setOnClickListener(new RecycleroneAdapter.OnClickListener() {
                @Override
                public void onClick(String content) {
                    Toast.makeText(RecyclerOne.this, content, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            list.add("第" + i + "个");
        }
        adapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        LinearLayoutManager ll = new LinearLayoutManager(this);
//        recyclerviewOne.addItemDecoration(new DividerItemDecoration(this,
//                DividerItemDecoration.VERTICAL_LIST));
        recyclerviewOne.setLayoutManager(ll);
        adapter = new RecycleroneAdapter(list);
        recyclerviewOne.setAdapter(adapter);
    }
}
