package com.example.habiburrahman.memory_lane;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.habiburrahman.memory_lane.Model.Note;
import com.example.habiburrahman.memory_lane.dbhandler.dbhandler;

import java.util.ArrayList;

public class RecordinglistActivity extends Activity {
    private static final String TAG="RecordingActivity";
    dbhandler mydbHandler;
    private ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recordinglist);
        myListView=(ListView)findViewById(R.id.recordinglistViewID);
        mydbHandler=new dbhandler(this);
        //populateRecListView();
    }
    /*private void populateRecListView(){
        Log.d(TAG,"Recording List created");
        Cursor data=mydbHandler.getRecordings();
        final ArrayList<String> reclist=new ArrayList<>();
        while (data.moveToNext()){
            String s=data.getString(1);
            reclist.add(s);
            Log.d("ash:RECLIST:",s);
        }
        ListAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,reclist);
        myListView.setAdapter(adapter);


    }*/
    private void toastMessage(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
