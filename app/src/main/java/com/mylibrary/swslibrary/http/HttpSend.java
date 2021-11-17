package com.mylibrary.swslibrary.http;

import com.mylibrary.stools.http.Result;
import com.mylibrary.swslibrary.http.request.TestRequestBean;
import com.mylibrary.swslibrary.http.response.TestResponseBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 *  存放接口地址
 */
public interface HttpSend {

    /**
     * 例
     * @param params
     * @return
     */
    @POST("config/getConfig")
    Observable<Result<TestResponseBean>> getConfig(@Body TestRequestBean params);




}
