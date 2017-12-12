package com.example.lyl.myapplication.recyclerview;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author lyl
 * @date 2017/12/5.
 * baseRecyclerviewAdapterHelper 分组布局
 */

public class MultipleItem implements MultiItemEntity {
    private int itemType;

    private String content;
    public static final int TEXT = 1;
    public static final int IMG = 2;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MultipleItem(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
