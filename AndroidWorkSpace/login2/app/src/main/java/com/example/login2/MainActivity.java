package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin,btnReg;
    private EditText edtName,edtPwd;
    private final int REQUEST_CODE=101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnReg=(Button)findViewById(R.id.btnReg);
        edtName=(EditText)findViewById(R.id.edtName);
        edtPwd=(EditText)findViewById(R.id.edtPwd);

        btnLogin.setOnClickListener(new View.OnClickListener(){  //登录按钮
            public void onClick(View v){

                Intent intent = new Intent(MainActivity.this, MyFirstActivity.class);

                String name=edtName.getText().toString();
                String pwd= edtPwd.getText().toString();

                intent.putExtra("name",name);
                intent.putExtra("pwd",pwd);
                startActivity(intent); //跳转到MyFirstActivity
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener(){  //注册按钮
            public void onClick(View v){
                Intent intent1 = new Intent(MainActivity.this, MySecondActivity.class);
                startActivityForResult(intent1,REQUEST_CODE);  //跳转到MySecondActivity
            }
        });
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){  //返回后触发
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==REQUEST_CODE){
            String name = data.getStringExtra("name");
            String pwd = data.getStringExtra("pwd");
            edtName.setText(name);
            edtPwd.setText(pwd);
        }
    }
}