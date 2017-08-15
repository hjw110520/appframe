package com.hjw.network.api;

import android.util.Log;

import com.hjw.base.utils.BaseConfig;
import com.hjw.base.utils.Md5Util;
import com.hjw.base.utils.SimpleLog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 网络请求构建工具类.
 */
public class ParametersUtils {

    // 参数TreeMap
    private TreeMap<String, String> params = new TreeMap<String, String>(new Comparator<Object>() {
        public int compare(Object o1, Object o2) {
            if (o1 == null || o2 == null) {
                return 0;
            } else {
                return String.valueOf(o1).compareTo(String.valueOf(o2));
            }
        }
    });

    public ParametersUtils(BaseRequestParam baseParam) {
        parseFromObject(baseParam);
        processMd5Signature();
    }

    public Map<String, String> getReqMap() {
        return params;
    }

    /**
     * 获取get请求参数的url
     */
    public String getReqParamURL() {
        String paramUrl = "";
        BaseRequestParam baseParam = new BaseRequestParam();
        StringBuilder urlBuilder = new StringBuilder();
        Iterator<?> iter = params.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<?, ?> entry = (Map.Entry<?, ?>) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            try {
                urlBuilder.append(
                        "&" + key + "=" + URLEncoder.encode(String.valueOf(val), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                urlBuilder.append("&" + key + "=" + val);
            }
        }

        if (!urlBuilder.toString().equals("")) {
            paramUrl = urlBuilder.toString().replaceFirst("&", "?");

        }
        return paramUrl;
    }

    private void parseFromObject(BaseRequestParam baseParam) {
        Field[] fs = baseParam.getClass().getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true);
            String val = null;
            try {
                val = String.valueOf(f.get(baseParam));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (!"null".equals(val)) {
                params.put(f.getName(), val);
            }
        }
    }

    /**
     * 参数签名的具体实现
     */
    private  void processMd5Signature() {
        StringBuilder paramsValue = new StringBuilder();
        for (Map.Entry<String, String> m : params.entrySet()) {
            paramsValue.append(m.getKey()).append(m.getValue());
        }
        String key = paramsValue.toString().toUpperCase();
        String sign = null;
        try {
            sign = Md5Util.makeMd5Sum(key.getBytes("utf-8"))+BaseConfig.APP_MD5_SIGNATURE;
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleLog.info("key:"+key);
        SimpleLog.info("sign:"+sign);
        params.put("signature", sign);
    }
}
