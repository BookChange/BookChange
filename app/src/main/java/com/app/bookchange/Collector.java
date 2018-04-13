package com.app.bookchange;

/**
 * Created by Administrator on 2017/9/22.
 */

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class Collector {                  //管理activity类

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {   //添加活动到列表
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {   //移除列表的活动
        activities.remove(activity);
    }

    public static void finishAll() {                       //清理活动
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}
