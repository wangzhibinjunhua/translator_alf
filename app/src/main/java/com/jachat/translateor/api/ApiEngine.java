package com.jachat.translateor.api;

import com.jachat.translateor.config.App;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Perryn on 2016/10/30.
 */

public class ApiEngine {

    private volatile static ApiEngine apiEngine;
    private Retrofit retrofit;

    private ApiEngine() {

        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        //缓存
        int size = 1024 * 1024 * 100;
        File cacheFile = new File(App.getContext().getCacheDir(), "OkHttpCache");
        Cache cache = new Cache(cacheFile, size);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
                .writeTimeout(12, TimeUnit.SECONDS)
                .addNetworkInterceptor(new NetworkInterceptor())
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();


    }

    public static ApiEngine getInstance() {
        if (apiEngine == null) {
            synchronized (ApiEngine.class) {
                if (apiEngine == null) {
                    apiEngine = new ApiEngine();
                }
            }
        }
        return apiEngine;
    }

}
