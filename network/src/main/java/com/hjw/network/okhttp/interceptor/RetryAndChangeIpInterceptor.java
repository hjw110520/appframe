package com.hjw.network.okhttp.interceptor;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RetryAndChangeIpInterceptor implements Interceptor {
    private int retryTimes = 3;//重连次数
    private String firstUrl;//初始服务器
    private List<String> switchUrls ;//允许切换服务器

    public RetryAndChangeIpInterceptor(String firstUrl, List<String> switchUrls) {
        this.firstUrl = firstUrl;
        this.switchUrls = switchUrls;
        this.retryTimes = 3;
    }

    public RetryAndChangeIpInterceptor(String firstUrl, List<String> switchUrls, int tryCount) {
        this.firstUrl = firstUrl;
        this.switchUrls = switchUrls;
        this.retryTimes = tryCount;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = doRequest(chain, request);
        int tryCount = 0;
        String url = request.url().toString();
        while (response == null && tryCount <= retryTimes) {
            url = switchServer(url);
            Request newRequest = request.newBuilder().url(url).build();
            Log.d("intercept", "Request is not successful - " + tryCount);
            tryCount++;
            // retry the request
            response = doRequest(chain, newRequest);
        }
        if (response == null) {
            throw new IOException();
        }
        return response;
    }

    private Response doRequest(Chain chain, Request request) {
        Response response = null;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
        }
        return response;
    }

    private String switchServer(String url) {
        if (switchUrls == null || switchUrls.size() == 0) {
            return url;
        }
        String newUrlString = url;
        if (url.contains(firstUrl)) {
            for (String server : switchUrls) {
                if (!firstUrl.equals(server)) {
                    newUrlString = url.replace(firstUrl, server);
                    break;
                }
            }
        } else {
            for (String server : switchUrls) {
                if (url.contains(server)) {
                    newUrlString = url.replace(server, firstUrl);
                    break;
                }
            }
        }

        return newUrlString;
    }


}
