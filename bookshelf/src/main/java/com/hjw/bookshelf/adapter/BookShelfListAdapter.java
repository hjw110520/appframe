package com.hjw.bookshelf.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hjw.bookshelf.PathConfig;
import com.hjw.bookshelf.R;
import com.hjw.bookshelf.model.BookSearchInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.bookshelf_adapter_bookshelflist, parent, false);
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
        @BindView(R.id.tv_book_name) TextView bookNameTV;
        @BindView(R.id.tv_book_author) TextView bookAuthorTV;
        @BindView(R.id.tv_book_completeStatus) TextView bookCompleteStatusTV;
        @BindView(R.id.tv_book_wordsNumber) TextView bookWordsNumberTV;
        @BindView(R.id.iv_book_img) ImageView bookImgIV;
        @BindView(R.id.rootView) LinearLayout rootView;
        public BookShelfListViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
