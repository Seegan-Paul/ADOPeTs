package com.example.adopets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.Toast;

public class Dog extends AppCompatActivity {

    DBHelper db;
    RecyclerView RV;
    RVadapter RVa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog);

        try {
            RV = findViewById(R.id.RecyclerView);
            db = new DBHelper(this);
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

        show();
    }

    public void show() {
        try {
            RVa = new RVadapter(db.getDogs());
          //  RV.setHasFixedSize(true);
          //  RV.setLayoutManager(new LinearLayoutManager(this));
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
            RV.setLayoutManager(gridLayoutManager);
            RV.setAdapter(RVa);
        }catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}