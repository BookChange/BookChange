package com.cd.bookchange.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cd.bookchange.R;
import com.cd.bookchange.common.Utils;
import com.cd.bookchange.view.BaseActivity;
import com.cd.bookchange.view.fragment.Fragment_Myview;


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
        tvname = (EditText)findViewById(R.id.change_nickname);
        tvname.setOnEditorActionListener(this);
        tv_introduction = (EditText)findViewById(R.id.change_introduction);
        tv_introduction.setOnEditorActionListener(this);
        String name = tvname.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Utils.showLongToast(MyphotoActivity.this, "请填写您的昵称！ ");
            return;
        }
        tvname.setText(name);
        Log.e("TAG_", "昵称" + name);    //调试代码，判断nickname
        String introduction = tv_introduction.getText().toString();
        tv_introduction.setText(introduction);
        Log.e("TAG_", "个性签名"+introduction);  //调试代码，introduction

        //传递当前页面的数据
        Fragment_Myview fragment_myview = new Fragment_Myview();
        Bundle bundle = new Bundle();
        bundle.putString("str_mynickname", name);
        bundle.putString("str_myintroduction", introduction);
        fragment_myview.setArguments(bundle);
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
