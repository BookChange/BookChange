package com.cd.bookchange;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cd.bookchange.bean.Account;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
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

        Account account_one = new Account();
        switch (v.getId()) {
            case R.id.b_one:
                String account = editTextone.getText().toString();
                String password = editTexttwo.getText().toString();
                if (account.equals("")||password.equals("")){return;}

                account_one.setAccount(account);
                account_one.setPassword(password);
                account_one.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e==null){
                            Toast.makeText(getApplicationContext(), "注册成功！", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "注册失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        }

    }







}
