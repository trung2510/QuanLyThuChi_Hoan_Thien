package com.example.quanlythuchi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quanlythuchi.database.DatabaseHelper;
import com.example.quanlythuchi.model.Note3;

import java.util.ArrayList;
import java.util.List;

public class LoaiThuDAO {

    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    public static  final  String SQL_KHOAN_THU = "CREATE TABLE [Note3] (\n" +
            "[noteId3] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
            "[noteTitle3] TEXT  NULL,\n" +
            "[noteContent3] TEXT  NULL,\n" +
//            "[khoanThu] TEXT  NULL,\n" +
            "[Gia3] FLOAT  NULL\n" +
            ")";

    public static final String TABLE_NOTE = "Note3" ;

    public LoaiThuDAO(Context context) {//khoi tao csdl
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();

    }

    public int insertNote(Note3 note){
        ContentValues values = new ContentValues();
        values.put("noteId3",note.getNoteId3());
        values.put("noteTitle3",note.getNoteTitle3());
        values.put("noteContent3",note.getNoteContent3());
//        values.put("khoanThu",note.getKhoanThu());
        values.put("Gia3",note.getGia3());

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

    public int updateNote(Note3 note){
        ContentValues values = new ContentValues();
        values.put("noteId3",note.getNoteId3());
        values.put("noteTitle3",note.getNoteTitle3());
        values.put("noteContent3",note.getNoteContent3());
//        values.put("khoanThu",note.getKhoanThu());
        values.put("Gia3",note.getGia3());

        try{
            if(db.update(TABLE_NOTE,values,"noteId3=?",new String[]{note.getNoteId3()})>0){
                return 1;
            }

        }
        catch (Exception ex){
            Log.e("KhoanThuDAO", ex.getMessage());
        }
        return -1;
    }

    public void deleteNote(Note3 note) {
        db.delete(TABLE_NOTE,"noteId3=?",new String[]{note.getNoteId3()});

    }

    public List<Note3> getAllNote(){
        List<Note3> ls = new ArrayList<Note3>();
        Cursor cursor = db.query(TABLE_NOTE,null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                Note3 note = new Note3();
                note.setNoteId3(cursor.getString(0));
                note.setNoteTitle3(cursor.getString(1));
                note.setNoteContent3(cursor.getString(2));
                note.setGia3(cursor.getFloat(3));
                ls.add(note);
            }
            while (cursor.moveToNext());
        }
        return ls;
    }


    public Double getThu(){
        double getThu=0;
        String sql = "select sum(Gia3) from Note3";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            getThu = cursor.getDouble(0);
            cursor.moveToNext();
        }
        return getThu;
    }
//    public int getID_khoanThu(){
//        int id = 0;
//
//        return id;
//    };


}
