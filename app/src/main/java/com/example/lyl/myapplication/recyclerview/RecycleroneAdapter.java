package com.example.lyl.myapplication.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lyl.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lyl
 * @date 2017/12/4.
 */

public class RecycleroneAdapter extends RecyclerView.Adapter<RecycleroneAdapter.ViewHolder> {
    public static final int TYPE_HEADER = 0;  //说明是带有Header的
    public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
    public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的


    private List<String> list = new ArrayList<>();

    public View headView;
    public View footView;
    private String TAG="lyl1";

    public void setFootView(View footView) {
        Log.e(TAG, "setFootView: " );
        this.footView = footView;
        //插入底部
        notifyItemInserted(getItemCount() - 1);
    }

    public void setHeadView(View headView) {
        Log.e(TAG, "setHeadView: " );
        this.headView = headView;
        //插入头部
        notifyItemInserted(0);
    }

    public RecycleroneAdapter(List<String> list) {
        Log.e(TAG, "RecycleroneAdapter: " );
        this.list = list;
    }

    @Override
    public RecycleroneAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder: " );
        if (headView != null && viewType == TYPE_HEADER) {
            return new ViewHolder(headView);
        }
        if (footView != null && viewType == TYPE_FOOTER) {
            return new ViewHolder(footView);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecycleroneAdapter.ViewHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder: " );
        if(getItemViewType(position) == TYPE_NORMAL){
            if(holder instanceof ViewHolder) {
                //这里加载数据的时候要注意，是从position-1开始，因为position==0已经被header占用了
                holder.textView.setText(list.get(position-1));
                holder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickListener.onClick(list.get(position-1));
                    }
                });
                return;
            }
            return;
        }else if(getItemViewType(position) == TYPE_HEADER){
            return;
        }else{
            return;
        }
    }

    @Override
    public int getItemViewType(int position) {
        Log.e(TAG, "getItemViewType: " );
        if (headView == null && footView == null) {
            return TYPE_NORMAL;
        }
        if (position == 0) {
            //第一个item应该加载Header
            return TYPE_HEADER;
        }
        if (position == getItemCount() - 1) {
            //最后一个,应该加载Footer
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount: " );
        if (headView == null && footView == null) {
            return list.size();
        } else if (headView == null && footView != null) {
            return list.size() + 1;
        } else if (headView != null && footView == null) {
            return list.size() + 1;
        } else {
            return list.size() + 2;
        }
    }

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(String content);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View itemView) {

            super(itemView);
            //如果是headerview或者是footerview,直接返回
            if (itemView == headView) {
                return;
            }
            if (itemView == footView) {
                return;
            }
            textView = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }
}
