package com.hjw.appframe.reader.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.SearchView;

import com.hjw.appframe.R;
import com.hjw.appframe.manager.ApiManager;
import com.hjw.appframe.model.BookSearchInfo;
import com.hjw.appframe.reader.ui.adapter.BookStoreListAdapter;
import com.hjw.commonui.BaseFragment;
import com.hjw.network.callback.SimpleCallBack;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/14 0014.
 */

public class BookstoreFragment extends BaseFragment{

    @BindView(R.id.bookSearchV) SearchView bookSearchV;
    @BindView(R.id.xRecyclerView) XRecyclerView xRecyclerView;
    BookStoreListAdapter bookStoreListAdapter;
    @Override
    protected int provideLayoutResId() {
        return R.layout.fragment_bookstore;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this,mRootView);
        bookSearchV.setIconifiedByDefault(false);
        bookSearchV.setSubmitButtonEnabled(true);
        bookSearchV.setQueryHint("查找");
        initXRecyclerView();
    }

    @Override
    protected void initListener() {
        bookSearchV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ApiManager apiManager = new ApiManager();
                apiManager.searchByKeyWord(query,new SimpleCallBack(){
                    @Override
                    public void onSuccess(Object result) {
                        List<BookSearchInfo> list = (List<BookSearchInfo>)result;
                        bookStoreListAdapter.transferData(list);
                    }

                    @Override
                    public void onFailed(Object result) {
                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    protected void initData(View rootView, Bundle savedInstanceState) {

    }

    private void initXRecyclerView(){
        LinearLayoutManager layoutManager=new LinearLayoutManager(fragmentActivity);
        xRecyclerView.setLayoutManager(layoutManager);
        bookStoreListAdapter =  new BookStoreListAdapter(fragmentActivity);
        xRecyclerView.setAdapter(bookStoreListAdapter);
    }
}
