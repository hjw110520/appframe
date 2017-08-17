package com.hjw.network.okhttp.interceptor;

import com.hjw.base.utils.LogUtils;
import com.hjw.network.api.OkHttpConfig;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RetryInterceptor implements Interceptor {

    private OkHttpConfig config;

    public RetryInterceptor(OkHttpConfig config) {
        this.config = config;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = doRequest(chain, request);
        int tryCount = 0;
        String url = request.url().toString();
        while (response == null && tryCount <= config.getRetryTimes()) {
            url = switchServer(url,tryCount);
            Request newRequest = request.newBuilder().url(url).build();
            tryCount++;
            LogUtils.error("Request is not successful : "+url+" and retry "+(tryCount+1));
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

    /**
     *
     * @param lastUrl 本次请求
     * @param tryCount 已经重试的次数
     * @return
     */
    private String switchServer(String lastUrl,int tryCount) {
        List<String> switchServerDomains = config.getSwitchServerDomains();

        //如果没有备用服务器或者重试次数大于备用服务器数量，则还用最后一次的url重试
        if (null == switchServerDomains || switchServerDomains.size() == 0 || (tryCount+1) > switchServerDomains.size()) {
            return lastUrl;
        }

        String originalServerDomain = config.getServerDomain();

        if (lastUrl.contains(originalServerDomain)) {//请求链接是默认服务器，则替换成备用1
            lastUrl = lastUrl.replace(originalServerDomain, switchServerDomains.get(0));
        } else {//请求链接不是默认服务器，则替换成备用next
            String nextDomain = switchServerDomains.get(tryCount);//下一个服务器地址
            String lastDomain = switchServerDomains.get(tryCount-1);//上一个服务器地址
            if(lastUrl.contains(lastDomain)){
                lastUrl = lastUrl.replace(lastDomain,nextDomain);//把服务器地址替换
            }
        }
        return lastUrl;
    }


}
