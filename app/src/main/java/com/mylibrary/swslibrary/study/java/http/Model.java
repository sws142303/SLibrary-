package com.mylibrary.swslibrary.study.java.http;

import com.mylibrary.stools.http.HttpUtil;
import com.mylibrary.stools.http.OnHttpObservableCallBack;
import com.mylibrary.stools.http.Result;
import com.mylibrary.swslibrary.study.java.http.HttpSend;
import com.mylibrary.swslibrary.study.java.http.request.TestRequestBean;
import com.mylibrary.swslibrary.study.java.http.response.TestResponseBean;

import java.io.Serializable;

/**
 * @author Sws
 * @time 16:57 2021/6/11
 * @dec
 **/
public class Model implements Serializable {

    private static HttpUtil mHttpUtil;

    public static HttpUtil HttpUtil() {
        return mHttpUtil == null ? (mHttpUtil = HttpUtil.getInstance()) : mHttpUtil;
    }

    /**
     * 配置接口前缀
     */
    private static String getMainApiUrl() {
        return "http://zbhd.bj.ydxxw:9999";
//        return "";
    }

    private static <T> T getRetrofit(String url, String tag, Class<T> clazz) {
        return HttpUtil().getRetrofit(url, clazz, tag);
    }

    private static HttpSend httpSend(String url, String tag) {
        return getRetrofit(url, tag, HttpSend.class);
    }

    private static HttpSend mainHttpSend() {
        return httpSend(getMainApiUrl(), "主服务地址");
    }

    /**
     * 例
     * @param requestGetConfig
     * @param listener
     */
    public static void getConfig(TestRequestBean requestGetConfig, OnHttpObservableCallBack<Result<TestResponseBean>> listener) {
        HttpUtil().send(mainHttpSend().getConfig(requestGetConfig), listener);
    }
}
