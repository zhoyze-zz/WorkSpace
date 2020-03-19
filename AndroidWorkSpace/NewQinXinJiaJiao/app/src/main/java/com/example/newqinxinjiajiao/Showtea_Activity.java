package com.example.newqinxinjiajiao;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Showtea_Activity extends Activity implements AdapterView.OnItemClickListener {

    private String[] message = {};
    private ImageView arrback;//返回按钮
    public ListView Listview;
    //获取课程名字
    public String subject_name;
    //中文名字
    public TextView subject_teacher;
    private Boolean isSucceed=false;
    GetTeach_name getname=new GetTeach_name();
    //保存老师姓名，用来查询具体的教师信息
    private String name="";
    //保存家长手机号
    public String par_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.showteacher);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        subject_name=bundle.getString("subject");//从SucesActivity处接收课程名字
        par_phone=bundle.getString("phone");//从SucesActivity处接收家长手机号
        init();
        new AnotherTask().execute((Void[]) null);
    }

    //组件初始化方法
    public void init(){
        subject_teacher=(TextView)findViewById(R.id.subject_teacher); //showteacher显示老师的界面中的界面标题
        subject_teacher.setText(subject_name+"教师");
        arrback = (ImageView)findViewById(R.id.arrback2);
        arrback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Showtea_Activity.this.finish();
            }
        });
        Listview =(ListView)findViewById(R.id.show_teacher);    //showteacher显示老师界面中的界面标题
        this.registerForContextMenu(Listview);   //添加
        Listview.setOnItemClickListener(this);   //添加点击事件
    }

    //获取教师信息
    private class AnotherTask extends AsyncTask<Void, Integer, Boolean> { //通过异步线程
        @Override
        protected Boolean doInBackground(Void... params) {
            try {// 填入用户名密码和连接地址
                isSucceed = getname.getname(subject_name);//调用getname函数，并且通过返回值判断是否获取成功
            } catch (Exception e) { e.printStackTrace(); }
            return null; }
        @Override
        protected void onPostExecute(Boolean result) {
            if (isSucceed) {  //如果接收成功
                message=getname.result.split(",");//通过“,”将接收到的字符串分割为数组message
                Listview.setAdapter(new ArrayAdapter<String>(Showtea_Activity.this,
                        android.R.layout.simple_list_item_1,message));
            }else { Toast.makeText(Showtea_Activity.this, "抱歉！暂时没有你查找的教师！", Toast.LENGTH_SHORT).show(); }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub
        name=message[position];
            Intent intent = new Intent(Showtea_Activity.this, ShowTeaDet_Activity.class);
            Bundle bundle = new Bundle();
            bundle.putString("teach_name", name);   //传递教师名字
            bundle.putString("subject", subject_name); //传递课程名称
            bundle.putString("par_phone", par_phone);  //传递家长电话号码
            intent.putExtras(bundle);
            startActivity(intent);
//        }
    }
}
