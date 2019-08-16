package com.example.quanlythuchi.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quanlythuchi.dao.KhoanChiDAO;
import com.example.quanlythuchi.dao.KhoanThuDAO;
import com.example.quanlythuchi.dao.LoaiChiDAO;
import com.example.quanlythuchi.dao.LoaiThuDAO;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static  final String DATABASE_NAME ="dbThuChi.db";
    public static  final int VERSION =1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(KhoanThuDAO.SQL_KHOAN_THU);
        db.execSQL(KhoanChiDAO.SQL_KHOAN_THU);
        db.execSQL(LoaiThuDAO.SQL_KHOAN_THU);
        db.execSQL(LoaiChiDAO.SQL_KHOAN_THU);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" drop table if exists " + KhoanThuDAO.TABLE_NOTE);
        db.execSQL(" drop table if exists " + KhoanChiDAO.TABLE_NOTE);
        db.execSQL(" drop table if exists " + LoaiThuDAO.TABLE_NOTE);
        db.execSQL(" drop table if exists " + LoaiChiDAO.TABLE_NOTE);
    }
}
