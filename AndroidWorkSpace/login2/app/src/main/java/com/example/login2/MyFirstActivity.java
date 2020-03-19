package com.example.login2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MyFirstActivity extends Activity {

    private TextView UserName;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_my_first);

        UserName=(TextView)findViewById(R.id.textViewName);
        Intent intent =  this.getIntent();
        String name= intent.getStringExtra("name");
        UserName.setText(name);

        listView=findViewById(R.id.list);
        ArrayList<HashMap<String,Object>> listItem = new ArrayList<HashMap<String,Object>>();
        for (int i=1;i<10;i++){
            HashMap<String,Object> map = new HashMap<String, Object>();
            map.put("imageView",R.drawable.test);
            map.put("itemtextView","好友"+i);
            listItem.add(map);
        }
        SimpleAdapter simpleAdapter2 = new SimpleAdapter(this,listItem,R.layout.item,
                new String[]{"imageView","itemtextView"},
                new int[] {R.id.imageView,R.id.itemtextView} );
        listView.setAdapter(simpleAdapter2);
    }
}
