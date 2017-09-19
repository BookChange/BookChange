package com.cd.bookchange.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cd.bookchange.R;
import com.cd.bookchange.common.Utils;
import com.cd.bookchange.view.BaseActivity;

public class BookfriendActivity extends BaseActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.bookfriend_list);
        super.onCreate(savedInstanceState);
        setOnListner();
    }

    private void setOnListner() {
        findViewById(R.id.back_button).setOnClickListener(this);
        findViewById(R.id.friend_list_myImg).setOnClickListener(this);
        findViewById(R.id.friend_list_context).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                Utils.finish(BookfriendActivity.this);
                break;
            case R.id.friend_list_myImg:
                startActivity(new Intent(BookfriendActivity.this, ContactfriendActivity.class));
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
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
