package com.mylibrary.swslibrary.utils.hiitem;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * @author Sws
 * @Time 2021/12/15 23:38
 * @msg
 **/
class TopTabDataItem extends HiDateItem<ItemData, RecyclerView.ViewHolder>{
    public TopTabDataItem(@NotNull Date date) {
        super(date);
    }

    @Override
    public void onBindData(@NotNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemLayoutRes() {
        // 返回布局id
        return super.getItemLayoutRes();
    }
}
