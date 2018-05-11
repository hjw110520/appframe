package com.hjw.network.api;

import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;


/**
 * Created by jinwei on 2015/7/31.
 */
public interface IBaseRequest {

    @GET()
    Observable<String> Obget(@Url String url);

    @FormUrlEncoded
    @POST()
    Observable<String> Obpost(@Url String url, @FieldMap Map<String, String> params);

}
