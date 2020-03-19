package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    private Button btntime;
    private Button btnduihuan;
    private Button btnlucky;
    private Button btnprize;

    private TextView textViewmoney;

    Dao dao = new Dao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper helper = new DatabaseHelper(this);
        helper.getWritableDatabase();


        textViewmoney=(TextView)findViewById(R.id.textjifen);
        textViewmoney.setText(" "+dao.querytime());

        btntime=(Button)findViewById(R.id.btn1);
        btnduihuan=(Button)findViewById(R.id.btn2);
        btnlucky=(Button)findViewById(R.id.btn3);
        btnprize=(Button)findViewById(R.id.btn4);

        /**
         * 学习一个小时的点击事件
         */
        btntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = dao.querytime();
                money = money+10;
                dao.update(money);
                textViewmoney.setText(" "+money);
            }
        });

        btnduihuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DuiHuanActivity.class);
                startActivity(intent);
            }
        });

        btnprize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Dblist.class);
                startActivity(intent);
            }
        });

        btnlucky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Lucky.class);
                startActivity(intent);
            }
        });

    }
}
