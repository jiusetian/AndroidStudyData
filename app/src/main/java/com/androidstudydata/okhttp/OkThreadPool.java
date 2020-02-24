package com.androidstudydata.okhttp;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author：Alex
 * Date：2019/12/31
 * Note：
 */
public class OkThreadPool {


    public void excuteTest() {

        //同步请求
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://myproject.com/helloworld.txt")
                .build();
        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //异步请求
        OkHttpClient client2 = new OkHttpClient();
        Request request2 = new Request.Builder()
                .url("http://myproject.com/helloworld.txt")
                .build();
        client2.newCall(request2).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("OkHttp", "Call Failed:" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("OkHttp", "Call succeeded:" + response.message());
            }
        });
    }
}
