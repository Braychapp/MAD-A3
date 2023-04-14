package com.example.tripplanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

// note - this code is heavily based on this link - https://www.geeksforgeeks.org/broadcast-receiver-in-android-with-example/
// and adapted to suit our application's needs

// create the AirplaneModeChangeReceiver class from the parent BroadcastReceiver
public class AirplaneModeChangeReceiver extends BroadcastReceiver {

    // string variables holding messages to toast, depending on airplane mode status
    private String airplaneModeOn = new String ("Airplane mode is on - enjoy your trip!");
    private String airplaneModeOff = new String ("Airplane mode is off");

    @Override
    public void onReceive(Context context, Intent intent){
        // log airplane mode change request
        Log.d("TripPlanner", "Airplane mode status change");

        // check to see if the airplane mode is on by calling function below and checking value
        if (airplaneModeOnStatus(context.getApplicationContext())){
            Toast.makeText(context, airplaneModeOn, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context, airplaneModeOff, Toast.LENGTH_LONG).show();
        }
    }

    private static boolean airplaneModeOnStatus(Context context){
        // check the system settings and returns 0 if airplane mode is off
        return Settings.System.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }
}
