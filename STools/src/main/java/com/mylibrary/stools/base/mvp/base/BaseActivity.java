package com.mylibrary.stools.base.mvp.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.common.eventbus.EventBus;
import com.mylibrary.stools.base.ActivityManager;

/**
 * @author Sws
 * @time 10:07 2021/10/20
 * @dec
 **/
public abstract class BaseActivity extends AppCompatActivity {
    protected Intent baseIntent;
    private Presenter mBasePresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mBasePresenter = bindPresenter();
        attachView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        super.onCreate(savedInstanceState);
        this.setContentView(getLayoutID());
        ActivityManager.addActivity(this,getClass());
        getBaseIntent();
        initView();
        initData();
        initListener();
        if (isFullScreen()){
            fullScreen(this);
        }
    }

    /**
     * 是否设置沉浸式状态栏
     * @return
     */
    public boolean isFullScreen(){
        return false;
    }

    /**
     * 通过设置全屏，设置状态栏透明
     *
     * @param activity
     */
    private void fullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = activity.getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                //导航栏颜色也可以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
    }

    public abstract int getLayoutID();

    private void getBaseIntent() {
        baseIntent = getIntent();
        if (baseIntent != null) {
            initIntent(baseIntent);
        }
    }

    public void initIntent(Intent intent) {
    }

    public abstract void initView();

    public abstract void initData();

    public void initListener() {
    }

    /**
     * 绑定presenter
     *
     * @return Presenter
     */
    public abstract Presenter bindPresenter();

    /**
     * 绑定MVP的View
     */
    public abstract void attachView();

    @Override
    protected void onDestroy() {
        if (mBasePresenter != null) {
            mBasePresenter.detachView();
        }
        ActivityManager.removeActivity(this);
        super.onDestroy();
    }

    /**
     * 是否注册事件分发
     *
     * @return true 注册；false 不注册，默认不注册
     */
    protected boolean isRegisteredEventBus() {
        return false;
    }
}
