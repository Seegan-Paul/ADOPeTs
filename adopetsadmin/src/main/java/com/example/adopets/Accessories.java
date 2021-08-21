package com.example.adopets;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

public class Accessories extends AppCompatActivity {

    ImageView image;
    EditText item,price;

    DBHelper db;

    private static final int IMAGE_PICK=1000;
    private static final int PERMISSION_CODE=1001;
    private Bitmap imageStore;
    private Uri imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessories);

        image = findViewById(R.id.imageView4);
        item = findViewById(R.id.editTextTextPersonName64);
        price = findViewById(R.id.editTextTextPersonName74);

        db = new DBHelper(this);
    }

    public void select(View view) {
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
        try {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            resultLauncher.launch(intent);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

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
                                Toast.makeText(Accessories.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }
            });

    public void Add(View view) {
        try {
            db.insertAccessories(imageStore,item.getText().toString(),Float.valueOf(price.getText().toString()));
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

}