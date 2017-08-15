package com.hjw.appframe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hjw.appframe.api.param.GetIpInfoParam;
import com.hjw.appframe.api.entity.IpInfoEntity;
import com.hjw.appframe.api.result.IpInfoResult;
import com.hjw.base.utils.SimpleLog;
import com.hjw.network.api.RequestUtils;
import com.hjw.network.callback.APICallBack;

public class MainActivity extends AppCompatActivity {
        String domain = "http://ip.taobao.com/service/";
        String getIpInfoUrl = "getIpInfo.php";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Button testBtn = (Button) findViewById(R.id.testBtn);
            final GetIpInfoParam param = new GetIpInfoParam();
            param.ip = "63.223.108.42";
            testBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RequestUtils.getInstance().doHttpGet(domain,getIpInfoUrl,param,IpInfoResult.class,new APICallBack<IpInfoEntity,IpInfoResult>(){
                        @Override
                        public void onSuccess(IpInfoEntity data) {
                            SimpleLog.debug(data.country);
                        }
                    });
                }
            });
        }

}
