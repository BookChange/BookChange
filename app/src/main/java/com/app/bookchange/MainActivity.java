package com.app.bookchange;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.bookchange.view.fragment.Fragment_Discover;
import com.app.bookchange.view.fragment.Fragment_Forum;
import com.app.bookchange.view.fragment.Fragment_Myview;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends FragmentActivity implements OnClickListener {
    private ImageView imagebuttons_one,imagebuttons_two,imagebuttons_three;
    private TextView textviews_one,textviews_two,textviews_three;


    String objectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main );
        Intent intent=getIntent();
        objectId=intent.getStringExtra("objectId");
        Log.d("objectId","-----------"+objectId+"--------------");

        imagebuttons_one= (ImageView) findViewById(R.id.ib_forum);
        textviews_one= (TextView) findViewById(R.id.tv_forum);
        imagebuttons_two= (ImageView) findViewById(R.id.ib_find);
        textviews_two= (TextView) findViewById(R.id.tv_find);
        imagebuttons_three= (ImageView) findViewById(R.id.ib_my);
        textviews_three= (TextView) findViewById(R.id.tv_my);


        imagebuttons_one.setSelected(true);
        textviews_one.setTextColor(0xFF45C01A);

        replaceFragment(new Fragment_Forum());



    }


    //获取当前页面的index
    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.btm_forum:
               replaceFragment(new Fragment_Forum());

                imagebuttons_two.setSelected(false);
                textviews_two.setTextColor(0xFF999999);
                imagebuttons_three.setSelected(false);
                textviews_three.setTextColor(0xFF999999);

                imagebuttons_one.setSelected(true);
                textviews_one.setTextColor(0xFF45C01A);
                break;
            case R.id.btm_find:
                replaceFragment(new Fragment_Discover());

                imagebuttons_one.setSelected(false);
                textviews_one.setTextColor(0xFF999999);
                imagebuttons_three.setSelected(false);
                textviews_three.setTextColor(0xFF999999);

                imagebuttons_two.setSelected(true);
                textviews_two.setTextColor(0xFF45C01A);

                break;
            case R.id.btm_my:
               replaceFragment(new Fragment_Myview());

                imagebuttons_two.setSelected(false);
                textviews_two.setTextColor(0xFF999999);
                imagebuttons_one.setSelected(false);
                textviews_one.setTextColor(0xFF999999);

                imagebuttons_three.setSelected(true);
                textviews_three.setTextColor(0xFF45C01A);

                break;
        }




    }

    private int keyBackClickCount = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { //再按一次就返回功能。
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            switch (keyBackClickCount++) {
                case 0:
                    Toast.makeText(this, "再次按返回键退出", Toast.LENGTH_SHORT).show();
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {  //不在三秒内点击就归零
                        @Override
                        public void run() {
                            keyBackClickCount = 0;
                        }
                    }, 3000);
                    break;
                case 1:
                    App.getInstance2().exit();
                    finish();
                    overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
                    break;
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    public  void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();

    }
    @Override
    public void onClick(View v) {

    }

}