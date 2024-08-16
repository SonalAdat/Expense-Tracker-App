package com.example.expence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN=
            Pattern.compile("^"+
                    "(?=.*[0-9])"+  //at least 1 digit
                    "(?=.*[a-z])"+  //at least 1 lower case letter
                    "(?=.*[A-Z])"+  //at least 1 upper case letter
                    "(?=.*[@#$%^&+=])"+  //at least 1 special character
                    "(?=.\\S+$)"+  //no white spaces
                    ".{6,}"+  //at least 6 characters
                    "$");

    EditText uname, pwd;
    Button login,signup;
    TextView forgot;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname=findViewById(R.id.username);
        pwd=findViewById(R.id.password);
        forgot=findViewById(R.id.btnforgetpass);

        login=findViewById(R.id.btnlogin);
        signup=findViewById(R.id.btnsignup);
        db=new DBHelper(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                //startActivity(intent);
                String user=uname.getText().toString();
                String pass=pwd.getText().toString();

                if(user.equals("")||pass.equals("")){
                    Toast.makeText(MainActivity.this, "Please Enter All the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean checkuserpass=db.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(MainActivity.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this, "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=uname.getText().toString();
                String pass=pwd.getText().toString();
                if(user.equals("")||pass.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Please Enter All fields...", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean checkuser=db.checkusername(user);
                    if(checkuser==false){
                        boolean insert=db.insertdata(user,pass);
                        if(insert==true){
                            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "User already exists please login", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passinput=pwd.getText().toString().trim();
                if(!PASSWORD_PATTERN.matcher(passinput).matches()){
                    pwd.setError("Password too Weak");
                }
            }
        });
    }
}