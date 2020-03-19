package com.aliyun.alink.devicesdk.app;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.aliyun.alink.devicesdk.demo.R;
import com.aliyun.alink.devicesdk.manager.IDemoCallback;
import com.aliyun.alink.devicesdk.manager.InitManager;
import com.aliyun.alink.dm.api.BaseInfo;
import com.aliyun.alink.dm.api.DeviceInfo;
import com.aliyun.alink.dm.model.ResponseModel;
import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import com.aliyun.alink.linksdk.tools.ThreadTools;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
// Demo 使用注意事项：
// 1.属性上报等与云端的操作 必须在初始化完成onInitDone之后执行；
// 2.初始化的时候请确保网络是OK的，如果初始化失败了，可以在网络连接再次初始化；
//
public class DemoApplication extends Application {
    private static final String TAG = "DemoApplication";


    public static boolean isInitDone = false;
    public static String productKey = null, deviceName = null, deviceSecret = null, productSecret = null, region=null;

    @Override
    public void onCreate() {
        super.onCreate();
        ALog.setLevel(ALog.LEVEL_DEBUG);
        // 从 raw 读取指定测试文件
        String testData = getFromRaw();
        // 解析数据
        getDeviceInfoFrom(testData);

        if (TextUtils.isEmpty(deviceSecret)) {
            tryGetFromSP();
        }
        if (TextUtils.isEmpty(productKey) ||
                (TextUtils.isEmpty(deviceSecret) && TextUtils.isEmpty(productSecret)) ||
                TextUtils.isEmpty(deviceName)) {
            throw new RuntimeException("DFileFormatError");
        }
        if (TextUtils.isEmpty(deviceSecret)) {
            InitManager.registerDevice(this, productKey, deviceName, productSecret, new IConnectSendListener() {
                @Override
                public void onResponse(ARequest aRequest, AResponse aResponse) {
                    Log.d(TAG, "onResponse() called with: aRequest = [" + aRequest + "], aResponse = [" + (aResponse == null ? "null" : aResponse.data) + "]");
                    if (aResponse != null && aResponse.data != null) {
                        // 解析云端返回的数据
                        ResponseModel<Map<String, String>> response = JSONObject.parseObject(aResponse.data.toString(),
                                new TypeReference<ResponseModel<Map<String, String>>>() {
                                }.getType());
                        if ("200".equals(response.code) && response.data != null && response.data.containsKey("deviceSecret") &&
                                !TextUtils.isEmpty(response.data.get("deviceSecret"))) {
                            deviceSecret = response.data.get("deviceSecret");
                            // getDeviceSecret success, to build connection.
                            // 持久化 deviceSecret 初始化建联的时候需要
                            // 用户需要按照实际场景持久化设备的三元组信息，用于后续的连接
                            SharedPreferences preferences = getSharedPreferences("deviceAuthInfo", 0);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("deviceId", productKey+deviceName);
                            editor.putString("deviceSecret", deviceSecret);
                            //提交当前数据
                            editor.commit();
                            connect();
                        }
                    }
                }

                @Override
                public void onFailure(ARequest aRequest, AError aError) {
                    Log.d(TAG, "onFailure() called with: aRequest = [" + aRequest + "], aError = [" + aError + "]");
                }
            });
        } else {
            connect();
        }

    }

