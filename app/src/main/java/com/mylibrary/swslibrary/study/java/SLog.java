package com.mylibrary.swslibrary.study.java;

import android.nfc.Tag;
import android.util.Log;

/**
 * @author Sws
 * @time 17:20 2021/11/15
 * @dec
 **/
public class SLog {
    private static String TAG = "SLog";
    public static void e(String msg){
//        Log.e(TAG,msg);
    print(msg);
    }

    public static void print(Object msg){
        System.out.println(msg);
    }
}
