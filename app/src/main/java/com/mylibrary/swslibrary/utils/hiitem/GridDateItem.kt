package com.mylibrary.swslibrary.utils.hiitem

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mylibrary.stools.base.mvp.base.View
import com.mylibrary.swslibrary.R

/**
 *@author Sws
 *@Time 2021/12/21 22:46
 *@msg
 **/
class GridDateItem(data: ItemData) : HiDateItem<ItemData, GridDateItem.MyHolder>(data) {

    inner class MyHolder(itemView : android.view.View) : RecyclerView.ViewHolder(itemView){
        var tv_TopBanner : TextView? = null
        init {
            tv_TopBanner = itemView.findViewById<TextView>(R.id.tv_TopBanner)
        }
    }

    override fun getItemLayoutRes(): Int {
        return R.layout.item_layout_top_banner
    }

    override fun onBindData(holder: MyHolder, position: Int) {
        holder.tv_TopBanner!!.text = "我是自定义的holder launchType ：Kotlin"
    }
}