package com.example.administrator.mystore.mainclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.mystore.R;

import java.util.Timer;
import java.util.TimerTask;

public class slpashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slpash);
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {//一定要记住调用这个方法schedule
            @Override
            public void run() {
                Intent intent = new Intent(slpashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }
}
