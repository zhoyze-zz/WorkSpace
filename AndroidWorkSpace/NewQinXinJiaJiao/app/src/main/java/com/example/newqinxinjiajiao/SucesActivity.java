package com.example.newqinxinjiajiao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SucesActivity extends Activity implements View.OnClickListener,View.OnTouchListener{

    private String Tag = "SucesActivity";
    private ViewFlipper vFlipper;
    private ViewPager ViewPager; //用来存放四个界面的viewpager
   // private ImageButton index_image,search_image,mes_image,me_image;
    //index里的组件
    public TextView local_text;
    public ImageView math,chinese,english,physical,politics,chemistry,biology,geography;
    private ViewFlipper vFliper;
    //消息里的组件
    public TextView order_teach,order_sug,order_talk,order_cheap;
    PopupWindow cheap_window;
    //me里的组件初始化
    public LinearLayout use_mes;
    public TextView phone,plant,set,evaluate,myteacher;
    public ImageView icon_img,order_img,money_img,reward_img;
    public String phonenum="";
    /*
     * 用来设置来回滑动
     */
    private List<View> lists = new ArrayList<View>();
    private MyAdapter myAdapter;
    //下面四个按钮，用来改变背景颜色
    private LinearLayout layout1,layout2,layout3,layout4;
    //图片轮播组件
    public int imageIds[];
    public String[] titles;
    public ArrayList<ImageView>images;
    public ArrayList<View> dots;
    public TextView title;
    public ViewPager mViewPager;
    public ViewPagerAdapter adapter;  //存放图片的ViewPager类
    public int oldPosition = 0;  //记录上一次点的位置
    public int currentItem;      //当前页面
    private ScheduledExecutorService scheduledExecutorService;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_navigational);
        Intent intent = this.getIntent(); //设置意图
        Bundle bundle = intent.getExtras(); //
        phonenum = bundle.getString("phone");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigational);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        init();
    }

    //添加导航栏
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        //导航栏
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:      //点击导航栏中首页图标
                    ViewPager.setCurrentItem(0);//切换到第一个页面
                    return true;
                case R.id.navigation_dashboard:  //点击导航栏中搜索图标
                    Intent intent=new Intent(SucesActivity.this,SchTeAxtivity.class);
                    startActivity(intent);     //跳转到搜索页面
                    return true;
                case R.id.navigation_notifications: //点击导航栏中消息图标
                    ViewPager.setCurrentItem(2);//切换到第二个页面
                    return true;
                case R.id.navigation_me:  //点击导航栏中我的信息图标
                    ViewPager.setCurrentItem(3);//切换到第三个页面
                    return true;
            }
            return false;
        }
    };


    //组件初始化
    @SuppressWarnings("deprecation")
    public void init() {
        //加载对应的布局文件
        View index = getLayoutInflater().inflate(R.layout.index, null);
        View search = getLayoutInflater().inflate(R.layout.search, null);
        View mes = getLayoutInflater().inflate(R.layout.mes, null);
        View me = getLayoutInflater().inflate(R.layout.me, null);
        lists.add(index);  //将布局添加到list表中
        lists.add(search);
        lists.add(mes);
        lists.add(me);  //lists表包含各个界面
        myAdapter = new MyAdapter(lists); //将lists表添加到myAdapter中
        ViewPager = (ViewPager) findViewById(R.id.viewPager2);
        ViewPager.setAdapter(myAdapter);   //设置ViewPager的Adapter为myAdapter
        //index组件
        //english,physical,politics,chemistry,biology,geography;
        math = (ImageView)index.findViewById(R.id.math);
        math.setOnClickListener(this);
        chinese = (ImageView)index.findViewById(R.id.chinses);
        chinese.setOnClickListener(this);
        english = (ImageView)index.findViewById(R.id.english);
        english.setOnClickListener(this);
        physical = (ImageView)index.findViewById(R.id.physics);
        physical.setOnClickListener(this);
        politics = (ImageView)index.findViewById(R.id.political);
        politics.setOnClickListener(this);
        chemistry = (ImageView)index.findViewById(R.id.chemistry);
        chemistry.setOnClickListener(this);
        biology = (ImageView)index.findViewById(R.id.biological);
        biology.setOnClickListener(this);
        geography = (ImageView)index.findViewById(R.id.geographic);
        geography.setOnClickListener(this);
        evaluate = (TextView)me.findViewById(R.id.evaluate);  //为我的评价按钮添加点击事件
        evaluate.setOnClickListener(this);
        myteacher = (TextView)me.findViewById(R.id.myteacher);//为我的老师按钮添加点击事件
        myteacher.setOnClickListener(this);
        //消息滚动通知
        vFlipper = (ViewFlipper)index.findViewById(R.id.marquee_view);
        vFlipper.addView(View.inflate(getApplicationContext(), R.layout.index_toutiao, null)); //将布局添加到Flipper中
        vFlipper.addView(View.inflate(getApplicationContext(), R.layout.index_toutiao1, null));//将布局添加到Flipper中
        vFlipper.addView(View.inflate(getApplicationContext(), R.layout.index_toutiao2, null));//将布局添加到Flipper中
        vFlipper.addView(View.inflate(getApplicationContext(), R.layout.index_toutiao3, null));//将布局添加到Flipper中
        //热门名师
        //图片ID
        imageIds = new int[]{ //设置一个保存图片的数组
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
                R.drawable.e
        };
        //图片标题
        titles = new String[]{ //设置一个保存标题的数组
                "热门心理老师",
                "教师节",
                "热门英语老师",
                "热门物理老师",
                "热门数学老师"
        };
        //显示的图片
        images = new ArrayList<ImageView>();   //将图片存入到image的ArrayList中
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);//设置一个view的背景图片，只不过传入的是一个drawable的id值或者color颜色值
            images.add(imageView);
        }
        //显示的点
        dots = new ArrayList<View>();
        dots.add(findViewById(R.id.dot_0));
        dots.add(findViewById(R.id.dot_1));
        dots.add(findViewById(R.id.dot_2));
        dots.add(findViewById(R.id.dot_3));
        dots.add(findViewById(R.id.dot_4));
        title = (TextView)index.findViewById(R.id.image_title); //famous_teacher.xml中的图片标题
        title.setText(titles[0]);

        mViewPager = (ViewPager)index.findViewById(R.id.vp);  //连接到图片框
        adapter = new ViewPagerAdapter();
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //TODO Auto-generated method stub
            }
            @Override
            public void onPageSelected(int position) {
                //TODO Auto-generated method stub
                title.setText(titles[position]);
                oldPosition = position;
                currentItem = position;
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                //TODO Auto-generated method stub
            }
        });
        //search组件
        //message组件
        order_teach = (TextView) mes.findViewById(R.id.order_teach);  //预约老师
        order_sug = (TextView) mes.findViewById(R.id.order_suggest);  //我的建议
        order_talk = (TextView) mes.findViewById(R.id.order_talk);    //消息
        order_cheap = (TextView) mes.findViewById(R.id.order_cheaper);//优惠信息
        order_teach.setOnClickListener(this);
        order_sug.setOnClickListener(this);
        order_talk.setOnClickListener(this);
        order_cheap.setOnClickListener(this);
        //me的组件
        use_mes = (LinearLayout) me.findViewById(R.id.user_message); //用户信息
        use_mes.setOnClickListener(this);
        phone = (TextView) me.findViewById(R.id.phone_numbers); //我的手机号
        phone.setText("手机号：" + phonenum);
        order_img = (ImageView) me.findViewById(R.id.order); //我的订单按钮
        order_img.setOnClickListener(this);
        money_img = (ImageView) me.findViewById(R.id.money); //我的钱包按钮
        money_img.setOnClickListener(this);
        reward_img = (ImageView) me.findViewById(R.id.reward); //我的奖券按钮
        reward_img.setOnClickListener(this);
        plant = (TextView) me.findViewById(R.id.plant);  //我的计划按钮
        plant.setOnClickListener(this);
        set = (TextView) me.findViewById(R.id.sets); //设置按钮
        set.setOnClickListener(this);
}
    private class ViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) {
                return arg0 == arg1;
        }
        @Override
        public void destroyItem(@NonNull ViewGroup view, int position, @NonNull Object object) {
            view.removeView(images.get(position));
        }
        @Override
        public Object instantiateItem(@NonNull ViewGroup view, @NonNull int position) {
            view.addView(images.get(position));
            return images.get(position);
        }
    }
    protected void onStart(){   //进程开始
        super.onStart();
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //每隔2秒切换一张图片,设定执行线程计划,初始2s延迟,每次任务完成后延迟4s再执行一次任务
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(),2,4, TimeUnit.SECONDS);
        //以固定频率来执行线程任务
    }
    //切换图片
    private class ViewPagerTask implements Runnable {  //多线程
        @Override
        public void run() {
            currentItem = (currentItem + 1)%imageIds.length; //更新界面
            handler.obtainMessage().sendToTarget(); }}
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            //设置当前页面
            mViewPager.setCurrentItem(currentItem); }};
    protected void onStop(){
        super.onStop();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
                /*
                 *首页组件监听事件
                 * 跳转到各科教师显示界面
                 */
            case R.id.math:
                Intent intent11 = new Intent(SucesActivity.this,Showtea_Activity.class);//创建意图
                Bundle bundle11 = new Bundle();
                bundle11.putString("subject","数学");  //发送科目名和手机号给Showtea_Activity
                bundle11.putString("phone",phonenum);
                intent11.putExtras(bundle11);
                startActivity(intent11);
                break;

            case R.id.chinses:
                Intent intent12 = new Intent(SucesActivity.this,Showtea_Activity.class);//创建意图
                Bundle bundle12 = new Bundle();
                bundle12.putString("subject","语文"); //发送科目名和手机号给Showtea_Activity
                bundle12.putString("phone",phonenum);
                intent12.putExtras(bundle12);
                startActivity(intent12);
                break;
            case R.id.english:
                Intent intent13 = new Intent(SucesActivity.this,Showtea_Activity.class);//创建意图
                Bundle bundle13 = new Bundle();
                bundle13.putString("subject","英语");//发送科目名和手机号给Showtea_Activity
                bundle13.putString("phone",phonenum);
                intent13.putExtras(bundle13);
                startActivity(intent13);
                break;
            case R.id.physics:
                Intent intent14 = new Intent(SucesActivity.this,Showtea_Activity.class);//创建意图
                Bundle bundle14 = new Bundle();
                bundle14.putString("subject","物理");//发送科目名和手机号给Showtea_Activity
                bundle14.putString("phone",phonenum);//发送科目名和手机号给Showtea_Activity
                intent14.putExtras(bundle14);
                startActivity(intent14);
                break;
            case R.id.chemistry:
                Intent intent21 = new Intent(SucesActivity.this,Showtea_Activity.class);//创建意图
                Bundle bundle21 = new Bundle();
                bundle21.putString("subject","化学");//发送科目名和手机号给Showtea_Activity
                bundle21.putString("phone",phonenum);
                intent21.putExtras(bundle21);
                startActivity(intent21);
                break;
            case R.id.biological:
                Intent intent22 = new Intent(SucesActivity.this,Showtea_Activity.class);//创建意图
                Bundle bundle22 = new Bundle();
                bundle22.putString("subject","生物");//发送科目名和手机号给Showtea_Activity
                bundle22.putString("phone",phonenum);
                intent22.putExtras(bundle22);
                startActivity(intent22);
                break;
            case R.id.geographic:
                Intent intent23 = new Intent(SucesActivity.this,Showtea_Activity.class);//创建意图
                Bundle bundle23 = new Bundle();
                bundle23.putString("subject","地理");//发送科目名和手机号给Showtea_Activity
                bundle23.putString("phone",phonenum);
                intent23.putExtras(bundle23);
                startActivity(intent23);
                break;
            case R.id.political:
                Intent intent24 = new Intent(SucesActivity.this,Showtea_Activity.class);//创建意图
                Bundle bundle24 = new Bundle();
                bundle24.putString("subject","政治");//发送科目名和手机号给Showtea_Activity
                bundle24.putString("phone",phonenum);
                intent24.putExtras(bundle24);
                startActivity(intent24);
                break;
            //消息界面的预约教师信息
            case R.id.order_teach:
                Intent order_teach=new Intent(SucesActivity.this,order_teachActivity.class);
                Bundle teach=new Bundle();
                teach.putString("phone", phonenum);//发送科目名和手机号给Showtea_Activity
                order_teach.putExtras(teach);
                startActivity(order_teach);
                break;
            //消息界面的我的建议
            case R.id.order_suggest:
                Intent order_suggest=new Intent(SucesActivity.this,order_suggestActivity.class);
                Bundle suggest=new Bundle();
                suggest.putString("phone", phonenum);//发送科目名和手机号给Showtea_Activity
                order_suggest.putExtras(suggest);
                startActivity(order_suggest);
                break;
            //消息界面的论坛（Activity跳转）
            case R.id.order_talk:
                Intent order_talk=new Intent(SucesActivity.this,order_talkActivity.class);
                Bundle talk=new Bundle();
                talk.putString("phone", phonenum);//发送科目名和手机号给Showtea_Activity
                order_talk.putExtras(talk);
                startActivity(order_talk);
                break;
            //消息界面的优惠信息(Activity跳转）
            case R.id.order_cheaper:
                break;
            //me_order(我的钱包，订单，奖学券处理Activity)
            case R.id.order:
                Intent order_intent=new Intent(SucesActivity.this,OrderActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("phone",phonenum );
                order_intent.putExtras(bundle);
                startActivity(order_intent);
                break;
            //me_money
            case R.id.money:
                Intent money_intent=new Intent(SucesActivity.this,MoneyActivity.class);
                Bundle money_bundle=new Bundle();
                money_bundle.putString("phone",phonenum );
                money_intent.putExtras(money_bundle);
                startActivity(money_intent);
                break;
            //me_reward
            case R.id.reward:
                Intent reward_intent=new Intent(SucesActivity.this,RewardActivity.class);
                Bundle bundle1=new Bundle();
                bundle1.putString("phone",phonenum );
                reward_intent.putExtras(bundle1);
                startActivity(reward_intent);
                break;
            case R.id.myteacher:        //我的老师evaluatemyteacher
                Intent myteach=new Intent(SucesActivity.this,order_teachActivity.class);
                Bundle teach1=new Bundle();
                teach1.putString("phone", phonenum);//发送科目名和手机号给Showtea_Activity
                myteach.putExtras(teach1);
                startActivity(myteach);
                break;
            case R.id.evaluate:        //我的评价
                Log.d(Tag,"点击evaluate");
                Intent myevaluate=new Intent(SucesActivity.this,MyEvaluateActivity.class);
                Bundle evaluate=new Bundle();
                evaluate.putString("phone", phonenum);//发送科目名和手机号给Showtea_Activity
                myevaluate.putExtras(evaluate);
                startActivity(myevaluate);
                break;
            //我的计划
            case R.id.plant:          //我的计划
                Intent plant_intent=new Intent(SucesActivity.this,PlantActivity.class);
                startActivity(plant_intent);
                break;
             //设置
            case R.id.sets:
                Intent set_intent=new Intent(SucesActivity.this,SetActivity.class);
                startActivity(set_intent);
            default:
                break;
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    private class MyAdapter extends PagerAdapter {
        private List<View> mViews;
        public MyAdapter(List<View> lists) {
            mViews = lists;
        }

        @Override
        public int getCount() {
            return mViews == null ? 0 : mViews.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup view, int position, @NonNull Object object) {
            view.removeView(mViews.get(position));
        }

        @Override
        public Object instantiateItem(@NonNull ViewGroup view, @NonNull int position) {
            view.addView(mViews.get(position));
            return mViews.get(position);
        }
    }
}
