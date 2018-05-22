package com.hjw.appframe.reader.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hjw.appframe.PathConfig;
import com.hjw.appframe.R;
import com.hjw.appframe.common.adapter.BaseListAdapter;
import com.hjw.appframe.model.BookIndex;

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
                ARouter.getInstance().build(PathConfig.PATH_BOOK_CHAPTER_DETAIL)
                        .withString("chapterUrl",bookIndex.chapterUrl)
                        .withString("indexUrl",indexUrl)
                        .navigation();
            }
        });
    }

    @Override
    public BookIndexViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_bookindex, parent, false);
        return new BookIndexAdapter.BookIndexViewHolder(view);
    }


    class BookIndexViewHolder extends RecyclerView.ViewHolder{
        private TextView chapterTitleTv;
        private View rootView;
        public BookIndexViewHolder(View itemView){
            super(itemView);
            rootView = itemView.findViewById(R.id.rootView);
            chapterTitleTv = itemView.findViewById(R.id.tv_chapter_title);
        }
    }
}
