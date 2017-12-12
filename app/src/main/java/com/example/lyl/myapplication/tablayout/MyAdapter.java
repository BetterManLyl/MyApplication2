package com.example.lyl.myapplication.tablayout;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lyl.myapplication.R;

import java.util.List;

/**
 * @author lyl
 * @date 2017/12/8.
 * 适配器
 */

public class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MyAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_text, item)
                .addOnClickListener(R.id.tv_text);
    }
}
