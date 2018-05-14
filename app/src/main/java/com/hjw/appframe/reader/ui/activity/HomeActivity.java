package com.hjw.appframe.reader.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

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

public class HomeActivity extends BaseActivity{
    private Fragment bookShelfFragment,bookStoreFragment;
    private Fragment currentFragment;
    private List<TextView> tabs;

    @BindView(R.id.bookShelfTab) private TextView bookShelfTab;
    @BindView(R.id.bookStoreTab) private TextView bookStoreTab;

    @Override
    protected int provideLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this,mRootView);
        bookShelfFragment = new BookshelfFragment();
        bookStoreFragment = new BookstoreFragment();
        tabs = new ArrayList<TextView>(2);
        tabs.add(bookShelfTab);
        tabs.add(bookStoreTab);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @OnClick(R.id.bookShelfTab)
    private void onBookShelfTabClick(){
        switchFragments(bookShelfFragment,0);
    }

    @OnClick(R.id.bookStoreTab)
    private void onBookStoreTabClick(){
        switchFragments(bookShelfFragment,1);
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