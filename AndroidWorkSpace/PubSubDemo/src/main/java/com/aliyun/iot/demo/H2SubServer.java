package com.aliyun.iot.demo;

import com.aliyun.openservices.iot.api.Profile;
import com.aliyun.openservices.iot.api.message.MessageClientFactory;
import com.aliyun.openservices.iot.api.message.api.MessageClient;
import com.aliyun.openservices.iot.api.message.callback.MessageCallback;
import com.aliyun.openservices.iot.api.message.entity.Message;
import com.aliyun.openservices.iot.api.message.entity.MessageToken;

public class H2SubServer {

    public static void main(String[] args) {
        // 身份
        String accessKey = "您的阿里云accessKey";
        String accessSecret = "您的阿里云accessSecret";
        String uid = "您的阿里云uid";
        String regionId = "您设备所处区域regionId";
        String productKey = "您的产品productKey";
        String deviceName = "您的设备名字deviceName";
        String endPoint = "https://" + uid + ".iot-as-http2." + regionId + ".aliyuncs.com";
        // 连接配置
        Profile profile = Profile.getAccessKeyProfile(endPoint, regionId, accessKey, accessSecret);

        // 构造客户端
        MessageClient client = MessageClientFactory.messageClient(profile);

        MessageCallback messageCallback = new MessageCallback() {
            @Override
            public Action consume(MessageToken messageToken) {
                Message m = messageToken.getMessage();
                System.out.println(messageToken.getMessage());
                return MessageCallback.Action.CommitSuccess;
            }
        };
        client.setMessageListener("/" + productKey + "/" + deviceName + "/user/devmsg", messageCallback);

        // 数据接收
        client.connect(new MessageCallback() {
            @Override
            public Action consume(MessageToken messageToken) {
                Message m = messageToken.getMessage();
                System.out.println("\ntopic=" + m.getTopic());
                System.out.println("payload=" + new String(m.getPayload()));
                System.out.println("generateTime=" + m.getGenerateTime());
                // 此处标记CommitSuccess已消费，IoT平台会删除当前Message，否则会保留到过期时间
                return MessageCallback.Action.CommitSuccess;
            }
        });
    }
}