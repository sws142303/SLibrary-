package com.mylibrary.swslibrary;

import android.app.Application;

import androidx.annotation.NonNull;

import com.mylibrary.stools.base.mvvm.base.BaseModel;
import com.mylibrary.stools.base.mvvm.base.BaseViewModel;

/**
 * @author Sws
 * @time 13:49 2021/12/21
 * @dec
 **/
public class TestHiAdapterVM extends BaseViewModel {
    public TestHiAdapterVM(@NonNull Application application) {
        super(application);
    }

    public TestHiAdapterVM(@NonNull Application application, BaseModel model) {
        super(application, model);
    }
}
