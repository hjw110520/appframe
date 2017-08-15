package com.hjw.network.callback;

import com.hjw.network.api.BaseEntity;
import com.hjw.network.api.BaseResult;

/**
 * Created by jinwei on 2015/7/31.
 */
public  interface  IAPICallback<T extends BaseEntity,R extends BaseResult<T>> {
    /**
     * 正确返回
     * @param result
     */
    public boolean onResponse(String result,Class<R> clz);

    /**
     * 服务器正确返回并且拿到正确的数据
     * @param data
     */
    public void onSuccess(T data);

    /**
     * 服务器正确返回但没有拿到正确的数据
     * @param result
     */
    public void onFailed(BaseResult<T> result);

    /**
     * 服务器返回异常
     * @param t
     */
    public void onFailure(Throwable t);
}
