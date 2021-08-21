package com.example.adopets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void adpet(View view) {
        Intent intent=new Intent(welcome.this,categories.class);
        startActivity(intent);
    }

    public void Accessories(View view) {
        Intent intent=new Intent(welcome.this,Accessories.class);
        startActivity(intent);
    }
}