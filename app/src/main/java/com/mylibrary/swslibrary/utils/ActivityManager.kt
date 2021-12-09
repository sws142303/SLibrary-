package com.mylibrary.swslibrary.utils

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.lang.ref.WeakReference
import java.util.ArrayList

/**
 *@author Sws
 *@Time 2021/12/5 21:36
 *@msg activity管理
 **/
class ActivityManager private constructor() {
    //创建一个list 使用弱引用的方式防止内存泄漏
    private val activityRefs = ArrayList<WeakReference<Activity>>()

    //应用前后台切换监听
    private val frontBackCallback = ArrayList<FrontBackCallBack>()

    //标识当前在前台的Activity数量
    private var activityStartCount = 0

    //标识当前应用是否在前台(应用打开肯定是在前台 所以默认为true)
    private var front = true

    //返回栈顶activity
    val topActivity: Activity?
        get() {
            if (activityStartCount <= 0) {
                return null
            } else {
                return activityRefs[activityRefs.size - 1].get()
            }
            return null
        }

    /**
     * 通过inner来将InnerActivityLifecycleCallbacks设置为一个内部类 这样就可以访问ActivityManager中的函数和变量。
     * InnerActivityLifecycleCallbacks通过实现ActivityLifecycleCallbacks来监听Activity的生命周期。
     */
    inner class InnerActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
            activityRefs.add(WeakReference(activity))
        }

        override fun onActivityStarted(activity: Activity?) {
            activityStartCount++
            //activityStartCount > 0 说明应用处于可见状态，也就是前台
            //!front 之前是不是在后台
            if (!front && activityStartCount > 0) {
                front = true
                onFrontBackChange(front)
            }
        }

        override fun onActivityResumed(activity: Activity?) {
        }

        override fun onActivityPaused(activity: Activity?) {
        }

        override fun onActivityStopped(activity: Activity?) {
            activityStartCount--
            if (activityStartCount <= 0 && front) {
                front = false
                onFrontBackChange(front)
            }
        }

        override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        }

        override fun onActivityDestroyed(activity: Activity?) {
            for (activityRef in activityRefs) {
                if (activityRef != null && activityRef.get() == activity) {
                    activityRefs.remove(activityRef)
                    break
                }
            }
        }

    }

    /**
     * 应用前后台切换
     */
    private fun onFrontBackChange(front: Boolean) {
        for (callBack in frontBackCallback) {
            callBack.onChanged(front)
        }
    }


    /**
     * 提供初始化函数
     */
    fun init(application: Application) {
        application.registerActivityLifecycleCallbacks(InnerActivityLifecycleCallbacks())
    }

    /**
     * 注册应用前后台监听
     */
    fun addFrontBackCallBack(frontBackCallBack: FrontBackCallBack) {
        frontBackCallback.add(frontBackCallBack)
    }

    /**
     * 移除应用前后台监听
     */
    fun removeFrontBackCallBack(frontBackCallBack: FrontBackCallBack) {
        frontBackCallback.remove(frontBackCallBack)
    }

    /**
     * 应用前后台切换监听
     */
    interface FrontBackCallBack {
        fun onChanged(front: Boolean)
    }

    //使用Kotlin来实现单例
    companion object {
        //通过by laze设置加载模式为线程安全
        val instance: ActivityManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            ActivityManager()
        }
    }

}