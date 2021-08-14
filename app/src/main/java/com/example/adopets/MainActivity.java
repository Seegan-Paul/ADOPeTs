package com.example.adopets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button Signup,Login;
    EditText uname,password;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        Signup=(Button)findViewById(R.id.button6);
        Login =(Button)findViewById(R.id.button5);
        uname = (EditText)findViewById(R.id.editTextTextPersonName3);
        password = (EditText)findViewById(R.id.editTextTextPassword);
        db = new DBHelper(this);
    }

    public void onClick(View view) {
        Intent intent=new Intent(MainActivity.this,SignUp.class);
        startActivity(intent);
    }

    public void login(View view) {
        String user = uname.getText().toString();
        String pwd = password.getText().toString();

        Boolean loginData = db.login(user,pwd);
        if(loginData==true) {
            Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
            Intent intent2=new Intent(MainActivity.this,welcome.class);
            startActivity(intent2);
        }
        else
            Toast.makeText(MainActivity.this,"Login Fail",Toast.LENGTH_SHORT).show();
    }
}