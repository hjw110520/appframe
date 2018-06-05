package com.hjw.bookshelf;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjw.bookshelf.fragment.BookshelfFragment;
import com.hjw.commonui.BaseActivity;


public class MainActivity extends BaseActivity {

    @Override
    protected int provideLayoutResId() {
        return R.layout.bookshelf_activity_base_fragment_container;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        BookshelfFragment fragment = new BookshelfFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.sdk_activity_fragment_container, fragment, "content")
                .commit();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
