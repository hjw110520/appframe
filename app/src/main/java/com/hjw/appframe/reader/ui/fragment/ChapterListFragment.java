package com.hjw.appframe.reader.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hjw.appframe.common.ui.BaseListFragment;
import com.hjw.commonui.BaseFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.ButterKnife;

/**
 * Created by hjw on 2018/5/20.
 */

public class ChapterListFragment extends BaseListFragment{
    @Override
    protected int provideLayoutResId() {
        return 0;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this,mRootView);
    }

    @Override
    protected void initData(View rootView, Bundle savedInstanceState) {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public RecyclerView.Adapter initAdapter() {
        return null;
    }

    @Override
    public RecyclerView.LayoutManager initLayoutManager() {
        return null;
    }
}
