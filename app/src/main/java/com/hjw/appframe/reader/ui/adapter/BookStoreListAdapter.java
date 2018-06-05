package com.hjw.appframe.reader.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hjw.appframe.R;
import com.hjw.appframe.model.BookSearchInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/18 0018.
 */

public class BookStoreListAdapter extends RecyclerView.Adapter<BookStoreListAdapter.BookShelfListViewHolder>{

    private Context mContext;
    private List<BookSearchInfo> dataList;
    public BookStoreListAdapter(Context context){
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.app_adapter_bookstorelist, parent, false);
        return new BookShelfListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookShelfListViewHolder holder, int position) {
        final BookSearchInfo bookSearchInfo = dataList.get(position);
        holder.bookSearchInfo.setText(bookSearchInfo.toSimpleInfo());
        holder.collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*BookShelfHelper bookShelfHelper = new BookShelfHelper();
                bookShelfHelper.addToBookShelf(mContext,bookSearchInfo);*/
            }
        });
}

    class BookShelfListViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_book_search_info) TextView bookSearchInfo;
        @BindView(R.id.btn_collect) Button collect;
        public BookShelfListViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
