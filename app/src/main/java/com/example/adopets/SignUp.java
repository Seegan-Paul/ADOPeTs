package com.example.adopets;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
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
            Toast.makeText(SignUp.this,"Password doesn't match",Toast.LENGTH_SHORT).show();
            return;
        }

        if(!(pwd.equals(pwd1))){
            System.out.println(pwd+" "+pwd1);
            Toast.makeText(SignUp.this,"Type the correct password",Toast.LENGTH_SHORT).show();
            return;
        }

        Boolean insertData = db.insert(Fname,user,Phone,Email,pwd);
        if(insertData==true)
            Toast.makeText(SignUp.this,"Data inserted",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(SignUp.this,"Data not inserted",Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(SignUp.this,MainActivity.class);
        intent.putExtra("username",user);
        intent.putExtra("password",pwd);
        startActivity(intent);
    }

    Pattern lowercase=Pattern.compile("^.*[a-z].*$");
    Pattern uppercase=Pattern.compile("^.*[A-Z].*$");
    Pattern number=Pattern.compile("^.*[0-9].*$");
   // Pattern special=Pattern.compile("^.*[^a-zA-Z0-9].*$");

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
    // Only to View database........
    public void view(View view) {
        Cursor res = db.getData();
        if(res.getCount()==0) {
            Toast.makeText(SignUp.this, "Table is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("Serial :"+res.getString(0)+"\n");
            buffer.append("Name :"+res.getString(1)+"\n");
            buffer.append("UserName :"+res.getString(2)+"\n");
            buffer.append("Phone :"+res.getString(3)+"\n");
            buffer.append("Email :"+res.getString(4)+"\n");
            buffer.append("Password :"+res.getString(5)+"\n\n");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
        builder.setCancelable(true);
        builder.setTitle("Login Details");
        builder.setMessage(buffer.toString());
        builder.show();
    }
}