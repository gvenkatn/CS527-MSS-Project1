package com.example.pinger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class results extends AppCompatActivity {

    public static final String RESULT = "Result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        Intent intent = getIntent();
        String messageText = intent.getStringExtra(RESULT) ;
        TextView resultView = (TextView) findViewById(R.id.resultsText);
        resultView.setText (messageText) ;
    }

    public void backToPinger (View view) {
        //"Back" button onClick

        Intent intent = new Intent (this, pinger. class);
        startActivity(intent) ;
        finish();
    }

    public void backToMenu (View view) {
        // "Main Menu" onClick
        
        Intent intent = new Intent (this, MainActivity. class);
        startActivity (intent) ;
        finish();
    }
}