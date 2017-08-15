package com.hjw.network.okhttp;

import android.content.Context;

import com.hjw.base.utils.SimpleLog;
import com.hjw.network.BuildConfig;
import com.hjw.network.api.ApiConfig;
import com.hjw.network.api.BaseApiService;
import com.hjw.network.okhttp.cacahe.CacheProvide;
import com.hjw.network.okhttp.interceptor.CacheInterceptor;
import com.hjw.network.okhttp.interceptor.DownLoadInterceptor;
import com.hjw.network.okhttp.interceptor.HeaderInterceptor;
import com.hjw.network.okhttp.interceptor.HttpLoggingInterceptor;
import com.hjw.network.okhttp.interceptor.RetryAndChangeIpInterceptor;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class OkHttpProvider {

    public static OkHttpClient getSimpleOkHttpClient(ApiConfig apiConfig,String domain) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                //.addInterceptor(new DownLoadInterceptor(domain))
                //.addInterceptor(new RetryAndChangeIpInterceptor(domain, apiConfig.getBaseSwitchUrls()))
                //.addNetworkInterceptor(new CacheInterceptor())
                //.cache(new CacheProvide().provideCache())
                .retryOnConnectionFailure(false)
                .connectTimeout(apiConfig.getConnectionTime(), TimeUnit.SECONDS)
                .readTimeout(apiConfig.getReadTime(), TimeUnit.SECONDS)
                .writeTimeout(apiConfig.getWriteTime(), TimeUnit.SECONDS)
                .build();
        SimpleLog.debug("OkHttpClient:"+client.toString());
        //if (BuildConfig.DEBUG) {//printf logs while  debug
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = client.newBuilder().addInterceptor(logging).build();
        //}
        return client;
    }
}
