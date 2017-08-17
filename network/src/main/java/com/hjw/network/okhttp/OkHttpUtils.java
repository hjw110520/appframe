package com.hjw.network.okhttp;

import com.hjw.base.utils.LogUtils;
import com.hjw.base.utils.StringUtils;
import com.hjw.network.BuildConfig;
import com.hjw.network.api.OkHttpConfig;
import com.hjw.network.okhttp.interceptor.HeaderInterceptor;
import com.hjw.network.okhttp.interceptor.RetryInterceptor;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

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

    public void initDefaultOkHttpClient(OkHttpConfig mDefaultOkHttpConfig){
        mDefaultClient = new OkHttpClient();
        mDefaultConfig = mDefaultOkHttpConfig;
        getOkHttpClient(mDefaultConfig,mDefaultClient);
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

        client = getOkHttpClient(mOkHttpConfig,new OkHttpClient());
        otherClientMap.put(key,client);
        return client;
    }

    private  OkHttpClient getOkHttpClient(OkHttpConfig mOkHttpConfig,OkHttpClient okHttpClient) {
        OkHttpClient.Builder builder = okHttpClient.newBuilder();
        builder.addInterceptor(new HeaderInterceptor(mOkHttpConfig))
                .addInterceptor(new RetryInterceptor(mOkHttpConfig))
                .retryOnConnectionFailure(false)
                .connectTimeout(mOkHttpConfig.getConnectionTime(), TimeUnit.SECONDS)
                .readTimeout(mOkHttpConfig.getReadTime(), TimeUnit.SECONDS)
                .writeTimeout(mOkHttpConfig.getWriteTime(), TimeUnit.SECONDS)
                .build();
        if (BuildConfig.DEBUG) {//printf logs while  debug
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient = okHttpClient.newBuilder().addInterceptor(logging).build();
        }
        return okHttpClient;
    }
}
