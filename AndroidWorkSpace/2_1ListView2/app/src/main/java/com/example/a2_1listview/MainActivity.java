package com.example.a2_1listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SimpleAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.list);
        ArrayList<HashMap<String,Object>>listItem = new ArrayList<HashMap<String,Object>>();

        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("imageView",R.drawable.test);map.put("itemtextView","学校:");map.put("itemtextView2","江苏大学");
        listItem.add(map);
        HashMap<String,Object> map1 = new HashMap<String, Object>();
        map1.put("imageView",R.drawable.test);map1.put("itemtextView","地址:");map1.put("itemtextView2","江苏省镇江市");
        listItem.add(map1);
        HashMap<String,Object> map2 = new HashMap<String, Object>();
        map2.put("imageView",R.drawable.test);map2.put("itemtextView","邮编:");map2.put("itemtextView2","212013");
        listItem.add(map2);
        HashMap<String,Object> map3 = new HashMap<String, Object>();
        map3.put("imageView",R.drawable.test);map3.put("itemtextView","前身:");map3.put("itemtextView2","江苏理工大学");
        listItem.add(map3);
        HashMap<String,Object> map4 = new HashMap<String, Object>();
        map4.put("imageView",R.drawable.test);map4.put("itemtextView","电话:");map4.put("itemtextView2","0511-88780030");
        listItem.add(map4);
        HashMap<String,Object> map5= new HashMap<String, Object>();
        map5.put("imageView",R.drawable.test);map5.put("itemtextView","学院:");map5.put("itemtextView2","计算机科学与通信工程学院");
        listItem.add(map5);

        SimpleAdapter simpleAdapter2 = new SimpleAdapter(this,listItem,R.layout.item,
                new String[]{"imageView","itemtextView","itemtextView2"},
                new int[] {R.id.imageView,R.id.itemtextView,R.id.itemtextView2} );
        listView.setAdapter(simpleAdapter2);
    }
}
