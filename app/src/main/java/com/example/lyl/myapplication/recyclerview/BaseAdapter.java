package com.example.lyl.myapplication.recyclerview;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lyl.myapplication.R;

import java.util.List;

/**
 * @author lyl
 * @date 2017/12/5.
 * 单布局
 */

public class BaseAdapter extends BaseItemDraggableAdapter<String, BaseViewHolder> {
    public BaseAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_base, item).
                setText(R.id.tv_content, item)
                .setText(R.id.tv_content_two,item)
                .addOnClickListener(R.id.tv_content_two)
                .addOnClickListener(R.id.tv_content);
    }
}
