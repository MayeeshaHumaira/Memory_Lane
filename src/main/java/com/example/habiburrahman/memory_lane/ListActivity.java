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

public class ListActivity extends Activity {
    private static final String TAG="ListActivity";
    dbhandler mydbHandler;
    private ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list);
        myListView=(ListView)findViewById(R.id.listViewID);
        mydbHandler=new dbhandler(this);
        populateListView();

    }
    private void populateListView(){
        Log.d(TAG,"List created");
        final Cursor data=mydbHandler.getNotes();
        final ArrayList<String> notelist=new ArrayList<>();
        while (data.moveToNext()){
            notelist.add(data.getString(1));
        }
        ListAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,notelist);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Note name=new Note(adapterView.getItemAtPosition(i).toString());

                Log.d(TAG,"ash:You clicked on "+name.getNote() );
                Cursor data=mydbHandler.getID(name);
                int itemID=-1;
                while (data.moveToNext()){
                    if(name.getNote().equals(data.getString(1)))
                    {
                        itemID=data.getInt(0);
                        break;
                    }
                }
                if(itemID>-1){
                    Log.d(TAG,"ash:onItemClick:the ID and note is:"+itemID+" "+name.getNote());
                    //Intent i1=new Intent(ListActivity.this,EditNoteActivity.class);

                    Intent i1=new Intent(view.getContext(),EditNoteActivity.class);
                    i1.putExtra("note", name.getNote());
                    i1.putExtra("id", itemID);
                    //i1.putExtra("id=",itemID);
                    //i1.putExtra("note=",name.getNote());
                    startActivity(i1);
                }else{
                    toastMessage("No id associated with that name");
                }
            }
        });
        /*myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO Auto-generated method stub
                //mydbHandler.deleteAll();
                ////Note name=new Note(adapterView.getItemAtPosition(i).toString());
                //Log.v("ash:long clicked","note: " + name.getNote());
                Log.v("ash:long clicked","note: " + i);
                mydbHandler.deleteNote(i+1);
                //if(del)
                    toastMessage("Deleted");
                    //mydbHandler.deleteAll();

                //setContentView(R.layout.layout_list);
                //myListView=(ListView)findViewById(R.id.listViewID);
                //populateListView();
                return true;
            }
        });*/

    }
    private void toastMessage(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    public void NewNote(View v){
        Intent i=new Intent(ListActivity.this,NoteActivity.class);
        startActivity(i);
    }
    public void EditNote(View v){
        Intent i=new Intent(ListActivity.this,EditNoteActivity.class);
        startActivity(i);
    }

}
