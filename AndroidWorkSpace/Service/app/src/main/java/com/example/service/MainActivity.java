package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity implements View.OnClickListener {
    private Button btn1, btn2, btn3;
    private test_bindService.MyBinder myBinder;
    private MyConn conn;  //创建conn对象，用于用于实现连接服务

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1://绑定服务
                if (conn == null) { //如果conn为空则
                    conn = new MyConn();//创建MyConn类，用于实现连接服务
                }
                Intent intent = new Intent(this, test_bindService.class); //创建Intent
                bindService(intent, conn, BIND_AUTO_CREATE);  //使用bindService()方式启动服务
                break;
            case R.id.button2: //调用服务中的方法
                myBinder.test();
                break;
            case R.id.button3: //解除绑定
                if(conn!=null){
                    unbindService(conn);  //解除绑定conn
                    conn=null;
                }
                break;
            default:
                break;
        }
    }

    //创建MyConn类，用于实现连接服务
    private class MyConn implements ServiceConnection{
        //成功绑定到服务时调用的方法
        @Override
        public  void  onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //  当使用bindService(intent, conn, BIND_AUTO_CREATE);时调用该方法
            myBinder=(test_bindService.MyBinder)iBinder;
            Log.v("MainActivity","服务绑定成功");
        }
        @Override
        public void  onServiceDisconnected(ComponentName componentName) {
        }
    }
}





