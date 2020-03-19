package com.example.a1_3shijian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

public class MainActivity extends AppCompatActivity {

    private TextView t1;
    private TextView t2;

    private TimePicker timePicker1;
    private DatePicker datePicker1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=(TextView)findViewById(R.id.textView1);
        t2=(TextView)findViewById(R.id.textView2);
        datePicker1=(DatePicker)findViewById(R.id.dataPicker1);
        datePicker1.init(2019, 9, 1, new OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                t1.setText("现在选择的日期是："+datePicker1.getYear()+"-"+(datePicker1.getMonth()+1)+"-"+datePicker1.getDayOfMonth());
            }
        });
        timePicker1=(TimePicker)findViewById(R.id.timePicker1);
        timePicker1.setIs24HourView(true); //设置24小时
        timePicker1.setOnTimeChangedListener(new OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                t2.setText("现在选择的时间是："+timePicker1.getHour()+"-"+timePicker1.getMinute());
            }
        });


    }
}

