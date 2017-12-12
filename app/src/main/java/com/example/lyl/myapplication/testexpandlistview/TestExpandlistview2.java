package com.example.lyl.myapplication.testexpandlistview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lyl.myapplication.R;
import com.example.lyl.myapplication.bean.WorkSheetRecord;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyl on 2017/9/11.
 */

public class TestExpandlistview2 extends AppCompatActivity {

//    public String[] groupStrings = {"西游记", "水浒传", "三国演义", "红楼梦"};
//    public String[][] childStrings = {
//            {"唐三藏", "孙悟空", "猪八戒", "沙和尚"},
//            {"宋江", "林冲", "李逵", "鲁智深"},
//            {"曹操", "刘备", "孙权", "诸葛亮", "周瑜"},
//            {"贾宝玉", "林黛玉", "薛宝钗", "王熙凤"}
//    };


    // private List<String> stringList = new ArrayList<>();

    private List<WorkSheetRecord.DataBean> datas = new ArrayList<>();


    private ExpandableListView expandableListView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandlist);
        initData();
        expandableListView = (ExpandableListView) findViewById(R.id.expandlistview);

        expandableListView.setAdapter(new MyAdapter());
        // expandableListView.setGroupIndicator(null);//设置指示器无

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // Toast.makeText(TestExpandlistview2.this, childStrings[groupPosition][childPosition], Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void initData() {

        //json原生解析
        for (int i = 0; i < 5; i++) {
            WorkSheetRecord.DataBean databean = new WorkSheetRecord.DataBean();
            WorkSheetRecord.DataBean.RecordInfo record = new WorkSheetRecord.DataBean.RecordInfo();
            List<WorkSheetRecord.DataBean.RecordInfo> records = new ArrayList<>();
            databean.setHandler(i);
            databean.setHandleTime("handleTime" + i);
            databean.setHandleType("handleType" + i);
            databean.setTitle("title" + i);
            List<String> imageUrl = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                if (i % 2 == 0) {
                } else {
                    imageUrl.add("imageUrl" + j);
                }
            }
            record.setPicUrl(imageUrl);
            record.setHandleResult("handleResult" + i);
            List<String> assist = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                assist.add("assist" + i);
            }
            record.setAssist(assist);
            records.add(record);
            databean.setRecordInfoList(records);
            datas.add(databean);
        }

    }

    class MyAdapter extends BaseExpandableListAdapter {
        //        获取分组的个数
        @Override
        public int getGroupCount() {
            return datas.size();
        }

        //        获取指定分组中的子选项的个数
        @Override
        public int getChildrenCount(int groupPosition) {
            return datas.get(groupPosition).getRecordInfoList().size();
        }

        //        获取指定的分组数据
        @Override
        public Object getGroup(int groupPosition) {
            return datas.get(groupPosition);
        }

        //        获取指定分组中的指定子选项数据
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return datas.get(groupPosition).getRecordInfoList();
        }

        //        获取指定分组的ID, 这个ID必须是唯一的
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        //        获取子选项的ID, 这个ID必须是唯一的
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        //        分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们。
        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupViewHolder groupViewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(TestExpandlistview2.this).inflate(R.layout.expanditem, parent, false);
                groupViewHolder = new GroupViewHolder();
                groupViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_expand);
                convertView.setTag(groupViewHolder);
            } else {
                groupViewHolder = (GroupViewHolder) convertView.getTag();
            }
            groupViewHolder.tvTitle.setText(datas.get(groupPosition).getTitle());
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildViewHolder childViewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(TestExpandlistview2.this).inflate(R.layout.subitem, parent, false);
                childViewHolder = new ChildViewHolder();
                childViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_sub);
                childViewHolder.gridView = (GridView) convertView.findViewById(R.id.sub_listview);
                childViewHolder.tv_ass = (TextView) convertView.findViewById(R.id.tv_ass);



                convertView.setTag(childViewHolder);
            } else {
                childViewHolder = (ChildViewHolder) convertView.getTag();
            }
            StringBuffer string = new StringBuffer();
            for (int i = 0; i < datas.get(groupPosition).getRecordInfoList().get(childPosition).getAssist().size(); i++) {
                string.append(datas.get(groupPosition).getRecordInfoList().get(childPosition).getAssist().get(i) + "i\n");
            }
            childViewHolder.gridView
                    .setAdapter(new GridViewAdapter(datas.get(groupPosition).getRecordInfoList().get(childPosition).getPicUrl(), TestExpandlistview2.this));
            childViewHolder.tv_ass.setText(string.toString());
            childViewHolder.tvTitle.setText(datas.get(groupPosition).getRecordInfoList().get(childPosition).getHandleResult());
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    static class GroupViewHolder {
        TextView tvTitle;
    }

    static class ChildViewHolder {
        GridView gridView;
        TextView tvTitle;
        TextView tv_ass;
    }
}
