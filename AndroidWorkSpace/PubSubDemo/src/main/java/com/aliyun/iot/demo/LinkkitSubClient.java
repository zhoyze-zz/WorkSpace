package com.aliyun.iot.demo;

import java.util.HashMap;
import java.util.Map;

import com.aliyun.alink.dm.api.DeviceInfo;
import com.aliyun.alink.dm.api.InitResult;
import com.aliyun.alink.linkkit.api.ILinkKitConnectListener;
import com.aliyun.alink.linkkit.api.IoTMqttClientConfig;
import com.aliyun.alink.linkkit.api.LinkKit;
import com.aliyun.alink.linkkit.api.LinkKitInitParams;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttSubscribeRequest;
import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSubscribeListener;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tools.AError;

public class LinkkitSubClient {

    public static void main(String[] args) {
        final String productKey = "您的产品productKey";
        final String deviceName = "您设备的名字deviceName";
        final String deviceSecret = "您的设备秘钥deviceSecret";
        final String region = "您设备所处区域regionId";

        LinkKitInitParams params = new LinkKitInitParams();
        //LinkKit底层是mqtt协议，设置mqtt的配置
        IoTMqttClientConfig config = new IoTMqttClientConfig();
        config.productKey = productKey;
        config.deviceName = deviceName;
        config.deviceSecret = deviceSecret;
        config.channelHost = productKey + ".iot-as-mqtt." + region + ".aliyuncs.com:1883";
        //设备的信息
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.productKey = productKey;
        deviceInfo.deviceName = deviceName;
        deviceInfo.deviceSecret = deviceSecret;
        //报备的设备初始状态，此处没有物模型初始状态
        Map<String, ValueWrapper> propertyValues = new HashMap<String, ValueWrapper>();

        params.mqttClientConfig = config;
        params.deviceInfo = deviceInfo;
        params.propertyValues = propertyValues;

        //连接并设置连接成功以后的回调函数
        LinkKit.getInstance().init(params, new ILinkKitConnectListener() {
            @Override
            public void onError(AError aError) {
                System.out.println("Init error:" + aError);
            }

            //初始化成功以后的回调
            @Override
            public void onInitDone(InitResult initResult) {
                //设置订阅的topic
                MqttSubscribeRequest request = new MqttSubscribeRequest();
                request.topic = "/" + productKey + "/" + deviceName + "/user/cloudmsg";
                request.isSubscribe = true;
                //发出订阅请求并设置订阅成功或者失败的回调函数
                LinkKit.getInstance().subscribe(request, new IConnectSubscribeListener() {
                    @Override
                    public void onSuccess() {
                        System.out.println("");
                    }

                    @Override
                    public void onFailure(AError aError) {

                    }
                });

                //设置订阅的下行消息到来时的回调函数
                IConnectNotifyListener notifyListener = new IConnectNotifyListener() {
                    //此处定义收到下行消息以后的回调函数。
                    @Override
                    public void onNotify(String connectId, String topic, AMessage aMessage) {
                        System.out.println(
                            "received message from " + topic + ":" + new String((byte[])aMessage.getData()));
                    }

                    @Override
                    public boolean shouldHandle(String s, String s1) {
                        return false;
                    }

                    @Override
                    public void onConnectStateChange(String s, ConnectState connectState) {

                    }
                };
                LinkKit.getInstance().registerOnNotifyListener(notifyListener);
            }
        });
    }
}