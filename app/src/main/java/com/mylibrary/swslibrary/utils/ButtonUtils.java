package com.mylibrary.swslibrary.utils;

import android.util.Log;

/**
 * Description：按钮双击return
 * Created on 2020/3/17
 * Author : 郭
 */
public class ButtonUtils {
    private static long lastClickTime = 0;

    private static final long DIFF = 300;
    private static final long DIFF_1000 = 1000;

    private static int lastButtonId = -1;

    /**
     * 判断两次点击的间隔，如果小于300，则认为是多次无效点击
     *
     * @return
     */
    public static boolean isFastDoubleClick() {
        return isFastDoubleClick(-1, DIFF);
    }

    /**
     * 判断两次点击的间隔，如果小于1000，则认为是多次无效点击
     *
     * @return
     */
    public static boolean isFastDoubleClick1() {
        return isFastDoubleClick(-1, DIFF_1000);
    }

    /**
     * 判断两次点击的间隔，如果小于1000，则认为是多次无效点击
     *
     * @return
     */
    public static boolean isFastDoubleClick3() {
        return isFastDoubleClick3(-1, DIFF_1000);
    }

    /**
     * 判断两次点击的间隔，如果小于1000，则认为是多次无效点击
     *
     * @return
     */
    public static boolean isFastDoubleClick2() {
        return isFastDoubleClick(-1, DIFF_1000);
    }

    /**
     * 判断两次点击的间隔，如果小于1000，则认为是多次无效点击
     *
     * @return
     */
    public static boolean isFastDoubleClick2(int buttonId) {
        return isFastDoubleClick(buttonId, DIFF_1000);
    }

    /**
     * 判断两次点击的间隔，如果小于1000，则认为是多次无效点击
     *
     * @return
     */
    public static boolean isFastDoubleClick(int buttonId) {
        return isFastDoubleClick(buttonId, DIFF);
    }

    /**
     * 判断两次点击的间隔，如果小于diff，则认为是多次无效点击
     *
     * @param diff
     * @return
     */
    public static boolean isFastDoubleClick(int buttonId, long diff) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (lastButtonId == buttonId && lastClickTime > 0 && timeD < diff) {
            Log.e("isFastDoubleClick", "短时间内按钮多次触发");
            return true;
        }
        lastClickTime = time;
        lastButtonId = buttonId;
        return false;
    }

    /**
     * 判断两次点击的间隔，如果小于diff，则认为是多次无效点击
     *
     * @param diff
     * @return
     */
    public static boolean isFastDoubleClick3(int buttonId, long diff) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (lastButtonId == buttonId && lastClickTime > 0 && timeD < diff) {
            Log.e("isFastDoubleClick", "短时间内按钮多次触发");
            return true;
        }
        lastClickTime = time;
        lastButtonId = buttonId;
        return false;
    }
}
