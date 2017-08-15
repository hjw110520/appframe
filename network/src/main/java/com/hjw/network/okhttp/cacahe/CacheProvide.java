package com.hjw.network.okhttp.cacahe;

import android.app.Application;
import android.content.Context;

import com.hjw.base.BaseApplication;

import okhttp3.Cache;

public class CacheProvide {
    public Cache provideCache() {
        return new Cache(BaseApplication.getAppContext().getCacheDir(), 50*1024 * 1024);
    }
}
