package com.mylibrary.swslibrary;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.mylibrary.stools.base.mvvm.base.BaseApplication;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * @ClassName MyApp
 * @Description TODO
 * @Author 史文胜
 * @Date 2020/12/12 11:23
 */
public class MyApp extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "11468437be", false);
        MultiDex.install(this);
    }
}
