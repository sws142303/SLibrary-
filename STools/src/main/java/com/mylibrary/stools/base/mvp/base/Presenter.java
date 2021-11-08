package com.mylibrary.stools.base.mvp.base;



public interface Presenter {
    void attachView(View view);

    boolean isViewAttached();

    void detachView();
}
