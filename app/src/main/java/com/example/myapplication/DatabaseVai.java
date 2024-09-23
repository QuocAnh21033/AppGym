package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.support.annotation.Nullable;

public class DatabaseVai extends SQLiteOpenHelper {
    public DatabaseVai(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //truy vấn nhập dữ liệu
    public void QueryData(String sqlVai){
        SQLiteDatabase databaseVai = getWritableDatabase();
        databaseVai.execSQL(sqlVai);
    }
    public void INSERT_DONGTACVAI(String ten,String buoc1,String buoc2,String buoc3,byte[] hinh){
        SQLiteDatabase databaseVai=getWritableDatabase();
        String sql="INSERT INTO DongTacVai VALUES(null,?,?,?,?,?)";//? để phiên dịch riêng thú tự (0,1,2,3) bên dưới
        SQLiteStatement statement=databaseVai.compileStatement(sql);//đổ dữ liệu vào các dấu ?
        statement.clearBindings();//khi phân tích xong thì xóa
        statement.bindString(1,ten);//phiên dịch dữ liệu cho dấu ?
        statement.bindString(2,buoc1);
        statement.bindString(3,buoc2);
        statement.bindString(4,buoc3);
        statement.bindBlob(5,hinh);
        statement.executeInsert();

    }

    //truy vấn trả dữ liệu
    public Cursor GetData(String sqlVai){
        SQLiteDatabase databaseVai=getReadableDatabase();
        return databaseVai.rawQuery(sqlVai,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
