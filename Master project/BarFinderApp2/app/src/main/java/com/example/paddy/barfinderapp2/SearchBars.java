package com.example.paddy.barfinderapp2;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SearchBars extends AppCompatActivity {


    private EditText postCode;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bars);

        Intent startingIntent = getIntent();
        username = startingIntent.getStringExtra("username");
        postCode = (EditText)findViewById(R.id.postcodeET);
        Toast.makeText(getApplicationContext(), username,
                Toast.LENGTH_SHORT).show();



    }


    public void toMap(View view){
        Intent intent = new Intent(this, PostcodeMapsActivity.class);
        startActivity(intent);
    }
    public void onClick(View view){

        String postcode = postCode.getText().toString();
        if(postcode.equals("")){
            Toast.makeText(getApplicationContext(), "Please enter a postcode",
                    Toast.LENGTH_SHORT).show();
        }else{


            Geocoder geocoder1 = new Geocoder(this);
            try {
                List<Address> addresses1 = geocoder1.getFromLocationName(postcode, 1);
                if (addresses1 != null && !addresses1.isEmpty()) {
                    Address address1 = addresses1.get(0);
                    // Use the address as needed
                    double latitude_start = address1.getLatitude();
                    double longitude_start = address1.getLongitude();
                    String message = "Latitude: "+address1.getLatitude()+", Longitude: "+address1.getLongitude();
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show();

                    String uri = String.format(Locale.ENGLISH, "geo:%f,%f", longitude_start, longitude_start);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(intent);

                } else {
                    // Display appropriate message when Geocoder services are not available
                    Toast.makeText(this, "Unable to geocode zipcode", Toast.LENGTH_LONG).show();
                }
            } catch (IOException e) {
                // handle exception
            }



        }

        }



    public void savePostcode(View view){

        PostCodes postcode = new PostCodes(username, postCode.getText().toString());
        MyDBHandler myDBHandler = new MyDBHandler(this, null, null, 1);
        myDBHandler.addPostcode(postcode);

        Toast.makeText(this, "this is the username "+username, Toast.LENGTH_LONG).show();
    }

    public void goToFavourites(View view){
        Intent intent = new Intent(this, UserFavouritePostcodes.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }



    /** Called when the user clicks the find bars by current location button */
    public void onClicks(View view) {
        Intent intent = new Intent(this, BarMapsActivity.class);
        startActivity(intent);
    }
}
