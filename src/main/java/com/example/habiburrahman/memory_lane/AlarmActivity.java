package com.example.habiburrahman.memory_lane;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.habiburrahman.memory_lane.Model.TimePickerFragment;
import com.example.habiburrahman.memory_lane.MyBroadcastReciver;

import java.util.Calendar;

public class AlarmActivity extends Activity {
    /*TextView tv;
    Calendar currentTime;
    int hour,minute;
    String format;*/
    int hour,mminute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_alarm);
        /*Button button=(Button)findViewById(R.id.timePickerBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker=new TimePickerFragment();
                timePicker.show(getFragmentManager(),"time picker");
                //timePicker.show(getSupportFragmentManager(),"time picker");
                //timePicker.show(getSu,"time picker");
            }
        });*/
       /* tv=(TextView)findViewById(R.id.textView);
        currentTime=Calendar.getInstance();

        hour=currentTime.get(Calendar.HOUR_OF_DAY);
        minute=currentTime.get(Calendar.MINUTE);
        selectedTimeFormat(hour);
        tv.setText(hour + " : "+ minute+" "+format);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(AlarmActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedTimeFormat(hourOfDay);
                        tv.setText(hourOfDay+ " : "+ minute+" "+format);

                    }
                },hour,minute,false);
                timePickerDialog.show();
            }
        });*/
    }
    public void setTime(View v){
        Calendar cal2 = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog
                (this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hour=hourOfDay;
                mminute=minute;
            }
        },cal2.get(Calendar.HOUR_OF_DAY),cal2.get(Calendar.MINUTE),false);
        timePickerDialog.show();
    }
    public void startAlarm(View v){
        Intent intent2 = new Intent(this,MyBroadcastReciver.class);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast
                (this.getApplicationContext(),23433,intent2, 0);
        AlarmManager alarmManager =(AlarmManager)getSystemService(ALARM_SERVICE);
        Calendar cal_alarm = Calendar.getInstance();
        cal_alarm.set(Calendar.HOUR_OF_DAY,hour);
        cal_alarm.set(Calendar.MINUTE,mminute);
        cal_alarm.set(Calendar.SECOND,0);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(),pendingIntent2);
            Toast.makeText(this,"Alarm has been set",Toast.LENGTH_SHORT).show();
        }
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT){
            alarmManager.set(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(),pendingIntent2);
            Toast.makeText(this,"Alarm<Kikat",Toast.LENGTH_SHORT).show();
        }
    }
    /*public void selectedTimeFormat(int hour){
        if(hour>=12){
            //hour+=12;
            format="PM";
        }else{
            format="AM";
        }

       /* if(hour==0){
            hour+=12;
            format="AM";

        }else if(hour==12){
            format="PM";
        }else if(hour>12){
            hour-=12;
            format="PM";
        }else{
            format="AM";
        }
    }*/
}
