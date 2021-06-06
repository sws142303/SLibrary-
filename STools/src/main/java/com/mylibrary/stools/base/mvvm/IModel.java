package com.mylibrary.stools.base.mvvm;

/**
 * @author Sws
 * @Time 2021/6/6 17:13
 * @msg
 **/
interface IModel {
    /**
     * ViewModel销毁时清除Model
     * Model层同样不能持有长生命周期对象
     */
    void onCleared();
}
