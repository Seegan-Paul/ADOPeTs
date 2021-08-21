package com.example.adopets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Detail extends AppCompatActivity {

    ImageView image;
    Bitmap bitmap;
    TextView age,weight,height,sex,breed,price;
    Button button;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        image = findViewById(R.id.imageView99);
        age = findViewById(R.id.textView20);
        weight = findViewById(R.id.textView21);
        height = findViewById(R.id.textView19);
        sex = findViewById(R.id.textView18);
        breed = findViewById(R.id.textView17);
        price = findViewById(R.id.textView16);
        button= findViewById(R.id.button11);
        db = new DBHelper(this);

        try {
            byte [] byteArray = getIntent().getByteArrayExtra("image");
            bitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
            image.setImageBitmap(bitmap);
            age.setText(getIntent().getStringExtra("age"));
            weight.setText(getIntent().getStringExtra("weight"));
            height.setText(getIntent().getStringExtra("height"));
            sex.setText(getIntent().getStringExtra("sex"));
            breed.setText(getIntent().getStringExtra("breed"));
            price.setText(Float.toString(getIntent().getFloatExtra("price",0)));
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    public void click(View view) {
        Toast toast;
        if(db.Delete(getIntent().getIntExtra("pid",0),breed.getText().toString())){
           toast = Toast.makeText(this,"Thank you \n Your order is placed",Toast.LENGTH_LONG);
           toast.setGravity(Gravity.CENTER,0,0);
           toast.show();
           Intent intent = new Intent(Detail.this,welcome.class);
           startActivity(intent);
        }
    }
}