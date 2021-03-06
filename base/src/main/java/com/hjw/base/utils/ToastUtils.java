package com.hjw.base.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;


import com.hjw.base.BaseApplication;

import java.lang.ref.WeakReference;


/**
 * 一个可复用的toast工具类。
 */
public class ToastUtils {

    private ToastUtils() {
    }

    private static WeakReference<Toast> mToastRef;

    private static Toast ensureToastInstance(Context context) {
        Toast temp;
        if (null == mToastRef || null == (temp = mToastRef.get())) {
            mToastRef = new WeakReference<Toast>(Toast.makeText(context, "", Toast.LENGTH_SHORT));
            temp = mToastRef.get();
        }
        return temp;
    }

    private static Handler getHandler() {
        return BaseApplication.getHandler();
    }

    private static Context getContext() {
        return BaseApplication.getAppContext();
    }

    public static void showToast(final CharSequence message) {
        showToastCheckingThread(message, 500);
    }
    public static void showToast(final CharSequence message, int time) {
        showToastCheckingThread(message, time);
    }


    /**
     * @param resId string resource ID
     */
    public static void showToast(final int resId) {
        final CharSequence message = getContext().getString(resId);
        showToast(message);
    }

    public static void showLongToast(CharSequence message) {
        showToastCheckingThread(message, Toast.LENGTH_LONG);
    }

    /**
     * @param resId string resource ID
     */
    public static void showLongToast(int resId) {
        final CharSequence message = getContext().getString(resId);
        showLongToast(message);
    }

    private static void showToastCheckingThread(final CharSequence message, final int length) {
        if (AndroidUtils.isMainThread()) {
            showToast0(message, length);
        } else {
            try {
                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        showToast0(message, length);
                    }
                });
            } catch (Exception e) {
            }
        }
    }

    private static void showToast0(CharSequence message, int length) {
        try {
            Toast temp = ensureToastInstance(getContext());
            temp.setDuration(length);
            temp.setText(message);
            temp.show();
        } catch (Exception e) {
        }
    }

    public static void cancelToast() {
        try {
            ensureToastInstance(getContext()).cancel();
        } catch (Exception e) {
        }

    }

}
