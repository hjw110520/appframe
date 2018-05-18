package com.hjw.appframe;

import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hjw.appframe.manager.ApiManager;
import com.hjw.appframe.model.BookSearchInfo;
import com.hjw.appframe.model.ChapterDetail;
import com.hjw.appframe.reader.ui.common.BookShelfHelper;
import com.hjw.commonui.BaseActivity;
import com.hjw.network.callback.SimpleCallBack;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = PathConfig.PATH_MAIN)
public class MainActivity extends BaseActivity {

    @BindView(R.id.testBtn) Button testBtn;
    @BindView(R.id.contentTv)TextView contentTv;
    @Override
    protected int provideLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this,mRootView);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @OnClick(R.id.testBtn)
    public void onTestBtnClick(){
        ApiManager apiManager = new ApiManager();
        /*apiManager.getChapterDetail(new SimpleCallBack(){
            @Override
            public void onSuccess(Object result) {
                ChapterDetail chapterDetail = (ChapterDetail)result;
                contentTv.setText(Html.fromHtml(chapterDetail.content));
            }
        });*/

        apiManager.searchByKeyWord(new SimpleCallBack(){
            @Override
            public void onSuccess(Object result) {
                List<BookSearchInfo> list = (List<BookSearchInfo>)result;
                BookShelfHelper bookShelfHelper = new BookShelfHelper();
                bookShelfHelper.addToBookShelf(MainActivity.this,list.get(0));
            }
        });
    }
}
