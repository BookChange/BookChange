package com.app.bookchange.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.app.bookchange.R;

import org.apache.http.message.BasicNameValuePair;

public class Utils {
    public static void showLongToast(Context context, String pMsg) {
        Toast.makeText(context, pMsg, Toast.LENGTH_LONG).show();
    }

    /**
     * 打开Activity
     *
     * @param activity
     * @param cls
     * @param name
     */
    public static void start_Activity(Activity activity, Class<?> cls,
                                      BasicNameValuePair... name) {
        Intent intent = new Intent();
        intent.setClass(activity, cls);
        if (name != null)
            for (int i = 0; i < name.length; i++) {
                intent.putExtra(name[i].getName(), name[i].getValue());
            }
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.push_left_in,
                R.anim.push_left_out);
    }

    /**
     * 关闭 Activity
     *
     * @param activity
     */
    public static void finish(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.push_right_in,
                R.anim.push_right_out);
    }

    private static final SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * 获取SharedPreference 值
     *
     * @param context
     * @param key
     * @return
     */
    public static final String getValue(Context context, String key) {
        return getSharedPreference(context).getString(key, "");
    }

    public static final long getLongValue(Context context, String key,
                                          long default_data) {
        return getSharedPreference(context).getLong(key, default_data);
    }


    /**
     * 设置SharedPreference 值
     *
     * @param context
     * @param key
     * @return
     */
    public static final boolean putValue(Context context, String key,
                                         String value) {
        value = value == null ? "" : value;
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(key, value);
        boolean result = editor.commit();
        if (!result) {
            return false;
        }
        return true;
    }

    /**
     * 设置SharedPreference 值
     *
     * @param context
     * @param key
     * @param value
     */
    public static final boolean putIntValue(Context context, String key,
                                            int value) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putInt(key, value);
        boolean result = editor.commit();
        if (!result) {
            return false;
        }
        return true;
    }



}
