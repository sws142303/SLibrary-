package com.mylibrary.swslibrary.study.java.lambda;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.List;

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
   private void onClick() {
       if (mInterface != null) {
            mInterface.onTestLambdaClick(1222, 2111, "312121");
        }
    }

    /**
     * 练习Lambda表达式
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void testLambda(OnLog onLog){

        //练习一
        String[] boysArray = {"张三","李四","王五","马六","JackLove","uzi","Sws"};
        List<String> boysList = Arrays.asList(boysArray);
        //正常遍历boysList集合
        for (String boy : boysList) {
            onLog.onLog("正常遍历boysList ：" + boy);
        }
        //使用Lambda表达式遍历
        //使用“->”在传递值的时候使用
        boysList.forEach((boy) -> {onLog.onLog("使用Lambda表达式遍历boysList："+boy);});
        //使用双冒号“::”可以直接输出内容
        boysList.forEach(onLog::onLog);

        //练习二
        

    }

    public interface OnLog{
        void onLog(String msg);
    }
}
