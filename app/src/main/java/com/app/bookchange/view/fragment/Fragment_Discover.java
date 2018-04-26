package com.app.bookchange.view.fragment;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.app.bookchange.R;
import com.app.bookchange.bean.LocationBean;
import com.app.bookchange.bean.MyBook;
import com.app.bookchange.common.Utils;
import com.app.bookchange.view.InfoWindowHolder;
import com.app.bookchange.view.activity.BookfriendActivity;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;


import static android.content.Context.SENSOR_SERVICE;

/*  发现页面
 */

public class Fragment_Discover extends Fragment implements OnClickListener,SensorEventListener {
    private Activity ctx;
    private View layout;
    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private MyLocationConfiguration.LocationMode mCurrentMode;
    BitmapDescriptor mCurrentMarker;
    private static final int accuracyCircleFillColor = 0xAAFFFF88;
    private static final int accuracyCircleStrokeColor = 0xAA00FF00;
    private SensorManager mSensorManager;
    private Double lastX = 0.0;
    private int mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccracy;
    String locationDescribe;
    String province,city,district,street;

    private String objectId;

    MapView mMapView;
    BaiduMap mBaiduMap;

    // UI相关
    RadioGroup.OnCheckedChangeListener radioButtonListener;
    Button requestLocButton;
    boolean isFirstLoc = true; // 是否首次定位
    private MyLocationData locData;
    private float direction;

    private Marker mMarker;
    private LinearLayout baidumap_infowindow;
    private InfoWindow mInfoWindow;

