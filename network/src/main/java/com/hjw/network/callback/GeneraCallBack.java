package com.hjw.network.callback;

import android.support.annotation.Nullable;

import com.hjw.base.utils.JsonUtils;
import com.hjw.base.utils.StringUtils;
import com.hjw.base.utils.ToastUtils;
import com.hjw.network.BuildConfig;
import com.hjw.network.api.BaseResult;
import com.hjw.network.api.FailedMsg;

import java.util.List;

/**
 * Created by jinwei on 2015/7/31.
 */
public class GeneraCallBack<R extends BaseResult> implements IGeneraCallback<R>{
    public GeneraCallBack(){}

    @Override
    public  final boolean onResponse(String response,Class clz) {
        try {
            R result = paraResponseToObject(response,clz);
            if(null == result){
                onFailed(null);
                return false;
            }
            onSuccess(result);
            return true;
        }catch (Exception e1){
            onFailure(e1);
            return false;
        }
    }

    @Override
    public  void onSuccess(R result) {
    }

    @Override
    public void onFailed(FailedMsg failedMsg) {
        if(null != failedMsg){
            ToastUtils.showToast(failedMsg.toString());
        }else {
            ToastUtils.showToast("服务器异常：空数据");
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(BuildConfig.DEBUG){
            t.printStackTrace();
        }
        ToastUtils.showToast("服务器异常："+t.getMessage());
    }

    @Nullable
    private R paraResponseToObject(String response, Class clz) throws Exception{
        if(StringUtils.isEmpty(response)){
            return null;
        }
        R result = (R)JsonUtils.parseJson2Obj(response, clz);
        return result;
    }
}
