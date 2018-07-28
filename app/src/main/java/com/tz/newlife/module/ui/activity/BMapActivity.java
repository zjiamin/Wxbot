package com.tz.newlife.module.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.tz.newlife.R;

import java.util.ArrayList;
import java.util.List;

public class BMapActivity extends AppCompatActivity {
    private Toolbar toolbar;

    private TextView posiiton_textView;

    public LocationClient mLocationClient;

    private List<String> permissionList = new ArrayList<>();

    private MapView mapView = null;

    private BaiduMap baiduMap;

    private boolean isFirstLocate = true;

    private BitmapDescriptor mCurrentMarker;

    private MyLocationConfiguration.LocationMode mCurrentMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());

        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_amap);

        mapView = (MapView) findViewById(R.id.amap_view);

        baiduMap = mapView.getMap();

        baiduMap.setMyLocationEnabled(true);

        initView();

        initPermissions();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        //虽然这个ActionBar是由ToolBar来具体实现的，但是还是要用下面的方法来找到ActionBar的实例
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowTitleEnabled(false);//去掉toolbar左侧Title
        }

        posiiton_textView = (TextView) findViewById(R.id.tv_location);


    }

    private void navigateTo(BDLocation location) {
        if (isFirstLocate) {
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }

        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);

//        mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;//定位跟随态
        mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;   //默认为 LocationMode.NORMAL 普通态
//        mCurrentMode = MyLocationConfiguration.LocationMode.COMPASS;  //定位罗盘态
        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.mipmap.iconmonstr_blue_location);

        baiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
                mCurrentMode, true, mCurrentMarker));

        //地图单击事件
        baiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {

            @Override
            public boolean onMapPoiClick(MapPoi arg0) {
                // TODO Auto-generated method stub
                //先清除图层
                baiduMap.clear();
                // 定义Maker坐标点
                LatLng point = new LatLng(arg0.getPosition().latitude, arg0.getPosition().longitude);
                // 构建MarkerOption，用于在地图上添加Marker
                BitmapDescriptor marker_icon = BitmapDescriptorFactory
                        .fromResource(R.mipmap.iconmonstr_red_location);
                MarkerOptions options = new MarkerOptions().position(point)
                        .icon(marker_icon).title(arg0.getName());
                PopupWindow mPop = new PopupWindow();

                mPop.isOutsideTouchable();

                // 在地图上添加Marker，并显示
                baiduMap.addOverlay(options);
                return false;
            }

            @Override
            public void onMapClick(LatLng arg0) {
                // TODO Auto-generated method stub
                Toast.makeText(BMapActivity.this, "单击了地图别的位置",
                        Toast.LENGTH_SHORT).show();
            }
        });

//        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);
//
//        baiduMap.setMyLocationConfiguration(config);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    private void initPermissions() {
        if (ContextCompat.checkSelfPermission(BMapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(BMapActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)  {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(BMapActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)  {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(BMapActivity.this, permissions, 1);
        } else {
            requestLocation();
        }
    }

    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setScanSpan(5000);//每5秒更新一下位置
        option.setIsNeedAddress(true);
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        mLocationClient.setLocOption(option);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();//一定记得在活动销毁的时候调用LocationClient的stop()方法来停止定位，
                                // 否则程序会一直在后台进行定位严重耗费手机电量
        mapView.onDestroy();

        baiduMap.setBaiduHeatMapEnabled(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "您需要同意所有权限才能使用定位",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误",
                            Toast.LENGTH_SHORT).show();
                }

                break;

            default:

        }
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
           /* StringBuilder currentPosition = new StringBuilder();
            currentPosition.append("纬度：").append(bdLocation.getLongitude()).append("\n");
            currentPosition.append("经经线：").append(bdLocation.getAltitude()).append("\n");
            currentPosition.append("定位方式：");
            currentPosition.append("国家：").append(bdLocation.getCountry()).append("\n");
            currentPosition.append("省：").append(bdLocation.getProvince()).append("\n");
            currentPosition.append("市：").append(bdLocation.getCity()).append("\n");
            currentPosition.append("区：").append(bdLocation.getDistrict()).append("\n");
            currentPosition.append("街道：").append(bdLocation.getStreet()).append("\n");
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                currentPosition.append("GPS");
            } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                currentPosition.append("网络");
            }
            posiiton_textView.setText(currentPosition);*/
           if (bdLocation.getLocType() == BDLocation.TypeGpsLocation
                   || bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
               navigateTo(bdLocation);
           }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //HomeAsUp的id永远都是android.R.id.home
                finish();
                break;

            default:
        }
        return true;
    }
}
