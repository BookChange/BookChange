package com.cd.bookchange.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.cd.bookchange.R;
import com.cd.bookchange.view.BaseActivity;

/**
 * Created by Administrator on 2017/9/18.
 */

public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private EditText etsearch;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.search_forum);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initControl() {
        etsearch = (EditText) findViewById(R.id.search_view_icon);

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

    @Override
    public void onClick(View v) {

    }
}
