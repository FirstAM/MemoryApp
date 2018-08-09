package com.example.memoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnActSec;
    Button btnActThird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnActSec = (Button) findViewById(R.id.GoToActivity1);
        btnActSec.setOnClickListener(this);
        btnActThird = (Button) findViewById(R.id.GoToActivity2);
        btnActThird.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.GoToActivity1:
                Intent firstView = new Intent(this, SecondActivity.class);
                startActivity(firstView);
                break;
            case R.id.GoToActivity2:
                Intent secView = new Intent(this,ThirdActivity.class);
                startActivity(secView);
                break;

        }
    }
}
