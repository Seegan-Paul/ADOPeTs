package com.example.adopets;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Login(serial INTEGER PRIMARY KEY AUTOINCREMENT,fname TEXT,uname TEXT,phone TEXT,email TEXT,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Login");
    }

    public Boolean insert(String fname,String uname,String phone,String email,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fname",fname);
        contentValues.put("uname",uname);
        contentValues.put("phone",phone);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result=db.insert("Login",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Login",null);
        return cursor;
    }

    public Boolean login(String uname,String password){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Login where uname = ? and password = ?", new String[] {uname,password});
        if(cursor.getCount()>0){
            return true;
        }
        else
            return false;
    }
}
