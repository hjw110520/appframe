package com.hjw.network.okhttp;

import com.hjw.network.api.OkHttpConfig;
import com.hjw.network.okhttp.interceptor.HeaderInterceptor;
import com.hjw.network.okhttp.interceptor.LoggingInterceptor;
import com.hjw.network.okhttp.interceptor.RetryInterceptor;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class OkHttpUtils {

    private static OkHttpUtils instance;

    private OkHttpClient mDefaultClient;

    private OkHttpConfig mDefaultConfig;

    private Map<String,OkHttpClient> otherClientMap;

    private OkHttpUtils(){
    }

    public static OkHttpUtils getInstance(){
        if(null == instance){
            synchronized (OkHttpUtils.class){
                if(null == instance){
                    instance = new OkHttpUtils();
                }
            }
        }
        return instance;
    }

    public OkHttpConfig getMDefaultConfig(){
        return mDefaultConfig;
    }

    public void initDefaultOkHttpClient(OkHttpConfig mDefaultOkHttpConfig){
        mDefaultConfig = mDefaultOkHttpConfig;
        mDefaultClient = initOkHttpClient(mDefaultConfig);
    }

    public  OkHttpClient getDefaultOkHttpClient() {
        if(null == mDefaultClient){
            throw new NullPointerException("please init default client");
        }
        return mDefaultClient;
    }

    public  OkHttpClient getOkHttpClient(OkHttpConfig mOkHttpConfig) {

        if(null == mOkHttpConfig){
            return getDefaultOkHttpClient();
        }

        if(null == otherClientMap){
            otherClientMap = new ConcurrentHashMap<String,OkHttpClient>();
        }

        String key = mOkHttpConfig.toString();
        OkHttpClient client = otherClientMap.get(key);

        if(null != client){
            return client;
        }

        client = initOkHttpClient(mOkHttpConfig);
        otherClientMap.put(key,client);
        return client;
    }

    private  OkHttpClient initOkHttpClient(OkHttpConfig mOkHttpConfig) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor(mOkHttpConfig))
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new RetryInterceptor(mOkHttpConfig))
                .retryOnConnectionFailure(false)
                .connectTimeout(mOkHttpConfig.getConnectionTime(), TimeUnit.SECONDS)
                .readTimeout(mOkHttpConfig.getReadTime(), TimeUnit.SECONDS)
                .writeTimeout(mOkHttpConfig.getWriteTime(), TimeUnit.SECONDS).build();
        return okHttpClient;
    }
}
