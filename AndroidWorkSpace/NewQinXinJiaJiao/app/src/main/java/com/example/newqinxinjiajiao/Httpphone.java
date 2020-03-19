package com.example.newqinxinjiajiao;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class Httpphone {

    private String Tag = "Httpphone";  //设置标签
    public String result = " ";   //用于保存接收到的结果
    boolean isGetSucceed = true;//用于判断是否查找成功

    /**
     * post请求后台
     * @param par_phone
     */
    public boolean getresult(String par_phone,String url) {

        final OkHttpClient client = new OkHttpClient();//创建okhttp
        Log.d(Tag, "建立请求表单，添加上传服务器的参数");
        RequestBody formBody = new FormBody.Builder()
                .add("phone",par_phone)
                .build();
        final Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                Log.d(Tag, "建立线程");
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        result = response.body().string();
                        if (result.contains("error")) {
                            isGetSucceed = false;
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
