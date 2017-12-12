package com.example.lyl.myapplication.testrecyclerandgridview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.bean.Liebiao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lyl on 2017/9/12.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHold> {

    private List<Liebiao> stringList = new ArrayList<>();
    private Context context;
    private Map<Integer, Boolean> localHashMap = new HashMap<>();


    public MyAdapter(List<Liebiao> stringList, Context context) {
        this.stringList = stringList;
        this.context = context;
        initBoolean();
    }

    private void initBoolean() {
        for (int i = 0; i < stringList.size(); i++) {
            localHashMap.put(i, false);
        }
    }

    @Override
    public MyViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expanditem, parent, false);
        MyViewHold myViewHold = new MyViewHold(view);
        return myViewHold;
    }

    @Override
    public void onBindViewHolder(final MyViewHold holder, final int position) {
        holder.textView.setText(stringList.get(position).getMessage());
        //初始化打开的页面
        if (localHashMap.get(position)){
            holder.rv_grid.setVisibility(View.VISIBLE);
            holder.img_up_down.setImageResource(R.mipmap.ic_expand_less);
        }else{
            holder.rv_grid.setVisibility(View.GONE);
            holder.img_up_down.setImageResource(R.mipmap.ic_expand_more);

        }
        MyAdapter2 imageAdapter = new MyAdapter2(stringList.get(position).getLiebiao2List());
        holder.rv_grid.setLayoutManager(new GridLayoutManager(context, 5));
        holder.rv_grid.setAdapter(imageAdapter);

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!localHashMap.get(position)) {
                    localHashMap.put(position, true);
                    if (stringList.get(position).getLiebiao2List().size() > 0) {

                        holder.rv_grid.setVisibility(View.VISIBLE);
                        holder.img_up_down.setImageResource(R.mipmap.ic_expand_less);

                    } else {
                        holder.rv_grid.setVisibility(View.GONE);
                        holder.img_up_down.setImageResource(R.mipmap.ic_expand_more);
                    }
                } else {
                    holder.rv_grid.setVisibility(View.GONE);
                    holder.img_up_down.setImageResource(R.mipmap.ic_expand_more);
                    localHashMap.put(position, false);
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return stringList.size();
    }

    static class MyViewHold extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView rv_grid;
       ImageView img_up_down;
        public MyViewHold(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_expand);
            rv_grid = (RecyclerView) itemView.findViewById(R.id.rv_grid);
            img_up_down= (ImageView) itemView.findViewById(R.id.img_up_down);
        }
    }
}
