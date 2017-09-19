package com.cd.bookchange.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.cd.bookchange.App;
import com.cd.bookchange.common.Utils;

import org.apache.http.message.BasicNameValuePair;

public abstract class BaseActivity extends Activity{
    protected Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        App.getInstance2().addActivity(this);
        initControl();
        initView();
        initData();
        setListener();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Utils.finish(this);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 打开 Activity
     *
     * @param activity
     * @param cls
     * @param name
     */
    public void start_Activity(Activity activity, Class<?> cls,
                               BasicNameValuePair... name) {
        Utils.start_Activity(activity, cls, name);
    }

    /**
     * 关闭 Activity
     *
     * @param activity
     */
    public void finish(Activity activity) {
        Utils.finish(activity);
    }

    /**
     * 绑定控件id
     */
    protected abstract void initControl();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置监听
     */
    protected abstract void setListener();
}
