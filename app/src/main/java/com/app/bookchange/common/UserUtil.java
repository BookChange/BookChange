package com.app.bookchange.common;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.app.bookchange.Constants;
import com.app.bookchange.bean.User;


public class UserUtil {
    /**
     * 获取用户信息
     *
     * @param context
     * @return
     */
    public static User getUserModel(Context context) {
        User user = null;
        String jsondata = Utils.getValue(context, Constants.UserInfo);
        if (!TextUtils.isEmpty(jsondata))
            user = JSON.parseObject(jsondata, User.class);
        return user;
    }

    /**
     * 获取用户ID
     *
     * @param context
     * @return
     */
    public static String getUserID(Context context) {
        User user = getUserModel(context);
        if (user != null)
            return user.getTelephone();
        else
            return "";
    }

    /**
     * 获取用户名字
     *
     * @param context
     * @return
     */
    public static String getUserName(Context context) {
        User user = getUserModel(context);
        if (user != null)
            return user.getUserName();
        else
            return "";
    }

    /**
     * 获取用户
     *
     * @param context
     * @return
     */
    public static String getUserPwd(Context context) {
        User user = getUserModel(context);
        if (user != null)
            return user.getPassword();
        else
            return "";
    }

}
