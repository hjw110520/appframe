package com.hjw.network.api;

/**
 * Created by Administrator on 2018/5/10 0010.
 */

public class FailedMsg {
    public String code;
    public String des;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("错误代码:");
        sb.append(code);
        sb.append("|||错误描述:");
        sb.append(des);
        return sb.toString();
    }
}
