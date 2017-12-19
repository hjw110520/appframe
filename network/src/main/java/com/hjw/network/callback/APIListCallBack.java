package com.hjw.network.callback;

import com.hjw.base.utils.JsonUtils;
import com.hjw.base.utils.StringUtils;
import com.hjw.network.api.BaseResult;

import java.util.ArrayList;

/**
 * Created by jinwei on 2015/7/31.
 */
public class APIListCallBack<R extends BaseResult> extends APICallBack<R> {

    @Override
    public  final boolean onResponse(String response,Class<R> clz) {
        try {
            ArrayList listResult = paraResponseToList(response,clz);
            if(null == listResult || listResult.isEmpty()){
                onFailed(null);
                return false;
            }
            onListSuccess(listResult);//请求成功
            return true;
        }catch (Exception e1){
            onFailure(e1);
            return false;
        }
    }

    private ArrayList<R> paraResponseToList(String response, Class<R> clz) throws Exception{
        if(StringUtils.isEmpty(response)){
            return null;
        }
        ArrayList<R> listResult = JsonUtils.parseJson2List(response, clz);
        return listResult;
    }
}
