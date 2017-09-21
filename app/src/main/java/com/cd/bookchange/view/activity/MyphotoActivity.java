package com.cd.bookchange.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cd.bookchange.Constants;
import com.cd.bookchange.GloableParams;
import com.cd.bookchange.R;
import com.cd.bookchange.bean.User;
import com.cd.bookchange.common.Utils;
import com.cd.bookchange.view.BaseActivity;


public class MyphotoActivity extends BaseActivity implements View.OnClickListener,TextView.OnEditorActionListener {
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
        tvname = (EditText)findViewById(R.id.change_nickname);
        tvname.setOnEditorActionListener(this);
        tv_introduction = (EditText)findViewById(R.id.change_introduction);
        tv_introduction.setOnEditorActionListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                Utils.finish(MyphotoActivity.this);
                break;
//            case R.id.change_headshot:
//                break;
//            case R.id.change_nickname:
//                break;
//            case R.id.change_introduction:
//                break;
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
        String name = tvname.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Utils.showLongToast(MyphotoActivity.this, "请填写您的昵称！ ");
            return;
        }
        tvname.setText(name);
        System.out.print("昵称"+name);    //调试代码，判断nickname
        User user = GloableParams.Users.get(name);
        if (user != null && !TextUtils.isEmpty(user.getUserName()))
            tvname.setText(user.getUserName());
        String introduction = tv_introduction.getText().toString();
        System.out.print("个性签名"+introduction);  //调试代码，introduction
        tv_introduction.setText(introduction);
        introduction = Utils.getValue(this, Constants.UserInfo);
        user = GloableParams.Users.get(introduction);
    }

    @Override
    protected void setListener() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {


        return true;
    }
}
