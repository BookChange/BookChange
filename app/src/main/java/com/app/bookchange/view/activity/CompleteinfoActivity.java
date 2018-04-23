package com.app.bookchange.view.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.TextView;

import com.app.bookchange.R;
import com.app.bookchange.common.Utils;
import com.app.bookchange.view.BaseActivity;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/9/15.
 */

public class CompleteinfoActivity extends BaseActivity implements OnClickListener {
    private TextView tv_age,tv_address;

    Calendar c = Calendar.getInstance();
    int thisyear = c.get(Calendar.YEAR);    //获取当前年份

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.complete_info);
        super.onCreate(savedInstanceState);
        setOnListner();
    }

    private void setOnListner() {
        findViewById(R.id.back_button).setOnClickListener(this);
        findViewById(R.id.name_text).setOnClickListener(this);
        findViewById(R.id.sex_man_Button).setOnClickListener(this);
        findViewById(R.id.sex_woman_Button).setOnClickListener(this);
        findViewById(R.id.love_switch).setOnClickListener(this);
        findViewById(R.id.hobby_text).setOnClickListener(this);
        findViewById(R.id.select_age).setOnClickListener(this);
        findViewById(R.id.select_address).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                Utils.finish(CompleteinfoActivity.this);
                break;
            case R.id.select_age:
                new DatePickerDialog(CompleteinfoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int age = thisyear - year;
                        tv_age.setText("年龄："+age +"岁");
                    }
                },1990,6,30).show();
                break;
            case R.id.select_address:
                //获取选中的地址
                Intent intent = new Intent(CompleteinfoActivity.this, AddressActivity.class);
                startActivityForResult(intent, 1);
                break;
            default:
                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        tv_address.setText(data.getStringExtra("str")); //getString()返回指定key的值,并用TextView显示值
    }

    @Override
    protected void initControl() {
        tv_age = (TextView)findViewById(R.id.activities_view_age);
        tv_address = (TextView)findViewById(R.id.activities_view_address);
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
}
