package com.mylibrary.stools.http;



import androidx.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.Executors;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpUtil {

    private static HttpUtil INSTANCE;
    private static Scheduler scheduler;
    public static HttpUtil getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpUtil();
                }
            }
        }
        return INSTANCE;
    }

    private HttpUtil() {
    }

    public <T> T getRetrofit(String baseUrl, Class<T> clazz, String[]... headers) {
        return getRetrofit(baseUrl, clazz, null, headers);
    }

    public <T> T getRetrofit(String baseUrl, Class<T> clazz, String tag, String[]... headers) {
        return makeRetrofitBuilder(baseUrl, true, tag, headers)
                .build()
                .create(clazz);
    }

    public Retrofit.Builder makeRetrofitBuilder(String baseUrl, boolean json, String tag, final String[]... headers) {
        Retrofit.Builder client = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(makeClient(headers, tag));
        if (json) {
            client.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());
        }
        return client;
    }

    @NonNull
    private OkHttpClient makeClient(final String[][] headers, String tag) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        if (tag != null) {
            builder.addInterceptor(new XHttpLoggingInterceptor(tag).setLevel(HttpLoggingInterceptor.Level.BODY));
        }

        return builder.addInterceptor(chain -> {
            Request.Builder builder1 = chain.request().newBuilder();
            if (headers != null) {
                for (String[] header : headers) {
                    builder1.addHeader(header[0], header[1]);
                }
            }
            return chain.proceed(builder1.build());
        }).build();
    }

    public <T> Flowable<T> send(Flowable<T> flowable, OnHttpFlowableCallBack<T> listener) {
        if (listener == null) {
            listener = OnHttpFlowableCallBack.DEFAULT_CALLBACK;
        }
        final OnHttpFlowableCallBack<T> finalListener = listener;
        if (scheduler == null){
            scheduler = Schedulers.from(Executors.newFixedThreadPool(5));
        }
        flowable.subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .onTerminateDetach()
                .subscribe(new Subscriber<T>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                        finalListener.onSubscribe(s);
                    }

                    @Override
                    public void onNext(T t) {
                        finalListener.onSuccess(t);
                        finalListener.onFinish(true);
                    }

                    @Override
                    public void onError(Throwable t) {
                        finalListener.onFailure(t);
                        finalListener.onFinish(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return flowable;
    }

    public <T> Observable<T> send(Observable<T> observable, OnHttpObservableCallBack<T> listener) {
        if (listener == null) {
            listener = OnHttpObservableCallBack.DEFAULT_CALLBACK;
        }
        final OnHttpObservableCallBack<T> finalListener = listener;
        if (scheduler == null){
            scheduler = Schedulers.from(Executors.newFixedThreadPool(5));
        }
        observable.subscribeOn(scheduler)
                .observeOn(AndroidSchedulers.mainThread())
                .onTerminateDetach()
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        finalListener.onSubscribe(d);
                    }

                    @Override
                    public void onNext(T t) {
                        finalListener.onSuccess(t);
                        finalListener.onFinish(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        finalListener.onFailure(e);
                        finalListener.onFinish(false);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return observable;
    }
}
