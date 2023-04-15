
//=====================================================================
//                      Assignment 2
//
//  Name of file:       MainActivity.java
//
//  Description:       Provides control over first screen
//
//=====================================================================
package com.example.tripplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // create new instance of AirplaneModeChangeReceiver - based on this code and adapted
    // to suit needs https://www.geeksforgeeks.org/broadcast-receiver-in-android-with-example/

    AirplaneModeChangeReceiver airplaneModeChangeReceiver = new AirplaneModeChangeReceiver();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView startText;
    private String username = "";
    private ArrayList<Trip> tripsList;
    private String[] destinations;
    private MediaPlayer mediaPlayer;
    private static final int PERMISSIONS_REQUEST_LOCATION = 1001;
    private boolean locationPermissionGranted = false;
    private LocationManager locationManager;

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            // Do something with the location, such as displaying it on the screen
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            Log.d("Location", "Latitude: " + latitude + ", Longitude: " + longitude);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        setContentView(R.layout.activity_main);

        Log.i(Global.MTAG, "onCreate: MainActivity started");

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        startText = findViewById(R.id.username);
        username = "";
        startText.setText(username);

        tripsList = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_LOCATION);
            } else {
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (locationManager != null) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                }
            }
        } else {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (locationManager != null) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }

        new LoadJsonTask().execute();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission granted
                locationPermissionGranted = true;
                //the line below causes an error and won't let me compile for some reason so it stays commented out upon submission
                //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            } else {
                // permission denied
                locationPermissionGranted = false;
                // Notify the user that location access is required
                Toast.makeText(this, "Location access is required to use this app", Toast.LENGTH_LONG).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    @Override
    protected void onStart(){
        super.onStart();

        // create the intent filter to define what the airplane mode broadcaster
        // is allowed to receive when the application starts

        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(airplaneModeChangeReceiver, filter);
    }

    @Override
    protected void onStop(){
        super.onStop();
        // unregister the receiver when the application stops
        unregisterReceiver(airplaneModeChangeReceiver);
    }

    private class LoadJsonTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String json = null;
            try {
                InputStream is = getAssets().open("destinations.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            if (json != null) {
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    destinations = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        destinations[i] = jsonArray.getString(i);
                    }

                    Random random = new Random();

                    for (int i = 0; i < 5; i++) {
                        String start = destinations[random.nextInt(destinations.length)];
                        String end = destinations[random.nextInt(destinations.length)];
                        tripsList.add(new Trip(start, end));
                    }

                    mAdapter = new TripAdapter(tripsList);
                    recyclerView.setAdapter(mAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Stop the service to stop the music playback
        stopService(new Intent(this, MusicService.class));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null)
            mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }



}
