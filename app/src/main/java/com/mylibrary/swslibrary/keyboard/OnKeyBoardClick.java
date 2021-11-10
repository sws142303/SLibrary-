package com.mylibrary.swslibrary.keyboard;

/**
 * @author Sws
 * @time 10:41 2021/6/17
 * @dec
 **/
public interface OnKeyBoardClick {
    /**
     * 输入省份 回调
     */
    void onInputClick(String content);

    /**
     * 输入完成
     */
    void onInputSuc(boolean isSuc,String con);
}
