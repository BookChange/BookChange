package com.app.bookchange.view.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.app.bookchange.R;
import com.app.bookchange.common.Utils;
import com.app.bookchange.view.activity.CompletebooknameActivity;
import com.app.bookchange.view.activity.CompleteinfoActivity;
import com.app.bookchange.view.activity.CustomerserviceActivity;
import com.app.bookchange.view.activity.MyphotoActivity;
import com.app.bookchange.view.activity.SettingActivity;

import static android.content.Context.MODE_PRIVATE;


/*  我的页面
 */
public class Fragment_Myview extends Fragment implements View.OnClickListener {
    private View layout;
    private ImageView iv_photo;
    private String objectId;//数据库主键
    private String name;    //昵称
    private String signature;//个性签名
    private TextView tv_name,tv_signature;
    public static final int UPDATE=1;
    public static final int NOUPDATE=0;

    private  IntentFilter intentFilter;
    private  saveSuccessReceiver saveSuccessReceiver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        layout=inflater.inflate(R.layout.my_info,container,false);


        tv_name= (TextView) layout.findViewById(R.id.account_name);
        tv_signature= (TextView) layout.findViewById(R.id.my_introduction);
        setOnListener();
        Bundle bundle=getArguments();
        if (bundle!=null){
            objectId=bundle.getString("objectId");
            Log.d("objectId","----Fragmentmyview----"+objectId+"------------");
        }
        receiveBrocast();
        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        sendBroadcast();
    }

    //增加监听器
    private void setOnListener() {
        layout.findViewById(R.id.new_message).setOnClickListener(this);
        layout.findViewById(R.id.my_photo_button).setOnClickListener(this);
        layout.findViewById(R.id.complet_info).setOnClickListener(this);
        layout.findViewById(R.id.complet_bookname).setOnClickListener(this);
        layout.findViewById(R.id.my_reading).setOnClickListener(this);
        layout.findViewById(R.id.setting_button).setOnClickListener(this);
        layout.findViewById(R.id.customer_service).setOnClickListener(this);
        layout.findViewById(R.id.about_us).setOnClickListener(this);
        iv_photo = (ImageView) layout.findViewById(R.id.my_photo);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_message:
                Utils.showLongToast(getActivity(), "你还没有新的消息... ");
                break;
            case R.id.my_photo_button:
                Intent intent = new Intent(getActivity(), MyphotoActivity.class);
                intent.putExtra("objectId",objectId);
                getActivity().startActivity(intent);

                break;
            case R.id.complet_info:
                Utils.start_Activity(getActivity(), CompleteinfoActivity.class);
                break;
            case R.id.complet_bookname:
                Utils.start_Activity(getActivity(), CompletebooknameActivity.class);
                break;
            case R.id.my_reading:
                Utils.showLongToast(getActivity(), "读书破万卷，我还没实现... ");
                break;
            case R.id.setting_button:
                Utils.start_Activity(getActivity(), SettingActivity.class);
                break;
            case R.id.customer_service:
                Utils.start_Activity(getActivity(), CustomerserviceActivity.class);
                break;
            case R.id.about_us:
                Utils.showLongToast(getActivity(), "关于郑州，我知道的不多... ");
                break;
            default:
                break;

        }
    }




    public void getMsg(){
       SharedPreferences sharedPreferences=getActivity().getSharedPreferences(objectId
               , MODE_PRIVATE);
       name=sharedPreferences.getString("name","");
       signature=sharedPreferences.getString("signature","");
       Log.d("Fragment_myview","-----getMsg----"+name+"--------"+signature+"--------");
    }


    class saveSuccessReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            getMsg();
            tv_name.setText(name);
            tv_signature.setText(signature);
            Log.d("Fragment_myview","-----------setText----------");
            Log.d("Fragment_myview","-----------收到广播----------");


        }
    }

    private void sendBroadcast(){
        Intent intent=new Intent("saveSuccess");
        getActivity().sendBroadcast(intent);
        Log.d("Mainactivity","------------发送广播-----------");
    }


    private void receiveBrocast(){
        intentFilter=new IntentFilter();
        intentFilter.addAction("saveSuccess");
        saveSuccessReceiver=new saveSuccessReceiver();
        getActivity().registerReceiver(saveSuccessReceiver,intentFilter);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(saveSuccessReceiver);

    }
}
