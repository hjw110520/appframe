package com.hjw.network.exception;

/**
 * Created by hjw on 2017/8/11.16:09
 */

public enum  ErrorCode{
    SUCCESS(0,"请求成功"),
    LOGIN_INVALID(1,"未登录，请重新登录！");
    private int code;
    private String desc;

    private ErrorCode (int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }

    public String find(int code){
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.getCode() == code) {
                return errorCode.desc;
            }
        }
        return null;
    }
}
