package com.hjw.appframe.reader.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjw.appframe.R;
import com.hjw.appframe.reader.ui.common.CommonActions;
import com.hjw.appframe.reader.ui.fragment.BookChapterDetailFragment;
import com.hjw.appframe.reader.ui.fragment.BookIndexFragment;
import com.hjw.bookbase.PathConfig;
import com.hjw.commonui.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/22 0022.
 */

@Route(path = PathConfig.PATH_BOOK_CHAPTER_DETAIL)
public class BookChapterDetailActivity extends BaseActivity{
    @BindView(R.id.mDrawerLayout) DrawerLayout mDrawerLayout;
    @Override
    protected int provideLayoutResId() {
        return R.layout.app_activity_book_chapter_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        BookChapterDetailFragment fragment = new BookChapterDetailFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_fragment_container, fragment, "content")
                .commit();

        BookIndexFragment indexFragment = new BookIndexFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.left_fragment_container, indexFragment, "leftcontent")
                .commit();
        ButterKnife.bind(this,mRootView);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected String[] provideBroadcastActions() {
        return new String []{CommonActions.BOOK_INDEX_SELECTED,CommonActions.BOOK_INDEX_OPEN};
    }

    @Override
    protected void onReceiveBroadcast(String action, Intent intent) {
        if(null == action){
            return;
        }

        if(action.equals(CommonActions.BOOK_INDEX_SELECTED)){
            mDrawerLayout.closeDrawer(Gravity.LEFT);
            return;
        }

        if(action.equals(CommonActions.BOOK_INDEX_OPEN)){
            mDrawerLayout.openDrawer(Gravity.LEFT);
            return;
        }

    }
}
