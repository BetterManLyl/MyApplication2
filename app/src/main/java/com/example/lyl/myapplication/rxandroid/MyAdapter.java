package com.example.lyl.myapplication.rxandroid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lyl.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lyl
 * @date 2017/11/16.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Appinfo> list = new ArrayList<>();

    public MyAdapter(List<Appinfo> list) {
        this.list = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rxandroid_item, parent,false  );


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    private OnClickListener onClickListener;
    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }
    interface  OnClickListener{
        public void onClick(int position);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.appname.setText(list.get(position).getAppName());
        holder.appid.setText(list.get(position).getId() + "");
        holder.apppage.setText(list.get(position).getPageCount() + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView appname;
        TextView appid;
        TextView apppage;

        public MyViewHolder(View itemView) {
            super(itemView);
            appname = (TextView) itemView.findViewById(R.id.appname);
            appid = (TextView) itemView.findViewById(R.id.appid);
            apppage = (TextView) itemView.findViewById(R.id.apppage);
        }
    }
}
