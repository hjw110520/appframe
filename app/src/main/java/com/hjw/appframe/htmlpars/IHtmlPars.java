package com.hjw.appframe.htmlpars;

import com.hjw.appframe.model.BookIndex;
import com.hjw.appframe.model.BookSearchInfo;
import com.hjw.appframe.model.ChapterDetail;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public interface IHtmlPars {
    public List<BookSearchInfo> parsBookSearchInfo(Document htmlDoc);
    public List<BookIndex> parsBookIndex(Document htmlDoc);
    public ChapterDetail parsChapterDetail(Document htmlDoc);
}
