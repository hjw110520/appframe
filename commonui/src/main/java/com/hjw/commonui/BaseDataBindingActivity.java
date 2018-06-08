package com.hjw.commonui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.hjw.base.LocalBroadcasts;


/**
 * 基础Activity
 */
public  abstract class  BaseDataBindingActivity extends AppCompatActivity {

    protected View mRootView;
    public static boolean isbackgroud = false;
    protected ViewDataBinding viewDataBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        viewDataBinding = initViewDataBinding();
        mRootView = viewDataBinding.getRoot();

        initView(savedInstanceState);
        initListener();
        initBroadcast();
        initData(savedInstanceState);

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    protected View getRootView() {
        return mRootView;
    }

    public abstract ViewDataBinding initViewDataBinding();
    //return DataBindingUtil.setContentView(this,provideLayoutResId());

    /**
     * 步骤一：初始化View，比如findViewById等操作
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 步骤二：初始化View的Listener，比如onClick等监听器
     */
    protected  void initListener(){};

    /**
     * 步骤三：初始化数据
     */
    protected abstract void initData(Bundle savedInstanceState);

    private BroadcastReceiver mPrReceiver;

    protected void initBroadcast() {
        String[] actions = provideBroadcastActions();
        if (null == actions || actions.length == 0) {
            return;
        }
        registerLocalBroadcastReceiver(mPrReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                onReceiveBroadcast(intent.getAction(), intent);
            }
        }, actions);
    }

    protected String[] provideBroadcastActions() {
        return null;
    }

    /**
     * 根据{@link #provideBroadcastActions()}中提供的
     * @param action Intent中的action，方便调用
     * @param intent 广播Intent
     */
    protected void onReceiveBroadcast(String action, Intent intent) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterLocalReceiver(mPrReceiver);
    }

    protected void registerLocalBroadcastReceiver(BroadcastReceiver receiver, String... actions) {
        LocalBroadcasts.registerLocalReceiver(receiver, actions);
    }

    protected void registerLocalBroadcastReceiver(BroadcastReceiver receiver, IntentFilter... filters) {
        LocalBroadcasts.registerLocalReceiver(receiver, filters);
    }

    protected void unregisterLocalReceiver(BroadcastReceiver receiver) {
        LocalBroadcasts.unregisterLocalReceiver(receiver);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                hideInputMethod(this, v);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    public Boolean hideInputMethod(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            return imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}