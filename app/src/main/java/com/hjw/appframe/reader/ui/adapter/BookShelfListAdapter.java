package com.hjw.appframe.reader.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hjw.appframe.PathConfig;
import com.hjw.appframe.R;
import com.hjw.appframe.model.BookSearchInfo;
import com.squareup.picasso.Picasso;

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
        //https://www.x23us.com/files/article/image/0/493/493s.jpg
        final BookSearchInfo bookSearchInfo = dataList.get(position);
        holder.bookNameTV.setText(bookSearchInfo.bookName);
        holder.bookAuthorTV.setText(bookSearchInfo.author);
        holder.bookCompleteStatusTV.setText(bookSearchInfo.completeStatus);
        holder.bookWordsNumberTV.setText(bookSearchInfo.wordsNumber);
        Picasso.with(mContext).load(bookSearchInfo.bookCoverUrl).into(holder.bookImgIV);
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(PathConfig.PATH_BOOK_INDEX)
                        .withString("indexUrl",bookSearchInfo.indexUrl)
                        .navigation();
            }
        });
    }

    class BookShelfListViewHolder extends RecyclerView.ViewHolder{
        private TextView bookNameTV;
        private TextView bookAuthorTV;
        private TextView bookCompleteStatusTV;
        private TextView bookWordsNumberTV;
        private ImageView bookImgIV;
        private LinearLayout rootView;
        public BookShelfListViewHolder(View itemView){
            super(itemView);
            rootView = itemView.findViewById(R.id.rootView);
            bookNameTV = itemView.findViewById(R.id.tv_book_name);
            bookAuthorTV = itemView.findViewById(R.id.tv_book_author);
            bookCompleteStatusTV = itemView.findViewById(R.id.tv_book_completeStatus);
            bookWordsNumberTV = itemView.findViewById(R.id.tv_book_wordsNumber);
            bookImgIV = itemView.findViewById(R.id.iv_book_img);
        }
    }
}
