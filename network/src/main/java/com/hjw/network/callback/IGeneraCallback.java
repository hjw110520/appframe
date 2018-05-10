package com.hjw.network.callback;

import com.hjw.base.utils.ToastUtils;
import com.hjw.network.api.BaseResult;
import com.hjw.network.api.FailedMsg;

import java.util.List;

/**
 * Created by jinwei on 2015/7/31.
 */
public  interface IGeneraCallback<R extends BaseResult> extends IAPICallback{

    /**
     * 服务器正确返回并且拿到正确的数据
     * @param data
     */
    public void onSuccess(R data);//返回对象
    public void onFailed(FailedMsg failedMsg);
}
