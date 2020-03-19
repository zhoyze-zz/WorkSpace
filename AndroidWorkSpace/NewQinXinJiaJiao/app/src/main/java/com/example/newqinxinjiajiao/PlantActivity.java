package com.example.newqinxinjiajiao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.Toast;

public class PlantActivity extends Activity implements CalendarView.OnDateChangeListener {

    private CalendarView calend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.me_plant);
        calend=(CalendarView)findViewById(R.id.calendview);
        calend.setOnDateChangeListener(this);
    }

    @Override
    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        Toast.makeText(PlantActivity.this, year+"-"+month+"-"+dayOfMonth, Toast.LENGTH_SHORT).show();
        Intent plant=new Intent(PlantActivity.this,MyPlantActivity.class);
        Bundle plant1=new Bundle();
        plant1.putString("date", year+"-"+month+"-"+dayOfMonth);//year+"-"+month+"-"+dayOfMonth
        plant.putExtras(plant1);
        startActivity(plant);

    }

}