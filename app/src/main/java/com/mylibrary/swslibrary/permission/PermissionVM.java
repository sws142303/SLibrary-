package com.mylibrary.swslibrary.permission;

import android.app.Application;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.mylibrary.stools.base.mvvm.base.BaseViewModel;
import com.mylibrary.stools.bus.event.SingleLiveEvent;


import java.util.List;

/**
 * @author Sws
 * @time 15:51 2021/11/9
 * @dec
 **/
public class PermissionVM extends BaseViewModel {
    public SingleLiveEvent<String> startPermission = new SingleLiveEvent<>();

    public PermissionVM(@NonNull Application application) {
        super(application);
    }

    /**
     * 开始申请权限
     */
    public View.OnClickListener startCLick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplication(), "哈哈哈哈", Toast.LENGTH_SHORT).show();
            startPermission.postValue("");
        }
    };

    /**
     * 退出事件
     */
    public View.OnClickListener finishClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           finish();
        }
    };
}
