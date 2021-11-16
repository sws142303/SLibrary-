package com.mylibrary.swslibrary.lambda;

import android.util.Log;

import com.mylibrary.swslibrary.utils.SLog;

/**
 * @author Sws
 * @time 14:16 2021/11/16
 * @dec
 **/
public class TestLambda {

    private TestLambdaInterface mInterface;

    public TestLambda(TestLambdaInterface mInterface) {
        this.mInterface = mInterface;
        onClick();
    }
    /**
     * 触发回调
     */
    public void onClick() {
       if (mInterface != null) {
            mInterface.onTestLambdaClick(1222, 2111, "312121");
        }
    }
}
