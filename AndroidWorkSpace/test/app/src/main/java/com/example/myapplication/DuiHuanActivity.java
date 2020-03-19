package com.example.myapplication;

import android.app.AppComponentFactory;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DuiHuanActivity extends AppCompatActivity {

    Calendar calendar = Calendar.getInstance();
    //年
    int year = calendar.get(Calendar.YEAR);
    //月
    int month = calendar.get(Calendar.MONTH)+1;
    //日
    int day = calendar.get(Calendar.DAY_OF_MONTH);


    private TextView textViewmoney;

    private Button yiyuanhongbao;
    private Button shucaibing;
    private Button jianbing;
    private Button diaozhabing;
    private Button qiaotoupaigu;
    private Button wugujizhua;
    private Button xiaolucai;
    private Button boboyu;
    private Button huangmengji;
    private Button zhutibao;
    private Button yanxianbi;
    private Button tangjiebaoqi;
    private Button kaorou;
    private Button zizhu;
    private Button niupai;
    private Button huoguo;
    private Button haidilao;
    private Button xiaoqunzi;
    private Button renyiyuanwang;

    Dao dao = new Dao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.duihuanqu);
        textViewmoney=(TextView)findViewById(R.id.textjifen);
        textViewmoney.setText(" "+dao.querytime());

        yiyuanhongbao=(Button)findViewById(R.id.button01);
        yiyuanhongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = dao.querytime();
                money = money-10;
                if(money<0){
                    money = money+10;
                    Toast.makeText(getApplicationContext(),"连10积分都不到了，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                }else if (money>=0){
                    dao.update(money);
                    textViewmoney.setText(" "+money);
                    dao.insert("一元红包",year+"年"+month+"月"+day+"日");
                }
            }
        });

        shucaibing=(Button)findViewById(R.id.button11);
        shucaibing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = dao.querytime();
                money = money-50;
                if(money<0){
                    money = money+50;
                    Toast.makeText(getApplicationContext(),"连50积分都不到了，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                }else if (money>=0){
                    dao.update(money);
                    textViewmoney.setText(" "+money);
                    dao.insert("一个蔬菜饼",year+"年"+month+"月"+day+"日");
                }
                dao.update(money);
                textViewmoney.setText(" "+money);
            }
        });
        jianbing=(Button)findViewById(R.id.button12);
        jianbing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = dao.querytime();
                money = money-50;
                if(money<0){
                    money = money+50;
                    Toast.makeText(getApplicationContext(),"连50积分都不到了，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                }else if (money>=0){
                    dao.update(money);
                    textViewmoney.setText(" "+money);
                    dao.insert("一个煎饼",year+"年"+month+"月"+day+"日");
                }
                dao.update(money);
                textViewmoney.setText(" "+money);
            }
        });
        diaozhabing=(Button)findViewById(R.id.button13);
        diaozhabing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = dao.querytime();
                money = money-50;
                if(money<0){
                    money = money+50;
                    Toast.makeText(getApplicationContext(),"连50积分都不到了，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                }else if (money>=0){
                    dao.update(money);
                    textViewmoney.setText(" "+money);
                    dao.insert("鸡蛋灌饼",year+"年"+month+"月"+day+"日");
                }
                dao.update(money);
                textViewmoney.setText(" "+money);
            }
        });
        qiaotoupaigu=(Button)findViewById(R.id.button21);
        qiaotoupaigu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = dao.querytime();
                money = money-100;
                if(money<0){
                    money = money+100;
                    Toast.makeText(getApplicationContext(),"积分不足，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                }else if (money>=0){
                    dao.update(money);
                    textViewmoney.setText(" "+money);
                    dao.insert("桥头排骨",year+"年"+month+"月"+day+"日");
                }
                dao.update(money);
                textViewmoney.setText(" "+money);
            }
        });

        wugujizhua=(Button)findViewById(R.id.button22);
        wugujizhua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = dao.querytime();
                money = money-100;
                if(money<0){
                    money = money+100;
                    Toast.makeText(getApplicationContext(),"积分不足，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                }else if (money>=0){
                    dao.update(money);
                    textViewmoney.setText(" "+money);
                    dao.insert("无骨鸡爪",year+"年"+month+"月"+day+"日");
                }
                dao.update(money);
                textViewmoney.setText(" "+money);
            }
        });

        xiaolucai=(Button)findViewById(R.id.button23);
        xiaolucai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = dao.querytime();
                money = money-100;
                if(money<0){
                    money = money+100;
                    Toast.makeText(getApplicationContext(),"积分不足，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                }else if (money>=0){
                    dao.update(money);
                    textViewmoney.setText(" "+money);
                    dao.insert("小卤菜一份",year+"年"+month+"月"+day+"日");
                }
                dao.update(money);
                textViewmoney.setText(" "+money);
            }
        });
        boboyu=(Button)findViewById(R.id.button31);
        boboyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = dao.querytime();
                money = money-150;
                if(money<0){
                    money = money+150;
                    Toast.makeText(getApplicationContext(),"积分不足，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                }else if (money>=0){
                    dao.update(money);
                    textViewmoney.setText(" "+money);
                    dao.insert("啵啵鱼一份",year+"年"+month+"月"+day+"日");
                }
                dao.update(money);
                textViewmoney.setText(" "+money);
            }
        });
        huangmengji=(Button)findViewById(R.id.button32);
        huangmengji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = dao.querytime();
                money = money-150;
                if(money<0){
                    money = money+150;
                    Toast.makeText(getApplicationContext(),"积分不足，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                }else if (money>=0){
                    dao.update(money);
                    textViewmoney.setText(" "+money);
                    dao.insert("黄焖鸡一份",year+"年"+month+"月"+day+"日");
                }
                dao.update(money);
                textViewmoney.setText(" "+money);
            }
        });

        zhutibao=(Button)findViewById(R.id.button33);
        zhutibao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = dao.querytime();
                money = money-150;
                if(money<0){
                    money = money+150;
                    Toast.makeText(getApplicationContext(),"积分不足，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                }else if (money>=0){
                    dao.update(money);
                    textViewmoney.setText(" "+money);
                    dao.insert("猪蹄煲一份",year+"年"+month+"月"+day+"日");
                }
                dao.update(money);
                textViewmoney.setText(" "+money);
            }
        });
        yanxianbi=(Button)findViewById(R.id.button41);
        yanxianbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = dao.querytime();
                money = money-200;
                if(money<0){
                    money = money+200;
                    Toast.makeText(getApplicationContext(),"积分不足，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                }else if (money>=0){
                    dao.update(money);
                    textViewmoney.setText(" "+money);
                    dao.insert("眼线笔",year+"年"+month+"月"+day+"日");
                }
                dao.update(money);
                textViewmoney.setText(" "+money);
            }
        });
        tangjiebaoqi=(Button)findViewById(R.id.button42);
        tangjiebaoqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = dao.querytime();
                money = money-200;
                if(money<0){
                    money = money+200;
                    Toast.makeText(getApplicationContext(),"积分不足，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                }else if (money>=0){
                    dao.update(money);
                    textViewmoney.setText(" "+money);
                    dao.insert("烫睫毛器",year+"年"+month+"月"+day+"日");
                }
                dao.update(money);
                textViewmoney.setText(" "+money);
            }
        });
        zizhu=(Button)findViewById(R.id.button51);
        zizhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = dao.querytime();
                money = money-250;
                if(money<0){
                    money = money+250;
                    Toast.makeText(getApplicationContext(),"积分不足，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                }else if (money>=0){
                    dao.update(money);
                    textViewmoney.setText(" "+money);
                    dao.insert("自助餐",year+"年"+month+"月"+day+"日");
                }
                dao.update(money);
                textViewmoney.setText(" "+money);
            }
        });

        niupai=(Button)findViewById(R.id.button52);
        niupai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = dao.querytime();
                money = money-250;
                if(money<0){
                    money = money+250;
                    Toast.makeText(getApplicationContext(),"积分不足，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                }else if (money>=0){
                    dao.update(money);
                    textViewmoney.setText(" "+money);
                    dao.insert("牛排",year+"年"+month+"月"+day+"日");
                }
                dao.update(money);
                textViewmoney.setText(" "+money);
            }
        });
        huoguo=(Button)findViewById(R.id.button53);
        huoguo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = dao.querytime();
                money = money-250;
                if(money<0){
                    money = money+250;
                    Toast.makeText(getApplicationContext(),"积分不足，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                }else if (money>=0){
                    dao.update(money);
                    textViewmoney.setText(" "+money);
                    dao.insert("火锅",year+"年"+month+"月"+day+"日");
                }
                dao.update(money);
                textViewmoney.setText(" "+money);
            }
        });
        haidilao=(Button)findViewById(R.id.button61);
        haidilao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = dao.querytime();
                money = money-300;
                if(money<0){
                    money = money+300;
                    Toast.makeText(getApplicationContext(),"积分不足，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                }else if (money>=0){
                    dao.update(money);
                    textViewmoney.setText(" "+money);
                    dao.insert("海底捞",year+"年"+month+"月"+day+"日");
                }
                dao.update(money);
                textViewmoney.setText(" "+money);
            }
        });
        xiaoqunzi=(Button)findViewById(R.id.button62);
        xiaoqunzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = dao.querytime();
                money = money-300;
                if(money<0){
                    money = money+300;
                    Toast.makeText(getApplicationContext(),"积分不足，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                }else if (money>=0){
                    dao.update(money);
                    textViewmoney.setText(" "+money);
                    dao.insert("小裙子",year+"年"+month+"月"+day+"日");
                }
                dao.update(money);
                textViewmoney.setText(" "+money);
            }
        });
        kaorou=(Button)findViewById(R.id.button63);
        kaorou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = dao.querytime();
                money = money-300;
                if(money<0){
                    money = money+300;
                    Toast.makeText(getApplicationContext(),"积分不足，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                }else if (money>=0){
                    dao.update(money);
                    textViewmoney.setText(" "+money);
                    dao.insert("烤肉",year+"年"+month+"月"+day+"日");
                }
                dao.update(money);
                textViewmoney.setText(" "+money);
            }
        });
        renyiyuanwang=(Button)findViewById(R.id.button71);
        renyiyuanwang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = dao.querytime();
                money = money-1000;
                if(money<0){
                    money = money+1000;
                    Toast.makeText(getApplicationContext(),"积分不足，快抓紧时间学习",Toast.LENGTH_SHORT).show();
                }else if (money>=0){
                    dao.update(money);
                    textViewmoney.setText(" "+money);
                    dao.insert("任意愿望",year+"年"+month+"月"+day+"日");
                }
                dao.update(money);
                textViewmoney.setText(" "+money);
            }
        });

    }
}
