package com.hjw.appframe.model;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class ChapterDetail {
    public String nextChapterUrl;
    public String previousChapterUrl;
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
