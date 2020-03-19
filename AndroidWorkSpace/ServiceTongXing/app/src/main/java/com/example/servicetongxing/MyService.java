package com.example.servicetongxing;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private int count=0;//充当服务的状态
    private boolean stop;//确定是否停止count计数
    //定义onBinder方法返回的对象
    private MyBinder binder = new MyBinder();

    public class MyBinder extends Binder {
        public int getCount() {
            //获取Service的运行状态
            return count;
        }
    }

    public IBinder onBind(Intent intent) {
        Log.v("MyService", "绑定服务成功");
        return binder;
    }

    public void onCreate() {
        super.onCreate();
        Log.v("MyService","服务创建成功");
        //启动一条线程，动态修改count的状态值
        new Thread(){
            @Override
            public void run() {
                while(!stop){
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        }.start();
    }

    public boolean onUnbind(Intent intent) {
        Log.v("MyService","服务解除绑定");
        return true;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.stop=true;
        Log.v("MyService","服务解除");
    }
}
