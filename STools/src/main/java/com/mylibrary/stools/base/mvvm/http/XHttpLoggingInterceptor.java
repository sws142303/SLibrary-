/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mylibrary.stools.base.mvvm.http;

import android.util.Log;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import okio.Buffer;
import okio.BufferedSource;

/**
 * An OkHttp interceptor which logs request and response information. Can be applied as an
 * {@linkplain OkHttpClient#interceptors() application interceptor} or as a {@linkplain
 * OkHttpClient#networkInterceptors() network interceptor}. <p> The format of the logs created by
 * this class should not be considered stable and may change slightly between releases. If you need
 * a stable logging format, use your own interceptor.
 */
public final class XHttpLoggingInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    private volatile Level level = Level.NONE;

    private final String tag;
    private String TAG = "CheckApp";

    public XHttpLoggingInterceptor(String tag) {
        this.tag = tag + " : ";
    }

    /**
     * Change the level at which this interceptor logs.
     */
    public XHttpLoggingInterceptor setLevel(Level level) {
        if (level == null) {
            throw new NullPointerException("level == null. Use Level.NONE instead.");
        }
        this.level = level;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Level level = this.level;

        Request request = chain.request();
        if (level == Level.NONE) {
            return chain.proceed(request);
        }

        boolean logBody = level == Level.BODY;
        boolean logHeaders = logBody || level == Level.HEADERS;

        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;

        Connection connection = chain.connection();
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        String requestStartMessage = "--> " + request.method() + ' ' + request.url() + ' ' + protocol;
        if (!logHeaders && hasRequestBody) {
            requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
        }
        Log.d(TAG,tag + "HTTP  " + requestStartMessage);
        if (hasRequestBody) {
            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            String body = buffer.readString(charset);
            Log.d(TAG,tag + "Request-Body --> " + body);
        }

        if (logHeaders) {
            if (hasRequestBody) {
                // Request body headers are only present when installed as a network interceptor. Force
                // them to be included (when available) so there values are known.
                if (requestBody.contentType() != null) {
                    Log.d(TAG,tag + "Request-Content-Type  --> " + requestBody.contentType());
                }
                if (requestBody.contentLength() != -1) {
                    Log.d(TAG,tag + "Request-Content-Length  --> " + requestBody.contentLength());
                }
            }

            Headers headers = request.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                String name = headers.name(i);
                // Skip headers from the request body as they are explicitly logged above.
                if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                    Log.d(TAG,tag + "HEARDERS  --> " + name + ": " + headers.value(i));
                }
            }

            if (!logBody || !hasRequestBody) {
//                xLogUtil.d("--> END " + request.method());
            } else if (bodyEncoded(request.headers())) {
//                xLogUtil.d(tag + "END  --> " + request.method() + " (encoded body omitted)");
            } else {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }
                if (isPlaintext(buffer)) {
//                    xLogUtil.d(buffer.readString(charset));
//                    xLogUtil.d("--> END " + request.method()
//                            + " (" + requestBody.contentLength() + "-byte body)");
                } else {
//                    xLogUtil.d("--> END " + request.method() + " (binary "
//                            + requestBody.contentLength() + "-byte body omitted)");
                }
            }
        }

        long startNs = System.nanoTime();
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            Log.d(TAG,"HTTP FAILED --> " + e);
            throw e;
        }
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
        Log.d(TAG,tag + "RESULT  --> " + response.code() + ' ' + response.message() + ' '
                + response.request().url() + " (" + tookMs + "ms" + (!logHeaders ? ", "
                + bodySize + " body" : "") + ')');

        if (logHeaders) {
            Headers headers = response.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                Log.d(TAG,tag + "DEFAULT_HEADERS  --> " + headers.name(i) + ": " + headers.value(i));
            }

            if (!logBody || !HttpHeaders.hasBody(response)) {
                Log.d(TAG,tag + "END HTTP  -->");
            } else if (bodyEncoded(response.headers()) && "gzip".equalsIgnoreCase(bodyEncodedString(headers))) {
                BufferedSource source = responseBody.source();
                // Buffer the entire body.
                source.request(Long.MAX_VALUE);
                Buffer buffer = source.buffer();
                InputStream stream = buffer.clone().inputStream();
                GZIPInputStream in = new GZIPInputStream(stream);
                InputStreamReader inr = new InputStreamReader(in, "utf-8");
                BufferedReader bufferedReader = new BufferedReader(inr);
                int size = stream.available();
                String body = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    body = body + line;
                }
                bufferedReader.close();
                Log.d(TAG,tag + "BODY  --> " + body);
                Log.d(TAG,tag + "END HTTP  --> (" + size + "-byte body)");
            } else {
                BufferedSource source = responseBody.source();
                // Buffer the entire body.
                source.request(Long.MAX_VALUE);
                Buffer buffer = source.buffer();
                Charset charset = UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    try {
                        charset = contentType.charset(UTF8);
                    } catch (UnsupportedCharsetException e) {
                        Log.d(TAG,tag + "END HTTP  --> Couldn't decode the response body; charset is likely malformed");
                        return response;
                    }
                }

                if (!isPlaintext(buffer)) {
                    Log.d(TAG,tag + "END HTTP  --> (binary " + buffer.size() + "-byte body omitted)");
                    return response;
                }

                if (contentLength != 0) {
                    Log.d(TAG,tag + "BODY  --> " + buffer.clone().readString(charset));
                }
                Log.d(TAG,tag + "END HTTP   --> (" + buffer.size() + "-byte body)");
            }
        }

        return response;
    }

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     */
    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            // Truncated UTF-8 sequence.
            return false;
        }
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

    private String bodyEncodedString(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null ? contentEncoding : "";
    }
}
