package com.example.a2_3sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.app.AlertDialog;   //构造Aler对话框
import android.content.DialogInterface;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor1;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button11;
    private Button button12;
    private Button button13;
    private Button button14;
    private Button button15;
    private Button button16;
    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private ListView listView1;
    private String[] mListStr;
    private int item_position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=this.openOrCreateDatabase("/data/data/com.example.a2_3sqlite/databases/school.db",0,null);//打开数据库
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);
        ///////////////////////////////////////////////////////////////////////
        button7 = (Button)findViewById(R.id.button7);
        button8 = (Button)findViewById(R.id.button8);
        button9 = (Button)findViewById(R.id.button9);
        button10 = (Button)findViewById(R.id.button10);
        button11 = (Button)findViewById(R.id.button11);
        button12 = (Button)findViewById(R.id.button12);
        button13 = (Button)findViewById(R.id.button13);
        button14 = (Button)findViewById(R.id.button14);
        button15 = (Button)findViewById(R.id.button15);
        button16 = (Button)findViewById(R.id.button16);
        ///////////////////////////////////////////////////////////////////
        editText1 = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        editText3 = (EditText)findViewById(R.id.editText3);
        listView1 = (ListView)findViewById(R.id.listview_1);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item_position=position+1;
                Toast.makeText(MainActivity.this,"item_position="+item_position,Toast.LENGTH_LONG).show();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {  //新建
            @Override
            public void onClick(View v) {
                //创建sql语句
                String school_table="create table school_table(_id integer primary key autoincrement,"+ "s_name text,s_province text,s_city text)";
                db.execSQL(school_table);  //执行sql语句
            }
        });

        button2.setOnClickListener(new View.OnClickListener() { //显示
            @Override
            public void onClick(View view) {
                cursor1 = db.rawQuery("select * from school_table",null);
                mListStr = new String[cursor1.getCount()];
                int i=0;
                while(cursor1.moveToNext()){
                    mListStr[i] = cursor1.getString(1); //创建mListStr数据，存放从数据库中读取的数据
                    i = i+1;
                }
                listView1.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_single_choice,mListStr));  //将mListStr中的数据显示到listView中
                listView1.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
            }

        });

        button3.setOnClickListener(new View.OnClickListener() { //增加
            @Override
            public void onClick(View view) {
                String sql = String.format("insert into school_table(s_name,s_province,s_city)"+ "values('%1$s','%2$s','%3$s')", editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString());
                db.execSQL(sql);

            }
        });

        button4.setOnClickListener(new View.OnClickListener() { //删除
            @Override
            public void onClick(View view) {
                String sql = "delete from school_table where _id ="+ item_position; // item_position=position+1;
                db.execSQL(sql);
                Toast.makeText(MainActivity.this,"item_position="+item_position,Toast.LENGTH_LONG).show();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() { //详细
            @Override
            public void onClick(View view) {
                cursor1.moveToFirst();
                cursor1.move(item_position);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(cursor1.getString(1));
                builder.setMessage("学校所在省份是:"+cursor1.getString(2)+"\n\r"+"学校所在城市是："+cursor1.getString(3));
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.close();
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        button7.setOnClickListener(new View.OnClickListener() {  //1
            @Override
            public void onClick(View v) {
                item_position=1;
                Toast.makeText(MainActivity.this,"item_position="+item_position,Toast.LENGTH_LONG).show();
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {  //2
            @Override
            public void onClick(View v) {
                item_position=2;
                Toast.makeText(MainActivity.this,"item_position="+item_position,Toast.LENGTH_LONG).show();
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {  //3
            @Override
            public void onClick(View v) {
                item_position=3;
                Toast.makeText(MainActivity.this,"item_position="+item_position,Toast.LENGTH_LONG).show();
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {  //4
            @Override
            public void onClick(View v) {
                item_position=4;
                Toast.makeText(MainActivity.this,"item_position="+item_position,Toast.LENGTH_LONG).show();
            }
        });

        button11.setOnClickListener(new View.OnClickListener() {  //5
            @Override
            public void onClick(View v) {
                item_position=5;
                Toast.makeText(MainActivity.this,"item_position="+item_position,Toast.LENGTH_LONG).show();
            }
        });

        button12.setOnClickListener(new View.OnClickListener() {  //6
            @Override
            public void onClick(View v) {
                item_position=6;
                Toast.makeText(MainActivity.this,"item_position="+item_position,Toast.LENGTH_LONG).show();
            }
        });

        button13.setOnClickListener(new View.OnClickListener() {  //7
            @Override
            public void onClick(View v) {
                item_position=7;
                Toast.makeText(MainActivity.this,"item_position="+item_position,Toast.LENGTH_LONG).show();
            }
        });

        button14.setOnClickListener(new View.OnClickListener() {  //8
            @Override
            public void onClick(View v) {
                item_position=8;
                Toast.makeText(MainActivity.this,"item_position="+item_position,Toast.LENGTH_LONG).show();
            }
        });

        button15.setOnClickListener(new View.OnClickListener() {  //9
            @Override
            public void onClick(View v) {
                item_position=9;
                Toast.makeText(MainActivity.this,"item_position="+item_position,Toast.LENGTH_LONG).show();
            }
        });

        button16.setOnClickListener(new View.OnClickListener() {  //0
            @Override
            public void onClick(View v) {
                item_position=0;
                Toast.makeText(MainActivity.this,"item_position="+item_position,Toast.LENGTH_LONG).show();
            }
        });


    }
}
