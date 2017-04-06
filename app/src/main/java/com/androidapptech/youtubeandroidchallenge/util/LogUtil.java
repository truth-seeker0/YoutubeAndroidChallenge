package com.androidapptech.youtubeandroidchallenge.util;

import android.util.Log;

import java.text.MessageFormat;

/**
 * Created by Benjamin on 11/14/2016.
 */

public class LogUtil {

    //TODO: use your own TAG here
    private static  String TAG = "MY_LOG";
    private static boolean sShowLogs = true;


    /**
     * utility method to place in methods just for the sake of seeing that they were called
     */
    public static void logMethodCalled() {
        if (sShowLogs) {
            doLog(Log.DEBUG, "called");
        }
    }

    /**
     * можно задать видимость логов.
     * @param pShowLogs
     */
    public static void showLogs(boolean pShowLogs) {
        sShowLogs = pShowLogs;
    }

    /**
     * установить свой таг для логов
     * @param pTag
     */
    public static void setTag(String pTag) {
        TAG = pTag;
    }

    public static void v(String text) {
        if (sShowLogs) {
            doLog(Log.VERBOSE, text);
        }
    }

    public static void d(String text) {
        if (sShowLogs) {
            doLog(Log.DEBUG, text);
        }
    }

    public static void i(String text) {
        if (sShowLogs) {
            doLog(Log.INFO, text);
        }
    }

    public static void w(String text) {
        if (sShowLogs) {
            doLog(Log.WARN, text);
        }
    }

    public static void e(String text) {
        if (sShowLogs) {
            doLog(Log.ERROR, text);
        }
    }

    public static void a(String msg) {
        if (sShowLogs) {
            doLog(Log.ASSERT, msg);
        }
    }

    /*********************************
     * private static methods
     *********************************/

    private static void doLog(int logLevel, String logText) {

        StackTraceElement[] stackTrace = Thread.currentThread()
                .getStackTrace();

        //take stackTrace element at index 4 because:
        //0: VMStack.getThreadStackTrace(Native Method)
        //1: java.lang.Thread.getStackTrace
        //2: LogUtil -> doLog method (this method)
        //3: LogUtil -> log method
        //4: this is the calling method!
        if (stackTrace != null && stackTrace.length > 4) {

            StackTraceElement element = stackTrace[4];

            String fullClassName = element.getClassName();
            String simpleClassName = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);

            //add class and method data to logText
            logText = MessageFormat.format("Thread:{0} | {1} , {2}() | {3}", Thread.currentThread()
                    .getId(), simpleClassName, element.getMethodName(), logText);
        }

        Log.println(logLevel, TAG, logText);
    }
}
