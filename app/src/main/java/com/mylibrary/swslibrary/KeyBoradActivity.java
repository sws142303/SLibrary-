package com.mylibrary.swslibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mylibrary.stools.base.mvvm.base.BaseActivity;
import com.mylibrary.stools.base.mvvm.binding.command.BindingAction;
import com.mylibrary.stools.base.mvvm.binding.command.BindingCommand;
import com.mylibrary.swslibrary.callback.OnKeyBoardClick;
import com.mylibrary.swslibrary.databinding.ActivityKeyBoradBinding;
import com.mylibrary.swslibrary.utils.LicenseKeyboardUtil;

public class KeyBoradActivity extends BaseActivity<ActivityKeyBoradBinding,KeyBoradVM> {
//implements View.OnClickListener
    private EditText button;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_key_borad;
    }

    @Override
    public int initVariableId() {
        return 0;
    }

    public static final String INPUT_LICENSE_COMPLETE = "me.kevingo.licensekeyboard.input.comp";
    public static final String INPUT_LICENSE_KEY = "LICENSE";

    private EditText inputbox1,inputbox2,
            inputbox3,inputbox4,
            inputbox5,inputbox6,inputbox7;
    private LinearLayout boxesContainer;
    private LicenseKeyboardUtil keyboardUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        button = (EditText) this.findViewById(R.id.btn_add_car);
//        button.setOnClickListener(this);
//        button.setOnTouchListener(dateEditTextOnTouchListener);
//        button.setInputType(InputType.TYPE_NULL);
        //输入车牌完成后的intent过滤器
        IntentFilter finishFilter = new IntentFilter(INPUT_LICENSE_COMPLETE);

        final BroadcastReceiver receiver =  new  BroadcastReceiver() {
            @Override
            public   void  onReceive(Context context, Intent intent) {
                String license = intent.getStringExtra(INPUT_LICENSE_KEY);
                if(license != null && license.length() > 0){
//                    boxesContainer.setVisibility(View.GONE);
                    if(keyboardUtil != null){
                        keyboardUtil.hideKeyboard();
                    }

                    AlertDialog alertDialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(KeyBoradActivity.this);
                    builder.setMessage("车牌号为:" + license);
                    alertDialog = builder.create();
                    alertDialog.setCancelable(true);
                    alertDialog.show();
                }
                unregisterReceiver(this);
            }
        };
        this.registerReceiver(receiver, finishFilter);
    }

   /* @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_add_car:
//                boxesContainer.setVisibility(View.VISIBLE);
                keyboardUtil = new LicenseKeyboardUtil(this, new OnKeyBoardClick() {
                    @Override
                    public void onInputClick(String content) {
                        Log.e("onClick","onInputClick : "+content);
                        button.setText(content);
                    }

                    @Override
                    public void onInputSuc(boolean isSuc, String con) {
                        Log.e("onClick","onInputSuc : " +isSuc + " con : " +con);
                        if (isSuc){
                            button.setText(con);
                        }
                    }
                });
                keyboardUtil.showKeyboard();
                break;
        }
    }*/

    /**
     * 防重复点击的监听
     */
    public BindingCommand click = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            Log.e("click","防重复点击的监听");
        }
    });
}
