package com.example.adopets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailFish extends AppCompatActivity {

    ImageView image;
    Bitmap bitmap;
    TextView breed,price;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fish);

        image = findViewById(R.id.imageView992);
        breed = findViewById(R.id.textView172);
        price = findViewById(R.id.textView162);

        db = new DBHelper(this);

        try {
            byte [] byteArray = getIntent().getByteArrayExtra("image");
            bitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
            image.setImageBitmap(bitmap);
            breed.setText(getIntent().getStringExtra("breed"));
            price.setText(Float.toString(getIntent().getFloatExtra("price",0)));
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    public void click(View view) {
        Toast toast;
        if(db.DeleteFish(getIntent().getIntExtra("pid",0),breed.getText().toString())){
            toast = Toast.makeText(this,"Thank you \n Your order is placed",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            Intent intent = new Intent(DetailFish.this,welcome.class);
            startActivity(intent);
        }
    }
}