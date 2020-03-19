package com.example.a2_2xml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import org.xmlpull.v1.XmlPullParser;
import android.util.Xml;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    XmlPullParser parser;
    ArrayList<HashMap<String,Object>>mData;
    HashMap<String,Object> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            InputStream inputStream1= getAssets().open("book.xml");
            parser = Xml.newPullParser();//创建一个XmlPullParser实例
            parser.setInput(inputStream1,"UTF-8");

            int eventType = parser.getEventType();
            while(eventType !=XmlPullParser.END_DOCUMENT){
                switch(eventType){
                    case XmlPullParser.START_DOCUMENT:
                        mData= new ArrayList<HashMap<String, Object>>();
                        break;
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("book")){
                            item = new HashMap<String, Object>();
                        }else if(parser.getName().equals("type")){
                            eventType = parser.next();
                            item.put("type",parser.getText());
                        }else if(parser.getName().equals("name")){
                            eventType = parser.next();
                            item.put("name",parser.getText());
                        }else if(parser.getName().equals("price")){
                            eventType = parser.next();
                            item.put("price",parser.getText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("book")){
                            mData.add(item);
                        }
                        break;
                }
                eventType = parser.next();
            }

        }catch (Exception e){
            Log.e("TAG",e.getMessage());
        }
        ListView listView1=(ListView)findViewById(R.id.listview1);
        SimpleAdapter simpleAdapter1=new SimpleAdapter(this,mData,R.layout.book_laylout,
                new String[]{"name","type","price"},new int[]{R.id.textView,R.id.textView2,R.id.textView3});
        listView1.setAdapter((simpleAdapter1));
    }
}
