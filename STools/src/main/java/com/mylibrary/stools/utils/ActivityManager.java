package com.mylibrary.stools.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sws
 * @time 16:44 2021/12/7
 * @dec
 **/
public class ActivityManager {
    private static ActivityManager instance;

    public static ActivityManager getInstance() {
        if (instance == null) {
            synchronized (ActivityManager.class) {
                if (instance == null) {
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }
    //创建一个list 使用弱引用的方式防止内存泄漏
    //activity集合
    private static List<WeakReference<Activity>> activityRefs = new ArrayList<>();
    //应用前后台切换监听集合
    private static List<FrontBackCallBack> frontBackCallBack = new ArrayList<>();
    //标识当前在前台的Activity数量
    private static int activityStartCount = 0;
    //标识当前应用是否在前台(应用打开肯定是在前台 所以默认为true)
    private static boolean front = true;

    /**
     * 初始化
     */
    public static void init(Application application){
        application.registerActivityLifecycleCallbacks(new InnerActivityLifecycleCallbacks());
    }

    /**
     *注册应用前后台监听
     * @param callBack
     */
    public void addFrontBackCallBack(FrontBackCallBack callBack){
        frontBackCallBack.add(callBack);
    }

    public void removeFrontBackCallBack(FrontBackCallBack callBack){
        frontBackCallBack.remove(callBack);
    }

    /**
     * 应用前后台切换监听
     */
    public static interface FrontBackCallBack {
        void onChanged(boolean front);
    }

    /**
     * 返回栈顶activity
     */
    public Activity getTopActivity(){
        if (activityStartCount <= 0){
            return null;
        }else {
            return activityRefs.get(activityRefs.size() -1).get();
        }
    }

    /**
     * 应用前后台切换
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private static void onFrontBackChange(boolean front) {
        frontBackCallBack.forEach(it ->{
            it.onChanged(front);
        });
    }

    public static class InnerActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks{

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            activityRefs.add(new WeakReference<>(activity));
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onActivityStarted(Activity activity) {
            activityStartCount++;
            //activityStartCount > 0 说明应用处于可见状态，也就是前台
            //!front 之前是不是在后台
            if (!front && activityStartCount > 0) {
                front = true;
                onFrontBackChange(true);
            }
        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onActivityStopped(Activity activity) {
            activityStartCount--;
            if (activityStartCount <= 0 && front){
                front = false;
                onFrontBackChange(false);
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onActivityDestroyed(Activity activity) {
            activityRefs.forEach(it -> {
                if (it != null && it.get() == activity){
                    activityRefs.remove(it);
                }
            });
        }
    }
}
