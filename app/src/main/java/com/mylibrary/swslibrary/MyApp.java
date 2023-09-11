package com.mylibrary.swslibrary;

import androidx.multidex.MultiDex;

import com.mylibrary.stools.base.mvvm.base.BaseApplication;
import com.mylibrary.stools.permission.XXPermissions;
import com.mylibrary.swslibrary.permission.PermissionInterceptor;
import com.mylibrary.swslibrary.study.zipfile.FileCompressionTool;
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
        //bugly初始化
        CrashReport.initCrashReport(getApplicationContext(), "11468437be", false);
        MultiDex.install(this);

        // 设置权限申请拦截器（全局设置）
        XXPermissions.setInterceptor(new PermissionInterceptor());

        //初始化压缩工具
        FileCompressionTool.getInstance().init(this);
    }
}
