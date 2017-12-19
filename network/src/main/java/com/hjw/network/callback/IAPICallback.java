package com.hjw.network.callback;

import com.hjw.network.api.BaseResult;

import java.util.List;

/**
 * Created by jinwei on 2015/7/31.
 */
public  interface  IAPICallback<R extends BaseResult> {
    /**
     * 正确返回
     * @param result
     */
    public boolean onResponse(String result,Class<R> clz);

    /**
     * 服务器正确返回并且拿到正确的数据
     * @param data
     */
    public void onSuccess(R data);//返回对象
    public void onListSuccess(List<R> data);//返回对象数组


    /**
     * 服务器正确返回但没有拿到正确的数据
     * @param result
     */
    public void onFailed(Object result);

    /**
     * 服务器返回异常
     * @param t
     */
    public void onFailure(Throwable t);
}
