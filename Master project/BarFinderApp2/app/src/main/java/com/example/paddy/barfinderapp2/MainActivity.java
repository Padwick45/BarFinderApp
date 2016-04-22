package com.example.paddy.barfinderapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent startingIntent = getIntent();
        username = startingIntent.getStringExtra("username");

        Toast.makeText(getApplicationContext(), "Welcome back "+username,
                Toast.LENGTH_LONG).show();
    }

   public void onClick(View view){


       Intent intent = new Intent(this, SearchBars.class);
       intent.putExtra("username", username);
       startActivity(intent);
   }




}
