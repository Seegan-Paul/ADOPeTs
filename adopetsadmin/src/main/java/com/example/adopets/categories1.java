package com.example.adopets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class categories1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories1);
    }

    public void showDogs(View view) {
        Intent intent = new Intent(categories1.this, AddDog.class);
        startActivity(intent);
    }

    public void showFish(View view) {
        Intent intent1 = new Intent(categories1.this, AddFish.class);
        startActivity(intent1);
    }

    public void showsBirds(View view) {
        Intent intent2 = new Intent(categories1.this, AddBird.class);
        startActivity(intent2);
    }

    public void showCats(View view) {
        Intent intent3 = new Intent(categories1.this, AddCat.class);
        startActivity(intent3);
    }
}