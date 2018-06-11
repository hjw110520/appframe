package com.hjw.appframe;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjw.appframe.manager.ApiManager;
import com.hjw.bookbase.PathConfig;
import com.hjw.commonui.BaseActivity;

@Route(path = PathConfig.PATH_MAIN)
public class MainActivity extends BaseActivity {

    Button testBtn;
    TextView contentTv;
    @Override
    protected int provideLayoutResId() {
        return R.layout.app_activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        testBtn = mRootView.findViewById(R.id.testBtn);
        contentTv = mRootView.findViewById(R.id.contentTv);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    public void onTestBtnClick(){
        ApiManager apiManager = new ApiManager();
        /*apiManager.getChapterDetail(new SimpleCallBack(){
            @Override
            public void onSuccess(Object result) {
                ChapterDetail chapterDetail = (ChapterDetail)result;
                contentTv.setText(Html.fromHtml(chapterDetail.content));
            }
        });*/

        /*apiManager.searchByKeyWord(new SimpleCallBack(){
            @Override
            public void onSuccess(Object result) {
                List<BookSearchInfo> list = (List<BookSearchInfo>)result;
                BookShelfHelper bookShelfHelper = new BookShelfHelper();
                bookShelfHelper.addToBookShelf(MainActivity.this,list.get(0));
            }
        });*/
        /*BookShelfHelper bookShelfHelper = new BookShelfHelper();
        LogUtils.debug(bookShelfHelper.getBookShelf(this).get(0).toString());*/
    }
}
