package com.example.habiburrahman.memory_lane;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.habiburrahman.memory_lane.dbhandler.dbhandler;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends Activity {
    Timer timer;
    dbhandler mydbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_welcome);
        mydbHandler=new dbhandler(this);
        //mydbHandler.onUpgrade();
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent(WelcomeActivity.this,MenuActivity.class);
                startActivity(intent);
                finish();

            }
        },2000);
    }
}
