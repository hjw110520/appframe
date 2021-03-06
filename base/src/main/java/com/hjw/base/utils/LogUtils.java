package com.hjw.base.utils;

import android.util.Log;

import java.text.SimpleDateFormat;

/**
 * 提供给实际应用的LOG日志类
 */
public class LogUtils {
    /**
     * 日志级别
     */
    public static int LOG_LEVEL = 1;
    /**
     * 异常栈位移
     */
    private static final int EXCEPTION_STACK_INDEX = 2;


    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * verbose级别的日志
     *
     * @param msg 打印内容
     * @see [类、类#方法、类#成员]
     */
    public static void verbose(String msg)
    {
        if (android.util.Log.VERBOSE >= LOG_LEVEL)
        {
            android.util.Log.v(getTag(), msg);
        }
    }

    /**
     * debug级别的日志
     *
     * @param msg 打印内容
     * @see [类、类#方法、类#成员]
     */
    public static void debug(String msg)
    {
        if (android.util.Log.DEBUG >= LOG_LEVEL)
        {
            android.util.Log.d(getTag(), msg);
        }
    }

    /**
     * info级别的日志
     *
     * @param msg 打印内容
     * @see [类、类#方法、类#成员]
     */
    public static void info(String msg)
    {
        if (android.util.Log.INFO >= LOG_LEVEL)
        {
            android.util.Log.i(getTag(), msg);
        }
    }

    /**
     * warn级别的日志
     *
     * @param msg 打印内容
     * @see [类、类#方法、类#成员]
     */
    public static void warn(String msg)
    {
        if (android.util.Log.WARN >= LOG_LEVEL)
        {
            android.util.Log.w(getTag(), msg);
        }
    }

    /**
     * error级别的日志
     *
     * @param msg 打印内容
     * @see [类、类#方法、类#成员]
     */
    public static void error(String msg)
    {
        if (android.util.Log.ERROR >= LOG_LEVEL)
        {
            String tag = getTag();
            android.util.Log.e(tag, msg);
        }
    }

    public static void error(String msg, Throwable tr)
    {
        if (android.util.Log.ERROR >= LOG_LEVEL)
        {
            String tag = getTag();
            android.util.Log.e(tag, msg, tr);
        }
    }

    /**
     * 获取日志的标签 格式：类名_方法名_行号 （需要权限：android.permission.GET_TASKS）
     *
     * @return tag
     * @see [类、类#方法、类#成员]
     */
    private static String getTag()
    {
        try
        {
            Exception exception = new LogException();
            if (exception.getStackTrace() == null || exception.getStackTrace().length <= EXCEPTION_STACK_INDEX)
            {
                return "***";
            }
            StackTraceElement element = exception.getStackTrace()[EXCEPTION_STACK_INDEX];

            String className = element.getClassName();

            int index = className.lastIndexOf(".");
            if (index > 0)
            {
                className = className.substring(index + 1);
            }

            return className + "_" + element.getMethodName() + "_" + element.getLineNumber();

        }
        catch (Throwable e)
        {
            e.printStackTrace();
            return "***";
        }
    }

    /**
     * 取日志标签用的的异常类，只是用于取得日志标签
     */
    private static class LogException extends Exception
    {
        /**
         * 注释内容
         */
        private static final long serialVersionUID = 1L;
    }
}
