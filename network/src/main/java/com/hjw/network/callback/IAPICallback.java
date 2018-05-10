package com.hjw.network.callback;

import com.hjw.network.api.BaseResult;

import java.util.List;

/**
 * Created by jinwei on 2015/7/31.
 */
public  interface  IAPICallback{
    /**
     * 正确返回
     * @param result
     * @param clz 想要打包成的数据类型
     * @return
     */
    public boolean onResponse(String result,Class clz);

    /**
     * 服务器返回异常
     * @param t
     */
    public void onFailure(Throwable t);
}
