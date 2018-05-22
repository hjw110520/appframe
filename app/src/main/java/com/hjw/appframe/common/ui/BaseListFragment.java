package com.hjw.appframe.common.ui;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.hjw.appframe.R;
import com.hjw.appframe.common.adapter.BaseListAdapter;
import com.hjw.commonui.BaseFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by hjw on 2018/5/20.
 */

public abstract class BaseListFragment extends BaseFragment implements XRecyclerView.LoadingListener{
    protected XRecyclerView xRecyclerView;
    protected BaseListAdapter listAdapter;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        xRecyclerView = mRootView.findViewById(R.id.xRecyclerView);
        if(null != xRecyclerView){
            initXRecyclerView();
        }
        super.onActivityCreated(savedInstanceState);
    }

    private void initXRecyclerView(){
        RecyclerView.LayoutManager layoutManager = initLayoutManager();
        xRecyclerView.setLayoutManager(layoutManager);
        listAdapter = initAdapter();
        xRecyclerView.setAdapter(listAdapter);
    }

    public abstract RecyclerView.LayoutManager initLayoutManager();
    public abstract BaseListAdapter initAdapter();
}
