package com.example.gpsinformation;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tvLocation;
    private TextView tvAddress;
    private Geocoder geocoder;
    private List<Address> addressList;
    private StringBuilder sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        initData();
        //tvAddress.setText("当前位置");
    }

    private void initData() {
        tvLocation.setText("当前经纬度:initData");
        tvAddress.setText("当前位置:initData");

        // 获取经纬度坐标
        // 1 获取位置管理者对象
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // 2 通过lm获得经纬调度坐标
        // (参数： provider（定位方式 提供者 通过 LocationManager静态调用），
        // minTime（获取经纬度间隔的最小时间 时时刻刻获得传参数0），
        // minDistance（移动的最小间距 时时刻刻传0），LocationListener（监听）)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            tvAddress.setText("没有权限");
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                // 获取经纬度主要方法
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                tvLocation.setText("latitude"+latitude+"  "+"longitude"+longitude);
                sb = new StringBuilder();
                geocoder = new Geocoder(MainActivity.this);
                addressList = new ArrayList<Address>();

                try {
                    // 返回集合对象泛型address
                    addressList= geocoder.getFromLocation(latitude,longitude,1);

                    if (addressList.size() > 0) {
                        Address address = addressList.get(0);
                        for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                            sb.append(address.getAddressLine(i)).append("\n");
                        }
                        sb.append(address.getFeatureName());//周边地址
                    }
                    tvAddress.setText("当前位置"+sb.toString());
                    ;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                Log.d("状态发生改变监听","状态发生改变监听");
                //状态发生改变监听
            }
            @Override
            public void onProviderEnabled(String s) {
                Log.d("GPS 开启的事件监听","GPS 开启的事件监听");
                // GPS 开启的事件监听
            }
            @Override
            public void onProviderDisabled(String s) {
                Log.d("GPS 关闭的事件监听","GPS 关闭的事件监听");
                // GPS 关闭的事件监听
            }
        });
    }

    private void initUi() {
        tvLocation = (TextView)findViewById(R.id.tv_location);
        tvAddress = (TextView)findViewById(R.id.tv_address);
    }
}