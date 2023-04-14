//=====================================================================
//                      Assignment 2
//
//  Name of file:      ViewTrips.java
//
//  Description:       Provides control over list of selected trips
//
//=====================================================================
// Taken from https://www.geeksforgeeks.org/how-to-read-data-from-sqlite-database-in-android/

package com.example.tripplanner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Mostly taken from https://www.geeksforgeeks.org/how-to-read-data-from-sqlite-database-in-android/
// and adapted to suit our needs
public class ViewTrips extends AppCompatActivity {

    // creating variables for array list, database handler, recycle view adapter and recycle view
    private ArrayList<TripModal> tripModalArrayList;
    private DBHandler dbHandler;
    private TripRVAdapter tripRVAdapter;
    private RecyclerView tripsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trips);

        // initialize variables
        tripModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewTrips.this);

        // get the trip array list from db handler
        tripModalArrayList = dbHandler.readTrips();

        // pass array to adapter
        tripRVAdapter = new TripRVAdapter(tripModalArrayList, ViewTrips.this);
        tripsRV = findViewById(R.id.idRVTrips);

        // set layout manager for recycle view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewTrips.this, RecyclerView.VERTICAL, false);
        tripsRV.setLayoutManager(linearLayoutManager);

        // set the adapter to recycler view
        tripsRV.setAdapter(tripRVAdapter);
    }
}
