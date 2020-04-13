package com.example.habiburrahman.memory_lane;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.habiburrahman.memory_lane.dbhandler.dbhandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class RecordingActivity extends Activity {
    Button btnRecord,btnStopRecord,btnPlay,btnStop,btnPreviousRecs;
    dbhandler mydbhandler;
    String pathSave="";
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    final int REQUEST_PERMISSION_CODE=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recording);
        final String myfolder=Environment.getExternalStorageDirectory().getAbsolutePath()+"/MemoryLane";
        File f=new File(myfolder);
        if (f.exists() == false) {
            f.mkdirs();
        }

        //Init View
        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnRecord = (Button)findViewById(R.id.btnStartRecord);
        btnStopRecord = (Button)findViewById(R.id.btnStopRecord);
        btnStop = (Button)findViewById(R.id.btnStop);
        btnPreviousRecs = (Button)findViewById(R.id.btnRecordingList);
        mydbhandler=new dbhandler(this);


        if(checkPermissionFromDevice()){
            btnRecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US);
                    Date now = new Date();
                    String fileName = formatter.format(now) ;
                    /*pathSave=myfolder+"/"
                            + UUID.randomUUID().toString()+"_audio_record.3gp";*/
                    pathSave=myfolder+"/" +fileName+"_audio_record.3gp";
                    setupMediaRecorder();
                    try{
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    btnPlay.setEnabled(false);
                    btnStop.setEnabled(false);
                    Toast.makeText(RecordingActivity.this, "Recording...",Toast.LENGTH_SHORT).show();

                }
            });
            btnStopRecord.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    toastMessage("Saving recording...");
                    mediaRecorder.stop();
                    btnStopRecord.setEnabled(false);
                    btnPlay.setEnabled(true);
                    btnRecord.setEnabled(true);
                    btnStop.setEnabled(false);
                    /*boolean res=mydbhandler.addRecordingDir(pathSave);
                    if(res)
                    {
                        Log.d("ash:","added recording to database"+pathSave);
                    }else
                    {
                        Log.d("ash:","recording not added");
                    }*/

                }
            });
            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    btnStop.setEnabled(true);
                    btnStopRecord.setEnabled(false);
                    btnRecord.setEnabled(false);
                    mediaPlayer = new MediaPlayer();
                    try{
                        mediaPlayer.setDataSource(pathSave);
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.start();
                    Toast.makeText(RecordingActivity.this, "Playing...", Toast.LENGTH_SHORT).show();

                }
            });
            btnStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toastMessage("Stopping audio...");
                    btnStopRecord.setEnabled(false);
                    btnRecord.setEnabled(true);
                    btnStop.setEnabled(false);
                    btnPlay.setEnabled(true);

                    if(mediaPlayer != null)
                    {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        setupMediaRecorder();
                    }


                }
            });

        }
        else {
            requestPermission();
        }

        btnPreviousRecs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri selectedUri = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + "/MemoryLane");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(selectedUri, "resource/folder");
                startActivity(intent);


            }
        });
    }

    private void setupMediaRecorder() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(pathSave);
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        },REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case REQUEST_PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();

            }
            break;
        }
    }

    private boolean checkPermissionFromDevice(){
        int write_external_storage_result= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio_result=ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        return  write_external_storage_result== PackageManager.PERMISSION_GRANTED&&
                record_audio_result==PackageManager.PERMISSION_GRANTED;
    }
    private void toastMessage(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    /*public void recList(View v){
        Intent i=new Intent(RecordingActivity.this,RecordinglistActivity.class);
        Log.d("ash:","Entered recording list");
        //Toast.makeText(this,"Entered recording list",Toast.LENGTH_SHORT).show();
        startActivity(i);
    }*/
}
