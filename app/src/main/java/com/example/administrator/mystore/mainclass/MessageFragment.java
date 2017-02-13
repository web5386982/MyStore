package com.example.administrator.mystore.mainclass;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mystore.R;
import com.mancj.materialsearchbar.MaterialSearchBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import static android.R.attr.password;

/**
 * Created by Administrator on 2017/2/7.
 */

public class MessageFragment extends Fragment {
    private static final String TAG = "MessageFragment";
    String username=null;
    String password=null;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_message,null);
//        MaterialSearchBar search=new MaterialSearchBar(getContext(),null);//attributset属性,不行弄就直接null
//        search.setTextHintColor(Color.parseColor("#3456aa"));

        new Thread(new Runnable() {
            @Override
            public void run() {
//                netvisitconstom();//传统法方式访问网络在这不用,只是写一下.
            }
        }).start();

        return view;
    }

    private void netvisitconstom() {
        OkHttpClient client=new OkHttpClient().newBuilder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))//添加日志拦截器
                .connectTimeout(1000,null)
                .readTimeout(1000,null)
                .build();

//        RequestBody requestBody = constomgetRequestBody();最原始的方式添加请求
        RequestBody requestBody =  new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .build();
        Request request = new Request.Builder()
                .url("http://baidu.com")
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: "+response.toString() );
            }
        });
    }

    @NonNull
    private RequestBody constomgetRequestBody() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username",username);
            jsonObject.put("password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String json=jsonObject.toString();
        return RequestBody.create(null, json);
    }
}
