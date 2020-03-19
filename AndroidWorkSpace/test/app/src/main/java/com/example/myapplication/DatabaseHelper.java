package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    /**
     *
     * @ context  上下文
     * @ name     数据库名称
     * @ factory  游标工厂
     * @ version  版本号
     */
    public DatabaseHelper(@Nullable Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.VERSION_CODE);
    }

    @Override
    /**
     * 第一次创建时被调用
     */
    public void onCreate(SQLiteDatabase db) {
        //创建时的回调
        Log.d(TAG,"创建数据库...");
        //创建字段
        //sql create table table_name(_id integer,name varchar(50),age integer,salary integer);
        String sql = "create table "+Constants.USER_TABLE+"(id integer,money integer)";
        db.execSQL(sql);
        sql = "create table "+Constants.PRIZE_TABLE+"(id integer,name varchar(50),money integer)";
        db.execSQL(sql);
        sql = "create table "+Constants.RECORDING_TABLE+"(id varchar(10),time varchar(20),name varchar(50))";
        db.execSQL(sql);
        sql = "insert into " + Constants.USER_TABLE + "(id,money)values(?,?)";
        db.execSQL(sql,new Object[]{1,0});
        sql = "insert into " + Constants.PRIZE_TABLE + "(id,name,money)values(?,?,?)";
        db.execSQL(sql,new Object[]{1,"一元红包",10});
        db.execSQL(sql,new Object[]{2,"一个蔬菜饼",50});
        db.execSQL(sql,new Object[]{3,"一个煎饼",50});
        db.execSQL(sql,new Object[]{4,"一个掉渣饼",50});
        db.execSQL(sql,new Object[]{5,"桥头排骨",100});
        db.execSQL(sql,new Object[]{6,"波波鱼一份",150});
        db.execSQL(sql,new Object[]{7,"黄焖鸡一份",150});
        db.execSQL(sql,new Object[]{8,"眼线笔",200});
        db.execSQL(sql,new Object[]{9,"烫睫毛器",200});
        db.execSQL(sql,new Object[]{10,"烤肉",250});
        db.execSQL(sql,new Object[]{11,"牛排",250});
        db.execSQL(sql,new Object[]{12,"火锅",250});
        db.execSQL(sql,new Object[]{13,"海底捞",300});
        db.execSQL(sql,new Object[]{14,"小裙子",300});
        db.execSQL(sql,new Object[]{15,"任意愿望",1000});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //升级数据库时的回调
        Log.d(TAG,"升级数据库...");
    }
}
