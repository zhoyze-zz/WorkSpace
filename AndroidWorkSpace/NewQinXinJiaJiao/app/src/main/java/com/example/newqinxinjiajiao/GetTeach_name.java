package com.example.newqinxinjiajiao;

/*
    通过点击科目获取老师名字的函数
 */

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class GetTeach_name {

    private String Tag = "GetTeach_name";  //设置标签
    public String result = " ";   //用于保存接收到的结果
    boolean isGetSucceed = true;//用于判断是否查找成功

    /**
     * post请求后台
     * @param subject_name
     */
    public boolean getname(String subject_name) {
        final OkHttpClient client = new OkHttpClient();//创建okhttp
        // 通过okhttp发起post请求
        Log.d(Tag, "建立请求表单，添加上传服务器的参数");//建立请求表单，添加上传服务器的参数
        RequestBody formBody = new FormBody.Builder()
                .add("subject_name",subject_name)
                .build();
        final Request request = new Request.Builder()//发起请求
                .url("http://10.0.2.2/get_teacher_name.php")
                .post(formBody)
                .build();
        //新建一个线程，用于得到服务器响应的参数
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                Log.d(Tag, "建立线程");
                try {//回调
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        result = response.body().string(); //用来存放服务器返回的值
                        if (result.contains("error")) {         //如果查找成功则显示查找成功
                            Log.d(Tag, "查找失败");
                            isGetSucceed = false; //查找失败，将其置为false
                        } else { Log.d(Tag, "查找成功");
                                isGetSucceed = true; //当查找成功时，将其置为true
                        } } else {
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
