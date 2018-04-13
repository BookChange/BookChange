package com.app.bookchange;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.bookchange.bean.Account;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.SaveListener;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextone;
    private EditText editTexttwo;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Bmob.initialize(this, "14499f070e7dd633a64c543597cade6f", "Bmob");

        editTextone = (EditText) findViewById(R.id.e_one);
        editTexttwo = (EditText) findViewById(R.id.e_two);


        button = (Button) findViewById(R.id.b_one);
        button.setOnClickListener(this);
    }

    public void onClick(View v) {

        final Account account_one = new Account();
        switch (v.getId()) {
            case R.id.b_one:
                BmobQuery<Account> query=new BmobQuery<Account>();
                final String account = editTextone.getText().toString();
                String password = editTexttwo.getText().toString();
                if (account.equals("")||password.equals("")){return;}

                account_one.setAccount(account);
                account_one.setPassword(password);
                query.addWhereEqualTo("account",account);
                query.count(Account.class, new CountListener() {
                    @Override
                    public void done(Integer integer, BmobException e) {
                        if (integer==0){
                            account_one.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if(e==null){
                                        Toast.makeText(getApplicationContext(), "注册成功！即将返回登陆界面", Toast.LENGTH_SHORT).show();
                                        new Handler().postDelayed(new Runnable() {
                                            public void run() {
                                                Intent intent = new Intent(SecondActivity.this, FirstActivity.class);
                                                SecondActivity.this.startActivity(intent);
                                                SecondActivity.this.finish();
                                            }
                                        }, 1500);
                                    }else{
                                        Toast.makeText(getApplicationContext(), "注册失败！", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else {Toast.makeText(getApplicationContext(), "账号已存在！", Toast.LENGTH_SHORT).show();}

                    }
                });



        }

    }







}
