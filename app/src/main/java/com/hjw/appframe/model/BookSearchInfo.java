package com.hjw.appframe.model;

import com.hjw.base.utils.Md5Util;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class BookSearchInfo {
    public String bookIdentity;//书籍标识
    public String bookName;//书籍名称
    public String author;//作者
    public String wordsNumber;//字数
    public String lastUpdate;//最后更新章节
    public String completeStatus;//完成状态
    public String detailsUrl;//书籍详情页面url
    public String indexUrl;//目录页面url
    public String bookCoverUrl;//封面url
    public void initBookIdentity(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.bookName);
        sb.append(this.author);
        this.bookIdentity = Md5Util.makeMd5Sum(sb.toString().getBytes());
    }

    public String toSimpleInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append(bookName);
        sb.append("\t\t\t");
        sb.append(author);
        sb.append("\t\t\t");
        sb.append(wordsNumber);
        sb.append("\t\t\t");
        sb.append(completeStatus);
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("bookName :");
        sb.append(bookName);
        sb.append("\n");
        sb.append("author :");
        sb.append(author);
        sb.append("\n");
        sb.append("wordsNumber :");
        sb.append(wordsNumber);
        sb.append("\n");
        sb.append("lastUpdate :");
        sb.append(lastUpdate);
        sb.append("\n");
        sb.append("completeStatus :");
        sb.append(completeStatus);
        sb.append("\n");
        sb.append("indexUrl :");
        sb.append(indexUrl);
        sb.append("\n");
        sb.append("detailsUrl :");
        sb.append(detailsUrl);
        sb.append("\n");
        sb.append("bookCoverUrl :");
        sb.append(bookCoverUrl);
        sb.append("\n");
        return sb.toString();
    }
}
