package com.mylibrary.swslibrary.study.hiadapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 *@author Sws
 *@Time 2021/12/9 22:18
 *@msg
 **/
abstract class HiDateItem<Date, VH : RecyclerView.ViewHolder>(date: Date) {
    private lateinit var adapter: HiAdapter
    var mData: Date? = null

    init {
        mData = date
    }


    abstract fun onBindData(holder: VH, position: Int)

    /**
     * 关联adapter
     */
    fun setAdapter(adapter: HiAdapter){
        this.adapter = adapter
    }

    /**
     * 返回该item的布局资源id
     */
    open fun getItemLayoutRes() : Int{
        return -1
    }

    /**
     * 返回该item的视图view
     */
    open fun getItemView(parent: ViewGroup) : View?{
        return null
    }

    /**
     * 刷新列表
     */
    fun refreshItem(){
        adapter.refreshItem(this)
    }

    /**
     * 从列表上移除
     */
    fun removeItem(){
        adapter.removeItem(this)
    }

    /**
     * 该Item在列表上占据几列
     */
     fun getSpanSize() : Int{
        return 0
    }

}