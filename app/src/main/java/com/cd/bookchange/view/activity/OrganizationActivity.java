package com.cd.bookchange.view.activity;

import android.os.Bundle;
import android.view.View;

import com.cd.bookchange.R;
import com.cd.bookchange.common.Utils;
import com.cd.bookchange.view.BaseActivity;


public class OrganizationActivity extends BaseActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.organize_activities);
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                Utils.finish(OrganizationActivity.this);
                break;
            default:
                break;

        }

    }

    @Override
    protected void initControl() {
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
        findViewById(R.id.back_button).setOnClickListener(this);
        findViewById(R.id.activities_time).setOnClickListener(this);
        findViewById(R.id.activitie_name).setOnClickListener(this);
        findViewById(R.id.copy_activitie_context).setOnClickListener(this);

    }
}
