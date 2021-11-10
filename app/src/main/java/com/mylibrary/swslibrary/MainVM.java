package com.mylibrary.swslibrary;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.mylibrary.stools.base.mvvm.base.BaseViewModel;
import com.mylibrary.stools.base.mvvm.http.OnHttpObservableCallBack;
import com.mylibrary.stools.base.mvvm.http.Result;
import com.mylibrary.swslibrary.bean.UserInfoBean;
import com.mylibrary.swslibrary.http.request.TestRequestBean;
import com.mylibrary.swslibrary.http.response.TestResponseBean;
import com.mylibrary.swslibrary.permission.PermissionActivity;

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
            startActivity(PermissionActivity.class);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }
}
