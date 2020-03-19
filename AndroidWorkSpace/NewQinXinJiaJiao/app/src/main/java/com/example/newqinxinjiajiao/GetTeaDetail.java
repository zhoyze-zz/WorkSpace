package com.example.newqinxinjiajiao;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class GetTeaDetail {

    private String Tag = "GetTeaDetial";
    public String result = " ";
    boolean isGetSucceed = true;//用于判断是否查找成功

    /**
     * post请求后台
     * @param teachname
     */
    public boolean getDetail(String teachname) {

        final OkHttpClient client = new OkHttpClient();//创建okhttp

        Log.d(Tag, "建立请求表单，添加上传服务器的参数");
        RequestBody formBody = new FormBody.Builder()
                .add("teachname", teachname)
                .build();
        final Request request = new Request.Builder()
                .url("http://10.0.2.2/teach_detail.php")
                .post(formBody)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                Log.d(Tag, "建立线程");

                try {
                    //回调
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        result = response.body().string();
                        if (result.contains("error")) {
                            Log.d(Tag, "查找失败");
                            isGetSucceed = false; //查找失败，将其置为false
                        } else {
                            Log.d(Tag, "查找成功");
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
