package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.provider.ContactsContract.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //定义List的对象用于保存数据
        List<String> string;
        setContentView(R.layout.activity_main);
        //得到ContentResolver对象
        ContentResolver cr = getContentResolver();
        //取得电话本中开始一项的光标
        Cursor cursor = cr.query(Contacts.CONTENT_URI, null, null, null, null);
        string=new ArrayList<String>();
        //向下移动光标
        while(cursor.moveToNext())
        {
        //取得联系人名字
            int nameFieldColumnIndex = cursor.getColumnIndex(
                    PhoneLookup.DISPLAY_NAME);
            String contact = cursor.getString(nameFieldColumnIndex);

            //取得电话号码
            String ContactId= cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            //获取电话本开始一项的Uri
            Cursor phone =  cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
            while(phone.moveToNext())  {
                String PhoneNumber= phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                string.add (contact + ": " + PhoneNumber + "\n");
            }
        }
        cursor.close();

        //获取定义的ListView用来显示通讯录信息
        ListView peo_list= findViewById(R.id.show_people);
        peo_list.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,string));
    }
}


