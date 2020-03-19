package com.example.newqinxinjiajiao;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class get_Select_Teacher {

    private String Tag = "get_Select_Teacher";
    public String result = " ";
    boolean isGetSucceed = false;//用于判断是否查找成功

    /**
     * post请求后台
     * @param subject
     * @param grade
     */
    public boolean get_Teach(String subject, String grade) {

        final OkHttpClient client = new OkHttpClient();
        Log.d(Tag, "建立请求表单，添加上传服务器的参数");
        RequestBody formBody = new FormBody.Builder()
                .add("subject", subject)
                .add("grade", grade)
                .build();
        //发起请求
        final Request request = new Request.Builder()
                .url("http://10.0.2.2/get_select_teacher.php")
                .post(formBody)
                .build();
        //新建一个线程，用于得到服务器响应的参数
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {//回调
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        result = response.body().string();
                        if (result.contains("error")) {
                            isGetSucceed = false;
                        } else {
                            isGetSucceed = true;
                        }
                    } else {
                        throw new IOException("Unexpected code:" + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return isGetSucceed;
    }
}
