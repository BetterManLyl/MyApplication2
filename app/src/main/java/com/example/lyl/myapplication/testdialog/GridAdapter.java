package com.example.lyl.myapplication.testdialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.lyl.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lyl on 2017/8/23.
 */

public class GridAdapter extends BaseAdapter {
    private Context context;
    private List<Item> items = new ArrayList<>();

    private HashMap<Integer, Boolean> localHashMap = new HashMap<>();

    public GridAdapter(Context context,
                       List<Item> items) {
        this.context = context;
        this.items = items;
        initMap(false);
    }

    //初始化map集合
    private void initMap(boolean tag) {
        localHashMap.clear();
        for (int i = 0; i < items.size(); i++) {
            localHashMap.put(i, tag);
        }
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHoler myViewHoler = new MyViewHoler();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, null);
            myViewHoler.button = (Button) convertView.findViewById(R.id.btn_check);
            convertView.setTag(myViewHoler);
        } else {
            myViewHoler = (MyViewHoler) convertView.getTag();
        }
        myViewHoler.button.setText(items.get(position).getPosition() + "");

        if (localHashMap.get(position)) {
            //Toast.makeText(context, "点击了选中" + position, Toast.LENGTH_SHORT).show();
            myViewHoler.button.setBackgroundResource(R.drawable.btn_eg);
        } else {
            // Toast.makeText(context, "点击了删除" + position, Toast.LENGTH_SHORT).show();
            myViewHoler.button.setBackgroundResource(R.drawable.btn_bg_white);
        }
        final MyViewHoler finalMyViewHoler = myViewHoler;
        myViewHoler.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击前先判断是否已经选中过
                if (localHashMap.get(position)) {
                    //Toast.makeText(context, "点击了选中" + position, Toast.LENGTH_SHORT).show();
                    localHashMap.put(position, false);
                    finalMyViewHoler.button.setBackgroundResource(R.drawable.btn_bg_white);
                } else {
                   // Toast.makeText(context, "点击了删除" + position, Toast.LENGTH_SHORT).show();
                    localHashMap.put(position, true);
                    finalMyViewHoler.button.setBackgroundResource(R.drawable.btn_eg);
                }

            }
        });
        return convertView;
    }

    //获取选中的数据的集合
    public List<String> getList() {
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < localHashMap.size(); i++) {
            if (localHashMap.get(i)) {
                lists.add(i+1 + "");
            }
        }
        return lists;
    }

    public void setSelecteAll(){
        for (int i = 0; i <localHashMap.size() ; i++) {
            localHashMap.put(i,true);
        }

        notifyDataSetChanged();
    }

    public void setSelecteAllDelere(){
        for (int i = 0; i <localHashMap.size() ; i++) {
            localHashMap.put(i,false);
        }
        notifyDataSetChanged();
    }

    static class MyViewHoler {
        Button button;
    }
}
