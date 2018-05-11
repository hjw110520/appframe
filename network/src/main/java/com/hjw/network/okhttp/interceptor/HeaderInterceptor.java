package com.hjw.network.okhttp.interceptor;

import com.hjw.network.api.OkHttpConfig;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    private OkHttpConfig config;

    public HeaderInterceptor(OkHttpConfig config) {
        this.config = config;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Map<String,String> headersMap = config.getHeaders();

        if(null == headersMap || headersMap.size() == 0){
            return chain.proceed(chain.request());
        }
        Request.Builder requestBuilder = chain.request().newBuilder();

        for (Map.Entry<String, String> headerMap : headersMap.entrySet()) {
            requestBuilder.addHeader(headerMap.getKey(),headerMap.getValue());
        }
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
