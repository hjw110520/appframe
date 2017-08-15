package com.hjw.appframe;

import android.view.WindowManager;

import com.hjw.base.BaseApplication;

import java.util.Map;


/**
 * Created by YeDongXia on 2015/8/3.
 */
public class MainApplication extends BaseApplication {
    private static boolean isLisve = false;
    public static boolean isUpdateApp = false;
    public static boolean isFist=false;

    @Override
    public void onCreate() {
        super.onCreate();
    }


    public static boolean isLive() {
        return isLisve;
    }

    public static void setIsLive(boolean isLisve) {
        MainApplication.isLisve = isLisve;
    }

    private WindowManager.LayoutParams windowParams = new WindowManager.LayoutParams();

    public WindowManager.LayoutParams getWindowParams() {
        return windowParams;
    }
}
