package com.hjw.network;

import com.hjw.network.api.OkHttpConfig;
import com.hjw.network.okhttp.OkHttpUtils;

/**
 * Created by hjw on 2017/8/16.15:54
 */

public class NetInit {
    public static void init(OkHttpConfig defaultOkHttpConfig){
        OkHttpUtils.getInstance().initDefaultOkHttpClient(defaultOkHttpConfig);
    }
}
