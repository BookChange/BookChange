package com.app.bookchange.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.app.bookchange.R;
import com.app.bookchange.common.Utils;
import com.app.bookchange.view.BaseActivity;


//设置
public class SettingActivity extends BaseActivity implements OnClickListener{
    private TextView tv_cache;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.setting_view);
        super.onCreate(savedInstanceState);
        setOnListner();
    }

    private void setOnListner() {
        findViewById(R.id.back_button).setOnClickListener(this);
        findViewById(R.id.share_location).setOnClickListener(this);
        findViewById(R.id.nearby_visible).setOnClickListener(this);
        findViewById(R.id.account_security).setOnClickListener(this);
        findViewById(R.id.wipe_cache).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                Utils.finish(SettingActivity.this);
                break;
            case R.id.account_security:
                startActivity(new Intent(SettingActivity.this, AccountsecurityActivity.class));
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                break;
            case R.id.wipe_cache:
                tv_cache.setText("0KB");
                Utils.showLongToast(context,"缓存已清除...");
                break;
            default:
                break;
        }
    }

    @Override
    protected void initControl() {
        // TODO Auto-generated method stub
        tv_cache = (TextView) findViewById(R.id.activity_wipe_cache);

    }

    @Override
    protected void initView() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void initData() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void setListener() {
        // TODO Auto-generated method stub

    }
}
