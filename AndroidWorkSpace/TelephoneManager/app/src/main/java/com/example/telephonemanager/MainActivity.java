package com.example.telephonemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView show_list;
    private ArrayList<String> status_values = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show_list = (ListView) findViewById(R.id.show_mes);
        //获取系统的TelephonyManager对象
        TelephonyManager telManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //获取设备编号
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        status_values.add(telManager.getDeviceId());
        Log.v("MainActivity",telManager.getDeviceId()+"1111111111111111");
        //获取系统平台的版本
        status_values.add(telManager.getDeviceSoftwareVersion() != null ? telManager.getDeviceSoftwareVersion() : "未知");
        //获取网络运营商代号
        status_values.add(telManager.getNetworkOperator());
        //获取SIM卡的国别
        status_values.add(telManager.getSimCountryIso());
        //获取SIM卡的序列号
        status_values.add(telManager.getSimSerialNumber());
        show_list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, status_values));
    }
}
