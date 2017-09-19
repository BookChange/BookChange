package com.cd.bookchange.view.activity;

import android.os.Bundle;
import android.view.View;

import com.cd.bookchange.R;
import com.cd.bookchange.common.Utils;
import com.cd.bookchange.view.BaseActivity;

public class CompletebooknameActivity extends BaseActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.complete_bookname);
        super.onCreate(savedInstanceState);
        setOnListner();
    }

    private void setOnListner() {
        findViewById(R.id.back_button).setOnClickListener(this);
        findViewById(R.id.book_count).setOnClickListener(this);
        findViewById(R.id.book_type).setOnClickListener(this);
        findViewById(R.id.bookname_edit1).setOnClickListener(this);
        findViewById(R.id.bookname_edit2).setOnClickListener(this);
        findViewById(R.id.bookname_edit3).setOnClickListener(this);
        findViewById(R.id.bookname_edit4).setOnClickListener(this);
        findViewById(R.id.bookname_edit5).setOnClickListener(this);
        findViewById(R.id.bookname_edit6).setOnClickListener(this);
        findViewById(R.id.bookname_edit7).setOnClickListener(this);
        findViewById(R.id.bookname_edit8).setOnClickListener(this);
        findViewById(R.id.bookname_edit9).setOnClickListener(this);
        findViewById(R.id.bookname_edit10).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                Utils.finish(CompletebooknameActivity.this);
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
