package com.hjw.appframe.manager;

import com.hjw.appframe.api.ApiConfig;
import com.hjw.appframe.htmlpars.ParsHelper;
import com.hjw.appframe.htmlpars.ParsType;
import com.hjw.bookbase.model.BookIndex;
import com.hjw.bookbase.model.BookSearchInfo;
import com.hjw.bookbase.model.ChapterDetail;
import com.hjw.bookbase.model.SearchParam;
import com.hjw.network.api.RequestUtils;
import com.hjw.network.callback.HtmlCallBack;
import com.hjw.network.callback.SimpleCallBack;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class ApiManager {
    public static final String CONTENT_ID = "content";
    public void searchByKeyWord(String searchKey , final SimpleCallBack callBack){
        SearchParam searchParam = new SearchParam();
        searchParam.searchkey = searchKey;//"恶魔法则";//URLEncoder.encode("恶魔法则","gb2312");
                RequestUtils.getInstance().get(ApiConfig.API_SEARCH,searchParam,String.class,new HtmlCallBack(){
            @Override
            public void onSuccess(Document htmlDoc) {
                List<BookSearchInfo> list = ParsHelper.getHtmlPars(ParsType.DingDian).parsBookSearchInfo(htmlDoc);
                callBack.onSuccess(list);
            }
        });
    }

    /**
     * @param indexUrl
     *         @see BookSearchInfo#indexUrl
     * @param callBack
     */
    public void getBookIndex(String indexUrl,final SimpleCallBack callBack){
        //String indexUrl = indexUrl;//"https://www.x23us.com/html/67/67759/";
        RequestUtils.getInstance().get(indexUrl,null,String.class,new HtmlCallBack(){
            @Override
            public void onSuccess(Document htmlDoc) {
                List<BookIndex> indexList = ParsHelper.getHtmlPars(ParsType.DingDian).parsBookIndex(htmlDoc);
                callBack.onSuccess(indexList);
            }
        });
    }

    public void getChapterDetail(final String indexUrl,final String chapterUrl,final SimpleCallBack callBack){
        //String chapterUrl = "https://www.x23us.com/html/67/67759/29689526.html";
        RequestUtils.getInstance().get(indexUrl+chapterUrl,null,String.class,new HtmlCallBack(){
            @Override
            public void onSuccess(Document htmlDoc) {
                ChapterDetail chapterDetail = ParsHelper.getHtmlPars(ParsType.DingDian).parsChapterDetail(htmlDoc);
                chapterDetail.indexUrl = indexUrl;
                chapterDetail.chapterUrl = chapterUrl;
                callBack.onSuccess(chapterDetail);
            }
        });
    }
}
