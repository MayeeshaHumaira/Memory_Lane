package com.example.habiburrahman.memory_lane.Model;



public class Note {
    //int id;
    String note;
    public Note(){

    }
    public Note(String Note)
    {
        //this.id=Id;
        this.note=Note;
    }

    /*public Note(String Note)
    {
        this.note=Note;
    }*/
    /*public int getId()
    {
        return this.id;
    }
    public void setId(int Id)
    {
        this.id=Id;
    }*/

    public String getNote()
    {
        return this.note;
    }
    public void setNote(String Note)
    {
        this.note=Note;
    }

}
