package com.example.json;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            InputStreamReader isr = new InputStreamReader(getAssets().open("abc.json"), "UTF-8"); //获取输入流，打开assest中的abc.json文件
            BufferedReader br = new BufferedReader(isr); //输入流中读取文本并缓冲字符,以便有效地读取字符数组和行
            String line; //定义字符转line
            StringBuilder bulider = new StringBuilder();
            while ((line = br.readLine()) != null) { //读取一行数据
                bulider.append(line);  //将line中的一行数据添加到builder中
            }
            JSONObject root = new JSONObject(bulider.toString()); //转化为json对象
            System.out.println("cat = " + root.getString("cat")); //显示cat 后的数据
            JSONArray array = root.getJSONArray("languages");
            for (int i = 0; i < array.length(); i++) {
                JSONObject lan = array.getJSONObject(i);
                System.out.println("---------------------");
                System.out.println("id = " + lan.getInt("id")); //读取显示id后数据
                System.out.println("ide = " + lan.getString("ide")); //读取显示ide后数据
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

