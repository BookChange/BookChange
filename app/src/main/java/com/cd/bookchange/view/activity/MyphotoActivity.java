package com.cd.bookchange.view.activity;

import android.content.Intent;
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
        findViewById(R.id.change_headshot_save).setOnClickListener(this);
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
            case R.id.change_headshot_save:
                String name = tvname.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Utils.showLongToast(MyphotoActivity.this, "请填写您的昵称！ ");
                    return;
                }
                tvname.setText(name);
                String introduction = tv_introduction.getText().toString();
                tv_introduction.setText(introduction);
//                Log.e("TAG_", "昵称" + name);    //调试代码，判断nickname
//                Log.e("TAG_", "个性签名"+introduction);  //调试代码，introduction

                //传递当前选中的数据
                Intent intent = new Intent();
                intent.putExtra("str_mynickname", name);  //给intent添加额外数据，key为“str”,value为选中的地址
                intent.putExtra("str_introduction", introduction);
                setResult(0, intent);  // 0表示成功

                Utils.finish(MyphotoActivity.this);
                break;
            case R.id.change_nickname:
                break;
            case R.id.change_introduction:
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
