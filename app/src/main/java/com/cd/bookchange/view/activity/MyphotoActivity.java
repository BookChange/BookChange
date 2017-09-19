package com.cd.bookchange.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cd.bookchange.Constants;
import com.cd.bookchange.GloableParams;
import com.cd.bookchange.R;
import com.cd.bookchange.bean.User;
import com.cd.bookchange.common.Utils;
import com.cd.bookchange.view.BaseActivity;


public class MyphotoActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvname, tv_introduction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.headshot_view);
        super.onCreate(savedInstanceState);
        setOnListner();
    }

    private void setOnListner() {
        findViewById(R.id.back_button).setOnClickListener(this);
        findViewById(R.id.change_headshot).setOnClickListener(this);
        findViewById(R.id.my_nickname).setOnClickListener(this);
        findViewById(R.id.my_biography).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                Utils.finish(MyphotoActivity.this);
                break;
//            case R.id.change_headshot:
//                break;
//            case R.id.my_nickname:
//                break;
//            case R.id.my_biography:
//                break;
            default:
                break;

        }

    }

    @Override
    protected void initControl() {
        tvname = (TextView) findViewById(R.id.my_nickname);
        tv_introduction = (TextView) findViewById(R.id.my_biography);
    }

    @Override
    protected void initView() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void initData() {
        String name = Utils.getValue(this, Constants.NAME);
        tvname.setText(name);
        User user = GloableParams.Users.get(name);
        String introduction = Utils.getValue(this, Constants.UserInfo);
        tv_introduction.setText(introduction);
        user = GloableParams.Users.get(introduction);
    }

    @Override
    protected void setListener() {
        // TODO Auto-generated method stub

    }
}
