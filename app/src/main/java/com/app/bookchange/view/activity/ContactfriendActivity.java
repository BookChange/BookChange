package com.app.bookchange.view.activity;

import android.os.Bundle;
import android.view.View;

import com.app.bookchange.R;
import com.app.bookchange.common.Utils;
import com.app.bookchange.view.BaseActivity;


public class ContactfriendActivity extends BaseActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.contact_friend);
        super.onCreate(savedInstanceState);
        setOnListner();
    }

    private void setOnListner() {
        findViewById(R.id.back_button).setOnClickListener(this);
        findViewById(R.id.friend_photo).setOnClickListener(this);
        findViewById(R.id.friend_nickname).setOnClickListener(this);
        findViewById(R.id.friend_introduction).setOnClickListener(this);
        findViewById(R.id.friend_books).setOnClickListener(this);
        findViewById(R.id.send_message).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                Utils.finish(ContactfriendActivity.this);
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

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }
}
