package com.cd.bookchange;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cd.bookchange.bean.Account;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextone;
    private EditText editTexttwo;
    private Button bone;
    private Button btwo;
    private Button bthree;
    private Button bfour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Bmob.initialize(this, "14499f070e7dd633a64c543597cade6f", "Bmob");

        Collector.addActivity(this);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
            actionBar.hide();

        editTextone=(EditText) findViewById(R.id.e_one);
        editTexttwo=(EditText) findViewById(R.id.e_two);
        bone=(Button) findViewById(R.id.b_one);
        btwo=(Button) findViewById(R.id.b_two);
        bthree=(Button) findViewById(R.id.b_three);
        bfour=(Button) findViewById(R.id.b_four);


        bone.setOnClickListener(this);
        btwo.setOnClickListener(this);
        bthree.setOnClickListener(this);
        bfour.setOnClickListener(this);

    }

    public void onClick(View v){

        switch (v.getId()) {
            case R.id.b_one:
                //点击登录关闭软键盘
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                BmobQuery<Account> query=new BmobQuery<Account>();
                final String a=editTextone.getText().toString();
                final String b=editTexttwo.getText().toString();


                query.addWhereEqualTo("account",a);
                query.count(Account.class, new CountListener() {
                    @Override

                    public void done(Integer integer, BmobException e) {
                        if (a.equals("")||b.equals("")){
                            Toast.makeText(getApplicationContext(), "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
                             return;}
                        if(e==null){
                            if (integer==0){
                                Toast.makeText(getApplicationContext(), "账号不存在！", Toast.LENGTH_SHORT).show();}
                        }else{
                            Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                        }
                    }
                });
                query.findObjects(new FindListener<Account>() {
                    @Override
                    public void done(List<Account> list, BmobException e) {
                        if(e==null){
                            for (Account account:list){

                                      if (b.equals(account.getPassword())){
                                          Toast.makeText(getApplication(),"登陆成功",Toast.LENGTH_SHORT).show();

                                          Intent intent = new Intent(FirstActivity.this, MainActivity.class);
                                          FirstActivity.this.startActivity(intent);
                                          Collector.finishAll();
                                      }
                                      else {
                                          Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_SHORT).show();
                                      }
                                  }

                        }else{
                            Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                        }
                    }
                });
                break;

            case R.id.b_two:
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                FirstActivity.this.startActivity(intent);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Collector.removeActivity(this);
    }
}
