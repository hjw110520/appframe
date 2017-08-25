package com.hjw.network.api;
import com.hjw.network.callback.IAPICallback;
import com.hjw.network.okhttp.OkHttpUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hjw on 2017/8/10.17:05
 */

public class RequestUtils {
    private static RequestUtils instance;
    private RequestUtils(){}
    public static RequestUtils getInstance(){
        if(null == instance){
            synchronized (RequestUtils.class){
                if(null == instance){
                    instance = new RequestUtils();
                }
            }
        }
        return instance;
    }

    public <T extends BaseEntity,R extends BaseResult<T>>  void get(String apiUrl,BaseRequestParam param,final Class<R> clz,final IAPICallback callBack){
        get(null,apiUrl,param,clz,callBack);
    }

    public <T extends BaseEntity,R extends BaseResult<T>>  void get(OkHttpConfig apiConfig,String apiUrl,BaseRequestParam param,final Class<R> clz,final IAPICallback callBack){
        OkHttpClient okHttpClient = OkHttpUtils.getInstance().getOkHttpClient(apiConfig);
        if(null == apiConfig){
            apiConfig = OkHttpUtils.getInstance().getMDefaultConfig();
        }
        /*创建retrofit对象*/
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(apiConfig.getServerDomain())
                .build();
        IBaseRequest baseRequest = retrofit.create(IBaseRequest.class);

        if(null == param){
            param = new BaseRequestParam();
        }

        ParametersUtils parametersUtils = new ParametersUtils(param);

        Observable<String> observable = baseRequest.Obget(apiUrl,parametersUtils.getReqMap());

        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(null == callBack){
                            return;
                        }
                        callBack.onFailure(e);
                    }

                    @Override
                    public void onNext(String baseEntityBaseResult) {
                        if(null == callBack){
                            return;
                        }
                        callBack.onResponse(baseEntityBaseResult,clz);
                    }
                });;
    }
}
