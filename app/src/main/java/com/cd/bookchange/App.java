package com.cd.bookchange;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

public class App {

    // 运用list来保存们每一个activity
    private List<Activity> mList = new LinkedList<Activity>();
    private static App instance;

    /*  构造方法
        实例化一次
     */
    public synchronized static App getInstance2() {
        if (null == instance) {
            instance = new App();
        }
        return instance;
    }

    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    // 关闭每一个list内的activity
    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

}
