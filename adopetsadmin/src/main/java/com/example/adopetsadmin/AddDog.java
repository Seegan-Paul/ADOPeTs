package com.example.adopetsadmin;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
//import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;

public class AddDog extends AppCompatActivity {

    ImageView image;
    EditText age,weight,height,sex,breed,price;
    //float Price;
    DBHelper db;

    private static final int IMAGE_PICK=1000;
    private static final int PERMISSION_CODE=1001;
    private Bitmap imageStore;
    private Uri imagePath;

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
//        Price = Float.valueOf(price.getText().toString());
        db = new DBHelper(this);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
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

    private void pickImage() {
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        startActivityForResult(intent,IMAGE_PICK);
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            resultLauncher.launch(intent);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
//
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK) {
//            image.setImageURI(data.getData());
//            try {
//                ImageStore = MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
//                Toast.makeText(this,"Success",Toast.LENGTH_LONG).show();
//            } catch (Exception e) {
//                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
//            }
//        }
//    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        if(data!=null && data.getData()!=null){
                            try {
                                imagePath = data.getData();
                                imageStore = MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);

                                image.setImageBitmap(imageStore);
                            }catch (Exception e){
                                Toast.makeText(AddDog.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }
            });

    public void Add(View view) {
        try {
            db.insertDog(imageStore,age.getText().toString(),weight.getText().toString(),height.getText().toString(),sex.getText().toString(),breed.getText().toString(),Float.valueOf(price.getText().toString()));
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
}