package com.hjw.appframe.sample;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjw.appframe.PathConfig;
import com.hjw.appframe.R;
import com.hjw.base.utils.ToastUtils;
import com.hjw.commonui.BaseActivity;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hjw on 2017/8/21.15:18
 */
@Route(path = PathConfig.PATH_SWIPE_REFRESH_ACTIVITY)
public class RefreshActivity extends BaseActivity{

    @BindView(R.id.mListView)
    ListView listView;

    @BindView(R.id.mRefreshLayout)
    TwinklingRefreshLayout mRefreshLayout;
    private ArrayAdapter<String> mAdapter;
    private List<String> mDatas = new ArrayList<String>(Arrays.asList("test1", "test2", "test3", "test4", "test5",
            "test6","test7","test7","test7","test7","test7","test7","test7","test7","test7","test7"));

    private RefreshListenerAdapter refreshListenerAdapter = new RefreshListenerAdapter() {
        @Override
        public void onRefresh(TwinklingRefreshLayout refreshLayout) {
            ToastUtils.showToast("onRefresh");
            refreshLayout.finishRefreshing();
        }

        @Override
        public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
            ToastUtils.showToast("onLoadMore");
            refreshLayout.finishLoadmore();
        }
    };

    @Override
    protected int provideLayoutResId() {
        return R.layout.swipe_refresh_activity;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);
        listView.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        mRefreshLayout.setOnRefreshListener(refreshListenerAdapter);
    }
}
