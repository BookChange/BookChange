package com.app.bookchange;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
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

import com.app.bookchange.bean.Account;
import com.app.bookchange.view.fragment.Fragment_Discover;
import com.app.bookchange.view.fragment.Fragment_Forum;
import com.app.bookchange.view.fragment.Fragment_Myview;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.QueryListener;



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


        imagebuttons_two.setSelected(true);
        textviews_two.setTextColor(0xFF45C01A);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container,new Fragment_Discover());
        transaction.commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //更新
        upData();
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

                Fragment_Myview my=new Fragment_Myview();
                Bundle bundle=new Bundle();
                bundle.putString("objectId",objectId);
                my.setArguments(bundle);

                replaceFragment(my);

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


        //更新数据
    public void upData(){
        Log.d("Mainactivity","-------------本地updata开始------------");
        BmobQuery<Account> query=new BmobQuery<Account>();
        query.getObject(objectId, new QueryListener<Account>() {

            @Override
            public void done(Account object, BmobException e) {
                if(e==null){
                    BmobFile file=object.getIcon();
                    downloadFile(file);
                    //存储到本地
                    saveMsg(object);
                    //发送完成
                    Log.d("Mainactivity","-------------本地updata开始------------");
                }else {

                }
            }

        });
    }


    //存储数据到本地
    private void saveMsg(Account account){



        SharedPreferences.Editor editor=getSharedPreferences(account.getObjectId()
                ,MODE_PRIVATE).edit();
        editor.putString("object",account.getObjectId());
        editor.putString("name",account.getName());
        editor.putString("signature",account.getSignature());
        editor.putString("account",account.getAccount());
        editor.apply();

        Log.d("Mainactivity","-----------------------"+account.getObjectId());
        Log.d("Mainactivity","------------saveMsg-----------");

    }


    private void sendBroadcast(){
        Intent intent=new Intent("saveSuccess");
        sendBroadcast(intent);
        Log.d("Mainactivity","------------发送广播-----------");
    }

    private void downloadFile(BmobFile file){
        //允许设置下载文件的存储路径，默认下载文件的目录为：context.getApplicationContext().getCacheDir()+"/bmob/"
        File saveFile = new File(Environment.getExternalStorageDirectory(), file.getFilename());
        file.download(saveFile, new DownloadFileListener() {

            @Override
            public void onStart() {
               Log.d("file","开始下载...");
            }

            @Override
            public void done(String savePath,BmobException e) {
                if(e==null){
                    SharedPreferences.Editor editor=getSharedPreferences(objectId
                            ,MODE_PRIVATE).edit();
                    editor.putString("imagePath",savePath);
                    editor.apply();
                    Log.d("file","下载成功,保存路径:"+savePath);
                    sendBroadcast();
                }else{
                    Log.d("file","下载失败："+e.getErrorCode()+","+e.getMessage());
                }
            }

            @Override
            public void onProgress(Integer value, long newworkSpeed) {
                Log.i("bmob","下载进度："+value+","+newworkSpeed);
            }

        });
    }

}