package com.example.newqinxinjiajiao;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyEvaluateActivity extends Activity implements AdapterView.OnItemClickListener  {

    private ListView evaluate_list;
    private String[] string=new String[200];
    private String phone_num;
    private Httpphone httpmyevaluate=new Httpphone();
    private Boolean isSucceed;
    private String[] teach_name=new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mes_evaluate);
        init();
        new Anothertask().execute((Void[]) null);
    }
    //组件初始化方法
    private void init(){
        //获取登录的手机号
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        phone_num=bundle.getString("phone");
        evaluate_list=(ListView)findViewById(R.id.evaluate_list);
        this.registerForContextMenu(evaluate_list);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    //异步任务获取我的评价信息
    private class Anothertask extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            // 对UI组件的更新操作,耗时的操作
            try {
                // 连接到服务器的地址
                String currenturl = "http://10.0.2.2/myevaluate.php";
                // 填入用户名电话
                isSucceed = httpmyevaluate.getresult(phone_num,currenturl);
                boolean flag = true;
                while(flag){         //判断另一个线程是否获取到了result的值，如果没有获取到则等待0.1秒，再次判断
                    if (httpmyevaluate.result.equals(" ")){
                        Thread.sleep(100);//睡眠0.1秒
                    }
                    else{
                        flag = false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Boolean result) {
            // TODO Auto-generated method stub
            if (isSucceed) {
                string=httpmyevaluate.result.split(",");
                evaluate_list.setAdapter(new ArrayAdapter<String>(MyEvaluateActivity.this, R.layout.array_adapt,string));
            }
        }

    }
}