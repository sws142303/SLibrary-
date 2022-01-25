package com.mylibrary.swslibrary.study.thread;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @author Sws
 * @time 14:19 2022/1/5
 * @dec  方式四 IntentService
 **/
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("方式四-IntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        /**
         * Creates an IntentService.  Invoked by your subclass's constructor.
         *
         * @param name Used to name the worker thread, important only for debugging.
         */
        String TAG_THREAD_CREATE = "线程的五种创建方式";
        Log.e(TAG_THREAD_CREATE,"方式四 IntentService curThreadName：" + Thread.currentThread());

    }
}
