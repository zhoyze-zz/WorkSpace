package com.example.newqinxinjiajiao;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;



public class SchTeAxtivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    //获取科目和年级按钮
    private Button sub_btn;
    private Button grade_btn;
    //返回按钮
    private ImageView arrback;
    //定义两个弹出框年级和科目
    private View contentView;
    private View contentView1;
    PopupWindow subPoWind;  //定义科目弹出框
    PopupWindow gradePoWind;//定义年级弹出框

    //sub_pouwin里的课程按钮
    private Button sub1;
    private Button sub2;
    private Button sub3;
    private Button sub4;
    private Button sub5;
    private Button sub6;
    private Button sub7;
    private Button sub8;
    //grade_pouwin里的年级按钮
    private Button grade1;
    private Button grade2;
    private Button grade3;
    private Button grade4;
    private Button grade5;
    private Button grade6;
    private Button grade7;
    private Button grade8;
    private Button grade9;
    private Button grade10;
    private Button grade11;
    private Button grade12;

    //添加标签
    private String TAG = "SchTeAxtivity";
    //保存选择的年级和科目(默认的年级和科目)
    private String sub_select = "语文",grade_select = "一年级";//sub_select1 = "chinese";
    //确定按钮
    private Button sure_sub_select;
    private Button sure_grade_select;
    //显示教室列表Listview
    private ListView select_list_teach;
    //存储教师姓名
    private String teach_name = "";
    //存储教室信息列表
    private String string_teach[] =new String[20];
    private Boolean isget = false;
    private String Tag = "SchTeAxtivity";
    private String par_phone = null;
    get_Select_Teacher select = new  get_Select_Teacher(); //通过get_Select_Teacher类创建select对象



    @Override
    public void onCreate(Bundle savedInstanceState){
        if (par_phone==null) {
            par_phone = LoginActivity.phone; //将用户的电话号码保存
        }
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search);
        init();
    }

    //组件初始化
    public void init(){
        arrback = (ImageView)findViewById(R.id.arrback);
        sub_btn = (Button)findViewById(R.id.subject_name);
        grade_btn = (Button)findViewById(R.id.grade_name);
        grade_btn.setOnClickListener(this);
        sub_btn = (Button) findViewById(R.id.subject_name);
        sub_btn.setOnClickListener(this);
        arrback = (ImageView) findViewById(R.id.arrback);
        arrback.setOnClickListener(this);
        select_list_teach = (ListView) findViewById(R.id.select_teacher);
        select_list_teach.setOnItemClickListener(this);
        sub_btn.setText("语文");
        grade_btn.setText("一年级");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.arrback:
                SchTeAxtivity.this.finish();
                break;
            case R.id.grade_name:
                //显示年级
                gradePoWin();
                break;
            case R.id.subject_name:
                //显示科目
                subPoWin();
                break;
/***********各个科目按钮的点击事件*********************************************************************/
            case R.id.sub1:
                sub_select = "语文";
              //  sub_select1 = "chinese";
                sub_btn.setText(sub_select); //设置科目按钮为对应选择科目
                set_subBack();//设置科目选择的背景颜色 全都重置为白色
                sub1.setBackgroundResource(R.color.grey);
                break;
            case R.id.sub2:
                sub_select = "数学";
              //  sub_select1 = "数学";
                sub_btn.setText(sub_select); //设置科目按钮为对应选择科目
                set_subBack();//设置科目选择的背景颜色 全都重置为白色
                sub2.setBackgroundResource(R.color.grey);
                break;
            case R.id.sub3:
                sub_select = "英语";
             //   sub_select1 = "english";
                sub_btn.setText(sub_select); //设置科目按钮为对应选择科目
                set_subBack();//设置科目选择的背景颜色 全都重置为白色
                sub3.setBackgroundResource(R.color.grey);
                break;
            case R.id.sub4:
                sub_select = "物理";
             //   sub_select1 = "physical";
                sub_btn.setText(sub_select); //设置科目按钮为对应选择科目
                set_subBack();//设置科目选择的背景颜色 全都重置为白色
                sub4.setBackgroundResource(R.color.grey);
                break;
            case R.id.sub5:
                sub_select = "政治";
              //  sub_select1 = "political";
                sub_btn.setText(sub_select); //设置科目按钮为对应选择科目
                set_subBack();//设置科目选择的背景颜色 全都重置为白色
                sub5.setBackgroundResource(R.color.grey);
                break;
            case R.id.sub6:
                sub_select = "化学";
           //     sub_select1 = "chemistry";
                sub_btn.setText(sub_select);
                set_subBack();//设置科目选择的背景颜色 全都重置为白色
                sub6.setBackgroundResource(R.color.grey);
                break;
            case R.id.sub7:
                sub_select = "生物";
               // sub_select1 = "biology";
                sub_btn.setText(sub_select); //设置科目按钮为对应选择科目
                set_subBack();//设置科目选择的背景颜色 全都重置为白色
                sub7.setBackgroundResource(R.color.grey);
                break;
            case R.id.sub8:
                sub_select = "地理";
              //  sub_select1 = "geographic";
                sub_btn.setText(sub_select); //设置科目按钮为对应选择科目
                set_subBack();//设置科目选择的背景颜色 全都重置为白色
                sub8.setBackgroundResource(R.color.grey);
                break;
/*********************************************************************************************/
//确认选择的科目
            case R.id.sure_sub_select:
                subPoWind.dismiss();
                break;

//年级选择按钮***********************************************************//
            case R.id.grade1:
                grade_select = "一年级";
                grade_btn.setText(grade_select); //设置年级按钮为对应选择年级
                set_gradeBack();//设置年级选择的背景颜色 全都重置为白色
                grade1.setBackgroundResource(R.color.grey);
                break;
            case R.id.grade2:
                grade_select = "二年级";
                grade_btn.setText(grade_select);//设置年级按钮为对应选择年级
                set_gradeBack();//设置年级选择的背景颜色 全都重置为白色
                grade2.setBackgroundResource(R.color.grey);
                break;
            case R.id.grade3:
                grade_select = "三年级";
                grade_btn.setText(grade_select);//设置年级按钮为对应选择年级
                set_gradeBack();//设置年级选择的背景颜色 全都重置为白色
                grade3.setBackgroundResource(R.color.grey);
                break;
            case R.id.grade4:
                grade_select = "四年级";
                grade_btn.setText(grade_select);//设置年级按钮为对应选择年级
                set_gradeBack();//设置年级选择的背景颜色 全都重置为白色
                grade4.setBackgroundResource(R.color.grey);
                break;
            case R.id.grade5:
                grade_select = "五年级";
                grade_btn.setText(grade_select);//设置年级按钮为对应选择年级
                set_gradeBack();//设置年级选择的背景颜色 全都重置为白色
                grade5.setBackgroundResource(R.color.grey);
                break;
            case R.id.grade6:
                grade_select = "六年级";
                grade_btn.setText(grade_select);//设置年级按钮为对应选择年级
                set_gradeBack();//设置年级选择的背景颜色 全都重置为白色
                grade6.setBackgroundResource(R.color.grey);
                break;
            case R.id.grade7:
                grade_select = "初一";
                grade_btn.setText(grade_select);//设置年级按钮为对应选择年级
                set_gradeBack();//设置年级选择的背景颜色 全都重置为白色
                grade7.setBackgroundResource(R.color.grey);
                break;
            case R.id.grade8:
                grade_select = "初二";
                grade_btn.setText(grade_select);//设置年级按钮为对应选择年级
                set_gradeBack();//设置年级选择的背景颜色 全都重置为白色
                grade8.setBackgroundResource(R.color.grey);
                break;
            case R.id.grade9:
                grade_select = "初三";
                grade_btn.setText(grade_select);//设置年级按钮为对应选择年级
                set_gradeBack();//设置年级选择的背景颜色 全都重置为白色
                grade9.setBackgroundResource(R.color.grey);
                break;
            case R.id.grade10:
                grade_select = "高一";
                grade_btn.setText(grade_select);//设置年级按钮为对应选择年级
                set_gradeBack();//设置年级选择的背景颜色 全都重置为白色
                grade10.setBackgroundResource(R.color.grey);
                break;
            case R.id.grade11:
                grade_select = "高二";
                grade_btn.setText(grade_select);//设置年级按钮为对应选择年级
                set_gradeBack();//设置年级选择的背景颜色 全都重置为白色
                grade11.setBackgroundResource(R.color.grey);
                break;
            case R.id.grade12:
                grade_select = "高三";
                grade_btn.setText(grade_select);//设置年级按钮为对应选择年级
                set_gradeBack();//设置年级选择的背景颜色 全都重置为白色
                grade12.setBackgroundResource(R.color.grey);
                break;

//年级选择按钮**********************************************************
            case R.id.sure_grade_select:
                //在这里加载数据库信息，显示出符合条件的教师信息
                gradePoWind.dismiss();
                new Anothertask().execute((Void[])null);
                break;
            default:
                break;
        }
    }

    //显示科目PopupWindow
    protected void subPoWin(){
        //加载PopuWindow的布局文件
        contentView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.sub_pouwin,null);
        //声明一个弹出框
        subPoWind = new PopupWindow(this.getWindowManager().getDefaultDisplay().getWidth(),700);
        subPoWind.setContentView(contentView);
        //显示
        subPoWind.showAsDropDown(sub_btn);
        sub_init();
    }

    //显示年级PopupWindow
    protected void gradePoWin(){
        //加载PopuWindow的布局文件
        contentView1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.grade_pouwin,null);
        //声明一个弹出框
        gradePoWind = new PopupWindow(this.getWindowManager().getDefaultDisplay().getWidth(),700);
        gradePoWind.setContentView(contentView1);
        //显示
        gradePoWind.showAsDropDown(grade_btn);
        grade_init();
    }
    //sub---powin里的组件初始化
    protected void sub_init(){
        sub1=(Button)contentView.findViewById(R.id.sub1);
        sub2=(Button)contentView.findViewById(R.id.sub2);
        sub3=(Button)contentView.findViewById(R.id.sub3);
        sub4=(Button)contentView.findViewById(R.id.sub4);
        sub5=(Button)contentView.findViewById(R.id.sub5);
        sub6=(Button)contentView.findViewById(R.id.sub6);
        sub7=(Button)contentView.findViewById(R.id.sub7);
        sub8=(Button)contentView.findViewById(R.id.sub8);
        sub1.setOnClickListener(this);
        sub2.setOnClickListener(this);
        sub3.setOnClickListener(this);
        sub4.setOnClickListener(this);
        sub5.setOnClickListener(this);
        sub6.setOnClickListener(this);
        sub7.setOnClickListener(this);
        sub8.setOnClickListener(this);
        sure_sub_select = (Button) contentView.findViewById(R.id.sure_sub_select);
        sure_sub_select.setOnClickListener(this);
    }

    //grade----powin里的组件初始化
    protected void grade_init(){
        grade1=(Button)contentView1.findViewById(R.id.grade1);
        grade2=(Button)contentView1.findViewById(R.id.grade2);
        grade3=(Button)contentView1.findViewById(R.id.grade3);
        grade4=(Button)contentView1.findViewById(R.id.grade4);
        grade5=(Button)contentView1.findViewById(R.id.grade5);
        grade6=(Button)contentView1.findViewById(R.id.grade6);
        grade7=(Button)contentView1.findViewById(R.id.grade7);
        grade8=(Button)contentView1.findViewById(R.id.grade8);
        grade9=(Button)contentView1.findViewById(R.id.grade9);
        grade10=(Button)contentView1.findViewById(R.id.grade10);
        grade11=(Button)contentView1.findViewById(R.id.grade11);
        grade12=(Button)contentView1.findViewById(R.id.grade12);
        grade1.setOnClickListener(this);
        grade2.setOnClickListener(this);
        grade3.setOnClickListener(this);
        grade4.setOnClickListener(this);
        grade5.setOnClickListener(this);
        grade6.setOnClickListener(this);
        grade7.setOnClickListener(this);
        grade8.setOnClickListener(this);
        grade9.setOnClickListener(this);
        grade10.setOnClickListener(this);
        grade11.setOnClickListener(this);
        grade12.setOnClickListener(this);
        sure_grade_select = (Button) contentView1.findViewById(R.id.sure_grade_select);
        sure_grade_select.setOnClickListener(this);
    }

    //设置科目选择的背景颜色 全都重置为白色
    protected void set_subBack(){
        sub1.setBackgroundResource(R.color.white);
        sub2.setBackgroundResource(R.color.white);
        sub3.setBackgroundResource(R.color.white);
        sub4.setBackgroundResource(R.color.white);
        sub5.setBackgroundResource(R.color.white);
        sub6.setBackgroundResource(R.color.white);
        sub7.setBackgroundResource(R.color.white);
        sub8.setBackgroundResource(R.color.white);
    }

    //设置年级的选择颜色 全都重置为白色
    protected void set_gradeBack() {
        grade1.setBackgroundResource(R.color.white);
        grade2.setBackgroundResource(R.color.white);
        grade3.setBackgroundResource(R.color.white);
        grade4.setBackgroundResource(R.color.white);
        grade5.setBackgroundResource(R.color.white);
        grade6.setBackgroundResource(R.color.white);
        grade7.setBackgroundResource(R.color.white);
        grade8.setBackgroundResource(R.color.white);
        grade9.setBackgroundResource(R.color.white);
        grade10.setBackgroundResource(R.color.white);
        grade11.setBackgroundResource(R.color.white);
        grade12.setBackgroundResource(R.color.white);
    }

    //通过异步任务获取教师信息
    private class Anothertask extends AsyncTask<Void,Integer,Boolean>{
        @Override
        protected Boolean doInBackground(Void... voids) {
            //对UI组件的更新操作，时耗时的操作
            try{//填入科目和年级
                isget = select.get_Teach(sub_select,grade_select); //当查询成功时isget为true否则为false
                Thread.currentThread().sleep(1000);
                isget = select.get_Teach(sub_select,grade_select); //当查询成功时isget为true否则为false
            }catch (Exception e){ e.printStackTrace(); }
            return null; }
        protected void onPostExecute(Boolean result){
            //TODO Auto -generated method stub
            string_teach = new String[]{""};
            select_list_teach.setAdapter(new ArrayAdapter<String>(SchTeAxtivity.this,R.layout.array_adapt,string_teach));
            if (isget){
                string_teach = select.result.split(",");//通过”，“将字符串分割
                Log.i(TAG, select.result);
                select_list_teach.setAdapter(new ArrayAdapter<String>(SchTeAxtivity.this,R.layout.array_adapt,string_teach));
                Toast.makeText(SchTeAxtivity.this,"找到如下老师",Toast.LENGTH_SHORT).show(); }
            else{ Toast.makeText(SchTeAxtivity.this,"没有你要查找的老师！",Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            Thread.currentThread().sleep(100);
            par_phone = par_phone+" "; //防止par_phone为空
            teach_name = string_teach[position];//获取教师名称
            Intent intent1 = new Intent(SchTeAxtivity.this,ShowTeaDet_Activity.class);
            Bundle bundle1 = new Bundle();
            bundle1.putString("teach_name",teach_name);//发送教师名字
            bundle1.putString("subject", sub_select);//发送科目名称
            bundle1.putString("par_phone", par_phone);//发送家长电话号码
            //Log.d(Tag,par_phone);
            intent1.putExtras(bundle1);
            startActivity(intent1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
