package com.mylibrary.swslibrary.permission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.Toast;

import com.mylibrary.stools.base.mvvm.base.BaseActivity;
import com.mylibrary.stools.base.mvvm.bus.event.SingleLiveEvent;
import com.mylibrary.stools.permission.OnPermissionCallback;
import com.mylibrary.stools.permission.Permission;
import com.mylibrary.stools.permission.XXPermissions;
import com.mylibrary.swslibrary.BR;
import com.mylibrary.swslibrary.R;
import com.mylibrary.swslibrary.databinding.ActivityPermissionBinding;

import java.util.List;

public class PermissionActivity extends BaseActivity<ActivityPermissionBinding,PermissionVM> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_permission;
    }

    @Override
    public int initVariableId() {
        return BR.vm;
    }

    @Override
    public void initViewObservable() {
        super.initViewObservable();

        viewModel.startPermission.observe(this, new Observer() {
           @Override
           public void onChanged(Object o) {
               XXPermissions.with(PermissionActivity.this)
                       .permission(Permission.CAMERA)
                       .request(new OnPermissionCallback() {
                           @Override
                           public void onGranted(List<String> permissions, boolean all) {
                               if (all) {
                                   Toast.makeText(getApplication(), "获取拍照权限成功", Toast.LENGTH_SHORT).show();
                               }
                           }
                       });
           }
       });
    }
}
