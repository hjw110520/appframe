package com.hjw.appframe.reader.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hjw.appframe.PathConfig;
import com.hjw.appframe.R;
import com.hjw.appframe.reader.ui.common.CommonActions;
import com.hjw.appframe.reader.ui.fragment.BookIndexFragment;
import com.hjw.commonui.BaseActivity;

/**
 * Created by Administrator on 2018/5/22 0022.
 */

@Route(path = PathConfig.PATH_BOOK_INDEX)
public class BookIndexActivity extends BaseActivity{

    @Override
    protected int provideLayoutResId() {
        return R.layout.activity_base_fragment_container;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        BookIndexFragment fragment = new BookIndexFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.sdk_activity_fragment_container, fragment, "content")
                .commit();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected String[] provideBroadcastActions() {
        return new String []{CommonActions.BOOK_INDEX_SELECTED};
    }

    @Override
    protected void onReceiveBroadcast(String action, Intent intent) {
        if(null != action && action.equals(CommonActions.BOOK_INDEX_SELECTED)){
            String chapterUrl = intent.getStringExtra("chapterUrl");
            String indexUrl = intent.getStringExtra("indexUrl");
            ARouter.getInstance().build(PathConfig.PATH_BOOK_CHAPTER_DETAIL)
                    .withString("chapterUrl",chapterUrl)
                    .withString("indexUrl",indexUrl)
                    .navigation();
            finish();
        }
    }
}
