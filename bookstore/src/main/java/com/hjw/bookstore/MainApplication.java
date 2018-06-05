package com.hjw.bookstore;

import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hjw.base.BaseApplication;
import com.hjw.network.NetInit;
import com.hjw.network.api.OkHttpConfig;

public class MainApplication extends BaseApplication {
    private static boolean isLise = false;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(BaseApplication.getAppContext());
        OkHttpConfig config = new OkHttpConfig.Builder()
                .retryTimes(10)
                .readTime(10)
                .writeTime(10)
                .serverDomain("https://www.x23us.com")
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Connection", "keep-alive")
                .addHeader("Accept", "*/*")
                .addHeader("User-Agent", "test")
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
