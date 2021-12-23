package com.mylibrary.swslibrary;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.mylibrary.stools.base.mvvm.base.BaseViewModel;
import com.tencent.bugly.crashreport.biz.UserInfoBean;

/**
 * @author Sws
 * @time 10:19 2021/6/10
 * @dec
 **/
public class MainVM extends BaseViewModel {

    public MainVM(@NonNull Application application) {
        super(application);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }
}
