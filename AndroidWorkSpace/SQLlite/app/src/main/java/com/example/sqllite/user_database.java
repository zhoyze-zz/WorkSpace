package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class user_database  extends SQLiteOpenHelper {
    public user_database(Context context) {
        super(context, "user_db",null,1);
    }

    //数据库第一次创建时调用该方法
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table user(id integer primary key autoincrement,username varchar(20),paswd varchar(20))"; //数据库执行语句
        sqLiteDatabase.execSQL(sql);
    }
    //数据库版本号更新时调用
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    //添加数据
    public void adddata(SQLiteDatabase sqLiteDatabase){
        ContentValues values=new ContentValues();
        values.put("username","张三");
        values.put("paswd","12222");
        sqLiteDatabase.insert("user",null,values);
        sqLiteDatabase.close();
    }

    //删除数据方法
    public void delete(SQLiteDatabase sqLiteDatabase) {
/*第一个参数：表名；第二个参数：需要删除的属性名，？代表占位符；第三
个参数：属性名的属性值*/
        sqLiteDatabase.delete("user","username=?", new String[]{"张三"});
        sqLiteDatabase.close();
    }

    //修改数据方法
    public void update(SQLiteDatabase sqLiteDatabase){
        //创建一个ContentValues对象
        ContentValues values=new ContentValues();
        //以键值对的形式插入
        values.put("paswd","22233333");
        //执行修改的方法(修改username=张三的密码)
        sqLiteDatabase.update("user",values,"username=?",new String[]{"张三"});
        sqLiteDatabase.close();
    }



}
