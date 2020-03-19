package com.example.newqinxinjiajiao;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private String Tag = "LoginActivity";
    private EditText phonenum; //用户名
    private EditText pwd;  //密码
    private String name1,pwd1;
    public CheckBox reme_box;
    private Button login_btn;   //登录按钮
    private TextView regist;
    public TextView forget;
    public static String phone;

    final OkHttpClient client = new OkHttpClient();//创建okhttp

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


/**
 * 组件初始化
 */
        phonenum = (EditText) findViewById(R.id.login_phonenumber); //输入用户名/电话框
        pwd = (EditText) findViewById(R.id.login_paswd); //输入密码框
        login_btn = (Button) findViewById(R.id.login);  //登录按钮
        reme_box = (CheckBox) findViewById(R.id.reme_paswd); //记住密码按钮
        regist = (TextView)findViewById(R.id.regester_user);  //注册按钮
        forget = (TextView) findViewById(R.id.forget_paswd); //忘记密码

        //检查上次是否保存数据
        SharedPreferences preferences = getSharedPreferences("user_mes", Activity.MODE_PRIVATE);
        if (preferences != null) {
            phonenum.setText(preferences.getString("phone", ""));
            pwd.setText(preferences.getString("paswd", ""));
        }

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phone = phonenum.getText().toString();
                //获取相关参数
                name1=phonenum.getText().toString().trim();
                pwd1=pwd.getText().toString().trim();
                //通过okhttp发起post请求
                postRequest(name1,pwd1);
            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
    /**
     * post请求后台
     * @param username
     * @param password
     */
    private void postRequest(String username,String password)  {
        RequestBody formBody = new FormBody.Builder()
                .add("phone",name1)
                .add("passwd",pwd1)
                .build();
        //发起请求
        final Request request = new Request.Builder()
                .url("http://10.0.2.2/user_login.php")
                .post(formBody)
                .build();
        //新建一个线程，用于得到服务器响应的参数
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {//回调
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String flag = response.body().string(); //用来存放服务器返回的值
                        Log.d(Tag,flag);
                        if(flag.contains("success")){
                            Intent intent = new Intent(LoginActivity.this,SucesActivity.class);
                            Bundle bundle1=new Bundle();
                            bundle1.putString("phone", name1.toString());
                            //传入手机号用来在me中显示
                            intent.putExtras(bundle1);
                            startActivity(intent);
                        } else {
                            Looper.prepare();
                            Toast.makeText(LoginActivity.this ,"账号或密码错误！",Toast.LENGTH_SHORT ).show();
                            Looper.loop();
                        } } else {
                        throw new IOException("Unexpected code:" + response);
                    } } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

