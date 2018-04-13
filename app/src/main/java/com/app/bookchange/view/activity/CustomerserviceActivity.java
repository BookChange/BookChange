package com.app.bookchange.view.activity;

import android.os.Bundle;
import android.view.View;

import com.app.bookchange.R;
import com.app.bookchange.common.Utils;
import com.app.bookchange.view.BaseActivity;

public class CustomerserviceActivity extends BaseActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.customer_service);
        super.onCreate(savedInstanceState);
        setOnListner();
    }

    private void setOnListner() {
        findViewById(R.id.back_button).setOnClickListener(this);
        findViewById(R.id.online_customer).setOnClickListener(this);
        findViewById(R.id.phone_customer).setOnClickListener(this);
        findViewById(R.id.report_phone).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                Utils.finish(CustomerserviceActivity.this);
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
