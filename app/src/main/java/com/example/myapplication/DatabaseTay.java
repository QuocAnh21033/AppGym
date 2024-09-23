package com.example.myapplication;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.support.annotation.Nullable;

public class DatabaseTay extends SQLiteOpenHelper {
    public DatabaseTay(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void QueryData(String sqlTay){
        SQLiteDatabase databaseTay = getWritableDatabase();
        databaseTay.execSQL(sqlTay);
    }
    public void INSERT_DONGTACTAY(String ten,String buoc1,String buoc2,String buoc3,byte[] hinh){
        SQLiteDatabase databaseTay=getWritableDatabase();
        String sql="INSERT INTO DongTacTay VALUES(null,?,?,?,?,?)";//? để phiên dịch riêng thú tự (0,1,2,3) bên dưới
        SQLiteStatement statement=databaseTay.compileStatement(sql);//đổ dữ liệu vào các dấu ?
        statement.clearBindings();//khi phân tích xong thì xóa
        statement.bindString(1,ten);//phiên dịch dữ liệu cho dấu ?
        statement.bindString(2,buoc1);
        statement.bindString(3,buoc2);
        statement.bindString(4,buoc3);
        statement.bindBlob(5,hinh);
        statement.executeInsert();

    }
    public Cursor GetData(String sqlTay){
        SQLiteDatabase databaseTay=getReadableDatabase();
        return databaseTay.rawQuery(sqlTay,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

