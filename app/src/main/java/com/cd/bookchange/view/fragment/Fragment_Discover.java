package com.cd.bookchange.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.cd.bookchange.R;

/*  发现页面
 */

public class Fragment_Discover extends Fragment implements OnClickListener {
    private Activity ctx;
    private View layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (layout == null) {
            ctx = this.getActivity();
            layout = ctx.getLayoutInflater().inflate(R.layout.nearby_reading, null);
            setOnListener();
        } else {
            ViewGroup parent = (ViewGroup) layout.getParent();
            if (parent != null) {
                parent.removeView(layout);
            }
        }
        return layout;
    }

    //增加监听器
    private void setOnListener() {
        layout.findViewById(R.id.nearby_reading_imagebuttom).setOnClickListener(this);
        layout.findViewById(R.id.nearby_reading_imagebuttom2).setOnClickListener(this);
        layout.findViewById(R.id.nearby_reading_imagebuttom3).setOnClickListener(this);
        layout.findViewById(R.id.nearby_reading_imagebuttom4).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
