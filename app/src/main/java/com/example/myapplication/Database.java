package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.content.ContentValues;

public class Database extends SQLiteOpenHelper {
    public static final String databaseName = "TaiKhoan.db";

    public Database(@Nullable Context context) {
        super(context, "Taikhoan.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table TaiKhoan(id INTEGER primary key AUTOINCREMENT, fullname TEXT,email TEXT ,username TEXT , password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists TaiKhoan");
    }
    public Boolean insertData(String fullname,String email,String username, String password){
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname",fullname);
        contentValues.put("email",email);
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = sqLiteDatabase.insert("TaiKhoan", null,contentValues);
        if (result ==-1) return false;
        else return true;
    }
    public  Boolean checkname(String username){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from TaiKhoan where username = ?",new String[]{username});
        if (cursor.getCount()> 0)
            return true;
        else
            return false;
    }

    public  Boolean checkpass(String username,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from TaiKhoan where username = ?  and  password = ?",new String[]{username,password});
        if (cursor.getCount()> 0)
            return true;
        else
            return false;
    }
    public  Cursor getAllData(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor= db.rawQuery("select id,fullname,email,username,password from TaiKhoan ",null);
        return cursor;
    }
    public  Boolean Delete(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        long result = db.delete("TaiKhoan" , "id=?",new String[]{String.valueOf(id)});
        if (result ==-1)
            return false;
        else
            return true;
    }
}
