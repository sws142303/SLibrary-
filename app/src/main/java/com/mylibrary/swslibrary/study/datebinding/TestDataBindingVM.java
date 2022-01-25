package com.mylibrary.swslibrary.study.datebinding;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.mylibrary.stools.base.mvvm.base.BaseViewModel;

/**
 * @author Sws
 * @time 13:47 2022/1/4
 * @dec
 **/
public class TestDataBindingVM extends BaseViewModel<TestDataBindingM> {
    public TestDataBindingVM(@NonNull Application application) {
        super(application);
    }

    public TestDataBindingVM(@NonNull Application application, TestDataBindingM model) {
        super(application, model);
    }

    public ObservableField<String> name = new ObservableField<>("oldName");
    public void setInfoUser() {
        name.set("newName");
    }
}
