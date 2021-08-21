package com.example.adopets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class categories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
    }

    public void showDogs(View view) {
        Intent intent = new Intent(categories.this,Dog.class);
        startActivity(intent);
    }

    public void showCats(View view) {
        Intent intent = new Intent(categories.this,Cat.class);
        startActivity(intent);
    }

    public void showBirds(View view) {
        Intent intent = new Intent(categories.this,Bird.class);
        startActivity(intent);
    }

    public void showFish(View view) {
        Intent intent = new Intent(categories.this,Fish.class);
        startActivity(intent);
    }
}