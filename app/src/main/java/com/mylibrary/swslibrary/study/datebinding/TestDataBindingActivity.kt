package com.mylibrary.swslibrary.study.datebinding

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.mylibrary.stools.base.mvvm.base.BaseActivity
import com.mylibrary.swslibrary.R
import com.mylibrary.swslibrary.databinding.ActivityTestDataBindingBinding

class TestDataBindingActivity : BaseActivity<ActivityTestDataBindingBinding, TestDataBindingVM>() {

    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_test_data_binding;
    }

    override fun initVariableId(): Int {
        return -1;
    }

    fun startFrom(activity: Activity) {
        val intent = Intent(activity, TestDataBindingActivity::class.java)
        activity.startActivity(intent)
    }

    /**
     * viewModel层事件注册
     */
    override fun initViewObservable() {

    }


    fun onClick(view: View?) {
        viewModel.setInfoUser()
    }

}
