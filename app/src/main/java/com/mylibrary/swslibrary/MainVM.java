package com.mylibrary.swslibrary;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.mylibrary.stools.base.mvvm.base.BaseViewModel;
import com.mylibrary.swslibrary.bean.UserInfoBean;

/**
 * @author Sws
 * @time 10:19 2021/6/10
 * @dec
 **/
public class MainVM extends BaseViewModel {

    public UserInfoBean mBean = new UserInfoBean();

    public ObservableField<String> name = new ObservableField<>("ces");

    public MainVM(@NonNull Application application) {
        super(application);
    }

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            name.set("哈哈哈");
            mBean.name.set("测试");
        }
    };
}
