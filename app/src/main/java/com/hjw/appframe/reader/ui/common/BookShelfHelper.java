package com.hjw.appframe.reader.ui.common;

import android.content.Context;

import com.google.gson.JsonParseException;
import com.hjw.appframe.model.BookSearchInfo;
import com.hjw.base.utils.JsonUtils;
import com.hjw.base.utils.PreferencesUtils;
import com.hjw.base.utils.StringUtils;
import com.hjw.base.utils.ToastUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/18 0018.
 */

public class BookShelfHelper {
    public static final String BOOK_SHELF_KEY = "BOOK_SHELF_KEY";
    public BookShelfHelper(){}

    public void addToBookShelf(Context context,BookSearchInfo bookSearchInfo){
        if(null == bookSearchInfo){
            return;
        }
        List<BookSearchInfo> currentShelf = getBookShelf(context);
        currentShelf.add(bookSearchInfo);
        PreferencesUtils.addConfigInfo(context,BOOK_SHELF_KEY,JsonUtils.parseObj2Json(currentShelf));
    }

    public List<BookSearchInfo> getBookShelf(Context context) {
        String currentShelfStr = PreferencesUtils.getStringByKey(context,BOOK_SHELF_KEY);
        if(!StringUtils.isEmpty(currentShelfStr,true)){
            try {
                return JsonUtils.parseJson2List(currentShelfStr,BookSearchInfo.class);
            }catch (JSONException e){
                e.printStackTrace();
                ToastUtils.showToast("获取数据错误"+e.getLocalizedMessage());
            }
        }
        return new ArrayList<BookSearchInfo>();
    }
}
