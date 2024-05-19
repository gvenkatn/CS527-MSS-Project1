package com.example.pinger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.io.IOException;


public class pinger extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pinger);
    }

    public String terminalPing(String inputIPText) throws IOException {
        // Initialize a shell process (sh)
        // Execute the ping in the shell process 
        // Direct the command's/terminal's output to the application

        String result = "";

        //Initialize the shell process
        Process ping = new ProcessBuilder("sh").redirectErrorStream(true).start();
        DataOutputStream os = new DataOutputStream(ping.getOutputStream());

        // Execute the ping in the shell process 
        os.writeBytes("ping -c 5 " + inputIPText + '\n');
        os.flush();

        // Close the terminal
        os.writeBytes("exit\n");
        os.flush();

        // Direct the command's/terminal's output to the application
        BufferedReader reader = new BufferedReader(new InputStreamReader(ping.getInputStream()));
        String line;


        //Format the output
        ArrayList<String> outputLines = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            outputLines.add(line + "\n");
        }

        while(outputLines.size() < 10){
            outputLines.add(1,"Request Timed Out. \n");
        }

        for(String linePtr:outputLines)
                result += linePtr;

        return result;
    }

    public void goToResults (View view) throws IOException{
        // "Ping" button onClick()
    
        Executors.newSingleThreadExecutor().execute(() -> {
            
            runOnUiThread(() -> {
                // Update the view in activity with "Loading"

                TextView loading = (TextView)findViewById(R.id.loading);
                loading.setText("Loading...");
            });

            // Ping in the Background
            String ipAddress = "";
            String resultText = "";

            //Extract the text from the TextBox
            EditText inputIPView = (EditText) findViewById (R.id.inputIP);
            String inputIPText = inputIPView.getText().toString ();

            //Create policy and provide permission to access the android OS
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
                                                            .Builder()
                                                            .permitAll()
                                                            .build();
            StrictMode.setThreadPolicy(policy);

            //Ping the provided IP Address
            try {
                resultText = terminalPing(inputIPText);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Pass the results to the next Activity
            Intent intent = new Intent(this, results.class);
            intent. putExtra (results.RESULT, resultText) ;
            startActivity(intent);
            finish();

        });
    }

    public void backToMenu (View view) {
        // "Main Menu" button onClick()

        Intent intent = new Intent (this, MainActivity. class);
        startActivity (intent) ;
        finish();
    }
}