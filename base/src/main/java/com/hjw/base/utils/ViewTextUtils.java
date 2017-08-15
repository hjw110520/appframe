package com.hjw.base.utils;

import android.text.TextUtils;
import android.widget.TextView;

/**
 * Created by jinwei on 2015/8/13.
 */
public class ViewTextUtils {

    /**
     * @param view
     * @param text
     * @param format
     * @param defaultValue
     */
    public static void setText(TextView view, CharSequence text, String format, String defaultValue) {
        if (view == null) {
            SimpleLog.error("setText view is null");
            return;
        }

        if (null == text || StringUtils.isEmpty(text.toString())) {
            if (StringUtils.isEmpty(defaultValue)) {
                view.setText("");
                return;
            } else {
                text = defaultValue;
            }
        }

        view.setVisibility(TextView.VISIBLE);
        if (StringUtils.isEmpty(format)) {
            view.setText(text);
        } else {
            view.setText(String.format(format, text.toString()));
        }
    }

    public static void setText(TextView view, CharSequence text, String format) {
        setText(view, text, format, null);
    }

    public static void setText(TextView view, Integer text, String format) {
        setText(view, String.valueOf(text), format);
    }

    public static void setText(TextView view, Double text, String format) {
        setText(view, String.valueOf(text), format);
    }

    public static void setText(TextView view, CharSequence text) {
        setText(view, text, null);
    }
    public static void setText(TextView view, Integer text) {
        setText(view, String.valueOf(text), null);
    }


    public static void appendText(TextView view, CharSequence text, String format) {
        if (view == null) {
            SimpleLog.error("setText view is null");
            return;
        }

        if (null == text || StringUtils.isEmpty(text.toString())) {
            view.append("");
        }

        if (StringUtils.isEmpty(format)) {
            view.append(text);
        } else {
            view.append(String.format(format, text.toString()));
        }
    }

    public static void appendText(TextView view, Integer text, String format) {
        appendText(view, String.valueOf(text), format);
    }

    public static void appendText(TextView view, Double text, String format) {
        appendText(view, String.valueOf(text), format);
    }

    public static void appendText(TextView view, CharSequence text) {
        appendText(view, text, null);
    }

    public static void appendText(TextView view, Integer text) {
        appendText(view, String.valueOf(text), null);
    }

    /**
     * 判断输入框是否为空
     *
     * @param view
     */
    public static boolean isEmpty(TextView view) {
        if (view == null) {
            SimpleLog.error("view is null");
            return true;
        }
        String text = view.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            return true;
        }
        return false;
    }

    /**
     * 获取view的text trim 非空
     *
     * @param view
     * @return
     */
    public static String getText(TextView view) {
        if (view == null) {
            SimpleLog.error("view is null");
            return "";
        }
        String text = view.getText().toString().trim().replaceAll(" ", "");
        if (TextUtils.isEmpty(text)) {
            return "";
        }
        return text;
    }

    public static int getIntText(TextView view, int def) {
        if (view == null) {
            SimpleLog.error("view is null");
            return def;
        }
        String text = view.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            return def;
        }
        try {
            return Integer.parseInt(text);
        } catch (Exception e) {
            SimpleLog.error(text + " is not a int value");
            return def;
        }
    }

    public static final String MONEYFORMAT_DEFAULT = "¥%s";

    public static void setMoneyText(TextView view, CharSequence text) {
        setText(view, text, MONEYFORMAT_DEFAULT, "0");
    }

    /**
     * 检测是否有emoji表情
     *
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }
}
