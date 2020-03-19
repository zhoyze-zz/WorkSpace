package com.example.contentproviderduanxinbeifeng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //content://sms查询所有短信的uri
                    Uri uri= Uri.parse("content://sms/");
                    //获取ContentResolver对象
                    ContentResolver contentResolver=getContentResolver();
                    //通过ContentResolver对象查询系统短信
                    Cursor cursor=contentResolver.query(uri,new String[]{"address","date",
                            "type","body"},null,null,null);
                    List<SmsInfo> list=new ArrayList<SmsInfo>();
                    while (cursor.moveToNext()){
                        String address=cursor.getString(0);
                        long date=cursor.getLong(1);
                        int type=cursor.getInt(2);
                        String body=cursor.getString(3);
                        SmsInfo smsInfo=new SmsInfo(address,date,type,body);
                        list.add(smsInfo);
                    }
                    cursor.close();
                    Sms_Xml.beifen_sms(list,MainActivity.this);
                }
            });
            }
    }


