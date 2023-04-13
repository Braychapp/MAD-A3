//=====================================================================
//                      Assignment 2
//
//  Name of file:    DateActivity.java
//
//  Description:       Provides control over calendar screen
//
//=====================================================================
package com.example.tripplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        calendarView = findViewById(R.id.calendarView);
        continueButton = findViewById(R.id.button);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = dayOfMonth + "/" + (month+1) + "/" + year;
                Global.date = selectedDate;
            }
        });

//        continueButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Start the SecondActivity
//                Intent intent = new Intent(DateActivity.this, SecondActivity.class);
//                startActivity(intent);
//            }
//        });


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //starting the weather activity
                Intent intent = new Intent(WeatherActivity.this, WeatherActivity.class);
                startActivity(intent);
            }
        });
    }
}
