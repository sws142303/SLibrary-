package com.mylibrary.swslibrary;


import android.app.Activity;
import android.app.Dialog;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;


public class MainActivity extends
        Activity {

    private boolean isClick = false;
    private RelativeLayout rl_info;
    private RelativeLayout relativeLayout;
    private EditText et_startTime;
    private EditText et_endTime;
    private EditText et_name;
    private EditText et_number;
    private TextView tv_startTime;
    private TextView tv_endTime;
    private TextView tv_name;
    private TextView tv_number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_r_z);
        ImageView imageView = findViewById(R.id.imgJianTou);
        Glide.with(this).load(R.mipmap.jiantou).into(imageView);

        relativeLayout = findViewById(R.id.layoutFather);
        rl_info = findViewById(R.id.rl_info);
        et_startTime = findViewById(R.id.et_startTime);
        et_endTime = findViewById(R.id.et_endTime);
        et_name = findViewById(R.id.et_name);
        et_number = findViewById(R.id.et_number);

        tv_startTime = findViewById(R.id.tv_startTime);
        tv_endTime = findViewById(R.id.tv_endTime);
        tv_name = findViewById(R.id.tv_name);
        tv_number = findViewById(R.id.tv_number);


        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ButtonUtils.isFastDoubleClick(R.id.layoutFather)) {
                    //双击  请点击要编辑的选项
                    if (rl_info.getVisibility() != View.VISIBLE) {
                        rl_info.setVisibility(View.VISIBLE);
                    }
                    ToastUtil.showShort("触发编辑事件");
                }
            }
        });
    }


    public void onCancel(View view) {
        if (rl_info.getVisibility() != View.GONE) {
            rl_info.setVisibility(View.GONE);
        }
    }


    public void onClick(View view) {

        String startTime = et_startTime.getText().toString();
        String endTime = et_endTime.getText().toString();
        String name = et_name.getText().toString();
        String number = et_number.getText().toString();

        if (
                TextUtils.isEmpty(startTime) ||
                        TextUtils.isEmpty(endTime) ||
                        TextUtils.isEmpty(name) ||
                        TextUtils.isEmpty(number)
        ) {
            ToastUtil.showShort("填写项不能为空");
            return;
        }

        tv_startTime.setText(startTime);
        tv_endTime.setText(endTime);
        tv_name.setText(name);
        tv_number.setText(number);

        if (rl_info.getVisibility() != View.GONE) {
            rl_info.setVisibility(View.GONE);
        }

    }


}