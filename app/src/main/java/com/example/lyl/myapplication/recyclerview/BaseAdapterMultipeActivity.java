package com.example.lyl.myapplication.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.rxandroid.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lyl
 * @date 2017/12/5.
 */

public class BaseAdapterMultipeActivity extends AppCompatActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private BaseAdapterMultiple baseAdapter;
    private List<MultipleItem> lists = new ArrayList<>();
    private LinearLayoutManager ll;
    private MultipleItem multipleItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baserecyclerviewadapter_activity);
        ButterKnife.bind(this);
        initRecycler();
    }

    public void initData() {
        for (int i = 0; i < 20; i++) {
            if (i > 10) {
                multipleItem = new MultipleItem(1);
                multipleItem.setContent("大于10");
            } else {
                multipleItem = new MultipleItem(2);
                multipleItem.setContent("小于10");
            }
            lists.add(multipleItem);
        }
        if (baseAdapter != null) {
            baseAdapter.notifyDataSetChanged();
        }
    }

    private void initRecycler() {
        initData();
        ll = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(ll);

        baseAdapter = new BaseAdapterMultiple(lists);
        //开启动画 默认动画
        //baseAdapter.openLoadAnimation();
        //设置动画 默认提供5种方法（渐显、缩放、从下到上，从左到右、从右到左）
        // baseAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);

        recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerview.setAdapter(baseAdapter);

        //删除指定view
        //baseAdapter.removeHeaderView(getView);
        //baseAdapter.removeFooterView(getView);

        /**
         * 滑动最后一个Item的时候回调onLoadMoreRequested方法
         */
        baseAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                initData();
                Toast.makeText(BaseAdapterMultipeActivity.this, "加载更多", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 设置点击事件
         */
        baseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(BaseAdapterMultipeActivity.this, "点击了" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * 设置item内部点击事件
         */
        baseAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                /**
                 * 通过view的id判断内部不同控件的点击事件
                 */
                if (view.getId() == R.id.tv_content) {
                    Toast.makeText(BaseAdapterMultipeActivity.this, "内部点击了" + position,
                            Toast.LENGTH_SHORT).show();
                } else if (view.getId() == R.id.tv_content_two) {
                    Toast.makeText(BaseAdapterMultipeActivity.this, "内部点击了tv_content_two" + position,
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}
