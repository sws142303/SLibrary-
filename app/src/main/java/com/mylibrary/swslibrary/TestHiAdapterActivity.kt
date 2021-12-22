package com.mylibrary.swslibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mylibrary.stools.base.mvvm.base.BaseActivity
import com.mylibrary.swslibrary.databinding.ActivityTestHiAdapterBinding
import com.mylibrary.swslibrary.utils.hiitem.*

/**
 *  测试recyclerView加载不同布局
 *
 */
class TestHiAdapterActivity : BaseActivity<ActivityTestHiAdapterBinding, TestHiAdapterVM>() {
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_test_hi_adapter;
    }

    override fun initVariableId(): Int {
        return -1;
    }

    //测试
    override fun initData() {
        super.initData()
        //初始化适配器
        val hiAdapter = HiAdapter(this)
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = hiAdapter

        //添加数据
        val dates  = ArrayList<HiDateItem<*,*>>()
        dates.add(TopBannerItem(ItemData()))
        dates.add(TopTabDataItem(ItemData()))
        dates.add(GridDateItem(ItemData()))
        hiAdapter.addItems(dates,false)
    }
}
