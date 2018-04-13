package com.app.bookchange.view.activity;

import android.os.Bundle;
import android.view.View;

import com.app.bookchange.R;
import com.app.bookchange.common.Utils;
import com.app.bookchange.dialog.BooktypeDialog;
import com.app.bookchange.view.BaseActivity;

public class CompletebooknameActivity extends BaseActivity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.complete_bookname);
        super.onCreate(savedInstanceState);
        setOnListner();
    }

    private void setOnListner() {
        findViewById(R.id.back_button).setOnClickListener(this);
        findViewById(R.id.book_count).setOnClickListener(this);
        findViewById(R.id.book_type).setOnClickListener(this);
        findViewById(R.id.bookname_edit1).setOnClickListener(this);
        findViewById(R.id.bookname_edit2).setOnClickListener(this);
        findViewById(R.id.bookname_edit3).setOnClickListener(this);
        findViewById(R.id.bookname_edit4).setOnClickListener(this);
        findViewById(R.id.bookname_edit5).setOnClickListener(this);
        findViewById(R.id.bookname_edit6).setOnClickListener(this);
        findViewById(R.id.bookname_edit7).setOnClickListener(this);
        findViewById(R.id.bookname_edit8).setOnClickListener(this);
        findViewById(R.id.bookname_edit9).setOnClickListener(this);
        findViewById(R.id.bookname_edit10).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                Utils.finish(CompletebooknameActivity.this);
                break;
            case R.id.book_type:
                BooktypeDialog dialog = new BooktypeDialog(CompletebooknameActivity.this,
                                new BooktypeDialog.DataBackListener() {
                                    @Override
                                    public void getData(String[] data) {
                                        String[] result = data; //定义String类型的result来存放用户选择的图书类型
//                                        for(String s: result){
//                                            Log.e("TAG_", s); //调试判断是否有值
//                                        }
                                    }
                                });
                        dialog.show();
                break;
            default:
                break;

        }

    }

    @Override
    protected void initControl() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void initView() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void initData() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void setListener() {
        // TODO Auto-generated method stub

    }
}
