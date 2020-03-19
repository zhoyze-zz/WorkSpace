package com.example.newqinxinjiajiao;


import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class order_suggestActivity extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private Spinner subject_spin;
    private EditText topic,content;
    private Button reprot;
    private String[] string;
    HttpAdvice advice = new HttpAdvice();
    private String Tag= "order_suggestActvity";
    private String phone_num,subject_name,contents;
    private String subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mes_order_suggest);
        init();
    }
    //组件初始化方法
    protected void init(){
        phone_num = LoginActivity.phone;
        subject_spin=(Spinner)findViewById(R.id.sugest_subject);
        topic=(EditText)findViewById(R.id.sugest_theme);
        content=(EditText)findViewById(R.id.sugest_advice);
        reprot=(Button)findViewById(R.id.sugest_report);
        reprot.setOnClickListener(this);
        string=new String[]{"请选择            ▼","数学","英语","语文","政治","地理","历史","物理","化学","生物"};
        subject_spin.setAdapter(new ArrayAdapter<String>(order_suggestActivity.this, R.layout.array_adapt,string));
        subject_spin.setOnItemSelectedListener(this);;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sugest_report:
                reprot.setEnabled(false);
                reprot.setBackgroundResource(android.R.color.darker_gray);
                Thread thread = new Thread(runnable);
                thread.start(); //开启线程
                break;
            default:
                break;
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        subject_name = string[position];
        Log.d(Tag,subject_name);
        // TODO Auto-generated method stub

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

    }
    //开启新的线程插入预约数据
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            String theme = topic.getText().toString();//获取话题
            contents = content.getText().toString();//获取具体建议内容
            Boolean flag = advice.getresult(subject_name,theme,contents,phone_num);  //科目，主题，内容，电话号码
            if(flag){//用于判断是否提交成功
                Looper.prepare();
                Toast.makeText(order_suggestActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }
    };

}
