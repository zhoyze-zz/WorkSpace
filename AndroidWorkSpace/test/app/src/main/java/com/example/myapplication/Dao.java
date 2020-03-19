package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * 这个类用于操作数据库的增删查改
 */
public class Dao {

    private List<Prize> prizeList = new ArrayList<>();;

    private static final String TAG = "Dao";
    private final DatabaseHelper mhelper;

    public Dao(Context context) {
        //创建数据库
        mhelper = new DatabaseHelper(context);
    }

    public void insert(String name,String date){
        SQLiteDatabase db = mhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //添加数据
        Random ra = new Random();
        values.put("id",ra.nextInt(100)+1);
        values.put("name",name);
        values.put("time",date);
        db.insert(Constants.RECORDING_TABLE,null,values);
        db.close();

}
    public int delete(String name,String id){
        SQLiteDatabase db = mhelper.getWritableDatabase();
        String qsl = "delete from "+Constants.RECORDING_TABLE+" where name = '"+ name +"' and id = '"+id+"' ";
        db.execSQL(qsl);
        int result = -1;                   //db.delete(Constants.RECORDING_TABLE,"name = ？",new String[]{name});
        //如果是-1则删除成功
        Log.d(TAG,"delete"+result);
        db.close();
        return result;
    }
    public void update(int money){
        SQLiteDatabase db = mhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("money",money);
        db.update(Constants.USER_TABLE,values,null,null);
        db.close();

    }
    public int querytime(){
        int money = 0;
        SQLiteDatabase db = mhelper.getWritableDatabase();

        Cursor cursor = db.query(false,Constants.USER_TABLE,null,null,null,null,null,null,null);

        while (cursor.moveToNext()){
            int index = cursor.getColumnIndex("money");
            money = cursor.getInt(index);
        }
        cursor.close();
        db.close();
        return money;
    }

    public List<Prize> findall() {
        SQLiteDatabase db = mhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+Constants.RECORDING_TABLE,null);
        while (cursor.moveToNext()){
            Prize prize = new Prize(cursor.getString(0),cursor.getString(2),cursor.getString(1));
            Log.d(TAG," "+cursor.getString(1)+cursor.getString(0));
            prizeList.add(prize);
        }
        return prizeList;
    }

}
