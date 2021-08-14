package com.example.adopets;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    Context context;

    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 2);
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
        //db.execSQL("drop table if exists Login");
       // db.execSQL("create Table Login(serial INTEGER PRIMARY KEY AUTOINCREMENT,fname TEXT,uname TEXT,phone TEXT,email TEXT,password TEXT)");
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

    public ArrayList<ModelClass> getDogs() {
        try {
            SQLiteDatabase database = this.getReadableDatabase();
            ArrayList<ModelClass> ModelClassList = new ArrayList<>();
            Cursor cursor = database.rawQuery("select * from Doginfo",null);
            if(cursor.getCount()>0) {
                while (cursor.moveToNext()) {
                    byte [] ImageByte = cursor.getBlob(1);
                    String age = cursor.getString(2);
                    String breed = cursor.getString(6);
                    float price = cursor.getFloat(7);

                    Bitmap image = BitmapFactory.decodeByteArray(ImageByte,0,ImageByte.length);
                    ModelClassList.add(new ModelClass(image,age,breed,price));
                }
                return ModelClassList;
            }
            else{
                Toast.makeText(context,"Database is Empty",Toast.LENGTH_LONG).show();
                return null;
            }
        } catch (Exception e){
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
            return null;
        }
    }
}
