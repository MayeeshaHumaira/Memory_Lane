package com.example.habiburrahman.memory_lane;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.habiburrahman.memory_lane.Model.Note;
import com.example.habiburrahman.memory_lane.dbhandler.dbhandler;

import java.text.DateFormat;
import java.util.Calendar;

public class EditNoteActivity extends Activity {

    private String selectedNote;
    private int selectedID;
    EditText newENNote;
    Button saveENButton,delENButton;
    dbhandler mydbhandler;
    int getID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_edit_note);
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        TextView textViewDate = findViewById(R.id.dateENTV);
        textViewDate.setText(currentDate);
        newENNote=(EditText) findViewById(R.id.EditNoteET);
        saveENButton=(Button) findViewById(R.id.saveNoteENBtn);
        delENButton=(Button) findViewById(R.id.deleteENBtn);
        mydbhandler=new dbhandler(this);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            String getName = (String) bd.get("note");
            getID=(int)bd.get("id");
            newENNote.setText(getName);

        }
        saveENButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry=newENNote.getText().toString();
                //Log.d("yayyyyyyyyyyy","yayayayaa");
                /*mydbhandler.delete(getID);
                String newEntry=newENNote.getText().toString();
                Log.d("ash:","edited note"+newEntry);
                if(newENNote.length()!=0){
                    Note n=new Note(newEntry);
                    addNote(n);
                    //editNote(n);
                    newENNote.setText("");
                }else{
                    toastMessage("You must put something in the text field");
                }*/
                editNote(new Note(newEntry));
                Intent i=new Intent(EditNoteActivity.this,ListActivity.class);
                startActivity(i);
            }
        });
        delENButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("yayyyyyyyyyyy","yayayayaa");
                mydbhandler.delete(getID);

                Intent i=new Intent(EditNoteActivity.this,ListActivity.class);
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
    public void editNote(Note editedEntry){
        //boolean insertNote=
                mydbhandler.updateNote(editedEntry,getID);
                //Log.d("ash:","edited note"+editedEntry.getNote());
        /*if(insertNote){
            toastMessage("ash:Note added successfully.");
        }else{
            toastMessage("ash:Something went wrong.");
        }*/
    }


    private void toastMessage(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    public void listEN(View v){
        Intent i=new Intent(EditNoteActivity.this,ListActivity.class);
        startActivity(i);
    }
}