    private void connect() {
        Log.d(TAG, "connect() called");
        // SDK初始化
        InitManager.init(this, productKey, deviceName, deviceSecret, productSecret, region, new IDemoCallback() {
            @Override
            public void onNotify(String s, String s1, AMessage aMessage) {
                Log.d(TAG, "onNotify() called with: s = [" + s + "], s1 = [" + s1 + "], aMessage = [" + aMessage + "]");
            }

            @Override
            public boolean shouldHandle(String s, String s1) {
                Log.d(TAG, "shouldHandle() called with: s = [" + s + "], s1 = [" + s1 + "]");
                return true;
            }

            @Override
            public void onConnectStateChange(String s, ConnectState connectState) {
                Log.d(TAG, "onConnectStateChange() called with: s = [" + s + "], connectState = [" + connectState + "]");
                if (connectState == ConnectState.CONNECTED) {
                    showToast("长链接已建联");
                } else if (connectState == ConnectState.CONNECTFAIL) {
                    showToast("长链接建联失败");
                } else if (connectState == ConnectState.DISCONNECTED) {
                    showToast("长链接已断连");
                }
            }

            @Override
            public void onError(AError aError) {
                Log.d(TAG, "onError() called with: aError = [" + aError + "]");
                showToast("初始化失败");
            }

            @Override
            public void onInitDone(Object data) {
                Log.d(TAG, "onInitDone() called with: data = [" + data + "]");
                showToast("初始化成功");
                isInitDone = true;
            }
        });
    }

    private void tryGetFromSP() {
        Log.d(TAG, "tryGetFromSP() called");
        SharedPreferences authInfo = getSharedPreferences("deviceAuthInfo", Activity.MODE_PRIVATE);
        String pkDn = authInfo.getString("deviceId", null);
        String ds = authInfo.getString("deviceSecret", null);
        if (pkDn != null && pkDn.equals(productKey + deviceName) && ds != null) {
            Log.d(TAG, "tryGetFromSP update ds from sp.");
            deviceSecret = ds;
        }
    }

    private void getDeviceInfoFrom(String testData) {  //三元组连接
        Log.d(TAG, "getDeviceInfoFrom() called with: testData = [" + testData + "]");
        try {
            Gson mGson = new Gson();
            DeviceInfoData deviceInfoData = mGson.fromJson(testData, DeviceInfoData.class);
            if (deviceInfoData == null) {
                Log.e(TAG, "getDeviceInfoFrom: file format error.");
                throw new RuntimeException("FileFormatError");
            }
            Log.d(TAG, "getDeviceInfoFrom deviceInfoData=" + deviceInfoData);
            if (!TextUtils.isEmpty(deviceInfoData.productKey) &&
                    !TextUtils.isEmpty(deviceInfoData.deviceSecret) &&
                    !TextUtils.isEmpty(deviceInfoData.deviceName)) {
                productKey = deviceInfoData.productKey;
                deviceName = deviceInfoData.deviceName;
                deviceSecret = deviceInfoData.deviceSecret;
                region = deviceInfoData.region;
            } else {
                throw new RuntimeException("DFileFormatError");
            }

        } catch (Exception e) {
            Log.e(TAG, "getDeviceInfoFrom: e", e);
        }
    }

    private boolean checkValid(BaseInfo baseInfo) {
        if (baseInfo == null) {
            return false;
        }
        if (TextUtils.isEmpty(baseInfo.productKey) || TextUtils.isEmpty(baseInfo.deviceName)) {
            return false;
        }
        if (baseInfo instanceof DeviceInfo) {
            if (TextUtils.isEmpty(((DeviceInfo) baseInfo).productSecret) && TextUtils.isEmpty(((DeviceInfo) baseInfo).deviceSecret)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 注意：该场景只适合于设备未激活的场景
     * 验证一型一密 需要以下步骤：
     * 1.云端创建产品，开启产品的动态注册功能；
     * 2.创建一个设备，在文件中(raw/deviceinfo)填写改设备信息 productKey，deviceName， productSecret;
     * 3.通过这三个信息可以去云端动态拿到deviceSecret，并建立长连接；
     *
     * @return
     */
    public String getFromRaw() {
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        try {
            inputReader = new InputStreamReader(getResources().openRawResource(R.raw.deviceinfo));
            bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufReader != null) {
                    bufReader.close();
                }
                if (inputReader != null){
                    inputReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    private void showToast(final String message) {
        ThreadTools.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(DemoApplication.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
