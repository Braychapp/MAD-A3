
//=====================================================================
//                      Assignment 2
//
//  Name of file:       MainActivity.java
//
//  Description:       Provides control over first screen
//
//=====================================================================
package com.example.tripplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView startText;
    private String username = "";
    private ArrayList<Trip> tripsList;
    private String[] destinations;

    MediaPlayer mediaPlayer;


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

        new LoadJsonTask().execute();
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
