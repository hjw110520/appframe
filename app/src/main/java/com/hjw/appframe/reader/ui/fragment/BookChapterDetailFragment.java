package com.hjw.appframe.reader.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjw.appframe.R;
import com.hjw.appframe.manager.ApiManager;
import com.hjw.appframe.reader.ui.common.CommonActions;
import com.hjw.base.LocalBroadcasts;
import com.hjw.base.utils.ScreenUtils;
import com.hjw.bookbase.model.ChapterDetail;
import com.hjw.commonui.BaseFragment;
import com.hjw.network.callback.SimpleCallBack;

/**
 * Created by hjw on 2018/5/20.
 */

public class BookChapterDetailFragment extends BaseFragment{

    TextView chapterDetailTV;
    View bottomRoot;
    View midRoot;
    ScrollView mScrollView;
    private ChapterDetail chapterDetail;
    @Override
    protected int provideLayoutResId() {
        return R.layout.app_fragment_bookchapterdetail;
    }

    @Override
    protected void initView(View rootView) {
        chapterDetailTV = mRootView.findViewById(R.id.tv_chapter_detail);
        bottomRoot = mRootView.findViewById(R.id.rv_bottomroot);
        midRoot = mRootView.findViewById(R.id.rv_midroot);
        mScrollView = mRootView.findViewById(R.id.mScrollView);
    }

    @Override
    protected void initData(View rootView, Bundle savedInstanceState) {
        String chapterUrl = getActivity().getIntent().getStringExtra("chapterUrl");
        refreshData(chapterUrl);
    }

    @Override
    protected void initListener() {

    }

    public void onChapterDetailTVClick(View view){
        toggleBottomAndRootVisible();
    }

    private void toggleBottomAndRootVisible(){
        if(bottomRoot.getVisibility() == View.VISIBLE){
            bottomRoot.setVisibility(View.GONE);
            midRoot.setVisibility(View.GONE);
        }else {
            bottomRoot.setVisibility(View.VISIBLE);
            midRoot.setVisibility(View.VISIBLE);
        }
    }

    public void onNextChapterClick(View view){
        refreshData(chapterDetail.nextChapterUrl);
        toggleBottomAndRootVisible();
    }

    public void onLastChapterClick(View view){
        refreshData(chapterDetail.previousChapterUrl);
        toggleBottomAndRootVisible();
    }

    public void onChapterIndexClick(View view){
        LocalBroadcasts.sendLocalBroadcast(CommonActions.BOOK_INDEX_OPEN);
        toggleBottomAndRootVisible();
    }

    //@OnClick(R.id.btn_addTextSize)
    public void onAddTextSizeClick(View view){
        addTextSize();
        toggleBottomAndRootVisible();
    }

    //@OnClick(R.id.btn_reduceTextSize)
    public void onReduceTextSizeClick(View view){
        reduceTextSize();
        toggleBottomAndRootVisible();
    }

    private void refreshData(String chapterUrl){
        String indexUrl = getActivity().getIntent().getStringExtra("indexUrl");
        ApiManager apiManager = new ApiManager();
        apiManager.getChapterDetail(indexUrl,chapterUrl,new SimpleCallBack(){
            @Override
            public void onSuccess(Object result) {
                chapterDetail = (ChapterDetail)result;
                chapterDetailTV.setText(Html.fromHtml(chapterDetail.content));
                mScrollView.scrollTo(0,0);
            }
        });
    }

    private void addTextSize(){
        float currentSize = chapterDetailTV.getTextSize();
        currentSize += ScreenUtils.sp2px(fragmentActivity,8f);
        chapterDetailTV.setTextSize(ScreenUtils.px2sp(fragmentActivity,currentSize));
    }

    private void reduceTextSize(){
        float currentSize = chapterDetailTV.getTextSize();
        currentSize -= ScreenUtils.sp2px(fragmentActivity,8f);
        chapterDetailTV.setTextSize(ScreenUtils.px2sp(fragmentActivity,currentSize));
    }

    @Override
    protected String[] provideBroadcastActions() {
        return new String []{CommonActions.BOOK_INDEX_SELECTED};
    }

    @Override
    protected void onReceiveBroadcast(String action, Intent intent) {
        if(null != action && action.equals(CommonActions.BOOK_INDEX_SELECTED)){
            refreshData(intent.getStringExtra("chapterUrl"));
        }
    }
}
