package com.example.lyl.myapplication.testexpandlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.lyl.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyl on 2017/9/12.
 */

public class GridViewAdapter extends BaseAdapter {

    private List<String> stringList = new ArrayList<>();
    private Context context;

    public GridViewAdapter(List<String> stringList, Context context) {
        this.stringList = stringList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {
        return stringList.get(position);
    }

    @Override
    public long getItemId(int c) {
        return c;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_item, null);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = view;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.button= (Button) convertView.findViewById(R.id.btn_check);
        viewHolder.button.setText(stringList.get(position).toString());

        return convertView;
    }

    static class ViewHolder {
        Button button;
    }
}
