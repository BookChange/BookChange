package com.cd.bookchange.view.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.cd.bookchange.R;
import com.cd.bookchange.common.Utils;


public class OrganizationActivity extends ActionBarActivity implements View.OnClickListener {
    private TextView tv;
//    long time=System.currentTimeMillis();   //获取当前系统时间

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.organize_activities);
        super.onCreate(savedInstanceState);
        setOnListener();
    }
//
//    Time t=new Time(time);
//    int year = t.getYear();
//    int month = t.getMonth();
//    int date = t.getDay();

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
                        tv.setText("时间："+String.format("%d-%d-%d",year,monthOfYear,dayOfMonth));
                    }
                },2017,9,20).show();
                break;
            default:
                break;

        }

    }

    //增加监听器
    private void setOnListener() {
        tv = (TextView) findViewById(R.id.activities_view_time);
        findViewById(R.id.back_button).setOnClickListener(this);
        findViewById(R.id.activities_time).setOnClickListener(this);
        findViewById(R.id.activities_address).setOnClickListener(this);
        findViewById(R.id.activitie_name).setOnClickListener(this);
        findViewById(R.id.activitie_context).setOnClickListener(this);
    }

}
