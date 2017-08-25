package com.hjw.appframe;

import android.os.Bundle;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hjw.appframe.api.ApiConfig;
import com.hjw.appframe.api.entity.IpInfoEntity;
import com.hjw.appframe.api.param.GetIpInfoParam;
import com.hjw.appframe.api.result.IpInfoResult;
import com.hjw.base.utils.ToastUtils;
import com.hjw.commonui.BaseActivity;
import com.hjw.network.api.RequestUtils;
import com.hjw.network.callback.APICallBack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = PathConfig.PATH_MAIN)
public class MainActivity extends BaseActivity {

    @BindView(R.id.testBtn) Button testBtn;
    @BindView(R.id.toSwipeRefreshActivityBtn) Button toSwipeRefreshActivityBtn;

    @Override
    protected int provideLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this,mRootView);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @OnClick(R.id.testBtn)
    public void onTestBtnClick(){
        final GetIpInfoParam param = new GetIpInfoParam();
        param.ip = "63.223.108.42";
        RequestUtils.getInstance().get(ApiConfig.GetIpInfoUrl,param,IpInfoResult.class,new APICallBack<IpInfoEntity,IpInfoResult>(){
            @Override
            public void onSuccess(IpInfoEntity data) {
                ToastUtils.showToast(data.country);
            }
        });
    }

    @OnClick(R.id.toSwipeRefreshActivityBtn)
    public void onToSwipeRefreshActivityBtn(){
        ARouter.getInstance().build(PathConfig.PATH_SWIPE_REFRESH_ACTIVITY).navigation();
    }
}
