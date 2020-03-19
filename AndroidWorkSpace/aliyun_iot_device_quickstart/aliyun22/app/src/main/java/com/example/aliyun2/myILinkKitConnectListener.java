package com.example.aliyun2;

import com.aliyun.alink.linkkit.api.ILinkKitConnectListener;
import com.aliyun.alink.linksdk.tools.AError;

public class myILinkKitConnectListener implements ILinkKitConnectListener {

    private listenerCallBack callBack;

    public myILinkKitConnectListener(listenerCallBack callBack) {
        this.callBack = callBack;
    }

    public interface listenerCallBack{
        void doSomeThingWhenError();
        void doSomethingWhenSuccess();
    };

    @Override
    public void onError(AError aError) {
        callBack.doSomeThingWhenError();
    }

    @Override
    public void onInitDone(Object o) {
        callBack.doSomethingWhenSuccess();
    }
}