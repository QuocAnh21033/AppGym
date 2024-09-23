package com.example.myapplication;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.support.annotation.Nullable;

public class DatabaseChan extends SQLiteOpenHelper {
    public DatabaseChan(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void QueryData(String sqlChan){
        SQLiteDatabase databaseChan = getWritableDatabase();
        databaseChan.execSQL(sqlChan);
    }
    public void INSERT_DONGTACCHAN(String ten,String buoc1,String buoc2,String buoc3,byte[] hinh){
        SQLiteDatabase databaseChan=getWritableDatabase();
        String sql="INSERT INTO DongTacChan VALUES(null,?,?,?,?,?)";//? để phiên dịch riêng thú tự (0,1,2,3) bên dưới
        SQLiteStatement statement=databaseChan.compileStatement(sql);//đổ dữ liệu vào các dấu ?
        statement.clearBindings();//khi phân tích xong thì xóa
        statement.bindString(1,ten);//phiên dịch dữ liệu cho dấu ?
        statement.bindString(2,buoc1);
        statement.bindString(3,buoc2);
        statement.bindString(4,buoc3);
        statement.bindBlob(5,hinh);
        statement.executeInsert();

    }
    public Cursor GetData(String sqlBung){
        SQLiteDatabase databaseBung=getReadableDatabase();
        return databaseBung.rawQuery(sqlBung,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

