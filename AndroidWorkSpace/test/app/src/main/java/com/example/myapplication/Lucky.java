package com.example.myapplication;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Lucky extends AppCompatActivity {

    private Button btnCJ;
    private TextView txResult;

    Calendar calendar = Calendar.getInstance();
    //年
    int year = calendar.get(Calendar.YEAR);
    //月
    int month = calendar.get(Calendar.MONTH)+1;
    //日
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        txResult=(TextView)findViewById(R.id.textViewResult);

        btnCJ=(Button)findViewById(R.id.btnChou);
        btnCJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dao dao = new Dao(Lucky.this);
                Random ra = new Random();
                int result = ra.nextInt(100)+1;
                if (result<=20){

                    int money = dao.querytime();
                    money = money-50;
                    if(money<0){
                        money = money +50;
                        Toast.makeText(getApplicationContext(),"没积分了，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                    }else if(money>=0){
                        dao.update(money);
                        dao.insert("一元红包",year+"年"+month+"月"+day+"日");
                        txResult.setText("恭喜你抽中了一元红包");
                    }
                }else if (result<=40){

                    int money = dao.querytime();
                    money = money-50;
                    if(money<0){
                        money = money +50;
                        Toast.makeText(getApplicationContext(),"没积分了，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                    }else if(money>=0){
                        dao.update(money);
                        dao.insert("谢谢参与",year+"年"+month+"月"+day+"日");
                        txResult.setText("恭喜你抽中了谢谢参与");
                    }
                }else if (result<=55){

                    int money = dao.querytime();
                    money = money-50;
                    if(money<0){
                        money = money +50;
                        Toast.makeText(getApplicationContext(),"没积分了，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                    }else if(money>=0){
                        dao.update(money);
                        dao.insert("蔬菜饼",year+"年"+month+"月"+day+"日");
                        txResult.setText("恭喜你抽中了蔬菜饼");
                    }
                }else if (result<=70){

                    int money = dao.querytime();
                    money = money-50;
                    if(money<0){
                        money = money +50;
                        Toast.makeText(getApplicationContext(),"没积分了，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                    }else if(money>=0){
                        dao.update(money);
                        dao.insert("煎饼",year+"年"+month+"月"+day+"日");
                        txResult.setText("恭喜你抽中了煎饼");
                    }
                }else if (result<=80){

                    int money = dao.querytime();
                    money = money-50;
                    if(money<0){
                        money = money +50;
                        Toast.makeText(getApplicationContext(),"没积分了，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                    }else if(money>=0){
                        dao.update(money);
                        dao.insert("桥头排骨",year+"年"+month+"月"+day+"日");
                        txResult.setText("恭喜你抽中了桥头排骨");
                    }
                }else if (result<=85){

                    int money = dao.querytime();
                    money = money-50;
                    if(money<0){
                        money = money +50;
                        Toast.makeText(getApplicationContext(),"没积分了，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                    }else if(money>=0){
                        dao.update(money);
                        dao.insert("波波鱼",year+"年"+month+"月"+day+"日");
                        txResult.setText("恭喜你抽中了波波鱼");
                    }
                }else if (result<=90){

                    int money = dao.querytime();
                    money = money-50;
                    if(money<0){
                        money = money +50;
                        Toast.makeText(getApplicationContext(),"没积分了，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                    }else if(money>=0){
                        dao.update(money);
                        dao.insert("黄焖鸡",year+"年"+month+"月"+day+"日");
                        txResult.setText("恭喜你抽中了黄焖鸡");
                    }
                }else if (result<=93){

                    int money = dao.querytime();
                    money = money-50;
                    if(money<0){
                        money = money +50;
                        Toast.makeText(getApplicationContext(),"没积分了，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                    }else if(money>=0){
                        dao.update(money);
                        dao.insert("烤肉",year+"年"+month+"月"+day+"日");
                        txResult.setText("恭喜你抽中了烤肉");
                    }
                }else if (result<=95){

                    int money = dao.querytime();
                    money = money-50;
                    if(money<0){
                        money = money +50;
                        Toast.makeText(getApplicationContext(),"没积分了，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                    }else if(money>=0){
                        dao.update(money);
                        dao.insert("牛排",year+"年"+month+"月"+day+"日");
                        txResult.setText("恭喜你抽中了牛排");
                    }
                }else if (result<=97){

                    int money = dao.querytime();
                    money = money-50;
                    if(money<0){
                        money = money +50;
                        Toast.makeText(getApplicationContext(),"没积分了，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                    }else if(money>=0){
                        dao.update(money);
                        dao.insert("火锅",year+"年"+month+"月"+day+"日");
                        txResult.setText("恭喜你抽中了火锅");
                    }
                }else if (result<=99){

                    int money = dao.querytime();
                    money = money-50;
                    if(money<0){
                        money = money +50;
                        Toast.makeText(getApplicationContext(),"没积分了，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                    }else if(money>=0){
                        dao.update(money);
                        dao.insert("小裙子",year+"年"+month+"月"+day+"日");
                        txResult.setText("恭喜你抽中了小裙子");
                    }
                }else if (result<=100){

                    int money = dao.querytime();
                    money = money-50;
                    if(money<0){
                        money = money +50;
                        Toast.makeText(getApplicationContext(),"没积分了，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                    }else if(money>=0){
                        dao.update(money);
                        dao.insert("海底捞",year+"年"+month+"月"+day+"日");
                        txResult.setText("恭喜你抽中了海底捞");
                    }
                }

            }
        });
    }
}
