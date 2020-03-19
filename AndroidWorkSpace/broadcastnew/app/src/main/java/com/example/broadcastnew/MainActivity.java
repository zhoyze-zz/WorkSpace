package com.example.broadcastnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button send_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send_btn=(Button)findViewById(R.id.send);
        //事件处理发送广播
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction("MyBroadcast");
                //发送广播
                MainActivity.this.sendBroadcast(intent);
                Toast.makeText(MainActivity.this,"广播发送成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
}


