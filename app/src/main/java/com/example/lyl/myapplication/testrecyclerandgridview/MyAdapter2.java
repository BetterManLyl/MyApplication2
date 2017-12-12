package com.example.lyl.myapplication.testrecyclerandgridview;

import android.security.keystore.KeyNotYetValidException;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.bean.Liebiao2;

import java.util.List;

/**
 * Created by lyl on 2017/9/12.
 */

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> {

    private List<Liebiao2> liebiao2List;

    public MyAdapter2(List<Liebiao2> liebiao2List) {
        this.liebiao2List = liebiao2List;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridview_item, parent, false);
        MyViewHolder myViewHold = new MyViewHolder(view);
        return myViewHold;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.iv.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return liebiao2List.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;

        public MyViewHolder(View view) {
            super(view);
            iv = (ImageView) view.findViewById(R.id.iv);
        }
    }
}
