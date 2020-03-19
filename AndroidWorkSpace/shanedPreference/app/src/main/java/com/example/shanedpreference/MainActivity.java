package com.example.shanedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView uesrname;
    private TextView password;
    private TextView textView;

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       uesrname=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        textView=(TextView)findViewById(R.id.textView);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = null;
                textView.setText(getuser_mes().toString());
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = null;
                String uname = uesrname.getText().toString();
                String pwd = password.getText().toString();
                save_userMes(uname,pwd);
            }
        });
    }

    //保存用户名和密码，并且生成user_mes.xml文件的方法
    private boolean save_userMes( String username, String paswd){
        SharedPreferences sharedPreferences= getSharedPreferences("user_mes",MODE_PRIVATE);//私有数据
        SharedPreferences.Editor editor=sharedPreferences.edit();//获取编辑器
        editor.putString("username",username);//存入数据
        editor.putString("paswd",paswd);//存入数据
        editor.commit();//提交修改
        return true;
    }

    //从user_mes.xml文件中取出用户名和密码的方法
    private Map<String,String> getuser_mes(){
        SharedPreferences sharedPreferences= getSharedPreferences("user_mes",MODE_PRIVATE);//获取实例对象
        String username=sharedPreferences.getString("username",null);//第2个参数表示默认值 获取用户名
        String paswd=sharedPreferences.getString("paswd",null);//获取密码
        Map<String,String> user=new HashMap<String,String>();
        user.put("username",username);
        user.put("paswd",paswd);
        return user;

    }
}
