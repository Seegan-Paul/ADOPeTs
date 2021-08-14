package com.example.adopets;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class DBHelper extends SQLiteOpenHelper {

    Context context;

    private ByteArrayOutputStream byteArrayOutputStream;
    private byte [] ImageByte;


    public DBHelper( Context context) {
        super(context, "Userdata.db",null,2);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create Table Login(serial INTEGER PRIMARY KEY AUTOINCREMENT,fname TEXT,uname TEXT,phone TEXT,email TEXT,password TEXT)");
            db.execSQL("create table Doginfo(Pid INTEGER PRIMARY KEY AUTOINCREMENT,image BLOB,age TEXT,weight TEXT,height TEXT,sex TEXT,breed TEXT,price REAL)");
        } catch (Exception e) {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("drop table if exists Doginfo");
    }

    public void insertDog(Bitmap Image,String age,String weight,String height,String sex,String breed,float price) {
        SQLiteDatabase database = this.getWritableDatabase();
        byteArrayOutputStream = new ByteArrayOutputStream();
        Image.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        ImageByte = byteArrayOutputStream.toByteArray();

        ContentValues contentValues = new ContentValues();
        contentValues.put("image",ImageByte);
        contentValues.put("age",age);
        contentValues.put("weight",weight);
        contentValues.put("height",height);
        contentValues.put("sex",sex);
        contentValues.put("breed",breed);
        contentValues.put("price",price);

        try {
            long result = database.insert("Doginfo",null,contentValues);
            if(result!=-1){
                Toast.makeText(context,"Data inserted successfully! :)",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
}
