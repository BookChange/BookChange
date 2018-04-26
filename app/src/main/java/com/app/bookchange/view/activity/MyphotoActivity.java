package com.app.bookchange.view.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.bookchange.R;
import com.app.bookchange.bean.Account;
import com.app.bookchange.common.Utils;
import com.app.bookchange.view.BaseActivity;


import java.io.File;


import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;


public class MyphotoActivity extends BaseActivity implements View.OnClickListener,TextView.OnEditorActionListener {
    private TextView tvname, tvsignature;
    private String etname, etsignature;
    private String name,signature;
    private String objectId;
    public  static final int CHOOSE_PHOTO=2;

    private ImageView picture;
    String imagePath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.headshot_view);
        super.onCreate(savedInstanceState);
        setOnListner();
        Intent intent=getIntent();

        objectId=intent.getStringExtra("objectId");
        Log.d("TAG","------------------"+objectId);

        getMsg();
        tvname.setText(name);
        tvsignature.setText(signature);
        Log.d("MyphotoActivity","---------------setText----------------");
        if(imagePath!=null) {
            displayImage(imagePath);
        }

    }

    private void setOnListner() {
        findViewById(R.id.back_button).setOnClickListener(this);
        findViewById(R.id.change_headshot).setOnClickListener(this);
        findViewById(R.id.change_save).setOnClickListener(this);
        tvname = (EditText)findViewById(R.id.change_nickname);
        tvname.setOnEditorActionListener(this);
        tvsignature= (EditText)findViewById(R.id.change_introduction);
        tvsignature.setOnEditorActionListener(this);
        picture= (ImageView) findViewById(R.id.image_head);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                Utils.finish(MyphotoActivity.this);
                break;
            case R.id.change_save:
                etname = tvname.getText().toString();
                etsignature = tvsignature.getText().toString();
                //保存数据

                if (TextUtils.isEmpty(etname)) {
                    Utils.showLongToast(MyphotoActivity.this, "请填写您的昵称！ ");
                    return;
                }

                changeMsg();
                Toast.makeText(MyphotoActivity.this
                        , "请等待，正在上传资料......", Toast.LENGTH_LONG).show();


                break;
            case R.id.change_headshot:
                if (ContextCompat.checkSelfPermission(MyphotoActivity.this
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                        PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MyphotoActivity.this
                            ,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    openAlbum();
                }
            case R.id.change_nickname:
                break;
            case R.id.change_introduction:
                break;
            default:
                break;

        }
    }

    private void Return(){

        MyphotoActivity.this.finish();// 0表示成功
    }

    @Override
    protected void initControl() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }


    public void changeMsg(){

        final Account object=new Account();
                    object.setName(etname);
                    object.setSignature(etsignature);
                    object.update(objectId, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {

                            if(e==null){

                                Log.d("MyphotoActivivty","-----updata-----"+object.getName());
                            }else{
                            }
                        }
                    });

                    upImage(object,imagePath);


    }


    public void getMsg(){
        SharedPreferences sharedPreferences=getSharedPreferences(objectId
                , MODE_PRIVATE);
        name=sharedPreferences.getString("name","");
        signature=sharedPreferences.getString("signature","");
        imagePath=sharedPreferences.getString("imagePath","");
        Log.d("MyphotoActivivty","-----getMsg----"+name+"--------"+signature+"--------"
        +imagePath);
    }

    private void openAlbum(){
        Intent intent=new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode
            , @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]
                        ==PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else {
                    Toast.makeText(this,"You denied the permission"
                    ,Toast.LENGTH_SHORT).show();
                }
                break;
                default:
                    break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case CHOOSE_PHOTO:
                 if (data==null)
                    break;
                else handleImageOnKitKat(data);
                break;
            default:
                break;

        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void handleImageOnKitKat(Intent data){
        imagePath = null;
        Uri uri = data.getData();
        Log.d("TAG", "handleImageOnKitKat: uri is " + uri);
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
        Log.d("MyphotoActivity","-----------imagePath-----------"+imagePath);
        // 根据图片路径显示图片
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    private void upImage(final Account account, String picPath){
        final BmobFile bmobFile = new BmobFile(new File(picPath));
        bmobFile.uploadblock(new UploadFileListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){

                    account.setIcon(bmobFile);
                    account.update(objectId, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {

                            Return();

                        }
                    });
                    Log.d("Myphotoactivity","-----------------------"+account.getObjectId());
                    //bmobFile.getFileUrl()--返回的上传文件的完整地址
                    Log.d("上传文件成功:" , bmobFile.getFileUrl());
                }else{
                    Log.d("上传文件失败：" , e.getMessage());
                }

            }

            @Override
            public void onProgress(Integer value) {
                // 返回的上传进度（百分比）
            }
        });
    }
}
