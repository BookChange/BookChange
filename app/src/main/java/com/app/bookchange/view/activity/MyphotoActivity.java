package com.app.bookchange.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.app.bookchange.R;
import com.app.bookchange.bean.Account;
import com.app.bookchange.common.Utils;
import com.app.bookchange.view.BaseActivity;


import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;


public class MyphotoActivity extends BaseActivity implements View.OnClickListener,TextView.OnEditorActionListener {
    private TextView tvname, tvsignature;
    private String etname, etsignature;
    private String name,signature;
    private String objectId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.headshot_view);
        super.onCreate(savedInstanceState);
        setOnListner();
        Intent intent=getIntent();

        objectId=intent.getStringExtra("objectId");
        Log.d("TAG","------------------"+objectId);

        getMsg();
        tvname.setText(name);
        tvsignature.setText(signature);
        Log.d("MyphotoActivity","---------------setText----------------");


    }

    private void setOnListner() {
        findViewById(R.id.back_button).setOnClickListener(this);
        findViewById(R.id.change_headshot).setOnClickListener(this);
        findViewById(R.id.change_save).setOnClickListener(this);
        tvname = (EditText)findViewById(R.id.change_nickname);
        tvname.setOnEditorActionListener(this);
        tvsignature= (EditText)findViewById(R.id.change_introduction);
        tvsignature.setOnEditorActionListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                Utils.finish(MyphotoActivity.this);
                break;
            case R.id.change_save:
                etname = tvname.getText().toString();
                etsignature = tvsignature.getText().toString();
                //保存数据

                if (TextUtils.isEmpty(etname)) {
                    Utils.showLongToast(MyphotoActivity.this, "请填写您的昵称！ ");
                    return;
                }

                changeMsg();


                break;
            case R.id.change_nickname:
                break;
            case R.id.change_introduction:
                break;
            default:
                break;

        }
    }

    private void Return(){

        MyphotoActivity.this.finish();// 0表示成功
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
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }


    public void changeMsg(){

        final Account object=new Account();
                    object.setName(etname);
                    object.update(objectId, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {

                            if(e==null){
                                Log.d("MyphotoActivivty","-----updata-----"+object.getName());
                            }else{
                            }
                        }
                    });

                    object.setSignature(etsignature);
                    object.update(objectId, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Log.d("MyphotoActivivty","------服务器updata完成------");
                                Return();

                            }else{

                            }
                        }
                    });



    }


    public void getMsg(){
        SharedPreferences sharedPreferences=getSharedPreferences(objectId
                , MODE_PRIVATE);
        name=sharedPreferences.getString("name","");
        signature=sharedPreferences.getString("signature","");
        Log.d("MyphotoActivivty","-----getMsg----"+name+"--------"+signature+"--------");
    }
}
