package com.example.broadcastdongtai;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button)findViewById(R.id.buttton);

        //事件处理发送广播
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MyReceiver my_rece=new MyReceiver();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
                //代码动态注册广播
                MainActivity.this.registerReceiver(my_rece, intentFilter);
                Toast.makeText(MainActivity.this,"注册成功",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
