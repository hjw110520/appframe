package com.hjw.bookbase.model;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class BookInfo {
    public String title;//书籍名称
    public String author;//作者
    public String wordsNumber;//字数
    public String lastUpdate;//最后更新章节
    public String completeStatus;//完成状态
    public String detailsUrl;//书籍详情页面url
    public String indexUrl;//目录页面url

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("title :");
        sb.append(title);
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
        return sb.toString();
    }
}
