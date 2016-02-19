package com.zhenmei.testsplashscreen.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences工具类
 */
public class SPUtils {

    /**
     * SharedPreferences文件名
     */
    public static final String FILE_NAME = "bangbang";

    /**
     * 从SharedPreferences中获得布尔值
     *
     * @param mContext 上下文
     * @param key      键
     * @param defValue 默认值
     * @return
     */
    public static boolean getBoolean(Context mContext, String key, boolean defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    /**
     * 向SharedPreferences中设置布尔值
     *
     * @param mContext 上下文
     * @param key      键
     * @param value    值
     */
    public static void setBoolean(Context mContext, String key, boolean value) {
        SharedPreferences sp = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }
}
