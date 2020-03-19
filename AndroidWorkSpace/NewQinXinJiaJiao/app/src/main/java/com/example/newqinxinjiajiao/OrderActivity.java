package com.example.newqinxinjiajiao;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderActivity extends Activity {

    private String phonenum = "";
    public Boolean order_flag = false;
    private String Tag = "OrderActivity";
    String content[] = {};
    List<Map<String, Object>> list;
    SimpleAdapter adapter;  //创建Adapter用于存放获取到的订单数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.order_layout);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        phonenum = bundle.getString("phone");
        list = new ArrayList<Map<String, Object>>();
        Log.d(Tag,"即将开启线程");
        Thread thread = new Thread(runnable); //创建新的线程
        thread.start();                       //开启线程
        Log.d(Tag,"开启线程之后");
        adapter = new SimpleAdapter(OrderActivity.this, list, R.layout.order1_layout, new String[]{"bookname", "booknum", "bookprice", "ztai"}, new int[]{R.id.bookname, R.id.booknum, R.id.bookprice, R.id.ztai});
        ListView listView = (ListView) findViewById(R.id.list_order);
        listView.setAdapter(adapter);//将Adapter添加到listView中去
    }

    //开启新的线程用来进行后台订单数据获取
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Httpphone httporder = new Httpphone();//创建httpME类

            order_flag = httporder.getresult(phonenum,"http://10.0.2.2/order.php");
            try {

                boolean flag = true;
                while(flag){         //判断另一个线程是否获取到了result的值，如果没有获取到则等待0.1秒，再次判断
                    if (httporder.result.equals(" ")){
                        Thread.sleep(100);//睡眠0.1秒
                    }
                    else{
                        flag = false;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (order_flag) {
                //取得返回的内容添加到数组content中
                content = httporder.result.split(",");
                Log.d(Tag,httporder.result);
               // Log.d(Tag,content[1]);
                //Log.d(Tag,content[2]);
                //添加列表内容
                Map<String, Object> map ;
                for (int i = 0; i < content.length / 4; i++) {
                    map = new HashMap<String, Object>();
                    Log.d(Tag,"循环将读取到的数据添加到map中");
                    map.put("bookname", content[4 * i + 0]);
                    map.put("booknum", content[4 * i + 1] + "本");
                    map.put("bookprice", content[4 * i + 2] + "元");
                    map.put("ztai", content[4 * i + 3]);
                    list.add(map);  //将map添加到list中
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }
    };
}
