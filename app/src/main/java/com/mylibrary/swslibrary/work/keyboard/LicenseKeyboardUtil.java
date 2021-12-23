package com.mylibrary.swslibrary.work.keyboard;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;

import com.mylibrary.swslibrary.R;

/**
 * @author Sws
 * @time 10:13 2021/6/17
 * @dec
 **/
public class LicenseKeyboardUtil {

    private Context ctx;
    private KeyboardView keyboardView;
    private Keyboard k1;// 省份简称键盘
    private Keyboard k2;// 数字字母键盘
    private String provinceShort[];
    private String letterAndDigit[];
    private OnKeyBoardClick onKeyBoardClick;
    private int currentEditText = 0;//默认当前光标在第一个EditText
    private StringBuilder content = null;


    public LicenseKeyboardUtil(Context ctx, OnKeyBoardClick onKeyBoardClick) {
        content = new StringBuilder();
        this.ctx = ctx;
        this.onKeyBoardClick = onKeyBoardClick;
        k1 = new Keyboard(ctx, R.xml.province_short_keyboard);
        k2 = new Keyboard(ctx, R.xml.lettersanddigit_keyboard);
//        keyboardView = (KeyboardView) ((Activity) ctx).findViewById(R.id.keyboard_view);
        keyboardView.setKeyboard(k1);
        keyboardView.setEnabled(true);
        //设置为true时,当按下一个按键时会有一个popup来显示<key>元素设置的android:popupCharacters=""
        keyboardView.setPreviewEnabled(false);
        //设置键盘按键监听器
        keyboardView.setOnKeyboardActionListener(listener);
        provinceShort = new String[]{"京", "津", "冀", "鲁", "晋", "蒙", "辽", "吉", "黑"
                , "沪", "苏", "浙", "皖", "闽", "赣", "豫", "鄂", "湘"
                , "粤", "桂", "渝", "川", "贵", "云", "藏", "陕", "甘"
                , "青", "琼", "新", "港", "澳", "台", "宁"};

        letterAndDigit = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
                , "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"
                , "A", "S", "D", "F", "G", "H", "J", "K", "L"
                , "Z", "X", "C", "V", "B", "N", "M"};
    }

    private KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void swipeUp() {
        }

        @Override
        public void swipeRight() {
        }

        @Override
        public void swipeLeft() {
        }

        @Override
        public void swipeDown() {
        }

        @Override
        public void onText(CharSequence text) {
        }

        @Override
        public void onRelease(int primaryCode) {
        }

        @Override
        public void onPress(int primaryCode) {
        }

        private boolean isNextDel = false;
        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
             if (primaryCode == 112) { //xml中定义的删除键值为112
                //将当前EditText置为""并currentEditText-1
                 currentEditText--;
                if ((content.length() - 1) > 0) {
                    content.delete(content.length() - 1, content.length());
                    String strCon = content.toString();
                    if (onKeyBoardClick != null) {
                        onKeyBoardClick.onInputClick(strCon);
                    }
                } else {
                    if (onKeyBoardClick != null) {
                        content.delete(0, content.length());
                        onKeyBoardClick.onInputClick(content.toString());
                    }
                    currentEditText = 0;
                    //切换为省份简称键盘
                    keyboardView.setKeyboard(k1);
                }
            } else if (primaryCode == 66) { //xml中定义的完成键值为66
                String strCon = content.toString();
                if (onKeyBoardClick != null) {
                    onKeyBoardClick.onInputClick(strCon);
                    if (currentEditText == 6) {
                        onKeyBoardClick.onInputSuc(true, strCon);
                    } else {
                        onKeyBoardClick.onInputSuc(false, null);
                    }
                }
            } else { //其它字符按键
                isNextDel = false;
                if (currentEditText == 0) {
                    if (primaryCode < provinceShort.length) {
                        content.append(provinceShort[primaryCode]);
                        String strCon = content.toString();
                        if (onKeyBoardClick != null) {
                            onKeyBoardClick.onInputClick(strCon);
                        }
                        currentEditText = 1;
                        //切换为字母数字键盘
                        keyboardView.setKeyboard(k2);
                    }

                } else {
                    //第二位必须大写字母
                    if (currentEditText == 1 && !letterAndDigit[primaryCode].matches("[A-Z]{1}")) {
                        return;
                    }
//                    edits[currentEditText].setText(letterAndDigit[primaryCode]);

                    currentEditText++;
                    if (content.length() < 7) {
                        if (primaryCode < letterAndDigit.length) {
                            content.append(letterAndDigit[primaryCode]);
                            String strCon = content.toString();
                            if (onKeyBoardClick != null) {
                                onKeyBoardClick.onInputClick(strCon);
                            }
                        }
                    }

                    if (currentEditText > 6) {
                        currentEditText = 6;
                    }
                }
            }
        }
    };

    /**
     * 显示键盘
     */
    public void showKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.GONE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏键盘
     */
    public void hideKeyboard() {
        int visibility = keyboardView.getVisibility();
        if (visibility == View.VISIBLE) {
            keyboardView.setVisibility(View.INVISIBLE);
        }
    }
}
