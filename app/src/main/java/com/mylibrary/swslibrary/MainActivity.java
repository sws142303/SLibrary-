package com.mylibrary.swslibrary;


import android.app.Activity;
import android.os.Bundle;

import com.mylibrary.stools.base.mvvm.base.BaseActivity;
import com.mylibrary.swslibrary.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding,MainVM> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.mainVM;
    }

    @Override
    public void initData() {
        super.initData();
    }
}