package com.cd.bookchange;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class ScreenActivity extends AppCompatActivity {
    private SimpleDraweeView dvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);//放在加载布局之前
        ActionBar actionBar = getSupportActionBar();
        setContentView(R.layout.activity_screen);
        dvWelcome= (SimpleDraweeView) findViewById(R.id.dv_welcome);


        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)//自动播放动画
                .setUri(Uri.parse("asset://com.cd.bookchange/screen.gif"))//路径
                .build();
        dvWelcome.setController(draweeController);

        if (actionBar != null)
            actionBar.hide();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(ScreenActivity.this, FirstActivity.class);
                ScreenActivity.this.startActivity(intent);
                ScreenActivity.this.finish();
            }
        }, 2500);
    }
}
