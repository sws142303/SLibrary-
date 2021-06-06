package com.mylibrary.stools.base.mvvm;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * @author Sws
 * @Time 2021/6/6 17:10
 * @msg mvvm基类act
 **/
public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends RxAppCompatActivity implements IBaseView {
    protected V binding;
    protected VM viewModel;
    private int viewModelId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //页面接受的参数方法
        initParam();
        //私有的初始化DateBinding和viewModel方法
        initViewDateBinding(savedInstanceState);
    }

    /**
     * 注入绑定
     * @param savedInstanceState
     */
    private void initViewDateBinding(Bundle savedInstanceState){
        binding =  DataBindingUtil.setContentView(this,initContentView(savedInstanceState));


    }

    @Override
    public void initParam() {

    }

    /**
     * 初始化视图
     * @param bundle
     * @return
     */
    public abstract int initContentView(Bundle bundle);

    /**
     * 初始化viewModel的id
     * @return
     */
    public abstract int initVariableId();

    /**
     * 初始化ViewModel
     * @return 继承BaseViewModel的ViewModel
     */
    public VM initViewModel(){
        return viewModel;
    }

    @Override
    public void initDate() {

    }
    public <T extends ViewModel> T createViewModel(FragmentActivity activity,Class<T> tClass){
        return ViewModelProvider.of(activity).get(tClass);
    }
}

