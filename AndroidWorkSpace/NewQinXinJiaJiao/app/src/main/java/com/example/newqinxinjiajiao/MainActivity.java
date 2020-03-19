package com.example.newqinxinjiajiao;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private int[] imageIds;
    private String[] titles;
    private ArrayList<ImageView>images;
    private ArrayList<View> dots;
    private TextView title;
    private ViewPager mViewPager;
    private ViewFlipper vFlipper;
    private ViewPagerAdapter adapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
                case R.id.navigation_me:
                    return true;
            }
            return false;
        }
    };
    private int currentItem;
    private int oldPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
      //  BottomNavigationView navView = findViewById(R.id.nav_view);
        // navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //消息滚动通知
        vFlipper=(ViewFlipper)findViewById(R.id.marquee_view);
        vFlipper.addView(View.inflate(getApplicationContext(),R.layout.index_toutiao,null));
        vFlipper.addView(View.inflate(getApplicationContext(),R.layout.index_toutiao1,null));
        vFlipper.addView(View.inflate(getApplicationContext(),R.layout.index_toutiao2,null));
        vFlipper.addView(View.inflate(getApplicationContext(),R.layout.index_toutiao3,null));

        //热门名师
        //图片ID
        imageIds = new int[]{
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
                R.drawable.e
        };
        //图片标题
        titles = new String[]{
                "热门心理老师",
                "教师节",
                "热门英语老师",
                "热门物理老师",
                "热门数学老师"
        };
        //显示的图片
        images = new ArrayList<ImageView>();
        for (int i = 0;i<imageIds.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        //显示的点
        dots = new ArrayList<View>();
        dots.add(findViewById(R.id.dot_0));
        dots.add(findViewById(R.id.dot_1));
        dots.add(findViewById(R.id.dot_2));
        dots.add(findViewById(R.id.dot_3));
        dots.add(findViewById(R.id.dot_4));
        title = (TextView)findViewById(R.id.image_title); //famous_teacher.xml中的图片标题
        title.setText(titles[0]);

        mViewPager =(ViewPager)findViewById(R.id.vp);  //连接到图片框
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
    }
    private class ViewPagerAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup view,int position, @NonNull Object object) {
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(@NonNull ViewGroup view, @NonNull int position) {
            view.addView(images.get(position));
            return images.get(position);
        }
    }
    protected void onStart(){
        super.onStart();
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(),2,4, TimeUnit.SECONDS);
    }
    //切换图片
    private class ViewPagerTask implements Runnable {  //多线程

        @Override
        public void run() {
            currentItem = (currentItem + 1)%imageIds.length;
            //更新界面
            handler.obtainMessage().sendToTarget();
        }
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            //设置当前页面
            mViewPager.setCurrentItem(currentItem);
        }
    };

    protected void onStop(){
        super.onStop();
    }
}

