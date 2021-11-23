package com.mylibrary.swslibrary.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sws
 * @time 15:27 2021/11/23
 * @dec 定义base工具类 通过泛型来制定具体类型
 **/
abstract public class TestBaseBean<T> {

    private T t;
    private List<T> date = new ArrayList<>();

    List<T> getTDate() {
        return date;
    }

    @SafeVarargs
    final void addDate(T... t) {
        date.addAll(Arrays.asList(t));
    }
}
