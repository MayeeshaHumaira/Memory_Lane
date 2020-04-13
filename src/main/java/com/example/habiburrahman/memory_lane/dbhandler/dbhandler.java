package com.example.habiburrahman.memory_lane.dbhandler;



import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import com.example.habiburrahman.memory_lane.Model.Note;



public class dbhandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="notesManager";
    private static final String TABLE_NAME="notes";
    private static final String TABLE_NAME1="passwordT";
    private static final String KEY_ID="id";
    private static final String KEY_ID1="pass_id";
    private static final String KEY_PASSWORD="passwordC";
    private static final String KEY_NOTE="note";
    //private static final String KEY_REC="recording_directories";


    public dbhandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTES_TABLE="CREATE TABLE "+ TABLE_NAME +"("
                + KEY_ID +" INTEGER PRIMARY KEY,"
                //+ KEY_PASSWORD +" TEXT,"
                + KEY_NOTE +" TEXT" +")";


        db.execSQL(CREATE_NOTES_TABLE);
        String CREATE_PASSWORD_TABLE="CREATE TABLE "+ TABLE_NAME1 +"("
                + KEY_ID1 +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                //+ KEY_PASSWORD +" TEXT,"
                + KEY_PASSWORD +" TEXT" +")";


        //db.execSQL(CREATE_PASSWORD_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(sql);
        String sql1 = "DROP TABLE IF EXISTS "+TABLE_NAME1;
        db.execSQL(sql1);
        onCreate(db);
    }

    public boolean addNote(Note note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(KEY_NOTE, note.getNote());
        Log.d("ash:ADDING NOTE:"+note+"to ",TABLE_NAME);
        long result=db.insert(TABLE_NAME, null,value);
        if(result==-1)
            return false;
        else return true;

    }
    public boolean addPassword(String pword)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(KEY_PASSWORD, pword);
        Log.d("ash:ADDING PASSWORD:"+pword+"to ",TABLE_NAME1);
        long result=db.insert(TABLE_NAME1, null,value);
        Log.d("ash:table1:",TABLE_NAME1);
        Log.d("ash:table1 result=:",""+result);
        if(result==-1)
            return false;
        else return true;
        /*SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO PASSWORDT(PASSWORD)" +
                "VALUES('"+pword+"')";
        db.execSQL(query);
        Log.d("ash:ADDING PASSWORD:"+pword+"to ",TABLE_NAME1);*/


    }

    public Note getSingleNote(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT ID,NOTE FROM NOTES WHERE ID = "+id;
        Cursor cursor = db.rawQuery(query, null);
        //Cursor cursor = db.query(TABLE_NAME, new String[] {KEY_ID,KEY_NAME,KEY_CONTACTNO}, "Id=?",new String[]{String.valueOf(id)} ,null, null,null, null);
        Note myNote = null;
        if(cursor.moveToFirst())
        {
            myNote=new Note(cursor.getString(1));
        }

        return myNote;
    }
    public List<Note> getAllNote()
    {
        List<Note> mynotesList=new ArrayList<Note>();

        String selectquery="SELECT * FROM "+ TABLE_NAME;// where phoneno LIKE '017%'";

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(selectquery, null);

        if(cursor.moveToFirst())
        {
            do
            {
                Note note= new Note(cursor.getString(1));
                mynotesList.add(note);
            }while(cursor.moveToNext());
        }

        return mynotesList;
    }
    ///we used this one
    public Cursor getNotes() {
        SQLiteDatabase db = this.getWritableDatabase();

        String query="SELECT * FROM "+ TABLE_NAME;
        Cursor data=db.rawQuery(query, null);
        return data;
    }
    public Cursor getPassword() {
        SQLiteDatabase db = this.getWritableDatabase();

        String query="SELECT * FROM "+ TABLE_NAME1;
        Cursor data=db.rawQuery(query, null);
        return data;
    }
    public Cursor getID(Note n) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query="SELECT * FROM "+ TABLE_NAME;
        Cursor data=db.rawQuery(query, null);
        return data;
    }
    public Cursor getID1(String n) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query="SELECT * FROM "+ TABLE_NAME1;
        Cursor data=db.rawQuery(query, null);
        return data;
    }

    public boolean loginValidation(String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM PASSWORDT";
        Cursor cursor=db.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
            cursor.close();
            db.close();
            return true;
        }

        cursor.close();
        db.close();
        return false;
    }

    public void updateNote(Note note,int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        String query = "UPDATE NOTES SET NOTE='"+note.getNote()+"' WHERE ID = "+id ;
        db.execSQL(query);
        db.close();
    }
    public void updatePassword(String rec,int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        String query = "UPDATE PASSWORDT WHERE ID = "+id ;
        db.execSQL(query);
        db.close();
    }
    public void delete(int id)
    {
        /*DELETE
                FROM
        table
                WHERE
        search_condition;*/
        SQLiteDatabase db=this.getWritableDatabase();
        String query="DELETE FROM NOTES WHERE ID="+id;
        db.execSQL(query);
    }
    public void deleteNote(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String table = TABLE_NAME;
        String whereClause = "id=?";
        String[] whereArgs = new String[] { String.valueOf(id) };
        db.delete(table, whereClause, whereArgs);
        /*
        String table = TABLE_NAME;
        String whereClause ="note=?";
        String[] whereArgs = new String[] { n.getNote() };
        db.delete(table, whereClause, whereArgs);
        Log.v("ash:trying to delete","note: " + n.getNote());*/
        //return db.delete(TABLE_NAME, KEY_ID + "=" + id, null) > 0;

        //String query = "DELETE from NOTES WHERE ID="+id;
        /*db.execSQL(query);

        //	db.delete(TABLE_NAME, KEY_ID+"=?", new String[]{String.valueOf(id)});
        db.close();*/
    }
    SQLiteDatabase db ;
    public void deleteAll()
    {
        String sql = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
        //return db.delete(TABLE_NAME,null,null);
    }
    public void deletePassword(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query = "DELETE from PASSWORDT WHERE ID="+id;
        db.execSQL(query);

        //	db.delete(TABLE_NAME, KEY_ID+"=?", new String[]{String.valueOf(id)});
        db.close();
    }

}


