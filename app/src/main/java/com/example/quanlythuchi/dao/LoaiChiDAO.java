package com.example.quanlythuchi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quanlythuchi.database.DatabaseHelper;
import com.example.quanlythuchi.model.Note4;

import java.util.ArrayList;
import java.util.List;

public class LoaiChiDAO {

    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    public static  final  String SQL_KHOAN_THU = "CREATE TABLE Note4 (" +
            " noteId4 INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " noteTitle4 TEXT," +
            " noteContent4 TEXT," +
            " Gia4 DOUBLE)";
    public static final String TABLE_NOTE = "Note4" ;

    public LoaiChiDAO(Context context) {//khoi tao csdl
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();

    }

    public int insertNote(Note4 note){
        ContentValues values = new ContentValues();
        values.put("noteId4",note.getNoteId4());
        values.put("noteTitle4",note.getNoteTitle4());
        values.put("noteContent4",note.getNoteContent4());
        values.put("Gia4",note.getGia4());

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

    public int updateNote(Note4 note){
        ContentValues values = new ContentValues();
//        values.put("noteId4",note.getNoteId4());
        values.put("noteTitle4",note.getNoteTitle4());
        values.put("noteContent4",note.getNoteContent4());
        values.put("Gia4",note.getGia4());

        try{
            if(db.update(TABLE_NOTE,values,"noteId4=?",new String[]{note.getNoteId4()})>0){
                return 1;
            }

        }
        catch (Exception ex){
            Log.e("KhoanThuDAO", ex.getMessage());
        }
        return -1;
    }

    public void deleteNote(Note4 note) {
        db.delete(TABLE_NOTE,"noteId4=?",new String[]{note.getNoteId4()});

    }

    public List<Note4> getAllNote(){
        List<Note4> ls = new ArrayList<Note4>();
        Cursor cursor = db.query(TABLE_NOTE,null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                Note4 note = new Note4();
                note.setNoteId4(cursor.getString(0));
                note.setNoteTitle4(cursor.getString(1));
                note.setNoteContent4(cursor.getString(2));
                note.setGia4(cursor.getDouble(3));
                ls.add(note);
            }
            while (cursor.moveToNext());
        }
        return ls;
    }


    public Double getThu(){
        double getThu=0;
        String sql = "select sum(Gia4) from Note4";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            getThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        return getThu;
    }

}
