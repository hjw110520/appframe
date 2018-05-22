package com.hjw.appframe.reader.ui.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjw.appframe.PathConfig;
import com.hjw.appframe.R;
import com.hjw.appframe.reader.ui.fragment.BookChapterDetailFragment;
import com.hjw.appframe.reader.ui.fragment.BookIndexFragment;
import com.hjw.commonui.BaseActivity;

/**
 * Created by Administrator on 2018/5/22 0022.
 */

@Route(path = PathConfig.PATH_BOOK_CHAPTER_DETAIL)
public class BookChapterDetailActivity extends BaseActivity{

    @Override
    protected int provideLayoutResId() {
        return R.layout.activity_base_fragment_container;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        BookChapterDetailFragment fragment = new BookChapterDetailFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.sdk_activity_fragment_container, fragment, "content")
                .commit();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
