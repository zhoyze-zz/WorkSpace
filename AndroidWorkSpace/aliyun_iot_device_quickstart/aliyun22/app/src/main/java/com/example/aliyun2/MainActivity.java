package com.example.aliyun2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements myILinkKitConnectListener.listenerCallBack {

    private Double Longitude;
    private Double Latitude;
    private Double Altitude;
    private int CoordinateSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IOTUtil iotUtil = new IOTUtil(this,this);
    }

    @Override
    public void doSomeThingWhenError() {

    }

    @Override
    public void doSomethingWhenSuccess() {
        IOTUtil.doReportByApi(Longitude,Latitude,Altitude,CoordinateSystem);
    }
}
