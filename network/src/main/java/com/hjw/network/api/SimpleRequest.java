package com.hjw.network.api;

import com.hjw.network.callback.IAPICallback;

/**
 * Created by hjw on 2017/8/10.15:22
 */

public class SimpleRequest {
    private static SimpleRequest instance;
    private SimpleRequest(){}
    public synchronized static SimpleRequest getInstance() {
        if (instance == null) {
            instance = new SimpleRequest();
        }
        return instance;
    }

    public  <T extends BaseEntity> void post(String apiUrl, BaseRequestParam param,IAPICallback callBack) {
        //RequestUtils.getInstance().doHttpGet(apiUrl,param,callBack);
    }

    public  <T extends BaseEntity,R extends BaseResult<T>> void get(String domain,String apiUrl, BaseRequestParam param, IAPICallback callBack,Class<R> clz) {
        RequestUtils.getInstance().doHttpGet(domain,apiUrl,param,clz,callBack);
    }
}
