package com.example.paddy.barfinderapp2;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class PostcodeMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double latitude_start, longitude_start;
    private LatLng latLng;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postcode_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    public void locationFromPostCode(String postCode){




    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        Intent intent = getIntent();
        String postCode = intent.getStringExtra("Postcode");

        Geocoder geocoder1 = new Geocoder(this);
     //   try {

          //  List<Address> addresses1 = geocoder1.getFromLocationName(postCode, 1);
          //  if (addresses1 != null && !addresses1.isEmpty()) {
          //      Address address1 = addresses1.get(0);
                // Use the address as needed
          //      latitude_start = address1.getLatitude();
           //     longitude_start = address1.getLongitude();
           //     String message = "Latitude: "+address1.getLatitude()+", Longitude: "+address1.getLongitude();
            //    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
//

          //  } else {
                // Display appropriate message when Geocoder services are not available
           //     Toast.makeText(this, "Unable to geocode zipcode", Toast.LENGTH_LONG).show();
           //     latitude_start = 0;
          //      longitude_start = 0;
         //   }

          //  LatLng latLng = new LatLng(latitude_start, longitude_start);

     //   } catch (IOException e) {
      //      // handle exception
      //  }

        //mMap.addMarker(new MarkerOptions().position(new LatLng(currentLatitude, currentLongitude)).title("Current Location"));
      //  MarkerOptions options = new MarkerOptions()
          //      .position(latLng)
          //      .title("I am here!");
      //  mMap.addMarker(options);
      //  mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        // Moving Camera to a Location with animation

      //  CameraPosition cameraPosition = new CameraPosition.Builder()
     //           .target(latLng).zoom(12).build();

     //   mMap.animateCamera(CameraUpdateFactory
      //          .newCameraPosition(cameraPosition));


    }

    public void onSearch(View view) throws IOException {
        EditText searchET = (EditText)findViewById(R.id.searchET);
        String search = searchET.getText().toString();
        List<Address> addressList;

        if(search!=null||!search.equals(""))
        {
            Geocoder geocoder = new Geocoder(this);
            addressList = geocoder.getFromLocationName(search, 1);

            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

            mMap.addMarker(new MarkerOptions().position(latLng)).setTitle("Marker");
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));







        }
    }

}