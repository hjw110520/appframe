package com.hjw.appframe.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2018/5/18 0018.
 */

public  abstract  class  BaseListAdapter<VH extends RecyclerView.ViewHolder,T> extends RecyclerView.Adapter<VH>{

    protected Context mContext;
    protected List<T> dataList;

    public BaseListAdapter(Context context){
        this.mContext = context;
    }

    public void transferData(List<T> dataList){
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList == null?0:dataList.size();
    }

    public T getItem(int position) {
        return dataList == null?null:dataList.get(position);
    }
}
