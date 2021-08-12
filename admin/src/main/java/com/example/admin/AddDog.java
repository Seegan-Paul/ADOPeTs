package com.example.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddDog extends AppCompatActivity {

    private static final int PICK_IMAGE=100;
    private Uri imageFilePath;
    private Bitmap ImageStore;
    DBHelper db;

    ImageView image;
    EditText age,weight,height,sex,breed,price;
    float Price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog);

        image = findViewById(R.id.imageView);
        age = findViewById(R.id.editTextTextPersonName2);
        weight = findViewById(R.id.editTextTextPersonName3);
        height = findViewById(R.id.editTextTextPersonName4);
        sex = findViewById(R.id.editTextTextPersonName5);
        breed = findViewById(R.id.editTextTextPersonName6);
        price = findViewById(R.id.editTextTextPersonName7);
        Price = Float.valueOf(price.getText().toString());

        db = new DBHelper(this);
    }

    public void select(View view) {
        try {
            Intent intent=new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent,PICK_IMAGE);

        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {

        try {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==PICK_IMAGE && resultCode==RESULT_OK && data!=null && data.getData()!=null){
                imageFilePath = data.getData();
                ImageStore = MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);

                image.setImageBitmap(ImageStore);
            }
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void store(View view) {
        try {
            db.storeDog(new ModelClass(age.getText().toString(),weight.getText().toString(),height.getText().toString(),sex.getText().toString(),breed.getText().toString(),Price,ImageStore));
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}