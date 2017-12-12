package com.example.lyl.myapplication.recyclerview;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lyl.myapplication.R;

import java.util.List;

/**
 * @author lyl
 * @date 2017/12/5.
 * 多布局
 */

public class BaseAdapterMultiple extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BaseAdapterMultiple(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.IMG, R.layout.base_adapter_mult_oneitem);
        addItemType(MultipleItem.TEXT, R.layout.base_adapter_mult_twoitem);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (item.getItemType()) {
            case MultipleItem.IMG:
                helper.setText(R.id.tv_content, item.getContent());
                break;
            case MultipleItem.TEXT:
                helper.setText(R.id.tv_content_two, item.getContent());
                break;
            default:
                break;
        }
    }
}
