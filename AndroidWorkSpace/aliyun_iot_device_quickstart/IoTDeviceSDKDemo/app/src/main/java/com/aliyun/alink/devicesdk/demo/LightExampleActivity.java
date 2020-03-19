package com.aliyun.alink.devicesdk.demo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.aliyun.alink.dm.model.RequestModel;
import com.aliyun.alink.linkkit.api.LinkKit;
import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;
import com.aliyun.alink.linksdk.tmp.api.MapInputParams;
import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.devicemodel.Service;
import com.aliyun.alink.linksdk.tmp.listener.IPublishResourceListener;
import com.aliyun.alink.linksdk.tmp.listener.ITResRequestHandler;
import com.aliyun.alink.linksdk.tmp.listener.ITResResponseCallback;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Copyright (c) 2014-2016 Alibaba Group. All rights reserved.
 * License-Identifier: Apache-2.0
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

/**
 * 注意！！！！
 * 1.该示例只共快速接入使用，只适用于有 Status、Data属性的快速接入测试设备；
 * 2.真实设备可以参考 ControlPanelActivity 里面有数据上下行示例；
 */
public class LightExampleActivity extends BaseActivity {

    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener(this);


    private final static int REPORT_MSG = 0x100;

    TextView consoleTV;
    String consoleStr;
    private Button btnstart;
    private Button btnstop;

    private InternalHandler mHandler = new InternalHandler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        /////////////////////////////
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，设置定位模式，默认高精度
//        LocationMode.Hight_Accuracy：高精度；
//        LocationMode. Battery_Saving：低功耗；
//        LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("GCJ02");
        //可选，设置返回经纬度坐标类型，默认GCJ02
        //GCJ02：国测局坐标；
        //BD09ll：百度经纬度坐标；
        //BD09：百度墨卡托坐标；
        //海外地区定位，无需设置坐标类型，统一返回WGS84类型坐标

        option.setScanSpan(5000);
        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
        //可选，设置是否使用gps，默认false
        //使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(false);
        //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
        //可选，定位SDK内部是一个service，并放到了独立进程。
        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
        //可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setWifiCacheTimeOut(5*60*1000);
        //可选，V7.2版本新增能力
        //如果设置了该接口，首次启动定位时，会先判断当前Wi-Fi是否超出有效期，若超出有效期，会先重新扫描Wi-Fi，然后定位

        option.setEnableSimulateGps(false);
        //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        mLocationClient.setLocOption(option);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true
        /////////////////////////////


        super.onCreate(savedInstanceState);
        // 在初始化的时候可以设置 灯的初始状态，或者等初始化完成之后 上报一次设备所有属性的状态
        // 注意在调用云端接口之前确保初始化完成了
        setContentView(R.layout.activity_light_example);
        consoleTV = (TextView) findViewById(R.id.textview_console);
        btnstart=(Button)findViewById(R.id.start);
        btnstop=(Button)findViewById(R.id.stop);

