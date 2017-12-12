package com.example.lyl.myapplication.testrecyclerandgridview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.bean.Liebiao;
import com.example.lyl.myapplication.bean.Liebiao2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyl on 2017/9/12.
 * 测试recyclerview嵌套gridview
 */

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private List<Liebiao> liebiaoList = new ArrayList<>();

    private MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerviewandgridview);

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);

        initData();

        initRecycler();

    }

    private void initRecycler() {
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        myAdapter = new MyAdapter(liebiaoList, this);

        recyclerview.setAdapter(myAdapter);
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {

            liebiaoList.add(new Liebiao("第" + i + "个"));
            List<Liebiao2> liebiao2 = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                liebiao2.add(new Liebiao2(new ImageView(this)));
            }
            liebiaoList.get(i).setLiebiao2List(liebiao2);
        }
    }
}
