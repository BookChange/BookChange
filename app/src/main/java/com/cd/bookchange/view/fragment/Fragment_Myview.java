package com.cd.bookchange.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cd.bookchange.Constants;
import com.cd.bookchange.R;
import com.cd.bookchange.common.Utils;
import com.cd.bookchange.view.activity.CompletebooknameActivity;
import com.cd.bookchange.view.activity.CompleteinfoActivity;
import com.cd.bookchange.view.activity.CustomerserviceActivity;
import com.cd.bookchange.view.activity.MyphotoActivity;
import com.cd.bookchange.view.activity.SettingActivity;

/*  我的页面
 */

public class Fragment_Myview extends Fragment implements View.OnClickListener {
    private Activity ctx;
    private View layout;
    private TextView tvname, tv_introduction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (layout == null) {
            ctx = this.getActivity();
            layout = ctx.getLayoutInflater().inflate(R.layout.my_info, null);
            setOnListener();
            initViews();
        } else {
            ViewGroup parent = (ViewGroup) layout.getParent();
            if(parent != null) {
                parent.removeView(layout);
            }
        }
        return layout;
    }

    //增加监听器
    private void setOnListener() {
        layout.findViewById(R.id.new_message).setOnClickListener(this);
        layout.findViewById(R.id.my_photo).setOnClickListener(this);
        layout.findViewById(R.id.complet_info).setOnClickListener(this);
        layout.findViewById(R.id.complet_bookname).setOnClickListener(this);
        layout.findViewById(R.id.my_reading).setOnClickListener(this);
        layout.findViewById(R.id.setting_button).setOnClickListener(this);
        layout.findViewById(R.id.customer_service).setOnClickListener(this);
        layout.findViewById(R.id.about_us).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_message:
                Utils.showLongToast(getActivity(), "你还没有新的消息... ");
                break;
            case R.id.my_photo:
                Utils.start_Activity(getActivity(), MyphotoActivity.class);
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

    private void initViews() {
        tvname = (TextView) layout.findViewById(R.id.my_nickname);
        tv_introduction = (TextView) layout.findViewById(R.id.my_introduction);
        String name = Utils.getValue(getActivity(), Constants.NAME);
        tvname.setText("昵称：" + name);
        String introduction = Utils.getValue(getActivity(), Constants.UserInfo);
        tv_introduction.setText(introduction);
    }
}
