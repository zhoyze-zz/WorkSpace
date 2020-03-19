package com.example.newqinxinjiajiao;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class Resrve_Teacher {

    private String Tag = "Resrve_Teacher";  //设置标签
    public String result = "";   //用于保存接收到的结果
    boolean isGetSucceed = true;//用于判断是否查找成功

    /**
     * post请求后台
     * @param subject
     * @param par_phone
     * @param teach_phone
     * @param data
     * @param teach_name
     */
    public boolean Reserve(String par_phone, String teach_phone, String teach_name, String subject,String data) {

        final OkHttpClient client = new OkHttpClient();//创建okhttp
        //通过okhttp发起post请求
        //建立请求表单，添加上传服务器的参数
        Log.d(Tag, "建立请求表单，添加上传服务器的参数");
        RequestBody formBody = new FormBody.Builder()
                .add("par_name",par_phone)
                .add("teach_phone",teach_phone)
                .add("teach_name",teach_name)
                .add("subject", subject)
                .add("data", data)
                .build();
        //发起请求
        final Request request = new Request.Builder()
                .url("http://10.0.2.2/par_reserve.php")
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
