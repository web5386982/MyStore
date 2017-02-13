package com.example.administrator.mystore.network;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator on 2017/2/9.
 */

public class EasyClient extends AppCompatActivity {
     private static EasyClient esyClient;

    /**
     * 只创建一次客户端
     * @return
     */
    public static EasyClient getInstance(){
     if(esyClient==null){
         esyClient=new EasyClient();
     }
        return esyClient;
    }
     public OkHttpClient HttpClient(){
         OkHttpClient client = new OkHttpClient().newBuilder()
                 .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                 .build();
         return client;
     }
    public Call registerOrLogin(String username,String password,String uri){
        RequestBody body=new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .build();
        Request request=new Request.Builder()

                .url(uri)
                .post(body)
                .build();

        return HttpClient().newCall(request);
    }


}
