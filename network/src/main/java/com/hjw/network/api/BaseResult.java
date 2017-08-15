package com.hjw.network.api;

/**
 * Created by hjw on 2017/8/10.14:56
 */

public class BaseResult<T extends BaseEntity> {
    public int code;
    public String desc;
    public T data;
}
