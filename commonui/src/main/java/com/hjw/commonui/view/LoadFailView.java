package com.hjw.commonui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hjw.commonui.R;

/**
 * Created by jinwei on 2015/8/7.
 */
public class LoadFailView extends FrameLayout {
    private ImageView refreshIcon_iv;
    private TextView failedReason_tv;
    private TextView operationHint_tv;

    public LoadFailView(Context context) {
        super(context);
        initView(null, 0);
    }

    public LoadFailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs, 0);

    }

    public LoadFailView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(attrs, defStyle);
    }

    private void initView(AttributeSet attrs, int defStyle) {
        inflate(getContext(), R.layout.view_load_fail, this);
        refreshIcon_iv = findViewById(R.id.iv_refresh_icon);
        failedReason_tv = findViewById(R.id.failed_reason);
        operationHint_tv = findViewById(R.id.operation_hint);
    }

    public void showLoadFailView(Drawable imgRes, String text1, String text2){
        refreshIcon_iv.setImageDrawable(imgRes);
        failedReason_tv.setText(text1);
        operationHint_tv.setText(text2);
        setVisibility(View.VISIBLE);
    }

    public void showNetWorkErrorView(){
        showLoadFailView(getResources().getDrawable(R.mipmap.failed_refresh_icon),
                "网络异常",
                "点击屏幕，重新加载");
    }

    public void hideLoadFailView(){
        setVisibility(View.GONE);
    }
}
