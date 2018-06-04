package com.hjw.appframe.reader.ui.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjw.appframe.PathConfig;
import com.hjw.appframe.R;
import com.hjw.appframe.reader.ui.fragment.BookshelfFragment;
import com.hjw.appframe.reader.ui.fragment.BookstoreFragment;
import com.hjw.commonui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/5/14 0014.
 */

@Route(path = PathConfig.PATH_HOME)
public class HomeActivity extends BaseActivity{
    private Fragment bookShelfFragment,bookStoreFragment;
    private Fragment currentFragment;
    private List<TextView> tabs;

    @BindView(R.id.bookShelfTab)  TextView bookShelfTab;
    @BindView(R.id.bookStoreTab)  TextView bookStoreTab;

    @Override
    protected int provideLayoutResId() {
        return R.layout.app_activity_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this,mRootView);
        bookShelfFragment = new BookshelfFragment();
        bookStoreFragment = new BookstoreFragment();
        tabs = new ArrayList<TextView>(2);
        tabs.add(bookShelfTab);
        tabs.add(bookStoreTab);
        ActionBar actionBar = getActionBar();
        /*if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); //Enable自定义的View
            actionBar.setCustomView(R.layout.actionbar_custom);//设置自定义的布局：actionbar_custom
        }*/

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        switchFragments(bookShelfFragment,0);
    }

    @OnClick(R.id.bookShelfTab)
    protected void onBookShelfTabClick(){
        switchFragments(bookShelfFragment,0);
    }

    @OnClick(R.id.bookStoreTab)
    protected void onBookStoreTabClick(){
        switchFragments(bookStoreFragment,1);
    }

    private void switchFragments(Fragment fragment, int postion) {
        if (null == fragment) {
            return;
        }
        if (fragment == currentFragment) {
            return;
        }
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        try {
            if (fragment.isAdded()) {
                beginTransaction.remove(fragment);
                fragment = getNewFragment(postion);
            }
        } catch (Exception e) {
        }
        beginTransaction.replace(R.id.home_page_container, fragment, "content").commitAllowingStateLoss();
        getSupportFragmentManager().executePendingTransactions();
        currentFragment = fragment;

    }

    private Fragment getNewFragment(int postion) {
        switch (postion) {
            case 1:
                bookStoreFragment = new BookstoreFragment();
                return bookStoreFragment;
            default:
                bookShelfFragment = new BookshelfFragment();
                return bookShelfFragment;
        }
    }

}
