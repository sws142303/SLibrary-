package com.mylibrary.swslibrary;

import android.app.Application;

import androidx.multidex.MultiDex;

/**
 * @ClassName MyApp
 * @Description TODO
 * @Author 史文胜
 * @Date 2020/12/12 11:23
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }
}
