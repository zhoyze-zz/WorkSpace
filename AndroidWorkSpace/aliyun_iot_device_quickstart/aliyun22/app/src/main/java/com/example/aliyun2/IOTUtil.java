package com.example.aliyun2;

import android.content.Context;
import android.util.Log;

import com.aliyun.alink.dm.api.DeviceInfo;
import com.aliyun.alink.dm.api.IThing;
import com.aliyun.alink.linkkit.api.ILinkKitConnectListener;
import com.aliyun.alink.linkkit.api.IoTMqttClientConfig;
import com.aliyun.alink.linkkit.api.LinkKit;
import com.aliyun.alink.linkkit.api.LinkKitInitParams;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttPublishRequest;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttSubscribeRequest;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSubscribeListener;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.devicemodel.Property;
import com.aliyun.alink.linksdk.tmp.listener.IPublishResourceListener;
import com.aliyun.alink.linksdk.tools.AError;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IOTUtil {

    private static final String TAG = "IOTUtil";
    private static final String DEVICE_NAME = "jklu";
    private static final String PRODUCT_KEY = "a1Wj84T68Wi";
    private static final String PRODUCT_SECRET = "sUA1rDetTDpgQImWYug5muEFeQnuPk5I";

    private DeviceInfo deviceInfo;
    private Context context;
    private myILinkKitConnectListener.listenerCallBack callBack;

    public IOTUtil(Context context,myILinkKitConnectListener.listenerCallBack callBack) {

        this.context = context;
        this.callBack = callBack;

        deviceInfo = new DeviceInfo();
        deviceInfo.deviceName = DEVICE_NAME;
        deviceInfo.productKey = PRODUCT_KEY;
        deviceInfo.deviceSecret = PRODUCT_SECRET;
    }

    public void connectIOT(){
        LinkKitInitParams params = initParams();
        LinkKit.getInstance().init(context, params, new myILinkKitConnectListener(callBack));
    }

    private LinkKitInitParams initParams(){
        Map<String, ValueWrapper> propertyValues = new HashMap<>();
        IoTMqttClientConfig clientConfig = new IoTMqttClientConfig(PRODUCT_KEY, DEVICE_NAME, PRODUCT_SECRET);
        LinkKitInitParams params = new LinkKitInitParams();
        params.deviceInfo = deviceInfo;
        params.propertyValues = propertyValues;
        params.mqttClientConfig = clientConfig;
        return params;
    }


//    public static void subscribe(){
//        final MqttSubscribeRequest subscribeRequest = new MqttSubscribeRequest();
//        // subTopic 替换成用户自己需要订阅的 topic
//        subscribeRequest.topic = "/a1sApeMfo2H/ssas/user/get";
//        subscribeRequest.isSubscribe = true;
//        LinkKit.getInstance().subscribe(subscribeRequest, new IConnectSubscribeListener() {
//            @Override
//            public void onSuccess() {
//                // 订阅成功
//                Log.d(TAG, "onSuccess: "+subscribeRequest.payloadObj);
//            }
//            @Override
//            public void onFailure(AError aError) {
//                // 订阅失败
//            }
//        });
//    }

    public static void doReportByApi(Double Longitude,Double Latitude,Double Altitude,int CoordinateSystem){
        Map<String, ValueWrapper> reportData  = new HashMap<>();
        // identifier 是云端定义的属性的唯一标识，valueWrapper是属性的值
        // reportData.put(identifier, valueWrapper);  // 参考示例，更多使用可参考demo
        Map<String, ValueWrapper> reportData2  = new HashMap<>();
        reportData2.put("Longitude",new ValueWrapper.DoubleValueWrapper( Longitude));
        reportData2.put("Latitude",new ValueWrapper.DoubleValueWrapper(Latitude));
        reportData2.put("Altitude",new ValueWrapper.DoubleValueWrapper(Altitude));
        reportData2.put("CoordinateSystem",new ValueWrapper.EnumValueWrapper(CoordinateSystem));
        reportData.put("GeoLocation",new ValueWrapper.StructValueWrapper(reportData2));
        LinkKit.getInstance().getDeviceThing().thingPropertyPost(reportData, new IPublishResourceListener() {
            @Override
            public void onSuccess(String resID, Object o) {
                // 属性上报成功 resID 设备属性对应的唯一标识
                Log.d(TAG, "API上报成功！");
            }
            @Override
            public void onError(String resId, AError aError) {
                // 属性上报失败
                Log.d(TAG, "API上报失败！");
            }
        });
    }
    public static void doReportByMQTT(){
        MqttPublishRequest request = new MqttPublishRequest();
        request.isRPC = false;
        request.topic = "/sys/"+PRODUCT_KEY+"/"+DEVICE_NAME+"/thing/event/property/post";
        // 设置 qos
        request.qos = 0;
        request.payloadObj = "{\"params\":{\"GeoLocation\":{\"CoordinateSystem\":2,\"Latitude\":67.91,\"Longitude\":16.4,\"Altitude\":8840.3}}}";
        LinkKit.getInstance().publish(request, new IConnectSendListener() {

            @Override
            public void onResponse(ARequest aRequest, AResponse aResponse) {
                Log.d(TAG, "MQTT 坐标上报成功！");
            }

            @Override
            public void onFailure(ARequest aRequest, AError aError) {
                // 发布失败
                Log.d(TAG, "MQTT 坐标上报失败！");
            }
        });
    }

//    public static List<Property> doReadAll(){
//
//        return null;
//    }
//    public static String doRead(String identifier){
//        Log.d(TAG, "doRead: "+LinkKit.getInstance().getDeviceThing().getAllPropertyValue());;
//        return null;
//    }
}
