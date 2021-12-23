package com.mylibrary.swslibrary.study.hiadapter

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mylibrary.swslibrary.R

/**
 *@author
 *@Time 2021/12/15 23:42
 *@msg
 **/
class TopBannerItem(itemData: ItemData) : HiDateItem<ItemData, RecyclerView.ViewHolder>(itemData) {
    override fun onBindData(holder: RecyclerView.ViewHolder, position: Int) {
       val tv_TopBanner = holder.itemView.findViewById<TextView>(R.id.tv_TopBanner)
        tv_TopBanner.text = "TopBannerItem launchType : Kotlin"
    }

    override fun getItemLayoutRes(): Int {
        return R.layout.item_layout_top_banner
    }

}