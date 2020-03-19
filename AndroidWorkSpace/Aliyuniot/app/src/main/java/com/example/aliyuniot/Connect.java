package com.example.aliyuniot;


import android.content.Context;
import android.util.Log;

import com.aliyun.alink.dm.api.DeviceInfo;
import com.aliyun.alink.h2.stream.api.AError;
import com.aliyun.alink.linkkit.api.ILinkKitConnectListener;
import com.aliyun.alink.linkkit.api.IoTMqttClientConfig;
import com.aliyun.alink.linkkit.api.LinkKit;
import com.aliyun.alink.linkkit.api.LinkKitInitParams;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.listener.IPublishResourceListener;

import java.util.HashMap;
import java.util.Map;

public class Connect {
    Context context ;
    String ProductKey ="a1LjRzS9OhU";
    String DeviceName = "aliyuniot";
    String DeviceSecret ="inRpT78eLdlU6FKjzZL7GYVLdh3X5jCG";

    public  Connect(Context context){  //构造函数，将context的值传过来
        this.context = context;
    }

    Double wendu = 0.0;


    public void report(Double temp){
        Map<String, ValueWrapper> reportData  = new HashMap<>();
        // identifier 是云端定义的属性的唯一标识，valueWrapper是属性的值
        reportData.put("CurrentTemperature", new ValueWrapper.DoubleValueWrapper(temp));  // 参考示例，更多使用可参考demo
        LinkKit.getInstance().getDeviceThing().thingPropertyPost(reportData, new IPublishResourceListener() {
            @Override
            public void onSuccess(String resID, Object o) {
                Log.v("report","属性上报成功 resID 设备属性对应的唯一标识");
                // 属性上报成功 resID 设备属性对应的唯一标识
            }
            @Override
            public void onError(String s, com.aliyun.alink.linksdk.tools.AError aError) {
                Log.v("report","属性上报失败 ");
            }// 属性上报失败
        });
    }
    
    public void connect(){
        /**
         * 设置设备三元组信息
         */
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.productKey = ProductKey;// 产品类型
        deviceInfo.deviceName = DeviceName;// 设备名称
        deviceInfo.deviceSecret = DeviceSecret;// 设备密钥
/**
 * 设置设备当前的初始状态值，属性需要和云端创建的物模型属性一致
 * 如果这里什么属性都不填，物模型就没有当前设备相关属性的初始值。
 * 用户调用物模型上报接口之后，物模型会有相关数据缓存。
 */
        Map<String, ValueWrapper> propertyValues = new HashMap<>();
// 示例
// propertyValues.put("LightSwitch", new ValueWrapper.BooleanValueWrapper(0));
        IoTMqttClientConfig clientConfig = new IoTMqttClientConfig(ProductKey, DeviceName, DeviceSecret);
        LinkKitInitParams params = new LinkKitInitParams();
        params.deviceInfo = deviceInfo;
        params.propertyValues = propertyValues;
        params.mqttClientConfig = clientConfig;
/**
 * 设备初始化建联
 * onError 初始化建联失败，需要用户重试初始化。如因网络问题导致初始化失败。
 * onInitDone 初始化成功
 */
        LinkKit.getInstance().init(context, params, new ILinkKitConnectListener() {
            @Override
            public void onError(com.aliyun.alink.linksdk.tools.AError aError) {
                Log.d("connect：","failed");
            }

            @Override
            public void onInitDone(Object data) {
                Log.d("connect：","success"); // 初始化成功 data 作为预留参数
                // 设备上报
                report(wendu);
                report(wendu+1);
                report(wendu+2);

            }
        });
    }
}
