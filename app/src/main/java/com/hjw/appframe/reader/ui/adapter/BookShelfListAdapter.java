package com.hjw.appframe.reader.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.hjw.appframe.model.BookSearchInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/5/14 0014.
 */

public class BookShelfListAdapter extends BaseAdapter{
    private List<BookSearchInfo> entities;
    private Context mContext;
    public BookShelfListAdapter(Context context){
        this.mContext = context;
    }

    public void transferData(List<BookSearchInfo> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_order_list_item, parent, false);
        }
        findViews(position, convertView);
        initData(position, convertView);
        intListener(position);
        return convertView;
    }

    private void findViews(int position, View convertView) {

    }

    protected void initData(int position, View convertView) {

    }

    private void intListener(final int  position) {

    }
}
