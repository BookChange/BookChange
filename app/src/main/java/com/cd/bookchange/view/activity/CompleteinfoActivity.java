package com.cd.bookchange.view.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.cd.bookchange.R;
import com.cd.bookchange.common.Utils;
import com.cd.bookchange.view.BaseActivity;

/**
 * Created by Administrator on 2017/9/15.
 */

public class CompleteinfoActivity extends BaseActivity implements OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.complete_info);
        super.onCreate(savedInstanceState);
        setOnListner();
    }

    private void setOnListner() {
        findViewById(R.id.back_button).setOnClickListener(this);
        findViewById(R.id.name_text).setOnClickListener(this);
        findViewById(R.id.sex_man_Button).setOnClickListener(this);
        findViewById(R.id.sex_woman_Button).setOnClickListener(this);
        findViewById(R.id.love_switch).setOnClickListener(this);
        findViewById(R.id.hobby_text).setOnClickListener(this);
        findViewById(R.id.select_age).setOnClickListener(this);
        findViewById(R.id.select_address).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                Utils.finish(CompleteinfoActivity.this);
                break;
            default:
                break;

        }

    }

    @Override
    protected void initControl() {
        // TODO Auto-generated method stub

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
