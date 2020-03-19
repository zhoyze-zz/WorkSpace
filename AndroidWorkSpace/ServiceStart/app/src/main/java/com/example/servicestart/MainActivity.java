package com.example.servicestart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {//为启动按钮添加点击事件
            @Override
            public void onClick(View view) {
                Intent startintent = new Intent(MainActivity.this,MyService.class);
                //创建Intent
                startService(startintent);  //启动服务
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stopintent = new Intent(MainActivity.this, MyService.class);
                //创建Intent
                stopService(stopintent);    //停止服务
            }
        });

    }
}

