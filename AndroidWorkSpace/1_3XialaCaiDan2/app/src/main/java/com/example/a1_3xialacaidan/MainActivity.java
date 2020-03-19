package com.example.a1_3xialacaidan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView t1;
    private TextView t2;
    private EditText e1;

    private RadioGroup radioGroup;
    private RadioButton radioButton11;
    private RadioButton radioButton12;

    private Spinner s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        t1=(TextView)findViewById(R.id.textView2);
        t2=(TextView)findViewById(R.id.textView3);
        e1=(EditText)findViewById(R.id.editText);
        radioGroup=(RadioGroup)findViewById(R.id.sex);
        radioButton11=(RadioButton)findViewById(R.id.radioButton);
        radioButton12=(RadioButton)findViewById(R.id.radioButton2);
        s1=(Spinner)findViewById(R.id.spinner);

        s1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value=parent.getItemAtPosition(position).toString();
                t2.setText("所在的城市为："+value);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String info = e1.getText().toString();
                t1.setText("输入的内容是："+info);
            }
        });

        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId){
                String temp = null;
                if(radioButton11.getId()==checkedId){
                    temp=radioButton11.getText().toString();
                }
                if(radioButton12.getId()==checkedId){
                    temp=radioButton12.getText().toString();
                }
                t1.setText("选择的内容是："+temp);
            }
        });
    }
}
