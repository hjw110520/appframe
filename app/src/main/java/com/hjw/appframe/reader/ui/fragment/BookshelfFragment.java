package com.hjw.appframe.reader.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.hjw.appframe.R;
import com.hjw.appframe.reader.ui.adapter.BookShelfListAdapter;
import com.hjw.appframe.reader.ui.common.BookShelfHelper;
import com.hjw.commonui.BaseFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/5/14 0014.
 */

public class BookshelfFragment extends BaseFragment implements XRecyclerView.LoadingListener{

    @BindView(R.id.xRecyclerView) XRecyclerView xRecyclerView;
    BookShelfListAdapter bookShelfListAdapter;

    @Override
    protected int provideLayoutResId() {
        return R.layout.fragment_bookshelf;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this,mRootView);
        initXRecyclerView();
    }

    @Override
    protected void initListener() {
        xRecyclerView.setLoadingListener(this);
    }

    @Override
    protected void initData(View rootView, Bundle savedInstanceState) {
        BookShelfHelper bookShelfHelper = new BookShelfHelper();
        bookShelfListAdapter.transferData(bookShelfHelper.getBookShelf(fragmentActivity));
    }

    @Override
    public void onRefresh() {
        xRecyclerView.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        xRecyclerView.refreshComplete();
    }

    @OnClick(R.id.btnClear)
    public void onBtnClearClick(View view){
        BookShelfHelper bookShelfHelper = new BookShelfHelper();
        bookShelfHelper.clearBookShelf(fragmentActivity);
    }

    private void initXRecyclerView(){
        LinearLayoutManager layoutManager=new LinearLayoutManager(fragmentActivity);
        xRecyclerView.setLayoutManager(layoutManager);
        bookShelfListAdapter =  new BookShelfListAdapter(fragmentActivity);
        xRecyclerView.setAdapter(bookShelfListAdapter);
    }
}
