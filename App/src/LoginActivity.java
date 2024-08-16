package com.example.expence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN=
            Pattern.compile("^"+
                    "(?=.*[0-9])"+  //at least 1 digit
                    "(?=.*[a-z])"+  //at least 1 lower case letter
                    "(?=.*[A-Z])"+  //at least 1 upper case letter
                    "(?=.*[@#$%^&+=])"+  //at least 1 special character
                    "(?=.\\S+$)"+  //no white spaces
                    ".{6,}"+  //at least 6 characters
                    "$");

    EditText username ,password;
    Button btnlogin;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.username1);
        password=findViewById(R.id.password1);

        btnlogin=findViewById(R.id.btnlogin1);
        db=new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String user=username.getText().toString();
                    String pass=password.getText().toString();

                    if(user.equals("")||pass.equals("")){
                        Toast.makeText(LoginActivity.this, "Please Enter All the fields", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        boolean checkuser=db.checkusername(user);
                        if(checkuser==true){
                            boolean checkpassupdate=db.updatepassword(user,pass);
                            if(checkpassupdate==true){
                            Toast.makeText(LoginActivity.this,"Password Updated Successfully",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }}else {
                            Toast.makeText(LoginActivity.this, "User Does NOT Exists...", Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passinput=password.getText().toString().trim();
                if(!PASSWORD_PATTERN.matcher(passinput).matches()){
                    password.setError("Password too Weak");
                }
            }
        });

    }
}