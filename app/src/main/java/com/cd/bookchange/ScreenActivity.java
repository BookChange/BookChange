package com.cd.bookchange;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class ScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        ActionBar actionBar = getSupportActionBar();
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
