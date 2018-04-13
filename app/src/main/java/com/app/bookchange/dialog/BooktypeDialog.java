package com.app.bookchange.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.bookchange.R;

import java.util.ArrayList;

public class BooktypeDialog extends Dialog implements View.OnClickListener {
    private ArrayList<String> bookstyledate = new ArrayList<String>();
    private Button bt;
    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14;

    //定义接口
    public interface DataBackListener{
        public void getData(String[] data);
    }

    DataBackListener listener;   //创建监听对象


    public BooktypeDialog(Context context, final DataBackListener listener) {
        super(context,R.style.MyAnimDialog);

        //用传递过来的监听器来初始化
        this.listener = listener;

        //加载布局并给布局的控件设置点击事件
        View contentView = getLayoutInflater().inflate(R.layout.booktype_select, null);
        super.setContentView(contentView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOnListner();

        // 预先设置Dialog的一些属性,指定的Dialog的高度和宽度
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        getWindow().setAttributes(p);
        p.height = (int) (d.getHeight() * 0.6);
        p.width = d.getWidth();
        p.gravity = Gravity.LEFT | Gravity.BOTTOM;
        dialogWindow.setAttributes(p);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                //这里调用接口，将数据传递出去。
                String[] s = new String[bookstyledate.size()];
                s = bookstyledate.toArray(s);
                listener.getData(s);

                Toast.makeText(getContext(), "已保存...", Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            case R.id.tv_1:
                tv1.setSelected(true);
                bookstyledate.add(tv1.getText().toString());
                break;
            case R.id.tv_2:
                tv2.setSelected(true);
                bookstyledate.add(tv2.getText().toString());
                break;
            case R.id.tv_3:
                tv3.setSelected(true);
                bookstyledate.add(tv3.getText().toString());
                break;
            case R.id.tv_4:
                tv4.setSelected(true);
                bookstyledate.add(tv4.getText().toString());
                break;
            case R.id.tv_5:
                tv5.setSelected(true);
                bookstyledate.add(tv5.getText().toString());
                break;
            case R.id.tv_6:
                tv6.setSelected(true);
                bookstyledate.add(tv6.getText().toString());
                break;
            case R.id.tv_7:
                tv7.setSelected(true);
                bookstyledate.add(tv7.getText().toString());
                break;
            case R.id.tv_8:
                tv8.setSelected(true);
                bookstyledate.add(tv8.getText().toString());
                break;
            case R.id.tv_9:
                tv9.setSelected(true);
                bookstyledate.add(tv9.getText().toString());
                break;
            case R.id.tv_10:
                tv10.setSelected(true);
                bookstyledate.add(tv10.getText().toString());
                break;
            case R.id.tv_11:
                tv11.setSelected(true);
                bookstyledate.add(tv11.getText().toString());
                break;
            case R.id.tv_12:
                tv12.setSelected(true);
                bookstyledate.add(tv12.getText().toString());
                break;
            case R.id.tv_13:
                tv13.setSelected(true);
                bookstyledate.add(tv13.getText().toString());
                break;
            case R.id.tv_14:
                tv14.setSelected(true);
                bookstyledate.add(tv14.getText().toString());
                break;

        }
    }

    private void setOnListner() {
        bt = (Button) findViewById(R.id.btn_confirm);
        bt.setOnClickListener(this);
        tv1 = (TextView)findViewById(R.id.tv_1);
        tv1.setOnClickListener(this);
        tv2 = (TextView) findViewById(R.id.tv_2);
        tv2.setOnClickListener(this);
        tv3 = (TextView) findViewById(R.id.tv_3);
        tv3.setOnClickListener(this);
        tv4 = (TextView) findViewById(R.id.tv_4);
        tv4.setOnClickListener(this);
        tv5 = (TextView) findViewById(R.id.tv_5);
        tv5.setOnClickListener(this);
        tv6 = (TextView) findViewById(R.id.tv_6);
        tv6.setOnClickListener(this);
        tv7 = (TextView) findViewById(R.id.tv_7);
        tv7.setOnClickListener(this);
        tv8 = (TextView) findViewById(R.id.tv_8);
        tv8.setOnClickListener(this);
        tv9 = (TextView) findViewById(R.id.tv_9);
        tv9.setOnClickListener(this);
        tv10 = (TextView) findViewById(R.id.tv_10);
        tv10.setOnClickListener(this);
        tv11 = (TextView) findViewById(R.id.tv_11);
        tv11.setOnClickListener(this);
        tv12 = (TextView) findViewById(R.id.tv_12);
        tv12.setOnClickListener(this);
        tv13 = (TextView) findViewById(R.id.tv_13);
        tv13.setOnClickListener(this);
        tv14 = (TextView) findViewById(R.id.tv_14);
        tv14.setOnClickListener(this);
    }

}
