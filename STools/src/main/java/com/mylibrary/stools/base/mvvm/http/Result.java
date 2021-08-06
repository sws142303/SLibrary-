package com.mylibrary.stools.base.mvvm.http;


import com.google.gson.annotations.SerializedName;

/**
 * 泛型解析类
 */

public class Result<T> {

    @SerializedName(value = "code", alternate = {"resultCode"})
    private int code;
    @SerializedName(value = "message", alternate = {"info", "resultMsg"})
    private String message;
    @SerializedName(value = "data", alternate = {"res", "resultData"})
    private T data;
    @SerializedName(value = "time",alternate = {"currentTime"})
    private long time;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", time=" + time +
                '}';
    }
}
