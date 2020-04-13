package com.example.habiburrahman.memory_lane;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

public class MyBroadcastReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Its Time!!!.",Toast.LENGTH_SHORT).show();
        //vivrate the mobile phone
        Vibrator vibrator = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        //vibrator.vibrate(1000);
        long[] pattern = { 0, 1000,1000,1000,1000,1000 };
        vibrator.vibrate(pattern , -1);


    }
}
