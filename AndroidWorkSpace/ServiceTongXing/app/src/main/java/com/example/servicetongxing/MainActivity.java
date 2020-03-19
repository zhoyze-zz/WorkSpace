package com.example.servicetongxing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyService.MyBinder myBinder;
    private Button btn1,btn2,btn3;

    //定义一个ServiceConnection对象
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.v("MainActivity","服务连接成功");
            myBinder = (MyService.MyBinder) iBinder;
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.v("MainActivity","服务断开连接");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.bind_btn);
        btn2 = findViewById(R.id.get_service_status);
        btn3 = findViewById(R.id.unbind_btn);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bind_btn:

                //绑定服务service
                Intent intent=new Intent(this, MyService.class);
                bindService(intent,connection,BIND_AUTO_CREATE);
                break;
            case R.id.unbind_btn:
                unbindService(connection);
                break;
            case R.id.get_service_status:
               Toast.makeText(MainActivity.this,String.valueOf(myBinder.getCount()),Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

