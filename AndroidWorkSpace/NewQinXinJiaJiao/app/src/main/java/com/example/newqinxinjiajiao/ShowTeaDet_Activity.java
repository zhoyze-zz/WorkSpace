package com.example.newqinxinjiajiao;
/*
   用于显示教师详细信息
   以及预约功能
 */

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class ShowTeaDet_Activity extends Activity implements View.OnClickListener {

    private TextView sex , name , phonum , address , subject;
    private EditText exper ; //教学经历
    private ImageView icon,back;
    private Button order;
    private String subject_name;
    private String det_name;
    private Boolean isget = false;
    GetTeaDetail teaDetail = new GetTeaDetail();
    Resrve_Teacher resrve = new Resrve_Teacher();
    private String teach_phone,par_phone;
    private String Tag = "ShowTeaDet_Activity";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.showteadetail);
        init();
        new AnotherTask().execute((Void[]) null);
    }

    //组件初始化
    private void init(){
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        subject_name = bundle.getString("subject"); //获取科目名
        det_name = bundle.getString("teach_name");  //获取预约老师名字
        par_phone = bundle.getString("par_phone");  //获取家长电话
        sex = (TextView)findViewById(R.id.det_sex);
        name = (TextView)findViewById(R.id.det_name);
        name.setText(det_name);
        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(this);
        phonum = (TextView)findViewById(R.id.det_phonum);
        address = (TextView)findViewById(R.id.det_address);
        subject = (TextView)findViewById(R.id.det_subject);
        exper = (EditText) findViewById(R.id.det_exper);
        icon = (ImageView)findViewById(R.id.det_icon);
        order = (Button)findViewById(R.id.det_order);
        order.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //预约教师
            case R.id.back:
                ShowTeaDet_Activity.this.finish();
            case R.id.det_order:
                order.setEnabled(false);
                order.setBackgroundResource(android.R.color.darker_gray);
                Thread thread = new Thread(runnable);
                thread.start();
                break;
            default:
                break;
        }
    }
    //获取教师个人信息
    private class AnotherTask extends AsyncTask<Void,Integer,Boolean> {
        @Override protected Boolean doInBackground(Void... params) {
            //对UI组件的更新操作，耗时的操作
            try { isget = teaDetail.getDetail(det_name);
            } catch (Exception e) { e.printStackTrace(); }
            return null; }
        protected void onPostExecute(Boolean result) {
            if (isget) {//获取具体信息数组
                String[] message = teaDetail.result.split(",");
                Log.d(Tag,teaDetail.result);
                //通过“，”分割，用于存放获取到的教师详细信息
                System.out.println(message);
                sex.setText(message[0]);   //教师性别信息
                phonum.setText(message[1]); //家长的电话
                teach_phone = message[1]; //老师电话
                address.setText(message[2]);  //教师地址信息
                exper.setText("教学经历:" + message[3]);//教学经历
                subject.setText(message[4]);//教授科目信息
            }
        }
    }


        Runnable runnable = new Runnable() {
            @Override //开启新的线程插入预约数据
            public void run() {
                Calendar now = Calendar.getInstance();//获取当前时间
                String year = now.get(Calendar.YEAR)+""; //年
                String month = now.get(Calendar.MONTH)+1+""; //月
                String day = now.get(Calendar.DAY_OF_MONTH)+""; //日
                Boolean flag = resrve.Reserve(par_phone,teach_phone,det_name,subject_name,
                        year+"-"+month+"-"+day);
                if(flag){
                    Looper.prepare();
                    Toast.makeText(ShowTeaDet_Activity.this,
                            "预约成功，在消息中查看相关信息",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        };
    }

