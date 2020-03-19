package com.aliyun.iot.demo;

import java.util.HashMap;
import java.util.Map;

import com.aliyun.alink.dm.api.DeviceInfo;
import com.aliyun.alink.dm.api.InitResult;
import com.aliyun.alink.linkkit.api.ILinkKitConnectListener;
import com.aliyun.alink.linkkit.api.IoTMqttClientConfig;
import com.aliyun.alink.linkkit.api.LinkKit;
import com.aliyun.alink.linkkit.api.LinkKitInitParams;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttPublishRequest;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tools.AError;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;

public class LinkkitPubClient {

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
                //设置pub消息的topic和内容
                MqttPublishRequest request = new MqttPublishRequest();
                request.topic = "/" + productKey + "/" + deviceName + "/user/devmsg";
                request.qos = 0;
                request.payloadObj = "{\"temperature\":35.0, \"time\":\"sometime\"}";
                //发送消息并设置成功以后的回调
                LinkKit.getInstance().publish(request, new IConnectSendListener() {
                    @Override
                    public void onResponse(ARequest aRequest, AResponse aResponse) {
                        System.out.println("onResponse:" + aResponse.getData());
                    }

                    @Override
                    public void onFailure(ARequest aRequest, AError aError) {
                        System.out.println("onFailure:" + aError.getCode() + aError.getMsg());
                    }
                });
            }
        });
    }
}

