package com.example.pinger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToPinger (View view) {
        //"Ping Tool" button onClick

        Intent intent = new Intent (this, pinger. class);
        startActivity (intent) ;
        finish();
    }
}