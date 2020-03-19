package com.aliyun.alink.devicesdk.manager;

import android.content.Context;
import android.util.Log;

import com.aliyun.alink.dm.api.DeviceInfo;
import com.aliyun.alink.dm.api.IoTApiClientConfig;
import com.aliyun.alink.linkkit.api.ILinkKitConnectListener;
import com.aliyun.alink.linkkit.api.IoTDMConfig;
import com.aliyun.alink.linkkit.api.IoTMqttClientConfig;
import com.aliyun.alink.linkkit.api.LinkKit;
import com.aliyun.alink.linkkit.api.LinkKitInitParams;
import com.aliyun.alink.linksdk.cmp.connect.hubapi.HubApiRequest;
import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tools.AError;

import java.util.HashMap;
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

public class InitManager {
    private static final String TAG = "InitManager";

    /**
     * 如果需要动态注册设备获取设备的deviceSecret， 可以参考本接口实现。
     * 动态注册条件检测：
     * 1.云端开启该设备动态注册功能；
     * 2.首先在云端创建 pk，dn；
     *
     * @param context       上下文
     * @param productKey    产品类型
     * @param deviceName    设备名称 需要现在云端创建
     * @param productSecret 产品密钥
     * @param listener      密钥请求回调
     */
    public static void registerDevice(Context context, String productKey, String deviceName, String productSecret, IConnectSendListener listener) {
        DeviceInfo myDeviceInfo = new DeviceInfo();
        myDeviceInfo.productKey = productKey;
        myDeviceInfo.deviceName = deviceName;
        myDeviceInfo.productSecret = productSecret;
        LinkKitInitParams params = new LinkKitInitParams();
        params.connectConfig = new IoTApiClientConfig();
        // 如果明确需要切换域名，可以设置 connectConfig 中 domain 的值；
        params.deviceInfo = myDeviceInfo;
        HubApiRequest hubApiRequest = new HubApiRequest();
        hubApiRequest.path = "/auth/register/device";
        // 调用动态注册接口
        LinkKit.getInstance().deviceRegister(context, params, hubApiRequest, listener);
    }

    /**
     * Android 设备端 SDK 初始化示例代码
     *
     * @param context       上下文
     * @param productKey    产品类型
     * @param deviceName    设备名称
     * @param deviceSecret  设备密钥
     * @param productSecret 产品密钥
     * @param region        区域
     * @param callback      初始化建联结果回调
     */
    public static void init(Context context, String productKey, String deviceName, String deviceSecret, String productSecret, String region, final IDemoCallback callback) {
        // 构造三元组信息对象
        DeviceInfo deviceInfo = new DeviceInfo();
        // 产品类型
        deviceInfo.productKey = productKey;
        // 设备名称
        deviceInfo.deviceName = deviceName;
        // 设备密钥
        deviceInfo.deviceSecret = deviceSecret;
        // 产品密钥
        deviceInfo.productSecret = productSecret;
        //  全局默认域名
        IoTApiClientConfig userData = new IoTApiClientConfig();
        // 设备的一些初始化属性，可以根据云端的注册的属性来设置。
        Map<String, ValueWrapper> propertyValues = new HashMap<>();

//        propertyValues.put("LightSwitch", new ValueWrapper.BooleanValueWrapper(0));
        LinkKitInitParams params = new LinkKitInitParams();
        params.deviceInfo = deviceInfo;
        params.propertyValues = propertyValues;
        params.connectConfig = userData;

        IoTMqttClientConfig clientConfig = new IoTMqttClientConfig(productKey, deviceName, deviceSecret);
        // 慎用 设置 mqtt 请求域名，默认".iot-as-mqtt.cn-shanghai.aliyuncs.com:1883" ,如果无具体的业务需求，请不要设置。
        //clientConfig.channelHost = "xxx";
        clientConfig.channelHost = productKey + ".iot-as-mqtt." + region + ".aliyuncs.com:1883";
        params.mqttClientConfig = clientConfig;

        IoTDMConfig ioTDMConfig = new IoTDMConfig();
        // 按照实际情况是否开启
        ioTDMConfig.enableNotify = false;

        params.ioTDMConfig = ioTDMConfig;
        // 注册下行监听
        // 包括长连接的状态
        // 云端下行的数据
        LinkKit.getInstance().registerOnPushListener(new IConnectNotifyListener() {
            @Override
            public void onNotify(String s, String s1, AMessage aMessage) {
                Log.d(TAG, "onNotify() called with: s = [" + s + "], s1 = [" + s1 + "], aMessage = [" + aMessage + "]");
                callback.onNotify(s, s1, aMessage);
            }

            @Override
            public boolean shouldHandle(String s, String s1) {
                Log.d(TAG, "shouldHandle() called with: s = [" + s + "], s1 = [" + s1 + "]");
                return callback.shouldHandle(s, s1);
            }

            @Override
            public void onConnectStateChange(String s, ConnectState connectState) {
                Log.d(TAG, "onConnectStateChange() called with: s = [" + s + "], connectState = [" + connectState + "]");
                callback.onConnectStateChange(s, connectState);
            }
        });
        // 设备初始化建联
        LinkKit.getInstance().init(context, params, new ILinkKitConnectListener() {
            @Override
            public void onError(AError error) {
                Log.d(TAG, "onError() called with: error = [" + error + "]");
                callback.onError(error);
            }

            @Override
            public void onInitDone(Object data) {
                Log.d(TAG, "onInitDone() called with: data = [" + data + "]");
                callback.onInitDone(data);
            }
        });
    }
}
