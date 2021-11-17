package com.mylibrary.stools.http;

/**
 * 和Http有关的常量字段
 */

public interface Http {
    int CODE_SUCCESS = 200;
    int CODE_ERR_FAILED_400 = 400;
    int CODE_ERR = 500;

    String HEADER_DEFAULT_CACHE = "Cache-Control:no-cache";
    String HEADER_DEFAULT_TYPE = "Content-Type:application/json";
}
