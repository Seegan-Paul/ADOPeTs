package com.example.adopets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Sign_up extends AppCompatActivity {

    EditText username,password,password1,fname,email,phone;
    Button signup;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username=(EditText)findViewById(R.id.editTextTextPersonName2);
        password=(EditText)findViewById(R.id.editTextTextPassword2);
        password1=(EditText)findViewById(R.id.editTextTextPassword3);
        fname=(EditText)findViewById(R.id.editTextTextPersonName);
        email=(EditText)findViewById(R.id.editTextTextEmailAddress);
        phone=(EditText)findViewById(R.id.editTextPhone);
        signup=(Button)findViewById(R.id.button);

        db=new DBHelper(this);
    }

    public void signup(View view) {
        String user=username.getText().toString();
        String pwd=password.getText().toString();
        String pwd1=password1.getText().toString();
        String Fname=fname.getText().toString();
        String Email=email.getText().toString();
        String Phone=phone.getText().toString();

        if(!isValidPassword(pwd)){
            Toast.makeText(Sign_up.this,"Password doesn't match",Toast.LENGTH_SHORT).show();
            return;
        }

        if(!(pwd.equals(pwd1))){
            System.out.println(pwd+" "+pwd1);
            Toast.makeText(Sign_up.this,"Type the correct password",Toast.LENGTH_SHORT).show();
            return;
        }

        Boolean insertData = db.insert(Fname,user,Phone,Email,pwd);
        if(insertData==true)
            Toast.makeText(Sign_up.this,"Data inserted",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(Sign_up.this,"Data not inserted",Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(Sign_up.this,MainActivity.class);
        //intent.putExtra("username",user);
        //intent.putExtra("password",pwd);
        startActivity(intent);
    }

    Pattern lowercase=Pattern.compile("^.*[a-z].*$");
    Pattern uppercase=Pattern.compile("^.*[A-Z].*$");
    Pattern number=Pattern.compile("^.*[0-9].*$");

    private boolean isValidPassword(String pwd) {
        if(pwd.length()<8)
            return false;
        if(!lowercase.matcher(pwd).matches())
            return false;
        if(!uppercase.matcher(pwd).matches())
            return false;
        if(!number.matcher(pwd).matches())
            return false;
        // if(!special.matcher(pwd).matches())
        //   return false;

        return true;
    }
}