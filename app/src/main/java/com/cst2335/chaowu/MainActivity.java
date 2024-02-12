package com.cst2335.chaowu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static String TAG="MainActivity";
    @Override
    protected void onStart() {
        super.onStart();
        Log.w(TAG,"The application is now visible on screen.");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG,"The application is now responding to user input");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG,"The application is no longer visible.\n");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG,"The application no longer responds to user input");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG,"Any memory used by the application is freed");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w( "MainActivity", "In onCreate() - Loading Widgets" );
        EditText emailEditText = findViewById(R.id.editTextTextEmailAddress);
        Button loginButton = findViewById(R.id.button);
        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String emailAddress = prefs.getString("LoginName", "");
        emailEditText.setText(emailAddress);
        loginButton.setOnClickListener(clk->{
            String enteredEmail = emailEditText.getText().toString();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("LoginName", enteredEmail);
            editor.apply();

            Intent nextPage = new Intent(MainActivity.this, SecondActivity.class);
            nextPage.putExtra("EmailAddress",enteredEmail);
            startActivity(nextPage);
        });


    }


}