package com.example.broadcastnew;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

//自定义广播接收器
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //收到广播时，显示一个通知
        Log.v("MyReciver", "广播接收成功");
        Toast.makeText(context,"广播接收成功",Toast.LENGTH_SHORT).show();
    }

}