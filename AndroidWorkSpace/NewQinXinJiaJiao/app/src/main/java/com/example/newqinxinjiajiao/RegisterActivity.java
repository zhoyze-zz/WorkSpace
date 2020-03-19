package com.example.newqinxinjiajiao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class  RegisterActivity extends AppCompatActivity {
    private String Tag = "RegisterActivity";
    private EditText phonenum; //用户名
    private EditText pwd;  //密码
    private String name1,pwd1;
    private Button register_btn;   //登录按钮
    private Button back;

    final OkHttpClient client_register = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
/**
 * 初始化
 */
        phonenum = (EditText) findViewById(R.id.register_phonenumber);
        pwd = (EditText) findViewById(R.id.register_paswd);
        register_btn = (Button) findViewById(R.id.register);
        back =(Button)findViewById(R.id.back);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取相关参数
                name1=phonenum.getText().toString().trim();
                pwd1=pwd.getText().toString().trim();
                //通过okhttp发起post请求
                postRequest(name1,pwd1);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    /**
     * post请求后台
     * @param username
     * @param password
     */
    private void postRequest(String username,String password)  {
        //建立请求表单，添加上传服务器的参数
        Log.d(Tag,"建立请求表单，添加上传服务器的参数");
        RequestBody formBody = new FormBody.Builder()
                .add("phone",name1)
                .add("passwd",pwd1)
                .build();
        //发起请求
        final Request request = new Request.Builder()
                .url("http://10.0.2.2/user_register.php")
                .post(formBody)
                .build();
        //新建一个线程，用于得到服务器响应的参数
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;   //创建一个response，含有服务器返回的信息
                Log.d(Tag,"建立线程");
                try {//回调
                    response = client_register.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String flag = response.body().string(); //用来存放服务器返回的值
                        if(flag.contains("success")){ //如果登录成功则显示登录成功
                            Log.d(Tag,"注册成功");
                            Intent intent = new Intent(RegisterActivity.this,SucesActivity.class);
                            Looper.prepare();
                            Toast.makeText(RegisterActivity.this ,"注册成功",Toast.LENGTH_SHORT ).show();
                            Looper.loop();                               //注册成功时提示注册成功
                            startActivity(intent);
                        } else {
                            Log.d(Tag,"注册失败");
                            Looper.prepare();
                            Toast.makeText(RegisterActivity.this ,"该用户已存在！",Toast.LENGTH_SHORT ).show();
                            Looper.loop();                            //当该用户已存在时，提示该用户已存在！
                        } } else {
                        throw new IOException("Unexpected code:" + response);
                    } } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}

