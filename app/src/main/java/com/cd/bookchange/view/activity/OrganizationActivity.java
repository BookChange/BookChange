package com.cd.bookchange.view.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.cd.bookchange.R;
import com.cd.bookchange.common.Utils;

import java.util.Calendar;


public class OrganizationActivity extends ActionBarActivity implements View.OnClickListener {
    private TextView tv_time;
    private TextView tv_address;

    Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.organize_activities);
        super.onCreate(savedInstanceState);
        setOnListener();
    }

    //增加监听器
    private void setOnListener() {
        tv_time = (TextView) findViewById(R.id.activities_view_time);
        tv_address = (TextView) findViewById(R.id.activities_view_address);
        findViewById(R.id.back_button).setOnClickListener(this);
        findViewById(R.id.activities_time).setOnClickListener(this);
        findViewById(R.id.activities_address).setOnClickListener(this);
        findViewById(R.id.activitie_name).setOnClickListener(this);
        findViewById(R.id.activitie_context).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                Utils.finish(OrganizationActivity.this);
                break;
            case R.id.activities_time:
                new DatePickerDialog(OrganizationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tv_time.setText("时间："+String.format("%d-%d-%d",year,monthOfYear+1,dayOfMonth));
                    }
                },year,month,day).show();
                break;
            case R.id.activities_address:
                Utils.start_Activity(OrganizationActivity.this, AddressActivity.class);

                //获取选中的地址
                Intent intent=getIntent();  //getIntent将该项目中包含的原始intent检索出来，将检索出来的intent赋值给一个Intent类型的变量intent
                String str=intent.getStringExtra("str"); //getString()返回指定key的值
                tv_address.setText(str);    //用TextView显示值

                break;
            default:
                break;

        }

    }


}
