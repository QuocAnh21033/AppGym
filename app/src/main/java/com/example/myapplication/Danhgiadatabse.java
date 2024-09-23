package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Danhgiadatabse extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "danhgiadatabase.db";

    public Danhgiadatabse(Context context){
        super(context,DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table DanhGia(id INTEGER primary key AUTOINCREMENT, fullname TEXT,noidung TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists DanhGia");
    }
    public Boolean addNhanXet(String fullname,String noidung){
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname",fullname);
        contentValues.put("noidung",noidung);
        long result = sqLiteDatabase.insert("DanhGia", null,contentValues);
        if (result ==-1) return false;
        else return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from DanhGia ",null);
        return cursor;
    }
    public  Boolean Delete(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        long result = db.delete("DanhGia" , "id=?",new String[]{String.valueOf(id)});
        if (result ==-1)
            return false;
        else
            return true;
    }
}
