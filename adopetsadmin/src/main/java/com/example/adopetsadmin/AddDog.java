package com.example.adopetsadmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class AddDog extends AppCompatActivity {

    ImageView image;
    EditText age,weight,height,sex,breed,price;
    float Price;

    private static final int IMAGE_PICK=1000;
    private static final int PERMISSION_CODE=1001;
    private Bitmap ImageStore;

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
    }

    public void select(View view) {
        //checking runtime permission
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permission,PERMISSION_CODE);
            }else{
                pickImage();
            }
        }else{
            //os<marshmallow
            pickImage();
        }

    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImage();
                }else {
                    Toast.makeText(this,"Permission Denied!",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK) {
            image.setImageURI(data.getData());
            try {
                ImageStore = MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
                Toast.makeText(this,"Success",Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
    }
}