package com.hjw.bookshelf.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjw.bookshelf.PathConfig;
import com.hjw.bookshelf.R;
import com.hjw.bookshelf.adapter.BookShelfListAdapter;
import com.hjw.bookshelf.common.BookShelfHelper;
import com.hjw.commonui.BaseFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/5/14 0014.
 */
@Route(path = PathConfig.FRAGMENT_BOOK_SHELF)
public class BookshelfFragment extends BaseFragment implements XRecyclerView.LoadingListener{

    @BindView(R.id.xRecyclerView)XRecyclerView xRecyclerView;
    BookShelfListAdapter bookShelfListAdapter;

    @Override
    protected int provideLayoutResId() {
        return R.layout.bookshelf_fragment_bookshelf;
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
