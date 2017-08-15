package com.hjw.network.api;

import android.content.Context;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by hjw on 2017/8/10.17:21
 */

public abstract class BaseApiService{
    private ApiConfig apiConfig;
    private Context mContext;

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public ApiConfig getApiConfig() {
        return apiConfig;
    }

    public void setApiConfig(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    public abstract <T extends BaseEntity> Observable<BaseResult<T>> getObservable(Retrofit retrofit);
}
