package com.mylibrary.swslibrary.study.thread;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.mylibrary.swslibrary.R;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadActivity extends AppCompatActivity {

    private static String TAG_THREAD_CREATE = "线程的五种创建方式";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_thread);

        //线程的五种创建方式
        initCreateThread();
    }

    //线程的五种创建方式
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void initCreateThread() {

        //TODO 方式一
        //直接new Thread的方式
        new Thread() {
            @Override
            public void run() {
                Log.e(TAG_THREAD_CREATE, "方式一  直接new Thread的方式");
            }
        }.start();

        //通过继承Thread 重写run方法
        new MyThread().start();

        //TODO 方式二
        //自定义class 继承AsyncTask来创建线程
        new MyAsyncTask().execute("AsyncTask 串行执行");//串行执行(假设有多个AsyncTask 有一个任务进入了阻塞 后面的任务就不会得到执行)
        new MyAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "AsyncTask 并行执行");//并行执行 (解决了上诉的问题)

        //直接使用AsyncTask 串行执行
        AsyncTask.execute(() -> {
            Log.e(TAG_THREAD_CREATE, "直接使用AsyncTask 串行执行");
        });
        //直接使用AsyncTask内置线程池 并行执行
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> {
            Log.e(TAG_THREAD_CREATE, "直接使用AsyncTask 并行执行");
        });

        //TODO 方式三 HandlerThread
        HandlerThread handlerThread = new HandlerThread("方式三-HandlerThread");
        handlerThread.start();
        MyHandle mHandle = new MyHandle(handlerThread.getLooper());
        //向子线程发消息
        mHandle.sendEmptyMessage(100);
        //停止线程
        handlerThread.quitSafely();

        //TODO 方式四 IntentService
        startService(new Intent(this,MyIntentService.class));

        //TODO 方式五 线程池
        //一、线程可复用的线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

            }
        });

        //

    }

    //通过继承Thread 重写run方法
    static class MyThread extends Thread {
        @Override
        public void run() {
            Log.e(TAG_THREAD_CREATE, "方式一  通过继承Thread,重写run方法");
        }
    }

    //通过AsyncTask来使用线程
    //泛型一：参数类型
    //泛型二：进度类型
    //泛型三：结果类型
    static class MyAsyncTask extends AsyncTask<String, Integer, Boolean> {
        private String Tag;

        @Override
        protected Boolean doInBackground(String... strings) {
            Tag = strings[0];
            Log.e(TAG_THREAD_CREATE, "方式二 " + Tag + "doInBackground ：" + strings[0]);
            for (int i = 0; i < 10; i++) {
                //更新进度
                publishProgress(i * 10);
            }
            //返回结果
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.e(TAG_THREAD_CREATE, "方式二 " + Tag + "onProgressUpdate ：" + values[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            Log.e(TAG_THREAD_CREATE, "方式二 " + Tag + "onPostExecute ：" + aBoolean);
        }
    }

    //方式三 使用ThreadHandle +  Handle来实现主线程给子线程发消息。
    static class MyHandle extends Handler {
        public MyHandle(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            Log.e(TAG_THREAD_CREATE,"方式三 ThreadHandle ：" + msg.what);
            Log.e(TAG_THREAD_CREATE,"方式三 ThreadHandle  curThread：" + Thread.currentThread());
        }
    }
}
