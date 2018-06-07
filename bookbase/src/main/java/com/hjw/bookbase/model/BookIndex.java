package com.hjw.bookbase.model;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class BookIndex {
    public String chapterTitle;//章节名称
    public String chapterUrl;//作者

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("chapterTitle :");
        sb.append(chapterTitle);
        sb.append("\n");
        sb.append("chapterUrl :");
        sb.append(chapterUrl);
        sb.append("\n");
        return sb.toString();
    }
}