        setDownStreamListener();
        showToast("已启动每5秒上报一次状态");
        log("已启动每5秒上报一次状态");

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLocationClient.start();
            }
        });

        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLocationClient.stop();
            }
        });
        mHandler.sendEmptyMessageDelayed(REPORT_MSG, 2 * 1000);

    }

    /**
     * 数据上行
     * 上报灯的状态
     */
    public void reportLocation(Double Longitude,Double Latitude,Double Altitude,int CoordinateSystem) {
        log("上报 Hello, World！");
        try {
            Map<String, ValueWrapper> reportData = new HashMap<>();
            Map<String, ValueWrapper> reportData2 = new HashMap<>();
            reportData2.put("Longitude", new ValueWrapper.DoubleValueWrapper(Longitude)); // 1开 0 关
            reportData2.put("Latitude", new ValueWrapper.DoubleValueWrapper(Latitude)); //hellow，word
            reportData2.put("Altitude", new ValueWrapper.DoubleValueWrapper(Altitude)); //hellow，word
            reportData2.put("CoordinateSystem",new ValueWrapper.EnumValueWrapper(CoordinateSystem));
            reportData.put("GeoLocation",new ValueWrapper.StructValueWrapper(reportData2));
            LinkKit.getInstance().getDeviceThing().thingPropertyPost(reportData, new IPublishResourceListener() {
                @Override
                public void onSuccess(String s, Object o) {
                    Log.d(TAG, "onSuccess() called with: s = [" + s + "], o = [" + o + "]");
                    showToast("设备上报状态成功");
                    log("上报 Hello, World! 成功。");
                }

                @Override
                public void onError(String s, AError aError) {
                    Log.d(TAG, "onError() called with: s = [" + s + "], aError = [" + aError + "]");
                    showToast("设备上报状态失败");
                    log("上报 Hello, World! 失败。");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDownStreamListener(){
        LinkKit.getInstance().registerOnPushListener(notifyListener);
    }

    private IConnectNotifyListener notifyListener = new IConnectNotifyListener() {
        @Override
        public void onNotify(String s, String s1, AMessage aMessage) {
            try {
                if (s1 != null && s1.contains("service/property/set")) {
                    String result = new String((byte[]) aMessage.data, "UTF-8");
                    RequestModel<String> receiveObj = JSONObject.parseObject(result, new TypeReference<RequestModel<String>>() {
                    }.getType());
                    log("Received a message: " + (receiveObj==null?"":receiveObj.params));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public boolean shouldHandle(String s, String s1) {
            Log.d(TAG, "shouldHandle() called with: s = [" + s + "], s1 = [" + s1 + "]");
            return true;
        }

        @Override
        public void onConnectStateChange(String s, ConnectState connectState) {
            Log.d(TAG, "onConnectStateChange() called with: s = [" + s + "], connectState = [" + connectState + "]");
        }
    };

    private void log(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ALog.d(TAG, "log(), " + str);
                if (TextUtils.isEmpty(str))
                    return;
                consoleStr = consoleStr + "\n \n" + (getTime()) + " " + str;
                consoleTV.setText(consoleStr);
            }
        });

    }

    private void clearMsg() {
        consoleStr = "";
        consoleTV.setText(consoleStr);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mHandler != null) {
            mHandler.removeMessages(REPORT_MSG);
            mHandler.removeCallbacksAndMessages(null);
            showToast("停止定时上报");
        }
        LinkKit.getInstance().unRegisterOnPushListener(notifyListener);
        clearMsg();
    }

    private class InternalHandler extends Handler {
        public InternalHandler() {
            super(Looper.getMainLooper());
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg == null) {
                return;
            }
            int what = msg.what;
            switch (what) {
                case REPORT_MSG:
                    reportLocation(myListener.getlatitude(),myListener.getlongitude(),myListener.getaltitude(),2);
                    mHandler.sendEmptyMessageDelayed(REPORT_MSG, 5*1000);
                    break;
            }

        }
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        private Context context;
        public MyLocationListener(Context context) {
            this.context = context;
        }

        public double latitude = 0.0;
        public double longitude = 0.0 ;
        public double  altitude = 0.0;

        public Double getlatitude(){
            return latitude;
        }
        public Double getlongitude(){
            return longitude;
        }
        public Double getaltitude(){
            return altitude;
        }
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            latitude = location.getLatitude();    //获取纬度信息
            longitude = location.getLongitude();    //获取经度信息
            altitude = location.getAltitude();
            float radius = location.getRadius();    //获取定位精度，默认值为0.0f

            String coorType = location.getCoorType();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准

            int errorCode = location.getLocType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            String addr = location.getAddrStr();    //获取详细地址信息
            String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息

            Toast.makeText(context, "经度："+latitude+"纬度："+longitude+"海拔"+altitude+"错误码"+errorCode, Toast.LENGTH_SHORT).show();
        }
    }

}
