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

    public  void get(String apiUrl, BaseRequestParam param, final Class clz, final IAPICallback callBack){
        get(null,apiUrl,param,clz,callBack);
    }

    public  void get(OkHttpConfig apiConfig, String apiUrl, BaseRequestParam param, final Class clz, final IAPICallback callBack){
        doRequest(apiConfig,apiUrl,param,clz,callBack,true);
    }

    public   void post(String apiUrl, BaseRequestParam param, final Class clz, final IAPICallback callBack){
        post(null,apiUrl,param,clz,callBack);
    }

    public   void post(OkHttpConfig apiConfig, String apiUrl, BaseRequestParam param, final Class clz, final IAPICallback callBack){
        doRequest(apiConfig,apiUrl,param,clz,callBack,false);
    }

    private  void doRequest(OkHttpConfig apiConfig, String apiUrl, BaseRequestParam param, final Class clz, final IAPICallback callBack, boolean isGet){
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

        Observable<String> observable;
        if(isGet){
            observable = baseRequest.Obget(apiUrl,parametersUtils.getReqMap());
        }else {
            observable = baseRequest.Obpost(apiUrl,parametersUtils.getReqMap());
        }


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

    private <T extends  BaseResult>  void doRequestV2(OkHttpConfig apiConfig, String apiUrl, BaseRequestParam param, final Class<T> clz, final IAPICallback callBack, boolean isGet){
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
        IBaseRequestV2 baseRequest = retrofit.create(IBaseRequestV2.class);

        if(null == param){
            param = new BaseRequestParam();
        }

        ;
        ParametersUtils parametersUtils = new ParametersUtils(param);
        Observable<T> observable;
        if(isGet){
            observable = baseRequest.Obget(apiUrl,parametersUtils.getReqMap());
        }else {
            observable = baseRequest.Obpost(apiUrl,parametersUtils.getReqMap());
        }

        Subscriber subscriber = new Subscriber<T>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(T o) {

            }
        };

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
                });
    }
}
