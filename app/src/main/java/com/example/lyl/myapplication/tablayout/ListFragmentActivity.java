package com.example.lyl.myapplication.tablayout;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.base.BaseActivity;
import com.example.lyl.myapplication.rxandroid.DividerItemDecoration;
import com.example.lyl.myapplication.tablayout.tablayout_viewpage_fragment.TestTablayoutActivity;
import com.example.lyl.myapplication.tablayout.tablayout_viewpage_view.TabViewpageViewAcitvity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author lyl
 * @date 2017/12/8.
 */

public class ListFragmentActivity extends BaseActivity {
    @BindView(R.id.rl_list_fragment)
    RecyclerView rlListFragment;

    private MyAdapter myAdapter;
    private List<String> list = new ArrayList<>();

    private String type;


    @Override
    public void getExtraValue() {
        super.getExtraValue();
        Intent intent=this.getIntent();
        type=intent.getStringExtra("type");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_list_fragment;
    }

    @Override
    public void initView() {
        super.initView();
        LinearLayoutManager ll = new LinearLayoutManager(this);
        rlListFragment.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        rlListFragment.setLayoutManager(ll);
        myAdapter = new MyAdapter(R.layout.list_fragment_item, list);
        rlListFragment.setAdapter(myAdapter);
        myAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (type.equals("fragment")){
                    Intent intent = new Intent(ListFragmentActivity.this, TestTablayoutActivity.class);
                    intent.putExtra("position", position);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(ListFragmentActivity.this, TabViewpageViewAcitvity.class);
                    intent.putExtra("position", position);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        for (int i = 0; i < 20; i++) {
            list.add("第" + i + "个");
        }
        myAdapter.notifyDataSetChanged();
    }
}
