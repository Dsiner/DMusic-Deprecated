/**
 * GGLog.java
 *
 * @date 2015-04-02
 */
package com.d.dmusic.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Log工具类，可以打印Log日志类名，方法名，行号
 */
public class ULog {

    private static final String LOG_TAG = "DMusic";
    private static ULog glog;

    public synchronized static ULog getInstance() {
        if (glog == null)
            glog = new ULog();
        return glog;
    }

    private ULog() {
    }

    public static void v(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        if (DEBUG.DEVELOP_MODE) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            final int i = 1;
            final StackTraceElement ste = stack[i];
            Log.println(Log.VERBOSE, LOG_TAG, String.format("[%s][%s][%s]%s", ste.getFileName(), ste.getMethodName(), ste.getLineNumber(), message));
        }
    }

    public static void d(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        if (DEBUG.DEVELOP_MODE) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            final int i = 1;
            final StackTraceElement ste = stack[i];
            Log.println(Log.DEBUG, LOG_TAG, String.format("[%s][%s][%s]%s", ste.getFileName(), ste.getMethodName(), ste.getLineNumber(), message));
        }
    }


    public static void i(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        if (DEBUG.DEVELOP_MODE) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            final int i = 1;
            final StackTraceElement ste = stack[i];
            Log.println(Log.INFO, LOG_TAG, String.format("[%s][%s][%s]%s", ste.getFileName(), ste.getMethodName(), ste.getLineNumber(), message));
        }
    }


    public static void w(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        if (DEBUG.DEVELOP_MODE) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            final int i = 1;
            final StackTraceElement ste = stack[i];
            Log.println(Log.WARN, LOG_TAG, String.format("[%s][%s][%s]%s", ste.getFileName(), ste.getMethodName(), ste.getLineNumber(), message));
        }
    }

    public static void e(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        if (DEBUG.DEVELOP_MODE) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            final int i = 1;
            final StackTraceElement ste = stack[i];
            Log.println(Log.ERROR, LOG_TAG, String.format("[%s][%s][%s]%s", ste.getFileName(), ste.getMethodName(), ste.getLineNumber(), message));
        }
    }


}
