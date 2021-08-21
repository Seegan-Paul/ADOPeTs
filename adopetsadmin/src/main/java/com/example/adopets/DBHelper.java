package com.example.adopets;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        super(context, "Userdata.db",null,5);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create Table Login(serial INTEGER PRIMARY KEY AUTOINCREMENT,fname TEXT,uname TEXT,phone TEXT,email TEXT,password TEXT)");
            db.execSQL("create table Doginfo(Pid INTEGER PRIMARY KEY AUTOINCREMENT,image BLOB,age TEXT,weight TEXT,height TEXT,sex TEXT,breed TEXT,price REAL)");
            db.execSQL("create table Catinfo(Pid INTEGER PRIMARY KEY AUTOINCREMENT,image BLOB,age TEXT,weight TEXT,height TEXT,sex TEXT,breed TEXT,price REAL)");
            db.execSQL("create table Birdinfo(Pid INTEGER PRIMARY KEY AUTOINCREMENT,image BLOB,age TEXT,weight TEXT,height TEXT,sex TEXT,breed TEXT,price REAL)");
            db.execSQL("create table Fishinfo(Pid INTEGER PRIMARY KEY AUTOINCREMENT,image BLOB,breed TEXT,price REAL)");
            db.execSQL("create table Accessories(Id INTEGER PRIMARY KEY AUTOINCREMENT,image BLOB,name TEXT,price REAL)");
           // db.execSQL("create table Acce(Pid INTEGER PRIMARY KEY AUTOINCREMENT,image BLOB,age TEXT,weight TEXT,height TEXT,sex TEXT,breed TEXT,price REAL)");
        } catch (Exception e) {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("create Table LoginAdmin(serial INTEGER PRIMARY KEY AUTOINCREMENT,fname TEXT,uname TEXT,phone TEXT,email TEXT,password TEXT)");
       /* try{
           db.execSQL("drop table Acce");
            db.execSQL("create table Accessories(Id INTEGER PRIMARY KEY AUTOINCREMENT,image BLOB,name TEXT,price REAL)");

        } catch (Exception e) {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }*/
        //db.execSQL("drop table if exists Doginfo");
    }

    public Boolean insert(String fname,String uname,String phone,String email,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fname",fname);
        contentValues.put("uname",uname);
        contentValues.put("phone",phone);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result=db.insert("LoginAdmin",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Boolean loginAdmin(String uname,String password){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from LoginAdmin where uname = ? and password = ?", new String[] {uname,password});
        if(cursor.getCount()>0){
            return true;
        }
        else
            return false;
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

    public void insertCat(Bitmap Image,String age,String weight,String height,String sex,String breed,float price) {
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
            long result = database.insert("Catinfo",null,contentValues);
            if(result!=-1){
                Toast.makeText(context,"Data inserted successfully! :)",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    public void insertBird(Bitmap Image,String age,String weight,String height,String sex,String breed,float price) {
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
            long result = database.insert("Birdinfo",null,contentValues);
            if(result!=-1){
                Toast.makeText(context,"Data inserted successfully! :)",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    public void insertFish(Bitmap Image,String breed,float price) {
        SQLiteDatabase database = this.getWritableDatabase();
        byteArrayOutputStream = new ByteArrayOutputStream();
        Image.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        ImageByte = byteArrayOutputStream.toByteArray();

        ContentValues contentValues = new ContentValues();
        contentValues.put("image",ImageByte);
        contentValues.put("breed",breed);
        contentValues.put("price",price);

        try {
            long result = database.insert("Fishinfo",null,contentValues);
            if(result!=-1){
                Toast.makeText(context,"Data inserted successfully! :)",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    public void insertAccessories(Bitmap Image,String item,float price) {
        SQLiteDatabase database = this.getWritableDatabase();
        byteArrayOutputStream = new ByteArrayOutputStream();
        Image.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        ImageByte = byteArrayOutputStream.toByteArray();

        ContentValues contentValues = new ContentValues();
        contentValues.put("image",ImageByte);
        contentValues.put("name",item);
        contentValues.put("price",price);

        try {
            long result = database.insert("Accessories",null,contentValues);
            if(result!=-1){
                Toast.makeText(context,"Data inserted successfully! :)",Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
}
