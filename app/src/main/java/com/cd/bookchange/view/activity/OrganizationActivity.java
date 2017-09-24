package com.cd.bookchange.view.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.cd.bookchange.R;
import com.cd.bookchange.common.Utils;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


public class OrganizationActivity extends ActionBarActivity implements View.OnClickListener {
    private TextView tv_date, tv_time;
    private TextView tv_address;

    Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.organize_activities);
        super.onCreate(savedInstanceState);
        setOnListener();
    }

    //增加监听器
    private void setOnListener() {
        tv_date = (TextView) findViewById(R.id.activities_view_date);
        tv_time = (TextView) findViewById(R.id.activities_view_time);
        tv_address = (TextView) findViewById(R.id.activities_view_address);
        findViewById(R.id.back_button).setOnClickListener(this);
        findViewById(R.id.activities_date).setOnClickListener(this);
        findViewById(R.id.activities_time).setOnClickListener(this);
        findViewById(R.id.activities_address).setOnClickListener(this);
        findViewById(R.id.activitie_name).setOnClickListener(this);
        findViewById(R.id.activitie_context).setOnClickListener(this);
        findViewById(R.id.organize_send_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                Utils.finish(OrganizationActivity.this);
                break;
            case R.id.activities_date:
                new DatePickerDialog(OrganizationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tv_date.setText("日期："+String.format("%d-%d-%d",year,monthOfYear+1,dayOfMonth));
                    }
                },year,month,day).show();
                break;
            case R.id.activities_time:
                new TimePickerDialog(OrganizationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String mHour = "" + hourOfDay;
                        String mMinute = "" + minute;
                        if(hourOfDay<10){
                            mHour="0"+hourOfDay;
                        }
                        if(minute<10){
                            mMinute="0"+minute;
                        }
                        tv_time.setText("时间:   "+ mHour + " : " +  mMinute);
                    }
                },hour,minute,true).show();
                break;
            case R.id.activities_address:
                //获取选中的地址
                Intent intent = new Intent(OrganizationActivity.this, AddressActivity.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.organize_send_button:
                //发布内容
                Utils.showLongToast(OrganizationActivity.this, "内容已发布... ");
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        Utils.finish(OrganizationActivity.this); //执行
                    }
                };
                timer.schedule(task, 500 * 1);  //0.5秒后结束页面
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


}
