package com.example.newqinxinjiajiao;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class order_teachActivity extends Activity implements AdapterView.OnItemClickListener {

    private ListView teach_list;
    private String[] string=new String[20];
    private String phone_num;
    private Httpphone httporderteacher=new Httpphone();
    private Boolean isSucceed;
    private String[] teach_name=new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mes_order_teach);
        init();
        new Anothertask().execute((Void[]) null);
    }
    //组件初始化方法
    private void init(){
        //获取登录的手机号
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        phone_num=bundle.getString("phone");
        teach_list=(ListView)findViewById(R.id.order_teach_list);
        this.registerForContextMenu(teach_list);
        teach_list.setOnItemClickListener(this);
    }
    //异步任务获取预约教师信息
    private class Anothertask extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            // 对UI组件的更新操作,耗时的操作
            try {
                String currenturl = "http://10.0.2.2/orderteacher.php";
                isSucceed = httporderteacher.getresult(phone_num,currenturl);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            // TODO Auto-generated method stub
            if (isSucceed) {
                string=httporderteacher.result.split(",");
                teach_list.setAdapter(new ArrayAdapter<String>(order_teachActivity.this, R.layout.array_adapt,string));
            }
        }

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub
        teach_name=string[position].split(" ");
        Intent intent=new Intent(order_teachActivity.this,ShowTeaDet_Activity.class);
        Bundle bundle=new Bundle();
        bundle.putString("teach_name", teach_name[1]);
        intent.putExtras(bundle);
        bundle.putString("subject", teach_name[0]);
        bundle.putString("par_phone", phone_num);
        startActivity(intent);


    }

}
