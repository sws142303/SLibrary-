package com.mylibrary.swslibrary.java;

import android.util.Log;

import com.mylibrary.swslibrary.utils.SLog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sws
 * @time 15:26 2021/11/23
 * @dec 练习Java泛型
 **/
public class TestJava {

    public static void main(String[] args) {

        MyIntUtils.getInstance().addDate(1,2,3,4,5);
        List<Integer> tDate = MyIntUtils.getInstance().getTDate();
        SLog.print("List<Integer>  = " + tDate);

        MyStringUtils.getInstance().addDate("1","2","3");
        List<String> tDate1 = MyStringUtils.getInstance().getTDate();
        SLog.print("List<String>  = " + tDate1);

        checkNotNull(tDate);
    }

    /**
     * 测试泛型工具类
     */
    static class MyIntUtils extends TestBaseBean<Integer> {
        private static MyIntUtils instance;

        synchronized static MyIntUtils getInstance() {
            if (instance == null) {
                instance = new MyIntUtils();
            }
            return instance;
        }


    }
    static class MyStringUtils extends TestBaseBean<String> {
        private static MyStringUtils instance;

         synchronized static MyStringUtils getInstance() {
            if (instance == null) {
                instance = new MyStringUtils();
            }
            return instance;
        }

    }

    /**
     * 定义泛型方法
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> T checkNotNull(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }

    /**
     * 测试泛型接口
     */
    static class MyInterfaceBean implements TestInterface<MyIntUtils,MyStringUtils>{

        @Override
        public void setParamsT(MyIntUtils myIntUtils) {

        }

        @Override
        public MyIntUtils loadT() {
            return null;
        }

        @Override
        public void setParams(MyStringUtils myStringUtils) {

        }

        @Override
        public MyStringUtils loadV() {
            return null;
        }
    }
}
