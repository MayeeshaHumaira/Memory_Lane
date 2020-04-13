package com.example.habiburrahman.memory_lane;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu);
    }
    public void menunoteclick(View v){
        Intent i=new Intent(MenuActivity.this,ListActivity.class);
        startActivity(i);
    }
    public void menurecordclick(View v){
        Intent i=new Intent(MenuActivity.this,RecordingActivity.class);
        startActivity(i);
    }
    public void about(View v){
        Intent i=new Intent(MenuActivity.this,AboutActivity.class);
        startActivity(i);
    }
    public void alarm(View v){
        Intent i=new Intent(MenuActivity.this,AlarmActivity.class);
        startActivity(i);
    }
    public void menugallery(View v){
        Intent i=new Intent(MenuActivity.this,GalleryActivity.class);
        startActivity(i);
    }
}
