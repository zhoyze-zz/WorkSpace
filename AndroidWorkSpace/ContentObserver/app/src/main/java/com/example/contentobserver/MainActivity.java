package com.example.contentobserver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;


public class MainActivity extends Activity {

    private TextView mes_text;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mes_text=findViewById(R.id.show_mes);
        //为content://sms的数据改变注册监听器
        ContentResolver contentResolver= getContentResolver();
        Uri uri= Uri.parse("content://sms/");
        contentResolver.registerContentObserver(uri,true, new SmsObsever(new Handler()));
    }
    //自定义的ContentObserver监听器类
    private class SmsObsever extends ContentObserver {
        public SmsObsever(Handler handler) {
            super(handler);
        }
        public void onChange(boolean selfChange) {
            //查询发件箱中的短信
            Cursor cursor= getContentResolver().query(Uri.parse("content://sms/sent"), null,null,null,null);
            //遍历查询得到的结果集,即可获取用户正在发送的短信
            while(cursor.moveToNext()){
                String address=cursor.getString(cursor.getColumnIndex("address"));
                String body=cursor.getString(cursor.getColumnIndex("body"));
                String time=cursor.getString(cursor.getColumnIndex("date"));
                mes_text.setText("收件人："+address+"\n内容："+body+"\n发送时间："+time);
            }
            cursor.close();
        }
    }
}
