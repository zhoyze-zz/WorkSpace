package com.example.smsmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText number, content;
    private Button send;
    SmsManager smsManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取SmsManager
        smsManager = SmsManager.getDefault();
        //初始化组件
        number = (EditText) findViewById(R.id.number);
        content = (EditText) findViewById(R.id.context1);
        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //创建一个PendingIntent对象
                PendingIntent pintent = PendingIntent.getActivity(
                        MainActivity.this, 0, new Intent(), 0);
                //发送短信
                smsManager.sendTextMessage(number.getText().toString(),
                        null, content.getText().toString(), pintent, null);
                Toast.makeText(MainActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
