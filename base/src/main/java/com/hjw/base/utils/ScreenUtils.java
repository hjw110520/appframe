package com.hjw.base.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class ScreenUtils {

    private static int sDisplayWidth = 0;
    private static int sDisplayHeight = 0;
    private static float sDensity = 0;

    public static void init(Context context) {
        if (sDisplayWidth <= 0 || sDisplayHeight <= 0) {
            WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            sDisplayWidth = dm.widthPixels;
            sDisplayHeight = dm.heightPixels;
            sDensity = dm.density;
        }
    }
    // 获取屏幕宽度
    public static int getDisplayWidth(Context context) {
        if(sDisplayWidth == 0){
            init(context);
        }
        return sDisplayWidth;
    }

    // 获取屏幕高度
    public static int getDisplayHeight(Context context) {
        if(sDisplayHeight == 0){
            init(context);
        }
        return sDisplayHeight;
    }

    // 获取像素密度
    public static float getDensity(Context context){
        if(sDensity == 0){
            init(context);
        }
        return sDensity;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取系统的 状态栏高度
     * @return
     */
    public static int getStatusBarHeight(Context context)
    {
        int statusBarHeight = 0;
        try
        {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            java.lang.reflect.Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return statusBarHeight;
    }
}
