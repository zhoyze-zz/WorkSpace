package com.aliyun.iot.demo;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.iot.model.v20180120.PubRequest;
import com.aliyuncs.iot.model.v20180120.PubResponse;
import com.aliyuncs.profile.DefaultProfile;
import org.eclipse.paho.client.mqttv3.internal.websocket.Base64;

public class PopPubServer {

    public static void main(String[] args) {
        String regionId = "您设备所处区域regionId";
        String accessKey = "您的阿里云accessKey";
        String accessSecret = "您的阿里云accessSecret";
        final String productKey = "您的产品productKey";
        final String deviceName = "您的设备名字deviceName";
        //设置client的参数
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKey, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        PubRequest request = new PubRequest();
        request.setQos(0);
        //设置发布消息的topic
        request.setTopicFullName("/" + productKey + "/" + deviceName + "/user/cloudmsg");
        request.setProductKey(productKey);
        //设置消息的内容，一定要用base64编码，否则乱码
        request.setMessageContent(Base64.encode("{\"accuracy\":0.001,\"time\":now}"));
        try {
            PubResponse response = client.getAcsResponse(request);
            System.out.println("pub success?:" + response.getSuccess());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
