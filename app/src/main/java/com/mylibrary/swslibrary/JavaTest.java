package com.mylibrary.swslibrary;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mylibrary.swslibrary.lambda.TestLambda;
import com.mylibrary.swslibrary.lambda.TestLambdaInterface;
import com.mylibrary.swslibrary.utils.SLog;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Sws
 * @time 14:01 2021/11/16
 * @dec
 **/
public class JavaTest {
    private TextView mView;

    public void main() {
        //开启一个子线程
        //正常代码
        new Thread(new Runnable() {
            @Override
            public void run() {
            }
        }).start();

        //Lambda表达式
        new Thread(() -> {
            Log.e("", "");
        }).start();
        //设置View长按监听
        //正常代码
        mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
        //Lambda表达式
        mView.setOnLongClickListener(v -> {
            return false;
        });


        //设置View点击监听
        //正常代码
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //Lambda表达式
        mView.setOnClickListener(v -> {
            Log.e("", "");
        });

        //定义比较器进行排序
        //正常代码
        List<String> names = Arrays.asList("lilei", "hanmeimei", "tom", "jack");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        //Lambda表达式 单行需要有返回值
        Collections.sort(names, (a, b) -> b.compareTo(a));
        //Lambda表达式 多行需要有返回值
        Collections.sort(names,(a,b) -> {
            return b.compareTo(a);
        });

        /**
         * 练习Lambda表达式
         */
        //正常代码
        TestLambda testLambda1 = new TestLambda(new TestLambdaInterface() {
            @Override
            public void onTestLambdaClick(int position, int index, String msg) {

            }
        });

        //使用Lambda表达式 多行不需要有返回值
        TestLambda testLambda2 = new TestLambda((position,index,msg) -> {
            SLog.print(position);
            SLog.print(index);
            SLog.print(msg);
        });
        //使用Lambda表达式 单行不需要有返回值
        TestLambda testLam = new TestLambda((position,index,msg) -> SLog.print(msg));
    }
}
