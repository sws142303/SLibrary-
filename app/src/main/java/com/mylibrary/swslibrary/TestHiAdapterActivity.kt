package com.mylibrary.swslibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mylibrary.stools.base.mvvm.base.BaseActivity
import com.mylibrary.swslibrary.databinding.ActivityTestHiAdapterBinding
import com.mylibrary.swslibrary.utils.hiitem.HiAdapter
import com.mylibrary.swslibrary.utils.hiitem.HiDateItem
import com.mylibrary.swslibrary.utils.hiitem.ItemData
import com.mylibrary.swslibrary.utils.hiitem.TopBannerItem

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
    var mList = mutableListOf<TopBannerItem>()
    override fun initData() {
        super.initData()
        val itemData: ItemData = ItemData()
        val topBannerItem: TopBannerItem = TopBannerItem(itemData)
        val hiAdapter = HiAdapter(this)
        hiAdapter.addItem(0,topBannerItem,true)
        binding.rvList.layoutManager = LinearLayoutManager(this)
        binding.rvList.adapter = hiAdapter
    }
}
