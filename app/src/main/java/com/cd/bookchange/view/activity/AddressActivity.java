package com.cd.bookchange.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cd.bookchange.R;
import com.cd.bookchange.common.Utils;

import java.util.Timer;
import java.util.TimerTask;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

public class AddressActivity extends AddressBaseActivity implements View.OnClickListener, OnWheelChangedListener {

    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    private Button mBtnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_select);
        setUpViews();
        setUpListener();
        setUpData();
    }

    private void setUpViews() {
        mViewProvince = (WheelView) findViewById(R.id.id_province);
        mViewCity = (WheelView) findViewById(R.id.id_city);
        mViewDistrict = (WheelView) findViewById(R.id.id_district);
        mBtnConfirm = (Button) findViewById(R.id.btn_confirm);
    }

    private void setUpListener() {
        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);
        // 添加onclick事件
        mBtnConfirm.setOnClickListener(this);
    }

    private void setUpData() {
        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(AddressActivity.this, mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCities();
        updateAreas();
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        // TODO Auto-generated method stub
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
        }
        this.mCurrentDistrictName=this.mDistrictDatasMap.get(this.mCurrentCityName)[0];
        if (wheel == mViewProvince) {
            updateCities();
            this.mCurrentDistrictName=this.mDistrictDatasMap.get(this.mCurrentCityName)[0];
        } else if (wheel == mViewCity) {
            updateAreas();
            this.mCurrentDistrictName = this.mDistrictDatasMap.get(this.mCurrentCityName)[0];
        }
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);
        mCurrentDistrictName = areas[0];
        mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);

        if (areas == null) {
            areas = new String[] { "" };
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
        mViewDistrict.setCurrentItem(0);
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[] { "" };
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                showSelectedResult();

                //传递当前选中的数据
                Intent intent = new Intent();
                intent.putExtra("str", mCurrentProviceName+","+mCurrentCityName+","
                        +mCurrentDistrictName);  //给intent添加额外数据，key为“str”,value为选中的地址
                setResult(0, intent);  // 0表示成功

                //传递当前选中的数据
                Intent intents = new Intent();
                intents.putExtra("str", mCurrentProviceName+","+mCurrentCityName+","
                        +mCurrentDistrictName);  //给intent添加额外数据，key为“str”,value为选中的地址
                setResult(1, intents);  // 0表示成功

                //设置1秒后自动关闭地址选择页面
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        Utils.finish(AddressActivity.this); //执行
                    }
                };
                timer.schedule(task, 500 * 1);  //0.5秒后结束页面
                break;
            default:
                break;
        }
    }

    private void showSelectedResult() {
        Toast.makeText(AddressActivity.this, "当前选中:"+mCurrentProviceName+","+mCurrentCityName+","
                +mCurrentDistrictName+","+mCurrentZipCode, Toast.LENGTH_SHORT).show();
    }
}
