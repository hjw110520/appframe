package com.hjw.appframe.htmlpars;

import com.hjw.appframe.model.BookIndex;
import com.hjw.appframe.model.BookSearchInfo;
import com.hjw.appframe.model.ChapterDetail;
import com.hjw.base.utils.LogUtils;
import com.hjw.base.utils.Md5Util;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class DingDianPars implements IHtmlPars{
    public List<BookSearchInfo> parsBookSearchInfo(Document htmlDoc) {
        Element element = htmlDoc.getElementById("content");
        //LogUtils.debug(element.toString());
        Elements elements = element.select("tr");
        List<BookSearchInfo> list = new ArrayList<BookSearchInfo>(elements.size()-1);
        for (int i=0;i<elements.size();i++){
            if(i>0){
                Element tr =elements.get(i);
                BookSearchInfo book = new BookSearchInfo();
                Elements bookElements = tr.select("td");
                book.detailsUrl = bookElements.get(0).select("a").attr("href");
                book.bookName = bookElements.get(0).select("a").text();
                book.indexUrl = bookElements.get(1).select("a").attr("href");
                book.lastUpdate = bookElements.get(1).select("a").text();
                book.author = bookElements.get(2).text();
                book.wordsNumber = bookElements.get(3).text();
                book.lastUpdate = bookElements.get(4).text();
                book.completeStatus = bookElements.get(5).text();
                book.initBookIdentity();
                LogUtils.debug(book.toString());
                list.add(book);
            }

        }
        return list;
    }

    public List<BookIndex> parsBookIndex(Document htmlDoc) {
        Elements table =  htmlDoc.select("table");
        Elements indexes = table.select("td.L");
        List<BookIndex> list = new ArrayList<BookIndex>();
        for (int i=0;i<indexes.size();i++){
            Element index = indexes.get(i);
            BookIndex bookIndex = new BookIndex();
            bookIndex.chapterUrl  = index.select("a").attr("href");
            bookIndex.chapterTitle  = index.text();
            LogUtils.debug(bookIndex.toString());
            list.add(bookIndex);
        }
        return list;
    }

    public ChapterDetail parsChapterDetail(Document htmlDoc){
        Elements indexes = htmlDoc.select("div.bdsub").select("h3").select("a");
        ChapterDetail chapterDetail = new ChapterDetail();
        chapterDetail.previousChapterUrl = indexes.get(0).attr("href");
        chapterDetail.nextChapterUrl = indexes.get(2).attr("href");
        chapterDetail.content = htmlDoc.select("#contents").toString();
        //chapterDetail.content = chapterDetail.content.replaceAll("\\<br\\>","\n");
        //chapterDetail.content = chapterDetail.content.replaceAll("&nbsp;","  ");
        LogUtils.debug(chapterDetail.toString());
        return chapterDetail;
    }
}
