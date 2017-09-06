package com.hjw.commonui.hwebview;

/**
 * Created by hjw on 2017/9/5.9:51
 */

public class CallJsHelper {
    /**
     * function CallJs(var para){
     *     ...
     * }
     */
    public String callName;
    public String callParam;//json

    public String toLoadUrl(){
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:");
        sb.append(callName);
        sb.append("(");
        sb.append(callParam);
        sb.append(")");
        return sb.toString();
    }

}
