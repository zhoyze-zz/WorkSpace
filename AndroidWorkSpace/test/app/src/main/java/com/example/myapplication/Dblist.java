package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Dblist extends AppCompatActivity {

    private ListView listView;
    private List<Prize> prizeList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dblist);

        Dao dao = new Dao(this);
        prizeList = dao.findall();

        listView=(ListView)findViewById(R.id.listview1);
        listView.setAdapter(new Myadapter());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView name1 = (TextView)view.findViewById(R.id.name);
                TextView id1 = (TextView)view.findViewById(R.id.id);
                final String name = name1.getText().toString();
                final String id11 = id1.getText().toString();
                Toast.makeText(getApplicationContext(),name1.getText(),Toast.LENGTH_SHORT).show();

                AlertDialog.Builder dialog = new AlertDialog.Builder(Dblist.this);
                dialog.setIcon(R.mipmap.ic_launcher_round);
                dialog.setTitle("提           示");
                dialog.setMessage("是否使用"+name1.getText());
                dialog.setCancelable(false);    //设置是否可以通过点击对话框外区域或者返回按键关闭对话框
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Dao dao1 = new Dao(Dblist.this);
                        int result = dao1.delete(name,id11);
                        if (result == -1){
                            Toast.makeText(Dblist.this, "成功使用", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Dblist.this, "该券不存在", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Dblist.this, "取消", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                //////////////////////////////////////////////////////////
            }
        });

    }

    private class Myadapter extends BaseAdapter{

        @Override
        // getCount()控制listview里面总共有多少个条目
        public int getCount() {
            int count = prizeList.size();
            return count;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //得到某个位置对应的person对象
            Prize prize = prizeList.get(position);
            //视图填充器：inflate ；这个为内部类，要使用上下文则为
            View view = View.inflate(Dblist.this,R.layout.array_adapt,null);

            TextView id11 = (TextView)view.findViewById(R.id.id);
            TextView name1 = (TextView)view.findViewById(R.id.name);
            TextView date1 = (TextView)view.findViewById(R.id.date);

            id11.setText(prize.getId());
            name1.setText(prize.getName());
            date1.setText(prize.getDate());

            return view;
        }
    }
}
