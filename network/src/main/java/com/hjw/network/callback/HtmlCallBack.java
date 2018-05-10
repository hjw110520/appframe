package com.hjw.network.callback;

import android.support.annotation.Nullable;

import com.hjw.base.utils.JsonUtils;
import com.hjw.base.utils.StringUtils;
import com.hjw.base.utils.ToastUtils;
import com.hjw.network.BuildConfig;
import com.hjw.network.api.BaseResult;
import com.hjw.network.api.FailedMsg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by jinwei on 2015/7/31.
 */
public class HtmlCallBack implements IAPICallback{
    public HtmlCallBack(){}

    @Override
    public  final boolean onResponse(String response,Class clz) {
        try {
            Document htmlDoc = Jsoup.parse(response);
            onSuccess(htmlDoc);
            return true;
        }catch (Exception e1){
            onFailure(e1);
            return false;
        }
    }

    public  void onSuccess(Document htmlDoc) {

    }

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
}
