package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MySecondActivity extends AppCompatActivity {

    private Button btnReg;
    private EditText edtName,edtPwd,edtRePwd;
    private static final int RESULT_CODE = 101;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_my_second);
        btnReg = (Button)findViewById(R.id.btnReg);
        edtName=(EditText)findViewById(R.id.edtName);
        edtPwd=(EditText)findViewById(R.id.edtPwd);
        edtRePwd=(EditText)findViewById(R.id.edtRepwd);

        btnReg.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String name = edtName.getText().toString();
                String pwd = edtPwd.getText().toString();
                String repwd = edtRePwd.getText().toString();

                if(!"".equals(pwd)&&pwd.equals(repwd)){
                    //获得启动该Active的Intent对象
                    Intent intent = getIntent();
                    intent.putExtra("name",name);
                    intent.putExtra("pwd",pwd);
                    //设置结果码，并设置结束后返回的Activity
                    setResult(RESULT_CODE,intent);
                    //结束RegActive
                    MySecondActivity.this.finish();

                }else{
                    Toast.makeText(MySecondActivity.this,"密码输入不一致",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}