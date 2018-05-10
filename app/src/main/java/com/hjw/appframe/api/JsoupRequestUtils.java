package com.hjw.appframe.api;

import com.hjw.network.api.BaseRequestParam;
import com.hjw.network.api.RequestUtils;
import com.hjw.network.callback.HtmlCallBack;

import org.jsoup.nodes.Document;

/**
 * Created by Administrator on 2018/5/10 0010.
 */

public class JsoupRequestUtils {
    public void get(String apiUrl){
        RequestUtils.getInstance().get(apiUrl,new BaseRequestParam(),String.class,new HtmlCallBack(){
            @Override
            public void onSuccess(Document htmlDoc) {

            }
        });
    }
}
