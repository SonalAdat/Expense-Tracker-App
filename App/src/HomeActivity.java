package com.example.expence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button addexpense,addincome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addexpense=findViewById(R.id.btnaddexpense);
        addincome=findViewById(R.id.btnaddincome);

        addexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ExpenseActivity.class);
                startActivity(intent);
            }
        });

        addincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),IncomeActivity.class);
                startActivity(intent);
            }
        });
    }
}