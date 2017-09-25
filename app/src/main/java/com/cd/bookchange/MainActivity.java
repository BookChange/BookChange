package com.cd.bookchange;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cd.bookchange.view.fragment.Fragment_Discover;
import com.cd.bookchange.view.fragment.Fragment_Forum;
import com.cd.bookchange.view.fragment.Fragment_Myview;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends FragmentActivity implements OnClickListener {
    private Fragment[] fragments;   //页面容器
    private Fragment_Forum forumfragment;   //添加论坛
    private Fragment_Discover findfragment; //添加发现
    private Fragment_Myview myfragment; //添加我的
    private ImageView[] imagebuttons;
    private TextView[] textviews;

    private int index;  //底部状态栏的index
    private int currentTabIndex;// 当前fragment的index

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main );
        App.getInstance2().addActivity(this);
        initTableView();
    }

    //初始化界面
    private void initTableView() {
        //实例化对象
        forumfragment = new Fragment_Forum();
        findfragment = new Fragment_Discover();
        myfragment = new Fragment_Myview();
        fragments = new Fragment[]{ forumfragment, findfragment, myfragment };

        //对底部导航栏添加监听，方便对图片以及字体改变颜色
        imagebuttons = new ImageView[3];
        imagebuttons[0] = (ImageView) findViewById(R.id.ib_forum);
        imagebuttons[1] = (ImageView) findViewById(R.id.ib_find);
        imagebuttons[2] = (ImageView) findViewById(R.id.ib_my);

        imagebuttons[0].setSelected(true);

        textviews = new TextView[3];
        textviews[0] = (TextView) findViewById(R.id.tv_forum);
        textviews[1] = (TextView) findViewById(R.id.tv_find);
        textviews[2] = (TextView) findViewById(R.id.tv_my);

        textviews[0].setTextColor(0xFF45C01A);

        //添加显示第一个fragment为论坛页面
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, forumfragment)
                .add(R.id.fragment_container, findfragment)
                .add(R.id.fragment_container, myfragment)
                .hide(findfragment).hide(myfragment)
                .show(forumfragment).commit();
    }

    //获取当前页面的index
    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.btm_forum:
                index = 0;
                break;
            case R.id.btm_find:
                index = 1;
                break;
            case R.id.btm_my:
                index = 2;
                break;
        }

        //跳转页面的逻辑判断，判断当前页面以及当前底部导航栏的index是否相等并添加显示页面
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager()
                    .beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        //跳转页面时将底部导航栏颜色取消
        imagebuttons[currentTabIndex].setSelected(false);
        textviews[currentTabIndex].setTextColor(0xFF999999);

        // 把当前tab设为选中状态并且改变其颜色
        imagebuttons[index].setSelected(true);
        textviews[index].setTextColor(0xFF45C01A);
        currentTabIndex = index;
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

    @Override
    public void onClick(View v) {

    }
}