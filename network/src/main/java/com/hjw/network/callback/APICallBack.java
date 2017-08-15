package com.hjw.network.callback;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hjw.base.utils.JsonUtils;
import com.hjw.base.utils.StringUtils;
import com.hjw.base.utils.ToastUtils;
import com.hjw.network.api.BaseEntity;
import com.hjw.network.api.BaseResult;
import com.hjw.network.exception.ErrorCode;

/**
 * Created by jinwei on 2015/7/31.
 */
public class  APICallBack <T extends BaseEntity,R extends BaseResult<T>>  implements IAPICallback<T,R> {
    public  APICallBack(){
    }

    @Override
    public  final boolean onResponse(String response,Class<R> clz) {
        try {
            BaseResult<T> result = paraResponseToBaseResult(response,clz);
            if(null == result){
                onFailed(null);
                return false;
            }
            if (result.code == ErrorCode.SUCCESS.getCode()) {
                onSuccess(result.data);//请求成功，并且返回了000000状态
                return true;
            }else {
                onFailed(result);//请求成功，但是返回了非000000状态
                return false;
            }
        }catch (Exception e1){
            onFailure(e1);
            return false;
        }
    }

    @Override
    public  void onSuccess(T data) {
    }

    @Override
    public void onFailed(BaseResult<T> result) {
        if(null != result && StringUtils.isEmpty(result.desc)){
            ToastUtils.showToast(result.desc);
            //可添加一些公共错误的处理，如未登录
            /*if(result.code == ErrorCode.LOGIN_INVALID.getCode()){
                 //登出处理
            }*/
        }else {
            ToastUtils.showToast("未知数据错误,返回数据为空");
        }
    }

    @Override
    public void onFailure(Throwable t) {
        ToastUtils.showToast("服务器异常："+t.getMessage());
    }

    private BaseResult<T> paraResponseToBaseResult(String response,Class<R> clz) throws Exception{
        if(StringUtils.isEmpty(response)){
            return null;
        }
        BaseResult<T> result = (BaseResult<T>) JsonUtils.parseJson2Obj(response, clz);
        return result;
    }
}
