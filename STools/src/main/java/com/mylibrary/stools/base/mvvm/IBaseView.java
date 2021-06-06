package com.mylibrary.stools.base.mvvm;

/**
 * @author Sws
 * @Time 2021/6/6 17:12
 * @msg
 **/
interface IBaseView {
    /**
     * 初始化界面 传递参数
     */
    void initParam();

    /**
     *  初始化数据
     */
    void initDate();

    /**
     * 初始化界面观察者监听
     */
    void iniViewObservable();
}
