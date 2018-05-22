package com.hjw.appframe.reader.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hjw.appframe.R;
import com.hjw.appframe.common.adapter.BaseListAdapter;
import com.hjw.appframe.common.ui.BaseListFragment;
import com.hjw.appframe.manager.ApiManager;
import com.hjw.appframe.model.BookIndex;
import com.hjw.appframe.reader.ui.adapter.BookIndexAdapter;
import com.hjw.network.callback.SimpleCallBack;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by hjw on 2018/5/20.
 */

public class BookIndexFragment extends BaseListFragment{
    @Override
    protected int provideLayoutResId() {
        return R.layout.fragment_bookindex;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this,mRootView);
    }

    @Override
    protected void initData(View rootView, Bundle savedInstanceState) {
        String indexUrl = getActivity().getIntent().getStringExtra("indexUrl");
        ApiManager apiManager = new ApiManager();
        apiManager.getBookIndex(indexUrl,new SimpleCallBack(){
            @Override
            public void onSuccess(Object result) {
                listAdapter.transferData((List)result);
            }
        });
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
    public BaseListAdapter initAdapter() {
        String indexUrl = getActivity().getIntent().getStringExtra("indexUrl");
        return new BookIndexAdapter(getActivity(),indexUrl);
    }

    @Override
    public RecyclerView.LayoutManager initLayoutManager() {
        return new LinearLayoutManager(fragmentActivity);
    }
}
