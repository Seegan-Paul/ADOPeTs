package com.example.adopets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void adpet(View view) {
        Intent intent=new Intent(add.this, categories1.class);
        startActivity(intent);
    }

    public void addAccessories(View view) {
        Intent intent=new Intent(add.this, Accessories.class);
        startActivity(intent);
    }
}