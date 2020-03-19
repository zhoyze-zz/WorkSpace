package com.example.newqinxinjiajiao;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MoneyActivity extends Activity {
    private String phonenum="";
    private Boolean isSucceed=false;
    private ImageView arrback;
    Httpphone  httpMoney=new Httpphone();
    // 用来存储钱包信息
    private String[] message = {};
    //获取布局文件的组件
    private TextView all,left,jifen;
    private String Tag = "moneyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.money_layout);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        phonenum=bundle.getString("phone");  //获取电话号码
        init();
        new AnotherTask().execute((Void[]) null);

    }
    //组件初始化
    private void init(){
        all=(TextView)findViewById(R.id.zong);
        left=(TextView)findViewById(R.id.yue);
        jifen=(TextView)findViewById(R.id.jifen);
        arrback =(ImageView)findViewById(R.id.arrback);
        arrback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MoneyActivity.this.finish();
            }
        });
    }

    // 获取钱包信息
    private class AnotherTask extends AsyncTask<Void, Integer, Boolean> {


        @Override
        protected Boolean doInBackground(Void... params) {
            // 对UI组件的更新操作,耗时的操作
            try {
                // 连接到服务器的地址
                // 填入用户名密码和连接地址
                isSucceed = httpMoney.getresult(phonenum,"http://10.0.2.2/money.php");
                boolean flag = true;
                while(flag){         //判断另一个线程是否获取到了result的值，如果没有获取到则等待0.1秒，再次判断
                    if (httpMoney.result.equals(" ")){
                        Thread.sleep(100);//睡眠0.1秒
                    }
                    else{
                        flag = false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Boolean result) {
            if (isSucceed) {
                message=httpMoney.result.split(",");
                Log.d(Tag,httpMoney.result);
                all.setText("我的总额："+message[0]);
                left.setText("我的余额："+message[1]);
                jifen.setText("我的积分："+message[2]);
            }
        }
    }
}
