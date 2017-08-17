package com.hjw.appframe;

import android.view.WindowManager;

import com.hjw.appframe.api.ApiConfig;
import com.hjw.base.BaseApplication;
import com.hjw.network.NetInit;
import com.hjw.network.api.OkHttpConfig;

public class MainApplication extends BaseApplication {
    private static boolean isLise = false;

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpConfig config = new OkHttpConfig.Builder()
                .retryTimes(10)
                .readTime(10)
                .writeTime(10)
                .serverDomain(ApiConfig.Domain)
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Connection", "keep-alive")
                .addHeader("Accept", "*/*")
                .addHeader("Cookie", "add cookies here")
                .build();
        NetInit.init(config);
    }


    public static boolean isLive() {
        return isLise;
    }

    public static void setIsLive(boolean isLise) {
        MainApplication.isLise = isLise;
    }

    private WindowManager.LayoutParams windowParams = new WindowManager.LayoutParams();

    public WindowManager.LayoutParams getWindowParams() {
        return windowParams;
    }
}
