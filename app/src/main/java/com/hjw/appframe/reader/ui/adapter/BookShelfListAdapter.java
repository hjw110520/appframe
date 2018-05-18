package com.hjw.appframe.reader.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hjw.appframe.R;
import com.hjw.appframe.model.BookSearchInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/5/18 0018.
 */

public class BookShelfListAdapter extends RecyclerView.Adapter<BookShelfListAdapter.BookShelfListViewHolder>{

    private Context mContext;
    private List<BookSearchInfo> dataList;
    public BookShelfListAdapter(Context context){
        this.mContext = context;
    }

    public void transferData(List<BookSearchInfo> dataList){
        this.dataList = dataList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return dataList == null?0:dataList.size();
    }

    @Override
    public BookShelfListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_bookshelflist, parent, false);
        return new BookShelfListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookShelfListViewHolder holder, int position) {
        BookSearchInfo bookSearchInfo = dataList.get(position);
        holder.bookNameTV.setText(bookSearchInfo.title);
    }

    class BookShelfListViewHolder extends RecyclerView.ViewHolder{
        private TextView bookNameTV;
        public BookShelfListViewHolder(View itemView){
            super(itemView);
            bookNameTV = (TextView)itemView.findViewById(R.id.tv_book_name);
        }
    }
}
