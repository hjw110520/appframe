package com.hjw.appframe.reader.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.hjw.appframe.R;
import com.hjw.appframe.reader.ui.adapter.BookShelfListAdapter;
import com.hjw.commonui.BaseFragment;
import com.hjw.commonui.view.xlistview.XListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/5/14 0014.
 */

public class BookshelfFragment extends BaseFragment implements XListView.IXListViewListener{

    @BindView(R.id.mXListView) private XListView mXListView;

    @Override
    protected int provideLayoutResId() {
        return R.layout.fragment_bookshelf;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this,mRootView);
        mXListView.setAdapter(new BookShelfListAdapter());
        mXListView.setPullLoadEnable(false);
        mXListView.setXListViewListener(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData(View rootView, Bundle savedInstanceState) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
