//=====================================================================
//                      Assignment 2
//
//  Name of file:      FourthActivity.java
//
//  Description:       Provides control over fourth screen
//
//=====================================================================
package com.example.tripplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FourthActivity extends AppCompatActivity {

    // variables for database that carried over
    private EditText tripStartTxt;
    private EditText tripEndTxt;
    private EditText tripDateTxt;
    private EditText tripMethodTxt;

    private Button addTripBtn, readTripBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        tripStartTxt = findViewById(R.id.idEditTripStartLocation);
        tripEndTxt = findViewById(R.id.idEditTripEndLocation);
        tripDateTxt = findViewById(R.id.idEditTripDate);
        tripMethodTxt = findViewById(R.id.idEditTripMethodTransport);
        addTripBtn = findViewById(R.id.idBtnAddTrip);
        readTripBtn = findViewById(R.id.idBtnReadTrip);

        //set the hint of tripDateTxt to the date variable from Global.java
        //setting hint because it wasn't working otherwise
        tripDateTxt.setHint(Global.date);
        tripStartTxt.setHint(Global.startLocation);
        tripEndTxt.setHint(Global.endLocation);
        tripMethodTxt.setHint(Global.methodTransport_To);

        //setting texts for use later when creating a trip in the database
        tripDateTxt.setText(Global.date);
        tripStartTxt.setText(Global.startLocation);
        tripEndTxt.setText(Global.endLocation);
        tripMethodTxt.setText(Global.methodTransport_To);

        dbHandler = new DBHandler(FourthActivity.this);

        addTripBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String tripStart = tripStartTxt.getText().toString();
                String tripEnd = tripEndTxt.getText().toString();
                String tripDate = tripDateTxt.getText().toString();
                String tripMethod = tripMethodTxt.getText().toString();

                //make sure none of the fields are empty
                if (tripStart.isEmpty() || tripEnd.isEmpty() || tripDate.isEmpty() || tripMethod.isEmpty()){
                    Toast.makeText(FourthActivity.this, "Please make sure no fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                //call on method created to add a new trip to the database
                dbHandler.addNewTrip(tripStart, tripEnd, tripDate, tripMethod);

                //display message confirming addition to database
                Toast.makeText(FourthActivity.this, "Trip added!", Toast.LENGTH_SHORT).show();

                //make the fields empty
                tripStartTxt.setText("");
                tripEndTxt.setText("");
                tripDateTxt.setText("");
                tripMethodTxt.setText("");

            }
        });

        readTripBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //opens a new activity when the user presses this button
                Intent i = new Intent(FourthActivity.this, ViewTrips.class);
                startActivity(i);
            }
        });
    }

}
