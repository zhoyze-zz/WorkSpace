package com.example.wbcc;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.os.Environment.getExternalStorageState;

public class MainActivity extends AppCompatActivity {

    private EditText editText1;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PermisionUtils.verifyStoragePermissions(this); //动态获取文件读取写入权限

        setContentView(R.layout.activity_main);

        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);

        editText1=(EditText)findViewById(R.id.editText);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String environment= Environment.getExternalStorageState();//获取SD卡状态
                if (Environment.MEDIA_MOUNTED.equals(environment)){  //如果正常装载
                    //外部设备可以进行读写操作
                    Toast.makeText(MainActivity.this,"正常装载",Toast.LENGTH_LONG).show();
                    File sd_path = Environment.getExternalStorageDirectory(); //获取路径
                    //if(!sd_path.exists()){return}
                    File file = new File(sd_path,"test.txt");
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String str = editText1.getText().toString();
                    FileOutputStream fos;
                    try {
                        //写入数据
                        fos = new FileOutputStream(file);
                        OutputStreamWriter osw = new OutputStreamWriter(fos);
                        osw.write(str);
                        Toast.makeText(MainActivity.this,"写入内容:"+str+"成功",Toast.LENGTH_LONG).show();
                        osw.flush();
                        osw.close();
                        fos.close();

                        editText1.setText("");
                    }
                    catch (Exception exception){
                        exception.printStackTrace();
                    }
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String environment = Environment.getExternalStorageState();
                if (Environment.MEDIA_MOUNTED.equals(environment)){
                    //外部设备可以进行读写操作
                    File sd_path = Environment.getExternalStorageDirectory();
                    //if(!sd_path.exists()){return}
                    File file = new File(sd_path,"test.txt");
                    FileInputStream fis;
                    String s="";
                    try {
                        fis = new FileInputStream(file);
                        InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
                        char[] input = new char[fis.available()];
                        isr.read(input);
                        s = new String (input);
                        Toast.makeText(MainActivity.this,"读取内容为:"+s,Toast.LENGTH_LONG).show();
                        isr.close();
                        fis.close();
                    }
                    catch (Exception exception){
                        exception.printStackTrace();
                    }

                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String file_name = "test_neibu.txt";
                String str = editText1.getText().toString();
                FileOutputStream fi_out;
                try{
                    fi_out = openFileOutput(file_name,MODE_PRIVATE);
                    fi_out.write(str.getBytes());
                    Toast.makeText(MainActivity.this,"写入内容:"+str+"成功",Toast.LENGTH_LONG).show();
                    fi_out.close();
                }catch(Exception exception){
                    exception.printStackTrace();
                }

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String file_name = "test_neibu.txt";
                String str = "";
                FileInputStream fi_in;
                try{
                    fi_in=openFileInput(file_name);
                    byte[] buffer = new byte[fi_in.available()];
                    fi_in.read(buffer);
                    str=new String(buffer);
                    Toast.makeText(MainActivity.this,"读取内容为:"+str,Toast.LENGTH_LONG).show();
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });
    }
}

/**
     * Checks if the app has permission to write to device storage
     * If the app does not has permission then the user will be prompted to
     * grant permissions
     *
     * @param activity
     */