    //自定义Marker图标
    BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_marker);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMsg();
        if (layout == null) {
            ctx = this.getActivity();
            layout = ctx.getLayoutInflater().inflate(R.layout.discover_fragment, null);
            requestLocButton = (Button) layout.findViewById(R.id.button1);
            baidumap_infowindow = (LinearLayout) LayoutInflater.from (getActivity ()).inflate (R.layout.baidumap_infowindow, null);
            // 构建Marker图标
            mSensorManager = (SensorManager) ctx.getSystemService(SENSOR_SERVICE);//获取传感器管理服务
            mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;
            //注册监听函数
            //requestLocButton.setText("普通");
            View.OnClickListener btnClickListener = new View.OnClickListener() {
                public void onClick(View v) {
                    switch (mCurrentMode) {
                        case COMPASS:
                            //requestLocButton.setText("跟随");
                            mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;
                            mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
                                    mCurrentMode, true, mCurrentMarker));
                            MapStatus.Builder builder = new MapStatus.Builder();
                            builder.overlook(0);
                            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                            break;
                        case FOLLOWING:
                            //requestLocButton.setText("罗盘");
                            mCurrentMode = MyLocationConfiguration.LocationMode.COMPASS;
                            mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
                                    mCurrentMode, true, mCurrentMarker));
                            break;
                        default:
                            break;
                    }
                }
            };
            requestLocButton.setOnClickListener(btnClickListener);

            // 地图初始化
            mMapView = (MapView)layout.findViewById(R.id.bmapView);
            mBaiduMap = mMapView.getMap();
            // 开启定位图层
            mBaiduMap.setMyLocationEnabled(true);
            // 定位初始化
            mLocClient = new LocationClient(ctx.getApplicationContext());
            mLocClient.registerLocationListener((BDAbstractLocationListener) myListener);
            LocationClientOption option = new LocationClientOption();
            option.setOpenGps(true); // 打开gps
            option.setCoorType("bd09ll"); // 设置坐标类型
            option.setScanSpan(1000);
            option.setIsNeedLocationDescribe(true);
            option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);

            option.setLocationNotify(true);
            option.setIsNeedAddress(true);
            option.setIgnoreKillProcess(false);

            option.SetIgnoreCacheException(false);
            option.setWifiCacheTimeOut(5*60*1000);

            option.setEnableSimulateGps(false);

            mLocClient.setLocOption(option);
            mLocClient.start();

            //Marker点击事件
            mBaiduMap.setOnMarkerClickListener (new BaiduMap.OnMarkerClickListener() {

                @Override
                public boolean onMarkerClick(final Marker marker){
                    //获得marker中的数据
                    LocationBean bean = (LocationBean) marker.getExtraInfo ().get ("marker");
                    InfoWindow.OnInfoWindowClickListener listener = null;
                    createInfoWindow(baidumap_infowindow, bean);
                    //将marker所在的经纬度的信息转化成屏幕上的坐标
                    final LatLng ll = marker.getPosition();
                    listener = new InfoWindow.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick() {
                            Utils.start_Activity(getActivity(),BookfriendActivity.class);
//                            Intent intent = new Intent(ctx, BookfriendActivity.this);
//                            Bundle bundle = marker.getExtraInfo();
//                            int id = bundle.getInt("id");
//                            intent.putExtra("id", id);
//                            openActivity(intent, TalentDetailActivity.class);
                        }
                    };

                    mInfoWindow = new InfoWindow (BitmapDescriptorFactory.fromView (baidumap_infowindow), ll, -47, listener);
                    //显示InfoWindow
                    mBaiduMap.showInfoWindow(mInfoWindow);

                    return true;
                }
            });
            //点击地图隐藏infowindow
            mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
                @Override
                public boolean onMapPoiClick(MapPoi arg0) {
                    return false;
                }
                @Override
                public void onMapClick(LatLng arg0) {
                    mBaiduMap.hideInfoWindow();
                }
            });
        }
        return layout;
    }

    private void createInfoWindow(LinearLayout baidumap_infowindow, LocationBean bean){

        InfoWindowHolder holder = null;
        if(baidumap_infowindow.getTag () == null){
            holder = new InfoWindowHolder ();

            holder.tv_location = (TextView) baidumap_infowindow.findViewById (R.id.tv_location);
            holder.tv_bookname = (TextView) baidumap_infowindow.findViewById (R.id.tv_bookname);
            holder.tv_author = (TextView) baidumap_infowindow.findViewById (R.id.tv_author);
            holder.btn_borrowbook = (Button) baidumap_infowindow.findViewById (R.id.btn_borrowbook);
            baidumap_infowindow.setTag (holder);
        }

        holder = (InfoWindowHolder) baidumap_infowindow.getTag ();

        holder.tv_location.setText ("我的位置："+ bean.getMyloctionmessage ());
        holder.tv_bookname.setText ("书名：" + bean.getBook().getBookname());
        holder.tv_author.setText ("作者：" + bean.getBook().getBookautor());
    }


    private void showData(List<LocationBean> list){
        mBaiduMap.clear ();
        addMarker (list);
    }

    private void addMarker(List<LocationBean> list){
        for ( int i = 0 ; i < list.size () ; i++ ) {
            LocationBean bean = list.get (i);
            // 经度,纬度
            double latitude = bean.getLat();
            double longitude = bean.getLng();

            if (longitude > 0 && latitude > 0) {
                // 定义Maker坐标点
                LatLng ll = new LatLng (latitude,longitude);
                // 构建MarkerOption，用于在地图上添加Marker
                // 设置marker的位置,图标，所在等级
                MarkerOptions options = new MarkerOptions ().position (ll).icon (bitmap).zIndex(9);
                // 在地图上添加Marker，并显示
                Marker marker = (Marker) mBaiduMap.addOverlay (options);
                // 将信息保存
                Bundle bundle = new Bundle ();
                bundle.putSerializable ("marker", bean);
                marker.setExtraInfo (bundle);

                if (i == 0) {
                    MapStatus.Builder builder = new MapStatus.Builder();
                    builder.target(ll).zoom(16);
                    mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                }
            }
        }
    }

    @Override
    public void onStart(){
        super.onStart ();
        getAround();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double x = sensorEvent.values[SensorManager.DATA_X];
        if (Math.abs(x - lastX) > 1.0) {
            mCurrentDirection = (int) x;
            locData = new MyLocationData.Builder()
                    .accuracy(mCurrentAccracy)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(mCurrentLat)
                    .longitude(mCurrentLon).build();
            mBaiduMap.setMyLocationData(locData);
        }
        lastX = x;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner extends BDAbstractLocationListener{

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            mCurrentLat = location.getLatitude();   //获取纬度
            mCurrentLon = location.getLongitude();     //获取经度信息
            mCurrentAccracy = location.getRadius();     //获取定位精度，默认值为0.0f
            province = location.getProvince();    //获取省份
            city = location.getCity();    //获取城市
            district = location.getDistrict();    //获取区县
            street = location.getStreet();    //获取街道信息
            locationDescribe = location.getLocationDescribe();
            //获取位置描述信息
            /*加载地图页面时启动BDAbstractLocationListener，拿到所有的位置信息
                    *拿到全局变量中的UserID
                    * 启用DAO层的UserDAO,将位置信息及userID存入location表中
                     */
            String mylocationmsg = city+district+locationDescribe;
            setLocation(mCurrentLon,mCurrentLat,mylocationmsg);
            locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    protected void centerToLocation(BDLocation location, int duration) {
        LatLng cenpt = new LatLng(location.getLatitude(),location.getLongitude());
        //定义地图状态,地图缩放级别 3~19
        MapStatus newMapStatus = new MapStatus.Builder().target(cenpt).zoom(16).build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(newMapStatus);
        //改变地图状态
        mBaiduMap.animateMapStatus(mMapStatusUpdate, duration);
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
        //为系统的方向传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onStop() {
        //取消注册传感器监听
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

    private void setLocation(final double lng, final double lat, final String mylocationmsg){

        BmobQuery<LocationBean> query=new BmobQuery<LocationBean>();
        query.addWhereEqualTo("userid",objectId);
        Log.d("SET",objectId);
        query.findObjects(new FindListener<LocationBean>() {
            @Override
            public void done(List<LocationBean> list, BmobException e) {
                if (e==null){
                    for (LocationBean locationBean:list){
                        locationBean.setLng(lng);
                        locationBean.setLat(lat);
                        locationBean.setMyloctionmessage(mylocationmsg);
                        locationBean.update(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {

                            }
                        });
                    }
                }

            }
        });
    }

    private void getAround(){
       BmobQuery<LocationBean> queryone=new BmobQuery<LocationBean>();
        queryone.setLimit(25);
        queryone.findObjects(new FindListener<LocationBean>() {
            @Override
            public void done(List<LocationBean> locationBeans, BmobException e) {

                if (e==null) {
                    for (LocationBean locationBean : locationBeans) {
                        queryBook(locationBean);
                    }
                    showData(locationBeans);
                }else {
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }


    private void queryBook(final LocationBean locationBean){
        BmobQuery<MyBook> querytwo=new BmobQuery<MyBook>();
        querytwo.addWhereEqualTo("accountId",locationBean.getUserid());
        querytwo.findObjects(new FindListener<MyBook>() {
            @Override
            public void done(List<MyBook> myBooks, BmobException e) {
                if (e==null) {
                    for (MyBook myBook : myBooks) {
                        locationBean.setBook(myBook);
                        Log.d("set","setbook成功！");

                    }
                }
            }
        });

    }


    private void getMsg(){
        Bundle bundle=getArguments();
        if (bundle!=null){
            objectId=bundle.getString("objectId");
        }

        Log.d("Fragment_Discover","--------------getMsg-------------"
                +objectId+"------------");

    }
}
