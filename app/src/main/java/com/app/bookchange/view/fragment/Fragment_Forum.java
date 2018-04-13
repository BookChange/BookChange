package com.app.bookchange.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.app.bookchange.R;
import com.app.bookchange.common.Utils;
import com.app.bookchange.view.activity.BookfriendActivity;
import com.app.bookchange.view.activity.OrganizationActivity;
import com.app.bookchange.view.activity.SearchActivity;

/*  论坛页面
 */
public class Fragment_Forum extends Fragment implements OnClickListener {
    private Activity ctx;
    private View layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (layout == null) {
            ctx = this.getActivity();
            layout = ctx.getLayoutInflater().inflate(R.layout.forum_square, null);
            setOnListener();
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
        layout.findViewById(R.id.etSearch).setOnClickListener(this);
        layout.findViewById(R.id.my_bookfriend).setOnClickListener(this);
        layout.findViewById(R.id.read_thoughts).setOnClickListener(this);
        layout.findViewById(R.id.offlint_meet).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_bookfriend:
                Utils.start_Activity(getActivity(), BookfriendActivity.class);
                break;
            case R.id.offlint_meet:
                Utils.start_Activity(getActivity(), OrganizationActivity.class);
                break;
            case R.id.etSearch:
                Utils.start_Activity(getActivity(), SearchActivity.class);
                break;

        }
    }
}
