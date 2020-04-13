package com.example.habiburrahman.memory_lane;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

import com.example.habiburrahman.memory_lane.Model.Note;
import com.example.habiburrahman.memory_lane.dbhandler.dbhandler;

public class NoteActivity extends Activity {

    EditText newNote;
    Button saveButton;
    dbhandler mydbhandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_note);
        mydbhandler=new dbhandler(this);
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.dateTV);
        textViewDate.setText(currentDate);
        newNote=(EditText) findViewById(R.id.newNoteET);
        saveButton=(Button) findViewById(R.id.saveNoteBtn);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ash:","writing new note");
                String newEntry=newNote.getText().toString();
                if(newNote.length()!=0){
                    Note n=new Note(newEntry);
                    addNote(n);
                    newNote.setText("");
                }else{
                    toastMessage("You must put something in the text field");
                }
                Intent i=new Intent(NoteActivity.this,ListActivity.class);
                startActivity(i);
            }
        });
    }
    public void addNote(Note newEntry){
        boolean insertNote=mydbhandler.addNote(newEntry);
        if(insertNote){
            toastMessage("Note added successfully.");
        }else{
            toastMessage("Something went wrong.");
        }
    }

    private void toastMessage(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    public void list2(View v){
        Intent i=new Intent(NoteActivity.this,ListActivity.class);
        startActivity(i);
    }
}
