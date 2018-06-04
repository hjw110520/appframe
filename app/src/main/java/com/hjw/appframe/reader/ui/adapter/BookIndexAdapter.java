package com.hjw.appframe.reader.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hjw.appframe.R;
import com.hjw.appframe.common.adapter.BaseListAdapter;
import com.hjw.appframe.model.BookIndex;
import com.hjw.appframe.reader.ui.common.CommonActions;
import com.hjw.base.LocalBroadcasts;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/18 0018.
 */

public class BookIndexAdapter extends BaseListAdapter<BookIndexAdapter.BookIndexViewHolder,BookIndex>{
    private String indexUrl;
    public BookIndexAdapter(Context context,String indexUrl){
        super(context);
        this.indexUrl = indexUrl;
    }

    @Override
    public void onBindViewHolder(BookIndexViewHolder holder, int position) {
        final BookIndex bookIndex = getItem(position);
        holder.chapterTitleTv.setText(bookIndex.chapterTitle);
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommonActions.BOOK_INDEX_SELECTED);
                intent.putExtra("chapterUrl",bookIndex.chapterUrl);
                intent.putExtra("indexUrl",indexUrl);
                LocalBroadcasts.sendLocalBroadcast(intent);
            }
        });
    }

    @Override
    public BookIndexViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.app_adapter_bookindex, parent, false);
        return new BookIndexAdapter.BookIndexViewHolder(view);
    }


    class BookIndexViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_chapter_title) TextView chapterTitleTv;
        @BindView(R.id.rootView) View rootView;
        public BookIndexViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
