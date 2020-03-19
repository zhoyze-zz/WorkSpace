package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;


public class test_bindService extends Service {
    class MyBinder extends Binder{
        public void test(){
            custom_metod();   //自定义的方法
        }
    }    //创建服务的代理，调用服务中的方法
    private void custom_metod() {
        Log.v("test_bindService","自定义的方法custom_metod()");
    }  //自定义方法

    public void onCreate() {
        Log.v("test_bindService","创建服务onCreate()");
        super.onCreate();   //第一次创建服务时执行的方法。
    }
    @Override
    public IBinder onBind(Intent intent) { //使用bindService()方式启动服务调用的方法。
        Log.v("test_bindService","绑定服务onBind()");
        return new MyBinder();
    }
    @Override
    public boolean onUnbind(Intent intent) {  //解除绑定时调用的方法。
        Log.v("test_bindService","解除绑定onUnbind()");
        return super.onUnbind(intent);
    }
}
