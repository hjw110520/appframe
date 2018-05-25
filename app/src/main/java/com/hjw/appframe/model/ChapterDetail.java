package com.hjw.appframe.model;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class ChapterDetail {
    public String indexUrl;//目录url
    public String chapterUrl;//本章url
    public String nextChapterUrl;//下一章url
    public String previousChapterUrl;//上一章url
    public String content;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("nextChapterUrl :");
        sb.append(nextChapterUrl);
        sb.append("\n");
        sb.append("previousChapterUrl :");
        sb.append(previousChapterUrl);
        sb.append("\n");
        sb.append("content :");
        sb.append(content);
        sb.append("\n");
        return sb.toString();
    }
}
