package com.example.a1_3zhizhen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView t1;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=(TextView)findViewById(R.id.textView);
        linearLayout=(LinearLayout)findViewById(R.id.linearlatout);

        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        t1.setText("ACTION_DOWN"+motionEvent.getX()+","+motionEvent.getY());
                        break;
                    case MotionEvent.ACTION_UP:
                        t1.setText("ACTION_UP"+motionEvent.getX()+","+motionEvent.getY());
                        break;
                    case MotionEvent.ACTION_MOVE:
                        t1.setText("ACTION_MOVE"+motionEvent.getX()+","+motionEvent.getY());
                        break;
                }
                return true;
            }
        });
    }
}
