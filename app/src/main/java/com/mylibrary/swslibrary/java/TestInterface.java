package com.mylibrary.swslibrary.java;

/**
 * @author Sws
 * @time 15:54 2021/11/23
 * @dec 泛型接口
 **/
public interface TestInterface<T,V> {
    void setParamsT(T t);
    T loadT();
    void setParams(V v);
    V loadV();
}
