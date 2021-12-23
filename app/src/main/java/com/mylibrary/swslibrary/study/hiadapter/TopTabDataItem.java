package com.mylibrary.swslibrary.study.hiadapter;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mylibrary.swslibrary.R;

import org.jetbrains.annotations.NotNull;

/**
 * @author Sws
 * @Time 2021/12/15 23:38
 * @msg
 **/
public class TopTabDataItem extends HiDateItem<ItemData, RecyclerView.ViewHolder>{
    public TopTabDataItem(@NotNull ItemData date) {
        super(date);
    }

    @Override
    public void onBindData(@NotNull RecyclerView.ViewHolder holder, int position) {
        TextView tv_TopBanner = holder.itemView.findViewById(R.id.tv_TopBanner);
        tv_TopBanner.setText("TopTabDataItem launchType ：Java");
    }

    @Override
    public int getItemLayoutRes() {
        // 返回布局id
        return R.layout.item_layout_top_banner;
    }
}
