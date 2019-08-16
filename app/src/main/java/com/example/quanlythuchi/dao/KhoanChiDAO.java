package com.example.quanlythuchi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quanlythuchi.database.DatabaseHelper;
import com.example.quanlythuchi.model.Note;
import com.example.quanlythuchi.model.Note2;

import java.util.ArrayList;
import java.util.List;

public class KhoanChiDAO {

    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    public static  final  String SQL_KHOAN_THU = "CREATE TABLE Note2 (" +
            " noteId2 INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " noteTitle2 TEXT" +
            " )";
    public static final String TABLE_NOTE = "Note2" ;

    public KhoanChiDAO(Context context) {//khoi tao csdl
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();

    }

    public int insertNote(Note2 note){
        ContentValues values = new ContentValues();
        values.put("noteId2",note.getNoteId2());
        values.put("noteTitle2",note.getNoteTitle2());



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

    public int updateNote(Note2 note){
        ContentValues values = new ContentValues();
        values.put("noteId2",note.getNoteId2());
        values.put("noteTitle2",note.getNoteTitle2());
//        values.put("noteContent2",note.getNoteContent2());

        try{
            if(db.update(TABLE_NOTE,values,"noteId2=?",new String[]{note.getNoteId2()})>0){
                return 1;
            }

        }
        catch (Exception ex){
            Log.e("KhoanThuDAO", ex.getMessage());
        }
        return -1;
    }

    public void deleteNote(Note2 note) {
        db.delete(TABLE_NOTE,"noteId2=?",new String[]{note.getNoteId2()});

    }

    public List<Note2> getAllNote(){
        List<Note2> ls = new ArrayList<Note2>();
        Cursor cursor = db.query(TABLE_NOTE,null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                Note2 note = new Note2();
                note.setNoteId2(cursor.getString(0));
                note.setNoteTitle2(cursor.getString(1));
//                note.setNoteContent2(cursor.getDouble(2));
                ls.add(note);
            }
            while (cursor.moveToNext());
        }
        return ls;
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


//    public Double getThu(){
//        double getThu=0;
//        String sql = "select sum(noteContent2) from Note2";
//        Cursor cursor = db.rawQuery(sql,null);
//        cursor.moveToFirst();
//        while (cursor.isAfterLast()==false){
//            getThu = cursor.getDouble(0);
//            cursor.moveToNext();
//        }
//        return getThu;
//    }

}
