//=====================================================================
//                      Assignment 2
//
//  Name of file:      ThirdActivity.java
//
//  Description:       Provides control over third screen
//
//=====================================================================

package com.example.tripplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;


public class ThirdActivity extends AppCompatActivity implements OnCheckedChangeListener, OnKeyListener {


    private RadioGroup transportRadioGroup;
    private RadioButton busRadioButton;
    private RadioButton taxiRadioButton;
    private RadioButton planeRadioButton;

    private TextView endLocation_id;
    private TextView startLocation_id;

    private RadioGroup transportRadioGroup2;
    private RadioButton busRadioButton2;
    private RadioButton taxiRadioButton2;
    private RadioButton planeRadioButton2;
    private TextView endLocation_id2;
    private TextView startLocation_id2;

    private Button continueButton;

    private SharedPreferences savedValues;

    private String methodTransport_To = "";
    private String methodTransport_From = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // ==============================
        // GET REFERENCES TO THE WIDGETS
        // ==============================

        continueButton = findViewById(R.id.continueButton);

        endLocation_id = (TextView) findViewById(R.id.endLocation_id);
        startLocation_id = (TextView) findViewById(R.id.startLocation_id);

        transportRadioGroup = (RadioGroup) findViewById(R.id.transportRadioGroup);
        busRadioButton = (RadioButton) findViewById(R.id.busRadioButton);
        taxiRadioButton = (RadioButton) findViewById(R.id.taxiRadioButton);
        planeRadioButton = (RadioButton) findViewById(R.id.planeRadioButton);

        endLocation_id2 = (TextView) findViewById(R.id.endLocation_id2);
        startLocation_id2 = (TextView) findViewById(R.id.startLocation_id2);

        /*
        transportRadioGroup2 = (RadioGroup) findViewById(R.id.transportRadioGroup2);
        busRadioButton2 = (RadioButton) findViewById(R.id.busRadioButton2);
        taxiRadioButton2 = (RadioButton) findViewById(R.id.taxiRadioButton2);
        planeRadioButton2 = (RadioButton) findViewById(R.id.planeRadioButton2);
        */

        // SET THE TEXTVIEW WITH THE GLOBAL VARIABLE "ENDLOCATION & STARTLOCATION"
        // FIRST SET OF PAIRS
        endLocation_id.setText(Global.endLocation);
        startLocation_id.setText(Global.startLocation);
        // Second set of pairs
        //endLocation_id2.setText(Global.startLocation);
        //startLocation_id2.setText(Global.endLocation);

        transportRadioGroup.setOnCheckedChangeListener(this);
        transportRadioGroup.setOnKeyListener(this);

        //transportRadioGroup2.setOnCheckedChangeListener(this);
        //transportRadioGroup2.setOnKeyListener(this);

        // NEXT BUTTON TO SWITCH TO NEXT ACTIVITY
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFourthActivity();
            }
        });

        // get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

    }

    //Checking for Radio Button input
    @Override
    public void onCheckedChanged(RadioGroup transportRadioGroup, int ID) {

        switch (ID) {
            case R.id.busRadioButton:
                methodTransport_To = "Bus";
                Global.methodTransport_To = methodTransport_To;
                break;
            case R.id.taxiRadioButton:
                methodTransport_To = "taxi";
                Global.methodTransport_To = methodTransport_To;
                break;
            case R.id.planeRadioButton:
                methodTransport_To = "Plane";
                Global.methodTransport_To = methodTransport_To;

                break;
        }

    }



    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return false;
    }

    // Open fourth activity
    public void openFourthActivity() {
        int selectedTransportId = transportRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedTransportRadioButton = findViewById(selectedTransportId);
        String selectedTransportText = selectedTransportRadioButton.getText().toString();

        // Set the methodTransport_To variable in Global.java to the selected transport method
        Global.methodTransport_To = selectedTransportText;
        Intent intent = new Intent(this, FourthActivity.class);
        startActivity(intent);
    }
}