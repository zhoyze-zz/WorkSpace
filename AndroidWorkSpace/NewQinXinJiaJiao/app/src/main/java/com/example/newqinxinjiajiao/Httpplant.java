package com.example.newqinxinjiajiao;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class Httpplant {

    private String Tag = "Httpplant";  //设置标签
    public String result = " ";   //用于保存接收到的结果
    boolean isGetSucceed = true;//用于判断是否查找成功


    public boolean getresult(String par_phone,String date,String url) {

        final OkHttpClient client = new OkHttpClient();//创建okhttp

        RequestBody formBody = new FormBody.Builder()
                .add("phone",par_phone)
                .add("date",date)
                .build();
        //发起请求
        final Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        //新建一个线程，用于得到服务器响应的参数
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    //回调
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        result = response.body().string();
                        if (result.contains("error")) {
                            isGetSucceed = false; //查找失败，将其置为false
                        } else {
                            Log.d(Tag,result);
                            isGetSucceed = true; //当查找成功时，将其置为true
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
