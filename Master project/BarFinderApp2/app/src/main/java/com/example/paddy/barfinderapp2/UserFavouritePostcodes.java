package com.example.paddy.barfinderapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserFavouritePostcodes extends AppCompatActivity {

    private String username;
    private TextView postcodeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_favourite_postcodes);

        Intent startingIntent = getIntent();
        username = startingIntent.getStringExtra("username");

        getPostcodes();

    }

    public void getPostcodes(){
        MyDBHandler myDBHandler = new MyDBHandler(this, null, null, 1);
        String postcode = myDBHandler.getPostcode(username);

        postcodeTV = (TextView)findViewById(R.id.favouritePostcodesTV);
        postcodeTV.setText(postcode);





    }
}
