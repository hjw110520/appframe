package com.hjw.commonui.hwebview;

import android.content.Context;
import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebView;

/**
 * Created by hjw on 2017/9/5.9:17
 */

public class BaseWebView extends WebView{

    public BaseWebView(Context context) {
        super(context);
    }

    private void init() {
        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);
        this.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    public void CallJs(CallJsHelper callJsEntity){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            this.loadUrl(callJsEntity.toLoadUrl());
        } else {
            this.evaluateJavascript(callJsEntity.toLoadUrl(), new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    //此处为 js 返回的结果
                }
            });
        }
    }

}
