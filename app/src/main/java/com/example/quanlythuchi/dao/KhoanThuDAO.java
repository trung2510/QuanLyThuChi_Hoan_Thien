package com.example.quanlythuchi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quanlythuchi.database.DatabaseHelper;
import com.example.quanlythuchi.model.Note;

import java.util.ArrayList;
import java.util.List;

public class KhoanThuDAO {

    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    public static  final  String SQL_KHOAN_THU = "CREATE TABLE Note (" +
            " noteId INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " noteTitle TEXT" +
            " )";
    public static final String TABLE_NOTE = "Note" ;

    public KhoanThuDAO(Context context) {//khoi tao csdl
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();

    }
    public List<Double> aDouble(){
        List<Double> ls = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NOTE,null,null,null,null,null,null);
        cursor.moveToFirst();

        while (cursor.isAfterLast()==false){

            ls.add(cursor.getDouble(2));

            cursor.moveToNext();
        }
        return ls;
    }

    public int insertNote(Note note){
        ContentValues values = new ContentValues();
        values.put("noteId",note.getNoteId());
        values.put("noteTitle",note.getNoteTitle());
//        values.put("noteContent",note.getNoteContent());

        try{
            if(db.insert(TABLE_NOTE,null,values)>0){
                return 1;
            }

        }
        catch (Exception ex){
            Log.e("KhoanThuDAO", ex.getMessage());
        }
        return -1;
    }

    public int updateNote(Note note){
        ContentValues values = new ContentValues();
        values.put("noteId",note.getNoteId());
        values.put("noteTitle",note.getNoteTitle());
//        values.put("noteContent",note.getNoteContent());

        try{
            if(db.update(TABLE_NOTE,values,"noteId=?",new String[]{note.getNoteId()})>0){
                return 1;
            }

        }
        catch (Exception ex){
            Log.e("KhoanThuDAO", ex.getMessage());
        }
        return -1;
    }

    public void deleteNote(Note note) {
        db.delete(TABLE_NOTE,"noteId=?",new String[]{note.getNoteId()});

    }

    public List<String> getAllNoteTitle(){
        List<String> ls = new ArrayList<String>();
        Cursor cursor = db.query(TABLE_NOTE,null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                ls.add(cursor.getString(1));
            }
            while (cursor.moveToNext());
        }
        return ls;
    }

    public List<Note> getAllNote(){
        List<Note> ls = new ArrayList<Note>();
        Cursor cursor = db.query(TABLE_NOTE,null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setNoteId(cursor.getString(0));
                note.setNoteTitle(cursor.getString(1));
//                note.setNoteContent(cursor.getDouble(2));
                ls.add(note);
            }
            while (cursor.moveToNext());
        }
        return ls;
    }

//    public Double getThu(){
//        double getThu=0;
//        String sql = "select sum(noteContent) from Note";
//        Cursor cursor = db.rawQuery(sql,null);
//        cursor.moveToFirst();
//        while (cursor.isAfterLast()==false){
//            getThu = cursor.getDouble(0);
//            cursor.moveToNext();
//        }
//        return getThu;
//    }



}
